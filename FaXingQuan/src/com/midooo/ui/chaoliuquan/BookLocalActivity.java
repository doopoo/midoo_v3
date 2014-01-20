package com.midooo.ui.chaoliuquan;


import java.io.File;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.lib.BaseFramework.ImageUtil;
import com.midooo.stylist.v3.adapter.BookInfo;
import com.midooo.stylist.v3.adapter.ListGirdAdapter;
import com.midooo.stylist.v3.adapter.ListGirdAdapter.BookView;
import com.midooo.ui.activitys.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
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
public class BookLocalActivity extends BaseActivity {

	private ListView bookShelf;
    private List<BookInfo>list;
    private ListGirdAdapter adapter;
    DisplayImageOptions options;
    private ProgressBar bar;
    private List<String>books;
    private BookInfo book;
    private BookInfo downBook;
    private boolean isLocal;
    private Map<Long,BookInfo> isFailed=new HashMap<Long, BookInfo>();
    Dialog dia;
 // 屏幕的高度
 	public static int screenHight;
 	// 屏幕的宽度
 	public static int screenWidth;
 	
 	
    public BookLocalActivity(int titleRes) {
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
    
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

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

		bookShelf = (ListView) findViewById(R.id.bookShelf);
		

	}
    
/*
	public class ImageLongClickListener implements BookLoadCallBack{

		@Override
		public void PostExecute(
				Object... params) {
			int index=(Integer) params[0];
			if(isLocal){
				dia=createDialog(BookLocalActivity.this, index);
				dia.show();
			}
		}
		
	}
	public class ImageClickListener implements BookLoadCallBack{
		@Override
		public void PostExecute(
				Object... params) {
			BookInfo bookInfo=(BookInfo) params[1];
			BookView bookView=(BookView) params[0];
			List<BookInfo> local=dbManager.getBookInfo(bookInfo.getId());
			

			ImageUtil.clearCache();
			System.gc();
			Intent bookdetails=new Intent(BookLocalActivity.this,MoreOperateActivity.class);
			Bundle bundle=new Bundle();
			bundle.putSerializable("book", local.get(0));
			bundle.putBoolean("browser", true);
			bookdetails.putExtras(bundle);
			startActivity(bookdetails); 
		
		}
	}
	


	private void showDownDialog(final BookInfo bookInfo){
		Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("下载提示").setMessage("书籍尚未下载，是否下载？");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(isOnlyWifiUse()){
					return;
				}
				 bar.setVisibility(View.VISIBLE);
				 bar.setProgress(0);
				 bar.setMax(0);
				String json=HttpManager.getLookBookJson(bookInfo.getId());
				String s=HttpManager.getS(json);
				RequestParams params=HttpManager.getParams(json);
					IApplication.getInstance().client.post(HttpManager.lookbook+s, params, new BookJsonHttpResponseHandler(HttpManager.lookbook));
				
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	
		dialog.create().show();
	}
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
					adapter=new ListGirdAdapter(list, BookLocalActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
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

	@Override
	protected void processLogic() {
		if(hasNetwork(this)){
			getServerData();
		}else{
			DBTask dbTask=new DBTask(this, callBack, true);
			dbTask.execute(OPERTYPE.getBookList);
		}
	}
	LoadCallBack callBack=new LoadCallBack() {
		
		@Override
		public void PostExecute(
				Object result) {
			list=(List<BookInfo>)result;
			adapter=new ListGirdAdapter(list, BookLocalActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
		        bookShelf.setAdapter(adapter);
		}
	};
    private void getServerData(){
    	final String json=HttpManager.getindexJson();
		final String s=HttpManager.getS(json);
		RequestParams params=HttpManager.getParams(json);
//		new Thread(){
//
//			@Override
//			public void run() {
//				try {
//					 File file=null;
//					 JSONObject jsonObject=null;
//					 LogUtil.i("HttpManager.login+signature=="+HttpManager.index+s);
//							jsonObject=HttpComm.post(HttpManager.index+s, json);
//					LogUtil.i("jsonObject=="+jsonObject);
//					int code=jsonObject.optInt("status");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				super.run();
//			}
//			
//		}.start();
		IApplication.getInstance().client.post(HttpManager.index+s,params, new BookJsonHttpResponseHandler(HttpManager.index));
    }
    private class BookJsonHttpResponseHandler extends JsonHttpResponseHandler{
    	private String url;
    	
    	public BookJsonHttpResponseHandler(String url) {
			super();
			this.url = url;
	
		}
    	
		@Override
		public void onStart() {
			if(url.equals(HttpManager.index)){
				showProgressDialog();
			}
			super.onStart();
		}

		@Override
		public void onSuccess(int statusCode, JSONObject response) {
			   closeProgressDialog();
			if(url.equals(HttpManager.lookbook)){
				String data=response.optString("data");
				LogUtil.i("data=="+data);
				 book=(BookInfo) JSONUtil.getData(data, BookInfo.class);
				books=book.getBook();
				book.setTotalpage(books.size());
				dbManager.updateBookInfo(book);
				 bar.setMax(books.size());
				 bar.setProgress(0);
				for(int i=0;i<books.size();i++){
					LogUtil.i("HttpManager.URL+books.get(i)=="+HttpManager.URL+books.get(i));
					if(!isFailed.containsKey(book.getId())){
						IApplication.getInstance().client.get(HttpManager.URL+books.get(i), new BookDownListener(book,i));
					}
				}
			}
			if(url.equals(HttpManager.index)){
				String data=response.optString("data");
				LogUtil.i("data=="+data);
				list=(List<BookInfo>) JSONUtil.getList(data, BookInfo.class);
				for(BookInfo bookInfo:list){
					boolean isHave=dbManager.isHave("id="+bookInfo.getId()+" and state=1", BookInfo.class);
					if(isHave){
						bookInfo.setState(1);
					}
					dbManager.insertBook(bookInfo);
				}
				adapter=new ListGirdAdapter(list, BookLocalActivity.this, options,new ImageClickListener(),new ImageLongClickListener());
			        bookShelf.setAdapter(adapter);
			}
			super.onSuccess(statusCode, response);
		}
		@Override
		public void onFailure(Throwable error, String content) {
			LogUtil.i("onFailure=="+error);
			if(error instanceof ConnectException){
			
				IToast.makeText(getApplicationContext(), R.string.str_connectionfail);
			}else{
				IToast.makeText(getApplicationContext(), R.string.str_getfail);
			}
			closeProgressDialog();
			if(bar!=null&&bar.isShown()){
				bar.setVisibility(View.GONE);
			}
			
			super.onFailure(error, content);
		}

    }
    int curr=0;
    private class BookDownListener extends BinaryHttpResponseHandler{
    	private BookInfo book;
    	private int index;
		public BookDownListener(BookInfo book,int index) {
			super();
			this.book = book;
			this.index = index;
		}

		@Override
		public void onSuccess(byte[] binaryData) {
			String targetPath=FileUtil.getRootPath()+ConstantPool.BOOK_IMG+book.getId()+File.separator;
			String targetName=index+".jpg";
			new Thread(new saveImageThread(targetPath, targetName, binaryData)).start();
			super.onSuccess(binaryData);
		}
		@Override
		public void onFailure(Throwable error, String content) {
			LogUtil.i("onFailure=="+error);
			if(!isFailed.containsKey(book.getId())){
				isFailed.put(book.getId(), book);
				IApplication.getInstance().client.cancelRequests(BookLocalActivity.this, true);
				if(error instanceof ConnectException){
					IToast.makeText(getApplicationContext(), R.string.str_connectionfail);
				}else{
					IToast.makeText(getApplicationContext(), R.string.str_getfail);
				}
			}
			bar.setVisibility(View.GONE);
			curr=0;
			super.onFailure(error, content);
		}

    }
    class saveImageThread implements Runnable{
	   	private String targetPath;
	   	private String targetName;
	   	private byte[] data;
		public saveImageThread(String targetPath, String targetName,
				byte[] data) {
			super();
			this.targetPath = targetPath;
			this.targetName = targetName;
			this.data = data;
		}
		@Override
		public void run() {
			if(isFailed.containsKey(book.getId())){
				curr=0;
				return;
			}
			FileUtil.saveImage(targetPath, targetName, data);
			curr++;
			LogUtil.i("curr=="+curr+";books.size()=="+books.size());
			bar.setProgress(curr);
			try {
				if(curr==books.size()){
					book.setState(1);
					dbManager.updateBookInfo(book);
					Message msg=handler.obtainMessage();
					msg.what=1;
					handler.sendMessage(msg);
					curr=0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
	   }
*/
    
}