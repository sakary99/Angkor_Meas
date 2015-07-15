package kh.edu.iic.angkormeas;






import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ColorMenuFragment extends ListFragment {

	Integer iconid[] = {
			R.drawable.p1,
			R.drawable.pi2,
			R.drawable.p3,
			
			R.drawable.pi5,
			R.drawable.pi6,
			R.drawable.pi7,
			R.drawable.pi8,
			R.drawable.pi9,
			R.drawable.pi10,
			R.drawable.p11,
			R.drawable.p12,
			
			
			
			
	};
	Intent intent;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.color_names);
		setListAdapter(new MyAdapter(colors));
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		
		//NokiaListfragment newlistcont = null;
		NokiaList newlistcont = null;
		Fragment newContent = null;
		Bundle b = new Bundle();
		try{
		
		switch (position) {
		
		case 0:
			newContent = new HomeFragment();
			b.putString("name", "home");
			newContent.setArguments(b);
			break;
		case 1:
			newContent = new AboutActivity();
			b.putString("name", "about");
			newContent.setArguments(b);
			break;
		case 2:
			newContent = new MotorolaFragment();
			//b.putString("name", "apple");
			b.putString("name", "home");
			newContent.setArguments(b);
			break;
	
		case 3:
//			newContent = new GridviewActivity();
//			//newContent = new GridviewActivity();
//			b.putString("name", "samsung2");
//			newContent.setArguments(b);
			newContent = new MotorolaFragment();
			b.putString("name", "samsung");
			newContent.setArguments(b);
			break;
		
		case 4:
			
//			newContent = new GridviewActivity();
//			b.putString("name", "nokia");
//			newContent.setArguments(b);
//			break;
			newContent = new MotorolaFragment();
			b.putString("name", "motorola");
			newContent.setArguments(b);
			break;
		case 5:			
			newContent = new MotorolaFragment();
			b.putString("name", "LG");
			newContent.setArguments(b);
			break;
		case 6:
			newContent = new MotorolaFragment();
			b.putString("name", "sony");
			newContent.setArguments(b);
			break;
		case 7:
			newContent = new MotorolaFragment();
			b.putString("name", "HTC");
			newContent.setArguments(b);
			break;
		case 8:
			newContent = new MotorolaFragment();
			b.putString("name", "motorola");
			newContent.setArguments(b);
			break;
		case 9:
//			newContent = new GridviewActivity();
//			b.putString("name", "secondhand");
//			newContent.setArguments(b);
//			break;
			newContent = new MotorolaFragment();
			b.putString("name", "motorola");
			newContent.setArguments(b);
			break;
		case 10:
//			newContent = new GridviewActivity();
//			b.putString("name", "access");
//			newContent.setArguments(b);
//			break;
			newContent = new MotorolaFragment();
			b.putString("name", "motorola");
			newContent.setArguments(b);
			break;
		case 11:
//			newContent = new GridviewActivity();
//			b.putString("name", "access");
//			newContent.setArguments(b);
//			break;
			newContent = new MotorolaFragment();
			b.putString("name", "motorola");
			newContent.setArguments(b);
			break;
		}
		
		if (newContent != null)
			switchFragment(newContent);
		
	         
	      }catch(Exception e){
	         System.out.println("Exception thrown  :" + e);
	      }
		
		
		
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof FragmentChangeActivity) {
			FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
			fca.switchContent(fragment);
		} else if (getActivity() instanceof ResponsiveUIActivity) {
			ResponsiveUIActivity ra = (ResponsiveUIActivity) getActivity();
			ra.switchContent(fragment);
		}
	}
	
	public class MyAdapter  extends BaseAdapter
	{
		private String[] data;
		private LayoutInflater inflater=null;


		public MyAdapter(String[] data)
		{
			this.data=data;        
			inflater =(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}



		public int getCount() 
		{
			return data.length;
		}



		public Object getItem(int position) 
		{
			return position;
		}



		public long getItemId(int position) 
		{
			return position;
		}



		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View vi=convertView;
			if(convertView==null)

				vi = inflater.inflate(R.layout.lrow, null);
			
			ImageView icon = (ImageView)vi.findViewById(R.id.listIcon);
			icon.getLayoutParams().height = 100;
			icon.setImageResource(iconid[position]);
			
			
			Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "Franken.ttf");
			TextView name = (TextView)vi.findViewById(R.id.row_title); // name
			name.setTypeface(myTypeface);
			name.setTextSize(24);
			name.setText(data[position]);
			
			return vi;
		}

	}


}
