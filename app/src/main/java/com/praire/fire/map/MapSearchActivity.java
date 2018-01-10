package com.praire.fire.map;

import android.content.Context;
import android.content.Intent;

import com.amap.api.maps2d.model.LatLng;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

/**
 * Created by lyp on 2018/1/9.
 */

public class MapSearchActivity extends BaseTitleActivity {

    public static void startActivity(Context context, String key, boolean forResult) {
        Intent intent = new Intent(context, MapSearchActivity.class);
        intent.putExtra(Constants.SEARCH_KEY, key);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_map_search;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initiTile() {

    }
}
