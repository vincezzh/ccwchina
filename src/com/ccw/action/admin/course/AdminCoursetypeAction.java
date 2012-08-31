package com.ccw.action.admin.course;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursetrunktype;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCoursetypeAction extends ActionSupport {
	private static final long serialVersionUID = -7881082248399629539L;
	private Log log = LogFactory.getLog(AdminCoursetypeAction.class);
	private ArrayList<Coursetrunktype> trunkList;
	private Integer courseTrunkTypeId;
	private Integer courseBranchTypeId;
	private Integer categoryLevel;
	private String key;
	private String nameCN;
	private String nameEN;
	private String nameJP;
	private String descCN;
	private String descEN;
	private String descJP;
	private String path;
	private String fontColor;
	private String backgroundColor;
	private Coursetrunktype trunk;
	private Coursebranchtype branch;
	private CourseDaoInf courseDao;

	public ArrayList<Coursetrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Coursetrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameJP() {
		return nameJP;
	}

	public void setNameJP(String nameJP) {
		this.nameJP = nameJP;
	}

	public String getDescCN() {
		return descCN;
	}

	public void setDescCN(String descCN) {
		this.descCN = descCN;
	}

	public String getDescEN() {
		return descEN;
	}

	public void setDescEN(String descEN) {
		this.descEN = descEN;
	}

	public String getDescJP() {
		return descJP;
	}

	public void setDescJP(String descJP) {
		this.descJP = descJP;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getCourseBranchTypeId() {
		return courseBranchTypeId;
	}

	public void setCourseBranchTypeId(Integer courseBranchTypeId) {
		this.courseBranchTypeId = courseBranchTypeId;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getCourseTrunkTypeId() {
		return courseTrunkTypeId;
	}

	public void setCourseTrunkTypeId(Integer courseTrunkTypeId) {
		this.courseTrunkTypeId = courseTrunkTypeId;
	}

	public Coursetrunktype getTrunk() {
		return trunk;
	}

	public void setTrunk(Coursetrunktype trunk) {
		this.trunk = trunk;
	}

	public Coursebranchtype getBranch() {
		return branch;
	}

	public void setBranch(Coursebranchtype branch) {
		this.branch = branch;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String gotoAddCoursePage() {
		try {
			log.debug("AdminCoursetypeAction.gotoCoursePage()");

			trunkList = courseDao.getAllCourseTrunkType();
			
			Coursetrunktype t = new Coursetrunktype();
			t.setAvaliable("yes");
			t.setCourseTrunkTypeId(-1);
			t.setCourseTrunkTypeNameKey("PLEASE_SELECT");
			trunkList.add(0, t);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String addCategoryCourse() {
		try {
			log.debug("AdminCoursetypeAction.addCategoryCourse()");

			if(categoryLevel == 1) {
				Coursetrunktype trunk = new Coursetrunktype();
				trunk.setAvaliable("yes");
				trunk.setCourseTrunkTypeNameKey(key);
				trunk.setCourseTrunkTypePath(path);
				trunk.setCourseTrunkTypeDescriptionKey(key + "_DESC");
				trunk.setBackgroundColor(backgroundColor);
				trunk.setFontColor(fontColor);
				courseDao.addCoursetrunktype(trunk);
				
				String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
				PropertyUtil.writeToAllLanguageProperties(trunk.getCourseTrunkTypeNameKey(), nameValues);
				String[] descValues = new String[]{descEN, descEN, descCN, descJP}; 
				PropertyUtil.writeToAllLanguageProperties(trunk.getCourseTrunkTypeDescriptionKey(), descValues);
			}else if(categoryLevel == 2) {
				if(courseTrunkTypeId != -1) {
					Coursebranchtype branch = new Coursebranchtype();
					Coursetrunktype trunk = new Coursetrunktype();
					trunk.setCourseTrunkTypeId(courseTrunkTypeId);
					branch.setCoursetrunktype(trunk);
					branch.setAvaliable("yes");
					branch.setCourseBranchTypeNameKey(key);
					branch.setCourseBranchTypePath(path);
					courseDao.addCoursebranchtype(branch);
					
					String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
					PropertyUtil.writeToAllLanguageProperties(branch.getCourseBranchTypeNameKey(), nameValues);
				}
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showCategoryCourse() {
		try {
			log.debug("AdminCoursetypeAction.showCategoryCourse()");

			trunkList = courseDao.getAllCourseTrunkType();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteCategoryCourse() {
		try {
			log.debug("AdminCoursetypeAction.deleteCategoryCourse()");

			if(categoryLevel == 1) {
				courseDao.deleteCoursetrunktype(courseTrunkTypeId);
			}else if(categoryLevel == 2) {
				courseDao.deleteCoursebranchtype(courseBranchTypeId);
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdateCoursePage() {
		try {
			log.debug("AdminCoursetypeAction.gotoUpdateCoursePage()");

			if(categoryLevel == 1) {
				trunk = courseDao.getDetailCoursetrunktype(courseTrunkTypeId);
				
				nameEN = PropertyUtil.get(Params.USA, trunk.getCourseTrunkTypeNameKey());
				nameCN = PropertyUtil.get(Params.CHINA, trunk.getCourseTrunkTypeNameKey());
				nameJP = PropertyUtil.get(Params.JAPAN, trunk.getCourseTrunkTypeNameKey());
				
				descEN = PropertyUtil.get(Params.USA, trunk.getCourseTrunkTypeDescriptionKey());
				descCN = PropertyUtil.get(Params.CHINA, trunk.getCourseTrunkTypeDescriptionKey());
				descJP = PropertyUtil.get(Params.JAPAN, trunk.getCourseTrunkTypeDescriptionKey());
			}else if(categoryLevel == 2) {
				branch = courseDao.getDetailCoursebranchtype(courseBranchTypeId);
				
				nameEN = PropertyUtil.get(Params.USA, branch.getCourseBranchTypeNameKey());
				nameCN = PropertyUtil.get(Params.CHINA, branch.getCourseBranchTypeNameKey());
				nameJP = PropertyUtil.get(Params.JAPAN, branch.getCourseBranchTypeNameKey());
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateCategoryCourse() {
		try {
			log.debug("AdminCoursetypeAction.updateCategoryCourse()");

			if(categoryLevel == 1) {
				trunk.setAvaliable("yes");
				courseDao.updateCoursetrunktype(trunk);
				
				String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
				PropertyUtil.writeToAllLanguageProperties(trunk.getCourseTrunkTypeNameKey(), nameValues);
				String[] descValues = new String[]{descEN, descEN, descCN, descJP}; 
				PropertyUtil.writeToAllLanguageProperties(trunk.getCourseTrunkTypeDescriptionKey(), descValues);
			}else if(categoryLevel == 2) {
				branch.setAvaliable("yes");
				courseDao.updateCoursebranchtype(branch);
				
				String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
				PropertyUtil.writeToAllLanguageProperties(branch.getCourseBranchTypeNameKey(), nameValues);
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
