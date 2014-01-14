package com.midooo.ui.activitys;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class RankingsFragment extends Fragment {

	ViewPager vp;

	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutView = inflater.inflate(R.layout.fxq_rankings_pager, null);
		vp = (ViewPager) layoutView.findViewById(R.id.rankings_pager);
		vp.setId("VP".hashCode());
		vp.setAdapter(new RankingsPagerAdapter(((FaXingQuanMainActivity) getActivity()).getSupportFragmentManager()));
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { 
				/*
				Log.w("---doop---", "---"+arg0+"---"+arg1+"---"+arg2+"---");
				FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
				if(arg0==1 && arg1==0.0 && fca.getSlidingMenu().getTouchModeAbove()==SlidingMenu.TOUCHMODE_MARGIN) {
					fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					//fca.getSlidingMenu().showSecondaryMenu();
					//(FaXingQuanMainActivity) getActivity().getSl//showSecondaryMenu();
				}
				if(arg0==0 && arg1==0.0 && fca.getSlidingMenu().getTouchModeAbove()==SlidingMenu.TOUCHMODE_MARGIN) {
					//fca.getSlidingMenu().showMenu(true);
					fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}
				*/
			}

			@Override
			public void onPageSelected(int position) {
				FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();

				switch (position) {
				case 0:
					fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					break;
				}
			}

		});	
		
		
		/*
		vp.setOnTouchListener(new OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
			      int action = event.getAction();
			      switch (action) {
			      case MotionEvent.ACTION_DOWN:
			        onClickX = event.getX();
			        break;
			      case MotionEvent.ACTION_UP:
			        if((count - 1) == selectP) { // 为最后一个时
			      if(onClickX > event.getX()) { // 向左滑动，右边已无view直接打开右侧滑菜单。
			        ((FaXingQuanMainActivity) getActivity()).getSlidingMenu().showSecondaryMenu();
			          }
			        } else if(0 == selectP) {
			          if(onClickX < event.getX()) { // 向右滑动，左边已无view直接打开左侧滑菜单。
			        ((FaXingQuanMainActivity) getActivity()).getSlidingMenu().showMenu();
			          }
			        }
			        onClickX = 0; // 还原坐标
			        break;
			      default:
			        break;
			    }
			      return false;
			    }
			});
		*/
		
		
		
		
		

		int pagerPosition;
		if (savedInstanceState != null)
			pagerPosition = savedInstanceState.getInt("mRankingsRes");
		else
			pagerPosition = 0;
		vp.setCurrentItem(pagerPosition);
		return layoutView;
	}
	
	
	
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mRankingsRes", vp.getCurrentItem());
	}

	public class RankingsPagerAdapter extends FragmentPagerAdapter {
		
		private ArrayList<Fragment> mFragments;
		
		public RankingsPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			mFragments.add(new ImageGridFragment());
			mFragments.add(new ImageListFragment());
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}


}




