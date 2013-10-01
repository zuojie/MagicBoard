package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class DouBan extends SiteActivity {
	public static boolean logOut;
	public static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.douban);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewDouBan);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.DOUBAN);
    }
    
    @Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_douban);
		super.onResume();
	}
}
