package com.ccw.action.mail;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Infomaillist;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MailAction extends ActionSupport {
	private static final long serialVersionUID = 7120870335747179159L;
	private Log log = LogFactory.getLog(MailAction.class);
	private Infomaillist mail;
	private String feedback;
	private String language;
	private CommonDaoInf commonDao;

	public Infomaillist getMail() {
		return mail;
	}

	public void setMail(Infomaillist mail) {
		this.mail = mail;
	}

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String gotoMailPage() {
		try {
			log.debug("MailAction.gotoMailPage()");
			
			Map session = ActionContext.getContext().getSession();
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
	
	public String saveMail() {
		try {
			log.debug("MailAction.saveMail()");
			
			mail.setAvaliable("yes");
			mail.setJointime(new Date());
			
			commonDao.addMail(mail);
			feedback = getText("MAIL_FEEDBACK");
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
