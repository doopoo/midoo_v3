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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.midooo.lib.BaseFramework.midooRequest;
import com.midooo.userCenter.autoLogin;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageGridFragment extends Fragment {

	//List <JSONObject> imageUrls;
	JSONArray imageUrls;
	private View layoutView;
	DisplayImageOptions options;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	protected AbsListView listView;

	private Handler mHandler;

	private LinkedList<String> mListItems;
	private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	private ImageAdapter mAdapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layoutView = inflater.inflate(R.layout.ac_image_grid, null);

		//imageUrls = Constants.IMAGES;
		options = Constants.options;

		mPullRefreshGridView = (PullToRefreshGridView) layoutView.findViewById(R.id.pull_refresh_grid);
		
		mGridView = mPullRefreshGridView.getRefreshableView();

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {
			FaXingQuanMainActivity fca = (FaXingQuanMainActivity) getActivity();
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(fca, "Pull Down!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(fca, "Pull Up!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

		});

		//Looper looper = Looper.myLooper();
		//messageHandler = new MessageHandler(looper);

/*
		mAdapter = new ImageAdapter();
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//startImagePagerActivity(position);
				Log.w("---doop---", "startImagePagerActivity(position)");
			}
		});
*/		
		
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
		
		return layoutView;
	}
/*
	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}
*/
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
		Log.w("---doop---faXingQuan_getList---", "---before faXingQuan_getList---");
		faXingQuan_getList();
		Log.w("---doop---faXingQuan_getList---", "---after faXingQuan_getList---");
    } 

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			//mListItems.addFirst("Added after refresh...");
			//mListItems.addAll(Arrays.asList(result));
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshGridView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	
	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length();
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
				imageView = (ImageView) ((FaXingQuanMainActivity) getActivity()).getLayoutInflater().inflate(R.drawable.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			
			try {
				imageLoader.displayImage(imageUrls.getJSONObject(position).getString("stylephoto"), imageView, options);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return imageView;
		}
	}
	
	
	
	
	private void faXingQuan_getList(){
		
		//final ProgressDialog dialog = ProgressDialog.show((FaXingQuanMainActivity) getActivity(), "", "正在努力加载,请稍候..");
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				//dialog.dismiss();
				if (msg.what == 1) {
					
					
					mAdapter = new ImageAdapter();
					mGridView.setAdapter(mAdapter);
					mGridView.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							//startImagePagerActivity(position);
							Log.w("---doop---", "startImagePagerActivity(position)");
						}
					});
					
					
				} else if (msg.what == 0) {
					Toast.makeText((FaXingQuanMainActivity) getActivity(), "Pull Down!", Toast.LENGTH_SHORT).show();

					
					

				} else if (msg.what == -1 && msg.obj != null) {

					Toast.makeText((FaXingQuanMainActivity) getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		};		
		faXingQuan_getList(null, null, null, null, null, null, null);
	}
 

	private void faXingQuan_getList(String style, String length, String curl, String color, String sex, String curpage, String pagesize) {
		RequestParams params = new RequestParams();

		Log.w("---doop---", "start---------faXingQuan_getList-----111----");
		params.put("style", (style==null)?"0":style);
		params.put("length", (style==null)?"0":style);
		params.put("curl", (style==null)?"0":style);
		params.put("color", (style==null)?"0":style);
		params.put("sex", (style==null)?"":style);
		params.put("curpage", (style==null)?"1":style);
		params.put("pagesize", (style==null)?"10":style);
		Log.w("---doop---", "start---------faXingQuan_getList----222-----");
		
		
		midooRequest.post("/faxingquan/index/getlist", params, new JsonHttpResponseHandler(){//

			Message msg = new Message();
			
			@Override
			public void onStart() {
				Log.w("---doop---faXingQuan_getList---", "---onStart---");
			}
			@Override
			public void onFinish() {
				Log.w("---doop---faXingQuan_getList---", "---onFinish---");
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.w("---doop---", "---onFailure---"+responseString+"------"+statusCode+"------"+throwable.getLocalizedMessage());
				
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.w("---doop---", "---onSuccess---"+response.toString()+"------"+statusCode+"------");
				Toast.makeText((FaXingQuanMainActivity) getActivity(), response.toString(), Toast.LENGTH_LONG).show();
				

				

				
			
				

				//JSONArray jsonList;
				try {
					imageUrls = response.getJSONArray("data");
					
					msg.what = (true) ? 1 : 0;
					msg.obj = (true) ? imageUrls : null;// 通知信息
					/*
					for (int i = 0; i < jsonList.length(); i++) {
					    JSONObject row = jsonList.getJSONObject(i);
					    Log.w("---doop---", "---row---"+row.toString()+"------------");
					    imageUrls.add(row);
					    //name = row.getString("stylephoto");
					}*/
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = -1;
					msg.obj = e;
				}
				mHandler.sendMessage(msg);

				
			}
		});
		
		
		
	}
	
	
	
	
/*	
	// 子类化一个Handler
	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				List<Car> listCar = (List<Car>) msg.obj;
				for (Car car : listCar) {
					Log.i(TAG, "车辆信息：" + car.getId() + "  " + car.getImage()
							+ "  " + car.getBrand() + "  " + car.getCarNum()
							+ "  " + car.getChejiahao());
				}
				if (null == listCar || listCar.size() == 0) {
					Log.i(TAG, "没有可管理的车辆！");
					listView.setAdapter(null);
//					list_lay.setBackgroundDrawable(null);
				} else {
//					list_lay.setBackgroundResource(R.drawable.bao);
					mainListAdapter = new MainListAdapter(MainActivity.this,
							listCar, listView, MainActivity.this);
					listView.setAdapter(mainListAdapter);
				}
				break;
			}		
			super.handleMessage(msg);
		}
	}
*/
	
	
}