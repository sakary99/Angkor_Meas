package kh.edu.iic.angkormeas;

import java.util.List;

import kh.edu.iic.angkormeas.R;
import kh.edu.iic.angkormeas.R.layout;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.sj.jsondemo.Application;
import com.sj.jsondemo.ApplicationAdapter;
import com.sj.jsondemo.FetchDataListener;
import com.sj.jsondemo.FetchDataTask;

//import kh.edu.iic.angkormeas.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NokiaList extends ListActivity implements FetchDataListener, OnClickListener{
	//ListActivity
    private ProgressDialog dialog;
    ListView lv;
    private List<Application> items;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main); 
       // listv = (ListView)findViewById(R.id.);
         lv = getListView();
        initImageLoader(getApplicationContext());
        initView();   
    }
    
     
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//    		Bundle savedInstanceState) {
//    	// TODO Auto-generated method stub
//    	
//    	View view = inflater.inflate(R.layout.activity_main, container, false);
//    	//initImageLoader(getActivity().getApplicationContext());
//    	initImageLoader(getActivity());
//    	initView();
//    	return view;
//    }

    private void initView() {
        // show progress dialog
       dialog = ProgressDialog.show(this, "", "Loading...");
       // dialog = ProgressDialog.show(getActivity(), title, message);
        
    
        
       // String url = "http://192.168.178.124/test/apps2.php";
        String url = "http://" + Constant.url + "/json/jsonscript_all.php";
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
    }
   
    @Override
    public void onFetchComplete(List<Application> data) {
    	
    	final Application app = new Application();
    	
    	System.out.println("app id= " + app.getId());
        // dismiss the progress dialog
    	
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        items = data;
        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
       lv.setAdapter(adapter);
       lv.setTextFilterEnabled(true);
       lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //lv.setListAdapter(adapter); 
        //create action on clickitem
        lv.setOnItemClickListener(new OnItemClickListener() {

        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				Application app = items.get(position);
				// TODO Auto-generated method stub
				//String id = app.getId();
				System.out.println("test id= " + app.getId());
				onItemsGridClick(app.getId());
			}
        	
        	
		});
   
    }

    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();        
    }
    

    public void onItemsGridClick (String id) {
    	System.out.println("id in click= " + id);
		Intent intent = new Intent(this, GridViewFull.class);
		Bundle b = new Bundle();
		b.putString("id", id );
		
		intent.putExtras(b);

		startActivity(intent);
	};
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.writeDebugLogs() // Remove for release app
                        .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


	
}
