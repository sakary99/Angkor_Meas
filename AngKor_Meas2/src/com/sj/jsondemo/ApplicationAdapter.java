package com.sj.jsondemo;

import java.text.NumberFormat;
import java.util.List;

import kh.edu.iic.angkormeas.Constant;
import kh.edu.iic.angkormeas.NokiaList;
import kh.edu.iic.angkormeas.R;

//import kh.edu.iic.angkormeas.Constant;





//import com.example.transferdata.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

//import kh.edu.iic.angkormeas.Constant;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
//import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ApplicationAdapter extends ArrayAdapter<Application>{
    private List<Application> items;
    Context mcontext;
    NokiaList itemonclick;
    
    DisplayImageOptions options;
	ImageLoader imageLoader = ImageLoader.getInstance();
    
	
	public ApplicationAdapter(Context context, List<Application> items) {
        super(context, R.layout.app_custom_list, items);
        this.items = items;
        
      //Create Option Image Loader
        options = new DisplayImageOptions.Builder()
		//.showStubImage(R.drawable.ic_launcher)
		.displayer(new FadeInBitmapDisplayer(300))
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
      mcontext = context;
    }
    
    @Override
    public int getCount() {
        return items.size();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        
        if(v == null) {
            LayoutInflater li = LayoutInflater.from(mcontext);
            v = li.inflate(R.layout.app_custom_list, null);            
        }
        
        Application app = items.get(position);
        
        if(app != null) {
            //final ImageView = new ImageView(context);
            final ImageView imageView = (ImageView)v.findViewById(R.id.appIcon);
            TextView titleText = (TextView)v.findViewById(R.id.titleTxt);
           // LinearLayout ratingCntr = (LinearLayout)v.findViewById(R.id.ratingCntr);
            TextView dlText = (TextView)v.findViewById(R.id.dlTxt);
            
           
//            v.setOnClickListener( new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					String id = app.getId();
//					System.out.println("id in adapter= " + id);
//					itemonclick = new NokiaList();
//					itemonclick.onItemsGridClick(id);//) = new NokiaList().onItemsGridClick(id);
//				}
//			});
            
//            if(icon != null) {
//                Resources res = getContext().getResources();
//                //http://"+ Constant.url + "/json/phone_pic/apple/" + id + ".jpg
//                //String sIcon = "com.sj.jsondemo:drawable/" + app.getIcon();
//                
//                String sIcon = "http://192.168.178.124/json/phone_pic/apple/" + app.getId() + ".jpg" ;
//                icon.setImageDrawable(res.getDrawable(res.getIdentifier(sIcon, null, null)));
//            }
//            options = new DisplayImageOptions.Builder()
//    		//.showStubImage(R.drawable.ic_launcher)
//    		.displayer(new FadeInBitmapDisplayer(1300))
//    		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//    		.bitmapConfig(Bitmap.Config.RGB_565)
//    		.build();
//
//    		ImageSize targetSize = new ImageSize(1500, 1500);
//
//    		//final ImageView img = (ImageView) findViewById(R.id.appIcon);
//    		//final ImageView img = (ImageView) fin
//    		imageLoader.loadImage("http://192.168.178.124/json/phone_pic/apple/" + app.getId() + ".jpg", targetSize, options, new SimpleImageLoadingListener() {
//    		//imageLoader.loadImage("http://"+ Constant.url + "/json/phone_pic/apple/1.jpg", targetSize, options, new SimpleImageLoadingListener() {
//    			@Override
//    			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//    				// Do whatever you want with Bitmap
//    				icon.setImageBitmap(loadedImage);
//    			}
//    		});
//            if(icon != null) {
//                Resources res = getContext().getResources();
//               // String sIcon = "com.sj.jsondemo:drawable/" + app.getIcon();
//                //String sIcon = "http://192.168.178.124/json/phone_pic/apple/" + app.getId() + ".jpg" ;
//               String sIcon = "com.sj.jsondemo:drawable/" + app.getId();
//                icon.setImageDrawable(res.getDrawable(res.getIdentifier(sIcon, null, null)));
//            }

            
           
            ImageSize targetSize = new ImageSize(350, 350); // result Bitmap will be fit to this size

    		//for(id = 0; id < 10;id++){
    		//System.out.print("position: "+img.get(position));
    		imageLoader.loadImage("http://" + Constant.url + "/json/phone_pic/apple/" +  app.getId() + ".jpg", targetSize, options, new SimpleImageLoadingListener() {
    			@Override
    			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {    	    	
    				//progressBar.setVisibility(View.GONE);
    				imageView.setVisibility(View.VISIBLE);
    				imageView.setImageBitmap(loadedImage);
    				//textView.setImageBitmap(loadedImage);
    			}
    			});
            
            if(titleText != null) titleText.setText(app.getTitle());
            
            if(dlText != null) {
                //NumberFormat nf = NumberFormat.getNumberInstance();
                dlText.setText(app.getPrice()+" $");            
            }
            
//            if(ratingCntr != null && ratingCntr.getChildCount() == 0) {        
//                /*
//                 * max rating: 5
//                 */
//                for(int i=1; i<=5; i++) {
//                    ImageView iv = new ImageView(getContext());
//                    
//                    if(i <= app.getRating()) {
//                        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_checked));
//                    }
//                    else {                
//                        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_unchecked));
//                    }
//                    
//                    ratingCntr.addView(iv);
//                }
//            }
        }
        
        return v;
    }
    

	
}
