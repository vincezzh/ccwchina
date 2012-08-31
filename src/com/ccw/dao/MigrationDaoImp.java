package com.ccw.dao;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MigrationDaoImp implements MigrationDaoInf {
//	private EntityManager em;
//	
//	public EntityManager getEntityManager() {
//		return em;
//	}
//	
//	@PersistenceContext
//	public void setEntityManager(EntityManager em) {
//		this.em = em;
//	}
//
//	public ArrayList<UserInformation> getAllUserInformation() {
//		Query query = getEntityManager().createQuery("select u from UserInformation u");
//		ArrayList<UserInformation> list = (ArrayList<UserInformation>)query.getResultList();
//		return list;
//	}
//
//	public void saveUserdetail(Userdetail user) {
//		getEntityManager().persist(getEntityManager().merge(user));
//	}
//
//	public ArrayList<CourseContent> getAllCourseContent() {
//		Query query = getEntityManager().createQuery("select c from CourseContent c");
//		ArrayList<CourseContent> list = (ArrayList<CourseContent>)query.getResultList();
//		return list;
//	}
//
//	public void saveCourse(Course course) {
//		getEntityManager().persist(getEntityManager().merge(course));
//	}
//
//	public ArrayList<CoursesCalendarWensi> getAllCoursesCalendarWensi() {
//		Query query = getEntityManager().createQuery("select c from CoursesCalendarWensi c");
//		ArrayList<CoursesCalendarWensi> list = (ArrayList<CoursesCalendarWensi>)query.getResultList();
//		return list;
//	}
//
//	public void saveCoursecalendar(Coursecalendar cc) {
//		getEntityManager().persist(getEntityManager().merge(cc));
//	}
//
//	public ArrayList<Course> getAllCourseByTrunktype(Integer typeId) {
//		Query query = getEntityManager().createQuery("select c from Course c where c.coursetrunktype.courseTrunkTypeId=" + typeId);
//		ArrayList<Course> list = (ArrayList<Course>)query.getResultList();
//		return list;
//	}
//
//	public void saveCalendarwithcourse(Calendarwithcourse cwc) {
//		getEntityManager().persist(getEntityManager().merge(cwc));
//	}
//
//	public ArrayList<OrderForm> getAllOrderForm() {
//		Query query = getEntityManager().createQuery("select o from OrderForm o");
//		ArrayList<OrderForm> list = (ArrayList<OrderForm>)query.getResultList();
//		return list;
//	}
//
//	public ArrayList<Userdetail> getAllUserdetail() {
//		Query query = getEntityManager().createQuery("select u from Userdetail u");
//		ArrayList<Userdetail> list = (ArrayList<Userdetail>)query.getResultList();
//		return list;
//	}
//
//	public void saveOrderbasic(Orderbasic ob) {
//		getEntityManager().persist(getEntityManager().merge(ob));
//	}
//
//	public void saveOrderpublic(Orderpublic op) {
//		getEntityManager().persist(getEntityManager().merge(op));
//	}
//
//	public Coursecalendar getCoursecalendarByMany(Date courseDate,
//			String courseTime, String courseLocation, String courseType) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String courseTimeSql = "";
//		if("10".equals(courseTime)) {
//			courseTimeSql = " and c.classtime.classTimeId=1";
//		}else if("13".equals(courseTime)) {
//			courseTimeSql = " and c.classtime.classTimeId=2";
//		}else if("19".equals(courseTime)) {
//			courseTimeSql = " and c.classtime.classTimeId=3";
//		}else {
//			return null;
//		}
//		
//		String courseLocationSql = "";
//		if("Shanghai Puxi".equals(courseLocation)) {
//			courseLocationSql = " and c.courselocation.courseLocationId=1";
//		}else if("Shanghai Pudong".equals(courseLocation)) {
//			courseLocationSql = " and c.courselocation.courseLocationId=2";
//		}else if("Suzhou SND".equals(courseLocation)) {
//			courseLocationSql = " and c.courselocation.courseLocationId=3";
//		}else {
//			return null;
//		}
//		
//		String courseTypeSql = "";
//		if("dim sum".equals(courseType)) {
//			courseTypeSql = " and c.coursetrunktype.courseTrunkTypeId=1";
//		}else if("wok".equals(courseType)) {
//			courseTypeSql = " and c.coursetrunktype.courseTrunkTypeId=2";
//		}else {
//			return null;
//		}
//		
//		Query query = getEntityManager().createQuery("select c from Coursecalendar c where c.classDate='" + sdf.format(courseDate) + "'" + courseTimeSql + courseLocationSql + courseTypeSql);
//		ArrayList<Coursecalendar> list = (ArrayList<Coursecalendar>)query.getResultList();
//		if(list.size() == 1)
//			return list.get(0);
//		else
//			return null;
//	}
//
//	public ArrayList<MailList> getAllMailList() {
//		Query query = getEntityManager().createQuery("select m from MailList m");
//		ArrayList<MailList> list = (ArrayList<MailList>)query.getResultList();
//		return list;
//	}
//
//	public void saveInfomaillist(Infomaillist im) {
//		getEntityManager().persist(getEntityManager().merge(im));
//	}
}
