package com.exception.crash.fun;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;

import android.os.Looper;
import android.widget.Toast;

public class CrashCaught implements UncaughtExceptionHandler {

	private static CrashCaught mInstance = null;
	private Thread.UncaughtExceptionHandler mExceptionHandler = null;
	private OnExceptionCaught onExceptionCaught = null;
	private String _str_device_info = "";// 获取设备信息
	private String _str_system_info = "";// 获取系統版本号信息
	private String _str_app_info = "";// 获取软件版本号信息

	public CrashCaught() {
		// TODO Auto-generated constructor stub
		CrashInit();
	}

	public static CrashCaught getInstance() {
		if (mInstance == null) {
			mInstance = new CrashCaught();
		}
		return mInstance;
	}

	/***************************
	 * 初始化异常捕捉
	 */
	private void CrashInit() {
		// 获取系统默认的异常捕捉句柄
		mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置系统的异常捕捉处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		// 这边是获取手机设备型号
		_str_device_info = android.os.Build.MODEL;
		// 获取手机系統版本号信息
		_str_system_info = android.os.Build.VERSION.RELEASE;
	}

	/*************************
	 * 设置接口函数监听
	 */

	public void setOnExceptionCaught(OnExceptionCaught onExceptionCaught) {
		this.onExceptionCaught = onExceptionCaught;
	}

	/*************************
	 * 处理所捕获到的异常信息
	 */
	private boolean handlerException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// 使用Toast来显示异常信息
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String _str_time = format.format(time);// 显示的是当前时间信息。
		StringBuffer stringBuffer = new StringBuffer();// 初始化字符串信息。
		stringBuffer.append("Info:");
		stringBuffer.append("Device:" + _str_device_info);// 添加设备信息
		stringBuffer.append("  "+_str_system_info);//系统信息。
		//按道理来讲应该需要添加当前的软件版本号。
		// 将错误信息反馈回去
		if (onExceptionCaught != null) {
			onExceptionCaught.getOnErrorCaught(stringBuffer.toString());
		}
		return true;
	}

	/**************************************
	 * 函数的重载
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		if (!handlerException(ex) && mExceptionHandler != null) {
			mExceptionHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}

	}

	/*************************
	 * 接口函数定义
	 * 
	 * @author beiyong01
	 * 
	 */
	public interface OnExceptionCaught {
		public void getOnErrorCaught(String error);
	}
}
