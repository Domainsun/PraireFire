package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;



/**
 * 欢迎页面
 * @author lyp
 */
public class GuidActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GuidActivity.this, WelcomeActivity1.class);
                startActivity(intent);
                GuidActivity.this.finish();
            }
        }, 3000);
    }
}
