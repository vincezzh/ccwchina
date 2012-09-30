package com.ccw.action.mobile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ccw.bean.Userdetail;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class InformationAction extends ActionSupport {
	private static final long serialVersionUID = -7932080412971317830L;
	private Log log = LogFactory.getLog(InformationAction.class);
	private Userdetail user;
	private UserDaoInf userDao;
	
	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public String editInformation() {
		log.debug("MobileInformationAction.editInformation()");
		Document document = DocumentHelper.createDocument();  
        Element editInformation = document.addElement("editInformation");
		try {
			Userdetail tempUser = userDao.getDetailUser(user.getUserId());
			tempUser.setPeopletitle(user.getPeopletitle());
			tempUser.setFirstName(new String(user.getFirstName().getBytes("ISO-8859-1"), "UTF-8"));
			tempUser.setLastName(new String(user.getLastName().getBytes("ISO-8859-1"), "UTF-8"));
			tempUser.setEmail(new String(user.getEmail().getBytes("ISO-8859-1"), "UTF-8"));
			tempUser.setCellphone(new String(user.getCellphone().getBytes("ISO-8859-1"), "UTF-8"));
			userDao.updateUserInformation(tempUser);
			
			Element message = editInformation.addElement("message");
			message.addText(MobileConst.EDIT_INFORMATION_SUCCESSFUL);
		}catch(Exception e) {
			Element errorMsg = editInformation.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
		return null;
	}
	
	public String editPassword() {
		log.debug("MobileInformationAction.editPassword()");
		Document document = DocumentHelper.createDocument();  
        Element editPassword = document.addElement("editPassword");
		try {
			user.setUserId(new String(user.getUserId().getBytes("ISO-8859-1"), "UTF-8"));
			user.setPassword(new String(user.getPassword().getBytes("ISO-8859-1"), "UTF-8"));
			Userdetail tempUser = userDao.getDetailUser(user.getUserId());
			tempUser.setPassword(user.getPassword());
			userDao.updateUserInformation(tempUser);
			
			Element message = editPassword.addElement("message");
			message.addText(MobileConst.EDIT_PASSWORD_SUCCESSFUL);
		}catch(Exception e) {
			Element errorMsg = editPassword.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
		return null;
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
