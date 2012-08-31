package com.ccw.action.admin.picture;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.common.Params;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class PictureAction extends ActionSupport {
	private static final long serialVersionUID = 8792268045518686588L;
	private Log log = LogFactory.getLog(PictureAction.class);
	private CommonDaoInf commonDao;
	private String folderName;
	private String path;
	private String[] folderNames;
	private String[] picturePaths;

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String[] getFolderNames() {
		return folderNames;
	}

	public void setFolderNames(String[] folderNames) {
		this.folderNames = folderNames;
	}

	public String[] getPicturePaths() {
		return picturePaths;
	}

	public void setPicturePaths(String[] picturePaths) {
		this.picturePaths = picturePaths;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String gotoAddPicturePage() {
		log.debug("PictureAction.gotoAddPicturePage()");
		
		String path = ServletActionContext.getServletContext().getRealPath(Params.PICTURECONTEXT);
		File folders = new File(path);
		String[] fns = folders.list();
		folderNames = new String[fns.length + 1];
		folderNames[0] = "";
		for(int i=0; i<fns.length; i++) {
			folderNames[i+1] = fns[i];
		}

		return SUCCESS;
	}

	public String addPictureFolder() {
		log.debug("PictureAction.addPictureFolder()");
		
		String path = ServletActionContext.getServletContext().getRealPath(
				Params.PICTURECONTEXT);
		File newFolder = new File(path + File.separator + folderName);
		newFolder.mkdirs();

		return SUCCESS;
	}

	public String showPictures() {
		log.debug("PictureAction.showPictures()");
		
		String path = ServletActionContext.getServletContext().getRealPath(Params.PICTURECONTEXT);
		File folders = new File(path);
		String[] fns = folders.list();
		folderNames = new String[fns.length + 1];
		folderNames[0] = "";
		for(int i=0; i<fns.length; i++) {
			folderNames[i+1] = fns[i];
		}
		
		if(folderName == null || "".equals(folderName))
			return SUCCESS;
		
		String fn = path + File.separator + folderName;
		File allPictures = new File(fn);
		picturePaths = allPictures.list();
		String contextPath = ServletActionContext.getServletContext().getContextPath();
		String context = contextPath + Params.PICTURECONTEXT;
		for(int i=0; i<picturePaths.length; i++) {
			picturePaths[i] = context + "/" + folderName + "/" + picturePaths[i];
		}

		return SUCCESS;
	}
	
	public String deletePicture() {
		log.debug("PictureAction.deletePicture()");
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		File picture = new File(rootPath + path);
		picture.delete();

		return SUCCESS;
	}
}
