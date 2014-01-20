/** 
 * @ClassName: ImageUtil 
 * @Description: 
 * @author eastedge.com.cn
 * @date 2013-6-14 下午4:34:37 
 * 
 */ 
package com.midooo.lib.BaseFramework;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.util.Random;

import com.nostra13.universalimageloader.core.ImageLoader;



import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Base64;

/**    
 * @{# ImageUtil.java Create on 2013-6-14 下午4:34:37      
 * @Description: 
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>      
 */

public class ImageUtil {

    
    /**
	 * 质量压缩方法
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {   
		  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中   
        int options = 100;   
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩          
            baos.reset();//重置baos即清空baos   
            
            options -= 10;//每次都减少10 
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中   
             
        }   
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中   
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片   
       
        return bitmap;   
    } 
	/**
	 * 图片按比例大小压缩方法（根据Bitmap图片压缩）：
	 * @param image
	 * @return
	 */
    public static Bitmap comp(Bitmap image,float width ,float height) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();         
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出    
            baos.reset();//重置baos即清空baos  
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中  
        }  
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());  
        BitmapFactory.Options newOpts = new BitmapFactory.Options();  
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
        newOpts.inJustDecodeBounds = true;  
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
        newOpts.inJustDecodeBounds = false;  
        int w = newOpts.outWidth;  
        int h = newOpts.outHeight;  
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
        float ww = width;//这里设置宽度为480f  
        float hh = height;//这里设置高度为800f  
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
        int be = 1;//be=1表示不缩放  
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
            be = (int) (newOpts.outWidth / ww);  
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
            be = (int) (newOpts.outHeight / hh);  
        }  
        if (be <= 0)  
            be = 1;  
        newOpts.inSampleSize = be;//设置缩放比例  
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
        isBm = new ByteArrayInputStream(baos.toByteArray());  
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
        LogUtil.i("压缩后：bitmap=getHeight="+bitmap.getHeight()+";getWidth=="+bitmap.getWidth());
        return bitmap;//压缩好比例大小后再进行质量压缩  
    }  
    

    /**
	 * 图片按比例大小压缩方法（根据路径获取图片并压缩
	 * @param srcPath
	 * @return
	 */
    private Bitmap getimage(String srcPath) {  
        BitmapFactory.Options newOpts = new BitmapFactory.Options();  
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
        newOpts.inJustDecodeBounds = true;  
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空  
          
        newOpts.inJustDecodeBounds = false;  
        int w = newOpts.outWidth;  
        int h = newOpts.outHeight;  
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
        float hh = 800f;//这里设置高度为800f  
        float ww = 480f;//这里设置宽度为480f  
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
        int be = 1;//be=1表示不缩放  
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
            be = (int) (newOpts.outWidth / ww);  
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
            be = (int) (newOpts.outHeight / hh);  
        }  
        if (be <= 0)  
            be = 1;  
        newOpts.inSampleSize = be;//设置缩放比例  
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);  
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩  
    }  
  

	/**
	 * 压缩图片
	 * @param path
	 * @param maxLenth
	 * @return
	 */
	public static Bitmap getSmallBitmap(String path) {
		LogUtil.d("file.path       " + path);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		
		BitmapFactory.decodeFile(path, opts);
		
		int srcWidth = opts.outWidth;
		int srcHeight = opts.outHeight;
		
		int destWidth = 0;
		int destHeight = 0;
		// 缩放的比例
		double ratio = 0.0;
		/**
		 * 最大高度
		 */
		int maxLength = 100;
		if (srcWidth > srcHeight) {
			ratio = srcWidth / maxLength;
			destWidth = maxLength;
			destHeight = (int) (srcHeight / ratio);
		} else {
			ratio = srcHeight / maxLength;
			destHeight = maxLength;
			destWidth = (int) (srcWidth / ratio);
		}
		
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		LogUtil.d("ratio + 1", "ratio + 1:" + ((int) ratio + 1));
		
		newOpts.inSampleSize = (int) ratio + 1;
		newOpts.inJustDecodeBounds = false;
		newOpts.outHeight = destHeight;
		newOpts.outWidth = destWidth;
		
		Bitmap bitMap = BitmapFactory.decodeFile(path, newOpts);
		return bitMap;
	}
	
	
	
	/**
     * 把一个文件转化为String
     * @param file
     * @return   
     * @throws Exception
     */
    public static String getStringFromFile(String path)
    {
    	try {
			File file = new File(path);
			byte[] bytes = null;
			if(file!=null)
			{
			    InputStream is = new FileInputStream(file);
			    int length = (int) file.length();
			    if(length>Integer.MAX_VALUE)   //当文件的长度超过了int的最大值
			    {
			    	LogUtil.d("this file is max ");
			        return null;
			    }
			    bytes = new byte[length];
			    int offset = 0;
			    int numRead = 0;
			    while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
			    {
			        offset+=numRead;
			    }
			    //如果得到的字节长度和file实际的长度不一致就可能出错了
			    if(offset<bytes.length)
			    {
			    	LogUtil.d("file length is error");
			        return null;
			    }
			    is.close();
			}
			String s =Base64.encodeToString(bytes, Base64.DEFAULT);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	 /**
	  * 压缩图片 按宽压缩
	  * @param oldbmp
	  * @param w 最大宽
	  * @return
	  */
	 public static Bitmap bitmapCompressWith(Bitmap oldbmp, int w ) {  
		 return bitmapCompress(oldbmp, w, -1);
		 
	 }
	 
	 /**
	  * 压缩图片 按高压缩
	  * @param oldbmp
	  * @param h  最大高
	  * @return
	  */
	 public static Bitmap bitmapCompressHeight(Bitmap oldbmp, int h) {  
		 return bitmapCompress(oldbmp, -1, h);
	 }
	 
	 /**
	  *  压缩图片
	  * @param oldbmp
	  * @param w  最大宽
	  * @param h  最大高
	  * @return
	  */
	 public static Bitmap bitmapCompress(Bitmap oldbmp, int w, int h) {  
		 if(oldbmp==null){
			 return null;
		 }
   	int width = oldbmp.getWidth() ;
   	int height = oldbmp.getHeight() ;
       Matrix matrix = new Matrix();  
       float a;
       float scaleWidth = ((float) w / width);  
       float scaleHeight = ((float) h / height);  
       if(w==-1){
       	a=scaleHeight;
       }else if(h==-1){
       	a=scaleWidth;
       }else{
       	a=scaleWidth>scaleHeight?scaleHeight:scaleWidth;
       }
       matrix.postScale(a, a);  
       
       Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,  matrix, true);  
       if(oldbmp!=null&&!oldbmp.isRecycled()){
    	   oldbmp.recycle();
    	   oldbmp=null;
    	   System.gc();
       }
       return newbmp ;
	 }  
	private static Bitmap decodeFile(String path) {
		try {
			File f=new File(path);
			if(!f.exists()){
				return null;
			}
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			 InputStream is =new FileInputStream(f);
			BitmapFactory.decodeFile(f.getPath(),o);
			int scale =1;
//			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 1000;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			LogUtil.i("前:::width_tmp=="+width_tmp+";;height_tmp=="+height_tmp);
			Bitmap bitmap=null;
			int w=0;
			int h=0;
//			if(!path.contains("book")){
			while (true) {
				if (width_tmp  < 800|| height_tmp < 1280)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *=2;
				}
			o.inJustDecodeBounds=false;
			o.inSampleSize = scale;
			o.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap =BitmapFactory.decodeStream(is, null, o);
			LogUtil.i("后-------:::tttwidth_tmp=="+bitmap.getWidth()+";;height_tmp=="+bitmap.getHeight());
//			}else{
//				if(width_tmp>1000){
//					width_tmp=1000;
//				}
//				if(height_tmp>1000){
//					height_tmp=1000;
//				}
//				o.inJustDecodeBounds=false;
//				o.inPreferredConfig = Bitmap.Config.RGB_565;
//				
//				clearCache();
//				Bitmap	bitmaps = BitmapFactory.decodeStream(is, null, o);
//				bitmap=bitmapCompress(bitmaps, width_tmp, height_tmp);
////				bitmap=ImgUtil.fileNameToBitmap(f, width_tmp, height_tmp);
////				bitmap = BitmapFactory.decodeFile(f.getPath(), o);
//			}
			// decode with inSampleSize
			is.close();
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static Bitmap decodeFile(File f) {
		try {
			if(!f.exists()){
				return null;
			}
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			 InputStream is =new FileInputStream(f);
			 BitmapFactory.decodeFile(f.getPath(),o);
			int scale =1;
//			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 1000;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			LogUtil.i("前:::tttwidth_tmp=="+width_tmp+";;height_tmp=="+height_tmp);
			Bitmap bitmap=null;
//			if(!f.getPath().contains("book")){
			while (true) {
				if (width_tmp  < 800|| height_tmp < 1280)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *=2;
				}
			o.inJustDecodeBounds=false;
			o.inSampleSize = scale;
			o.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap =BitmapFactory.decodeStream(is, null, o);
			LogUtil.i("后======:::tttwidth_tmp=="+bitmap.getWidth()+";;height_tmp=="+bitmap.getHeight());
//			}else{
//				if(width_tmp>1000){
//					width_tmp=1000;
//				}
//				if(height_tmp>1000){
//					height_tmp=1000;
//				}
//				o.inJustDecodeBounds=false;
//				o.inPreferredConfig = Bitmap.Config.RGB_565;
//				clearCache();
////				Bitmap	bitmaps = BitmapFactory.decodeFile(f.getPath(), o);
//				Bitmap	bitmaps=BitmapFactory.decodeStream(is, null, o);
//				bitmap=bitmapCompress(bitmaps, width_tmp, height_tmp);
////				bitmap = BitmapFactory.decodeFile(f.getPath(), o);
//			}	
			// decode with inSampleSize
			is.close();
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	public static Bitmap getImageFromCache(String key){
		Bitmap bmp=null;	
		if(key.contains("http")){
			try {
				bmp=(Bitmap) IApplication.getInstance().cache.get(key);
//				bmp=(Bitmap) IApplication.getInstance().cache.getData(key);
//				bmp=MainActivity.cache.getData(key);
				LogUtil.i("bmp==="+bmp);
				if(bmp==null){
					File file=ImageLoader.getInstance().getDiscCache().get(key);
					bmp=decodeFile(file);
					IApplication.getInstance().cache.put(key, bmp);
//					IApplication.getInstance().cache.putData(key, bmp);
//					MainActivity.cache.putData(key, bmp);
				}else{
					if(bmp.isRecycled()){
						bmp=null;
						File file=ImageLoader.getInstance().getDiscCache().get(key);
						bmp=decodeFile(file);
//						IApplication.getInstance().cache.putData(key, bmp);
						IApplication.getInstance().cache.put(key, bmp);
//						MainActivity.cache.putData(key, bmp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
//			bmp=(Bitmap) IApplication.getInstance().cache.getData(key);
			bmp=(Bitmap) IApplication.getInstance().cache.get(key);
//			bmp=MainActivity.cache.getData(key);
			LogUtil.i("from cache:"+bmp);
		}
		if(bmp==null){
			bmp=getImageFromSD(key);
//			IApplication.getInstance().cache.putData(key, bmp);
			IApplication.getInstance().cache.put(key, bmp);
//			MainActivity.cache.putData(key, bmp);
			LogUtil.i("from sdk");
		}else{
			LogUtil.i("bmp=getHeighteeeeeee="+bmp.isRecycled()+"key==="+key);
			if(bmp.isRecycled()){
				clearCache();
				bmp=getImageFromSD(key);
//				IApplication.getInstance().cache.putData(key, bmp);
				IApplication.getInstance().cache.put(key, bmp);
//				MainActivity.cache.putData(key, bmp);
			}
		}

		return bmp;
	}
	
	*/
	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
		
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 旋转图片
	 * 
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		if (bitmap != null && bitmap.isRecycled()) {
			bitmap.recycle();
		}
		return resizedBitmap;
	}

	public static  Bitmap getImageFromSD(String key){	
		Bitmap bitmap =null;
		File f=new File(key);
		if(!f.exists()){
			return null;
		}
		 bitmap =decodeFile(key);
		 int degree=readPictureDegree(key);
//		 Bitmap map=bitmapCompress(bitmap, MainActivity.screenWidth,MainActivity.screenHight);
			Bitmap   bit=rotaingImageView(degree, bitmap);
//			if(bitmap!=null&&!bitmap.isRecycled()){
//				bitmap.recycle();
//				bitmap=null;
//			}
		return bit;
	}

	public static void clearCache(){
		System.gc();
		ImageLoader.getInstance().clearMemoryCache();
		UILApplication.getInstance().getImageLoader().clearMemoryCache();
		UILApplication.getInstance().getImageLoader().getMemoryCache().clear();
//		MainActivity.cache.clearCaches();
//		IApplication.getInstance().cache.clearCaches();
//		IApplication.getInstance().cache.clearCache();
		UILApplication.getInstance().cache.clear();
	}
	
	public static void put(String key,Bitmap bitmap){
		UILApplication.getInstance().cache.put(key, bitmap);
	}
	

}
