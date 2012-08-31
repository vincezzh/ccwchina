package com.ccw.action.admin.course;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Course;
import com.ccw.bean.Coursepackage;
import com.ccw.bean.Coursetrunktype;
import com.ccw.bean.Packagewithcourse;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCoursePackageAction extends ActionSupport {
	private static final long serialVersionUID = -7761222668952883747L;
	private Log log = LogFactory.getLog(AdminCoursePackageAction.class);
	private Coursepackage coursePackage;
	private ArrayList<Coursetrunktype> trunkList;
	private ArrayList<Course> courseList;
	private ArrayList<Coursepackage> coursePackages;
	private String[] courseIds;
	private String coursePackageId;
	private CourseDaoInf courseDao;

	public Coursepackage getCoursePackage() {
		return coursePackage;
	}

	public void setCoursePackage(Coursepackage coursePackage) {
		this.coursePackage = coursePackage;
	}

	public ArrayList<Coursetrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Coursetrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public ArrayList<Coursepackage> getCoursePackages() {
		return coursePackages;
	}

	public void setCoursePackages(ArrayList<Coursepackage> coursePackages) {
		this.coursePackages = coursePackages;
	}

	public String[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String[] courseIds) {
		this.courseIds = courseIds;
	}

	public String getCoursePackageId() {
		return coursePackageId;
	}

	public void setCoursePackageId(String coursePackageId) {
		this.coursePackageId = coursePackageId;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String gotoAddCoursePackagePage() {
		try {
			log.debug("AdminCoursePackageAction.gotoAddCoursePackagePage()");

			trunkList = courseDao.getAllCourseTrunkType();
			courseList = courseDao.getAllCourse();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String addCoursePackage() {
		try {
			log.debug("AdminCoursePackageAction.addCoursePackage()");

			Date now = new Date();
			coursePackage.setAvaliable("yes");
			coursePackage.setCoursePackageId("P-" + now.getTime());
			courseDao.addCoursepackage(coursePackage);
			
			Packagewithcourse pwc = null;
			Course c = null;
			for(String courseId : courseIds) {
				pwc = new Packagewithcourse();
				c = new Course();
				c.setCourseId(courseId);
				pwc.setCourse(c);
				pwc.setCoursepackage(coursePackage);
				courseDao.addPackagewithcourse(pwc);
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String showCoursePackage() {
		try {
			log.debug("AdminCoursePackageAction.showCoursePackage()");
			
			coursePackages = courseDao.getAllCoursepackage();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String deleteCoursePackage() {
		try {
			log.debug("AdminCoursePackageAction.deleteCoursePackage()");

			courseDao.deleteCoursepackage(coursePackageId);
			courseDao.removeAllPackagewithcourse(coursePackageId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String gotoUpdateCoursePackagePage() {
		try {
			log.debug("AdminCoursePackageAction.gotoUpdateCoursePackagePage()");

			coursePackage = courseDao.getDetailCoursepackage(coursePackageId);
			ArrayList<Packagewithcourse> packagewithcourse = courseDao.getAllPackagewithcourseByCoursepackage(coursePackage.getCoursePackageId());
			courseIds = new String[packagewithcourse.size()];
			for(int i=0; i<packagewithcourse.size(); i++) {
				courseIds[i] = packagewithcourse.get(i).getCourse().getCourseId();
			}
			
			trunkList = courseDao.getAllCourseTrunkType();
			courseList = courseDao.getAllCourse();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateCoursePackage() {
		try {
			log.debug("AdminCoursePackageAction.updateCoursePackage()");

			coursePackage.setAvaliable("yes");
			courseDao.updateCoursepackage(coursePackage);
			
			courseDao.removeAllPackagewithcourse(coursePackage.getCoursePackageId());
			Packagewithcourse pwc = null;
			Course c = null;
			for(String courseId : courseIds) {
				pwc = new Packagewithcourse();
				c = new Course();
				c.setCourseId(courseId);
				pwc.setCourse(c);
				pwc.setCoursepackage(coursePackage);
				courseDao.addPackagewithcourse(pwc);
			}
				
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
