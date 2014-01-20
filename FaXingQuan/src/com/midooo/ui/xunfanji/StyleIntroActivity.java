/** 
 * @ClassName: StyleIntroActivity 
 * @Description: 
 * @author eastedge.com.cn
 * @date 2013-6-5 下午5:21:24 
 * 
 */ 
package com.midooo.ui.xunfanji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.midooo.ui.activitys.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**    
 * @{# StyleIntroActivity.java Create on 2013-6-5 下午5:21:24      
 * @Description: 
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>      
 */

public class StyleIntroActivity extends Activity{
	private Button ibtn_styleintro_faxingcankao;
	private Button ibtn_styleintro_aimeise;
	private Button ibtn_styleintro_taofaxing;
	private ImageView iv_xunfanji_img1,iv_xunfanji_img2,iv_xunfanji_img3;
	private ImageView iv_xunfanji_sehao_1,iv_xunfanji_sehao_2,iv_xunfanji_sehao_3;
	private TextView iv_xunfanji_biaoqian_1,iv_xunfanji_biaoqian_2,iv_xunfanji_biaoqian_3,iv_xunfanji_biaoqian_4,iv_xunfanji_biaoqian_5,iv_xunfanji_biaoqian_6;
	private TextView txt_xunfanji_name1,txt_xunfanji_name2,txt_xunfanji_name3;
	private TextView txt_xunfanji_fenggetedian,txt_xunfanji_faxingtedian,txt_xunfanji_ranfajianyi,txt_xunfanji_tangfajianyi;
	private TextView txt_xunfanji_sehao_1,txt_xunfanji_sehao_2,txt_xunfanji_sehao_3;
	private String title;
	private String fenggetedian;
	private String faxingtedian;
	private String[] fenggebiaoqians;
	private String ranfajianyi;
	private String[] midousehaotuijians;
	private String[] txt_daibiaomingxings;
	private String[] img_daibiaomingxing;
	private int posi;
	
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		//this.ibtn_header_right.setImageResource(R.drawable.header_right_ninestyle_btn_selected);
		setContentView(R.layout.styleintro);
	

		ibtn_styleintro_faxingcankao=(Button) findViewById(R.id.ibtn_styleintro_faxingcankao);
		ibtn_styleintro_aimeise=(Button) findViewById(R.id.ibtn_styleintro_aimeise);
		ibtn_styleintro_taofaxing=(Button) findViewById(R.id.ibtn_styleintro_taofaxing);
		iv_xunfanji_img1=(ImageView) findViewById(R.id.iv_xunfanji_img1);
		iv_xunfanji_img2=(ImageView) findViewById(R.id.iv_xunfanji_img2);
		iv_xunfanji_img3=(ImageView) findViewById(R.id.iv_xunfanji_img3);
		iv_xunfanji_sehao_1=(ImageView) findViewById(R.id.iv_xunfanji_sehao_1);
		iv_xunfanji_sehao_2=(ImageView) findViewById(R.id.iv_xunfanji_sehao_2);
		iv_xunfanji_sehao_3=(ImageView) findViewById(R.id.iv_xunfanji_sehao_3);
		iv_xunfanji_biaoqian_1=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_1);
		iv_xunfanji_biaoqian_2=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_2);
		iv_xunfanji_biaoqian_3=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_3);
		iv_xunfanji_biaoqian_4=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_4);
		iv_xunfanji_biaoqian_5=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_5);
		iv_xunfanji_biaoqian_6=(TextView) findViewById(R.id.iv_xunfanji_biaoqian_6);
		txt_xunfanji_name1=(TextView) findViewById(R.id.txt_xunfanji_name1);
		txt_xunfanji_name2=(TextView) findViewById(R.id.txt_xunfanji_name2);
		txt_xunfanji_name3=(TextView) findViewById(R.id.txt_xunfanji_name3);
		txt_xunfanji_fenggetedian=(TextView) findViewById(R.id.txt_xunfanji_fenggetedian);
		txt_xunfanji_faxingtedian=(TextView) findViewById(R.id.txt_xunfanji_faxingtedian);
		txt_xunfanji_ranfajianyi=(TextView) findViewById(R.id.txt_xunfanji_ranfajianyi);
		txt_xunfanji_tangfajianyi=(TextView) findViewById(R.id.txt_xunfanji_tangfajianyi);
		txt_xunfanji_sehao_1=(TextView) findViewById(R.id.txt_xunfanji_sehao_1);
		txt_xunfanji_sehao_2=(TextView) findViewById(R.id.txt_xunfanji_sehao_2);
		txt_xunfanji_sehao_3=(TextView) findViewById(R.id.txt_xunfanji_sehao_3);
		
		
		

		 title=this.getIntent().getStringExtra("title");
			if(title!=null){
				//this.txt_title.setText(title);
			}
		//this.txt_title.setText(title);
		 posi=this.getIntent().getIntExtra("posi", 0);
		 txt_daibiaomingxings=this.getResources().getStringArray(R.array.str_arry_txt_daibiaomingxing)[posi].split("_");
		 fenggebiaoqians=this.getResources().getStringArray(R.array.str_arry_fenggebiaoqian)[posi].split("_");
		 midousehaotuijians=this.getResources().getStringArray(R.array.str_arry_midousehaotuijian)[posi].split("_");
		 fenggetedian=this.getResources().getStringArray(R.array.str_arry_fenggetedian)[posi];
		 faxingtedian=this.getResources().getStringArray(R.array.str_arry_faxingtedian)[posi];
		 ranfajianyi=this.getResources().getStringArray(R.array.str_arry_ranfajianyi)[posi];
			txt_xunfanji_name1.setText(txt_daibiaomingxings[0]);
			txt_xunfanji_name2.setText(txt_daibiaomingxings[1]);
			txt_xunfanji_name3.setText(txt_daibiaomingxings[2]);
			
			txt_xunfanji_sehao_1.setText(midousehaotuijians[0]);
			txt_xunfanji_sehao_2.setText(midousehaotuijians[1]);
			txt_xunfanji_sehao_3.setText(midousehaotuijians[2]);
			txt_xunfanji_fenggetedian.setText(fenggetedian);
			txt_xunfanji_faxingtedian.setText(faxingtedian);
			txt_xunfanji_ranfajianyi.setText(ranfajianyi);
			txt_xunfanji_tangfajianyi.setText(R.string.str_tangfajianyi_content);
			iv_xunfanji_biaoqian_1.setText(fenggebiaoqians[0]);
			iv_xunfanji_biaoqian_2.setText(fenggebiaoqians[1]);
			iv_xunfanji_biaoqian_3.setText(fenggebiaoqians[2]);
			iv_xunfanji_biaoqian_4.setText(fenggebiaoqians[3]);
			iv_xunfanji_biaoqian_5.setText(fenggebiaoqians[4]);
			iv_xunfanji_biaoqian_6.setText(fenggebiaoqians[5]);
		 switch (posi) {
		case 0:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_1_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_1_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_1_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_4_5);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_5_5);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_5_4);
			break;
		case 1:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_2_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_2_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_2_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_2_5);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_2_4);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_3_4);
			break;
		case 2:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_3_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_3_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_3_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_4_4);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_2_2);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_2_5);
			break;
		case 3:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_4_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_4_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_4_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_2_3);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_6_4);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_6_3);
			break;
		case 4:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_5_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_5_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_5_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_4_3);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_5_3);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_5_4);
			break;
		case 5:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_6_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_6_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_6_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_6_1);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_4_1);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_4_3);
			break;
		case 6:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_7_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_7_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_7_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_6_2);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_5_2);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_6_1);
			break;
		case 7:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_8_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_8_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_8_2);
			
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_3_1);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_3_2);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_5_1);
			break;
		case 8:
			iv_xunfanji_img1.setImageResource(R.drawable.styleintro_9_0);
			iv_xunfanji_img2.setImageResource(R.drawable.styleintro_9_1);
			iv_xunfanji_img3.setImageResource(R.drawable.styleintro_9_2);
			
			iv_xunfanji_sehao_1.setImageResource(R.drawable.color_2_1);
			iv_xunfanji_sehao_2.setImageResource(R.drawable.color_3_3);
			iv_xunfanji_sehao_3.setImageResource(R.drawable.color_3_1);
			break;


		default:
			break;
		}

/*
			ibtn_styleintro_faxingcankao.setOnClickListener(this);
			ibtn_styleintro_aimeise.setOnClickListener(this);
			ibtn_styleintro_taofaxing.setOnClickListener(this);
			
*/		
	
	}
	

	
/*

	@Override
	protected boolean onClickEvent(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.ibtn_styleintro_faxingcankao:
			intent=new Intent(this,HairReferenceActivity.class);
			intent.putExtra("where", "styleintro");
			intent.putExtra("summary", title);
			intent.putExtra("posi", posi);
			startActivity(intent);
			break;
		case R.id.ibtn_styleintro_aimeise:
			intent=new Intent(this,AimeiseActivity.class);
			startActivity(intent);
			break;
		case R.id.ibtn_styleintro_taofaxing:
			intent=new Intent(this,PullToRefreshSampleActivity.class);
			startActivity(intent);
			break;
		case R.id.ibtn_header_right:
			Intent aimeise=new Intent(this,NineStyleActivity.class);
			String title=this.getString(R.string.str_title_jiufengge);
			aimeise.putExtra("title", title);
			startActivity(aimeise);
			this.finish();
			return true;
		default:
			break;
		}

		return false;
	}
*/


}
