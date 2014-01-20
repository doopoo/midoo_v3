package com.midooo.ui.xunfanji;

import com.midooo.ui.activitys.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CefenggeModelView extends View {
	
	private Context mContext;
	
	private View mView;
	
	private LayoutInflater inflater;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	private ImageView theaderchoose_img, tchooseone_imageButton, tchoosetwo_imageButton, tchoosethr_imageButton, timageview[];
	
	private TextView theaderchoose_title, tchooseone_text, tchoosetow_text, tchoosethr_text;
	
	private ImageView bheaderchoose_img, bchooseone_imageButton, bchoosetwo_imageButton, bchoosethr_imageButton, bimageview[];
	
	private TextView bheaderchoose_title, bchooseone_text, bchoosetow_text, bchoosethr_text;
	
	public boolean topischoose = false, bottomischoose = false;
	
	public int topchooseid, bottomchooseid;
	
	private boolean completed = false;
	
	
	  DisplayImageOptions options;
	public CefenggeModelView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		this.mContext = context;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds=false;
		o.inSampleSize=3;
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.waiting_header)
		.showImageForEmptyUri(R.drawable.waiting_header)
		.cacheOnDisc(true)
		.cacheInMemory(true)
		.decodingOptions(o)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.resetViewBeforeLoading(true)
		.displayer(new SimpleBitmapDisplayer()) 
		.build();	
		inflater = LayoutInflater.from(mContext);
		
		mView = inflater.inflate(R.layout.activity_fanzhengduan_itemmodel, null);
		
		timageview = new ImageView[3];
		
		bimageview = new ImageView[3];
		
		initTopControl();
		
		initBottomControl();
	}
	
	private void initTopControl(){
		
		theaderchoose_title = (TextView) mView.findViewById(R.id.theaderchoose_text);
		
		theaderchoose_img = (ImageView) mView.findViewById(R.id.theaderchoose_image);
		
		tchooseone_imageButton = (ImageView) mView.findViewById(R.id.tchooseone_imageButton);
		tchooseone_imageButton.setId(0);
		timageview[0] = tchooseone_imageButton;
		tchooseone_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		tchooseone_text = (TextView) mView.findViewById(R.id.tchooseone_text);
		
		tchoosetwo_imageButton = (ImageView) mView.findViewById(R.id.tchoosetwo_imageButton);
		tchoosetwo_imageButton.setId(1);
		timageview[1] = tchoosetwo_imageButton;
		tchoosetwo_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		tchoosetow_text = (TextView) mView.findViewById(R.id.tchoosetwo_text);
		
		tchoosethr_imageButton = (ImageView) mView.findViewById(R.id.tchoosethr_imageButton);
		tchoosethr_imageButton.setId(2);
		timageview[2] = tchoosethr_imageButton;
		tchoosethr_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		tchoosethr_text = (TextView) mView.findViewById(R.id.tchoosethr_text);
	}
	
	public void initTopData(CharSequence[] data, int[] imglist){
		theaderchoose_title.setText(data[0]);
		
		tchooseone_text.setText(data[1]);
		
		tchoosetow_text.setText(data[2]);
		
		tchoosethr_text.setText(data[3]);
		imageLoader.displayImage("drawable://"+imglist[0], tchooseone_imageButton, options);
		imageLoader.displayImage("drawable://"+imglist[1], tchoosetwo_imageButton, options);
		imageLoader.displayImage("drawable://"+imglist[2], tchoosethr_imageButton, options);
//		tchooseone_imageButton.setImageResource(imglist[0]);
		
//		tchoosetwo_imageButton.setImageResource(imglist[1]);
		
//		tchoosethr_imageButton.setImageResource(imglist[2]);
	}
	
	
	private void initBottomControl(){
		bheaderchoose_title = (TextView) mView.findViewById(R.id.bheaderchoose_text);
		
		bheaderchoose_img = (ImageView) mView.findViewById(R.id.bheaderchoose_image);
		
		bchooseone_imageButton = (ImageView) mView.findViewById(R.id.bchooseone_imageButton);
		bchooseone_imageButton.setId(3);
		bimageview[0] = bchooseone_imageButton;
		bchooseone_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		bchooseone_text = (TextView) mView.findViewById(R.id.bchooseone_text);
		
		bchoosetwo_imageButton = (ImageView) mView.findViewById(R.id.bchoosetwo_imageButton);
		bchoosetwo_imageButton.setId(4);
		bimageview[1] = bchoosetwo_imageButton;
		bchoosetwo_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		bchoosetow_text = (TextView) mView.findViewById(R.id.bchoosetwo_text);
		
		bchoosethr_imageButton = (ImageView) mView.findViewById(R.id.bchoosethr_imageButton);
		bchoosethr_imageButton.setId(5);
		bimageview[2] = bchoosethr_imageButton;
		bchoosethr_imageButton.setOnClickListener(new ImageViewOnClickListener());
		
		bchoosethr_text = (TextView) mView.findViewById(R.id.bchoosethr_text);
	}
	
	public void initBottomData(CharSequence[] data, int[] imglist){
		bheaderchoose_title.setText(data[0]);
		
		bchooseone_text.setText(data[1]);
		
		bchoosetow_text.setText(data[2]);
		
		bchoosethr_text.setText(data[3]);
		imageLoader.displayImage("drawable://"+imglist[0], bchooseone_imageButton, options);
		imageLoader.displayImage("drawable://"+imglist[1], bchoosetwo_imageButton, options);
		imageLoader.displayImage("drawable://"+imglist[2], bchoosethr_imageButton, options);
//		bchooseone_imageButton.setImageResource(imglist[0]);
		
//		bchoosetwo_imageButton.setImageResource(imglist[1]);
		
//		bchoosethr_imageButton.setImageResource(imglist[2]);
	}
	
	
	private class ImageViewOnClickListener implements OnClickListener{

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			if(id < 3){
				for(int i = 0; i < timageview.length; i++){
					if(i == id){
						timageview[i].setBackgroundResource(R.drawable.x_test_key_selectedbg);
						topischoose = true;
						topchooseid = id;
						theaderchoose_img.setImageResource(R.drawable.x_test_doneicon);
					}else{
						timageview[i].setBackgroundResource(R.drawable.x_test_keybg);
					}
				}
			}else{
				for(int i = 0; i < bimageview.length; i++){
					if(i == id - 3){
						bimageview[i].setBackgroundResource(R.drawable.x_test_key_selectedbg);
						bottomischoose = true;
						bottomchooseid = id - 3;
						bheaderchoose_img.setImageResource(R.drawable.x_test_doneicon);
					}else{
						bimageview[i].setBackgroundResource(R.drawable.x_test_keybg);
					}
				}
			}
			 	
			if(topischoose && bottomischoose){
				
				((CefenggeActivity)mContext).autofillingPage();
				
				((CefenggeActivity)mContext).showConfirm();
				
			}

		}
		
	}
	
	public View getView(){
		return mView;
	}

}
