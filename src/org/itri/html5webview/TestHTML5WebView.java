package org.itri.html5webview;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public class TestHTML5WebView extends Activity {
	
	HTML5WebView mWebView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new HTML5WebView(this);
        
        if (savedInstanceState != null) {
        	mWebView.restoreState(savedInstanceState);
        } else {
        	//mWebView.loadUrl("http://www.youtube.com/embed/wGMLhaa98GI?autoplay=1");
            mWebView.loadUrl("http://player.vimeo.com/video/61524830?autoplay=1");
            //mWebView.loadUrl("http://www.uitzendinggemist.nl/player/15785533");
            //mWebView.loadUrl("http://www.wappzapp.tv/videostrip.php?id=011940562");
            //mWebView.loadUrl("http://media.zie.nl/e/?v=m1nzir6fh0iv");

        }
        
        setContentView(mWebView.getLayout());
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	mWebView.saveState(outState);
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	mWebView.stopLoading();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.inCustomView()) {
            	mWebView.hideCustomView();
            	return true;
            }
    	}
    	return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
         super.onConfigurationChanged(newConfig);
    }
}