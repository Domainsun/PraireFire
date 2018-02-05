package com.praire.fire.my.popwindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.praire.fire.R;


/**
 * Created by lyp on 2017/8/8.
 */

public class ChoosePhotoPopWindow extends PopupWindow implements View.OnClickListener {


    private Activity context;
    private View mainView;
    private TextView take, photo, cancer;
    private int coreWidth = 728;
    private int coreHight = 560;
    public ChoosePhotoPopWindow(final Activity context) {
        super(context);
        this.context = context;

        int layoutid = R.layout.pop_choose_photo;
        mainView = LayoutInflater.from(context).inflate(layoutid, null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new ColorDrawable(00000000));
        this.setContentView(mainView);
        this.setFocusable(true);

        final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        initView();
    }

    private void initView() {

        //
        take = (TextView)mainView.findViewById(R.id.button_take_photo);
        take.setOnClickListener(this);
        //
        photo = (TextView) mainView.findViewById(R.id.button_choice_photo);
        photo.setOnClickListener(this);
        //
        cancer = (TextView) mainView.findViewById(R.id.button_choice_cancer);
        cancer.setOnClickListener(this);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_take_photo:
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(1);
                }
                dismiss();
                break;
            case R.id.button_choice_photo:
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(2);
                }
                dismiss();
                break;
            case R.id.button_choice_cancer:
                 dismiss();
                break;
        }
    }
    private  OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener( OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //define interface
    public  interface OnItemClickListener {
        void onItemClick(int position);
    }
}
