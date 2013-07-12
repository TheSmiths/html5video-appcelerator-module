package tv.wappzapp.html5video;

import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.itri.html5webview.HTML5WebView;

import android.widget.FrameLayout;

public class VideoWebView extends TiUIView {
	
	
	public VideoWebView(TiViewProxy proxy, HTML5WebView webview) {
		
		super(proxy);
		
		FrameLayout view = new FrameLayout(proxy.getActivity());
		view.addView(webview);
		setNativeView(view);
		
	}


}
