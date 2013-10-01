package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class Zread extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.zread);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewZread);
        wv.getSettings().setUserAgentString(MyData.UA_CHROME_PC);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.ZREAD);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_zread);
		super.onResume();
	}
}
