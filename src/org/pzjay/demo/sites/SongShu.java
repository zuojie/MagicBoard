package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class SongShu extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.songshu);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewSongShu);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.SONGSHU);
    }
    
    @Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_songshu);
		super.onResume();
	}
    
	@Override
	public void adBlock() {
		String js = "javascript:void((function(){" +
				"document.getElementById(\"footerBao\").style.display=\"none\";" +
				"})());";
		MainActivity.mWebView.loadUrl(js);
		Log.i(MainActivity.TAG, "songshuhui ad");
	}
}
