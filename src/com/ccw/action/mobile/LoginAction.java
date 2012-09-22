package com.ccw.action.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ccw.bean.Courselocation;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -6254134374698827063L;
	private Log log = LogFactory.getLog(LoginAction.class);
	private Userdetail user;
	private String username;
	private String password;
	private UserDaoInf userDao;
	
	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public String login() {
		log.debug("MobileLoginAction.login()");
		Document document = DocumentHelper.createDocument();  
        Element myInformation = document.addElement("myInformation");
		try {
			user = userDao.login(username, password);
			if(user != null) {
		        Element username = myInformation.addElement("username");
		        username.addText(user.getUserId());
		        
		        Element title = myInformation.addElement("title");
		        Element titleId = title.addElement("id");
		        titleId.addText(user.getPeopletitle().getPeopleTitleId().toString());
		        Element titleValue = title.addElement("value");
		        titleValue.addText(user.getPeopletitle().getPeopleTitleName());
		        
		        Element firstname = myInformation.addElement("firstname");
		        firstname.addText(user.getFirstName());
		        
		        Element lastname = myInformation.addElement("lastname");
		        lastname.addText(user.getLastName());
		        
		        Element email = myInformation.addElement("email");
		        email.addText(user.getEmail());
		        
		        Element cellphone = myInformation.addElement("cellphone");
		        cellphone.addText(user.getCellphone());
			}else {
				Element errorMsg = myInformation.addElement("errorMsg");
				errorMsg.addText("Username or password is incorrect.");
			}
		}catch(Exception e) {
			Element errorMsg = myInformation.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
        return null;
	}
	
	public String register() {
		log.debug("MobileLoginAction.register()");
		Document document = DocumentHelper.createDocument();  
        Element myInformation = document.addElement("myInformation");
		try {
			user = userDao.getDetailUser(user.getUserId());
			if(user == null) {
				user.setAvaliable("yes");
				user.setIsVegetarian("false");
				user.setRegisterTime(new Date());
				user.setNickName(user.getUserId());
				user.setFirstName("");
				user.setLastName("");
				user.setCellphone("");
				user.setAddress("");
				user.setNeedNews("true");
				user.setNeedCoupon("true");
				user.setPoints(0);
				Courselocation courselocation = new Courselocation();
				courselocation.setCourseLocationId(1);
				user.setCourselocation(courselocation);
				user.setPreferredLanguage("en");
				Peopletitle peopleTitle = new Peopletitle();
				peopleTitle.setPeopleTitleId(1);
				user.setPeopletitle(peopleTitle);
				userDao.register(user);
				
				username = user.getUserId();
				password = user.getPassword();
				
				return SUCCESS;
			}else {
				Element errorMsg = myInformation.addElement("errorMsg");
				errorMsg.addText(MobileConst.USER_EXISTED);
				
				sendBackXMLToMobile(document);
				return null;
			}
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String retrievePassword() {
		log.debug("MobileLoginAction.retrievePassword()");
		Document document = DocumentHelper.createDocument();  
        Element retrivePassword = document.addElement("retrivePassword");
		try {
			user = userDao.getDetailUser(username);
			if(user == null) {
				Element message = retrivePassword.addElement("message");
				message.addText(MobileConst.USER_NOT_EXISTED);
			}else {
				Date now = new Date();
				user.setPassword(String.valueOf(now.getTime()));
				userDao.updateUserInformation(user);
				sendMails();
				Element message = retrivePassword.addElement("message");
				message.addText(MobileConst.RETRIEVE_PASSWORD_SUCCESSFUL + user.getEmail());
			}
		}catch(Exception e) {
			Element errorMsg = retrivePassword.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
		return null;
	}
	
	private void sendMails() throws Exception {
		String templatePath = "/mail_template/register/retrieve_password_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", user.getFirstName() + " " + user.getLastName());
		content.put("#password#", user.getPassword());
		String[] emailAndContactPerson = new String[]{user.getEmail() + "_with_" + user.getFirstName() + " " + user.getLastName()};
		SendMail spom = new SendMail(Params.RETRIEVE_PASSWORD_SUCCESSFUL, templatePath, content, emailAndContactPerson);
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
