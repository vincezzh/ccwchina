package com.ccw.action.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Classtime;
import com.ccw.bean.Confirmtime;
import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Coursepackage;
import com.ccw.bean.Coursetrunktype;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Specialtype;
import com.ccw.bean.Userdetail;
import com.ccw.common.CommonUtil;
import com.ccw.common.DesEncrypt;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PrivateOrderAction extends ActionSupport {
	private static final long serialVersionUID = -257111648341237481L;
	private Log log = LogFactory.getLog(PrivateOrderAction.class);
	private Date date;
	private String courseDate;
	private Integer classTimeId;
	private Integer courseLocationId;
	private Userdetail user;
	private ArrayList<Peopletitle> titleList;
	private ArrayList<Confirmtime> confirmTimeList;
	private Courselocation courseLocation;
	private Classtime classTime;
	private Orderprivate order;
	private ArrayList<String> orderIds;
	private String contactPerson;
	private String customerLocation;
	private String customerClassTime;
	private String language;
	private HashMap<Coursetrunktype, ArrayList<Coursepackage>> packages;
	private String mp;
	private String flag;
	private CourseDaoInf courseDao;
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;

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

	public Integer getCourseLocationId() {
		return courseLocationId;
	}

	public void setCourseLocationId(Integer courseLocationId) {
		this.courseLocationId = courseLocationId;
	}

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

	public Courselocation getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(Courselocation courseLocation) {
		this.courseLocation = courseLocation;
	}

	public Orderprivate getOrder() {
		return order;
	}

	public void setOrder(Orderprivate order) {
		this.order = order;
	}

	public Classtime getClassTime() {
		return classTime;
	}

	public void setClassTime(Classtime classTime) {
		this.classTime = classTime;
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

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public String getCustomerClassTime() {
		return customerClassTime;
	}

	public void setCustomerClassTime(String customerClassTime) {
		this.customerClassTime = customerClassTime;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public String getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public HashMap<Coursetrunktype, ArrayList<Coursepackage>> getPackages() {
		return packages;
	}

	public void setPackages(HashMap<Coursetrunktype, ArrayList<Coursepackage>> packages) {
		this.packages = packages;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String gotoPrivateOrderPage() {
		try {
			log.debug("PrivateOrderAction.gotoPrivateOrderPage()");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(courseDate);
			if(date.compareTo(new Date()) < 0)
				return INPUT;

			Map session = ActionContext.getContext().getSession();
			user = (Userdetail) session.get("user_session");
			if (user == null)
				user = new Userdetail();

			courseLocation = courseDao.getDetailCourselocation(courseLocationId);
			titleList = userDao.getAllPeopletitle();
			confirmTimeList = userDao.getAllConfirmTime();
			classTime = courseDao.getDetailClasstime(classTimeId);
			
			packages = courseDao.getAllCoursepackageWithCoursetrunktype();
			
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
	
	public String bookPrivateOrder() {
		try {
			log.debug("PrivateOrderAction.bookPrivateOrder()");

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
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(courseDate);
			order.setCourseDate(date);
			order.setAvaliable("yes");
			order.setOrderPrivateId("PR-" + now.getTime());
			order.getOrderbasic().setOrderBasicId("PR-" + now.getTime());
			order.getOrderbasic().setAvaliable("yes");
			order.getOrderbasic().setBookingTime(now);
			order.getOrderbasic().setUserdetail(user);
			
			orderDao.saveOrderbasic(order.getOrderbasic());
			orderDao.savePrivateOrder(order);

			Coursecalendar course = new Coursecalendar();
			course.setAvaliable("yes");
			Coursetrunktype t = new Coursetrunktype();
			t.setCourseTrunkTypeId(Params.PRIVATE_TRUNKTYPE_ID);
			course.setCoursetrunktype(t);
			Coursebranchtype b = new Coursebranchtype();
			b.setCourseBranchTypeId(Params.PRIVATE_BRANCHTYPE_ID);
			course.setCoursebranchtype(b);
			Specialtype s = new Specialtype();
			s.setSpecialTypeId(Params.SPECIAL_TYPE_NORMAL_ID);
			course.setSpecialtype(s);
			course.setCourselocation(order.getCourselocation());
			course.setClasstime(order.getClasstime());
			course.setClassDate(date);
			course.setSeatLeft(1);
			course.setCourseCalendarId("L-" + now.getTime());
			course.setPoints(0);
			course.setPricePerPerson(order.getPricePerPerson());
			
			courseDao.addCoursecalendar(course);
			
			contactPerson = order.getOrderbasic().getContactPerson();
			orderIds = new ArrayList<String>();
			orderIds.add("PR-" + now.getTime());
			
			sendMails();
			
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
		
		Classtime ct = courseDao.getDetailClasstime(order.getClasstime().getClassTimeId());
		Courselocation cl = courseDao.getDetailCourselocation(order.getCourselocation().getCourseLocationId());
		String templatePath = "/mail_template/order/booking_order_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", order.getOrderbasic().getContactPerson());
		content.put("#orderId#", order.getOrderPrivateId());
		content.put("#course#", PropertyUtil.get(locale.getLanguage(), "PRIVATE"));
		content.put("#location#", cl.getCourseLocationName());
		content.put("#date#", sdf.format(order.getCourseDate()));
		content.put("#time#", ct.getClassTimeContent());
		content.put("#person#", String.valueOf(order.getTotalPeopleNumber()));
		String[] emailAndContactPerson = new String[]{order.getOrderbasic().getEmail() + "_with_" + order.getOrderbasic().getContactPerson()};
		SendMail spom = new SendMail(Params.BOOKING_PRIVATE_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
		spom.start();
		
		Peopletitle pt = userDao.getDetailPeopletitle(order.getOrderbasic().getPeopletitle().getPeopleTitleId());
		String path = PrivateOrderAction.class.getResource("/").getPath();
		String adminEmail = CommonUtil.readProperty("ADMIN_EMAIL", path + "ccw.properties");
		templatePath = "/mail_template/reminder/admin_reminder_template_zh.txt";
		content = new HashMap<String, String>();
		content.put("#orderType#", "私人课程");
		content.put("#bookingTime#", sdf1.format(order.getOrderbasic().getBookingTime()));
		content.put("#kitchen#", cl.getCourseLocationName());
		content.put("#date#", sdf.format(order.getCourseDate()));
		content.put("#time#", ct.getClassTimeContent());
		content.put("#number#", String.valueOf(order.getTotalPeopleNumber()));
		content.put("#name#", pt.getPeopleTitleName() + " " + order.getOrderbasic().getContactPerson());
		content.put("#cellphone#", order.getOrderbasic().getCellphone());
		content.put("#email#", order.getOrderbasic().getEmail());
		emailAndContactPerson = new String[]{adminEmail + "_with_CCW Auto Robot"};
		spom = new SendMail(Params.ADMIN_BOOKING_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson, true);
		spom.start();
	}
	
	public String mailPrivateOrderPage() {
		try {
			log.debug("PrivateOrderAction.mailPrivateOrderPage()");
			
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(courseDate);
			if(date.compareTo(new Date()) < 0)
				return INPUT;

			courseLocation = courseDao.getDetailCourselocation(courseLocationId);
			titleList = userDao.getAllPeopletitle();
			confirmTimeList = userDao.getAllConfirmTime();
			classTime = courseDao.getDetailClasstime(classTimeId);
			
			packages = courseDao.getAllCoursepackageWithCoursetrunktype();
			
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if(locale == null) {
				locale = Locale.US;
				session.put("WW_TRANS_I18N_LOCALE", locale);
			}
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
