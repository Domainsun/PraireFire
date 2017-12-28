package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by lyp on 2017/12/27.
 */

public class WelcomeActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity2.this, WelcomeActivity3.class);
                startActivity(intent);
                WelcomeActivity2.this.finish();
            }
        }, 3000);
    }
}
