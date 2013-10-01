package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class GanJi extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.ganji);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewGanJi);
        MainActivity.setWebView(wv, this);
        MainActivity.setSa(this);
        MainActivity.mWebView.loadUrl(MyData.GANJI);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.setSa(this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_ganji);
		super.onResume();
	}
	
	@Override
	public void adBlock() {
		String js = "javascript:void((function(){" +
				"document.getElementsByClassName(\"gj-ad\")[0].style.display=\"none\";" +
				"document.getElementsByClassName(\"gj-ad\")[1].style.display=\"none\";" +
				"document.getElementsByClassName(\"gj-ad\")[2].style.display=\"none\";" +
				"document.getElementsByClassName(\"gj-ad\")[3].style.display=\"none\";" +
				"document.getElementsByClassName(\"gj-ad\")[4].style.display=\"none\";" +
				"document.getElementsByClassName(\"gj-ad gj-ad2\")[0].style.display=\"none\";" +
				"})());";
		MainActivity.mWebView.loadUrl(js);
		Log.i(MainActivity.TAG, "ganji ad");
	}
}
