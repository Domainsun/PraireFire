package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.praire.fire.home.fragment.MainActivity;

/**
 * Created by lyp on 2017/12/27.
 */

public class WelcomeActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity3.this, MainActivity.class);
                startActivity(intent);
                WelcomeActivity3.this.finish();
            }
        }, 3000);
    }
}
