package com.praire.fire.merchant;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.JavaBean.ServiceTypeBean;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.JavaBean.UserHeadBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class AddServiceActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.et_service_name)
    EditText etServiceName;
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.tv_show_service_type)
    TextView tvShowServiceType;
    @BindView(R.id.tv_choose_service_type)
    TextView tvChooseServiceType;
    @BindView(R.id.rl_service_type)
    RelativeLayout rlServiceType;
    @BindView(R.id.tv_service_price)
    TextView tvServicePrice;
    @BindView(R.id.et_service_price)
    EditText etServicePrice;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_product_introduction)
    EditText etProductIntroduction;
    @BindView(R.id.submit)
    Button submit;
    private WheelView mainWheelView;
    List<String> servicelists = new ArrayList<>();

    Map<String, String> map = new HashMap<>();

    String name = "";
    String type = "";
    String id = "";
    String price = "";
    String introdction = "";
    String cookie;
    ServiceListBean.PagelistBean s = new ServiceListBean.PagelistBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        ButterKnife.bind(this);


        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        initdata();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            s = (ServiceListBean.PagelistBean) b.getSerializable("data");
            String tab = b.getString("tab");

//            if (tab.equals("1")) {

            etServiceName.setText(s.getName());
            tvShowServiceType.setText(s.getClass_name());
            etServicePrice.setText(s.getNprice());
            etProductIntroduction.setText(s.getDesc());
            submit.setText("修改");
            id = s.getId();
            type = s.getClassX();
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getDate();
                    if (name.length() == 0 || price.length() == 0 || introdction.length() == 0 || type.length() == 0) {
                        Toast.makeText(AddServiceActivity.this, "请把信息填写完整！", Toast.LENGTH_SHORT).show();
                    } else {
                        String str = "";
                        str = new UseAPIs().changeServiceInfo(id, name, type, introdction, price, cookie);

                        if (str.length() != 0) {
                            APIResultBean a = new J2O().getAPIResult(str);
                            if (a.getCode() == 1) {
                                AddServiceActivity.this.finish();
                            }
                            Toast.makeText(AddServiceActivity.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddServiceActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
//            }
        }


    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_add_service;
    }

    @Override
    protected void initViews() {

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


    @OnClick({R.id.tv_back, R.id.rl_service_type, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_service_type:
                show1();
                break;
            case R.id.submit:

                getDate();
                if (name.length() == 0 || price.length() == 0 || introdction.length() == 0 || type.length() == 0) {
                    Toast.makeText(this, "请把信息填写完整！", Toast.LENGTH_SHORT).show();
                } else {
                    String str = "";
                    str = new UseAPIs().addService(name, type, introdction, price, cookie);
                    if (str.length() != 0) {

                        Log.d("onViewClicked", "onViewClicked: " + str);
                        APIResultBean a = new J2O().getAPIResult(str);
                        if (a.getCode() == 1) {
                            this.finish();
                        }
                        Toast.makeText(this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }
    }

    private void getDate() {
        name = etServiceName.getText().toString();
        price = etServicePrice.getText().toString();
        introdction = etProductIntroduction.getText().toString();
    }

    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);
        switch (msg.what) {
            case 1:
                String str = msg.obj + "";
                if (!str.isEmpty()) {
                    ServiceTypeBean s = new J2O().getServiceType(str);
                    for (int i = 0; i < s.getList().size(); i++) {
                        String name = s.getList().get(i).getName();
                        String code = s.getList().get(i).getId();
                        servicelists.add(name);
                        map.put(name, code);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void initdata() {


        OkhttpRequestUtil.get(ConstanUrl.GET_SERVICE_TYPE, 1, true, uiHandler);


//        String str = "";
//        str=new UseAPIs().getServiceType();
//
//        if (str.length() != 0) {
//            ServiceTypeBean s = new J2O().getServiceType(str);
//            for (int i = 0; i < s.getList().size(); i++) {
//                String name = s.getList().get(i).getName();
//                String code = s.getList().get(i).getId();
//                servicelists.add(name);
//                map.put(name, code);
//            }
//        } else {
//            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
//        }

    }

    private void show1() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_service_type_dialog, null);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;

        mainWheelView = (WheelView) contentView.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(servicelists);
        mainWheelView.setStyle(style);


        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

        TextView tv_confirm = contentView.findViewById(R.id.tv_ok);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShowServiceType.setText(mainWheelView.getSelectionItem() + "");
                type = map.get(mainWheelView.getSelectionItem());
                bottomDialog.dismiss();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });

    }


}
