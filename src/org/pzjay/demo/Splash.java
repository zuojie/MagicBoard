package org.pzjay.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity{
/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        Handler x = new Handler();
        x.postDelayed(new splashhandler(), 2000); //延时2秒
        
    }
    class splashhandler implements Runnable{

        public void run() {
        	startActivity(new Intent(getApplication(), MagicBoard.class));  //其中HelloAndroidActivity是你要进入的下一个Activity
            Splash.this.finish(); 
        }
        
    }
}
