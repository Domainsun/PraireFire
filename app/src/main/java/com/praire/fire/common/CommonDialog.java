package com.praire.fire.common;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;


public class CommonDialog implements OnClickListener {
    Context context;
    BaseActivity activity;
    AlertDialog alertDialog;
    boolean hasTitle = true;
    String title, message, confirm, cancel, close;
    int confirmColor = 0, cancelColor = 0, closeColor = 0;
    OnClickListener confirmOnClickListener, cancelOnClickListener, closeOnClickListener, checkBoxOnClickListener, systemMsgOnClistener, selfMsgOnClistener;

    private CommonDialog(BaseActivity activity) {
        this.activity = activity;
    }

    public static CommonDialog Build(BaseActivity activity) {
        return new CommonDialog(activity);
    }

     private CommonDialog(Context context) {
        this.context = context;
    }

    public static CommonDialog Build(Context context) {
        return new CommonDialog(context);
    }

    public static CommonDialog BuildNew(Context context) {
        return new CommonDialog((BaseActivity) context);
    }

    /**
     * 设置标题 默认“提示”
     *
     * @param title
     * @return
     */
    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 启用标题栏 默认true
     *
     * @param hasTitle
     * @return
     */
    public CommonDialog setTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        return this;
    }

    public CommonDialog setMessage(String msg) {
        message = msg;
        return this;
    }

    public CommonDialog setConfirm(int color, String name, OnClickListener listener) {
        confirmColor = color;
        confirm = name;
        confirmOnClickListener = listener;
        return this;
    }

    public CommonDialog setCancel(int color, String name, OnClickListener listener) {
        cancelColor = color;
        cancel = name;
        cancelOnClickListener = listener;
        return this;
    }

    public CommonDialog setClose(int color, String name, OnClickListener listener) {
        closeColor = color;
        close = name;
        closeOnClickListener = listener;
        return this;
    }

    public CommonDialog setConfirm(String name, OnClickListener listener) {
        confirm = name;
        confirmOnClickListener = listener;
        return this;
    }

    public CommonDialog setCancel(String name, OnClickListener listener) {
        cancel = name;
        cancelOnClickListener = listener;
        return this;
    }

    public CommonDialog setClose(String name, OnClickListener listener) {
        close = name;
        closeOnClickListener = listener;
        return this;
    }

    public CommonDialog setConfirm(OnClickListener listener) {
        confirmOnClickListener = listener;
        return this;
    }

    public CommonDialog setCancel(OnClickListener listener) {
        cancelOnClickListener = listener;
        return this;
    }

    public CommonDialog setClose(OnClickListener listener) {
        closeOnClickListener = listener;
        return this;
    }

    public CommonDialog setCheckBox(OnClickListener listener) {
        checkBoxOnClickListener = listener;
        return this;
    }

    public CommonDialog setSystemMsg(OnClickListener listener) {
        systemMsgOnClistener = listener;
        return this;
    }

    public CommonDialog setSelfMsg(OnClickListener listener) {
        selfMsgOnClistener = listener;
        return this;
    }

    public void showclose() {
        alertDialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.view_common_dialog, null);
        Button closebutton = (Button) view.findViewById(R.id.close);
        closebutton.setOnClickListener(this);
        if (closeColor != 0) {
            closebutton.setTextColor(closeColor);
        }
        if (!TextUtils.isEmpty(close)) {
            closebutton.setText(close);
        }
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView messageView = (TextView) view.findViewById(R.id.message);
        if (hasTitle) {
            if (!TextUtils.isEmpty(title)) {
                titleView.setText(title);
            }
            // messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.common_dp_70));
        } else {
            titleView.setVisibility(View.GONE);
            // messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.common_dp_100));
        }
        if (!TextUtils.isEmpty(message)) {
            messageView.setText(message);
        }
        view.findViewById(R.id.button_layout).setVisibility(View.GONE);
        alertDialog.show();
        alertDialog.setContentView(view);
    }

    public void showconfirm() {
        alertDialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.view_common_dialog, null);
        Button confirmbutton = (Button) view.findViewById(R.id.confirm);
        confirmbutton.setOnClickListener(this);
        if (confirmColor != 0) {
            confirmbutton.setTextColor(confirmColor);
        }
        if (!TextUtils.isEmpty(confirm)) {
            confirmbutton.setText(confirm);
        }
        Button cancelbutton = (Button) view.findViewById(R.id.cancel);
        cancelbutton.setOnClickListener(this);
        if (cancelColor != 0) {
            cancelbutton.setTextColor(cancelColor);
        }
        if (!TextUtils.isEmpty(cancel)) {
            cancelbutton.setText(cancel);
        }
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView messageView = (TextView) view.findViewById(R.id.message);
        if (hasTitle) {
            if (!TextUtils.isEmpty(title)) {
                titleView.setText(title);
            }
            // messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.common_dp_70));
        } else {
            titleView.setVisibility(View.GONE);
            // messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.common_dp_100));
        }
        if (!TextUtils.isEmpty(message)) {
            messageView.setText(message);
        }
        view.findViewById(R.id.close).setVisibility(View.GONE);
        alertDialog.show();
        alertDialog.setContentView(view);
    }

    public void showCheckBox() {
        alertDialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.view_common_dialog, null);
        Button confirmbutton = (Button) view.findViewById(R.id.confirm);
        confirmbutton.setOnClickListener(this);
        if (confirmColor != 0) {
            confirmbutton.setTextColor(confirmColor);
        }
        if (!TextUtils.isEmpty(confirm)) {
            confirmbutton.setText(confirm);
        }
        Button cancelbutton = (Button) view.findViewById(R.id.cancel);
        cancelbutton.setOnClickListener(this);
        if (cancelColor != 0) {
            cancelbutton.setTextColor(cancelColor);
        }
        if (!TextUtils.isEmpty(cancel)) {
            cancelbutton.setText(cancel);
        }
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView messageView = (TextView) view.findViewById(R.id.message);
        if (hasTitle) {
            if (!TextUtils.isEmpty(title)) {
                titleView.setText(title);
            }
            messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.x70));
        } else {
            titleView.setVisibility(View.GONE);
            messageView.setMinHeight(activity.getResources().getDimensionPixelSize(R.dimen.x100));
        }
        if (!TextUtils.isEmpty(message)) {
            messageView.setText(message);
        }
        view.findViewById(R.id.close).setVisibility(View.GONE);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.dialog_checkbox);
        checkBox.setVisibility(View.VISIBLE);
        checkBox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkBoxOnClickListener != null) {
                    checkBoxOnClickListener.onClick(v);
                }
            }
        });
        alertDialog.show();
        alertDialog.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        if (alertDialog != null) {
            switch (v.getId()) {
                case R.id.close: {
                    if (closeOnClickListener != null) {
                        closeOnClickListener.onClick(v);
                    }
                }
                break;
                case R.id.confirm: {
                    if (confirmOnClickListener != null) {
                        confirmOnClickListener.onClick(v);
                    }
                }
                break;
                case R.id.cancel: {
                    if (cancelOnClickListener != null) {
                        cancelOnClickListener.onClick(v);
                    }
                }
                break;
                default:
                    break;
            }
            alertDialog.dismiss();
        }
    }


}
