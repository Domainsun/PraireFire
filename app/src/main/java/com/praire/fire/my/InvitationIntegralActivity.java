package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.ImageUtils;
import com.praire.fire.utils.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xyzlf.share.library.bean.ShareEntity;
import com.xyzlf.share.library.interfaces.ShareConstant;
import com.xyzlf.share.library.util.ShareUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请得积分
 * Created by lyp on 2018/1/2.
 */

public class InvitationIntegralActivity extends BaseActivity {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.share_info)
    TextView shareInfo;
    @BindView(R.id.totle_number)
    TextView totleNumber;
    @BindView(R.id.btn_share)
    TextView btnShare;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int index = 1;
    private boolean isFrist = true;
    private boolean loadMore = true;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, InvitationIntegralActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_invitation_integral;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
         refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isFrist = true;
                index = 1;
                initData();

                //加载失败的话2秒后结束加载
//                refreshlayout.finishRefresh(2000  *//*,false*//*  );//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (loadMore) {
                    getNextPage();
                    //加载失败的话2秒后结束加载
//                    refreshlayout.finishLoadmore(2000 *//*,false*//*);//传入false表示加载失败
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }

            }


        });
    }

    private void getNextPage() {
    }

    @Override
    protected void initAdapters() {
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void initData() {
//        OkhttpRequestUtil.get(ConstanUrl.CREDIT_detail + "?p=" + index, 1, true, uiHandler);
    }


    @OnClick({R.id.back, R.id.share_info, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share_info:
                ShareExplainActivity.startActivity(this,false);
                break;
            case R.id.btn_share:
//                showShareDialog();
//                startShare();
                mTargetScene = SendMessageToWX.Req.WXSceneSession;
//                mTargetScene = SendMessageToWX.Req.WXSceneTimeline;
//                mTargetScene = SendMessageToWX.Req.WXSceneFavorite;
                showShareWeixin();
                break;
        }
    }
    /**
     * 分享到微信
     */
    public void showShareWeixin() {
        IWXAPI api  ;
        api = WXAPIFactory.createWXAPI(this, Constants.PRODUCT_WEIXIN_APP_ID);
// 将该app注册到微信
        api.registerApp(Constants.PRODUCT_WEIXIN_APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.qq.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "燎原生活APP，您身边的服务管家！";
        msg.description = "生活所需，服务所致，足不出户下单同城服务，省钱省力，分享还可赚钱";
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        api.sendReq(req);

    }

    /**
     * 发送到聊天界面——WXSceneSession
     发送到朋友圈——WXSceneTimeline
     添加到微信收藏——WXSceneFavorite
     */
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;//WXSceneTimeline//WXSceneFavorite
    private static final int THUMB_SIZE = 150;
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    /**
     * 调用分享界面
     */
    public void showShareDialog() {
       ShareEntity testBean = new ShareEntity("“TA”！您身边的服务管家！", "生活所需，服务所致");
        testBean.setUrl("https://www.baidu.com"); //分享链接
//        testBean.setDrawableId(R.mipmap.ic_launcher);
        testBean.setImgUrl("https://www.baidu.com/img/bd_logo1.png");
        ShareUtil.showShareDialog(this, testBean, ShareConstant.REQUEST_CODE);
    }

    /**
     * 使用分享功能，如下实例 使用 更多(系统自带) 分享功能：
     */
    public void startShare() {
/*        ShareEntity testBean = new ShareEntity("“TA”！您身边的服务管家！", "生活所需，服务所致");
        testBean.setUrl("https://www.baidu.com"); //分享链接
//        testBean.setImgUrl("https://www.baidu.com/img/bd_logo1.png");
        testBean.setDrawableId(R.mipmap.ic_launcher);

        ShareUtil.startShare(this, ShareConstant.SHARE_CHANNEL_SYSTEM, testBean, ShareConstant.REQUEST_CODE);*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    /*    *//**
         * 分享回调处理
         *//*
        if (requestCode == ShareConstant.REQUEST_CODE) {
            if (data != null) {
                int channel = data.getIntExtra(ShareConstant.EXTRA_SHARE_CHANNEL, -1);
                int status = data.getIntExtra(ShareConstant.EXTRA_SHARE_STATUS, -1);
                onShareCallback(channel, status);
            }
        }*/
    }

    /**
     * 分享回调处理
     *
     * @param channel
     * @param status
     */
    private void onShareCallback(int channel, int status) {
//        new ShareCallBack().onShareCallback(channel, status);
    }

  /*  private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        Toast.makeText(this, "Uri:" + uriPath, Toast.LENGTH_SHORT).show();
        return uriPath;
    }*/


}
