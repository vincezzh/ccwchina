package com.ccw.action.admin.photo;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Photo;
import com.ccw.bean.Phototrunktype;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.CommonDaoInf;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPhotoAction extends ActionSupport {
	private static final long serialVersionUID = -1193146311350705576L;
	private Log log = LogFactory.getLog(AdminPhotoAction.class);
	private ArrayList<Photo> photoList;
	private ArrayList<Phototrunktype> trunkList;
	private Photo photo;
	private String photoId;
	private CommonDaoInf commonDao;
	private UserDaoInf userDao;
	private PageContainer pageContainer;
	private Integer pageNumber;
	
	public ArrayList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ArrayList<Photo> photoList) {
		this.photoList = photoList;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public ArrayList<Phototrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Phototrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public String gotoAddPhotoPage() {
		try {
			log.debug("AdminPhotoAction.gotoAddPhotoPage()");
			
			trunkList = commonDao.getAllPhototrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String addPhoto() {
		try {
			log.debug("AdminPhotoAction.addPhoto()");

			Date now = new Date();
			photo.setPhotoId("photo-" + now.getTime());
			photo.setUpdatedTime(now);
			photo.setAvaliable("yes");
			photo.setUserdetail(userDao.getDetailUser(Params.CCW_PUBLIC_USER_ID));
			commonDao.addPhoto(photo);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showPhoto() {
		try {
			log.debug("AdminPhotoAction.showPhoto()");
			if(pageNumber == null) {
				pageNumber = 1;
			}
			
			photoList = commonDao.getAllPhotoByPageNumber(pageNumber);
			
			Long count = commonDao.getAllPhotoCount();
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_PHOTO_PER_PAGE_NUMBER), "/_administrator/archives-photo.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deletePhoto() {
		try {
			log.debug("AdminPhotoAction.deletePhoto()");

			commonDao.deletePhoto(photoId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdatePhotoPage() {
		try {
			log.debug("AdminPhotoAction.gotoUpdatePhotoPage()");

			photo = commonDao.getDetailPhoto(photoId);
			trunkList = commonDao.getAllPhototrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updatePhoto() {
		try {
			log.debug("AdminPhotoAction.updatePhoto()");

			photo.setAvaliable("yes");
			commonDao.updatePhoto(photo);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
