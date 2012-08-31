package com.ccw.action.admin.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Infomaillist;
import com.ccw.bean.Userdetail;
import com.ccw.common.DesEncrypt;
import com.ccw.common.Params;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class AdminMailAction extends ActionSupport {
	private static final long serialVersionUID = 4973934431603136814L;
	private Log log = LogFactory.getLog(AdminMailAction.class);
	private TreeSet<String> mailList;
	private String guestTitle;
	private String title;
	private String userType;
	private String[] receivers;
	private String mailContent;
	private String isCalendar;
	private UserDaoInf userDao;

	public TreeSet<String> getMailList() {
		return mailList;
	}

	public void setMailList(TreeSet<String> mailList) {
		this.mailList = mailList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGuestTitle() {
		return guestTitle;
	}

	public void setGuestTitle(String guestTitle) {
		this.guestTitle = guestTitle;
	}
	
	public String getIsCalendar() {
		return isCalendar;
	}

	public void setIsCalendar(String isCalendar) {
		this.isCalendar = isCalendar;
	}

	public String gotoMailPage() {
		try {
			log.debug("AdminMailAction.gotoMailPage()");

			mailList = new TreeSet<String>();
			
			ArrayList<Infomaillist> allMailList = userDao.getAllInfomaillist();
			Iterator<Infomaillist> iAllMailList = allMailList.iterator();
			while(iAllMailList.hasNext()) {
				mailList.add(iAllMailList.next().getEmail());
			}
			
			ArrayList<Userdetail> allUserList = userDao.getAllUser();
			Iterator<Userdetail> iAllUserList = allUserList.iterator();
			while(iAllUserList.hasNext()) {
				mailList.add(iAllUserList.next().getEmail());
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String sendMails() {
		try {
			log.debug("AdminMailAction.sendMails()");
			
			if("member".equals(userType)) {
				ArrayList<Userdetail> allUserList = userDao.getAllUser();
				
				sendMail(allUserList);
			}else if("maillist".equals(userType)) {
				mailList = new TreeSet<String>();
				
				ArrayList<Infomaillist> allMailList = userDao.getAllInfomaillist();
				Iterator<Infomaillist> iAllMailList = allMailList.iterator();
				while(iAllMailList.hasNext()) {
					mailList.add(iAllMailList.next().getEmail());
				}
				
				sendMail(mailList);
			}else if("all".equals(userType)) {
				mailList = new TreeSet<String>();
				
				ArrayList<Infomaillist> allMailList = userDao.getAllInfomaillist();
				Iterator<Infomaillist> iAllMailList = allMailList.iterator();
				while(iAllMailList.hasNext()) {
					mailList.add(iAllMailList.next().getEmail());
				}
				
				ArrayList<Userdetail> allUserList = userDao.getAllUser();
				Iterator<Userdetail> iAllUserList = allUserList.iterator();
				while(iAllUserList.hasNext()) {
					mailList.add(iAllUserList.next().getEmail());
				}
				
				sendMail(mailList);
			}else if("custom".equals(userType)) {
				sendMail(receivers);
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	private void sendMail(ArrayList<Userdetail> users) {
		Iterator<Userdetail> iAllUserList = users.iterator();
		Userdetail u = null;
		String prefixPublic = Params.CCW_CONTEXT + "order/mail-public-order.htm?";
		String prefixPrivate = Params.CCW_CONTEXT + "order/mail-private-order.htm?";
		
		while(iAllUserList.hasNext()) {
			u = iAllUserList.next();
			if(!"false".equals(isCalendar)) {
				String parameter = "mp=" + DesEncrypt.encrypt(u.getUserId() + Params.ADMIN_MAIL_INTERVAL + u.getPassword());
				mailContent = mailContent.replace("@@@", prefixPublic + parameter + "&");
				mailContent = mailContent.replace("$$$", prefixPrivate + parameter + "&");
			}
			sendMail(u.getEmail());
		}
	}
	
	private void sendMail(String receiver) {
		String[] emailAndContactPerson = new String[]{receiver + "_with_" + guestTitle};
		SendMail spom = new SendMail(title, mailContent, emailAndContactPerson);
		spom.start();
	}
	
	private void sendMail(String[] receivers) {
		for(String receiver : receivers) {
			sendMail(receiver);
		}
	}
	
	private void sendMail(Set<String> mailList) {
		Iterator<String> i = mailList.iterator();
		String prefixPublic = Params.CCW_CONTEXT + "order/mail-public-order.htm?";
		String prefixPrivate = Params.CCW_CONTEXT + "order/mail-private-order.htm?";
		
		while(i.hasNext()) {
			String receiver = i.next();
			if(!"false".equals(isCalendar)) {
				mailContent = mailContent.replace("@@@", prefixPublic);
				mailContent = mailContent.replace("$$$", prefixPrivate);
			}
			sendMail(receiver);
		}
	}
	
	public static void main(String[] args) {
		//<a href="@@@cid=L-1303972175225">public order</a>
		//<a href="$$$cl=1&cd=20110501&ct=1">example link</a>
		String mailContent = "<div>5/26</div><div>注册用户公共课预订示例<a href='@@@cid=L-1303972175225'><span style='background-color: rgb(255, 0, 0);'>午後</span></a></div><div>大人気中華-淮揚料理</div><div>1)淮河の魚の細切り蒸し焼き</div><div>2)小エビの素炒め</div><div>3)キノコと豆腐の炒め物朱洁，点击以上链接测试下。</div>";
		String prefix = "http://www.chinesecookingworkshop.com/order/mail-public-order.htm?";
		String parameter = "mp=" + DesEncrypt.encrypt("aga.sun" + Params.ADMIN_MAIL_INTERVAL + "shanghai2008");
		mailContent = mailContent.replace("@@@", prefix + parameter + "&");

		String[] emailAndContactPerson = new String[]{"aga.sun@hwaschuen.com" + "_with_" + "Aga Sun"};
		SendMail spom = new SendMail("Example of booking lessons by email", mailContent, emailAndContactPerson);
		spom.start();
		
		//example: http://localhost:8080/order/mail-public-order.htm?cid=L-1305092271377
//		mailContent = "<div>5/26</div><div>非注册用户公共课<a href='@@@cid=L-1305089446545'><span style='background-color: rgb(255, 0, 0);'>午後</span></a></div><div>大人気中華-淮揚料理</div><div>1)淮河の魚の細切り蒸し焼き</div><div>2)小エビの素炒め</div><div>3)キノコと豆腐の炒め物朱洁，点击以上链接测试下。</div>";
//		prefix = "http://localhost:8080/order/mail-public-order.htm?";
//		mailContent = mailContent.replace("@@@", prefix);
//
//		emailAndContactPerson = new String[]{"vincezzh@gmail.com" + "_with_" + "Aga Sun"};
//		spom = new SendMail("Example for booking lessons by email", mailContent, emailAndContactPerson);
//		spom.start();


//		mailContent = "<div>5/26</div><div>注册用户私人课<a href='$$$courseDate=20110527&classTimeId=1&courseLocationId=1'><span style='background-color: rgb(255, 0, 0);'>午後</span></a></div><div>大人気中華-淮揚料理</div><div>1)淮河の魚の細切り蒸し焼き</div><div>2)小エビの素炒め</div><div>3)キノコと豆腐の炒め物朱洁，点击以上链接测试下。</div>";
//		prefix = "http://localhost:8080/order/mail-private-order.htm?";
//		parameter = "mp=" + DesEncrypt.encrypt("aga.sun" + Params.ADMIN_MAIL_INTERVAL + "shanghai2008");
//		mailContent = mailContent.replace("$$$", prefix + parameter + "&");
//
//		emailAndContactPerson = new String[]{"vincezzh@gmail.com" + "_with_" + "Aga Sun"};
//		spom = new SendMail("Example for booking lessons by email", mailContent, emailAndContactPerson);
//		spom.start();

		//example: http://localhost:8080/order/mail-private-order.htm?courseDate=20110623&classTimeId=4&courseLocationId=1
//		mailContent = "<div>5/26</div><div>非注册用户私人课<a href='$$$courseDate=20110527&classTimeId=2&courseLocationId=1'><span style='background-color: rgb(255, 0, 0);'>午後</span></a></div><div>大人気中華-淮揚料理</div><div>1)淮河の魚の細切り蒸し焼き</div><div>2)小エビの素炒め</div><div>3)キノコと豆腐の炒め物朱洁，点击以上链接测试下。</div>";
//		prefix = "http://localhost:8080/order/mail-private-order.htm?";
//		mailContent = mailContent.replace("$$$", prefix);
//
//		emailAndContactPerson = new String[]{"vincezzh@gmail.com" + "_with_" + "Aga Sun"};
//		spom = new SendMail("Example for booking lessons by email", mailContent, emailAndContactPerson);
//		spom.start();
		
		System.out.println("TEST FINISHED");
	}
}
