package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class QiuBai extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.qiubai);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewQiuBai);
        wv.getSettings().setUserAgentString(MyData.UA_OPERA);
        wv.addJavascriptInterface(new GetHtmlSrc(), "pz_get_src");
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.QIUBAI);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_qiubai);
		super.onResume();
	}
	
	@Override
	public void adBlock() {
		String js = "javascript:void((function(){" +
				"document.getElementsByClassName('ad_top')[0].style.display='none';" +
				"document.getElementsByClassName('ad_bottom')[0].style.display='none';" + 
				//"document.getElementById('box').style.display='none';" + 
				"})());";
		MainActivity.mWebView.loadUrl(js);
		Log.i(MainActivity.TAG, "qiubai ad");
	}
}
