package com.ccw.common.pages;

import java.util.ArrayList;

public class PageContainer {
	private ArrayList<PageBean> allPages;
	private Integer currentPageNumber;
	private Integer totalPagesNumber;
	private String linkContext;
	private String params;
	
	public ArrayList<PageBean> getAllPages() {
		return allPages;
	}
	public void setAllPages(ArrayList<PageBean> allPages) {
		this.allPages = allPages;
	}
	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public Integer getTotalPagesNumber() {
		return totalPagesNumber;
	}
	public void setTotalPagesNumber(Integer totalPagesNumber) {
		this.totalPagesNumber = totalPagesNumber;
	}

	public PageBean getCurrentPage(Integer currentPageNumber) {
		return allPages.get(currentPageNumber - 1);
	}
	
	public String getLinkContext() {
		return linkContext;
	}
	
	public void setLinkContext(String linkContext) {
		this.linkContext = linkContext;
	}
	
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
	public PageBean getNextPage(Integer currentPageNumber) {
		if(currentPageNumber+1 == allPages.size())
			return null;
		else
			return allPages.get(currentPageNumber);
	}
	
	public PageBean getPreviousPage(Integer currentPageNumber) {
		if(currentPageNumber == 1)
			return null;
		else
			return allPages.get(currentPageNumber - 2);
	}
	
	private void savePageBeans() {
		allPages = new ArrayList<PageBean>();
		PageBean pb = null;
		for(int i=0; i<totalPagesNumber; i++) {
			pb = new PageBean();
			pb.setCurrentPageNumber(i+1);
			pb.setTotalPage(totalPagesNumber);
			pb.setPageLink(linkContext + "?pageNumber=" + (i+1) + params);
			allPages.add(pb);
		}
	}
	
	public void setup(Integer currentPageNumber, Integer totalPagesNumber, String linkContext, String params) {
		this.setCurrentPageNumber(currentPageNumber);
		this.setTotalPagesNumber(totalPagesNumber);
		this.setLinkContext(linkContext);
		this.setParams(params);
		this.savePageBeans();
	}
}
