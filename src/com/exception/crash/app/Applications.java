package com.exception.crash.app;

import com.exception.crash.app.Services.CrashBinder;
import com.exception.crash.fun.CrashCaught;
import com.exception.crash.fun.CrashCaught.OnExceptionCaught;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

@SuppressLint("NewApi")
public class Applications extends Application implements OnExceptionCaught {
	private static CrashCaught mCrashCaught = null;// 一直伴随着整个app，用static，防止被清理掉。
	public static boolean debug = false;
	private Services mServices = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mCrashCaught = CrashCaught.getInstance();
		mCrashCaught.setOnExceptionCaught(Applications.this);
		Intent intent = new Intent(getApplicationContext(), Services.class);
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mServices = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			CrashBinder binder = (CrashBinder) service;
			mServices = binder.getService();
		}
	};

	@Override
	public void getOnErrorCaught(String error) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
	}

	public void exit() {
		unbindService(mServiceConnection);
	}

}
