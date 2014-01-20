/** 
 * @ClassName: BookInfo 
 * @Description: 
 * @author eastedge.com.cn
 * @date 2013-6-19 下午1:59:50 
 * 
 */ 
package com.midooo.stylist.v3.adapter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.midooo.lib.BaseFramework.Const;
import com.midooo.lib.BaseFramework.FileUtil;
import com.midooo.lib.BaseFramework.LogUtil;


/**    
 * @{# BookInfo.java Create on 2013-6-19 下午1:59:50      
 * @Description: 潮流圈书籍
 * @author eastedge.com.cn <a href="mailto:jusng@foxmail.com">jusng</a>      
 */

public class BookInfo implements Serializable{
	private long id;
	private String title;
	private String description;
	private String indexPic;
	private String dtTime;
	private int totalpage;//页数
	private int state;//0未下载,1已下载
	private List<String> book;
	private List<String> service;
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookInfo(String title, String description, String indexPic) {
		super();
		this.title = title;
		this.description = description;
		this.indexPic = indexPic;
	}
	
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the totalpage
	 */
	public int getTotalpage() {
		return totalpage;
	}
	/**
	 * @param totalpage the totalpage to set
	 */
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	/**
	 * @return the book
	 */
	public List<String> getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(List<String> book) {
		this.book = book;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the indexPic
	 */
	public String getIndexPic() {
		return indexPic;
	}
	/**
	 * @param indexPic the indexPic to set
	 */
	public void setIndexPic(String indexPic) {
		this.indexPic = indexPic;
	}
	
	/**
	 * @return the dtTime
	 */
	public String getDtTime() {
		return dtTime;
	}
	/**
	 * @param dtTime the dtTime to set
	 */
	public void setDtTime(String dtTime) {
		this.dtTime = dtTime;
	}
	

	/**
	 * @return the service
	 */
	public List<String> getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(
			List<String> service) {
		this.service = service;
	}
	public List<String> getpath(){
		List<String> list=new ArrayList<String>();
		for(int i=0;i<totalpage;i++){
			String path=FileUtil.getRootPath()+Const.BOOK_IMG+id+File.separator+i+".jpg";
			LogUtil.i("path=="+path);
			list.add(path);
		}
		return list;
	}

}
