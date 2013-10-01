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
import android.view.LayoutInflater;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RenRen extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.rr);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewRR);
        wv.requestFocus();
        MainActivity.setWebView(wv, this);
        // 人人页面默认字体较大，调小一点，不过貌似没效果
        //MainActivity.mWebView.getSettings().setDefaultZoom(ZoomDensity.FAR);
        MainActivity.mWebView.loadUrl(MyData.RR);
    }
    
    @Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_rr);
		super.onResume();
	}
}
