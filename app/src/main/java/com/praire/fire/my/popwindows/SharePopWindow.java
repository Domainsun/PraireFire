package com.praire.fire.my.popwindows;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.praire.fire.R;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.TextViewUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Hashtable;


/**
 * Created by lyp
 */

public class SharePopWindow extends PopupWindow implements View.OnClickListener {


    private Activity context;
    private View mainView;
    private TextView take, photo,info1,info2;
    private ImageView cancer,QRCode;
    public SharePopWindow(final Activity context) {
        super(context);
        this.context = context;

        int layoutid = R.layout.pop_share;
        mainView = LayoutInflater.from(context).inflate(layoutid, null);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        this.setBackgroundDrawable(new ColorDrawable(00000000));
        this.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ban_tou_ming));
        this.setContentView(mainView);
        this.setFocusable(true);

       /* final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }*/
        initView();
    }

    private void initView() {
        info1  =  mainView.findViewById(R.id.share_info_how1);
        info2  =  mainView.findViewById(R.id.share_info_how2);
        TextViewUtils.changeFontColor(context,info1,2,3,R.color.grey,R.color.orange);
        TextViewUtils.changeFontColor(context,info2,1,4,R.color.grey,R.color.orange);
        QRCode =  mainView.findViewById(R.id.share_qr);
        take =  mainView.findViewById(R.id.share_weixin);
        take.setOnClickListener(this);
        //
        photo =   mainView.findViewById(R.id.share_circle_of_friends);
        photo.setOnClickListener(this);
        //
        cancer =   mainView.findViewById(R.id.close_share);
        cancer.setOnClickListener(this);
        String code = ConstanUrl.SHARE_URL + SharePreferenceMgr.get(context, Constants.USER_ID,"");
        //生成带图片二维码
        Bitmap mBitmap = CodeUtils.createImage(code, 400, 400, BitmapFactory.decodeResource(context.getResources(), R.mipmap.share_logo));
        QRCode.setImageBitmap(mBitmap);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.share_weixin:
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(1);
                }
                dismiss();
                break;
            case R.id.share_circle_of_friends:
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(2);
                }
                dismiss();
                break;
            case R.id.close_share:
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
