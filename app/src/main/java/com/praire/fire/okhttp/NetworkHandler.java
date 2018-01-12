package com.praire.fire.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class NetworkHandler extends Handler {
	protected Context mContext;

	public NetworkHandler() {
		this(null);
	}

	public NetworkHandler(Context context) {
		mContext = context;
	}

	@Override
	public final void handleMessage(Message msg) {
	}
}
