package org.pzjay.demo.sites;

import org.pzjay.demo.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class GuoKr extends SiteActivity {
	private static boolean logOut;
	private static long start;
	private static WebView wv;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.guokr);
       
        logOut = false;
        wv = (WebView) findViewById(R.id.WebviewGuoKr);
		//wv.getSettings().setUserAgentString(MyData.UA_UC);
        wv.getSettings().setUserAgentString(MyData.UA_OPERA);
        MainActivity.setWebView(wv, this);
        MainActivity.mWebView.loadUrl(MyData.GUOKR);
    }

	@Override
	protected void onResume() {
        MainActivity.setWebView(wv, this);
        MainActivity.progressBar = (ProgressBar) findViewById(R.id.pb_guokr);
		super.onResume();
	}
	
	@Override
    public void adBlock() {
		/*
		String js = "javascript:void((function(){ " +
		//"var html5Nodes = document.getElementsByTagName(\"section\");" +
		"for (var i = 0; i < 1; i ++) { " +
			"var oldNode = document.getElementById(\"h_minisite__10\"); " +
			"var newNode = document.createElement(\"div\");" +
			"newNode.id = oldNode.id;" +
			"newNode.data-minstiteid = oldNode.data-minstiteid;" +			
			"newNode.className = oldNode.className;" +
			"newNode.style = oldNode.style;" +
			"newNode.innerHTML = oldNode.innerHTML;" +
			"oldNode.parentNode.replaceChild(newNode, oldNode); " +
		"}" +
		"html5Nodes = document.getElementsByTagName(\"article\");" +
		"for (var i = 0; i < html5Nodes.length; i ++) { " +
			"oldNode = html5Nodes[i]; " +
			"newNode = document.createElement(\"div\");" +
			//"newNode.id = oldNode.id;" +
			//"newNode.className = oldNode.className;" +
			//"newNode.style = oldNode.style;" +
			"newNode.innerHTML = oldNode.innerHTML;" +
			"oldNode.parentNode.replaceChild(newNode, oldNode); " +
		"}" +
		"})())";
		MainActivity.mWebView.loadUrl(js);
		*/
        Log.i(MainActivity.TAG, "guokr ad");
	}
}
