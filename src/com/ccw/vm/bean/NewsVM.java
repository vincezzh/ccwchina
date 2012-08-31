package com.ccw.vm.bean;

import com.ccw.bean.News;

public class NewsVM {
	private String toptitle;
	private String bottomfoot;
	private News news;
	
	public String getToptitle() {
		return toptitle;
	}
	public void setToptitle(String toptitle) {
		this.toptitle = toptitle;
	}
	public String getBottomfoot() {
		return bottomfoot;
	}
	public void setBottomfoot(String bottomfoot) {
		this.bottomfoot = bottomfoot;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	
}
