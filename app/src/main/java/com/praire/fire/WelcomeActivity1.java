package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.praire.fire.home.MainActivity;
import com.praire.fire.utils.SharePreferenceMgr;

/**
 * Created by lyp on 2017/12/27.
 */

public class WelcomeActivity1 extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome1);
       /*    Handler handler = new Handler();
      //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLeft();
            }
        }, 3000);*/
    }
    private float startX,offSetX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            startX = event.getX();
//            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            offSetX = event.getX();
//            y2 = event.getY();
           /* if(y1 - y2 > 50) {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else*/ if(Math.abs(startX - offSetX) > 50) {
//                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                swipeLeft();
            }/* else if(x2 - x1 > 50) {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }*/
        }
        return super.onTouchEvent(event);
    }

    private void swipeLeft() {
        Intent intent = new Intent(WelcomeActivity1.this, WelcomeActivity2.class);
        startActivity(intent);
        WelcomeActivity1.this.finish();
    }
}
