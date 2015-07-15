package kh.edu.iic.angkormeas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sj.jsondemo.Application;
import com.sj.jsondemo.FetchDataListener;
import com.sj.jsondemo.FetchDataTask;
import com.sj.jsondemo.MotorolaAppAdapter;

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
import android.widget.ListView;

public class MotorolaFragment extends Fragment implements FetchDataListener {
	//public class GridviewActivity extends Activity {
	String name;
	ListView gridView;
	ArrayList<String> img = new ArrayList<String>();
	private List<Application> items;
	String currenturl;
	String imgcurenturl;

	public static MotorolaFragment newInstance(String index){
		MotorolaFragment pageFragment = new MotorolaFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", index); 
		pageFragment.setArguments(bundle);
        
		return pageFragment;
	}
	//311678
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		name = b.getString("name");
		System.out.println("name on create= " + name);
		
		if (name.equals("motorola")){
			currenturl = Constant.urlmotorola;
			imgcurenturl = Constant.urlimgmoto;
		
		}
		else if (name.equals("LG")){
			currenturl = Constant.urllg;
			imgcurenturl = Constant.urlimglg;
		}
		else if (name.equals("sony")){
			currenturl = Constant.urlsony;
			imgcurenturl = Constant.urlimgsony;
		}
		else if (name.equals("samsung")){
			currenturl = Constant.urlsamsung;
			imgcurenturl = Constant.urlimgsamsung;
		}
		else{
			System.out.println("error ");
		}
		
		String url = currenturl;
		FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
		
	}

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Bundle b = getArguments();
		name = b.getString("name");
		System.out.println("name view= " + name);
		
		// get data from json then add to img
			try{
				String result = new RetreiveFeedTask().execute(currenturl).get();
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
			

		View view = inflater.inflate(R.layout.activity_main, container, false);
		gridView = (ListView) view.findViewById(R.id.listview);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				
				onItemsGridClick(img.get(position));
				System.out.println("print: " +img.get(position));
				
			}
		});
		return view;
	}

	public void onItemsGridClick (String id) {
		Intent intent = new Intent(getActivity(), GridViewFull.class);
		Bundle b = new Bundle();
		b.putString("cate", name);
		System.out.println("cate to detail: " +name);
		b.putString("id", id);
		
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

	@Override
	public void onFetchComplete(List<Application> data) {
		MotorolaAppAdapter adapter = new MotorolaAppAdapter(getActivity(), data, imgcurenturl);
		gridView.setAdapter(adapter);
	}

	@Override
	public void onFetchFailure(String msg) {
		// TODO Auto-generated method stub
		
	}
}
