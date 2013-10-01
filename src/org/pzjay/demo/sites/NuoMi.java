package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class NuoMi extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.nuomi);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewNuoMi);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.NUOMI);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_nuomi);
		super.onResume();
	}
	
	@Override
	public void adBlock() {
		String js = "javascript:void((function(){" +
				"document.getElementsByTagName(\"a\")[8].style.display=\"none\";" +
				"document.getElementsByClassName(\"more\")[0].style.display=\"none\";" +
				"})());";
		MainActivity.mWebView.loadUrl(js);
		Log.i(MainActivity.TAG, "nuomi ad");
	}
}
