package com.ccw.action.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Orderpublic;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.common.CommonUtil;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class PublicOrderAction extends ActionSupport {
	private static final long serialVersionUID = -8283580165545570936L;
	private Log log = LogFactory.getLog(PublicOrderAction.class);
	private String courseCalendarId;
	private Userdetail user;
	private Coursecalendar course;
	private Orderpublic order;
	private Integer pricePerPerson;
	private CourseDaoInf courseDao;
	private String username;
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	
	public String getCourseCalendarId() {
		return courseCalendarId;
	}

	public void setCourseCalendarId(String courseCalendarId) {
		this.courseCalendarId = courseCalendarId;
	}

	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
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

	public Integer getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(Integer pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String bookPublicOrder() {
		log.debug("MobilePublicOrderAction.bookPublicOrder()");
		Document document = DocumentHelper.createDocument();  
        Element bookPublicOrder = document.addElement("bookPublicOrder");
		try {
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			courseCalendarId = new String(courseCalendarId.getBytes("ISO-8859-1"), "UTF-8");
			user = userDao.getDetailUser(username);
			
			Date now = new Date();
			order.setAvaliable("yes");
			order.setOrderPublicId("PU-" + now.getTime());
			order.setTotalPrice(order.getTotalPeopleNumber() * pricePerPerson);
			order.getOrderbasic().setOrderBasicId("PU-" + now.getTime());
			order.getOrderbasic().setAvaliable("yes");
			order.getOrderbasic().setBookingTime(now);
			order.getOrderbasic().setUserdetail(user);
			order.getOrderbasic().setContactPerson(new String(order.getOrderbasic().getContactPerson().getBytes("ISO-8859-1"), "UTF-8"));
			order.getOrderbasic().setEmail(new String(order.getOrderbasic().getEmail().getBytes("ISO-8859-1"), "UTF-8"));
			order.getOrderbasic().setCellphone(new String(order.getOrderbasic().getCellphone().getBytes("ISO-8859-1"), "UTF-8"));
			order.setFlag(new String(order.getFlag().getBytes("ISO-8859-1"), "UTF-8"));
			Coursecalendar cc = new Coursecalendar();
			cc.setCourseCalendarId(courseCalendarId);
			order.setCoursecalendar(cc);
			
			orderDao.saveOrderbasic(order.getOrderbasic());
			orderDao.savePublicOrder(order);
			
			course = courseDao.getDetailCoursecalendar(courseCalendarId);
			course.setSeatLeft(course.getSeatLeft() - order.getTotalPeopleNumber());
			courseDao.updateCoursecalendar(course);
			
			Element message = bookPublicOrder.addElement("message");
			message.addText(MobileConst.BOOK_PUBLIC_COURSE_SUCCESSFUL + user.getEmail());
			
			sendMails();
		} catch (Exception e) {
			Element errorMsg = bookPublicOrder.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
		return null;
	}
		
	private void sendMails() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coursecalendar cc = courseDao.getDetailCoursecalendar(order.getCoursecalendar().getCourseCalendarId());
		
		String templatePath = "/mail_template/order/booking_order_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", order.getOrderbasic().getContactPerson());
		content.put("#orderId#", order.getOrderPublicId());
		content.put("#course#", PropertyUtil.get(user.getPreferredLanguage(), cc.getCoursetrunktype().getCourseTrunkTypeNameKey()));
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
	
	private void sendBackXMLToMobile(Document document) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();      
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(document.asXML()); 
	        out.flush();
	        out.close();
		} catch (IOException e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
}
