package com.ccw.common.pages;

public class PageBean {
	private String pageLink;
	private int currentPageNumber;
	private int totalPage;
	
	public String getPageLink() {
		return pageLink;
	}
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
