package kh.edu.iic.angkormeas;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
//import com.actionbarsherlock.app.SherlocActivity;
/**
 * This activity is an example of a responsive Android UI.
 * On phones, the SlidingMenu will be enabled only in portrait mode.
 * In landscape mode, it will present itself as a dual pane layout.
 * On tablets, it will will do the same general thing. In portrait
 * mode, it will enable the SlidingMenu, and in landscape mode, it
 * will be a dual pane layout.
 * 
 * @author panha
 *
 */
public class ResponsiveUIActivity extends SlidingFragmentActivity {

	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.responsive_ui);

		setContentView(R.layout.responsive_content_frame);

		// check if the content frame contains the menu frame
		if (findViewById(R.id.menu_frame) == null) {
			setBehindContentView(R.layout.menu_frame);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			// show home as up so we can toggle
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			// add a dummy view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}

		// set the Above View Fragment
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)

			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, mContent)
			.commit();

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(final Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}	
}
