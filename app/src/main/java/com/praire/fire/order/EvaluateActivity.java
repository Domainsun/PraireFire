package com.praire.fire.order;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForEvaluateActivity;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.adapter.EvaluateOrderAdapter;
import com.praire.fire.order.bean.EvaluateCommitInfo;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.INTENT_DATA;

/**
 * 评价订单
 * Created by lyp on 2018/1/23.
 */

public class EvaluateActivity extends BaseTitleActivity {


    IntentDataForEvaluateActivity data;
    String base64_photo1 = "";
    String base64_photo2 = "";
    String base64_photo3 = "";
    String base64_photo4 = "";

    @BindView(R.id.btn_commit_evaluate)
    Button btnCommitEvaluate;
    @BindView(R.id.recyclerview_ev)
    RecyclerView recyclerviewEv;
    List<EvaluateCommitInfo.OrderpsListBean> sList = new ArrayList<>();

    private CommonMethod commonMethod;
    private List<String> img = new ArrayList<>();//商品图片
    private List<String> ids = new ArrayList<>();//商品id
    private EvaluateOrderAdapter adapter;
    private String productId;

    public static void startActivity(Context context, IntentDataForEvaluateActivity orderInfo,boolean forResult) {
        Intent intent = new Intent(context, EvaluateActivity.class);
        intent.putExtra(INTENT_DATA, orderInfo);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);


        recyclerviewEv.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewEv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerviewEv.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        adapter = new EvaluateOrderAdapter(this);

        recyclerviewEv.setAdapter(adapter);
        adapter.setEntities(data.orderInfo.getPslist());
    }

    @Override
    protected void initListeners() {
        adapter.setItemClickLister(new EvaluateOrderAdapter.ItemClickLister() {
            @Override
            public void itemClick(int position, EvaluateCommitInfo.OrderpsListBean item) {
                if(sList.size()>0){
                    for(int i=0;i<sList.size();i++){
                        if(sList.get(i).getOrderps_id().equals(item.getOrderps_id())){
                            sList.remove(i);
                            sList.add(item);
                            return;
                        }
                    }
                    sList.add(item);
                    return;
                }
                sList.add(item);
            }
        });

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initiTile() {
        data = getIntent().getParcelableExtra(INTENT_DATA);
        setDefaultBack();
        setTitleMiddle(data.orderInfo.getShopname());
    }


    @OnClick({ R.id.btn_commit_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_commit_evaluate:
                commitEvaluateInfo();
                break;
            default:
                break;
        }
    }

    private void commitEvaluateInfo() {

        EvaluateCommitInfo commitInfo = new EvaluateCommitInfo();
        commitInfo.setOrder_id(data.orderInfo.getId());
        commitInfo.setOrderps_list(sList);
        // 转换得到String
        Gson gson = new Gson();
        String strins = gson.toJson(commitInfo);
        RequestBody requestBody = new FormBody.Builder()
                .add("orderid", data.orderInfo.getOrderno())
                .add("commentstr", strins)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.COMMENT_IN, requestBody, 1, uiHandler, true);
    }

    public void showChoosePic(int requestCode, String id) {
        productId = id;
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(EvaluateActivity.this)
                    // 选择 mime 的类型
                    .choose(MimeType.allOf())
                    .countable(true)
                    // 图片选择的最多数量
                    .maxSelectable(1)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    // 缩略图的比例0.85
                    .thumbnailScale(0.85f)
//                    .capture(true) //是否提供拍照功能.captureStrategy(new CaptureStrategy(true,String authority))//存储到哪里

                    // 使用的图片加载引擎
                    .imageEngine(new PicassoEngine())
                    // 设置作为标记的请求码
                    .forResult(requestCode);
        } else {
            EasyPermissions.requestPermissions(this, "打开相册需要获取权限",
                    0, perms);
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        commonMethod = new CommonMethod();
        *//*选择照片回调*//*
        if (resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            switch (requestCode) {
                case REQUEST_CODE_UPLOAD_PRODUCT1:
                    if (mSelected.size() > 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo1 = base64;
                        imgEvaluate1.setImageURI(mSelected.get(0));
                        imgEvaluate2.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT2:

                    if (mSelected.size() > 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo2 = base64;
                        imgEvaluate2.setImageURI(mSelected.get(0));
                        imgEvaluate3.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT3:
                    if (mSelected.size() > 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo3 = base64;
                        imgEvaluate3.setImageURI(mSelected.get(0));
                        imageSay.setVisibility(View.GONE);
                        imgEvaluate4.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT4:
                    if (mSelected.size() > 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo4 = base64;
                        imgEvaluate4.setImageURI(mSelected.get(0));
                    }
                    break;
                default:
                    break;
            }
        }


    }
*/
    @Override
    protected void networkResponse(Message msg) {
        if (msg.what == 1) {
            Log.e("Message", (String) msg.obj);
            Gson gson = new Gson();
            CommentResultBean bean = gson.fromJson((String) msg.obj, CommentResultBean.class);

            if (bean.getCode() == 1) {
                ResultActivity.startActivity(this,"评价成功","感谢您的评价！",false);
                finish();
                return;
            }
            ToastUtil.show(this, bean.getMsg());
        }
    }


}
