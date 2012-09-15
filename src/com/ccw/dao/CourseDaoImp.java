package com.ccw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

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
import com.ccw.common.Params;

@Transactional
public class CourseDaoImp implements CourseDaoInf {
	private Log log = LogFactory.getLog(CourseDaoImp.class);
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByMonth(Integer courseLocationId, Date month) throws Exception {
		log.debug("CourseDaoImp.getCoursecalendarBycourseLocationByMonth()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date from = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date to = c.getTime();
		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.avaliable='yes' and c.seatLeft>0 and c.courselocation.courseLocationId=" + courseLocationId + " and c.classDate>='" + sdf.format(from) + "' and c.classDate<='" + sdf.format(to) + "' order by c.classDate,c.classtime.order asc");
		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
		return list;
	}
	
	public ArrayList<Coursecalendar> getCoursecalendarByMonth(Date fromMonth, Date toMonth) throws Exception {
		log.debug("CourseDaoImp.getCoursecalendarByMonth()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.avaliable='yes' and c.seatLeft>0 and c.classDate>='" + sdf.format(fromMonth) + "' and c.classDate<='" + sdf.format(toMonth) + "' order by c.classDate,c.classtime.order asc");
		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
		return list;
	}
	
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByStartDateAndEndDate(Integer courseLocationId, Date startDate, Date endDate) throws Exception {
		log.debug("CourseDaoImp.getCoursecalendarBycourseLocationByStartDateAndEndDate()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.avaliable='yes' and c.courselocation.courseLocationId=" + courseLocationId + " and c.classDate>='" + sdf.format(startDate) + "' and c.classDate<='" + sdf.format(endDate) + "' order by c.classDate,c.classtime.order asc");
		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
		return list;
	}

	public ArrayList<Classtime> getAllClasstime() throws Exception {
		log.debug("CourseDaoImp.getAllClasstime()");
		
		Query query = getEntityManager().createQuery("select c from Classtime c where c.avaliable='yes' order by c.order asc");
		ArrayList<Classtime> list = (ArrayList<Classtime>)query.getResultList();
		return list;
	}

	public Coursecalendar getDetailCoursecalendar(String courseCalendarId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCoursecalendar()");
		
		return getEntityManager().find(Coursecalendar.class, courseCalendarId);
	}

	public void updateCoursecalendar(Coursecalendar course) throws Exception {
		log.debug("CourseDaoImp.updateCoursecalendar()");
		
		getEntityManager().merge(course);
	}

	public Courselocation getDetailCourselocation(Integer courseLocationId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCourselocation()");
		
		return getEntityManager().find(Courselocation.class, courseLocationId);
	}

	public Classtime getDetailClasstime(Integer classTimeId) throws Exception {
		log.debug("CourseDaoImp.getDetailClasstime()");
		
		return getEntityManager().find(Classtime.class, classTimeId);
	}

	public void addCoursecalendar(Coursecalendar coursecalendar) throws Exception {
		log.debug("CourseDaoImp.addCoursecalendar()");
		
		getEntityManager().persist(getEntityManager().merge(coursecalendar));
	}

	public void addCalendarwithcourse(Calendarwithcourse cwc) throws Exception {
		log.debug("CourseDaoImp.addCalendarwithcourse()");
		
		getEntityManager().persist(getEntityManager().merge(cwc));
	}

	public ArrayList<Coursetrunktype> getAllCourseTrunkType() throws Exception {
		log.debug("CourseDaoImp.getAllCourseTrunkType()");
		
		Query query = getEntityManager().createQuery("select c from Coursetrunktype c where c.avaliable='yes' order by c.courseTrunkTypeId asc");
		ArrayList<Coursetrunktype> list = (ArrayList<Coursetrunktype>)query.getResultList();
		return list;
	}

	public ArrayList<Coursebranchtype> getAllCourseBranchTypeByTrunk(Integer courseTrunkTypeId)
			throws Exception {
		log.debug("CourseDaoImp.getAllCourseBranchType()");
		
		Query query = getEntityManager().createQuery("select c from Coursebranchtype c where c.avaliable='yes' and c.coursetrunktype.courseTrunkTypeId=" + courseTrunkTypeId + " order by c.courseBranchTypeId asc");
		ArrayList<Coursebranchtype> list = (ArrayList<Coursebranchtype>)query.getResultList();
		return list;
	}

	public void addCoursetrunktype(Coursetrunktype trunk) throws Exception {
		log.debug("CourseDaoImp.addCoursetrunktype()");
		
		getEntityManager().persist(getEntityManager().merge(trunk));
	}

	public void addCoursebranchtype(Coursebranchtype branch) throws Exception {
		log.debug("CourseDaoImp.addCoursebranchtype()");
		
		getEntityManager().persist(getEntityManager().merge(branch));
	}

	public void deleteCoursetrunktype(Integer courseTrunkTypeId)
			throws Exception {
		log.debug("CourseDaoImp.deleteCoursetrunktype()");
		
		Coursetrunktype trunk = getEntityManager().find(Coursetrunktype.class, courseTrunkTypeId);
		trunk.setAvaliable("no");
		getEntityManager().merge(trunk);
	}

	public void deleteCoursebranchtype(Integer courseBranchTypeId)
			throws Exception {
		log.debug("CourseDaoImp.deleteCoursetrunktype()");
		
		Coursebranchtype branch = getEntityManager().find(Coursebranchtype.class, courseBranchTypeId);
		branch.setAvaliable("no");
		getEntityManager().merge(branch);
	}

	public Coursebranchtype getDetailCoursebranchtype(Integer courseBranchTypeId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCoursebranchtype()");
		
		return getEntityManager().find(Coursebranchtype.class, courseBranchTypeId);
	}

	public Coursetrunktype getDetailCoursetrunktype(Integer courseTrunkTypeId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCoursetrunktype()");
		
		return getEntityManager().find(Coursetrunktype.class, courseTrunkTypeId);
	}

	public void updateCoursebranchtype(Coursebranchtype branch)
			throws Exception {
		log.debug("CourseDaoImp.updateCoursebranchtype()");
		
		getEntityManager().merge(branch);
	}

	public void updateCoursetrunktype(Coursetrunktype trunk) throws Exception {
		log.debug("CourseDaoImp.updateCoursetrunktype()");
		
		getEntityManager().merge(trunk);
	}

	public void addCourse(Course course) throws Exception {
		log.debug("CourseDaoImp.addCourse()");
		
		getEntityManager().persist(getEntityManager().merge(course));
	}

	public void updateCourse(Course course) throws Exception {
		log.debug("CourseDaoImp.updateCourse()");
		
		getEntityManager().merge(course);
	}

	public void deleteCourse(String courseId) throws Exception {
		log.debug("CourseDaoImp.deleteCourse()");
		
		Course course = getEntityManager().find(Course.class, courseId);
		course.setAvaliable("no");
		getEntityManager().merge(course);
	}
	
	public Course getDetailCourse(String courseId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCourse()");
		
		return getEntityManager().find(Course.class, courseId);
	}

	public ArrayList<Course> getAllCourseByPageNumber(Integer pageNumber)
			throws Exception {
		log.debug("CourseDaoImp.getAllCourseByPageNumber()");
		
		Query query = getEntityManager().createQuery("select c from Course c where c.avaliable='yes' order by c.courseId desc");
		query.setFirstResult((pageNumber - 1) * Params.ADMIN_COURSE_PER_PAGE_NUMBER);
		query.setMaxResults(Params.ADMIN_COURSE_PER_PAGE_NUMBER);
		ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
		return list;
	}
	
	public ArrayList<Course> getAllCourse()
			throws Exception {
		log.debug("CourseDaoImp.getAllCourse()");
		
		Query query = getEntityManager().createQuery("select c from Course c where c.avaliable='yes'");
		ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
		return list;
	}

	public Long getAllCoursesCount() throws Exception {
		log.debug("CourseDaoImp.getAllCoursesCount()");
		
		Query query = getEntityManager().createQuery("select count(c) from Course c where c.avaliable='yes'");
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public ArrayList<Courselocation> getAllCourselocation() throws Exception {
		log.debug("CourseDaoImp.getAllCourselocation()");
		
		Query query = getEntityManager().createQuery("select c from Courselocation c where c.avaliable='yes' order by c.courseLocationId asc");
		ArrayList<Courselocation> list = (ArrayList<Courselocation>)query.getResultList();
		return list;
	}
	
	public ArrayList<Course> getAllCourseByCoursetrunktype(Integer courseTrunkTypeId) throws Exception {
		log.debug("CourseDaoImp.getAllCourseByCoursetrunktype()");
		
		Query query = getEntityManager().createQuery("select c from Course c where c.avaliable='yes' and c.coursetrunktype.courseTrunkTypeId=" + courseTrunkTypeId + " order by c.courseId asc");
		ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
		return list;
	}
	
	public ArrayList<Course> getAllCourseByCoursetrunktypeAndBranchtrunktype(Integer courseTrunkTypeId, Integer courseBranchTypeId) throws Exception {
		log.debug("CourseDaoImp.getAllCourseByCoursetrunktypeAndBranchtrunktype()");
		
		if(courseBranchTypeId == null) {
			return getAllCourseByCoursetrunktype(courseTrunkTypeId);
		}else {
			Query query = getEntityManager().createQuery("select c from Course c where c.avaliable='yes' and c.coursetrunktype.courseTrunkTypeId=" + courseTrunkTypeId + "  and c.coursebranchtype.courseBranchTypeId=" + courseBranchTypeId + " order by c.courseId asc");
			ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
			return list;
		}
	}
	
	public ArrayList<Specialtype> getAllSpecialtype() throws Exception {
		log.debug("CourseDaoImp.getAllSpecialtype()");
		
		Query query = getEntityManager().createQuery("select s from Specialtype s where s.avaliable='yes' order by s.specialTypeId asc");
		ArrayList<Specialtype> list = (ArrayList<Specialtype>)query.getResultList();
		return list;
	}

	public ArrayList<Calendarwithcourse> getAllCalendarwithcourseByCoursecalendar(String courseCalendarId) throws Exception {
		log.debug("CourseDaoImp.getAllCalendarwithcourseByCoursecalendar()");
		
		Query query = getEntityManager().createQuery("select c from Calendarwithcourse c where c.coursecalendar.courseCalendarId='" + courseCalendarId + "' order by c.id asc");
		ArrayList<Calendarwithcourse> list = (ArrayList<Calendarwithcourse>)query.getResultList();
		return list;
	}
	
	public ArrayList<Calendarwithcourse> getAllCalendarwithcourseByCourseAndLocationAndDate(String courseId, Integer courseLocationId, Date from, Date to) throws Exception {
		log.debug("CourseDaoImp.getAllCalendarwithcourseByCourseAndLocationAndDate()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select c from Calendarwithcourse c where c.course.courseId='" + courseId + "' and c.coursecalendar.courselocation.courseLocationId=" + courseLocationId + " and c.coursecalendar.classDate>='" + sdf.format(from) + "' and c.coursecalendar.classDate<='" + sdf.format(to) + "' order by c.coursecalendar.classDate asc");
		ArrayList<Calendarwithcourse> list = (ArrayList<Calendarwithcourse>)query.getResultList();
		return list;
	}
	
	public void removeAllCalendarwithcourse(String courseCalendarId) throws Exception {
		log.debug("CourseDaoImp.removeAllCalendarwithcourse()");
		
		Query query = getEntityManager().createQuery("delete from Calendarwithcourse c where c.coursecalendar.courseCalendarId='" + courseCalendarId + "'");
		query.executeUpdate();
	}
	
	public void deleteCoursecalendar(String courseCalendarId) throws Exception {
		log.debug("CourseDaoImp.deleteCoursecalendar()");
		
		Coursecalendar coursecalendar = getEntityManager().find(Coursecalendar.class, courseCalendarId);
		coursecalendar.setAvaliable("no");
		getEntityManager().merge(coursecalendar);
	}

	public HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> getCoursesOfBottomfoot() throws Exception {
		log.debug("CourseDaoImp.getCoursesOfBottomfoot()");
		
		int[] trunkIds = {Params.DIM_SUM_COURSE_TRUNK_TYPE_ID, Params.WOK_COURSE_TRUNK_TYPE_ID, Params.BAKERY_COURSE_TRUNK_TYPE_ID, Params.INTERNATIONAL_COURSE_TRUNK_TYPE_ID, Params.COOKING_EVENTS_TRUNK_TYPE_ID};
		HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> bottomCourseList = new HashMap<Coursetrunktype, ArrayList<Coursebranchtype>>();
		ArrayList<Coursebranchtype> branchs = null;
		for(int i=0; i<trunkIds.length; i++) {
			branchs = this.getAllCourseBranchTypeByTrunk(trunkIds[i]);
			bottomCourseList.put(this.getDetailCoursetrunktype(trunkIds[i]), branchs);
		}
		
		return bottomCourseList;
	}
	
	public ArrayList<Course> getCourseByCoursetrunktype(Integer courseTrunkTypeId, Integer recordNumber) throws Exception {
		log.debug("CourseDaoImp.getCourseByCoursetrunktype()");
		
		Query query = getEntityManager().createQuery("select c from Course c where c.avaliable='yes' and c.coursetrunktype.courseTrunkTypeId=" + courseTrunkTypeId + " order by c.courseId desc");
		if(recordNumber != null && recordNumber > 0) {
			query.setFirstResult(0);
			query.setMaxResults(recordNumber);
		}
		ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
		return list;
	}

	public Set<Course> getIndexShowCourses(Date from, Date to, Userdetail user) throws Exception {
		log.debug("CourseDaoImp.getIndexShowCourses()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String userSql = "";
		if(user != null) {
			userSql = " and c.courselocation.courseLocationId=" + user.getCourselocation().getCourseLocationId();
		}
		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.avaliable='yes' and c.classDate>='" + sdf.format(from) + "' and c.classDate<='" + sdf.format(to) + "'" + userSql);
		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
		Set<Course> allCourses = new HashSet<Course>();
		for(int i=0; i<list.size(); i++) {
			if(allCourses.size() < Params.INDEX_SHOW_COURSES_NUMBER) {
				addCourseList(list.get(i).getCalendarwithcourses(), allCourses);
			}
		}
		return allCourses;
	}
	
	private void addCourseList(Set<Calendarwithcourse> calendarwithcourses, Set<Course> allCourses) {
		Iterator<Calendarwithcourse> i = calendarwithcourses.iterator();
		Course course = null;
		while(i.hasNext()) {
			course = i.next().getCourse();
			if(allCourses.size() < Params.INDEX_SHOW_COURSES_NUMBER) {
				allCourses.add(course);
			}
		}
	}

	public void addCoursepackage(Coursepackage coursePackage) throws Exception {
		log.debug("CourseDaoImp.addCoursepackage()");
		
		getEntityManager().persist(getEntityManager().merge(coursePackage));
	}

	public void addPackagewithcourse(Packagewithcourse pwc) throws Exception {
		log.debug("CourseDaoImp.addPackagewithcourse()");
		
		getEntityManager().persist(getEntityManager().merge(pwc));
	}

	public ArrayList<Coursepackage> getAllCoursepackage() throws Exception {
		log.debug("CourseDaoImp.getAllCoursepackage()");
		
		Query query = getEntityManager().createQuery("select c from Coursepackage c where c.avaliable='yes'");
		ArrayList<Coursepackage> list = (ArrayList<Coursepackage>)query.getResultList();
		return list;
	}

	public void deleteCoursepackage(String coursePackageId) throws Exception {
		log.debug("CourseDaoImp.deleteCoursepackage()");
		
		Coursepackage coursePackage = getEntityManager().find(Coursepackage.class, coursePackageId);
		coursePackage.setAvaliable("no");
		getEntityManager().merge(coursePackage);
	}

	public void removeAllPackagewithcourse(String coursePackageId)
			throws Exception {
		log.debug("CourseDaoImp.removeAllPackagewithcourse()");
		
		Query query = getEntityManager().createQuery("delete from Packagewithcourse p where p.coursepackage.coursePackageId='" + coursePackageId + "'");
		query.executeUpdate();
	}

	public Coursepackage getDetailCoursepackage(String coursePackageId)
			throws Exception {
		log.debug("CourseDaoImp.getDetailCoursepackage()");
		
		return getEntityManager().find(Coursepackage.class, coursePackageId);
	}

	public ArrayList<Packagewithcourse> getAllPackagewithcourseByCoursepackage(
			String coursePackageId) throws Exception {
		log.debug("CourseDaoImp.getAllPackagewithcourseByCoursepackage()");
		
		Query query = getEntityManager().createQuery("select p from Packagewithcourse p where p.coursepackage.coursePackageId='" + coursePackageId + "' order by p.packageWithCourseId asc");
		ArrayList<Packagewithcourse> list = (ArrayList<Packagewithcourse>)query.getResultList();
		return list;
	}

	public void updateCoursepackage(Coursepackage coursePackage)
			throws Exception {
		log.debug("CourseDaoImp.updateCoursepackage()");
		
		getEntityManager().merge(coursePackage);
	}

	public HashMap<Coursetrunktype, ArrayList<Coursepackage>> getAllCoursepackageWithCoursetrunktype()
			throws Exception {
		log.debug("CourseDaoImp.getAllCoursepackageWithCoursetrunktype()");
		
		HashMap<Coursetrunktype, ArrayList<Coursepackage>> packages = new LinkedHashMap<Coursetrunktype, ArrayList<Coursepackage>>();
		ArrayList<Coursetrunktype> allTrunk = this.getAllCourseTrunkType();
		Iterator<Coursetrunktype> iAllTrunk = allTrunk.iterator();
		Coursetrunktype trunk = null;
		ArrayList<Coursepackage> aPackage = null;
		while(iAllTrunk.hasNext()) {
			trunk = iAllTrunk.next();
			aPackage = this.getAllCoursepackageByCoursetrunktype(trunk);
			if(aPackage.size() > 0)
				packages.put(trunk, aPackage);
		}
		
		return packages;
	}

	public ArrayList<Coursepackage> getAllCoursepackageByCoursetrunktype(
			Coursetrunktype trunk) throws Exception {
		log.debug("CourseDaoImp.getAllCoursepackageByCoursetrunktype()");
		
		Query query = getEntityManager().createQuery("select c from Coursepackage c where c.avaliable='yes' and c.coursetrunktype.courseTrunkTypeId=" + trunk.getCourseTrunkTypeId());
		ArrayList<Coursepackage> list = (ArrayList<Coursepackage>)query.getResultList();
		return list;
	}

	public LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>> getCalendarTemplateCourses(
			Date from, Date to) throws Exception {
		log.debug("CourseDaoImp.getCalendarTemplateCourses()");
		
		LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>> allCourses = new LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>>();
		LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>> dateCourses = null;
		LinkedHashMap<Classtime, ArrayList<Coursecalendar>> timeCourses = null;
		
		ArrayList<Courselocation> allLocation = this.getAllCourselocation();
		Iterator<Courselocation> iAllLocation = allLocation.iterator();
		ArrayList<Classtime> allClassTime = this.getAllClasstime();
		Courselocation cl = null;
		while(iAllLocation.hasNext()) {
			cl = iAllLocation.next();
			Calendar c = Calendar.getInstance();
			c.setTime(from);
			dateCourses = new LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>();
			while(c.getTime().compareTo(to) <= 0) {
				Iterator<Classtime> iAllClassTime = allClassTime.iterator();
				Classtime ct = null;
				timeCourses = new LinkedHashMap<Classtime, ArrayList<Coursecalendar>>();
				while(iAllClassTime.hasNext()) {
					ct = iAllClassTime.next();
					ArrayList<Coursecalendar> courseCalendarList = this.getCoursecalendarBycourseLocationByDayByClasstime(cl.getCourseLocationId(), c.getTime(), ct.getClassTimeId());
					timeCourses.put(ct, courseCalendarList);
				}
				dateCourses.put(c.getTime(), timeCourses);
				c.add(Calendar.DATE, 1);
			}
			allCourses.put(cl, dateCourses);
		}
		
		return allCourses;
	}
	
	public ArrayList<Coursecalendar> getCoursecalendarBycourseLocationByDayByClasstime(Integer courseLocationId, Date day, Integer classTimeId) throws Exception {
		log.debug("CourseDaoImp.getCoursecalendarBycourseLocationByDayByClasstime()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.avaliable='yes' and c.seatLeft>0 and c.courselocation.courseLocationId=" + courseLocationId + " and c.classDate='" + sdf.format(day) + "' and c.classtime.classTimeId=" + classTimeId);
		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
		return list;
	}
}
