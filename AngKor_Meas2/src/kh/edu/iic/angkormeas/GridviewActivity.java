package kh.edu.iic.angkormeas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.iic.angkormeas.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridviewActivity extends Fragment {
	//public class GridviewActivity extends Activity {

	GridView gridView;
	ArrayList<String> img = new ArrayList<String>();

	public static GridviewActivity newInstance(String index){
		GridviewActivity pageFragment = new GridviewActivity();
		Bundle bundle = new Bundle();
		bundle.putString("title", index); 
		pageFragment.setArguments(bundle);
		return pageFragment;
	}

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Bundle b = getArguments();
		String name = b.getString("name");
		System.out.println("name= " + name);
		
		// get data from json then add to img
			try{
				String result = new RetreiveFeedTask().execute("http://" + Constant.url + "/json/jsonscript_all.php").get();
				JSONArray jArray = new JSONArray(result);
				for(int i=0;i<jArray.length();i++){
					JSONObject json_data = jArray.getJSONObject(i);
					System.out.println(json_data.getString("id"));
					img.add(json_data.getString("id"));
					System.out.println(img.size());

				}
			}
			catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		View view = inflater.inflate(R.layout.activity_gridview, container, false);
		gridView = (GridView) view.findViewById(R.id.gridViewTest);
		//Set Gridview with adapter (check condition if follow phone category )
		//gridView.setAdapter(new GridViewAdapter(getActivity(), img));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				//Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
				onItemsGridClick(img.get(position));
				System.out.println("print: " +img.get(position));
				//onItemsGridClick(position);
			}
		});
		return view;
	}

	public void onItemsGridClick (String id) {
		Intent intent = new Intent(getActivity(), GridViewFull.class);
		Bundle b = new Bundle();
		b.putString("cate", "home");
		b.putString("id", id);
		//b.putInt("position", position);
		intent.putExtras(b);

		startActivity(intent);
	};
	
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
}
