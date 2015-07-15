package kh.edu.iic.angkormeas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.fraction;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware.GetSocketData;
import com.koushikdutta.ion.Ion;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import kh.edu.iic.angkormeas.R;

public class GridViewFull extends SherlockActivity {

	String currenturl;
	String imgcurenturl;
	String detailUrl;
	
	// TextView to show the result of MySQL query 
	TextView tvprice;
	TextView tvname;
	TextView tvdisplay;
	TextView tvdata;
	TextView tvbluetooth;
	TextView tvcamera;
	TextView tvvideo;
	TextView tvOS;
	TextView tvchipset;
	TextView tvcolor;
	TextView tvfeature;
	TextView tvbattery;

	// to store the result of MySQL query after decoding JSON
	String returnString;
	String tname;
	String tprice;
	String tdisplay;
	String tdata;
	String tbluetooth;
	String tcamera;
	String tvideo;
	String tOS;
	String tchipset;
	String tcolor;
	String tfeature;
	String tbattery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_detail);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);


		tvname = (TextView) findViewById(R.id.textViewname);
		tvprice = (TextView) findViewById(R.id.textViewprice);
		tvdisplay = (TextView) findViewById(R.id.textViewdisplay);
		tvdata = (TextView) findViewById(R.id.textViewdata);
		tvbluetooth = (TextView) findViewById(R.id.textViewbluetooth);
		tvcamera = (TextView) findViewById(R.id.textViewcamera);
		tvvideo = (TextView) findViewById(R.id.textViewvideo);
		tvOS = (TextView) findViewById(R.id.textViewOS);
		tvchipset = (TextView) findViewById(R.id.textViewchipset);
		tvfeature = (TextView) findViewById(R.id.textViewfeature);
		tvbattery = (TextView) findViewById(R.id.textViewbattery);

		Bundle b = getIntent().getExtras();
		String id = b.getString("id");
		String cate = b.getString("cate");
		
		System.out.println("Id in detail: " +id);
		System.out.println("cate int detail: " +cate);
		
		if (cate.equals("motorola")){
			currenturl = Constant.urlmotorola;
			imgcurenturl = Constant.urlimgmoto;
			detailUrl = Constant.urlDmoto;
		
		}
		else if (cate.equals("home")){
			currenturl = Constant.urlhome;
			imgcurenturl = Constant.urlimghome;
			detailUrl = Constant.urlDhome;
		}
		else if (cate.equals("LG")){
			currenturl = Constant.urllg;
			imgcurenturl = Constant.urlimglg;
			detailUrl = Constant.urlDlg;
		}
		else if (cate.equals("sony")){
			currenturl = Constant.urlsony;
			imgcurenturl = Constant.urlimgsony;
			detailUrl = Constant.urlDsony;
		}
		else if (cate.equals("samsung")){
			currenturl = Constant.urlsamsung;
			imgcurenturl = Constant.urlimgsamsung;
			detailUrl = Constant.urlDsamsung;
		}
		else{
			System.out.println("error ");
		}
		


		final ImageView img = (ImageView) findViewById(R.id.imageViewFull);

		
		//create imageview by ion
		Ion.with(getApplicationContext(), imgcurenturl + id + ".jpg")
		.withBitmap()
		.intoImageView(img);

		// call executeHttpPost method passing necessary parameters 
		try {
			// store the result returned by PHP script that runs MySQL query
			//String result = getJSON("http://192.168.1.110/json/jsonscript_app.php");
			System.out.println("Url :" +detailUrl);
			String result = new RetreiveFeedTask().execute( detailUrl+"?id=" + id).get();
			

			//parse json data
			try{
				returnString = "";
				tname = "";

				JSONArray jArray = new JSONArray(result);
				for(int i=0;i<jArray.length();i++){
					JSONObject json_data = jArray.getJSONObject(i);
					tname = json_data.getString("name");
					tprice = json_data.getString("price");
					tdisplay = json_data.getString("display");
					tdata = json_data.getString("data");
					tbluetooth = json_data.getString("blutooth");
					tcamera = json_data.getString("camera");
					tvideo = json_data.getString("video");
					tOS = json_data.getString("OS");
					tchipset = json_data.getString("chipset");
					tfeature = json_data.getString("feature");
					tbattery = json_data.getString("battery");

				}
			}
			catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}
			try{
				tvname.setText(tname);
				tvprice.setText(tprice +" $");
				tvdisplay.setText(tdisplay);
				tvdata.setText(tdata);
				tvbluetooth.setText(tbluetooth);
				tvcamera.setText(tcamera);
				tvvideo.setText(tvideo);
				tvOS.setText(tOS);
				tvchipset.setText(tchipset);
				tvcolor.setText(tcolor);
				tvfeature.setText(tfeature);
				tvbattery.setText(tbattery);
			}
			catch(Exception e){
				Log.e("log_tag","Error in Display!" + e.toString());;          
			}   
		}
		catch (Exception e) {
			Log.e("log_tag","Error in http connection!!" + e.toString());     
		}
	}

	public String getJSON(String url) {
		String result = "";
		try {
			// Create a URL for the desired page
			URL _url = new URL(url);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(_url.openStream()));
			String str;
			StringBuffer sb = new StringBuffer("");
			while ((str = in.readLine()) != null) {
				// str is one line of text; readLine() strips the newline character(s)
				sb.append(str);
			}
			in.close();
			result = sb.toString();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return result;
	}
	
	class RetreiveFeedTask extends AsyncTask<String, Void, String> {

	    protected String doInBackground(String... urls) {
	    	String result = "";
			try {
				// Create a URL for the desired page
				URL _url = new URL(urls[0]);

				// Read all the text returned by the server
				BufferedReader in = new BufferedReader(new InputStreamReader(_url.openStream()));
				String str;
				StringBuffer sb = new StringBuffer("");
				while ((str = in.readLine()) != null) {
					// str is one line of text; readLine() strips the newline character(s)
					sb.append(str);
				}
				in.close();
				result = sb.toString();
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
			return result;
	    }

	    protected void onPostExecute(String feed) {
	        
	    }
	}
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	
	      finish();
	      break;
	      // Something else
//	    case R.id.action_settings:
//	      intent = new Intent(this, ThirdActivity.class);
//	      startActivity(intent);
//	    default:
//	      break;
	    }
	    return super.onOptionsItemSelected(item);
	  }

	
	
}
