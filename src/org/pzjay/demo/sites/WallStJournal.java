package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.ProgressBar;

public class WallStJournal extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.wallst);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewWallSt);
        wv.getSettings().setDefaultZoom(ZoomDensity.CLOSE);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.WALLST);
    }
    
    @Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_wallst);
		super.onResume();
	}
}
