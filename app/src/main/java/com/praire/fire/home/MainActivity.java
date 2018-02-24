package com.praire.fire.home;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.praire.fire.BuildConfig;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.home.fragment.MapFragmentNew;
import com.praire.fire.utils.version.ProgressResponseBody;
import com.praire.fire.utils.version.VersionInfo;
import com.praire.fire.home.fragment.DownloadDialogFragment;
import com.praire.fire.home.fragment.HomeFragment;
import com.praire.fire.home.fragment.MyFragment;
import com.praire.fire.home.fragment.NewVersionDialogFragment;
import com.praire.fire.home.fragment.OrderFragment;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okio.BufferedSink;
import okio.Okio;


/**
 * 主页
 *
 * @author lyp
 * @date 2017/12/27
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;

    private String searchKey = "0";
    private MyFragment myFragment;
    private HomeFragment homeFragment;


    public static void startActivity(Context context, int type, boolean forResult) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.UI_TYPE, type);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        checkVersionHandler.postDelayed(checkNewVersionRunnable, 1000);
        myFragment = new MyFragment();
        myFragment.setOnClickShopListner(new MyFragment.OnClickShopListner() {
            @Override
            public void setOnClickShopListner(int index) {
                if (index == 2) {
                    bottomNavigationBar.selectTab(2);
                }
                if (index == 1) {
                    bottomNavigationBar.selectTab(1);
                }
            }
        });
        homeFragment = new HomeFragment();
        homeFragment.setOnClickShopListner(new HomeFragment.OnClickShopListner() {

            @Override
            public void setOnClickShopListner(String key) {
                setSearchKey(key);
                bottomNavigationBar.selectTab(1);
            }
        });

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//MODE_SHIFTING
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_page1, getString(R.string.index)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.home_map, getString(R.string.map)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.home_order, getString(R.string.order)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.home_my, getString(R.string.my)).setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        //从店铺点击进入
        if (getIntent() != null ) {
            bottomNavigationBar.selectTab(getIntent().getIntExtra(Constants.UI_TYPE, 0));
        }

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }


    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 设置默认的页面（首页）
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, homeFragment);
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(new MapFragmentNew());
        fragments.add(new OrderFragment());
        fragments.add(myFragment);
        return fragments;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            if (data != null) {
                setSearchKey(data.getStringExtra(Constants.SEARCH_KEY));
                bottomNavigationBar.selectTab(1);
            }
        }
    }


    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


//----------------版本自动升级-----------开始-------------------------------

    DownloadDialogFragment downloadDialogFragment;
    ProgressResponseBody.ProgressListener progressListener;
    Handler checkVersionHandler = new Handler();
    NewVersionDialogFragment newVersionDialogFragment;
    Call call;

    Runnable checkNewVersionRunnable = new Runnable() {
        @Override
        public void run() {
            checkNewVersion();
        }
    };

    public void checkNewVersion() {
        OkhttpRequestUtil.get(ConstanUrl.CHECK_VERSION, 1, false, uiHandler);
    }

    @Override
    protected void networkResponse(Message msg) {

        switch (msg.what) {
            case 1:
                VersionInfo versionInfo = new Gson().fromJson((String) msg.obj, VersionInfo.class);
                if (versionInfo.getCode() == 1) {
                    if (null != versionInfo.getVersion()) {
                        compareVersion(versionInfo.getVersion());
                    }
                }
                break;
            default:
                break;
        }
    }

    public void compareVersion(VersionInfo.VersionBean version) {

        if (Integer.valueOf(version.getVersioncode()) > BuildConfig.VERSION_CODE) {
            findNewVersion(version);
        }
    }

    public void findNewVersion(final VersionInfo.VersionBean version) {
        newVersionDialogFragment = NewVersionDialogFragment.newInstance(new NewVersionDialogFragment.NewVersionDialogListener() {
            @Override
            public void onDialogPositiveClick(DialogFragment dialog) {
                showDownloadDialog(version.getDownurl());
                dialog.dismissAllowingStateLoss();
            }

            @Override
            public void setNegativeClick(DialogFragment dialog) {
                dialog.dismissAllowingStateLoss();
            }
        }, version.getDesc());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(newVersionDialogFragment, "NEW_VERSION");
        transaction.commitAllowingStateLoss();

    }

    public void showDownloadDialog(final String url) {
        downloadDialogFragment = DownloadDialogFragment.newInstance(new DownloadDialogFragment.DownloadDialogFragmentListener() {
            @Override
            public void onRetryClick(DialogFragment dialog) {
                retryDownload(url);
            }

            @Override
            public void setCancelClick(DialogFragment dialog) {
                dialog.dismissAllowingStateLoss();
                cancelDownload();
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(downloadDialogFragment, "DOWNLOAD");
        transaction.commitAllowingStateLoss();
        download(url);
    }

    public void download(final String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        progressListener = new ProgressResponseBody.ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                if (contentLength == -1) {
                    contentLength = 30 * 1024 * 1024;
                }
                // Transfer data size percent
                int transferSizePercent = (int) ((100 * bytesRead) / contentLength);
                // Transfer data size
                int transferDataSize = (int) (bytesRead / 1024 / 1024);
                // Data size
                int dataSize = (int) (contentLength / 1024 / 1024);
                downloadDialogFragment.updateProgress(transferSizePercent, transferDataSize, dataSize);

            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                        okhttp3.Response originalResponse = chain.proceed(chain.request());

                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();

                    }
                }).build();
        call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show(MainActivity.this, "网络出错，请重试");
                    }
                });
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String fileName = url.split("/")[url.split("/").length - 1];
                    File downloadedFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
                    BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
                    sink.writeAll(response.body().source());
                    sink.close();
                    downloadDialogFragment.dismiss();
                    install(downloadedFile);

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(MainActivity.this, "网络出错，请重试");
                        }
                    });
                }
            }


        });
    }

    public void install(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void cancelDownload() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }

    public void retryDownload(String url) {
        cancelDownload();
        download(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadDialogFragment != null) {
            downloadDialogFragment.dismiss();
        }
    }
    //----------------版本自动升级-----------结束-------------------------------


}
