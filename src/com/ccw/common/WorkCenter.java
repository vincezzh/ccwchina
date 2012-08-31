package com.ccw.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;

public class WorkCenter {
	private Log log = LogFactory.getLog(WorkCenter.class);
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	private CourseDaoInf courseDao;
	
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
	
	public CourseDaoInf getCourseDao() {
		return courseDao;
	}
	
	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}
	
	public void sendCoursesReminder() {
		log.debug("WorkCenter.sendCoursesReminder()");
		try {
			sendAllOrderReminderToAdmin();
//			sendOrderpublicReminder();
//			sendOrderprivateReminder();
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
	
	private void sendOrderprivateReminder() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, Params.ADMIN_EVERYDAY_REMINDER_DAY_NUMBER);
		ArrayList<Orderprivate> privateOrderList = orderDao.getAllOrderprivateByDateForReminder(c.getTime());
		Iterator<Orderprivate> iPrivateOrderList = privateOrderList.iterator();
		Orderprivate op1 = null;
		while(iPrivateOrderList.hasNext()) {
			op1 = iPrivateOrderList.next();
			
			String templatePath = "/mail_template/reminder/order_reminder_template_" + op1.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", op1.getOrderbasic().getContactPerson());
			content.put("#orderId#", op1.getOrderPrivateId());
			content.put("#time#", sdf.format(op1.getCourseDate()) + " " + op1.getClasstime().getClassTimeContent());
			content.put("#location#", op1.getCourselocation().getCourseLocationName());
			String[] emailAndContactPerson = new String[]{op1.getOrderbasic().getEmail() + "_with_" + op1.getOrderbasic().getContactPerson()};
			String subject = Params.REMINDER_ORDER_MAIL_SUBJECT + sdf.format(op1.getCourseDate()) + " " + op1.getClasstime().getClassTimeContent();
			SendMail spom = new SendMail(subject, templatePath, content, emailAndContactPerson);
			spom.start();
		}
	}
	
	private void sendOrderpublicReminder() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, Params.ADMIN_EVERYDAY_REMINDER_DAY_NUMBER);
		ArrayList<Orderpublic> publicOrderList = orderDao.getAllOrderpublicByDateForReminder(c.getTime());
		Iterator<Orderpublic> iPublicOrderList = publicOrderList.iterator();
		Orderpublic op1 = null;
		while(iPublicOrderList.hasNext()) {
			op1 = iPublicOrderList.next();
			
			String templatePath = "/mail_template/reminder/order_reminder_template_" + op1.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", op1.getOrderbasic().getContactPerson());
			content.put("#orderId#", op1.getOrderPublicId());
			content.put("#time#", sdf.format(op1.getCoursecalendar().getClassDate()) + " " + op1.getCoursecalendar().getClasstime().getClassTimeContent());
			content.put("#location#", op1.getCoursecalendar().getCourselocation().getCourseLocationName());
			String[] emailAndContactPerson = new String[]{op1.getOrderbasic().getEmail() + "_with_" + op1.getOrderbasic().getContactPerson()};
			String subject = Params.REMINDER_ORDER_MAIL_SUBJECT + sdf.format(op1.getCoursecalendar().getClassDate()) + " " + op1.getCoursecalendar().getClasstime().getClassTimeContent();
			SendMail spom = new SendMail(subject, templatePath, content, emailAndContactPerson);
			spom.start();
		}
	}
	
	private void sendAllOrderReminderToAdmin() throws Exception {
		StringBuffer publicContent = new StringBuffer();
		StringBuffer privateContent = new StringBuffer();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ArrayList<Orderpublic> publicOrderList = orderDao.getAllOrderpublicByDateForReminder(now);
		Iterator<Orderpublic> iPublicOrderList = publicOrderList.iterator();
		Orderpublic op1 = null;
		while(iPublicOrderList.hasNext()) {
			op1 = iPublicOrderList.next();
			publicContent.append("<tr>");
			publicContent.append("<td>");
			publicContent.append(op1.getCoursecalendar().getCourselocation().getCourseLocationName());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op1.getCoursecalendar().getClasstime().getClassTimeContent());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op1.getOrderbasic().getContactPerson());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op1.getOrderbasic().getCellphone());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op1.getOrderbasic().getEmail());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op1.getTotalPeopleNumber());
			publicContent.append("</td>");
			publicContent.append("</tr>");
		}
		
		ArrayList<Orderprivate> privateOrderList = orderDao.getAllOrderprivateByDateForReminder(now);
		Iterator<Orderprivate> iPrivateOrderList = privateOrderList.iterator();
		Orderprivate op2 = null;
		while(iPrivateOrderList.hasNext()) {
			op2 = iPrivateOrderList.next();
			privateContent.append("<tr>");
			privateContent.append("<td>");
			privateContent.append(op2.getCourselocation().getCourseLocationName());
			privateContent.append("</td>");
			privateContent.append("<td>");
			privateContent.append(op2.getClasstime().getClassTimeContent());
			privateContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op2.getOrderbasic().getContactPerson());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op2.getOrderbasic().getCellphone());
			publicContent.append("</td>");
			publicContent.append("<td>");
			publicContent.append(op2.getOrderbasic().getEmail());
			publicContent.append("</td>");
			privateContent.append("<td>");
			privateContent.append(op2.getTotalPeopleNumber());
			privateContent.append("</td>");
			privateContent.append("</tr>");
		}
		
		String path = WorkCenter.class.getResource("/").getPath();
		String CCW_MAIL_SENDER = CommonUtil.readProperty("CCW_MAIL_SENDER", path + "ccw.properties");
		
		String templatePath = "/mail_template/reminder/admin_reminder_everyday_template_zh.txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#date#", sdf.format(now));
		content.put("#public_content#", publicContent.toString());
		content.put("#private_content#", privateContent.toString());
		String[] emailAndContactPerson = new String[]{CCW_MAIL_SENDER + "_with_CCW Auto Robot"};
		SendMail spom = new SendMail(Params.ADMIN_REMINDER_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
		spom.start();
	}
}
