package com.praire.fire.my.setActivitys;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.BankCityBean;
import com.praire.fire.okhttp.JavaBean.BindBankCardInfoBean;
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
import static com.praire.fire.common.Constants.REQUEST_BANK;

public class BindCardActivity extends BaseActivity {


    @BindView(R.id.tv_id_num)
    TextView tvIdNum;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_bank_card_num)
    EditText etBankCardNum;
    @BindView(R.id.rl_bank_card_num)
    RelativeLayout rlBankCardNum;
    @BindView(R.id.tv_choose_creat_account)
    TextView tvChooseCreatAccount;

    @BindView(R.id.tv_choose_address)
    TextView tvChooseAddress;
    @BindView(R.id.rl_adder)
    RelativeLayout rlAdder;
    @BindView(R.id.tv_pay_password)
    TextView tvPayPassword;
    @BindView(R.id.et_sub_account_name)
    EditText etSubAccountName;
    @BindView(R.id.rl_creat_sub_account)
    RelativeLayout rlCreatSubAccount;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.rl_submit)
    LinearLayout rlSubmit;
    private WheelView mainWheelView, subWheelView;

    List<String> mainList = new ArrayList<>();
    List<String> subList = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    Map<String, String> subMap = new HashMap<>();


    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie = "";


    String bankAccountName = "";
    String bankAccountNum = "";
    String creatAccountName = "";
    String creatAccountAddress = "";
    String creatAccountSubName = "";
    String creatAccountId = "";
    String creatAccountAddressId = "";
    @BindView(R.id.rl_creat_account)
    RelativeLayout rlCreatAccount;
    @BindView(R.id.tv_back)
    TextView tvBack;


    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, BindCardActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_bind_card;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        try {

            String str = u.getBindBankCardInfo(cookie);
            BindBankCardInfoBean o = j.getBindBankCardInfo(str);


            if (1 == (o.getCode())) {

                etName.setText(o.getCardinfo().getTruename());
                etBankCardNum.setText(o.getCardinfo().getCardno());
                tvChooseCreatAccount.setText(o.getCardinfo().getBankname());
                tvChooseAddress.setText(o.getCardinfo().getScity());
                etSubAccountName.setText(o.getCardinfo().getBranchname());

                etName.setEnabled(false);
                etBankCardNum.setEnabled(false);
                rlCreatAccount.setEnabled(false);
                rlAdder.setEnabled(false);
                etSubAccountName.setEnabled(false);
                rlSubmit.setVisibility(View.INVISIBLE);

            }

            Log.d("initData", "initData: " + str);


        } catch (Exception e) {

            Log.e("initData", "initData: " + e.toString());

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


        try {


            String str = new UseAPIs().getBankCity();
            Log.d("str", "str: " + str);
            if (str.length() != 0) {
                BankCityBean s = new J2O().getBankCity(str);


                Log.d("size", "size: " + s.getCity().size());

                for (int i = 0; i < s.getCity().size(); i++) {
                    String mainName = s.getCity().get(i).getName();

                    Log.d("cityname", "\ncityname: " + mainName);

                    mainList.add(mainName);
                    List<String> cSublist = new ArrayList<>();

                    int size = 0;

                    try {
                        size = s.getCity().get(i).get_child().size();
                    } catch (Exception e) {
                        Log.e("initData", "initData: " + e.toString());
                    }


                    if (size != 0) {
                        for (int j = 0; j < s.getCity().get(i).get_child().size(); j++) {
                            String subName = s.getCity().get(i).get_child().get(j).getName();
                            String subCode = s.getCity().get(i).get_child().get(j).getId();
                            cSublist.add(subName);
                            subList.add(subName);
                            subMap.put(subName, subCode);

                        }
                        map.put(mainName, cSublist);
                    }
                }
                String defaultType = s.getCity().get(0).get_child().get(0).getName();
                String defaultTypeCode = s.getCity().get(0).get_child().get(0).getId();

            } else {
                Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

            Log.e("e", "initDatae: " + e.toString());

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_BANK && resultCode == RESULT_OK) {
            creatAccountId = data.getStringExtra("bankId");
            creatAccountName = data.getStringExtra("bankName");
            tvChooseCreatAccount.setText(creatAccountName);
        }
    }


    @OnClick({R.id.tv_back, R.id.rl_creat_account, R.id.rl_adder, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_creat_account:
                ChooseBankActivity.startActivity(this, true);
                break;
            case R.id.rl_adder:

                show1();

                System.out.println(map.get(mainList.get(0)));
                break;
            case R.id.submit:

                bankAccountName = etName.getText().toString();
                bankAccountNum = etBankCardNum.getText().toString();
                creatAccountSubName = etSubAccountName.getText().toString();

                if (bankAccountName.length() != 0 && bankAccountNum.length() != 0 && creatAccountId.length() != 0 && creatAccountAddressId.length() != 0 && creatAccountSubName.length() != 0) {
                    try {
                        String str = u.bindBankCard(bankAccountNum, creatAccountId, creatAccountAddressId, creatAccountSubName, cookie);

                        Log.d("str", "str: " + str);

                        APIResultBean o = j.getAPIResult(str);
                        Toast.makeText(this, o.getMsg() + "", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Log.e("onViewClicked", "onViewClicked: " + e.toString());

                    }


                }

                break;
        }
    }


    private void show1() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_product_type_dialog, null);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 12;
        style.textSize = 8;
        mainWheelView = (WheelView) contentView.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(mainList);
        mainWheelView.setStyle(style);
        subWheelView = (WheelView) contentView.findViewById(R.id.sub_wheelview);
        subWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        subWheelView.setSkin(WheelView.Skin.Holo);
        subWheelView.setWheelData(map.get(mainList.get(mainWheelView.getSelection())));
        subWheelView.setStyle(style);
        mainWheelView.join(subWheelView);
        mainWheelView.joinDatas(map);
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
                tvChooseAddress.setText(subWheelView.getSelectionItem() + "");
                creatAccountAddressId = subMap.get(subWheelView.getSelectionItem());
                Log.d("creatAccountAddressId", "onClick: " + creatAccountAddressId);
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
