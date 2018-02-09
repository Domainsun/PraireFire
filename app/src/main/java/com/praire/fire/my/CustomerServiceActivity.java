package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.edu.EducationActivity;

/**客服中心
 * Created by lyp on 2018/1/2.
 */

public class CustomerServiceActivity extends BaseTitleActivity {
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, CustomerServiceActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_call_center;//这个要换掉 不能修改这个activity_call_center文件这是客服中心详情用的
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

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("客服中心");
    }
}
