package com.praire.fire.merchant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praire.fire.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettledActivity1 extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.rl4)
    RelativeLayout rl4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.rl5)
    RelativeLayout rl5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.rl6)
    RelativeLayout rl6;
    String type = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settled1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl1, R.id.tv_back, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.rl6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                show_selected(iv1);
                type = "1";
                back();
                break;
            case R.id.rl2:
                show_selected(iv2);
                type = "2";
                back();
                break;
            case R.id.rl3:
                show_selected(iv3);
                type = "3";
                back();
                break;
            case R.id.rl4:
                show_selected(iv4);
                type = "4";
                back();
                break;
            case R.id.rl5:
                show_selected(iv5);
                type = "5";
                back();
                break;
            case R.id.rl6:
                show_selected(iv6);
                type = "6";
                back();
                break;

            case R.id.tv_back:
                this.finish();
                break;
        }
    }

    public void back(){
        Log.d("type", type);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        bundle.putString("type", type);//添加要返回给页面1的数据
        intent.putExtras(bundle);
        this.setResult(Activity.RESULT_OK, intent);//返回页面1
        this.finish();
    }

    public void show_selected(ImageView imageView) {
        ImageView ivs[] = {iv1, iv2, iv3, iv4, iv5, iv6};
        for (int i = 0; i < ivs.length; i++) {
            if (imageView.getId() == ivs[i].getId()) {
                ivs[i].setImageResource(R.mipmap.store_type_selected);
            } else {
                ivs[i].setImageResource(R.mipmap.store_type_normal);
            }
        }
    }



}