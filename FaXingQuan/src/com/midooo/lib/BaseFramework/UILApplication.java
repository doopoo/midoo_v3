/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.midooo.lib.BaseFramework;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.os.StrictMode;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import com.midooo.ui.activitys.Constants.Config;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */

import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;


public class UILApplication extends Application {
	
	private static UILApplication instance;
	
	public static PersistentCookieStore midooCookieStore;
	public ImageLoader imageLoader;
	public FIFOLimitedMemoryCache cache;
	public AsyncHttpClient client;
	
	private SharedPreferences sp;

	public static UILApplication getInstance() {
		return instance;
	}
	
	/**
	 * @return the imageLoader
	 */
	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	/**
	 * @param imageLoader the imageLoader to set
	 */
	public void setImageLoader(
			ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}
	
	public static PersistentCookieStore getCookieStore() {
		return midooCookieStore;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		if (Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		}
		midooCookieStore = new PersistentCookieStore(getApplicationContext());//

		client = new AsyncHttpClient();
		ThreadPoolExecutor threadPool = HttpThreadPool.getInstance();
		client.setThreadPool(threadPool);
		
		
//		ImageCacheParams cacheParams=new ImageCacheParams("imgcache");
//		cache=new ImageCache(this, cacheParams);
//		 cache=new View2DCache(ConstantPool.lruCacheSize);
//		cache= new WeakMemoryCache();
		cache= new FIFOLimitedMemoryCache(8* 1024 * 1024);
		sp = getSharedPreferences("midou", MODE_PRIVATE);


		
		
		
		instance = this;
		initImageLoader(getApplicationContext());
		
		super.onCreate();
		

	}

	//-----------------------
	/*
	  private static final HandlerThread b = new HandlerThread("MyApplication");
	  private static final Handler c = new Handler(b.getLooper());
	  Map a = new HashMap();

	  static
	  {
	    b.start();
	  }

	  public void a(Runnable paramRunnable)
	  {
	    c.post(paramRunnable);
	  }
  */
	  //--------------------------------
	public void initImageLoader(Context context) {


		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		//.showStubImage(R.drawable.waiting)
		//.showImageForEmptyUri(R.drawable.waiting)
		.cacheOnDisc(true)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.resetViewBeforeLoading(true)
		.displayer(new SimpleBitmapDisplayer()) 
		.build();	
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.threadPoolSize(3)
				.discCacheExtraOptions(500, 900, CompressFormat.JPEG, 75, null)
//				.memoryCacheSize(10* 1024 * 1024) 
				.memoryCache(cache)
				.defaultDisplayImageOptions(options)
//				.memoryCacheExtraOptions(500, 900)
				
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				//.tasksProcessingOrder(QueueProcessingType.LIFO)
				.tasksProcessingOrder(QueueProcessingType.FIFO) // Not
				.writeDebugLogs() // Remove for release app	
				
				.imageDownloader(new BaseImageDownloader(this)) // default		
				.build();
		
		imageLoader=ImageLoader.getInstance();
		// Initialize ImageLoader with configuration.
		imageLoader.init(config);

		setImageLoader(imageLoader);
	}
	
	
	@Override
	public void onLowMemory() {
		ImageUtil.clearCache();
		
	}


	public AsyncHttpClient getClient() {
		return client;
	}

	public void setClient(AsyncHttpClient client) {
		this.client = client;
	}
	
	
	
	
	
	
	

	public void saveValue(String key, String value) {
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void saveValue(String key, boolean value) {
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	public void saveValue(String key, int value) {
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public void saveValue(String key, long value) {
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public long getLongValue(String key) {
		long def=0;
		return sp.getLong(key, def);
	}

	public boolean getBooleanValue(String key) {
		return sp.getBoolean(key, false);
	}

	public String getStrValue(String key) {
		return sp.getString(key, null);
	}
	public int getIntValue(String key) {
		return sp.getInt(key, -1);
	}	
	
	
	public boolean isLogined() {
		String username = sp.getString("username", null);
		String pwd = sp.getString("password", null);
		if (username == null && pwd == null) {
			return false;
		}
		return true;
	}

	
	public SharedPreferences getSp() {
		return sp;
	}
	
	
	
	
	
	
	
}