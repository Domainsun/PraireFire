package com.praire.fire.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amap.api.maps.MapView;
import com.praire.fire.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lyp on 2017/12/27.
 */

public class MapFragment extends Fragment {


    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.map_recyclerView)
    SwipeMenuRecyclerView mapRecyclerView;
    @BindView(R.id.map_input_key)
    EditText mapInputKey;
    Unbinder unbinder;






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        return rootView;
    }

    @OnClick({R.id.check_more_tv, R.id.map_back, R.id.map_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_more_tv:
                mapRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.map_back:
                break;
            case R.id.map_clean:
                mapInputKey.setText("");
                break;
            default:
                break;
        }
    }
}
