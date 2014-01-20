package com.midooo.ui.xunfanji;

import java.util.ArrayList;
import java.util.Arrays;

import com.midooo.lib.BaseFramework.Const.Cefengge;
import com.midooo.ui.activitys.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @{# CefenggeActivity.java Create on 2013-6-21 下午2:32:35      
 * @Description: 寻范记测风格
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>
 */
public class CefenggeActivity extends Activity {

	 public ViewPager viewPager;  
	 
	 public ArrayList<View> pageViews;  
	 
	 private ImageView imageView;  
	 
	 private ImageView[] imageViews; 
	 
	 // 包裹滑动图片LinearLayout
	 private ViewGroup main;	 
	 
	 // 包裹小圆点的LinearLayout
	 private ViewGroup group;

	 public int number = 7;

	 private final int pagecount = 7;
	 
	 private GuidePageAdapter gpAdapter;
	 
	 private GuidePageChangeListener listener;
	 
	 public int saveOpenNum[];
	 
	 public int currPageNum;
	 
	 private Button topbar_btn_left, topbar_btn_right, topbar_btn_right2;
	 private boolean isDirty;
	 
	 private TextView titleTv;
	 
	 private int questionpot[];
	 String[] isSelectStrings={"true","false","false","false","false","false","false","false"};
	 private CefenggeModelView view1, view2, view3, view4, view5, view6, view7, view8;
	 
    // Called when the activity is first created. 
   
	 
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//this.txt_title.setText(R.string.str_title_cefengge);
			//this.ibtn_header_right.setImageResource(R.drawable.header_right_ninestyle_btn_selected);
	        saveOpenNum = new int[pagecount + 1];
	        
	        questionpot = new int[(pagecount + 1) * 2];
	        
	        gpAdapter = new GuidePageAdapter();
	        
	        LayoutInflater inflater = getLayoutInflater();  
	        
	        pageViews = new ArrayList<View>();  
	        
	        view1 = new CefenggeModelView(this);
	        pageViews.add(view1.getView());
	        
	        view2 = new CefenggeModelView(this);
	        pageViews.add(view2.getView());
	        
	        view3 = new CefenggeModelView(this);
	        pageViews.add(view3.getView());
	        
	        view4 = new CefenggeModelView(this);
	        pageViews.add(view4.getView());
	        
	        view5 = new CefenggeModelView(this);
	        pageViews.add(view5.getView());
	        
	        view6 = new CefenggeModelView(this);
	        pageViews.add(view6.getView());
	        
	        view7 = new CefenggeModelView(this);
	        pageViews.add(view7.getView());
	        
	        view8 = new CefenggeModelView(this);
	        pageViews.add(view8.getView());
	        
	        imageViews = new ImageView[pageViews.size()]; 
	        
	        main = (ViewGroup)inflater.inflate(R.layout.activity_fanzhenduan, null);  
	        
	        group = (ViewGroup)main.findViewById(R.id.viewGroup); 
	        
	        viewPager = (ViewPager)main.findViewById(R.id.guidePages);

	        initImageViews();

	        setContentView(main);

//	        initContorl();
	        
	        initContorlData();
	        
	        viewPager.setAdapter(gpAdapter);  
	        
	        listener = new GuidePageChangeListener();
	        viewPager.setOnPageChangeListener(listener);
	        listener.onPageSelected(0);
		
			

		}
		



    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			goBack();
			return true;
		}
		return false;
	}
    
    private void goBack()
    {
    	if(view1.topischoose){
			showConfirmIgnorBack();
		}
		else
		{
			//IApplication.SHOW_ACTIVITY=0;
			finish();
		}
    }

    
    private void initContorlData(){
    	view1.initTopData(this.getResources().getTextArray(R.array.one), Cefengge.ONE);
    	view1.initBottomData(this.getResources().getTextArray(R.array.two), Cefengge.TWO);
    	
    	view2.initTopData(this.getResources().getTextArray(R.array.three), Cefengge.THREE);
    	view2.initBottomData(this.getResources().getTextArray(R.array.four), Cefengge.FOUR);
    	
    	view3.initTopData(this.getResources().getTextArray(R.array.fivf), Cefengge.FIVE);
    	view3.initBottomData(this.getResources().getTextArray(R.array.six), Cefengge.SIX);
    	
    	view4.initTopData(this.getResources().getTextArray(R.array.seven), Cefengge.SEVEN);
    	view4.initBottomData(this.getResources().getTextArray(R.array.enght), Cefengge.ENGHT);
    	
    	view5.initTopData(this.getResources().getTextArray(R.array.nine), Cefengge.NINE);
    	view5.initBottomData(this.getResources().getTextArray(R.array.ten), Cefengge.TEN);
    	
    	view6.initTopData(this.getResources().getTextArray(R.array.eleven), Cefengge.ELEVEN);
    	view6.initBottomData(this.getResources().getTextArray(R.array.twelve), Cefengge.TWELVE);
    	
    	view7.initTopData(this.getResources().getTextArray(R.array.thirteen), Cefengge.THIRTEEN);
    	view7.initBottomData(this.getResources().getTextArray(R.array.fourteen), Cefengge.FOURTEEN);
    	
    	view8.initBottomData(this.getResources().getTextArray(R.array.fifteen), Cefengge.FIFTEEN);
    	view8.initTopData(this.getResources().getTextArray(R.array.sixteen), Cefengge.SIXTEEN);
    }
    
    private void initImageViews(){
    	
    	for (int i = 0; i < pageViews.size(); i++) {  
            imageView = new ImageView(CefenggeActivity.this);  
            
            LinearLayout.LayoutParams vParams=new LinearLayout.LayoutParams(10,10);
            vParams.leftMargin=10;
            vParams.rightMargin=10;
            imageView.setLayoutParams(vParams);  
            //imageView.setPadding(20, 0, 20, 0);
            
            imageViews[i] = imageView;  
            
            if (i == 0) {  
                //默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.x_text_progress_doingicon);  
            } else {  
                imageViews[i].setBackgroundResource(R.drawable.x_text_progress_notyeticon);  
            }  
            
            group.addView(imageViews[i]);  
        }
    }
    
    public void autofillingPage(){
    	if(number > 0){
			if(saveOpenNum[currPageNum + 1] == 0){
				number -= 1;
				viewPager.setCurrentItem(pageViews.size() - number);
			}
		}
    }
    
    private void showConfirmIgnorBack() {
    	new AlertDialog.Builder(this) 
		.setTitle("确认")
		.setMessage("测试尚未完成，是否退出？")
		.setNegativeButton("否", null)
		.setPositiveButton("是", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				finish();
			}
		})
		.show();
	}
    
//    private void showConfirmIgnor() {
//    	new AlertDialog.Builder(this) 
//		.setTitle("确认")
//		.setMessage("测试尚未完成，是否退出？")
//		.setNegativeButton("否", null)
//		.setPositiveButton("是", new DialogInterface.OnClickListener(){
//			@Override
//			public void onClick(DialogInterface arg0, int arg1) {
//				IApplication.SHOW_ACTIVITY=IApplication.ACTIVITY_MAIN;
//				//setResult(pubContans.ACTIVITY_MAIN); 
//				finish();
//			}
//		})
//		.show();
//	}
//    
//    private void showConfirmIgnorNine() {
//    	new AlertDialog.Builder(this) 
//		.setTitle("确认")
//		.setMessage("测试尚未完成，是否退出？")
//		.setNegativeButton("否", null)
//		.setPositiveButton("是", new DialogInterface.OnClickListener(){
//			@Override
//			public void onClick(DialogInterface arg0, int arg1) {  
//				//setResult(pubContans.ACTIVITY_NICE_STYLE); 
//				IApplication.SHOW_ACTIVITY=IApplication.ACTIVITY_NICE_STYLE;
//				finish();
//			}
//		})
//		.show();
//	}
    
    private void popConfirm(View parentView, String inConfirmStr) {
		//
		final PopupWindow popupWindow;

		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View common_popup = (View) mLayoutInflater.inflate(
				R.layout.pop_test_end, null);
		TextView vTextContext = (TextView) common_popup
				.findViewById(R.id.content);
		
		vTextContext.setText(inConfirmStr);

		popupWindow = new PopupWindow(common_popup, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);

		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// popupWindow.setAnimationStyle(R.style.PopupAnimation);
		popupWindow.showAtLocation(parentView, Gravity.CENTER | Gravity.CENTER,
				0, 0);
		popupWindow.update();

		Button vButtonConfirm = (Button) common_popup
				.findViewById(R.id.button_confirm);
		vButtonConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
				goDetail();
				
			}
		});

		Button vButtonAddRemind = (Button) common_popup
				.findViewById(R.id.button_cancle);
		vButtonAddRemind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
		// RelativeLayout

		RelativeLayout vParView = (RelativeLayout) common_popup
				.findViewById(R.id.addGridViewPopLayout);
		vParView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// popupWindow.dismiss();
			}
		});

		LinearLayout vShowInfo = (LinearLayout) common_popup
				.findViewById(R.id.show_info_view);
		vShowInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
    
    public void goDetail()
    {
    	questionpot[0] = view1.topchooseid;
		questionpot[1] = view1.bottomchooseid;
		
		questionpot[2] = view2.topchooseid;
		questionpot[3] = view2.bottomchooseid;
		
		questionpot[4] = view3.topchooseid;
		questionpot[5] = view3.bottomchooseid;
		
		questionpot[6] = view4.topchooseid;
		questionpot[7] = view4.bottomchooseid;
		
		questionpot[8] = view5.topchooseid;
		questionpot[9] = view5.bottomchooseid;
		
		questionpot[10] = view6.topchooseid;
		questionpot[11] = view6.bottomchooseid;
		
		questionpot[12] = view7.topchooseid;
		questionpot[13] = view7.bottomchooseid;
		
		questionpot[14] = view8.topchooseid;
		questionpot[15] = view8.bottomchooseid;
		
		for (int i = 0; i < questionpot.length; i++) {
			Log.d("xmx","question:"+questionpot[i]);
			if(questionpot[i] == 2){
				questionpot[i] = -1;
			}
			else if(questionpot[i] == 1){
				questionpot[i] = 0;
			}
			else if(questionpot[i] == 0){
				questionpot[i] = 1;
			}
			Log.d("xmx","question:"+questionpot[i]);
		}
		
		int x = 0, y = 0;
		for (int i = 0; i < questionpot.length; i++) {
			if(i%2==0){
				x += questionpot[i];
			}else{
				y += questionpot[i];
			}
		}
		Log.d("xmx","check out:"+x+","+y);
		
		int fenggeId = 0;
		if(x>2 && y>2){
			fenggeId = 0;
		}
		if((x>-3 && x<3) && y>2){
			fenggeId = 1;
		}
		if(x<-2 && y>2){
			fenggeId = 2;
		}
		if(x>2 && (y>-3 && y<3)){
			fenggeId = 3;
		}
		if((x>-3 && x<3) && (y>-3 && y<3)){
			fenggeId = 4;
		}
		if(x<-2 && (y>-3 && y<3)){
			fenggeId = 5;
		}
		
		if(x>2 && y<-2){
			fenggeId = 6;
		}
		if((x>-3 && x<3) && (y<-2)){
			fenggeId = 7;
		}
		if(x<-2 && y<-2){
			fenggeId = 8;
		}
		
		
		//ImageUtil.clearCache();
		Intent intent = new Intent(this,StyleIntroActivity.class);
		intent.putExtra("posi", fenggeId);
		String title=this.getResources().getStringArray(R.array.str_arry_fengge)[fenggeId];
		intent.putExtra("title", title);
		startActivity(intent);
		this.finish();
    }
    int j=0;
    int currPage=0;
    boolean isTrue;
    //完成选择后弹出对话框
    public void showConfirm(){

//    	if((currPageNum == pagecount) && view8.topischoose && view8.bottomischoose)
//    	{
//    		 
//    		popConfirm(findViewById(R.id.activity_layout),"测试结束，点击确认查看测试结果");
//    	}
    	j++;
    	if(currPageNum==7){
    		currPage=1;
    	}
    	Log.i("---doop----", "number==="+number+";currPageNum=="+currPageNum+";j=="+j);
    	if(j>=8&&currPage==1&&view8.topischoose && view8.bottomischoose){
    		popConfirm(findViewById(R.id.activity_layout),"测试结束，点击确认查看测试结果");
    	}
    }
    
    // 指引页面数据适配器
    private class GuidePageAdapter extends PagerAdapter {  
  	  
        @Override  
        public int getCount() {  
            return pageViews.size() - number;  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
        	((ViewPager) arg0).destroyDrawingCache();
            ((ViewPager) arg0).removeView(pageViews.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
        	
            ((ViewPager) arg0).addView(pageViews.get(arg1)); 
            
            saveOpenNum[arg1] = 1;
            return pageViews.get(arg1); 
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }

		@Override
		public void notifyDataSetChanged() {
			// TODO Auto-generated method stub
			super.notifyDataSetChanged();
		}  
        
        
    } 
    
    // 指引页面更改事件监听器
    private class GuidePageChangeListener implements OnPageChangeListener {  
    	  
        
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
        }  
  
       
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
  
        }  
  
        
        public void onPageSelected(int arg0) {  
        	currPageNum = arg0;
        	isSelectStrings[arg0]="true";
        	Log.i("---doop----", "isSelectStrings==="+arg0+"---"+isSelectStrings[arg0]);
            for (int i = 0; i < imageViews.length; i++) {  
                if((pageViews.size() - number) - 1 < pagecount)
                	imageViews[i].setBackgroundResource(R.drawable.x_text_progress_notyeticon); 	//锁定
                
                if (i < (pageViews.size() - number))
            		imageViews[i].setBackgroundResource(R.drawable.x_text_progress_doneicon);  		//开锁
                
                imageViews[arg0].setBackgroundResource(R.drawable.x_text_progress_doingicon);		//当前
            }
            
        }
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//    	Log.d("xmx","Cefengge rec:"+resultCode+"");
//		super.onActivityResult(requestCode, resultCode, data);
//		if (IApplication.SHOW_ACTIVITY == IApplication.ACTIVITY_MAIN) {
//			setResult(IApplication.ACTIVITY_MAIN); 
//			finish();
//		}
//		if (IApplication.SHOW_ACTIVITY == IApplication.ACTIVITY_NICE_STYLE) {
//			finish();
//		}
//		
//	}

/*
	@Override
	protected boolean onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.ibtn_header_right:
			Intent aimeise=new Intent(this,NineStyleActivity.class);
			String title=this.getString(R.string.str_title_jiufengge);
			aimeise.putExtra("title", title);
			startActivity(aimeise);
			return true;
		case R.id.ibtn_header_left:
			goBack();
			return true;

		default:
			break;
		}
		return false;
	}
*/

}