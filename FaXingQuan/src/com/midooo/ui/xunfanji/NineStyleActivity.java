package com.midooo.ui.xunfanji;

import com.midooo.stylist.v3.adapter.AimeiseAdapter;
import com.midooo.ui.activitys.Constants;
import com.midooo.ui.activitys.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 首页第二个条目：爱美色
 * 
 * @author xushengxing
 * 
 */
public class NineStyleActivity extends Activity {

	private GridView gv_aimeise_home;
	private String title;
	
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		title=this.getString(R.string.str_title_jiufengge);
		//this.txt_title.setText(title);
		//this.ibtn_header_right.setVisibility(View.GONE);
		setContentView(R.layout.jiu_feng_ge);

		gv_aimeise_home = (GridView) findViewById(R.id.jiu_fenge);
	

		title=this.getIntent().getStringExtra("title");
		if(title!=null){
			//this.txt_title.setText(title);
		}
	
		processLogic();
		
	}
	


	protected void processLogic() {
		gv_aimeise_home.setAdapter(new AimeiseAdapter(this,Constants.options));
		gv_aimeise_home.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				switch (arg2) {
				case 0:// 可爱
//					Intent intent = new Intent(AimeiseActivity.this, AimeiseItems.class);
//					AimeiseActivity.this.startActivity(intent);
					selectItem("可爱风格",title,arg2);
					break;
				case 1:// 时尚
					selectItem("时尚风格",title,arg2);
					break;
				case 2:// 青春
					selectItem("青春风格",title,arg2);
					break;
					
					
				case 3:// 优雅
					selectItem("优雅风格",title,arg2);
					break;
				case 4:// 柔美
					selectItem("柔美风格",title,arg2);
					break;
				case 5:// 知性
					selectItem("知性风格",title,arg2);
					break;
				case 6:// 浪漫
					selectItem("浪漫风格",title,arg2);
					break;
				case 7:// 华丽
					selectItem("华丽风格",title,arg2);
					break;
				case 8:// 摩登
					selectItem("摩登风格",title,arg2);
					break;

				default:
					break;
				}

			}
		});
	}

	
	
/**
 * 点击九宫格开启新页面
 * 传入两个数据：1，照片；2，模块小说明（照片下的小图片）
 */
	private void selectItem(String summary,String title,int posi) {
		
		if(title!=null&&title.equals(this.getString(R.string.str_title_jiufengge))){
			Intent intent = new Intent(this, StyleIntroActivity.class);
			intent.putExtra("title", summary);
			intent.putExtra("posi", posi);
			startActivity(intent);
		}
	}



		 }
