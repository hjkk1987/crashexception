package com.exception.crash.main;

import com.exception.crash.app.Applications;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Applications mApplications = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mApplications = (Applications) getApplication();
		String _dev_info = new String("Product Model: "
				+ android.os.Build.MODEL + "," + android.os.Build.VERSION.SDK
				+ "," + android.os.Build.VERSION.RELEASE);
		Toast.makeText(MainActivity.this, _dev_info, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		//返回键退出整个应用
		mApplications.exit();
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
