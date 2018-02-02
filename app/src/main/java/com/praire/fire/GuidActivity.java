package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.praire.fire.home.MainActivity;
import com.praire.fire.utils.SharePreferenceMgr;


/**
 * 欢迎页面
 * @author lyp
 */
public class GuidActivity extends Activity {
    private static final String IS_FRIST_OPEN = "IS_FRIST_OPEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

               Boolean isfrist = (Boolean) SharePreferenceMgr.get(GuidActivity.this, IS_FRIST_OPEN, true);
                if(isfrist) {
                    SharePreferenceMgr.put(GuidActivity.this, IS_FRIST_OPEN, false);
                    Intent intent = new Intent(GuidActivity.this, WelcomeActivity1.class);
                    startActivity(intent);
                    GuidActivity.this.finish();
                }else {
                    Intent intent = new Intent(GuidActivity.this, MainActivity.class);
                    startActivity(intent);
                    GuidActivity.this.finish();
                }
            }
        }, 3000);
    }
}
