package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.ProgressBar;

public class FtCn extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.ftcn);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewFtCn);
        //wv.getSettings().setDefaultZoom(ZoomDensity.CLOSE);
        //wv.setInitialScale(600);
        wv.getSettings().setUserAgentString(MyData.UA_OPERA);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.FTCN);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_ftcn);
		super.onResume();
	}
}
