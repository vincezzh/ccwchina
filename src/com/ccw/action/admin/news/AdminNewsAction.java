package com.ccw.action.admin.news;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.News;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminNewsAction extends ActionSupport {
	private static final long serialVersionUID = -857163988018627488L;
	private Log log = LogFactory.getLog(AdminNewsAction.class);
	private ArrayList<News> newsList;
	private News news;
	private Long newsId;
	private Date today;
	private String highlight;
	private CommonDaoInf commonDao;
	private PageContainer pageContainer;
	private Integer pageNumber;

	public ArrayList<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(ArrayList<News> newsList) {
		this.newsList = newsList;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public PageContainer getPageContainer() {
		return pageContainer;
	}

	public void setPageContainer(PageContainer pageContainer) {
		this.pageContainer = pageContainer;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String gotoAddNewsPage() {
		try {
			log.debug("AdminNewsAction.gotoAddNewsPage()");

			today = new Date();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String addNews() {
		try {
			log.debug("AdminNewsAction.addNews()");

			news.setAvaliable("yes");
			if(!"false".equals(highlight)) {
				news.setFlag(highlight);
			}
			commonDao.addNews(news);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showNews() {
		try {
			log.debug("AdminNewsAction.showNews()");
			if(pageNumber == null) {
				pageNumber = 1;
			}
			
			newsList = commonDao.getAllNewsByPageNumber(pageNumber);
			
			Long count = commonDao.getAllNewsCount();
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_NEWS_PER_PAGE_NUMBER), "/_administrator/archives-news.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteNews() {
		try {
			log.debug("AdminNewsAction.deleteNews()");

			commonDao.deleteNews(newsId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdateNewsPage() {
		try {
			log.debug("AdminNewsAction.gotoUpdateNewsPage()");

			news = commonDao.getDetailNews(newsId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateNews() {
		try {
			log.debug("AdminNewsAction.updateNews()");

			news.setAvaliable("yes");
			if(!"false".equals(highlight)) {
				news.setFlag(highlight);
			}else {
				news.setFlag("");
			}
			commonDao.updateNews(news);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
