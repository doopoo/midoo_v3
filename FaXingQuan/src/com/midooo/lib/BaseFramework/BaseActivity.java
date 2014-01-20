package com.midooo.lib.BaseFramework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import com.midooo.ui.activitys.R;
import com.midooo.userCenter.autoLogin;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class BaseActivity extends 
	SlidingFragmentActivity {
	
	private boolean isRequesting;
	private ProgressDialog progressDialog;
	private View contentView;
	public ImageButton ibtn_header_left;
	public ImageButton ibtn_header_right;
	public TextView txt_title;
	private ImageButton ibtn_share;
	private ImageButton ibtn_remind;
	public ImageButton ibtn_main;
	public TextView tv_remind_num;
	public LinearLayout frame_content;
	public RelativeLayout head_layout;
	public RelativeLayout ll_bar;
	public FrameLayout fl_main;
	public LayoutInflater inflater;
	DisplayImageOptions options;
	KeyboardLayout mainView ;
	public Uri imageUri;//to store the big bitmap
	public   String IMAGE_FILE_LOCATION = "file:///sdcard/.midoo_cache/temp.jpg";
	public   String cutImgPath =null;
	public File tempFile;
	public File tempDir;
	boolean isEdit=false;
	public boolean isEditMode;

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private int mTitleRes;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		//IApplication.getInstance().addActivity(this);
		//imageLoader=UILApplication.getInstance().getImageLoader();
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		   BitmapFactory.Options o = new BitmapFactory.Options();
		   o.inJustDecodeBounds=false;
			o.inSampleSize=4;
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.waiting)
		.showImageForEmptyUri(R.drawable.waiting)
		.cacheOnDisc(true)
		.cacheInMemory(true)
		.decodingOptions(o)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.resetViewBeforeLoading(true)
		.displayer(new SimpleBitmapDisplayer()) 
		.build();	
		//ImageUtil.clearCache();

		inflater = LayoutInflater.from(this);

		if(0==1){
			//IApplication.getInstance().deleUserInfo();
			//IApplication.getInstance().relogin();
			//startActivity(new Intent(this, LoginActivity.class));
		}
	
		
		
		
		
		
		
		
		
		
		super.onCreate(savedInstanceState);
		//setTitle(mTitleRes);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
		// set the Behind View
		//super.setContentView(R.layout.frame);menu_frame
		setBehindContentView(R.layout.frame);
		/*
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new SampleListFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}
*/
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();//SlidingMenu控件的初始化 
		sm.setShadowWidthRes(R.dimen.shadow_width);//阴影宽度  
		sm.setShadowDrawable(R.drawable.shadow);//阴影Drawable 
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//拉开后离边框距离 
		sm.setFadeDegree(0.35f);						//颜色渐变比例 
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);	//拉动事件区域  --全屏 TOUCHMODE_FULLSCREEN

		//getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ActionBar返回启用
		initBaseView();
		//setBaseListener();

	}

/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		case R.id.github:
			Util.goToGitHub(this);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.layout.main, menu);
		return true;
	}
*/

	private void initBaseView() {
		ibtn_header_left = (ImageButton) super
				.findViewById(R.id.ibtn_header_left);
		ibtn_header_right = (ImageButton) super
				.findViewById(R.id.ibtn_header_right);
		ibtn_share = (ImageButton) super.findViewById(R.id.ibtn_shares);
		ibtn_remind = (ImageButton) super.findViewById(R.id.ibtn_remind);
		ibtn_main = (ImageButton) super.findViewById(R.id.ibtn_main);
		txt_title = (TextView) super.findViewById(R.id.txt_title);
		tv_remind_num = (TextView) super.findViewById(R.id.tv_remind_num);
		frame_content = (LinearLayout) super.findViewById(R.id.frame_content);
		head_layout = (RelativeLayout) super.findViewById(R.id.head_layout);
		ll_bar = (RelativeLayout) super.findViewById(R.id.ll_bar);
		fl_main = (FrameLayout) super.findViewById(R.id.fl_main);
		String rootPath = FileUtil.getRootPath();
		if (rootPath == null) {
			Toast.makeText(getApplicationContext(), R.string.sd_nofound, 0)
					.show();
		}
		tempDir = new File(rootPath + Const.TEMP_IMG);
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		File hidden = new File(rootPath + Const.TEMP_IMG_HIDDEN);
		if (!hidden.exists()) {
			hidden.mkdirs();
		}
	}
	
/*
	private void setBaseListener() {
		ibtn_header_left.setOnClickListener(this);
		ibtn_header_right.setOnClickListener(this);
		ibtn_share.setOnClickListener(this);
		ibtn_remind.setOnClickListener(this);
		ibtn_main.setOnClickListener(this);
	}
	*/

	@Override
	protected void onPause() {
		//UILApplication.getInstance().getImageLoader().pause();
		super.onPause();
	}






/*	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//getMenuInflater().inflate(R.layout.main, menu);
    	getSupportMenuInflater().inflate(R.layout.main, menu);
    	//return true;

        SubMenu styleSubMenu = menu.addSubMenu("全部风格^");
        styleSubMenu.add("可爱");
        styleSubMenu.add("时尚");
        styleSubMenu.add("青春");
        styleSubMenu.add("优雅");
        styleSubMenu.add("柔美");
        styleSubMenu.add("知性");
        styleSubMenu.add("浪漫");
        styleSubMenu.add("美丽");
        styleSubMenu.add("摩登");
        
        MenuItem subMenu1Item = styleSubMenu.getItem();
        //subMenu1Item.setIcon(R.drawable.ic_title_share_default);
        subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
			case android.R.id.home:
				toggle();
				autoLogin.login();
				return true;
            case R.id.fxq_menu_mode1:
                Toast.makeText(this, "First Action Item", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.fxq_menu_mode2:
                Toast.makeText(this, "Second Action Item", Toast.LENGTH_SHORT).show();
                return true;
            default:
            	Toast.makeText(this, "---"+item.getOrder()+"---"+item.getTitle(), Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

*/

	public boolean hasNetwork(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if (workinfo == null || !workinfo.isAvailable()||!workinfo.isConnected()) {
			return false;
		}
		return true;
	}	
	
}
