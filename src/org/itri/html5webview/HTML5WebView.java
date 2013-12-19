package org.itri.html5webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class HTML5WebView extends WebView {
	
	private Context 							mContext;
	private MyWebChromeClient					mWebChromeClient;
	private View								mCustomView;
	private FrameLayout							mCustomViewContainer;
	private WebChromeClient.CustomViewCallback 	mCustomViewCallback;
	
	private FrameLayout							mContentView;
	private FrameLayout							mBrowserFrameLayout;
	private FrameLayout							mLayout;
	
	public FrameLayout getFrame() {
		return mLayout;
	}
	
    static final String LOGTAG = "HTML5WebView";
	    
	private void init(Context context) {
		mContext = context;		
		Activity a = (Activity) mContext;
				
		mLayout = new FrameLayout(mContext);
		
		mBrowserFrameLayout = new FrameLayout(mContext); // (FrameLayout) LayoutInflater.from(a).inflate(R.layout.custom_screen, null);
		mContentView = new FrameLayout(mContext); //(FrameLayout) mBrowserFrameLayout.findViewById(R.id.main_content);
		mCustomViewContainer = new FrameLayout(mContext); //(FrameLayout) mBrowserFrameLayout.findViewById(R.id.fullscreen_custom_content);
		
		
		mLayout.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mBrowserFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mContentView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mCustomViewContainer.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		

        mBrowserFrameLayout.addView(mCustomViewContainer);
        mBrowserFrameLayout.addView(mContentView);
        
		mLayout.addView(mBrowserFrameLayout, COVER_SCREEN_PARAMS);

		mWebChromeClient = new MyWebChromeClient();
	    setWebChromeClient(mWebChromeClient);
	    
	    setWebViewClient(new MyWebViewClient());
	       
	    // Configure the webview
	    WebSettings s = getSettings();
	    s.setBuiltInZoomControls(false);
	    s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
	    s.setUseWideViewPort(true);
	    s.setLoadWithOverviewMode(true);
	    s.setSavePassword(true);
	    s.setSaveFormData(true);
	    s.setJavaScriptEnabled(true);
        s.setUserAgentString("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25");
	    
	    // enable navigator.geolocation 
	    s.setGeolocationEnabled(true);
	    //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
	    
	    // enable Web Storage: localStorage, sessionStorage
	    s.setDomStorageEnabled(true);
	    
	    mContentView.addView(this);
	}
	
	/*@Override
	public void onPause () {
		Log.i(LOGTAG, "onPause called in the webview");
		this.loadData("", "text/html", "utf-8");
		super.onPause();
	}*/
	
	public HTML5WebView(Context context) {
		super(context);
		init(context);
	}

	public HTML5WebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public HTML5WebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public FrameLayout getLayout() {
		return mLayout;
	}
	
    public boolean inCustomView() {
		return (mCustomView != null);
	}
    
    public void hideCustomView() {
		mWebChromeClient.onHideCustomView();
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		if ((mCustomView == null) && canGoBack()){
    			goBack();
    			return true;
    		}
    	}
    	return super.onKeyDown(keyCode, event);
    }

    private class MyWebChromeClient extends WebChromeClient {
		private Bitmap 		mDefaultVideoPoster;
		private View 		mVideoProgressView;
    	
    	@Override
		public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)
		{
			//Log.i(LOGTAG, "here in on ShowCustomView");
	        HTML5WebView.this.setVisibility(View.GONE);
	        
	        // if a view already exists then immediately terminate the new one
	        if (mCustomView != null) {
	            callback.onCustomViewHidden();
	            return;
	        }
	        
	        mCustomViewContainer.addView(view);
	        mCustomView = view;
	        mCustomViewCallback = callback;
	        mCustomViewContainer.setVisibility(View.VISIBLE);
		}
		
		@Override
		public void onHideCustomView() {
			
			if (mCustomView == null)
				return;	       
			
			// Hide the custom view.
			mCustomView.setVisibility(View.GONE);
			
			// Remove the custom view from its container.
			mCustomViewContainer.removeView(mCustomView);
			mCustomView = null;
			mCustomViewContainer.setVisibility(View.GONE);
			mCustomViewCallback.onCustomViewHidden();
			
			HTML5WebView.this.setVisibility(View.VISIBLE);
			
	        //Log.i(LOGTAG, "set it to webVew");
		}
		
		@Override
		public Bitmap getDefaultVideoPoster() {
			//Log.i(LOGTAG, "here in on getDefaultVideoPoster");	
			if (mDefaultVideoPoster == null) {
//				mDefaultVideoPoster = BitmapFactory.decodeResource(getResources(), 0); //, R.drawable.default_video_poster
		    }
			return mDefaultVideoPoster;
		}
		
		@Override
		public View getVideoLoadingProgressView() {
			//Log.i(LOGTAG, "here in on getVideoLoadingPregressView");
			
	        if (mVideoProgressView == null) {
	            LayoutInflater inflater = LayoutInflater.from(mContext);
//	            mVideoProgressView = inflater.inflate(null, null); //R.layout.video_loading_progress
	        }
	        return mVideoProgressView; 
		}
    	
    	 @Override
         public void onReceivedTitle(WebView view, String title) {
            ((Activity) mContext).setTitle(title);
         }

         @Override
         public void onProgressChanged(WebView view, int newProgress) {
        	 ((Activity) mContext).getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress*100);
         }
         
         @Override
         public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
             callback.invoke(origin, true, false);
         }
    }
	
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	Log.i(LOGTAG, "shouldOverrideUrlLoading: "+url);
	    	// don't override URL so that stuff within iframe can work properly
	        // view.loadUrl(url);
	        return false;
	    }
	}
	
	static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
        new FrameLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
}