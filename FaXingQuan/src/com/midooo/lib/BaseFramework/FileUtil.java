package com.midooo.lib.BaseFramework;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;




import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.widget.ProgressBar;

public class FileUtil {
	
	public static String getRootPath(){
		String sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();// 获取跟目录
		}
		if (sdDir != null) {
			return sdDir;
		} else {
			return null;
		}
	}

	public static String getRealPath(Context mContext, Uri fileUrl) {
		String fileName = null;
		Uri filePathUri = fileUrl;
		
		File f = new File( Environment.getExternalStorageDirectory() +"");
		
		if (fileUrl != null) {
			if (fileUrl.getScheme().toString().compareTo("content") == 0) // content://开头的uri
			{
				Cursor cursor = mContext.getContentResolver().query(fileUrl,
						null, null, null, null);
				if (cursor != null && cursor.moveToFirst()) {
					int column_index = cursor
							.getColumnIndexOrThrow(MediaColumns.DATA);
					fileName = cursor.getString(column_index); // 取出文件路径
					
					
					if (f.getAbsolutePath().startsWith("/mnt")&&!fileName.startsWith("/mnt")) {
						// 检查是否有”/mnt“前缀

						fileName = "/mnt" + fileName;
					}
					cursor.close();
				}
			} else if (fileUrl.getScheme().compareTo("file") == 0) // file:///开头的uri
			{
				fileName = filePathUri.toString();
				fileName = filePathUri.toString().replace("file://", "");
				// 替换file://
				if (f.getAbsolutePath().startsWith("/mnt")&&!fileName.startsWith("/mnt")) {
					// 加上"/mnt"头
					fileName = "/mnt" + fileName;
				}
			}
		}
		return fileName;
	}


	public static byte[] readData(InputStream ins) throws IOException{
		if (ins == null)
			return null;
		
		BufferedInputStream bis = new BufferedInputStream(ins);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//BufferedOutputStream bos = new BufferedOutputStream(baos);
		int len = 0;
		byte[] buffer = new byte[1024 * 8];
		
		while ((len = bis.read(buffer)) != -1)
			baos.write(buffer, 0, len);
		
		buffer = baos.toByteArray();
		
		bis.close();
		ins.close();
		//bos.close();
		baos.close();
		
		return buffer;
	}


	public static boolean deleteFile(String path){
		File file=new File(path);
			boolean isDeleted=file.delete();
			if(isDeleted){
				return true;
			}else{
				file.deleteOnExit();
				return false;
			}
		
	}
	//android获取一个用于打开HTML文件的intent
		public static Intent getHtmlFileIntent(String Path)
		{
			File file = new File(Path);
			Uri uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build();
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.setDataAndType(uri, "text/html");
			return intent;
		}
		//android获取一个用于打开图片文件的intent
		public static Intent getImageFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "image/*");
			return intent;
		}
		//android获取一个用于打开PDF文件的intent
		public static Intent getPdfFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/pdf");
			return intent;
		}

		//android获取一个用于打开文本文件的intent
		public static Intent getTextFileIntent(String Path)
		{ 
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "text/plain");
			return intent;
		}

		//android获取一个用于打开音频文件的intent
		public static Intent getAudioFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("oneshot", 0);
			intent.putExtra("configchange", 0);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "audio/*");
			return intent;
		}
		//android获取一个用于打开视频文件的intent
		public static Intent getVideoFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("oneshot", 0);
			intent.putExtra("configchange", 0);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "video/*");
			return intent;
		}


		//android获取一个用于打开CHM文件的intent
		public static Intent getChmFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/x-chm");
			return intent;
		}


		//android获取一个用于打开Word文件的intent
		public static Intent getWordFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/msword");
			return intent;
		}
		//android获取一个用于打开Excel文件的intent
		public static Intent getExcelFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/vnd.ms-excel");
			return intent;
		}
		//android获取一个用于打开PPT文件的intent
		public static Intent getPPTFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
			return intent;
		}
		//android获取一个用于打开XML文件的intent
		public static Intent getXMLFileIntent(String path)
		{
			File file = new File(path);
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/xhtml+xml");	
			return intent;
		}
		//android获取一个用于打开apk文件的intent
		public static Intent getApkFileIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent(); 
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			intent.setAction(android.content.Intent.ACTION_VIEW); 
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive"); 
			return intent;
		}
		 //Android获取一个用于打开APK文件的intent   
	    public static Intent getAllIntent( String param ) {  
	  
	        Intent intent = new Intent();    
	        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
	        intent.setAction(android.content.Intent.ACTION_VIEW);    
	        Uri uri = Uri.fromFile(new File(param ));  
	        intent.setDataAndType(uri,"*/*");   
	        return intent;  
	    } 
		//android获取一个用于打开apk文件的intent
		public static Intent getFloderIntent(String Path)
		{
			File file = new File(Path);
			Intent intent = new Intent(); 
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			intent.setAction(android.content.Intent.ACTION_VIEW); 
			intent.setData(Uri.fromFile(file)); 
			return intent;
		}
		
		public static void openFile(String path,String type){
			if(type.contains("htm")){
				FileUtil.getHtmlFileIntent(path);
			}
			if(type.equals("image")){
				FileUtil.getImageFileIntent(path);
			}
			if(type.equals("app")){
				FileUtil.getApkFileIntent(path);
			}
			if(type.equals("audio")){
				FileUtil.getAudioFileIntent(path);
			}
			if(type.equals("video")){
				FileUtil.getVideoFileIntent(path);
			}
			if(type.equals("doc")){
				FileUtil.getWordFileIntent(path);
			}
			if(type.equals("pdf")){
				FileUtil.getPdfFileIntent(path);
			}
			if(type.equals("ppt")){
				FileUtil.getPPTFileIntent(path);
			}
			if(type.equals("xls")){
				FileUtil.getExcelFileIntent(path);
			}
			if(type.equals("xlsx")){
				FileUtil.getExcelFileIntent(path);
			}
			if(type.equals("txt")){
				FileUtil.getTextFileIntent(path);
			}
			if(type.equals("xml")){
				FileUtil.getTextFileIntent(path);
			}
		}
		public static Intent openFile(String filePath){  
			  
	        File file = new File(filePath);  
	        if(!file.exists()) return null;  
	        /* 取得扩展名 */  
	        String end=file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();   
	        /* 依扩展名的类型决定MimeType */  
	        if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")||  
	                end.equals("xmf")||end.equals("ogg")||end.equals("wav")){  
	            return getAudioFileIntent(filePath);  
	        }else if(end.equals("3gp")||end.equals("mp4")){  
	            return getVideoFileIntent(filePath);  
	        }else if(end.equals("jpg")||end.equals("gif")||end.equals("png")||  
	                end.equals("jpeg")||end.equals("bmp")){  
	            return getImageFileIntent(filePath);  
	        }else if(end.equals("apk")){  
	            return getApkFileIntent(filePath);  
	        }else if(end.equals("ppt")){  
	            return getPdfFileIntent(filePath);  
	        }else if(end.equals("xls")||end.equals("xlsx")){  
	            return getExcelFileIntent(filePath);  
	        }else if(end.equals("doc")){  
	            return getWordFileIntent(filePath);  
	        }else if(end.equals("pdf")){  
	            return getPdfFileIntent(filePath);  
	        }else if(end.equals("chm")){  
	            return getChmFileIntent(filePath);  
	        }
	        else if(end.equals("txt")){  
	            return getTextFileIntent(filePath);  
	        }
	        else if(end.equals("xml")){  
	        	return getXMLFileIntent(filePath);  
	        }
	   
	        else{  
	            return getAllIntent(filePath);  
	        }  
	    }  
		
	    /**
	     * 判断是否有足够的空间供下载
	     * 
	     * @param downloadSize
	     * @return
	     */
	    public static boolean isEnoughForDownload(long downloadSize)
	    {
	    	File files=Environment.getExternalStorageDirectory();
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				   StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
				            .getAbsolutePath());
			        //sd卡分区数
			        int blockCounts = statFs.getBlockCount();

			        Log.i("FileUtil", "blockCounts=" + blockCounts);

			        //sd卡可用分区数
			        int avCounts = statFs.getAvailableBlocks();

			        Log.i("FileUtil", "avCounts=" + avCounts);

			        //一个分区数的大小
			        long blockSize = statFs.getBlockSize();

			        Log.i("FileUtil","blockSize=" + blockSize);

			        //sd卡可用空间
			        long spaceLeft = avCounts * blockSize;

			        Log.i("FileUtil","spaceLeft=" + spaceLeft);

			        Log.i("FileUtil","downloadSize=" + downloadSize);

			        if (spaceLeft < downloadSize)
			        {
			            return false;
			        }
			        return true;
			}

	        return false;
	    }
	
		/**
		 * delete all file
		 * @param dir
		 * @return boolean
		 */
		public static boolean deleteDir(File dir,String parent) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]),parent);
					if (!success) {
						return false;
					}
				}
			}
			if(dir.getName().equals("calls")||dir.getName().equals(parent)&&dir.isDirectory()){
				return false;
			}else{
				return dir.delete();
			}
			
		}
		
		/**
		 * delete all file
		 * @param dir
		 * @return boolean
		 */
		public static boolean deleteDir(String path,String parent) {
			File dir=new File(path);
			if(!dir.exists()){
				return false;
			}
			Log.i("FileUtil","getAbsolutePath==="+dir.getAbsolutePath());
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]),parent);
					if (!success) {
						return false;
					}
				}
			}
			if(dir.getName().equals(parent)&&dir.isDirectory()){
				return false;
			}else{
				return dir.delete();
			}
			
		}
		public static boolean deleteTemp(final String path,final String parent) {
			new Thread(){
				
				@Override
				public void run() {
					deleteDir(path, parent);
					super.run();
				}
				
			}.start();
			File dir=new File(path);
			if(!dir.exists()){
				return false;
			}
			Log.i("FileUtil","getAbsolutePath==="+dir.getAbsolutePath());
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]),parent);
					if (!success) {
						return false;
					}
				}
			}
			if(dir.getName().equals(parent)&&dir.isDirectory()){
				return false;
			}else{
				return dir.delete();
			}
			
		}
		
		public static void copyFile(String source,String dest){
			try {
				   File in = new File(source);
				   File out = new File(dest);
				   FileInputStream inFile = new FileInputStream(in);
				   FileOutputStream outFile = new FileOutputStream(out);
				   byte[] buffer = new byte[10240];
				   int i = 0;
				   while ((i = inFile.read(buffer)) != -1) {
				   outFile.write(buffer, 0, i);
				   }//end while
				   inFile.close();
				   outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		public static void saveImage(String targetPath,String targetName,byte[] data){
			try {
				File file=new File(targetPath);
				file.mkdirs();
				File nofile=new File(targetPath+".nomedia"+File.separator);
				nofile.mkdirs();
				file=new File(file.getAbsolutePath()+File.separator+targetName);
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(data,0,data.length);
				fos.close();
				data=null;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		public static boolean saveBitmap(File f,Bitmap mBitmap){
			boolean isSave;
			  try {
				  f.createNewFile();
				  FileOutputStream fOut = null;
				  fOut = new FileOutputStream(f);
				  mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
				  fOut.flush();
				  fOut.close();
				  isSave=true;
			  } catch (IOException e) {
			   e.printStackTrace();
			   isSave=false;
			  }
			return isSave;
		}
		
		public static Bitmap decodeUriAsBitmap(ContentResolver Resolver,Uri uri){
			Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeStream(Resolver.openInputStream(uri));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return bitmap;
		}
}

