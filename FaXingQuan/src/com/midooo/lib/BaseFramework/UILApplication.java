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
import android.os.Build;
import android.os.StrictMode;

import com.loopj.android.http.PersistentCookieStore;
import com.midooo.ui.activitys.Constants.Config;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */

import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Map;


public class UILApplication extends Application {
	
	private static UILApplication instance;
	
	public static PersistentCookieStore midooCookieStore;
	public ImageLoader imageLoader;
	

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
		super.onCreate();

		initImageLoader(getApplicationContext());
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
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}