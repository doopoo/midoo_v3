package com.midooo.ui.aimeise;


import com.midooo.lib.BaseFramework.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AimeiseItems extends BaseActivity {

	public AimeiseItems(int titleRes) {
		super(titleRes);
		// TODO Auto-generated constructor stub
	}

	private Button bt_short_fair;
	private Button bt_zhong_fair;
	private Button bt_long_fair;
	private ImageView iv_item_picture;
	private TextView tv_item_summary;

	/*

	@Override
	protected void loadViewLayout() {
		this.txt_title.setText(R.string.str_title_faxingcankao);
		this.ibtn_header_right.setVisibility(View.GONE);
		setContentView(R.layout.aimeiseitems);
	}

	@Override
	protected void findViewById() {

		bt_short_fair = (Button) findViewById(R.id.bt_short_fair);
		bt_zhong_fair = (Button) findViewById(R.id.bt_zhong_fair);
		bt_long_fair = (Button) findViewById(R.id.bt_long_fair);
		iv_item_picture = (ImageView) findViewById(R.id.iv_item_picture);
		tv_item_summary = (TextView) findViewById(R.id.tv_item_summary);
	}

	@Override
	protected void setUpView() {

	}

	@Override
	protected void setListener() {
		

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_short_fair:
			
			break;
		case R.id.bt_zhong_fair:
			
			break;
		case R.id.bt_long_fair:
			
			break;
		case R.id.iv_item_picture:
			
			break;

		default:
			break;
		}
	}
	@Override
	protected boolean onClickEvent(View v) {
		return false;
	}

	@Override
	protected void processLogic() {
		//接受数据并设置入当前页面
		Bundle myBundle = this.getIntent().getExtras();
		int picture = myBundle.getInt("picture");
		String summary = myBundle.getString("summary");
		iv_item_picture.setImageResource(picture);
		tv_item_summary.setText(summary);
	}
	/**
	 * 页面的滑动方法
	 */

}
