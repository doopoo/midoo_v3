package com.midooo.stylist.v3.adapter;

import com.midooo.ui.activitys.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 爱美色数据适配器
 * 
 * @author xushengxing
 * 
 */
public class AimeiseAdapter extends BaseAdapter {

	public static String[] names = { "可爱", "时尚", "青春", "优雅", "柔美", "知性", "浪漫",
			"华丽", "摩登" };
	public static int[] icons = { R.drawable.xunfangji_9fengge_p_01_selected,
		R.drawable.xunfangji_9fengge_p_02_selected, R.drawable.xunfangji_9fengge_p_03_selected,
			R.drawable.xunfangji_9fengge_p_04_selected, R.drawable.xunfangji_9fengge_p_05_selected,
			R.drawable.xunfangji_9fengge_p_06_selected, R.drawable.xunfangji_9fengge_p_07_selected,
			R.drawable.xunfangji_9fengge_p_08_selected, R.drawable.xunfangji_9fengge_p_09_selected, };
	private Context context;
	private SharedPreferences sp;
	private LayoutInflater inflater;
	private DisplayImageOptions options;
	public AimeiseAdapter(Context context,DisplayImageOptions options) {
		this.context = context;
		this.options=options;
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.adapter_aimeise, null);
			holder = new ViewHolder();
			holder.iv_aimeise_adapter_icon = (ImageView) convertView
					.findViewById(R.id.iv_aimeise_adapter_icon);
			holder.tv_aimeise_adapter_name = (TextView) convertView
					.findViewById(R.id.tv_aimeise_adapter_name);
//			IApplication.getInstance().getImageLoader().displayImage("drawable://"+icons[position], holder.iv_aimeise_adapter_icon, options);
			holder.iv_aimeise_adapter_icon.setImageResource(icons[position]);
			holder.tv_aimeise_adapter_name.setText(names[position]);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView iv_aimeise_adapter_icon;
		TextView tv_aimeise_adapter_name;
	}

}
