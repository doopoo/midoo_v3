/** 
 * @ClassName: LoginActivity 
 * @Description: 
 * @author eastedge.com.cn
 * @date 2013-6-3 下午2:40:21 
 * 
 */
package com.midooo.userCenter;

import java.io.File;
import java.net.ConnectException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.midooo.lib.BaseFramework.midooRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @{# LoginActivity.java Create on 2013-6-3 下午2:40:21
 * @Description:
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>
 */

public class autoLogin {



	
	public static void login() {
		RequestParams params = new RequestParams();

		params.put("username", "doopoo");
		params.put("pwd", "123456");
	
		midooRequest.post("/user/index/login", params, new JsonHttpResponseHandler(){
			

			@Override
			public void onStart() {
				Log.w("---doop---", "---onStart---");
			}
			@Override
			public void onFinish() {
				Log.w("---doop---", "---onFinish---");
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.w("---doop---", "---onFailure---"+responseString+"------"+statusCode+"------"+throwable.getLocalizedMessage());
				
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.w("---doop---", "---onSuccess---"+response.toString()+"------"+statusCode+"------");
				
			}
		});
		
		/*
		AsyncHttpClient client = new AsyncHttpClient();
		client.post("/user/index/login", params, new TextHttpResponseHandler(){

			@Override
			public void onStart() {
				Log.w("---doop---", "---onStart---");
			}
			@Override
			public void onFinish() {
				Log.w("---doop---", "---onFinish---");
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.w("---doop---", "---onFailure---"+responseString+"------"+statusCode+"------"+throwable.getLocalizedMessage());
				
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Log.w("---doop---", "---onSuccess---"+responseString+"------"+statusCode+"------");
				
			}


		});*/
	}


}
