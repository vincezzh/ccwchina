package com.ccw.action.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.News;
import com.ccw.common.Params;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends ActionSupport {
	private static final long serialVersionUID = 4496334991790463284L;
	private Log log = LogFactory.getLog(NewsAction.class);
	private ArrayList<News> newsList;
	private Integer year;
	private String newsContext = Params.NEWS_PATH_CONTEXT;
	private CommonDaoInf commonDao;
	
	public ArrayList<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(ArrayList<News> newsList) {
		this.newsList = newsList;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public String getNewsContext() {
		return newsContext;
	}

	public void setNewsContext(String newsContext) {
		this.newsContext = newsContext;
	}

	public String gotoNewsPage() {
		try {
			log.debug("NewsAction.gotoNewsPage()");
			
			if(year == null) {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				year = c.get(Calendar.YEAR);
			}
			newsList = commonDao.getNewsByYear(year);
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
