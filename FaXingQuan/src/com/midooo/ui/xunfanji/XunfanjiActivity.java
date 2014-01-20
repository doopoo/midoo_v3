/** 
 * @ClassName: XunfanjiActivity 
 * @Description: 
 * @author eastedge.com.cn
 * @date 2013-6-3 上午11:49:46 
 * 
 */ 
package com.midooo.ui.xunfanji;


import com.midooo.lib.BaseFramework.BaseActivity;
import com.midooo.ui.activitys.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**    
 * @{# XunfanjiActivity.java Create on 2013-6-3 上午11:49:46      
 * @Description: 寻范记
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>      
 */

public class XunfanjiActivity extends BaseActivity implements OnClickListener {


	private LinearLayout iv_xunfanji_select;
	private LinearLayout iv_xunfanji_test;
	
	public XunfanjiActivity() {
		super(R.string.str_title_xunfanji);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.txt_title.setText(R.string.str_title_xunfanji);
		this.ibtn_header_right.setVisibility(View.GONE);
		setContentView(R.layout.xunfanji);
		iv_xunfanji_select=(LinearLayout) findViewById(R.id.iv_xunfanji_select);
		iv_xunfanji_test=(LinearLayout) findViewById(R.id.iv_xunfanji_test);
		iv_xunfanji_select.setOnClickListener(this);
		iv_xunfanji_test.setOnClickListener(this);

	}
	






	@Override
	public void onClick(View v) {

		
		Log.i("---doop----", "------onClickEvent---------"+v.getId()+"------");
		switch (v.getId()) {
		case R.id.iv_xunfanji_select:
			Intent aimeise=new Intent(this,NineStyleActivity.class);
			String title=this.getString(R.string.str_title_jiufengge);
			aimeise.putExtra("title", title);
			startActivity(aimeise);
			break;
		case R.id.iv_xunfanji_test:
			Intent cefengge=new Intent(this,CefenggeActivity.class);
			startActivity(cefengge);
			break;

		default:
			break;
		}
		
	
	}

}
