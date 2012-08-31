package com.ccw.action.admin.common;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.common.Params;
import com.ccw.common.WebsiteUtil;
import com.ccw.dao.CommonDaoInf;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.ItemDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class WebsiteAction extends ActionSupport {
	private static final long serialVersionUID = 1764880485785711266L;
	private Log log = LogFactory.getLog(WebsiteAction.class);
	private CourseDaoInf courseDao;
	private CommonDaoInf commonDao;
	private ItemDaoInf itemDao;
	
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

	public ItemDaoInf getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDaoInf itemDao) {
		this.itemDao = itemDao;
	}

	public String execute() {
		try {
			log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Create Website startedly!!!! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String contextPath = ServletActionContext.getServletContext().getRealPath("");
			String fromPath = contextPath + File.separator + "WEB-INF" + File.separator + Params.WEBSITE_FROM_FOLDER_NAME;
			String toPath = contextPath;
			WebsiteUtil wu = new WebsiteUtil();
			
			wu.deleteDirectory(fromPath);
			wu.createAllWebPages(fromPath, courseDao, commonDao, itemDao);
			wu.deleteDirectories(toPath, this.getAllFolderNames());
			wu.copyWebPages(fromPath, toPath);
			wu.deleteDirectory(fromPath);
			log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Create Website Successfully!!!! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private ArrayList<String> getAllFolderNames() {
		ArrayList<String> folderNames = new ArrayList<String>();
		folderNames.add(Params.WEBSITE_TOP_TEMP_FOLDER_NAME);
		folderNames.add(Params.WEBSITE_BOTTOM_TEMP_FOLDER_NAME);
		folderNames.add(Params.COURSE_PATH_CONTEXT);
		folderNames.add(Params.NEWS_PATH_CONTEXT);
		folderNames.add(Params.ITEM_PATH_CONTEXT);
		
		return folderNames;
	}
}
