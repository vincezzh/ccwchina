package com.ccw.action.admin.migration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.dao.CommonDaoInf;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.MigrationDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class MigrationAction extends ActionSupport {
	private static final long serialVersionUID = -1853975849383848247L;
	private Log log = LogFactory.getLog(MigrationAction.class);
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	private CommonDaoInf commonDao;
	private CourseDaoInf courseDao;
	private MigrationDaoInf migrationDao;
	
	public UserDaoInf getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}
	public OrderDaoInf getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}
	public CommonDaoInf getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}
	public CourseDaoInf getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}
	public MigrationDaoInf getMigrationDao() {
		return migrationDao;
	}
	public void setMigrationDao(MigrationDaoInf migrationDao) {
		this.migrationDao = migrationDao;
	}
	
	public String migrateFromWensiToCCW() throws Exception {
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~ Migrate Database Start ~~~~~~~~~~~~~~~~~~~~~~~");
		//migrateUserdetail();
		//migrateMaillist();
		//migrateCourse();
		//migrateCourseCalendar();
		//migratePublicOrder();
		
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~ Migrate Database End ~~~~~~~~~~~~~~~~~~~~~~~");
		
		return null;
	}
	
//	private void migrateMaillist() throws Exception {
//		log.debug("MigrationAction.migrateMaillist()");
//		ArrayList<MailList> mailList = migrationDao.getAllMailList();
//		Iterator<MailList> iMailList = mailList.iterator();
//		MailList m = null;
//		Infomaillist im = null;
//		while(iMailList.hasNext()) {
//			m = iMailList.next();
//			im = new Infomaillist();
//			im.setAvaliable("yes");
//			im.setEmail(m.getEmail());
//			im.setFirstname(m.getFirstName());
//			im.setJointime(m.getAddTime());
//			im.setLastname(m.getLastName());
//			
//			migrationDao.saveInfomaillist(im);
//		}
//	}
//	
//	private void migratePublicOrder() throws Exception {
//		log.debug("MigrationAction.migratePublicOrder()");
//		HashMap<String, Userdetail> allUsers = new HashMap<String, Userdetail>();
//		ArrayList<Userdetail> userList = migrationDao.getAllUserdetail();
//		Iterator<Userdetail> iUserList = userList.iterator();
//		Userdetail user = null;
//		while(iUserList.hasNext()) {
//			user = iUserList.next();
//			allUsers.put(user.getUserId(), user);
//		}
//		
//		ArrayList<OrderForm> orderList = migrationDao.getAllOrderForm();
//		Iterator<OrderForm> iOrderList = orderList.iterator();
//		OrderForm of = null;
//		Orderpublic op = null;
//		Orderbasic ob = null;
//		while(iOrderList.hasNext()) {
//			of = iOrderList.next();
//			
//			Confirmtime ct = new Confirmtime();
//			if("09:00-12:00".equals(of.getConfirmTime())) {
//				ct.setConfirmTimeId(1);
//			}else if("13:00-15:00".equals(of.getConfirmTime())) {
//				ct.setConfirmTimeId(2);
//			}else if("16:00-19:00".equals(of.getConfirmTime())) {
//				ct.setConfirmTimeId(3);
//			}else if("20:00-22:00".equals(of.getConfirmTime())) {
//				ct.setConfirmTimeId(4);
//			}else {
//				continue;
//			}
//			
//			Peopletitle pt = new Peopletitle();
//			if("Mr.".equals(of.getPersonTitle())) {
//				pt.setPeopleTitleId(1);
//			}else if("Ms.".equals(of.getPersonTitle())) {
//				pt.setPeopleTitleId(2);
//			}else if("Mrs.".equals(of.getPersonTitle())) {
//				pt.setPeopleTitleId(3);
//			}else if("Miss".equals(of.getPersonTitle())) {
//				pt.setPeopleTitleId(4);
//			}else {
//				continue;
//			}
//			
//			Orderstatus os = new Orderstatus();
//			if("booking".equals(of.getStatus())) {
//				os.setOrderStatusId(1);
//			}else if("confirmed".equals(of.getStatus())) {
//				os.setOrderStatusId(3);
//			}else {
//				continue;
//			}
//			
//			Coursecalendar cc = migrationDao.getCoursecalendarByMany(of.getCourseDate(), of.getCourseTime(), of.getCourseLocation(), of.getCourseType());
//			if(cc == null)
//				continue;
//			
//			Date now = new Date();
//			ob = new Orderbasic();
//			ob.setAddress(of.getAddress());
//			ob.setAllergic(of.getAllergic());
//			ob.setAvaliable("yes");
//			ob.setBookingTime(of.getOrderDate());
//			ob.setCellphone(of.getCellphone());
//			ob.setCheckingTime(now);
//			ob.setConfirmtime(ct);
//			ob.setContactPerson(of.getOrderPerson());
//			ob.setDescription(of.getDescription());
//			ob.setEmail(of.getEmail());
//			ob.setFax(of.getFax());
//			if("yes".equals(of.getIsVegetarian()))
//				ob.setIsVegetarian("true");
//			else
//				ob.setIsVegetarian("false");
//			ob.setOrderBasicId("PU-" + now.getTime());
//			ob.setOrderstatus(os);
//			ob.setPeopletitle(pt);
//			ob.setPoints(0);
//			ob.setTelephone(of.getTelephone());
//			if(allUsers.get(of.getUsername()) != null)
//				ob.setUserdetail(allUsers.get(of.getUsername()));
//			else
//				ob.setUserdetail(allUsers.get("anonymous"));
//			ob.setZipCode(of.getZipCode());
//			
//			migrationDao.saveOrderbasic(ob);
//			
//			op = new Orderpublic();
//			op.setAvaliable("yes");
//			op.setCoursecalendar(cc);
//			if("yes".equals(of.getNeedMap()))
//				op.setNeedMap("true");
//			else
//				op.setNeedMap("false");
//			op.setOrderbasic(ob);
//			op.setOrderPublicId("PU-" + now.getTime());
//			op.setTotalPeopleNumber(of.getFriendsPlus() + 1);
//			op.setTotalPrice(op.getTotalPeopleNumber() * 150);
//			
//			migrationDao.saveOrderpublic(op);
//		}
//	}
//	
//	private void migrateCourseCalendar() throws Exception {
//		log.debug("MigrationAction.migrateCourseCalendar()");
//		HashMap<String, HashMap<String, String>> allTypes = new HashMap<String, HashMap<String, String>>();
//		ArrayList<Course> courseListDimsum = migrationDao.getAllCourseByTrunktype(1);
//		ArrayList<Course> courseListWok = migrationDao.getAllCourseByTrunktype(2);
//		Iterator<Course> iCourseListDimsum = courseListDimsum.iterator();
//		Iterator<Course> iCourseListWok = courseListWok.iterator();
//		HashMap<String, String> dimsum = new HashMap<String, String>();
//		HashMap<String, String> wok = new HashMap<String, String>();
//		Course tempCourse = null;
//		while(iCourseListDimsum.hasNext()) {
//			tempCourse = iCourseListDimsum.next();
//			dimsum.put(tempCourse.getCourseNameEn(), tempCourse.getCourseId());
//		}
//		while(iCourseListWok.hasNext()) {
//			tempCourse = iCourseListWok.next();
//			wok.put(tempCourse.getCourseNameEn(), tempCourse.getCourseId());
//		}
//		allTypes.put("dim sum", dimsum);
//		allTypes.put("wok", wok);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		ArrayList<CoursesCalendarWensi> ccwList = migrationDao.getAllCoursesCalendarWensi();
//		Iterator<CoursesCalendarWensi> iCcwList = ccwList.iterator();
//		CoursesCalendarWensi ccw = null;
//		Coursecalendar cc = null;
//		while(iCcwList.hasNext()) {
//			ccw = iCcwList.next();
//			
//			Courselocation cl = new Courselocation();
//			if("Shanghai Puxi".equals(ccw.getId().getCourseLocation())) {
//				cl.setCourseLocationId(1);
//			}else if("Shanghai Pudong".equals(ccw.getId().getCourseLocation())) {
//				cl.setCourseLocationId(2);
//			}else if("Suzhou SND".equals(ccw.getId().getCourseLocation())) {
//				cl.setCourseLocationId(3);
//			}else {
//				continue;
//			}
//			
//			Classtime ct = new Classtime();
//			if(ccw.getId().getTime() == 10) {
//				ct.setClassTimeId(1);
//			}else if(ccw.getId().getTime() == 13) {
//				ct.setClassTimeId(2);
//			}else if(ccw.getId().getTime() == 19) {
//				ct.setClassTimeId(3);
//			}else {
//				continue;
//			}
//			
//			Coursetrunktype cTrunk = new Coursetrunktype();
//			if("dim sum".equals(ccw.getId().getCourseType())) {
//				cTrunk.setCourseTrunkTypeId(1);
//			}else if("wok".equals(ccw.getId().getCourseType())) {
//				cTrunk.setCourseTrunkTypeId(2);
//			}else {
//				continue;
//			}
//			
//			cc = new Coursecalendar();
//			cc.setAvaliable("yes");
//			cc.setClassDate(sdf.parse(ccw.getId().getYear() + "-" + ccw.getId().getMonth() + "-" + ccw.getId().getDay()));
//			cc.setClasstime(ct);
//			cc.setCourselocation(cl);
//			cc.setCourseAdvise("");
//			Date now = new Date();
//			cc.setCourseCalendarId("L-" + now.getTime());
//			cc.setCoursetrunktype(cTrunk);
//			Coursebranchtype cBranch = new Coursebranchtype();
//			if("dim sum".equals(ccw.getId().getCourseType())) {
//				cBranch.setCourseBranchTypeId(1);
//			}else {
//				cBranch.setCourseBranchTypeId(6);
//			}
//			cc.setCoursebranchtype(cBranch);
//			cc.setPoints(0);
//			cc.setPricePerPerson(150);
//			cc.setSeatLeft(ccw.getSeatLeft());
//			Specialtype st = new Specialtype();
//			st.setSpecialTypeId(1);
//			cc.setSpecialtype(st);
//			
//			migrationDao.saveCoursecalendar(cc);
//			
//			String[] courses = ccw.getCourseContent().split(";");
//			Set<Calendarwithcourse> calendarwithcourse = new HashSet<Calendarwithcourse>();
//			Calendarwithcourse cwc = null;
//			if("dim sum".equals(ccw.getId().getCourseType())) {
//				for(String course : courses) {
//					Course cz = new Course();
//					Coursecalendar ccz = new Coursecalendar();
//					String courseId = allTypes.get("dim sum").get(course);
//					if(courseId != null)
//						cz.setCourseId(courseId);
//					else
//						continue;
//					ccz.setCourseCalendarId(cc.getCourseCalendarId());
//					cwc = new Calendarwithcourse();
//					cwc.setCourse(cz);
//					cwc.setCoursecalendar(ccz);
//					
//					calendarwithcourse.add(cwc);
//					migrationDao.saveCalendarwithcourse(cwc);
//				}
//			}else if("wok".equals(ccw.getId().getCourseType())) {
//				for(String course : courses) {
//					Course cz = new Course();
//					Coursecalendar ccz = new Coursecalendar();
//					String courseId = allTypes.get("wok").get(course);
//					if(courseId != null)
//						cz.setCourseId(courseId);
//					else
//						continue;
//					ccz.setCourseCalendarId(cc.getCourseCalendarId());
//					cwc = new Calendarwithcourse();
//					cwc.setCourse(cz);
//					cwc.setCoursecalendar(ccz);
//					
//					calendarwithcourse.add(cwc);
//					migrationDao.saveCalendarwithcourse(cwc);
//				}
//			}
//		}
//	}
//	
//	private void migrateCourse() throws Exception {
//		log.debug("MigrationAction.migrateCourse()");
//		ArrayList<CourseContent> coursecontentList = migrationDao.getAllCourseContent();
//		Iterator<CourseContent> iCoursecontentList = coursecontentList.iterator();
//		CourseContent cc = null;
//		Course c = null;
//		while(iCoursecontentList.hasNext()) {
//			cc = iCoursecontentList.next();
//			
//			Coursetrunktype trunk = new Coursetrunktype();
//			Coursebranchtype branch = new Coursebranchtype();
//			if("dim sum".equals(cc.getId().getCourseType())) {
//				trunk.setCourseTrunkTypeId(1);
//				branch.setCourseBranchTypeId(1);
//			}else if("wok".equals(cc.getId().getCourseType())) {
//				trunk.setCourseTrunkTypeId(2);
//				branch.setCourseBranchTypeId(6);
//			}else {
//				continue;
//			}
//			
//			c = new Course();
//			c.setAvaliable("yes");
//			c.setCoursetrunktype(trunk);
//			c.setCoursebranchtype(branch);
//			String type = cc.getId().getCourseType();
//			String content = cc.getId().getCourseContent();
//			c.setCourseDescriptionCn(type + " " + content);
//			c.setCourseDescriptionEn(type + " " + content);
//			c.setCourseDescriptionJp(type + " " + content);
//			Date now = new Date();
//			c.setCourseId("C-" + now.getTime());
//			c.setCourseNameCn(content);
//			c.setCourseNameEn(content);
//			c.setCourseNameJp(content);
//			if("dim sum".equals(cc.getId().getCourseType())) {
//				c.setPictureOne("/uploaded/dim_sum/" + c.getCourseNameEn().toLowerCase().replace(" ", "_") + ".jpg");
//				c.setPictureTwo("/uploaded/dim_sum/" + c.getCourseNameEn().toLowerCase().replace(" ", "_") + ".jpg");
//			}else if("wok".equals(cc.getId().getCourseType())) {
//				c.setPictureOne("/uploaded/wok/" + c.getCourseNameEn().toLowerCase().replace(" ", "_") + ".jpg");
//				c.setPictureTwo("/uploaded/wok/" + c.getCourseNameEn().toLowerCase().replace(" ", "_") + ".jpg");
//			}
//			c.setPictureThree("");
//			c.setPictureFour("");
//			c.setSearchDescription(type + " " + content);
//			c.setSearchKeyWords(type + " " + content);
//			
//			migrationDao.saveCourse(c);
//		}
//	}
//	
//	private void migrateUserdetail() throws Exception {
//		log.debug("MigrationAction.migrateUserdetail()");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		ArrayList<UserInformation> userList = migrationDao.getAllUserInformation();
//		Iterator<UserInformation> iUserList = userList.iterator();
//		UserInformation ui = null;
//		Userdetail ud = null;
//		while(iUserList.hasNext()) {
//			ui = iUserList.next();
//			
//			Peopletitle pt = new Peopletitle();
//			if("Mr.".equals(ui.getTitle())) {
//				pt.setPeopleTitleId(1);
//			}else if("Ms.".equals(ui.getTitle())) {
//				pt.setPeopleTitleId(2);
//			}else if("Mrs.".equals(ui.getTitle())) {
//				pt.setPeopleTitleId(3);
//			}else if("Miss".equals(ui.getTitle())) {
//				pt.setPeopleTitleId(4);
//			}else {
//				pt.setPeopleTitleId(2);
//			}
//			
//			ud = new Userdetail();
//			ud.setAddress(ui.getAddress());
//			ud.setAllergic("");
//			ud.setAvaliable("yes");
//			ud.setCellphone(ui.getCellphone());
//			Courselocation location = new Courselocation();
//			location.setCourseLocationId(1);
//			ud.setCourselocation(location);
//			ud.setEmail(ui.getEmail());
//			ud.setFax(ui.getFax());
//			ud.setFirstName(ui.getFirstName());
//			ud.setIsVegetarian("false");
//			ud.setLastName(ui.getLastName());
//			ud.setNeedCoupon("true");
//			ud.setNeedNews("true");
//			ud.setNickName(ui.getUsername());
//			ud.setPassword(ui.getPassword());
//			ud.setPeopletitle(pt);
//			ud.setPoints(0);
//			ud.setPreferredLanguage("en");
//			ud.setRegisterTime(sdf.parse("2011-03-01"));
//			ud.setTelephone(ui.getTelephone());
//			ud.setUserId(ui.getUsername());
//			ud.setZipcode(ui.getZipCode());
//			
//			migrationDao.saveUserdetail(ud);
//		}
//	}
}
