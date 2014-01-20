package com.midooo.ui.activitys;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.lib.BaseFramework.midooRequest;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.midooo.ui.activitys.R;
import com.midooo.ui.aimeise.AimeiseActivity;
import com.midooo.userCenter.autoLogin;




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
		
		//autoLogin.login();
	
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



/*
[{"id":"3","userid":"41","style":"1","length":"1","curl":"1","color":"1","sex":"0","stylephoto":"http:\/\/img1.kb8.cn\/201312260001.jpg","recording":"http:\/\/img1.kb8.cn\/style.mp3","praise":"0","collect":"0","share":"0","comment":"0","location":"\u90d1\u5dde","remark":"111","createtime":"1389264564","lasttime":"1389264564","isdelete":"0","name":"\u738b\u5927\u9524","photo":null,"vip":"1"},
{"id":"1","userid":"41","style":"1","length":"1","curl":"1","color":"1","sex":"0","stylephoto":"http:\/\/img1.kb8.cn\/201312260001.jpg","recording":"http:\/\/img1.kb8.cn\/style.mp3","praise":"0","collect":"0","share":"0","comment":"0","location":"","remark":"","createtime":"1389264506","lasttime":"1389264506","isdelete":"0","name":"\u738b\u5927\u9524","photo":null,"vip":"1"}]
 * 
 * */
/*
	private void initData(){
		SharedPreferences press = FaXingQuanMainActivity.this.getSharedPreferences("FaXingQuan", 0);
		String faXingQuan_cache = press.getString("/faxingquan/index/getlist", "");

		if ("".equals(faXingQuan_cache)) {
		} else {
			try {
				JSONArray jsonList = new JSONArray(faXingQuan_cache);
		        //boolean isArray = jsonList.isArray();
		        //boolean isEmpty = jsonList.isEmpty();
		        //boolean isNullObject = jsonObject.isNullObject();			
				String flag = jsonn.optString("flat");
				Log.i(TAG, "用户flat：" + flag);
				if ("1".equals(flag)) { // 付费用户
					String payType = jsonn.optString("payType");
					String status = jsonn.optString("status");
					String endTime = jsonn.optString("end");
					Log.i(TAG, "用户payType:" + payType + "status:" + status
							+ "end:" + endTime);
					if ("2".equals(payType)) { // 短信付费
						if ("1".equals(status)) { // 确定是会员
							nsCompare();
						} else if ("0".equals(status)) {
							if (Util.DateCheck(endTime)) { // 判断是否在短信免费的一天内
								nsCompare();
							}
						}
					} else if ("1".equals(payType)) { // 支付宝付费
						nsCompare();
					}

				} else {
					carService.updateRemindAll();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	private void faXingQuan_getList(){
		faXingQuan_getList(null, null, null, null, null, null, null);
	}
 

	private void faXingQuan_getList(String style, String length, String curl, String color, String sex, String curpage, String pagesize) {
		RequestParams params = new RequestParams();

		params.put("style", (style==null)?"0":style);
		params.put("length", (style==null)?"0":style);
		params.put("curl", (style==null)?"0":style);
		params.put("color", (style==null)?"0":style);
		params.put("sex", (style==null)?"":style);
		params.put("curpage", (style==null)?"1":style);
		params.put("pagesize", (style==null)?"10":style);
		
		midooRequest.post("/faxingquan/index/getlist", params, new JsonHttpResponseHandler(){
			

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
				Toast.makeText(FaXingQuanMainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
				
			}
		});
		

	}




	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	    if(tab.getPosition()==0){
	        autoLogin.login();
	    }else if(tab.getPosition()==1){
	    	faXingQuan_getList();
	        //ft.replace(android.R.id.content, PortfolioFragment.newInstance(i));
	    }
		
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
