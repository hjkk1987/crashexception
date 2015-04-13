package com.exception.crash.fun;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;

import android.os.Looper;
import android.widget.Toast;

public class CrashCaught implements UncaughtExceptionHandler {

	private static CrashCaught mInstance = null;
	private Thread.UncaughtExceptionHandler mExceptionHandler = null;
	private OnExceptionCaught onExceptionCaught = null;
	private String _str_device_info = "";// ��ȡ�豸��Ϣ
	private String _str_system_info = "";// ��ȡϵ�y�汾����Ϣ
	private String _str_app_info = "";// ��ȡ����汾����Ϣ

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
	 * ��ʼ���쳣��׽
	 */
	private void CrashInit() {
		// ��ȡϵͳĬ�ϵ��쳣��׽���
		mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		// ����ϵͳ���쳣��׽������
		Thread.setDefaultUncaughtExceptionHandler(this);
		// ����ǻ�ȡ�ֻ��豸�ͺ�
		_str_device_info = android.os.Build.MODEL;
		// ��ȡ�ֻ�ϵ�y�汾����Ϣ
		_str_system_info = android.os.Build.VERSION.RELEASE;
	}

	/*************************
	 * ���ýӿں�������
	 */

	public void setOnExceptionCaught(OnExceptionCaught onExceptionCaught) {
		this.onExceptionCaught = onExceptionCaught;
	}

	/*************************
	 * ���������񵽵��쳣��Ϣ
	 */
	private boolean handlerException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// ʹ��Toast����ʾ�쳣��Ϣ
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String _str_time = format.format(time);// ��ʾ���ǵ�ǰʱ����Ϣ��
		StringBuffer stringBuffer = new StringBuffer();// ��ʼ���ַ�����Ϣ��
		stringBuffer.append("Info:");
		stringBuffer.append("Device:" + _str_device_info);// ����豸��Ϣ
		stringBuffer.append("  "+_str_system_info);//ϵͳ��Ϣ��
		//����������Ӧ����Ҫ��ӵ�ǰ������汾�š�
		// ��������Ϣ������ȥ
		if (onExceptionCaught != null) {
			onExceptionCaught.getOnErrorCaught(stringBuffer.toString());
		}
		return true;
	}

	/**************************************
	 * ����������
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
	 * �ӿں�������
	 * 
	 * @author beiyong01
	 * 
	 */
	public interface OnExceptionCaught {
		public void getOnErrorCaught(String error);
	}
}
