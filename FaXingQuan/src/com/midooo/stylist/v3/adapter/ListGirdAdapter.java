package com.midooo.stylist.v3.adapter;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.midooo.lib.BaseFramework.Const;
import com.midooo.lib.BaseFramework.ImageUtil;
import com.midooo.lib.BaseFramework.LogUtil;
import com.midooo.lib.BaseFramework.UILApplication;

import com.midooo.ui.activitys.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageSize;

public class ListGirdAdapter extends BaseAdapter {

	private List<BookInfo> list;
//	private static int lineConut=12;
	private int defaultLine;
	private Context context;
	private DisplayImageOptions options;
	//private ImageClickListener clickListener;
	int screenW,screenH;
	int lineH;
	int lineW;
	int imgH;
	int imgW;
	int pbW;
	protected ImageLoader imageLoader =UILApplication.getInstance().getImageLoader();
	public void setList(List<BookInfo> list){
		this.list=list;
		this.notifyDataSetChanged();
	}
	public ListGirdAdapter( List<BookInfo> list,Context context, DisplayImageOptions options) {
		super();
		this.list = list;
//		this.defaultLine = defaultLine;
		this.context = context;
		this.options = options;
		//this.clickListener=clickListener;

		WindowManager windowManager=((Activity)context).getWindowManager();
		DisplayMetrics displayMetrics=((Activity)context).getResources().getDisplayMetrics();
		screenW=windowManager.getDefaultDisplay().getWidth();
		screenH=windowManager.getDefaultDisplay().getHeight();
		Log.e("", ""+screenW);
		Log.e("", ""+screenH);
		
		float density=displayMetrics.density;
		LogUtil.i("density==="+density);
		imgW=screenW/4;
		pbW=imgW;
		if(screenH>1000){ 
			lineH=320;
			imgH=220;
		}
		if(screenW>1000){
			lineH=380;
			imgH=300;
		}
		else{
			lineH=(int)(320*displayMetrics.density/2);
			imgH=lineH-lineH/4;
		}
		if(density==3.0&&screenH>1900){
			lineH=420;
			imgH=300;
			pbW=imgW-200;
		}
		this.defaultLine=(screenH-1)/lineH+1;
	}


	@Override
	public int getCount() {
		if(list==null){
			return 0;
		}
		return ((list.size()-1)/3+1)>defaultLine?list.size():defaultLine;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemView itemView=null;
		if(convertView==null){
			itemView=new ItemView();
			convertView=initItemView(itemView);
			convertView.setTag(itemView);
		}else{
			itemView=(ItemView)convertView.getTag();
		}
		
		int start=position*3;
		int end=position*3+2;
		
		for (int i = start; i <=end; i++) {
			BookView bookView=itemView.bookViewlist.get(i%3);
			if(list.size()-1>=i){
				setItem(i,bookView);
			}else{
				setDefaultItem(bookView);
			}
		}
		
		return convertView;
	}
	
	
	/** 
	 * @Title: initItemView 
	 * @Description: 
	 * @param @param itemView
	 * @param @return  
	 * @return View  
	 * @throws 
	 */ 
	private View initItemView(ItemView itemView) {
		
		View view=LayoutInflater.from(context).inflate(R.layout.book_item, null);
		
		LinearLayout rl=( LinearLayout)view.findViewById(R.id.rl);
		
		rl.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,lineH));
		
		BookView bookView1=new BookView();
		BookView bookView2=new BookView();
		BookView bookView3=new BookView();
		List<BookView> list=new ArrayList<BookView>();
		list.add(bookView1);
		list.add(bookView2);
		list.add(bookView3);
		itemView.bookViewlist=list;
		bookView1.rl_book=(LinearLayout)view.findViewById(R.id.rl_book_1);
		bookView1.tv_book_state=(TextView)view.findViewById(R.id.tv_book_state_1);
		bookView1.pb_down_bar=(ProgressBar)view.findViewById(R.id.pb_down_bar_1);
		bookView1.tv_book_title=(TextView)view.findViewById(R.id.tv_book_title_1);
		bookView1.iv_book_img=(ImageView)view.findViewById(R.id.iv_book_img_1);
		LayoutParams params1=bookView1.iv_book_img.getLayoutParams();
		params1.height=imgH;
		params1.width=imgW;
		bookView1.iv_book_img.setLayoutParams(params1);
		LayoutParams paramsBar1=bookView1.pb_down_bar.getLayoutParams();
		paramsBar1.width=pbW;
		bookView1.pb_down_bar.setLayoutParams(paramsBar1);
		
		bookView2.rl_book=(LinearLayout)view.findViewById(R.id.rl_book_2);
		bookView2.tv_book_state=(TextView)view.findViewById(R.id.tv_book_state_2);
		bookView2.pb_down_bar=(ProgressBar)view.findViewById(R.id.pb_down_bar_2);
		bookView2.tv_book_title=(TextView)view.findViewById(R.id.tv_book_title_2);
		bookView2.iv_book_img=(ImageView)view.findViewById(R.id.iv_book_img_2);
		LayoutParams params2=bookView2.iv_book_img.getLayoutParams();
		params2.height=imgH;
		params2.width=imgW;
		bookView2.iv_book_img.setLayoutParams(params2);
		LayoutParams paramsBar2=bookView2.pb_down_bar.getLayoutParams();
		paramsBar2.width=pbW;
		bookView2.pb_down_bar.setLayoutParams(paramsBar2);
		
		bookView3.rl_book=(LinearLayout)view.findViewById(R.id.rl_book_3);
		bookView3.tv_book_state=(TextView)view.findViewById(R.id.tv_book_state_3);
		bookView3.pb_down_bar=(ProgressBar)view.findViewById(R.id.pb_down_bar_3);
		bookView3.tv_book_title=(TextView)view.findViewById(R.id.tv_book_title_3);
		bookView3.iv_book_img=(ImageView)view.findViewById(R.id.iv_book_img_3);
		LayoutParams params3=bookView3.iv_book_img.getLayoutParams();
		params3.height=imgH;
		params3.width=imgW;
		bookView3.iv_book_img.setLayoutParams(params3);
		LayoutParams paramsBar3=bookView3.pb_down_bar.getLayoutParams();
		paramsBar3.width=pbW;
		bookView3.pb_down_bar.setLayoutParams(paramsBar3);
		return view;
	}


	public void setItem(int index,BookView bookView){
		BookInfo bookInfo=list.get(index);
		bookView.rl_book.setVisibility(View.VISIBLE);
		bookView.tv_book_title.setText(bookInfo.getTitle());
		//DBManager dbManager=new DBManager(context);
		//boolean isHave=dbManager.isHave("id="+bookInfo.getId()+" and state=1", BookInfo.class);
		/*
		if(isHave){
			bookInfo.setState(1);
		}
		if(bookInfo.getState()==1){
			bookView.tv_book_state.setVisibility(View.VISIBLE);
		}else{
			bookView.tv_book_state.setVisibility(View.GONE);
		}
		*/
		String url=Const.URL+bookInfo.getIndexPic();
		LogUtil.i("bookurl==="+url);
		//bookView.iv_book_img.setOnClickListener(new ImgClickListener(bookInfo, bookView));
		//bookView.iv_book_img.setOnLongClickListener(new ImgLongClickListener(index));
//		this.imageLoader.displayImage(url, bookView.iv_book_img, options);
		ImageSize imageSize=new ImageSize(Const.THUM_W, Const.THUM_H);
		imageLoader.loadImage(url, imageSize,options,new MyImageLoader(bookView.iv_book_img, imageSize));
	}
	int count=0;
	private class MyImageLoader implements ImageLoadingListener{
		private ImageView imageView;
		private ImageSize imageSize;
		
		public MyImageLoader(
				ImageView imageView,
				ImageSize imageSize) {
			super();
			this.imageView = imageView;
			this.imageSize = imageSize;
		}

		@Override
		public void onLoadingStarted(
				String imageUri,
				View view) {
			imageView.setImageResource(R.drawable.waiting);
			
		}

		@Override
		public void onLoadingFailed(
				String imageUri,
				View view,
				FailReason failReason) {
			if(failReason.getType().name().equals(Const.OUT_OF_MEMORY)){
				if(count<2){
					imageLoader.clearMemoryCache();
					imageLoader.getMemoryCache().clear();
					ImageUtil.clearCache();
					java.lang.System.gc();
					imageLoader.loadImage(imageUri, imageSize, options, this);
				}
				count++;
			}else{
				imageView.setImageResource(R.drawable.waiting);
			}
			
		}

		@Override
		public void onLoadingComplete(
				String imageUri,
				View view,
				Bitmap loadedImage) {
			LogUtil.i("loadedImage==="+loadedImage.getHeight()+";loadedImage=="+loadedImage.getWidth());
			imageView.setImageBitmap(loadedImage);
			
		}

		@Override
		public void onLoadingCancelled(
				String imageUri,
				View view) {
			// TODO Auto-generated method stub
			
		}
		
	} 
	public void setDefaultItem(BookView bookView){
		bookView.rl_book.setVisibility(View.INVISIBLE);
	}
	
	class ItemView{
		
		List<BookView> bookViewlist;
		
	}

	public class BookView{
		 public LinearLayout  rl_book;
		 public TextView  tv_book_state;
		 public ProgressBar  pb_down_bar;
		 public TextView  tv_book_title;
		 public ImageView  iv_book_img;
	}
	/*
	private class ImgClickListener implements OnClickListener{
		private BookInfo bookInfo;
		private BookView bookView;
		
		public ImgClickListener(
				BookInfo bookInfo,
				BookView bookView) {
			super();
			this.bookInfo = bookInfo;
			this.bookView = bookView;
		}

		
		@Override
		public void onClick(View arg0) {
			clickListener.PostExecute(bookView,bookInfo);
		}
		
	}*/
}
