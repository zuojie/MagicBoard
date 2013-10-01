package org.pzjay.demo.sites;

import org.pzjay.demo.MainActivity;
import org.pzjay.demo.MyData;
import org.pzjay.demo.R;
import org.pzjay.demo.SiteActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TQQ extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.qq);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewQQ);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.TQQ);
    }
    
    @Override
	protected void onResume() {
		Log.i(MainActivity.TAG, "QQonResume");
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_qq);
		super.onResume();
	}
}
