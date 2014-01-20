package com.midooo.ui.activitys;

import com.midooo.ui.chaoliuquan.BookShelfActivity;
import com.midooo.ui.xunfanji.CefenggeActivity;
import com.midooo.ui.xunfanji.XunfanjiActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

///import com.midooo.ui.activitys.R;

public class FaXingQuanMenuFragment extends Fragment {

	

	private View layoutView;
	DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	//protected AbsListView listView;

	private GridView menuGrid;
	
    public final static String[] menu_image = {"drawable://"+R.drawable.xunfanji, "drawable://"+R.drawable.gukebiao, 
    	"drawable://"+R.drawable.chaoliuquan, "drawable://"+R.drawable.aimeise, "drawable://"+R.drawable.xiaofeiku,
    	"drawable://"+R.drawable.yuyuekong, "drawable://"+R.drawable.taofaxing, "drawable://"+R.drawable.waimaibao, 
    	 "drawable://"+R.drawable.more };//菜单图片资源  
    //public final static String[] menu_name_array = { "用户反馈","帮助","退出", };//菜单文字资源  
 



	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layoutView = inflater.inflate(R.layout.menu_image_grid, null);
		options = Constants.options;
		menuGrid = (GridView) layoutView.findViewById(R.id.menugrid);

		//((GridView) listView).setAdapter(new ImageAdapter());
		menuGrid.setAdapter(new ImageAdapter(inflater));
		menuGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//startImagePagerActivity(position);
				Log.w("---doop---", "startImagePagerActivity(position)----"+position+"-----");
				switch (position) {
				case 0:
					
					Intent xfj=new Intent((FaXingQuanMainActivity) getActivity(), XunfanjiActivity.class);

					startActivity(xfj);

					break;
				case 1:
					//newContent = new ColorFragment(R.color.green);BookShelfActivity
					break;

				case 2:

					startActivity(new Intent((FaXingQuanMainActivity) getActivity(), BookShelfActivity.class));
					//newContent = new ColorFragment(R.color.green);
					break;
				}				
			}
		});
		return layoutView;
	}
	/*
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.color_names);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, colors);
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new ColorFragment(R.color.red);
			break;
		case 1:
			newContent = new ColorFragment(R.color.green);
			break;
		case 2:
			newContent = new ColorFragment(R.color.blue);
			break;
		case 3:
			newContent = new ColorFragment(android.R.color.white);
			break;
		case 4:
			newContent = new ColorFragment(android.R.color.black);
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

*/
	
	public class ImageAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		
		ImageAdapter(LayoutInflater inflater) {
			this.inflater = inflater;
		}
		@Override
		public int getCount() {
			return menu_image.length;
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

			imageLoader.displayImage(menu_image[position], imageView, options);
			return imageView;
		}
	}

}
