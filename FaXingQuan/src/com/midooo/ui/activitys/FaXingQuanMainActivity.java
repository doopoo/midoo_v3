package com.midooo.ui.activitys;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.lib.BaseFramework.SampleListFragment;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.midooo.ui.activitys.R;




public class FaXingQuanMainActivity extends BaseActivity implements ActionBar.TabListener{ //
	
	private Fragment mContent;
	
	
	private CanvasTransformer mTransformer;
	
	private static Interpolator interp = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}		
	};

	public FaXingQuanMainActivity() {
		super(R.string.changing_fragments);
		// see the class CustomAnimation for how to attach 
		// the CanvasTransformer to the SlidingMenu
		mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.translate(0, canvas.getHeight()*(1-interp.getInterpolation(percentOpen)));
			}			
		};
	
	}
	
	
	

	
	
	
	
	
	
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new ImagePagerFragment(); //new ColorFragment(R.color.red);	
		
	
		/*
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new SampleListFragment())
		.commit();
		*/
		
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.replace(R.id.rankings_grid, new RankingsFragment())
		.commit();
		
		// set the Behind View
		/*
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, IMAGES);
		startActivity(intent);
		*/
		

		 //* 侧滑菜单仅支持左滑动

		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new FaXingQuanMenuFragment())
		.commit();	
		
		SlidingMenu sm = getSlidingMenu();
		setSlidingActionBarEnabled(true);
		sm.setBehindScrollScale(0.0f);
		sm.setBehindCanvasTransformer(mTransformer);
		
		
		
		/*
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		//侧滑菜单左右都能滑动
		//getSlidingMenu().setMenu(R.layout.menu_frame);
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame2);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadow);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame2, new FaXingQuanMenuFragment())
		.commit();
    	*/
		
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		

	    getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
	    ActionBar.Tab tab1 = getSupportActionBar().newTab();
	    tab1.setText(R.string.changing_fragments);
	    //tab1.setCustomView(R.layout.custom_tab);
        //TextView tab_title = (TextView) tab1.getCustomView().findViewById(R.id.tab_title);
        //tab_title.setText(R.string.changing_fragments);
	    tab1.setTabListener(this);
	    getSupportActionBar().addTab(tab1);
  
    
	    ActionBar.Tab tab2 = getSupportActionBar().newTab();
	    tab2.setText("全部风格^");
	    tab2.setTabListener(this);
	    getSupportActionBar().addTab(tab2);
	    
  	    
	    ActionBar.Tab tab3 = getSupportActionBar().newTab();
	    tab3.setText("上传");
	    tab3.setTabListener(this);
	    getSupportActionBar().addTab(tab3);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	
	
	
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}






 





	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}















	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}















	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}




}
