package com.ccw.action.main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Advertisement;
import com.ccw.bean.Course;
import com.ccw.bean.News;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.dao.CommonDaoInf;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {
	private static final long serialVersionUID = 1235287595501151316L;
	private Log log = LogFactory.getLog(MainAction.class);
	private ArrayList<Advertisement> imageSliderAdvertisement;
	private ArrayList<Advertisement> middleTwoAdvertisement;
	private News news;
	private Set<Course> courseList;
	private String newsContext = Params.NEWS_PATH_CONTEXT;
	private String courseContext = Params.COURSE_PATH_CONTEXT;
	private String language;
	private CourseDaoInf courseDao;
	private CommonDaoInf commonDao;

	public ArrayList<Advertisement> getImageSliderAdvertisement() {
		return imageSliderAdvertisement;
	}

	public void setImageSliderAdvertisement(
			ArrayList<Advertisement> imageSliderAdvertisement) {
		this.imageSliderAdvertisement = imageSliderAdvertisement;
	}

	public ArrayList<Advertisement> getMiddleTwoAdvertisement() {
		return middleTwoAdvertisement;
	}

	public void setMiddleTwoAdvertisement(
			ArrayList<Advertisement> middleTwoAdvertisement) {
		this.middleTwoAdvertisement = middleTwoAdvertisement;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Set<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(Set<Course> courseList) {
		this.courseList = courseList;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
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

	public String getCourseContext() {
		return courseContext;
	}

	public void setCourseContext(String courseContext) {
		this.courseContext = courseContext;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String gotoMainPage() {
		try {
			log.debug("MainAction.gotoMainPage()");
			
			middleTwoAdvertisement = commonDao.getMiddleTwoAdvertisement();
			imageSliderAdvertisement = commonDao.getImageSliderAdvertisement();
			news = commonDao.getHighlightNews();
			
			Map session = ActionContext.getContext().getSession();
			Userdetail user = (Userdetail)session.get("user_session");
			Calendar c = Calendar.getInstance();
			Date today = new Date();
			c.setTime(today);
			c.add(Calendar.MONTH, 1);
			Date nextMonth = c.getTime();
			courseList = courseDao.getIndexShowCourses(today, nextMonth, user);
			
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if(locale == null) {
				locale = Locale.US;
				session.put("WW_TRANS_I18N_LOCALE", locale);
			}
			language = locale.getLanguage();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String selectLanguage() {
		try {
			log.debug("MainAction.selectLanguage()");
			
			Locale locale = null;
			if("zh".equals(language)) {
				locale = Locale.CHINA;
			}else if("ja".equals(language)) {
				locale = Locale.JAPAN;
			}else {
				locale = Locale.US;
			}
			Map session = ActionContext.getContext().getSession();
			session.put("WW_TRANS_I18N_LOCALE", locale);
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String checkLanguage() {
		try {
			log.debug("MainAction.checkLanguage()");
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			
			Map session = ActionContext.getContext().getSession();
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if(locale == null) {
				locale = Locale.US;
				session.put("WW_TRANS_I18N_LOCALE", locale);
			}
			out.print(locale.getLanguage());
			
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
