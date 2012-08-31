package com.ccw.action.admin.course;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Course;
import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursetrunktype;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCourseAction extends ActionSupport {
	private static final long serialVersionUID = -643817045200974212L;
	private Log log = LogFactory.getLog(AdminCourseAction.class);
	private ArrayList<Coursetrunktype> trunkList;
	private Course course;
	private String courseId;
	private ArrayList<Course> courseList;
	private Integer courseBranchTypeId;
	private CourseDaoInf courseDao;
	private PageContainer pageContainer;
	private Integer pageNumber;

	public ArrayList<Coursetrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Coursetrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	public Integer getCourseBranchTypeId() {
		return courseBranchTypeId;
	}

	public void setCourseBranchTypeId(Integer courseBranchTypeId) {
		this.courseBranchTypeId = courseBranchTypeId;
	}

	public String gotoAddCoursePage() {
		try {
			log.debug("AdminCourseAction.gotoCoursePage()");

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
	
	public String addCourse() {
		try {
			log.debug("AdminCourseAction.addCourse()");

			Coursebranchtype branch = new Coursebranchtype();
			branch.setCourseBranchTypeId(courseBranchTypeId);
			course.setCoursebranchtype(branch);
			course.setAvaliable("yes");
			Date now = new Date();
			course.setCourseId("C-" + now.getTime());
			
			courseDao.addCourse(course);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showCourse() {
		try {
			log.debug("AdminCourseAction.showCourse()");
			if(pageNumber == null) {
				pageNumber = 1;
			}
			
			courseList = courseDao.getAllCourseByPageNumber(pageNumber);
			
			Long count = courseDao.getAllCoursesCount();
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_COURSE_PER_PAGE_NUMBER), "/_administrator/archives-course.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteCourse() {
		try {
			log.debug("AdminCourseAction.deleteCourse()");

			courseDao.deleteCourse(courseId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdateCoursePage() {
		try {
			log.debug("AdminCourseAction.gotoUpdateCoursePage()");

			course = courseDao.getDetailCourse(courseId);
			trunkList = courseDao.getAllCourseTrunkType();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateCourse() {
		try {
			log.debug("AdminCoursetypeAction.updateCourse()");

			course.setAvaliable("yes");
			courseDao.updateCourse(course);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
