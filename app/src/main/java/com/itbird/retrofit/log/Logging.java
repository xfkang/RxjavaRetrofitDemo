package com.itbird.retrofit.log;

import android.util.Log;

/**
 * 日志记录方法
 * Created by xfkang on 2016/6/6
 */
public final class Logging {

	private Logging() {
	}

	private static boolean mLoggingEnabled = true;

	public static void setDebugLogging(boolean enabled) {
		mLoggingEnabled = enabled;
	}

	public static boolean isDebugLogging() {
		return mLoggingEnabled;
	}

	public static void v(String tag, String msg) {
		if (mLoggingEnabled) {
			Log.v(tag, msg);
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (mLoggingEnabled) {
			Log.v(tag, msg, tr);
		}
	}

	public static void d(String tag, String msg) {
		if (mLoggingEnabled) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (mLoggingEnabled) {
			Log.d(tag, msg, tr);
		}
	}

	public static void i(String tag, String msg) {
		if (mLoggingEnabled) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (mLoggingEnabled) {
			Log.i(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg) {
		if (mLoggingEnabled) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (mLoggingEnabled) {
			Log.w(tag, msg, tr);
		}
	}

	public static void w(String tag, Throwable tr) {
		if (mLoggingEnabled) {
			Log.w(tag, tr);
		}
	}

	public static void e(String tag, String msg) {
		if (mLoggingEnabled) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (mLoggingEnabled) {
			Log.e(tag, msg, tr);
		}
	}
}