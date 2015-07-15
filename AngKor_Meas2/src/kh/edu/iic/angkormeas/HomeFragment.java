package kh.edu.iic.angkormeas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kh.edu.iic.angkormeas.GridviewActivity.RetreiveFeedTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.sj.jsondemo.Application;
import com.sj.jsondemo.FetchDataListener;
import com.sj.jsondemo.FetchDataTask;
import com.sj.jsondemo.HomeGridAdapter;
import com.sj.jsondemo.MotorolaAppAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class HomeFragment extends Fragment implements FetchDataListener {

	static final String KEY_ITEM = "item"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_CATEGORY = "category";
	static final String KEY_TITLE = "title";
	static final String KEY_IMAGE = "image";
	static final String KEY_THUMBNIAL = "thumbnail";
	private boolean pagerMoved = false;
	
	private static final long ANIM_VIEWPAGER_DELAY = 3000;
	private Handler h = new Handler();
	private DisplayImageOptions options;
	private ViewPager slide;
	ImageLoader imageLoader = ImageLoader.getInstance();
	ArrayList<String> img = new ArrayList<String>();
	GridView gridView;
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.homefragment, container, false);
		slide = (ViewPager) view.findViewById(R.id.slideshow);
		//Set Gridview with adapter (check condition if follow phone category )
		
		options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.displayer(new FadeInBitmapDisplayer(300))
		.build();
		
		// Slide show
		XMLParser parser = new XMLParser();
		String xml = parser.getXml(getActivity(), "slide.xml"); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);

		List<String> slide_id = new ArrayList<String>();
		List<String> slide_title = new ArrayList<String>();
		List<String> slide_image = new ArrayList<String>();

		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			slide_id.add(parser.getValue(e, KEY_ID));
			slide_title.add(parser.getValue(e, KEY_TITLE));
			slide_image.add(parser.getValue(e, KEY_IMAGE));
		}

		SlideAdaptor mAdaptor = new SlideAdaptor(slide_id, slide_title, slide_image);

		slide.setAdapter(mAdaptor);

		slide.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//pagerMoved = true;
				return false;
			}
		});
		CirclePageIndicator mIndicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
        mIndicator.setViewPager(slide);
        
        try{
			String result = new RetreiveFeedTask().execute("http://" + Constant.url + "/json/jsonscript_allsamsung.php").get();
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
        
        String url = "http://" + Constant.url + "/json/jsonscript_allsamsung.php";
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
        
        gridView = (GridView) view.findViewById(R.id.listcategory);
		//Set Gridview with adapter (check condition if follow phone category )
		//listView.setAdapter(new GridViewAdapter(getActivity(), img));
//        Application app = new Application();
//        FetchDataTask apps = new FetchDataTask(List<app> data) ;
       // List<Application> data = FetchDataTask.
        
//        HomeGridAdapter adapter = new HomeGridAdapter(getActivity(), data);
//		gridView.setAdapter(adapter);
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
		
		b.putString("cate", "sumsung");
		b.putString("id", id);
		//b.putInt("position", position);
		intent.putExtras(b);

		startActivity(intent);
	};
	
	private Runnable animateViewPager = new Runnable() {
		public void run() {
			if (!pagerMoved) {
				if (slide.getCurrentItem() == (slide.getAdapter().getCount() - 1)) {
					slide.setCurrentItem(0, true);
				} else {
					slide.setCurrentItem(slide.getCurrentItem()+1, true);
				}
				h.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
			}
		}
	};
	
	@Override
	public void onFetchComplete(List<Application> data) {
		HomeGridAdapter adapter = new HomeGridAdapter(getActivity(), data, "http://"+ Constant.url + "/json/phone_pic/samsung/");
		gridView.setAdapter(adapter);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (h != null) {
			h.removeCallbacks(animateViewPager);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		h.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
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
	
	public class SlideAdaptor extends PagerAdapter {

		//protected String[] CONTENT = null;
		private LayoutInflater mInflater;
		List<String> slide_id = new ArrayList<String>();
		List<String> slide_title = new ArrayList<String>();
		List<String> slide_image = new ArrayList<String>();

		public SlideAdaptor(List<String> slide_id,
				List<String> slide_title, List<String> slide_image) {
			mInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.slide_id = slide_id;
			this.slide_title = slide_title;
			this.slide_image = slide_image;
		}

		@Override
		public int getCount() {
			return slide_id.size();
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}
		
		@Override
		public Object instantiateItem(View view, int position) {
			View layout = mInflater.inflate(R.layout.slideframe, null);
			
			final int current_positon = position;
			
			final TextView title = (TextView) layout.findViewById(R.id.slidetitle);
			title.setText(slide_title.get(current_positon));
			title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
			title.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					OnItemClick(current_positon);
				}
			});
			
			final ProgressBar spinner = (ProgressBar) layout.findViewById(R.id.progressspinner);
			
			final ImageView mImageView = (ImageView) layout.findViewById(R.id.imageslide);
			mImageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					OnItemClick(current_positon);
				}
			});
			
	        imageLoader.displayImage(slide_image.get(current_positon), mImageView, options, new ImageLoadingListener() {

				@Override
				public void onLoadingStarted(String imageUri, View view) {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view,
						FailReason failReason) {
					String message = null;
					
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
					spinner.setVisibility(View.GONE);
					mImageView.setImageResource(android.R.drawable.ic_delete);
					
				}

				@Override
				public void onLoadingComplete(String imageUri, View view,
						Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingCancelled(String imageUri, View view) {
					spinner.setVisibility(View.GONE);
				}
	        });

	        ((ViewPager) view).addView(layout, 0);
	        return layout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}
		
		private void OnItemClick(int position) {
			/*Bundle b = new Bundle();
			b.putString("id", slide_id.get(position));
			b.putString("title", slide_title.get(position));
			Intent intent = new Intent(getActivity(), ContentActivity.class);
			intent.putExtras(b);
			startActivity(intent);*/
		}
		
	}

	@Override
	public void onFetchFailure(String msg) {
		// TODO Auto-generated method stub
		
	}
}