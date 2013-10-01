package org.pzjay.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;
 /**
  * 
  * @author Pzjay
  * 网站显示完善：去除广告，订制UA，寻找最适合在移动设备显示的站点
  */
public class MagicBoard extends Activity {
	public static final long QUIT_INTERVAL = 2000; // 两次退出键的时间间隔2s
	
	private ImageView sns;
	private ImageView tech;
	private ImageView news;
	private ImageView bbs;
	private ImageView life;
	private ImageView shopping;
	private ImageView it;
	private static Bundle bundle;
	private static Intent intent;
	private static boolean logOut;
	private static long start;
	private static MenuItem quit, about;
	// 选项菜单
	private static enum itemId {ABOUT, QUIT};
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.home);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        
        logOut = false;
        sns = (ImageView)findViewById(R.id.sns);
        tech = (ImageView)findViewById(R.id.tech);
        news = (ImageView)findViewById(R.id.news);
        bbs = (ImageView)findViewById(R.id.bbs);
        life = (ImageView)findViewById(R.id.life);
        shopping = (ImageView)findViewById(R.id.shopping);
        it = (ImageView)findViewById(R.id.it);
        
        sns.setImageResource(R.drawable.sns_logo);
        tech.setImageResource(R.drawable.tech_logo);
        news.setImageResource(R.drawable.news_logo);
        bbs.setImageResource(R.drawable.bbs_logo);
        life.setImageResource(R.drawable.life_logo);
        it.setImageResource(R.drawable.it_logo);
        shopping.setImageResource(R.drawable.shopping_logo);
        
        bundle = new Bundle();
        
        sns.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("sns");
			}
        });
        tech.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("tech");
			}
        });
        news.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("news");
			}
        });
        bbs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("bbs");
			}
        });
        life.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("life");
			}
        });
        it.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("it");
			}
        });
        shopping.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startUp("shopping");
			}
        });
        Intent intentRecv = getIntent();
        if (intentRecv != null) {
			Boolean night = intentRecv.getBooleanExtra("night", false);
			if (night == true) {
				intoNight();
			}
        }
    }
    
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
	
    public void startUp(String type) {
    	intent = new Intent(this, MainActivity.class);
    	bundle.clear();
		bundle.putString("type", type);
		bundle.putBoolean("night", GlobleState.isNight);
		intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		System.exit(RESULT_OK);
		
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	MainActivity.isBack = false;
		if (keyCode == KeyEvent.KEYCODE_BACK ) {
			if (!logOut){
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				logOut = true;
				start = System.currentTimeMillis();
				return true;
			} else {
				if (System.currentTimeMillis() - start <= QUIT_INTERVAL) {
					System.exit(RESULT_OK);
					return true;
				} else {
					Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
    	about = menu.add(0, itemId.ABOUT.ordinal(), 1, "关于").setIcon(
				R.drawable.about);
		quit = menu.add(0, itemId.QUIT.ordinal(), 0, "退出").setIcon(
				R.drawable.quit);
		return true;
	}
    
 // 响应之
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == itemId.QUIT.ordinal()) { // 退出
			if (GlobleState.isNight) {
				GlobleState.isNight = false;
				resetNight();
			}
			System.exit(0);
		} else if (item.getItemId() == itemId.ABOUT.ordinal()) {
			Toast toast = Toast
			.makeText(this,
					"\"Social Master, V1.0, By PZJAY, 2012/7/21, pengzuojie@gmail.com\"",
					Toast.LENGTH_LONG);
					toast.show();
		}
		return true;
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
		GlobleState.isNight = true;
		ContentResolver cr = getContentResolver();
		Settings.System.putFloat(cr, "screen_brightness", GlobleState.LIGHT); 
		Window window = getWindow(); 
		LayoutParams attributes = window.getAttributes(); 
		float flevel = GlobleState.LIGHT;
		attributes.screenBrightness = flevel / 255.0f;
		getWindow().setAttributes(attributes);
	}
}