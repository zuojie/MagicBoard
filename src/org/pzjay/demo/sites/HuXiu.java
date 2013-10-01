package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class HuXiu extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.huxiu);

		logOut = false;
		wv = (WebView) findViewById(R.id.WebviewHuXiu);
		MainActivity.setWebView(wv, this);
		MainActivity.setSa(this);
		MainActivity.mWebView.loadUrl(MyData.HUXIU);
	}

	@Override
	protected void onResume() {
		MainActivity.setWebView(wv, this);
		MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_huxiu);
		super.onResume();
	}

	@Override
	public void adBlock() {
		String js = "javascript:void((function(){document.getElementsByClassName(\"footer\")[0].style.display=\"none\";})());";
		MainActivity.mWebView.loadUrl(js);
		Log.i(MainActivity.TAG, "huxiu ad");
	}
}
