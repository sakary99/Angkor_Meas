package kh.edu.iic.angkormeas;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AboutActivity extends SherlockFragment {
	
	public AboutActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about, container, false);
		
		
			
		//WebView wv = new WebView(get);
		//WebView wv = new WebView(this);
		WebView wv = (WebView) view.findViewById(R.id.webView_about);
		wv.loadUrl("file:///android_asset/about/about.html");
		WebSettings websetting = wv.getSettings();
		//websetting.setBuiltInZoomControls(true);
//		websetting.setBuiltInZoomControls(true);
//		websetting.setDisplayZoomControls(true);
		wv.getSettings().setBuiltInZoomControls(true);
	
		
		return view;
	}
	
}
