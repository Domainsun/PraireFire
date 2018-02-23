package com.praire.fire.order;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.praire.fire.FindPasswordActivity;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.SharePreferenceMgr;

/**
 * Created by lyp
 * on 2018/2/11.
 */

public class InputPasswordPopwindows extends PopupWindow{
    private Activity context;
    PasswordView passwordView;
    View mainView;
    private OnItemClickListener mOnItemClickListener;

    public InputPasswordPopwindows(Activity context) {
        super(context);
        this.context = context;
        int layoutid = R.layout.input_pass_word;
        mainView = LayoutInflater.from(context).inflate(layoutid, null);
//        passwordView = new PasswordView(context);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ban_tou_ming));
        this.setContentView(mainView);
        this.setFocusable(true);

        final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        initView();
        initData();
    }

    private void initData() {

       //添加密码输入完成的响应
        passwordView.setOnFinishInput(new PasswordView.OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                //输入完成后我们简单显示一下输入的密码
                //也就是说——>实现你的交易逻辑什么的在这里写
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(passwordView.getStrPassword());
                    dismiss();
                }
            }
        });

        /**
         *  可以用自定义控件中暴露出来的cancelImageView方法，重新提供相应
         *  如果写了，会覆盖我们在自定义控件中提供的响应
         *  可以看到这里toast显示 "Biu Biu Biu"而不是"Cancel"*/
        passwordView.getCancelImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Biu Biu Biu", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        passwordView.getForgetTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPasswordActivity.startActivity(context,(String)SharePreferenceMgr.get(context, Constants.USER_ID,""),false);
//                Toast.makeText(context, "Biu Biu Biu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        passwordView = mainView.findViewById(R.id.pwd_view);
    }

    public interface OnItemClickListener {
        void onItemClick(String pwd);
    }

    public void setDataBack(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
