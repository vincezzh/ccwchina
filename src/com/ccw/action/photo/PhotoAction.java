package com.ccw.action.photo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Photo;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport {
	private static final long serialVersionUID = -3918436111973705681L;
	private Log log = LogFactory.getLog(PhotoAction.class);
	private LinkedHashMap<String, ArrayList<Photo>> photoList;
	private Integer typeCount;
	private CommonDaoInf commonDao;
	
	public LinkedHashMap<String, ArrayList<Photo>> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(LinkedHashMap<String, ArrayList<Photo>> photoList) {
		this.photoList = photoList;
	}

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public Integer getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}

	public String gotoPhotoPage() {
		try {
			log.debug("PhotoAction.gotoPhotoPage()");
			
			photoList = commonDao.getAllPhotoForShow();
			typeCount = photoList.values().size();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
