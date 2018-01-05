package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.car.CarActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.edu.EducationActivity;
import com.praire.fire.car.ShopActivity;
import com.praire.fire.home.adapter.ShopListAdapter;
import com.praire.fire.home.bean.ShopListBean;
import com.praire.fire.home.bean.SwipeBean;
import com.praire.fire.utils.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lyp on 2017/12/27.
 */

public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener {


    @BindView(R.id.search_bar_address)
    TextView searchBarAddress;
    @BindView(R.id.plug_search_edittext)
    EditText plugSearchEdittext;
    @BindView(R.id.search_bar_relativeLayout)
    RelativeLayout searchBarRelativeLayout;
    @BindView(R.id.home_banner_slider)
    SliderLayout homeBannerSlider;
    @BindView(R.id.home_car)
    TextView homeCar;
    @BindView(R.id.home_edu)
    TextView homeEdu;
    @BindView(R.id.home_life)
    TextView homeLife;
    @BindView(R.id.home_trip)
    TextView homeTrip;
    @BindView(R.id.home_clothes)
    TextView homeClothes;
    @BindView(R.id.home_ecyclerView)
    RecyclerView homeEcyclerView;
    Unbinder unbinder;
    private ShopListAdapter adapter;
    private int index = 1;
    private List<ShopListBean.PagelistBean> evEntitys = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager            linearLayoutManager;
    private boolean loadMore = true;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initListener() {
        index = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestSwipe();
                requestShopList(index);
            }
        }).start();


        //设置指示器的位置
        homeBannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //设置图片的切换效果
        homeBannerSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        //添加textView动画特效
        homeBannerSlider.setCustomAnimation(new DescriptionAnimation());
        //设置切换时长3000 ,时长越小，切换速度越快
        homeBannerSlider.setDuration(3000);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        homeEcyclerView.setLayoutManager(linearLayoutManager);
        homeEcyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        homeEcyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        adapter = new ShopListAdapter(getActivity());
        homeEcyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShopActivity.startActivity(getActivity(),evEntitys.get(position).getId(),false);
            }
        });
      /*  homeEcyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadMore) {
                                getNextPage();
                            }
                        }
                    }, 1000);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });*/
    }



    @Override
    public void initData() {


    }
    private void getNextPage() {
        if (evEntitys.isEmpty() || evEntitys.size() % 10 != 0) {
            loadMore = false;
            return;
        }
        index++;
        requestShopList(index);
    }
    /**
     * 获取商家列表
     */
    private void requestShopList(int index) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.COMMONINFO_SHOPLIST + "?p=" + index)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络出错，请重试", Toast.LENGTH_SHORT).show();
                        loadMore = false;
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if(data == null){
                    loadMore = false;
                }
                Gson gson = new Gson();
                final ShopListBean evEntity = gson.fromJson(data, ShopListBean.class);
                evEntitys = evEntity.getPagelist();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setEntities(evEntitys);
                    }
                });

            }
        });

    }

    /**
     * 获取轮播图片
     */
    private void requestSwipe() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.COMMONINFO_GET_SWIPE)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络出错，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();

                Gson gson = new Gson();
                SwipeBean bean = gson.fromJson(data, SwipeBean.class);
                final List<SwipeBean.SwipelistBean> swipelist = bean.getSwipelist();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getAdSuccess(swipelist);
                    }
                });
            }
        });


    }

    /**
     * 添加图片控件
     */
    public void getAdSuccess(List<SwipeBean.SwipelistBean> swipelist) {
        if (swipelist == null || swipelist.size() <= 0) {
            return;
        }

        for (int i = 0, n = swipelist.size(); i < n; ++i) {
            SwipeBean.SwipelistBean bannerItem = swipelist.get(i);

            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .image(bannerItem.getOssclientcover())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle())
//                    .getBundle()
//                    .putString(Constants.HTTP_URL, bannerItem.getImgHttpUrl());
//            textSliderView.getBundle().putString(Constants.TITLE, bannerItem.getImgName());
            homeBannerSlider.addSlider(textSliderView);
        }
    }

    @OnClick({R.id.search_bar_address, R.id.plug_search_edittext, R.id.search_bar_relativeLayout,
            R.id.home_car, R.id.home_edu, R.id.home_life, R.id.home_trip, R.id.home_clothes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_bar_address:
                break;
            case R.id.plug_search_edittext:
                break;
            case R.id.search_bar_relativeLayout:
                break;
            case R.id.home_car:
                CarActivity.startActivity(getActivity(),false);
                break;
            case R.id.home_edu:
                EducationActivity.startActivity(getActivity(),false);
                break;
            case R.id.home_life:
                break;
            case R.id.home_trip:
                break;
            case R.id.home_clothes:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //性能优化。当页面显示时进行自动播放
    @Override
    public void onStart() {
        super.onStart();
        if (homeBannerSlider != null) {
            homeBannerSlider.startAutoCycle();
        }
    }

    //性能优化。当页面不显示时暂停自动播放
    @Override
    public void onStop() {
        super.onStop();
        if (homeBannerSlider != null) {
            homeBannerSlider.stopAutoCycle();
        }
    }

    /**
     * 动态图点击事件
     *
     * @param baseSliderView
     */
    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        homeBannerSlider.startAutoCycle(4000, 4000, true);

        int id = homeBannerSlider.getCurrentPosition();
//        switch (imgAdEntitys.get(id).imgPage) {}
    }
}
