package com.ccw.action.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Courselocation;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InformationAction extends ActionSupport {
	private static final long serialVersionUID = -7932080412971317830L;
	private Log log = LogFactory.getLog(InformationAction.class);
	private ArrayList<Courselocation> locationList;
	private ArrayList<Peopletitle> peopleTitleList;
	private Userdetail user;
	private String birthday;
	private String language;
	private String password;
	private String informationResult;
	private String passwordResult;
	private CourseDaoInf courseDao;
	private UserDaoInf userDao;

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
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
	
	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInformationResult() {
		return informationResult;
	}

	public void setInformationResult(String informationResult) {
		this.informationResult = informationResult;
	}

	public String getPasswordResult() {
		return passwordResult;
	}

	public void setPasswordResult(String passwordResult) {
		this.passwordResult = passwordResult;
	}

	public String gotoInformation() {
		try {
			log.debug("InformationAction.gotoInformation()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			peopleTitleList = userDao.getAllPeopletitle();
			locationList = courseDao.getAllCourselocation();

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
	
	public String editInformation() {
		try {
			log.debug("InformationAction.editInformation()");
			
			Map session = ActionContext.getContext().getSession();
			Userdetail tempUser = (Userdetail)session.get("user_session");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(sdf.parse(birthday));
			user.setAvaliable(tempUser.getAvaliable());
			user.setFlag(tempUser.getFlag());
			user.setUserId(tempUser.getUserId());
			user.setPassword(tempUser.getPassword());
			user.setRegisterTime(tempUser.getRegisterTime());
			
			userDao.updateUserInformation(user);
			session.put("user_session", user);
			informationResult = getText("EDIT_INFORMATION_SUCCESSFUL");
			
			Locale locale = null;
			if("ja".equals(user.getPreferredLanguage())) {
				locale = Locale.JAPAN;
			}else if("zh".equals(user.getPreferredLanguage())) {
				locale = Locale.CHINA;
			}else {
				locale = Locale.US;
			}
			session.put("WW_TRANS_I18N_LOCALE", locale);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String editPassword() {
		try {
			log.debug("InformationAction.editPassword()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			user.setPassword(password);
			userDao.updateUserInformation(user);
			session.put("user_session", user);
			passwordResult = getText("EDIT_PASSWORD_SUCCESSFUL");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
