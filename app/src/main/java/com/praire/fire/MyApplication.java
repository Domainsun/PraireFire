package com.praire.fire;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.praire.fire.base.BaseApplication;

/**
 * Created by lyp on 2017/12/29.
 */

public class MyApplication extends BaseApplication {
    @Override
    protected void create() {
        Fresco.initialize(this);
    }
}
