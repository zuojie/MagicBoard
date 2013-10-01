package org.pzjay.demo;

import android.util.Log;

final public class GetHtmlSrc {
	public static String jsGerSrc = "javascript:window.pz_get_src.showSrcCode(" +
	"document.getElementsByTagName('html')[0].innerHTML" +
	");";
	public void showSrcCode(String src) {
		Log.i(MainActivity.TAG, src);
	}
}
