package com.praire.fire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class SettledActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settled);
    }
}
