package com.midooo.ui.aimeise;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.ui.activitys.Constants;
import com.midooo.ui.activitys.FaXingQuanMainActivity;
import com.midooo.ui.activitys.R;
import com.midooo.ui.activitys.ImageGridFragment.ImageAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 首页第二个条目：爱美色
 * 
 * @author xushengxing
 * 
 */
public class AimeiseActivity extends BaseActivity 
		 {

	public AimeiseActivity(int titleRes) {
		super(titleRes);
		// TODO Auto-generated constructor stub
	}
	
	private GridView mGridView;
	private PullToRefreshGridView gv_aimeise_home;
	private String title;
	String[] imageUrls;
	private View layoutView;
	DisplayImageOptions options;
	private ImageAdapter mAdapter;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	protected AbsListView listView;
	//private ColorInfo colorInfo;
	

  
	protected void loadViewLayout() {
		title=this.getString(R.string.str_title_main);
		this.txt_title.setText(title);
		this.ibtn_header_right.setVisibility(View.GONE);
		setContentView(R.layout.ac_image_grid);

		imageUrls = Constants.IMAGES;
		options = Constants.options;
		gv_aimeise_home = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
		mGridView = gv_aimeise_home.getRefreshableView();
		
		gv_aimeise_home.setOnRefreshListener(new OnRefreshListener2<GridView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(AimeiseActivity.this, "Pull Down!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(AimeiseActivity.this, "Pull Up!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

		});
		
		

		mAdapter = new ImageAdapter(inflater);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//startImagePagerActivity(position);
				Log.w("---doop---", "startImagePagerActivity(position)");
			}
		});
		mGridView.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
	}







	// Set a listener to be invoked when the list should be refreshed.



	
	
	/*
	((GridView) listView).setAdapter(new ImageAdapter(inflater));
	listView.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//startImagePagerActivity(position);
			Log.w("---doop---", "startImagePagerActivity(position)");
		}
	});
	*/
	//return layoutView;



	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return imageUrls;
		}

		@Override
		protected void onPostExecute(String[] result) {
			//mListItems.addFirst("Added after refresh...");
			//mListItems.addAll(Arrays.asList(result));
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			gv_aimeise_home.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	
	public class ImageAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		
		ImageAdapter(LayoutInflater inflater) {
			this.inflater = inflater;
		}
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = (ImageView) inflater.inflate(R.drawable.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;
		}
	}
	


}
