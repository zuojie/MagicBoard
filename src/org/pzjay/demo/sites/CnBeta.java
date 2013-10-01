package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class CnBeta extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.cnbeta);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewCnBeta);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.CNBETA);
    }
    
    @Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_cnbeta);
		super.onResume();
	}
    
    @Override
    public void adBlock() {
    	String js = "javascript:void((function(){document.getElementsByClassName(\"footer\")[0].style.display=\"none\";})());";
        MainActivity.mWebView.loadUrl(js);
        Log.i(MainActivity.TAG, "cnbeta ad");
    }
}
