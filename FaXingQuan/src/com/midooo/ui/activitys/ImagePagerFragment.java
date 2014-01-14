/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.midooo.ui.activitys;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.ui.activitys.Constants.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerFragment extends Fragment {

	private static final String STATE_POSITION = "STATE_POSITION";

	private View layoutView;
	
	DisplayImageOptions options;

	private ViewPager pager;

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	


//R.string.viewpager

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layoutView = inflater.inflate(R.layout.fxq_image_pager, null);
		int pagerPosition;
		String[] imageUrls;
		/*
		if (getActivity() instanceof FaXingQuanMainActivity) {
			FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
			Bundle bundle = fca.getIntent().getExtras();
			imageUrls = bundle.getStringArray(Extra.IMAGES);
			pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		} else {*/
					//Log.w("---doop---", "getActivity() instanceof FaXingQuanMainActivity fail......");
					pagerPosition = 0;
					imageUrls = Constants.IMAGES;
					options = Constants.options;
		//}
		
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}
		
		pager = (ViewPager) layoutView.findViewById(R.id.banner_pager);
		pager.setAdapter(new ImagePagerAdapter(imageUrls, inflater));
		pager.setCurrentItem(pagerPosition);

		return layoutView;




	}

/*
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	  super.onViewCreated(view, savedInstanceState);
	  version = (TextView) view.findViewById(R.id.version);
	  loginfo = (TextView) view.findViewById(R.id.loginfo);
	}
	
*/	
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				/*
				Log.w("---doop---", "---"+arg0+"---"+arg1+"---"+arg2+"---");
				if(arg0==9 && arg1==0.0) {
					FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
					fca.getSlidingMenu().showSecondaryMenu();
					//(FaXingQuanMainActivity) getActivity().getSl//showSecondaryMenu();
				}
				if(arg0==0 && arg1==0.0) {
					FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
					fca.getSlidingMenu().showMenu(true);
				}*/
			}
				
			@Override
			public void onPageSelected(int position) {
				FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
				switch (position) {
				case 0:
					if (getActivity() instanceof FaXingQuanMainActivity) {
						fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					}
					break;
				default:
					if (getActivity() instanceof FaXingQuanMainActivity) {
						fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					}
					break;
				}
			}

		});
		
		if (getActivity() instanceof FaXingQuanMainActivity) {
			FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
			fca.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}

	}


	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends PagerAdapter {

		private String[] images;
		private LayoutInflater inflater;

		ImagePagerAdapter(String[] images, LayoutInflater inflater) {
			this.images = images;
			this.inflater = inflater;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return 5;//images.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(R.layout.fxq_item_pager_image, view, false);
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

			imageLoader.displayImage(images[position], imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					//Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
				}
			});

			((ViewPager) view).addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
}