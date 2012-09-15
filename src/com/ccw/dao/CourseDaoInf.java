package com.ccw.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import com.ccw.bean.Calendarwithcourse;
import com.ccw.bean.Classtime;
import com.ccw.bean.Course;
import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Coursepackage;
import com.ccw.bean.Coursetrunktype;
import com.ccw.bean.Packagewithcourse;
import com.ccw.bean.Specialtype;
import com.ccw.bean.Userdetail;

public interface CourseDaoInf {
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByMonth(Integer courseLocationId, Date month) throws Exception;
	
	public ArrayList<Coursecalendar> getCoursecalendarByMonth(Date fromMonth, Date toMonth) throws Exception;
	
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByStartDateAndEndDate(Integer courseLocationId, Date startDate, Date endDate) throws Exception;
	
	public ArrayList<Classtime> getAllClasstime() throws Exception;
	
	public Coursecalendar getDetailCoursecalendar(String courseCalendarId) throws Exception;
	
	public void updateCoursecalendar(Coursecalendar course) throws Exception;
	
	public Courselocation getDetailCourselocation(Integer courseLocationId) throws Exception;
	
	public Classtime getDetailClasstime(Integer classTimeId) throws Exception;
	
	public void addCoursecalendar(Coursecalendar coursecalendar) throws Exception;
	
	public void addCalendarwithcourse(Calendarwithcourse cwc) throws Exception;
	
	public ArrayList<Coursetrunktype> getAllCourseTrunkType() throws Exception;
	
	public ArrayList<Coursebranchtype> getAllCourseBranchTypeByTrunk(Integer courseTrunkTypeId) throws Exception;
	
	public void addCoursetrunktype(Coursetrunktype trunk) throws Exception;
	
	public void addCoursebranchtype(Coursebranchtype branch) throws Exception;
	
	public void deleteCoursetrunktype(Integer courseTrunkTypeId) throws Exception;
	
	public void deleteCoursebranchtype(Integer courseBranchTypeId) throws Exception;
	
	public Coursetrunktype getDetailCoursetrunktype(Integer courseTrunkTypeId) throws Exception;
	
	public Coursebranchtype getDetailCoursebranchtype(Integer courseBranchTypeId) throws Exception;
	
	public void updateCoursetrunktype(Coursetrunktype trunk) throws Exception;
	
	public void updateCoursebranchtype(Coursebranchtype branch) throws Exception;
	
	public void addCourse(Course course) throws Exception;
	
	public void updateCourse(Course course) throws Exception;
	
	public void deleteCourse(String courseId) throws Exception;
	
	public Course getDetailCourse(String courseId) throws Exception;
	
	public ArrayList<Course> getAllCourseByPageNumber(Integer pageNumber) throws Exception;
	
	public Long getAllCoursesCount() throws Exception;
	
	public ArrayList<Courselocation> getAllCourselocation() throws Exception;
	
	public ArrayList<Course> getAllCourseByCoursetrunktype(Integer courseTrunkTypeId) throws Exception;
	
	public ArrayList<Course> getAllCourseByCoursetrunktypeAndBranchtrunktype(Integer courseTrunkTypeId, Integer courseBranchTypeId) throws Exception;
	
	public ArrayList<Specialtype> getAllSpecialtype() throws Exception;
	
	public ArrayList<Calendarwithcourse> getAllCalendarwithcourseByCoursecalendar(String courseCalendarId) throws Exception;
	
	public ArrayList<Calendarwithcourse> getAllCalendarwithcourseByCourseAndLocationAndDate(String courseId, Integer courseLocationId, Date from, Date to) throws Exception;
	
	public void removeAllCalendarwithcourse(String courseCalendarId) throws Exception;
	
	public void deleteCoursecalendar(String courseCalendarId) throws Exception;
	
	public HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> getCoursesOfBottomfoot() throws Exception;
	
	public ArrayList<Course> getCourseByCoursetrunktype(Integer courseTrunkTypeId, Integer recordNumber) throws Exception;
	
	public ArrayList<Course> getAllCourse() throws Exception;
	
	public Set<Course> getIndexShowCourses(Date from, Date to, Userdetail user) throws Exception;
	
	public void addCoursepackage(Coursepackage coursePackage) throws Exception;
	
	public void addPackagewithcourse(Packagewithcourse pwc) throws Exception;
	
	public ArrayList<Coursepackage> getAllCoursepackage() throws Exception;
	
	public void deleteCoursepackage(String coursePackageId) throws Exception;
	
	public void removeAllPackagewithcourse(String coursePackageId) throws Exception;
	
	public Coursepackage getDetailCoursepackage(String coursePackageId) throws Exception;
	
	public ArrayList<Packagewithcourse> getAllPackagewithcourseByCoursepackage(String coursePackageId) throws Exception;
	
	public void updateCoursepackage(Coursepackage coursePackage) throws Exception;
	
	public HashMap<Coursetrunktype, ArrayList<Coursepackage>> getAllCoursepackageWithCoursetrunktype() throws Exception;
	
	public ArrayList<Coursepackage> getAllCoursepackageByCoursetrunktype(Coursetrunktype trunk) throws Exception;
	
	public LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>> getCalendarTemplateCourses(Date from, Date to) throws Exception;
	
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByDayByClasstime(Integer courseLocationId, Date day, Integer classTimeId) throws Exception;
}
