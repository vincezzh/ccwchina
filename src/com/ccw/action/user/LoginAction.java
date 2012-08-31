package com.ccw.action.user;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Courselocation;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -6254134374698827063L;
	private Log log = LogFactory.getLog(LoginAction.class);
	private Userdetail user;
	private String username;
	private String password;
	private String language;
	private String birthday;
	private String retrievePasswordResult;
	private ArrayList<Courselocation> locationList;
	private ArrayList<Peopletitle> peopleTitleList;
	private CourseDaoInf courseDao;
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

	public ArrayList<Courselocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Courselocation> locationList) {
		this.locationList = locationList;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public ArrayList<Peopletitle> getPeopleTitleList() {
		return peopleTitleList;
	}

	public void setPeopleTitleList(ArrayList<Peopletitle> peopleTitleList) {
		this.peopleTitleList = peopleTitleList;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRetrievePasswordResult() {
		return retrievePasswordResult;
	}

	public void setRetrievePasswordResult(String retrievePasswordResult) {
		this.retrievePasswordResult = retrievePasswordResult;
	}

	public String isLogin() {
		try {
			log.debug("LoginAction.isLogin()");
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			if(user == null) {
				out.print("");
			}else {
				out.print(user.getNickName());
			}
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String login() {
		try {
			log.debug("LoginAction.login()");

			user = userDao.login(username, password);
			if(user == null) {
				addActionError(getText("SIGN_IN_ERROR"));
				locationList = courseDao.getAllCourselocation();
				Map session = ActionContext.getContext().getSession();
				Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
				if(locale == null) {
					locale = Locale.US;
					session.put("WW_TRANS_I18N_LOCALE", locale);
				}
				language = locale.getLanguage();
				
				return INPUT;
			}else {
				Map session = ActionContext.getContext().getSession();
				session.put("user_session", user);
				
				if(Params.CHINA.equals(user.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
				}else if(Params.JAPAN.equals(user.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.JAPAN);
				}else {
					session.put("WW_TRANS_I18N_LOCALE", Locale.US);
				}
				
				return SUCCESS;
			}
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoSignInSignUp() {
		try {
			log.debug("LoginAction.gotoSignInSignUp()");

			locationList = courseDao.getAllCourselocation();
			Map session = ActionContext.getContext().getSession();
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if(locale == null) {
				locale = Locale.US;
				session.put("WW_TRANS_I18N_LOCALE", locale);
			}
			language = locale.getLanguage();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String checkUserExisted() {
		try {
			log.debug("LoginAction.checkUserExisted()");
			Userdetail user = userDao.getDetailUser(username);
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			if(user == null) {
				out.print("passed");
			}else {
				out.print("failed");
			}
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String logout() {
		try {
			log.debug("LoginAction.logout()");
			
			Map session = ActionContext.getContext().getSession();
			session.put("user_session", null);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String register() {
		try {
			log.debug("LoginAction.register()");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(sdf.parse(birthday));
			user.setAvaliable("yes");
			user.setIsVegetarian("false");
			user.setRegisterTime(new Date());
			user.setNickName(user.getUserId());
			user.setFirstName("");
			user.setLastName("");
			user.setNeedNews("true");
			user.setNeedCoupon("true");
			user.setPoints(0);
			
			userDao.register(user);
			
			username = user.getUserId();
			password = user.getPassword();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String fillForm() {
		try {
			log.debug("LoginAction.fillForm()");
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			if(user == null) {
				out.print("");
			}else {
				out.print(user.getFirstName() + " " + user.getLastName() + "_and_" + user.getEmail() + "_and_" + user.getCellphone());
			}
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String retrievePassword() {
		try {
			log.debug("LoginAction.retrievePassword()");
			
			user = userDao.getDetailUser(username);
			if(user == null) {
				retrievePasswordResult = getText("RETRIEVE_PASSWORD_ERROR");
				return SUCCESS;
			}
			
			Date now = new Date();
			user.setPassword(String.valueOf(now.getTime()));
			userDao.updateUserInformation(user);
			sendMails();
			retrievePasswordResult = getText("RETRIEVE_PASSWORD_SUCCESSFUL");
			
			return SUCCESS;
		}catch(Exception e) {
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
		
		String templatePath = "/mail_template/register/retrieve_password_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", user.getFirstName() + " " + user.getLastName());
		content.put("#password#", user.getPassword());
		String[] emailAndContactPerson = new String[]{user.getEmail() + "_with_" + user.getFirstName() + " " + user.getLastName()};
		SendMail spom = new SendMail(Params.RETRIEVE_PASSWORD_SUCCESSFUL, templatePath, content, emailAndContactPerson);
		spom.start();
	}
}
