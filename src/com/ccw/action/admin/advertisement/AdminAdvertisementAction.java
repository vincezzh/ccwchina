package com.ccw.action.admin.advertisement;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Advertisement;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAdvertisementAction extends ActionSupport {
	private static final long serialVersionUID = -470969341462541641L;
	private Log log = LogFactory.getLog(AdminAdvertisementAction.class);
	private ArrayList<Advertisement> advertisementList;
	private Long advertisementId;
	private String advertisementPath;
	private String advertisementLink;
	private CommonDaoInf commonDao;

	public ArrayList<Advertisement> getAdvertisementList() {
		return advertisementList;
	}

	public void setAdvertisementList(ArrayList<Advertisement> advertisementList) {
		this.advertisementList = advertisementList;
	}

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getAdvertisementPath() {
		return advertisementPath;
	}

	public void setAdvertisementPath(String advertisementPath) {
		this.advertisementPath = advertisementPath;
	}

	public String getAdvertisementLink() {
		return advertisementLink;
	}

	public void setAdvertisementLink(String advertisementLink) {
		this.advertisementLink = advertisementLink;
	}

	public String showAdvertisement() {
		try {
			log.debug("AdminAdvertisementAction.showAdvertisement()");

			advertisementList = commonDao.getAllAdvertisement();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateAdvertisement() {
		try {
			log.debug("AdminAdvertisementAction.updateAdvertisement()");

			Advertisement advertisement = commonDao.getDetailAdvertisement(advertisementId);
			advertisement.setAdvertisementPath(advertisementPath);
			advertisement.setAdvertisementLink(advertisementLink);
			commonDao.updateAdvertisement(advertisement);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
