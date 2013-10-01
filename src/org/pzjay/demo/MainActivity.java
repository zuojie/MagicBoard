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
 * ����: 
 * �ص���ҳ�жԵ�ǰ��activity�������٣�ʹ�´ε���ǿ��ִ��onCreate
 */
public class MainActivity extends TabActivity implements 
							OnTouchListener, OnGestureListener {
	public static WebView mWebView;
	public static ProgressBar loadingState;
	public static final String TAG = "PZJAY";
	public static final String ALERT = "�ٰ�һ�η�����ҳ";
	public static MainActivity mainActivity = null;
	// ���е�ǰչ�����û���ǰ��activityʵ����ͨ����̬
	// ����ִ�и��ԵĹ������js
	private static SiteActivity sa = null;
	
	// ����ʶ��
	private static boolean loadGestureOK = false;
	private static GestureLibrary gestureLibrary;
	
	private static boolean logOut;
	// ���۵�WeiBo.java�ļ����Լ���OnKeyDown����������Ҫ���ʴ˱���������Ϊpublic
	public static boolean isBack;
	private static boolean isFullScreen;
	private static long start;
	private static Context currentContext;
	private static MenuItem fullScreen, night, 
					quit, refresh, stop, toTop, shareTxt;
	public static ProgressBar progressBar;
	// ѡ��˵�
	private static enum itemId {
		FULL_SCREEN, NIGHT, HOMEBOARD, QUIT, REFRESH, STOP, TOTOP, SHARETXT
	};
	// ����
	private GestureDetector gt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// request feature
															// ���һ��Ҫж��onCreat��setContent����м�
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
	// ���������б�
	public static void shareText(Context context, String title, CharSequence text) {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    intent.putExtra(Intent.EXTRA_SUBJECT, title);
	    intent.putExtra(Intent.EXTRA_TEXT, text);
	    context.startActivity(Intent.createChooser(intent, title));
	}

	// WebViewClient����WebView�������֪ͨ�������¼���ҳ�����==
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
			// ���ȵ���ҳȫ��������Ͼͽ��й������
			if (getSa() != null) {
				getSa().adBlock(); 
			}			
			if (!redirect) {
				loadingReallyFinished = true;
			}
			if (loadingReallyFinished && !redirect) {
				progressBar.setVisibility(View.GONE);// ȥ������Ȧ
			} else {
				redirect = false;
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			if (!isBack && !progressBar.isShown()) { // ����Ǻ��ˣ�����ʾloading״̬
				progressBar.setVisibility(View.VISIBLE);// ��ʾ����Ȧ
			} else {
				isBack = false; // ������˺��ٴε����ҳ�����ӿ��ܲ�����loading��ԭ�����û�ж�isBack���и�λ
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

	// WebChromeClient����WebView����JS�Ի�����վͼ�꣬title�ͼ��ؽ��ȵ�
	class MyWebChromeClient extends WebChromeClient {

		@Override
		public void getVisitedHistory(ValueCallback<String[]> callback) {
			super.getVisitedHistory(callback);
		}

		// ������ҳ���صĽ�����
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
		}

		// ����Ӧ�ó���ı���
		@Override
		public void onReceivedTitle(WebView view, String title) {
			MainActivity.this.setTitle(title);
			super.onReceivedTitle(view, title);
		}
	}

	// ///////////ѡ��˵�
	// ѡ��˵�����ʱҪ����һ��
	public boolean onCreateOptionsMenu(Menu menu) {
		String mode;
		int iconId;
		refresh = menu.add(0, itemId.REFRESH.ordinal(), 0, "ˢ��").setIcon(
				R.drawable.refresh);
		
		// ֹͣ�����Ȳ���
		/*
		stop = menu.add(0, itemId.STOP.ordinal(), 0, "ֹͣ����").setIcon(
				R.drawable.stop);
		*/
		mode = "����ȫ��";
		iconId = R.drawable.full_screen;
		if (isFullScreen) {
			mode = "�˳�ȫ��";
			iconId = R.drawable.ic_menu_crop;
		}
		fullScreen = menu.add(0, itemId.FULL_SCREEN.ordinal(), 1, mode)
				.setIcon(iconId);
		
		toTop = menu.add(0, itemId.TOTOP.ordinal(), 2, "�ص�����").setIcon(
				R.drawable.top);
		
		mode = "�ص���ҳ";
		quit = menu.add(0, itemId.HOMEBOARD.ordinal(), 3, mode).setIcon(
				R.drawable.home);
		
		// �˳�
		mode = "�˳�";
		quit = menu.add(0, itemId.QUIT.ordinal(), 4, mode).setIcon(
				R.drawable.quit);

		
		mode = "����ѡ������";
		iconId = R.drawable.share_txt;
		shareTxt = menu.add(0, itemId.SHARETXT.ordinal(), 5, mode).setIcon(iconId);
		
		mode = "ʡ��ģʽ";
		iconId = R.drawable.night;
		if (GlobleState.isNight) {
			mode = "����ģʽ";
			iconId = R.drawable.day;
		}
		night = menu.add(0, itemId.NIGHT.ordinal(), 6, mode).setIcon(iconId);
		return true;
	}

	// ÿ����ʾѡ��˵�ǰҪ����
	public boolean onMenuOpened(int featureId,Menu menu) {
		if (isFullScreen) {
			fullScreen.setIcon(R.drawable.ic_menu_crop);
			fullScreen.setTitle("�˳�ȫ��");
		} else {
			fullScreen.setIcon(R.drawable.full_screen);
			fullScreen.setTitle("����ȫ��");
		}
		if (GlobleState.isNight) {
			night.setIcon(R.drawable.day);
			night.setTitle("����ģʽ");
		} else {
			night.setIcon(R.drawable.night);
			night.setTitle("ʡ��ģʽ");
		}
		return true;
	}
	
	// ��Ӧ֮
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
				fullScreen.setTitle("����ȫ��");
			} else {
				getTabHost().getTabWidget().setVisibility(View.GONE);
				WindowManager.LayoutParams attrs = getWindow().getAttributes();
				attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
				getWindow().setAttributes(attrs);
				getWindow().addFlags(
						WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
				// ���ȫ����Ȼ��̬����ȫ��ͼ��
				isFullScreen = true;
				fullScreen.setIcon(R.drawable.ic_menu_crop);
				fullScreen.setTitle("�˳�ȫ��");
			}
		} else if (item.getItemId() == itemId.NIGHT.ordinal()) { // ʡ��ģʽ
			if (GlobleState.isNight) {
				GlobleState.isNight = false; // �˳�ʡ��ģʽ
				resetNight();
				night.setIcon(R.drawable.night);
				night.setTitle("ʡ��ģʽ");
			} else {
				intoNight();
				night.setIcon(R.drawable.day);
				night.setTitle("����ģʽ");
			}
		} else if (item.getItemId() == itemId.QUIT.ordinal()) { // �˳�
			System.exit(0);
		} else if (item.getItemId() == itemId.REFRESH.ordinal()) {
			mWebView.reload();
		} else if(item.getItemId() == itemId.TOTOP.ordinal()) {
			mWebView.pageUp(true); // ����ҳ�涥��
		} else if (item.getItemId() == itemId.STOP.ordinal()) {
			mWebView.stopLoading();
		} else if (item.getItemId() == itemId.SHARETXT.ordinal()) {
			// ��ȡ����������
			CharSequence txt = "null";
			ClipboardManager cbm=(ClipboardManager)getSystemService(currentContext.CLIPBOARD_SERVICE);
			if (cbm.hasText()) {
				txt = cbm.getText();
			}
			// ��ʾ����ҳ��
			if (txt.toString().compareTo("null") != 0) {
				shareText(mainActivity, "�������ݵ�", txt);
			} else {
				Toast.makeText(currentContext, "��������������", Toast.LENGTH_LONG).show();
			}
		} else if (item.getItemId() == itemId.HOMEBOARD.ordinal()) { // �ص���ҳ
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
	
	/// ����
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
            // ��������ǰ����ò��Ҫ���¼��أ�Ŀǰ���鲻̫��
        	if (mWebView.canGoForward()) {
				mWebView.goForward();
			}
        } else if (e2.getX()-e1.getX() > SnsConstant.getFlingMinXDistance() 
        		&& Math.abs(e1.getY()-e2.getY()) < SnsConstant.getFingMaxYDistance() 
                && Math.abs(velocityX) > SnsConstant.getFlingMinVelocity()) {    
            // ���һ���������
        	if (mWebView.canGoBack()) {
				mWebView.goBack();
				isBack = true;
				logOut = false;
			}
        }  
        return true;    
    }
    
    // ��ȡ����������
	@Override
	public void onLongPress(MotionEvent e) {
		Toast.makeText(currentContext, "�뻬����Ҫ���Ƶ�����", Toast.LENGTH_SHORT).show();
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
	
	// �˴���������onKeyDown�Ļ�������ҳloadingʱ�����˼��ᵼ�³���ֱ���˳�
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
		// �ָ�ҹ��ģʽ
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
	
	// �����ж��Ƿ��˳��ĺ���
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
				// ��ʾ�˳�����
				returnHome(); // �ٳ��˳��¼���������ҳ
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