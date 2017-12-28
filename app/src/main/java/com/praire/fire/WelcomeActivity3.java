package com.praire.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.praire.fire.home.fragment.MainActivity;

/**
 * Created by lyp on 2017/12/27.
 */

public class WelcomeActivity3 extends Activity {
    Button checkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);
         checkin =  findViewById(R.id.check_in);
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity3.this, MainActivity.class);
                startActivity(intent);
                WelcomeActivity3.this.finish();
            }
        });
    }
}
