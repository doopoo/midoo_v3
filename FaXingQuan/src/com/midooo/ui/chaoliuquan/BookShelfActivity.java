package com.midooo.ui.chaoliuquan;


import java.io.File;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.lib.BaseFramework.Const;
import com.midooo.lib.BaseFramework.FileUtil;
import com.midooo.lib.BaseFramework.ImageUtil;
import com.midooo.lib.BaseFramework.UILApplication;
import com.midooo.lib.BaseFramework.midooRequest;
import com.midooo.stylist.v3.adapter.BookInfo;
import com.midooo.stylist.v3.adapter.ListGirdAdapter;
import com.midooo.stylist.v3.adapter.ListGirdAdapter.BookView;
import com.midooo.ui.activitys.FaXingQuanMainActivity;
import com.midooo.ui.activitys.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 
 * @{# ChaoliuquanActivity.java Create on 2013-6-21 下午2:33:02      
 * @Description: 潮流圈
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>
 */
public class BookShelfActivity extends BaseActivity {

	private GridView bookShelf;
    private List<BookInfo>list;
    private BookShelfAdapter adapter;
    DisplayImageOptions options;
    private ProgressBar bar;
    private List<String>books;
    private BookInfo book;
    private BookInfo downBook;
    private boolean isLocal;
    JSONArray jsonArray = null;
    private Map<Long,BookInfo> isFailed=new HashMap<Long, BookInfo>();
    Dialog dia;
 // 屏幕的高度
 	public static int screenHight;
 	// 屏幕的宽度
 	public static int screenWidth;
 	
 	
    public BookShelfActivity(int titleRes) {
		super(titleRes);
		// TODO Auto-generated constructor stub
	}
    private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				bar.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), R.string.sd_isdowned, 0).show();
//				list.remove(downBook);
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
    	
    };
    
    
	public BookShelfActivity() {
		super(R.string.changing_fragments);
		// see the class CustomAnimation for how to attach 
		// the CanvasTransformer to the SlidingMenu
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//this.txt_title.setText(R.string.str_title_chaoliu);
		//this.ibtn_header_left.setImageResource(R.drawable.header_left_online_btn_selected);
		//this.ibtn_header_right.setImageResource(R.drawable.header_right__book_local_btn_selected);
		setContentView(R.layout.booklist);
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.waiting)
		.showImageForEmptyUri(R.drawable.waiting)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();	
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
			screenHight = dm.heightPixels;
			screenWidth = dm.widthPixels;

		bookShelf = (GridView) findViewById(R.id.bookShelf);
		if(hasNetwork(this)){
			getBookShelf();
		}

	}
    

/*
	public class ImageClickListener implements BookLoadCallBack{
		@Override
		public void PostExecute(
				Object... params) {
			BookInfo bookInfo=(BookInfo) params[1];
			BookView bookView=(BookView) params[0];
			List<BookInfo> local=dbManager.getBookInfo(bookInfo.getId());
			

			ImageUtil.clearCache();
			System.gc();
			Intent bookdetails=new Intent(BookShelfActivity.this,MoreOperateActivity.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("book", local.get(0));
			bundle.putBoolean("browser", true);
			bookdetails.putExtras(bundle);
			startActivity(bookdetails); 
		
		}
	}


*/

/*

	@Override
	protected boolean onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.ibtn_header_left:
			if(bar!=null&&bar.isShown()){
				IToast.makeText(getApplicationContext(),R.string.sd_isdowning);
			}else{
				isLocal=false;
				 processLogic();
			}
			return true;
		case R.id.ibtn_header_right:
			if(bar!=null&&bar.isShown()){
				IToast.makeText(getApplicationContext(),R.string.sd_isdowning);
			}else{
				isLocal=true;
				list=dbManager.getDownBooks();
				if(list==null){
					list=new ArrayList<BookInfo>();
				}
				if(adapter!=null){
					adapter.setList(list);
				}else{
					adapter=new ListGirdAdapter(list, BookShelfActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
			        bookShelf.setAdapter(adapter);
				}
				
//				adapter=new ListGirdAdapter(list, BookListActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
//		        bookShelf.setAdapter(adapter);
			}
			return true;

		default: 
			break;
		}
		return false;
	}

*/

	
	/*
	LoadCallBack callBack=new LoadCallBack() {
		
		@Override
		public void PostExecute(
				Object result) {
			list=(List<BookInfo>)result;
			adapter=new ListGirdAdapter(list, BookShelfActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
		        bookShelf.setAdapter(adapter);
		}
	};*/

    

    

	
	public void getBookShelf() {
		RequestParams params = new RequestParams();
		params.put("sort", "");
	
		midooRequest.post("/ebook/index/bookshelf", params, new JsonHttpResponseHandler(){
			

			@Override
			public void onStart() {
				Log.w("---doop---", "---onStart---");
			}
			@Override
			public void onFinish() {
				Log.w("---doop---", "---onFinish---");
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.w("---doop---", "---onFailure---"+responseString+"------"+statusCode+"------"+throwable.getLocalizedMessage());

				
				super.onFailure(statusCode, headers, responseString, throwable);
			
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.w("---doop---", "---onSuccess---"+response.toString()+"------"+statusCode+"------");


				if ("0".equals(response.optString("status"))){

					adapter=new BookShelfAdapter(BookShelfActivity.this, response.optJSONArray("data"));
			        bookShelf.setAdapter(adapter);
				}

				super.onSuccess(statusCode, headers, response);
			
			}
		});
		
	}

    
    
    
 

    
	public class BookShelfAdapter extends BaseAdapter {

		
		LayoutInflater inflater;
		JSONArray jsonArray;

		public BookShelfAdapter(Context context, JSONArray jsonArray) {
			this.jsonArray = jsonArray;
			inflater = LayoutInflater.from(context);
		}
		
		private class ViewHolder {
			public TextView text;
			public ImageView image;
		}
		
		@Override
		public int getCount() {
			return jsonArray.length();
		}

		@Override
		public Object getItem(int position) {
			try {
				return jsonArray.get(position);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
			
		}


		@Override
		public long getItemId(int position) {
			return position;
		}

		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {		
			View view=convertView;
			ViewHolder holder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.book_sh_item, parent, false);
				//TextView idTextView = (TextView) view.findViewById(R.id.book_sh_image);
				//TextView nameTextView = (TextView) view.findViewById(R.id.book_sh_title);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.book_sh_title);
				holder.image = (ImageView) view.findViewById(R.id.book_sh_image);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			
			try {
				// 得到JSONObject
				JSONObject jsonObject = jsonArray.getJSONObject(position);
				holder.text.setText(jsonObject.optString("title"));
				imageLoader.displayImage(jsonObject.optString("lastestcover"), holder.image, options);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return view;
		}
	}
	
	

    
    
}