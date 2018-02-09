package com.praire.fire.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseInfoActivity extends BaseActivity {
    @BindView(R.id.tv_Contact_person1)
    TextView tvContactPerson1;
    @BindView(R.id.tv_Contact_phone1)
    TextView tvContactPhone1;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.tv_back)
    TextView tvBack;

    J2O j=new J2O();


    String starttime="";
    String endtime="";



    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, BaseInfoActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_base_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        if (hasLogin()) {
            OkhttpRequestUtil.get(ConstanUrl.GET_SHOP_INFO, 1, true, uiHandler);
        }
    }


    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);


        switch (msg.what) {
            case 1:

                try {
                    String str=msg.obj+"";
                    ShopInfoBean o=j.getShopInfo(str);

                    tvContactPerson1.setText(o.getContact());
                    tvContactPhone1.setText(o.getTel());
                    tvContactPerson1.setText(o.getContact());

                    String times[]=o.getOpentime().split("-");

                    tvStartTime.setText(times[0]+"    -");
                    tvEndTime.setText(times[1]);
                } catch (Exception e) {

                    Log.e("networkResponseeeee", "networkResponseeeee: "+e.toString() );
                }


                break;
            default:
                break;
        }


    }


    @OnClick({R.id.tv_back, R.id.tv_Contact_person1, R.id.tv_Contact_phone1, R.id.tv_start_time, R.id.tv_end_time, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;

            case R.id.tv_Contact_person1:

                break;
            case R.id.tv_Contact_phone1:

                break;
            case R.id.tv_start_time:

                choseTime("0");

                break;
            case R.id.tv_end_time:

                choseTime("1");

                break;
            case R.id.submit:



                break;

        }
    }


    public void choseTime(final String type) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String str = sdf.format(date);

                if (type.equals("0")) {
                    starttime= str;


                    tvStartTime.setText(str+"  -");

                } else if(type.equals("1")){
                    tvEndTime.setText(str);
                    endtime=str;
                }


            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})
                .build();
        pvTime.show();

    }

}
