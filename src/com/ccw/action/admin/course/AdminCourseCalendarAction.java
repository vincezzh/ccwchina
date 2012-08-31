package com.ccw.action.admin.course;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.action.admin.common.AdminCalendarGenerator;
import com.ccw.bean.Calendarwithcourse;
import com.ccw.bean.Classtime;
import com.ccw.bean.Course;
import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Coursetrunktype;
import com.ccw.bean.Specialtype;
import com.ccw.common.Params;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCourseCalendarAction extends ActionSupport {
	private static final long serialVersionUID = -7761222668952883747L;
	private Log log = LogFactory.getLog(AdminCourseCalendarAction.class);
	private Date currentMonth;
	private String currentMonthDate;
	private Integer courseLocationId;
	private Date date;
	private String courseDate;
	private Integer classTimeId;
	private Courselocation courseLocation;
	private ArrayList<Courselocation> locationList;
	private Classtime classTime;
	private ArrayList<Classtime> classTimes;
	private ArrayList<Coursetrunktype> trunkList;
	private ArrayList<Course> courseList;
	private ArrayList<Specialtype> specialTypeList;
	private Coursecalendar courseCalendar;
	private String[] courseIds;
	private Integer[] classTimeIds;
	private String courseCalendarId;
	private Integer courseBranchTypeId;
	private CourseDaoInf courseDao;
	private String startDate;
	private String endDate;
	
	public Date getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Date currentMonth) {
		this.currentMonth = currentMonth;
	}

	public Integer getCourseLocationId() {
		return courseLocationId;
	}

	public void setCourseLocationId(Integer courseLocationId) {
		this.courseLocationId = courseLocationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getClassTimeId() {
		return classTimeId;
	}

	public void setClassTimeId(Integer classTimeId) {
		this.classTimeId = classTimeId;
	}

	public Courselocation getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(Courselocation courseLocation) {
		this.courseLocation = courseLocation;
	}

	public Classtime getClassTime() {
		return classTime;
	}

	public void setClassTime(Classtime classTime) {
		this.classTime = classTime;
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

	public ArrayList<Specialtype> getSpecialTypeList() {
		return specialTypeList;
	}

	public void setSpecialTypeList(ArrayList<Specialtype> specialTypeList) {
		this.specialTypeList = specialTypeList;
	}

	public Coursecalendar getCourseCalendar() {
		return courseCalendar;
	}

	public void setCourseCalendar(Coursecalendar courseCalendar) {
		this.courseCalendar = courseCalendar;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String[] courseIds) {
		this.courseIds = courseIds;
	}

	public String getCourseCalendarId() {
		return courseCalendarId;
	}

	public void setCourseCalendarId(String courseCalendarId) {
		this.courseCalendarId = courseCalendarId;
	}

	public ArrayList<Courselocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Courselocation> locationList) {
		this.locationList = locationList;
	}

	public String getCurrentMonthDate() {
		return currentMonthDate;
	}

	public void setCurrentMonthDate(String currentMonthDate) {
		this.currentMonthDate = currentMonthDate;
	}

	public String getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}
	
	public Integer getCourseBranchTypeId() {
		return courseBranchTypeId;
	}

	public void setCourseBranchTypeId(Integer courseBranchTypeId) {
		this.courseBranchTypeId = courseBranchTypeId;
	}

	public ArrayList<Classtime> getClassTimes() {
		return classTimes;
	}

	public void setClassTimes(ArrayList<Classtime> classTimes) {
		this.classTimes = classTimes;
	}

	public Integer[] getClassTimeIds() {
		return classTimeIds;
	}

	public void setClassTimeIds(Integer[] classTimeIds) {
		this.classTimeIds = classTimeIds;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String gotoAddCourseCalendarPage() {
		try {
			log.debug("AdminCourseCalendarAction.gotoAddCourseCalendarPage()");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(courseDate);
			
			classTime = courseDao.getDetailClasstime(classTimeId);
			classTimeIds = new Integer[1];
			classTimeIds[0] = classTime.getClassTimeId();
			
			courseLocation = courseDao.getDetailCourselocation(courseLocationId);
			trunkList = courseDao.getAllCourseTrunkType();
			if(trunkList.size() > 0) {
				courseList = courseDao.getAllCourseByCoursetrunktype(trunkList.get(0).getCourseTrunkTypeId());
			}
			Coursetrunktype t = new Coursetrunktype();
			t.setAvaliable("yes");
			t.setCourseTrunkTypeId(-1);
			t.setCourseTrunkTypeNameKey("PLEASE_SELECT");
			trunkList.add(0, t);
			
			specialTypeList = courseDao.getAllSpecialtype();
			classTimes = courseDao.getAllClasstime();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String addCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.addCourseCalendar()");
			
			if(courseBranchTypeId != -1 && classTimeIds != null && classTimeIds.length != 0) {
				Coursebranchtype branch = new Coursebranchtype();
				branch.setCourseBranchTypeId(courseBranchTypeId);
				courseCalendar.setCoursebranchtype(branch);
				for(Integer aClassTimeId : classTimeIds) {
					Date now = new Date();
					courseCalendar.setAvaliable("yes");
					courseCalendar.setCourseCalendarId("L-" + now.getTime());
					Classtime tempTime = new Classtime();
					tempTime.setClassTimeId(aClassTimeId);
					courseCalendar.setClasstime(tempTime);
					courseDao.addCoursecalendar(courseCalendar);
					
					Calendarwithcourse cwc = null;
					Course c = null;
					for(String courseId : courseIds) {
						cwc = new Calendarwithcourse();
						c = new Course();
						c.setCourseId(courseId);
						cwc.setCourse(c);
						cwc.setCoursecalendar(courseCalendar);
						courseDao.addCalendarwithcourse(cwc);
					}
				}
				
				courseCalendarId = courseCalendar.getCourseCalendarId();
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String showCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.showCourseCalendar()");
			
			locationList = courseDao.getAllCourselocation();
			courseLocation = locationList.get(0);
			
			currentMonth = new Date();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String refreshCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.showCourseCalendar()");
			
			if(courseLocationId == null)
				courseLocationId = 1;
			if(currentMonthDate == null) {
				currentMonth = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				currentMonth = sdf.parse(currentMonthDate);
			}
			ArrayList<Coursecalendar> courses = courseDao.getCoursecalendarBycourseLocationByMonth(courseLocationId, currentMonth);
			ArrayList<Classtime> allTimes = courseDao.getAllClasstime();
			String calendar = AdminCalendarGenerator.generateAMonthCalendar(courses, allTimes, courseLocationId, currentMonth, Locale.CHINA);
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(calendar);

			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String deleteCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.deleteCourseCalendar()");

			courseDao.deleteCoursecalendar(courseCalendarId);
			courseDao.removeAllCalendarwithcourse(courseCalendarId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String gotoUpdateCourseCalendarPage() {
		try {
			log.debug("AdminCourseCalendarAction.gotoUpdateCourseCalendarPage()");

			courseCalendar = courseDao.getDetailCoursecalendar(courseCalendarId);
			ArrayList<Calendarwithcourse> calendarwithcourse = courseDao.getAllCalendarwithcourseByCoursecalendar(courseCalendar.getCourseCalendarId());
			courseIds = new String[calendarwithcourse.size()];
			for(int i=0; i<calendarwithcourse.size(); i++) {
				courseIds[i] = calendarwithcourse.get(i).getCourse().getCourseId();
			}
			
			trunkList = courseDao.getAllCourseTrunkType();
			if(trunkList.size() > 0) {
				courseList = courseDao.getAllCourseByCoursetrunktype(courseCalendar.getCoursetrunktype().getCourseTrunkTypeId());
			}
			
			specialTypeList = courseDao.getAllSpecialtype();
			classTimes = courseDao.getAllClasstime();
			locationList = courseDao.getAllCourselocation();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.updateCategoryCourseCalendar()");

			if(courseBranchTypeId != -1) {
				Coursebranchtype branch = new Coursebranchtype();
				branch.setCourseBranchTypeId(courseBranchTypeId);
				courseCalendar.setCoursebranchtype(branch);
				courseCalendar.setAvaliable("yes");
				courseDao.updateCoursecalendar(courseCalendar);
				
				courseDao.removeAllCalendarwithcourse(courseCalendar.getCourseCalendarId());
				Calendarwithcourse cwc = null;
				Course c = null;
				for(String courseId : courseIds) {
					cwc = new Calendarwithcourse();
					c = new Course();
					c.setCourseId(courseId);
					cwc.setCourse(c);
					cwc.setCoursecalendar(courseCalendar);
					courseDao.addCalendarwithcourse(cwc);
				}
				
				courseCalendarId = courseCalendar.getCourseCalendarId();
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String copyCourseCalendar() {
		try {
			log.debug("AdminCourseCalendarAction.copyCourseCalendar()");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ArrayList<Courselocation> allLocations = courseDao.getAllCourselocation();
			Iterator<Courselocation> iAlllocations = allLocations.iterator();
			Courselocation location = null;
			ArrayList<Coursecalendar> courses = null;
			Iterator<Coursecalendar> iCourses = null;
			Coursecalendar course = null;
			Calendarwithcourse cwc = null;
			while(iAlllocations.hasNext()) {
				location = iAlllocations.next();
				courses = courseDao.getCoursecalendarBycourseLocationByStartDateAndEndDate(location.getCourseLocationId(), sdf.parse(startDate), sdf.parse(endDate));
				iCourses = courses.iterator();
				while(iCourses.hasNext()) {
					course = iCourses.next();
					int trunkId = course.getCoursetrunktype().getCourseTrunkTypeId();
					if(trunkId != Params.PRIVATE_TRUNKTYPE_ID.intValue() && trunkId != Params.HOLIDAY_TRUNKTYPE_ID.intValue()) {
						Coursecalendar cloneCourse = cloneCoursecalendar(course);
						courseDao.addCoursecalendar(cloneCourse);
						
						Iterator<Calendarwithcourse> i = course.getCalendarwithcourses().iterator();
						while(i.hasNext()) {
							cwc = i.next();
							Calendarwithcourse newCwc = new Calendarwithcourse();
							Course c = new Course();
							c.setCourseId(cwc.getCourse().getCourseId());
							newCwc.setCourse(c);
							newCwc.setCoursecalendar(cloneCourse);
							courseDao.addCalendarwithcourse(newCwc);
						}
					}
				}
			}
			
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	private Coursecalendar cloneCoursecalendar(Coursecalendar cloneCourse) {
		Coursecalendar courseCalendar = new Coursecalendar();
		Date now = new Date();
		courseCalendar.setCourseCalendarId("L-" + now.getTime());
		
		Courselocation courselocation = new Courselocation();
		courselocation.setCourseLocationId(cloneCourse.getCourselocation().getCourseLocationId());
		courseCalendar.setCourselocation(courselocation);
		
		Classtime classtime = new Classtime();
		classtime.setClassTimeId(cloneCourse.getClasstime().getClassTimeId());
		courseCalendar.setClasstime(classtime);
		
		Specialtype specialtype = new Specialtype();
		specialtype.setSpecialTypeId(cloneCourse.getSpecialtype().getSpecialTypeId());
		courseCalendar.setSpecialtype(specialtype);
		
		Coursebranchtype coursebranchtype = new Coursebranchtype();
		coursebranchtype.setCourseBranchTypeId(cloneCourse.getCoursebranchtype().getCourseBranchTypeId());
		courseCalendar.setCoursebranchtype(coursebranchtype);
		
		Coursetrunktype coursetrunktype = new Coursetrunktype();
		coursetrunktype.setCourseTrunkTypeId(cloneCourse.getCoursetrunktype().getCourseTrunkTypeId());
		courseCalendar.setCoursetrunktype(coursetrunktype);
		
		Calendar c = Calendar.getInstance();
		c.setTime(cloneCourse.getClassDate());
		c.add(Calendar.DATE, Params.COPY_COURSE_CALENDAR_PERIOD_WEEKS * 7);
		Date classDate = c.getTime();
		courseCalendar.setClassDate(classDate);
		
		courseCalendar.setSeatLeft(10);
		courseCalendar.setPricePerPerson(cloneCourse.getPricePerPerson());
		courseCalendar.setPoints(cloneCourse.getPoints());
		courseCalendar.setCourseAdvise(cloneCourse.getCourseAdvise());
		courseCalendar.setFlag(cloneCourse.getFlag());
		courseCalendar.setAvaliable(cloneCourse.getAvaliable());
		
		return courseCalendar;
	}
}
