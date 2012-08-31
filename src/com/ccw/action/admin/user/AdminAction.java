package com.ccw.action.admin.user;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Admindetail;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private static final long serialVersionUID = -82876418662315636L;
	private Log log = LogFactory.getLog(AdminAction.class);
	private Admindetail admin;
	private String adminId;
	private String oldPassword;
	private UserDaoInf userDao;
	
	public Admindetail getAdmin() {
		return admin;
	}

	public void setAdmin(Admindetail admin) {
		this.admin = admin;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public String login() {
		try {
			log.debug("AdminAction.login()");

			Admindetail loginAdmin = userDao.adminLogin(admin);
			if(loginAdmin == null) {
				addActionError("AdminId or Password is incorrect!");
				return INPUT;
			}else {
				Map session = ActionContext.getContext().getSession();
				session.put("admin_session", loginAdmin);
				
				Locale locale = Locale.CHINA;
				session.put("WW_TRANS_I18N_LOCALE", locale);
				
				return SUCCESS;
			}
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String logout() {
		try {
			log.debug("AdminAction.logout()");
			
			Map session = ActionContext.getContext().getSession();
			session.put("admin_session", null);
				
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
