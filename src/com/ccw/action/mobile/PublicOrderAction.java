package com.ccw.action.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Confirmtime;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Orderpublic;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.common.CommonUtil;
import com.ccw.common.DesEncrypt;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.common.SamePackageUtil;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PublicOrderAction extends ActionSupport {
	private static final long serialVersionUID = -8283580165545570936L;
	private Log log = LogFactory.getLog(PublicOrderAction.class);
	private String courseCalendarId;
	private Userdetail user;
	private Coursecalendar course;
	private Orderpublic order;
	private ArrayList<Peopletitle> titleList;
	private ArrayList<Confirmtime> confirmTimeList;
	private String orderFullMonth;
	private Integer seatLeft;
	private Integer pricePerPerson;
	private ArrayList<Date> packageClassesDate;
	private String plusCourseCalendarid;
	private CourseDaoInf courseDao;
	private ArrayList<String> orderIds;
	private String contactPerson;
	private Integer courseLocationId;
	private Date date;
	private String language;
	private String mp;
	private String cid;
	private String flag;
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	
	public Userdetail getUser() {
		return user;
	}
	public void setUser(Userdetail user) {
		this.user = user;
	}
	public CourseDaoInf getCourseDao() {
		return courseDao;
	}
	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}
	public UserDaoInf getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}
	public String getCourseCalendarId() {
		return courseCalendarId;
	}
	public void setCourseCalendarId(String courseCalendarId) {
		this.courseCalendarId = courseCalendarId;
	}
	public Coursecalendar getCourse() {
		return course;
	}
	public void setCourse(Coursecalendar course) {
		this.course = course;
	}
	public Orderpublic getOrder() {
		return order;
	}
	public void setOrder(Orderpublic order) {
		this.order = order;
	}
	public ArrayList<Peopletitle> getTitleList() {
		return titleList;
	}
	public void setTitleList(ArrayList<Peopletitle> titleList) {
		this.titleList = titleList;
	}
	public ArrayList<Confirmtime> getConfirmTimeList() {
		return confirmTimeList;
	}
	public void setConfirmTimeList(ArrayList<Confirmtime> confirmTimeList) {
		this.confirmTimeList = confirmTimeList;
	}
	public String getOrderFullMonth() {
		return orderFullMonth;
	}
	public void setOrderFullMonth(String orderFullMonth) {
		this.orderFullMonth = orderFullMonth;
	}
	public Integer getSeatLeft() {
		return seatLeft;
	}
	public void setSeatLeft(Integer seatLeft) {
		this.seatLeft = seatLeft;
	}
	public ArrayList<Date> getPackageClassesDate() {
		return packageClassesDate;
	}
	public void setPackageClassesDate(ArrayList<Date> packageClassesDate) {
		this.packageClassesDate = packageClassesDate;
	}
	public String getPlusCourseCalendarid() {
		return plusCourseCalendarid;
	}
	public void setPlusCourseCalendarid(String plusCourseCalendarid) {
		this.plusCourseCalendarid = plusCourseCalendarid;
	}
	public OrderDaoInf getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}
	public ArrayList<String> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(ArrayList<String> orderIds) {
		this.orderIds = orderIds;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public Integer getPricePerPerson() {
		return pricePerPerson;
	}
	public void setPricePerPerson(Integer pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
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
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getMp() {
		return mp;
	}
	
	public void setMp(String mp) {
		this.mp = mp;
	}
	
	public String getCid() {
		return cid;
	}
	
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String gotoPublicOrderPage() {
		try {
			log.debug("PublicOrderAction.gotoPublicOrderPage()");
			
			course = courseDao.getDetailCoursecalendar(courseCalendarId);
			if(course.getClassDate().compareTo(new Date()) < 0)
				return INPUT;
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail) session.get("user_session");
			if (user == null)
				user = new Userdetail();
			
			titleList = userDao.getAllPeopletitle();
			confirmTimeList = userDao.getAllConfirmTime();
			ArrayList<Coursecalendar> courseList = courseDao.getCoursecalendarBycourseLocationByMonth(course.getCourselocation().getCourseLocationId(), course.getClassDate());
			Object[] info = SamePackageUtil.findOutSamePackage(course, courseList);
			packageClassesDate = (ArrayList<Date>)info[0];
			plusCourseCalendarid = (String)info[1];
			
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
	
	public String bookPublicOrder() {
		try {
			log.debug("PublicOrderAction.bookPublicOrder()");
			
			Map session = ActionContext.getContext().getSession();
			Userdetail tempUser = (Userdetail)session.get("user_session");
			if(tempUser == null) {
				tempUser = new Userdetail();
//				Long totalNumber = userDao.getAllNamedUserCount(Params.AUTOMATIC_REGISTERED_USER_FORMAT);
//				tempUser.setUserId(Params.AUTOMATIC_REGISTERED_USER_FORMAT + (totalNumber + 1));
//				tempUser.setNickName(Params.AUTOMATIC_REGISTERED_USER_FORMAT + (totalNumber + 1));
				String u = order.getOrderbasic().getEmail();
				if(u.indexOf("@") != -1)
					u = CommonUtil.StringFilter(u.substring(0, u.indexOf("@")));
				int num = 1;
				String checkUserName = u;
				while(userDao.getDetailUser(checkUserName) != null) {
					checkUserName = u + num;
					num++;
				}
				tempUser.setUserId(checkUserName);
				tempUser.setNickName(checkUserName);
				tempUser.setFirstName("");
				tempUser.setLastName("");
				tempUser.setCellphone(order.getOrderbasic().getCellphone());
				tempUser.setPassword(Params.AUTOMATIC_REGISTERED_DEFAULT_PASSWORD);
				tempUser.setEmail(order.getOrderbasic().getEmail());
				tempUser.setBirthday(new Date());
				Courselocation cl = new Courselocation();
				cl.setCourseLocationId(1);
				tempUser.setCourselocation(cl);
				Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
				if(locale == null) {
					locale = Locale.US;
					session.put("WW_TRANS_I18N_LOCALE", locale);
				}
				tempUser.setPreferredLanguage(locale.getLanguage());
				tempUser.setNeedNews("true");
				tempUser.setNeedCoupon("true");
				tempUser.setAvaliable("yes");
				tempUser.setIsVegetarian("false");
				tempUser.setPoints(0);
				tempUser.setRegisterTime(new Date());
				
				userDao.register(tempUser);
				session.put("user_session", tempUser);
				if(Params.CHINA.equals(tempUser.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
				}else if(Params.JAPAN.equals(tempUser.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.JAPAN);
				}else {
					session.put("WW_TRANS_I18N_LOCALE", Locale.US);
				}
				
				String templatePath = "/mail_template/register/register_template_" + tempUser.getPreferredLanguage() + ".txt";
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("#nickname#", tempUser.getNickName());
				content.put("#userId#", tempUser.getUserId());
				content.put("#password#", tempUser.getPassword());
				String[] emailAndContactPerson = new String[]{tempUser.getEmail() + "_with_" + tempUser.getNickName()};
				SendMail spom = new SendMail(Params.REGISTER_SUCCESSFUL, templatePath, content, emailAndContactPerson);
				spom.start();
			}
			user = tempUser;
			
			String[] allCourse = (courseCalendarId + ";" + plusCourseCalendarid).split(";");
			Coursecalendar cc = null;
			int totalPersonNum = order.getTotalPeopleNumber() + 1;
			orderIds = new ArrayList<String>();
			contactPerson = order.getOrderbasic().getContactPerson();
			for(String id : allCourse) {
				Date now = new Date();
				order.setAvaliable("yes");
				order.setOrderPublicId("PU-" + now.getTime());
				orderIds.add("PU-" + now.getTime());
				order.setTotalPeopleNumber(totalPersonNum);
				order.setTotalPrice(order.getTotalPeopleNumber() * pricePerPerson);
				order.getOrderbasic().setOrderBasicId("PU-" + now.getTime());
				order.getOrderbasic().setAvaliable("yes");
				order.getOrderbasic().setBookingTime(now);
				order.getOrderbasic().setUserdetail(user);
				cc = new Coursecalendar();
				cc.setCourseCalendarId(id);
				order.setCoursecalendar(cc);
				
				orderDao.saveOrderbasic(order.getOrderbasic());
				orderDao.savePublicOrder(order);
				
				course = courseDao.getDetailCoursecalendar(id);
				course.setSeatLeft(course.getSeatLeft() - totalPersonNum);
				courseDao.updateCoursecalendar(course);
				
				sendMails();
				
				if("false".equals(orderFullMonth))
					break;
			}
			
			courseLocationId = course.getCourselocation().getCourseLocationId();
			date = course.getClassDate();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
		
	private void sendMails() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
		if(locale == null) {
			locale = Locale.US;
			session.put("WW_TRANS_I18N_LOCALE", locale);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coursecalendar cc = courseDao.getDetailCoursecalendar(order.getCoursecalendar().getCourseCalendarId());
		
		String templatePath = "/mail_template/order/booking_order_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", order.getOrderbasic().getContactPerson());
		content.put("#orderId#", order.getOrderPublicId());
		content.put("#course#", PropertyUtil.get(locale.getLanguage(), cc.getCoursetrunktype().getCourseTrunkTypeNameKey()));
		content.put("#location#", cc.getCourselocation().getCourseLocationName());
		content.put("#date#", sdf.format(cc.getClassDate()));
		content.put("#time#", cc.getClasstime().getClassTimeContent());
		content.put("#person#", String.valueOf(order.getTotalPeopleNumber()));
		String[] emailAndContactPerson = new String[]{order.getOrderbasic().getEmail() + "_with_" + order.getOrderbasic().getContactPerson()};
		SendMail spom = new SendMail(Params.BOOKING_PUBLIC_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
		spom.start();
		
		Peopletitle pt = userDao.getDetailPeopletitle(order.getOrderbasic().getPeopletitle().getPeopleTitleId());
		String path = PublicOrderAction.class.getResource("/").getPath();
		String adminEmail = CommonUtil.readProperty("ADMIN_EMAIL", path + "ccw.properties");
		templatePath = "/mail_template/reminder/admin_reminder_template_zh.txt";
		content = new HashMap<String, String>();
		content.put("#orderType#", "公共课程");
		content.put("#bookingTime#", sdf1.format(order.getOrderbasic().getBookingTime()));
		content.put("#kitchen#", cc.getCourselocation().getCourseLocationName());
		content.put("#date#", sdf.format(cc.getClassDate()));
		content.put("#time#", cc.getClasstime().getClassTimeContent());
		content.put("#number#", String.valueOf(order.getTotalPeopleNumber()));
		content.put("#classtype#", PropertyUtil.get(user.getPreferredLanguage(), cc.getCoursetrunktype().getCourseTrunkTypeNameKey()));
		content.put("#name#", pt.getPeopleTitleName() + " " + order.getOrderbasic().getContactPerson());
		content.put("#cellphone#", order.getOrderbasic().getCellphone());
		content.put("#email#", order.getOrderbasic().getEmail());
		emailAndContactPerson = new String[]{adminEmail + "_with_CCW Auto Robot"};
		spom = new SendMail(Params.ADMIN_BOOKING_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
		spom.start();
	}
	
	public String mailPublicOrderPage() {
		try {
			log.debug("PublicOrderAction.mailPublicOrderPage()");
			
			Map session = ActionContext.getContext().getSession();
			user = new Userdetail();
			if(mp != null && !"".equals(mp)) {
				String decryptParams = DesEncrypt.decrypt(mp);
				String[] parameters = decryptParams.split(Params.ADMIN_MAIL_INTERVAL);
				
				user.setUserId(parameters[0]);
				user.setPassword(parameters[1]);
				
				user = userDao.login(user.getUserId(), user.getPassword());
				if(user != null) {
					session.put("user_session", user);
				}else {
					return LOGIN;
				}
			}
			
			courseCalendarId = cid;
			course = courseDao.getDetailCoursecalendar(courseCalendarId);
			if(course.getClassDate().compareTo(new Date()) < 0)
				return INPUT;

			titleList = userDao.getAllPeopletitle();
			confirmTimeList = userDao.getAllConfirmTime();
			ArrayList<Coursecalendar> courseList = courseDao.getCoursecalendarBycourseLocationByMonth(course.getCourselocation().getCourseLocationId(), course.getClassDate());
			Object[] info = SamePackageUtil.findOutSamePackage(course, courseList);
			packageClassesDate = (ArrayList<Date>)info[0];
			plusCourseCalendarid = (String)info[1];
			
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if("ja".equals(user.getPreferredLanguage())) {
				locale = Locale.JAPAN;
			}else if("zh".equals(user.getPreferredLanguage())) {
				locale = Locale.CHINA;
			}else {
				locale = Locale.US;
			}
			session.put("WW_TRANS_I18N_LOCALE", locale);
			language = locale.getLanguage();
			flag = "byMail";
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
