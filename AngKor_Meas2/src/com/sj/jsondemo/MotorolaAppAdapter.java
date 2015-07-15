package com.sj.jsondemo;

import java.text.NumberFormat;
import java.util.List;

import kh.edu.iic.angkormeas.Constant;
import kh.edu.iic.angkormeas.NokiaList;
import kh.edu.iic.angkormeas.R;


import com.koushikdutta.ion.Ion;
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

public class MotorolaAppAdapter extends ArrayAdapter<Application>{
    private List<Application> items;
    Context mcontext;
    String imgurl;
    
     
	
	public MotorolaAppAdapter(Context context, List<Application> items, String imgurl) {
        super(context, R.layout.app_custom_list, items);
        this.items = items;
        this.imgurl = imgurl;
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
           
            final ImageView imageView = (ImageView)v.findViewById(R.id.appIcon);
//            LayoutParams params = (LayoutParams) imageView.getLayoutParams();
//            params.width = 120;
//            // existing height is ok as is, no need to edit it
//            imageView.setLayoutParams(params);
            imageView.getLayoutParams().height = 100;
            
            
            TextView titleText = (TextView)v.findViewById(R.id.titleTxt);
           // LinearLayout ratingCntr = (LinearLayout)v.findViewById(R.id.ratingCntr);
            TextView dlText = (TextView)v.findViewById(R.id.dlTxt);
            
           
    		Ion.with(mcontext, imgurl + app.getId() + ".jpg")
    		.withBitmap()
    		.intoImageView(imageView);
            
            if(titleText != null) titleText.setText(app.getTitle());
            
            if(dlText != null) {
                
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
