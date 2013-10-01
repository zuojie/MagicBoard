package org.pzjay.demo;

import org.pzjay.demo.MainActivity;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;

public class SiteActivity extends Activity {
	private static boolean logOut;
	private static long start;
	
	public static boolean isLogOut() {
		return logOut;
	}
	public static void setLogOut(boolean logOut) {
		SiteActivity.logOut = logOut;
	}
	public static long getStart() {
		return start;
	}
	public static void setStart(long start) {
		SiteActivity.start = start;
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	MainActivity.isBack = false;
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			if (MainActivity.mainActivity != null && 
					MainActivity.mainActivity.isOut(this, keyCode, event)) {
				return true;
			} else {
				return	super.onKeyDown(keyCode, event);
			}
		} else {
	    	return super.onKeyDown(keyCode, event);
	    }
    }
	
	// 各个网站共同继承的删除广告函数
	public void adBlock(){
		Log.i(MainActivity.TAG, "Main ad");
	}
}
