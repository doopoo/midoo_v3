// Static wrapper library around AsyncHttpClient
package com.midooo.lib.BaseFramework;


import com.loopj.android.http.*;

public class midooRequest {
    private static AsyncHttpClient client = new AsyncHttpClient();
    
    

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		client.setCookieStore(UILApplication.getCookieStore());
		if(params==null) client.post(getAbsoluteUrl(url), responseHandler);
		else client.post(getAbsoluteUrl(url), params, responseHandler);
    }    
    
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.setCookieStore(UILApplication.getCookieStore());
		if(params==null) client.post(getAbsoluteUrl(url), responseHandler);
		else client.post(getAbsoluteUrl(url), params, responseHandler);
    }   
    

    public static void post(String url, RequestParams params, TextHttpResponseHandler responseHandler) {
		client.setCookieStore(UILApplication.getCookieStore());
		if(params==null) client.post(getAbsoluteUrl(url), responseHandler);
		else client.post(getAbsoluteUrl(url), params, responseHandler);
    }
  
    
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    	client.setCookieStore(UILApplication.getCookieStore()); 
    	client.get(getAbsoluteUrl(url), params, responseHandler);
    }


    
 

    private static String getAbsoluteUrl(String relativeUrl) {
    	return Const.BASE_URL + relativeUrl;
    }
  

    /* 

    String[] allowedTypes = new String[] { "image/png" };
    client.get("http://www.example.com/image.png", new BinaryHttpResponseHandler(allowedTypes) {
        @Override
        public void onSuccess(byte[] imageData) {
            // Successfully got a response
        }

        @Override
        public void onFailure(Throwable e, byte[] imageData) {
            // Response failed :(
        }
    });    
    
  
    RequestParams params = new RequestParams();
    params.put("username", "james");
    params.put("password", "123456");
    params.put("email", "my@email.com");
    params.put("profile_picture", new File("pic.jpg")); // Upload a File
    params.put("profile_picture2", someInputStream); // Upload an InputStream
    params.put("profile_picture3", new ByteArrayInputStream(someBytes)); // Upload some bytes

    client.post("http://myendpoint.com", params, responseHandler);
*/    
    
}