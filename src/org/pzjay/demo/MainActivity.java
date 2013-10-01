package org.pzjay.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pzjay.demo.sites.*;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.ZoomDensity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Pzjay 
 * 需求: 
 * 回到首页中对当前的activity进行销毁，使下次调用强制执行onCreate
 */
public class MainActivity extends TabActivity implements 
							OnTouchListener, OnGestureListener {
	public static WebView mWebView;
	public static ProgressBar loadingState;
	public static final String TAG = "PZJAY";
	public static final String ALERT = "再按一次返回首页";
	public static MainActivity mainActivity = null;
	// 持有当前展现在用户面前的activity实例，通过多态
	// 按需执行各自的广告拦截js
	private static SiteActivity sa = null;
	
	// 手势识别
	private static boolean loadGestureOK = false;
	private static GestureLibrary gestureLibrary;
	
	private static boolean logOut;
	// 蛋疼的WeiBo.java文件有自己的OnKeyDown函数，那里要访问此变量，故设为public
	public static boolean isBack;
	private static boolean isFullScreen;
	private static long start;
	private static Context currentContext;
	private static MenuItem fullScreen, night, 
					quit, refresh, stop, toTop, shareTxt;
	public static ProgressBar progressBar;
	// 选项菜单
	private static enum itemId {
		FULL_SCREEN, NIGHT, HOMEBOARD, QUIT, REFRESH, STOP, TOTOP, SHARETXT
	};
	// 手势
	private GestureDetector gt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// request feature
															// 语句一定要卸载onCreat和setContent语句中间
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost;
		currentContext = this;

		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab
		mainActivity = this;
		logOut = false;
		isBack = false;
		isFullScreen = false;
		gt = new GestureDetector((OnGestureListener)this);
		
		Intent intentRecv = getIntent();
		String type = intentRecv.getStringExtra("type");
		Boolean night = intentRecv.getBooleanExtra("night", false);
		if (night == true) {
			intoNight();
		}
		List<Class> cls= MyData.clsMap.get(type);
		List<String> tit = MyData.titMap.get(type);
		List<Integer> id = MyData.idMap.get(type);
		int cnt = cls.size();
		for (int i = 0; i < cnt; ++ i) {
			intent = new Intent().setClass(this, cls.get(i));
			spec = tabHost.newTabSpec(tit.get(i)).setIndicator(tit.get(i),
					res.getDrawable(id.get(i).intValue())).setContent(intent);
			tabHost.addTab(spec);
		}
		tabHost.setCurrentTab(0);
	}

	public static void initWebView(final WebView wv) {
		wv.setHorizontalScrollBarEnabled(false);
		wv.setSaveEnabled(true);
		wv.setClickable(true);
		wv.setHorizontalScrollbarOverlay(true);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setSupportZoom(true);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setSaveFormData(true);
		wv.getSettings().setSavePassword(true);
		wv.setInitialScale(70);
		wv.requestFocus();
		wv.requestFocusFromTouch();
		wv.setWebViewClient(mainActivity.new MyWebViewClient());
		wv.setWebChromeClient(new WebChromeClient());
		// wv.addJavascriptInterface(null, "classNameBeExposedInJs");
		wv.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
            	switch(event.getAction()) {
            	case MotionEvent.ACTION_DOWN:
            	case MotionEvent.ACTION_UP:
                	if (!v.hasFocus()) {
                		v.requestFocus();
                	}
                	break;
            	}
            	return false;
            }
        });
	}

	public static void setWebView(WebView wv, Context context) {
		setSa((SiteActivity)context);
		currentContext = context;
		initWebView(wv);
		mWebView = wv;
	}
	
	public static SiteActivity getSa() {
		return sa;
	}
	
	public static void setSa(SiteActivity newSa) {
		sa = newSa;
	}
	// 调出分享列表
	public static void shareText(Context context, String title, CharSequence text) {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    intent.putExtra(Intent.EXTRA_SUBJECT, title);
	    intent.putExtra(Intent.EXTRA_TEXT, text);
	    context.startActivity(Intent.createChooser(intent, title));
	}

	// WebViewClient帮助WebView处理各种通知，请求事件，页面加载==
	class MyWebViewClient extends WebViewClient {
		private boolean loadingReallyFinished = false;
		private boolean redirect = false;

		public void onReceivedError(WebView view, int errorCode,
				String description) {
			loadFinished();
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			loadFinished();
		}

		public void loadFinished() {
			// 不等到网页全部加载完毕就进行广告拦截
			if (getSa() != null) {
				getSa().adBlock(); 
			}			
			if (!redirect) {
				loadingReallyFinished = true;
			}
			if (loadingReallyFinished && !redirect) {
				progressBar.setVisibility(View.GONE);// 去掉加载圈
			} else {
				redirect = false;
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			if (!isBack && !progressBar.isShown()) { // 如果是后退，则不显示loading状态
				progressBar.setVisibility(View.VISIBLE);// 显示加载圈
			} else {
				isBack = false; // 点击后退后再次点击网页内链接可能不出现loading，原因就是没有对isBack进行复位
			}
			loadingReallyFinished = false;
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (!loadingReallyFinished) {
				redirect = true;
			}

			loadingReallyFinished = false;
			view.loadUrl(url);
			return true;
		}
	}

	// WebChromeClient辅助WebView处理JS对话框，网站图标，title和加载进度等
	class MyWebChromeClient extends WebChromeClient {

		@Override
		public void getVisitedHistory(ValueCallback<String[]> callback) {
			super.getVisitedHistory(callback);
		}

		// 设置网页加载的进度条
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
		}

		// 设置应用程序的标题
		@Override
		public void onReceivedTitle(WebView view, String title) {
			MainActivity.this.setTitle(title);
			super.onReceivedTitle(view, title);
		}
	}

	// ///////////选项菜单
	// 选项菜单创建时要调用一次
	public boolean onCreateOptionsMenu(Menu menu) {
		String mode;
		int iconId;
		refresh = menu.add(0, itemId.REFRESH.ordinal(), 0, "刷新").setIcon(
				R.drawable.refresh);
		
		// 停止加载先不加
		/*
		stop = menu.add(0, itemId.STOP.ordinal(), 0, "停止加载").setIcon(
				R.drawable.stop);
		*/
		mode = "进入全屏";
		iconId = R.drawable.full_screen;
		if (isFullScreen) {
			mode = "退出全屏";
			iconId = R.drawable.ic_menu_crop;
		}
		fullScreen = menu.add(0, itemId.FULL_SCREEN.ordinal(), 1, mode)
				.setIcon(iconId);
		
		toTop = menu.add(0, itemId.TOTOP.ordinal(), 2, "回到顶部").setIcon(
				R.drawable.top);
		
		mode = "回到首页";
		quit = menu.add(0, itemId.HOMEBOARD.ordinal(), 3, mode).setIcon(
				R.drawable.home);
		
		// 退出
		mode = "退出";
		quit = menu.add(0, itemId.QUIT.ordinal(), 4, mode).setIcon(
				R.drawable.quit);

		
		mode = "分享选中内容";
		iconId = R.drawable.share_txt;
		shareTxt = menu.add(0, itemId.SHARETXT.ordinal(), 5, mode).setIcon(iconId);
		
		mode = "省电模式";
		iconId = R.drawable.night;
		if (GlobleState.isNight) {
			mode = "正常模式";
			iconId = R.drawable.day;
		}
		night = menu.add(0, itemId.NIGHT.ordinal(), 6, mode).setIcon(iconId);
		return true;
	}

	// 每次显示选项菜单前要调用
	public boolean onMenuOpened(int featureId,Menu menu) {
		if (isFullScreen) {
			fullScreen.setIcon(R.drawable.ic_menu_crop);
			fullScreen.setTitle("退出全屏");
		} else {
			fullScreen.setIcon(R.drawable.full_screen);
			fullScreen.setTitle("进入全屏");
		}
		if (GlobleState.isNight) {
			night.setIcon(R.drawable.day);
			night.setTitle("正常模式");
		} else {
			night.setIcon(R.drawable.night);
			night.setTitle("省电模式");
		}
		return true;
	}
	
	// 响应之
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == itemId.FULL_SCREEN.ordinal()) {
			if (isFullScreen) {
				getTabHost().getTabWidget().setVisibility(View.VISIBLE);
				final WindowManager.LayoutParams attrs = getWindow()
						.getAttributes();
				attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
				getWindow().setAttributes(attrs);
				getWindow().clearFlags(
						WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
				isFullScreen = false;
				fullScreen.setIcon(R.drawable.full_screen);
				fullScreen.setTitle("进入全屏");
			} else {
				getTabHost().getTabWidget().setVisibility(View.GONE);
				WindowManager.LayoutParams attrs = getWindow().getAttributes();
				attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
				getWindow().setAttributes(attrs);
				getWindow().addFlags(
						WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
				// 标记全屏，然后动态更新全屏图标
				isFullScreen = true;
				fullScreen.setIcon(R.drawable.ic_menu_crop);
				fullScreen.setTitle("退出全屏");
			}
		} else if (item.getItemId() == itemId.NIGHT.ordinal()) { // 省电模式
			if (GlobleState.isNight) {
				GlobleState.isNight = false; // 退出省电模式
				resetNight();
				night.setIcon(R.drawable.night);
				night.setTitle("省电模式");
			} else {
				intoNight();
				night.setIcon(R.drawable.day);
				night.setTitle("正常模式");
			}
		} else if (item.getItemId() == itemId.QUIT.ordinal()) { // 退出
			System.exit(0);
		} else if (item.getItemId() == itemId.REFRESH.ordinal()) {
			mWebView.reload();
		} else if(item.getItemId() == itemId.TOTOP.ordinal()) {
			mWebView.pageUp(true); // 滚到页面顶部
		} else if (item.getItemId() == itemId.STOP.ordinal()) {
			mWebView.stopLoading();
		} else if (item.getItemId() == itemId.SHARETXT.ordinal()) {
			// 获取剪贴板内容
			CharSequence txt = "null";
			ClipboardManager cbm=(ClipboardManager)getSystemService(currentContext.CLIPBOARD_SERVICE);
			if (cbm.hasText()) {
				txt = cbm.getText();
			}
			// 显示分享到页面
			if (txt.toString().compareTo("null") != 0) {
				shareText(mainActivity, "分享内容到", txt);
			} else {
				Toast.makeText(currentContext, "剪贴板中无数据", Toast.LENGTH_LONG).show();
			}
		} else if (item.getItemId() == itemId.HOMEBOARD.ordinal()) { // 回到首页
			returnHome();
		}
		return true;
	}
    
	public void returnHome() {
		Intent intent = new Intent(this, MagicBoard.class);
		Bundle bundle = new Bundle(); 
		bundle.putBoolean("night", GlobleState.isNight);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		//overridePendingTransition(R.anim.my_scale_action,  
		  //              R.anim.my_alpha_action);
		System.exit(0);
	}
	
	public void resetNight() {
		ContentResolver cr = getContentResolver();
		Settings.System.putInt(cr, "screen_brightness", 255); 
		Window window = getWindow(); 
		LayoutParams attributes = window.getAttributes();
		attributes.screenBrightness = 1.0f;
		getWindow().setAttributes(attributes);
	}
	
	public void intoNight() {
		Log.i(TAG, "set");
		GlobleState.isNight = true;
		ContentResolver cr = getContentResolver();
		Settings.System.putFloat(cr, "screen_brightness", GlobleState.LIGHT); 
		Window window = getWindow(); 
		LayoutParams attributes = window.getAttributes(); 
		float flevel = GlobleState.LIGHT;
		attributes.screenBrightness = flevel / 255.0f;
		getWindow().setAttributes(attributes);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		super.onTouchEvent(event);
		return  gt.onTouchEvent(event); 
	}
	
	/// 手势
    @Override    
    public boolean onTouchEvent(MotionEvent event) {
    	super.onTouchEvent(event);
        return this.gt.onTouchEvent(event);    
    }
    
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY) {
        if (e1.getX()-e2.getX() > SnsConstant.getFlingMinXDistance() 
        		&& Math.abs(e1.getY()-e2.getY()) < SnsConstant.getFingMaxYDistance()
                && Math.abs(velocityX) > SnsConstant.getFlingMinVelocity()) {
            // 向左滑屏，前进，貌似要重新加载，目前体验不太好
        	if (mWebView.canGoForward()) {
				mWebView.goForward();
			}
        } else if (e2.getX()-e1.getX() > SnsConstant.getFlingMinXDistance() 
        		&& Math.abs(e1.getY()-e2.getY()) < SnsConstant.getFingMaxYDistance() 
                && Math.abs(velocityX) > SnsConstant.getFlingMinVelocity()) {    
            // 向右滑屏：后退
        	if (mWebView.canGoBack()) {
				mWebView.goBack();
				isBack = true;
				logOut = false;
			}
        }  
        return true;    
    }
    
    // 获取剪贴板内容
	@Override
	public void onLongPress(MotionEvent e) {
		Toast.makeText(currentContext, "请滑过想要复制的区域", Toast.LENGTH_SHORT).show();
		try {
			KeyEvent shiftPressEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
					KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
			shiftPressEvent.dispatch(mWebView);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent e){
	    super.dispatchTouchEvent(e);
	    return gt.onTouchEvent(e);
	}
	
	// 此处不重载在onKeyDown的话，在网页loading时按后退键会导致程序直接退出
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	MainActivity.isBack = false;
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			if (MainActivity.mWebView.canGoBack()) {
	        	MainActivity.mWebView.goBack();
	        	logOut = false;
	        	MainActivity.isBack = true;
	            return true;
	        } else if (!logOut){
				Toast.makeText(this, ALERT, Toast.LENGTH_SHORT).show();
				logOut = true;
				start = System.currentTimeMillis();
				return true;
			} else {
				if (System.currentTimeMillis() - start <= MagicBoard.QUIT_INTERVAL) {
					returnHome();
					return super.onKeyDown(keyCode, event);
				} else {
					Toast.makeText(this, ALERT, Toast.LENGTH_SHORT).show();
					logOut = true;
					start = System.currentTimeMillis();
					return true;
				}
			}
	    } else {
	    	return super.onKeyDown(keyCode, event);
	    }
    }

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		// 恢复夜间模式
		if (GlobleState.isNight) {
			intoNight();
		}
		super.onResume();
	}

	@Override
	protected void onStop() {
		resetNight();
		super.onStop();
	}
	
	// 共有判断是否退出的函数
	public boolean isOut(SiteActivity sa, int keyCode, KeyEvent event) {
		if (MainActivity.mWebView.canGoBack()) {
        	MainActivity.mWebView.goBack();
        	logOut = false;
        	MainActivity.isBack = true;
            return true;
        } else if (!logOut){
			Toast.makeText((Context)sa, ALERT, Toast.LENGTH_SHORT).show();
			logOut = true;
			start = System.currentTimeMillis();
			return true;
		} else {
			if (System.currentTimeMillis() - start <= MagicBoard.QUIT_INTERVAL) {
				// 表示退出程序
				returnHome(); // 劫持退出事件，返回首页
				return false;
			} else {
				Toast.makeText((Context)sa, ALERT, Toast.LENGTH_SHORT).show();
				logOut = true;
				start = System.currentTimeMillis();
				return true;
			}
		}
    
	}
}