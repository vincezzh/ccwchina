package com.ccw.action.course;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Course;
import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursetrunktype;
import com.ccw.common.Params;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CourseContentListAction extends ActionSupport {
	private static final long serialVersionUID = 4160815243449971682L;
	private Log log = LogFactory.getLog(CourseContentListAction.class);
	private Integer trunk;
	private Coursetrunktype trunkType;
	private Integer branch;
	private Coursebranchtype branchType;
	private ArrayList<Course> courseList;
	private String courseContext = Params.COURSE_PATH_CONTEXT;
	private String language;
	private CourseDaoInf courseDao;

	public Integer getTrunk() {
		return trunk;
	}

	public void setTrunk(Integer trunk) {
		this.trunk = trunk;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
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

	public Coursetrunktype getTrunkType() {
		return trunkType;
	}

	public void setTrunkType(Coursetrunktype trunkType) {
		this.trunkType = trunkType;
	}

	public Integer getBranch() {
		return branch;
	}

	public void setBranch(Integer branch) {
		this.branch = branch;
	}

	public Coursebranchtype getBranchType() {
		return branchType;
	}

	public void setBranchType(Coursebranchtype branchType) {
		this.branchType = branchType;
	}

	public String getCourseDesc() {
		try {
			log.debug("CourseContentListAction.getCourseDesc()");
			
			if(trunk == null)
				trunk = 1;
			
			trunkType = courseDao.getDetailCoursetrunktype(trunk);
			if(branch != null) {
				branchType = courseDao.getDetailCoursebranchtype(branch);
			}else {
				branchType = new Coursebranchtype();
			}
			courseList = courseDao.getAllCourseByCoursetrunktypeAndBranchtrunktype(trunkType.getCourseTrunkTypeId(), branchType.getCourseBranchTypeId());
			
			Map session = ActionContext.getContext().getSession();
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
}
