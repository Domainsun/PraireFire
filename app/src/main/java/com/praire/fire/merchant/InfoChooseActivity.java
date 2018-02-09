package com.praire.fire.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoChooseActivity extends BaseActivity {


    @BindView(R.id.rl_base_info)
    RelativeLayout rlBaseInfo;
    @BindView(R.id.rl_shop_info)
    RelativeLayout rlShopInfo;
    @BindView(R.id.tv_back)
    TextView tvBack;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, InfoChooseActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_info_choose;
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

    }


    @OnClick({R.id.tv_back,R.id.rl_base_info, R.id.rl_shop_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_back:

                BaseInfoActivity.startActivity(this, false);

                break;
            case R.id.rl_base_info:

                BaseInfoActivity.startActivity(this, false);

                break;

            case R.id.rl_shop_info:

                ShopInfoActivity.startActivity(this, false);

                break;
        }
    }


}
