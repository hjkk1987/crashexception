package com.exception.crash.app;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Services extends Service {
	private CrashBinder mBinder = new CrashBinder();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public class CrashBinder extends Binder {
		public Services getService() {
			return Services.this;
		}
	}

}
