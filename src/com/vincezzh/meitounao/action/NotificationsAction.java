package com.vincezzh.meitounao.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Notifications;
import com.ccw.dao.CommonDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class NotificationsAction extends ActionSupport {
	private static final long serialVersionUID = -7870007759570528045L;
	private Log log = LogFactory.getLog(NotificationsAction.class);
	
	private CommonDaoInf commonDao;

	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public String fetchTodayTomorrowNotifications() {
		try {
			log.debug("NotificationsAction.getTodayTomorrowNotifications()");
			
			ArrayList<Notifications> list = commonDao.getTodayTomorrowNotifications();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			
			HttpServletResponse response = ServletActionContext.getResponse();      
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
			
	        StringBuffer xmlsb = new StringBuffer();
	        xmlsb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	        xmlsb.append("<all_notifications>");
	        xmlsb.append("<meimei_notifications>");
			
			if(list.size() == 0) {
				xmlsb.append("<content>" + "运气不错，30天内没有任务。" + "</content>");
			}else {
				for(Notifications n : list) {
					xmlsb.append("<content>" + sdf.format(n.getContentTime()) + " " + n.getContent() + "</content>");
				}
			}
			
			xmlsb.append("</meimei_notifications>");
			xmlsb.append("</all_notifications>");
			
			PrintWriter out = response.getWriter();
	        out.write(xmlsb.toString()); 
	        out.flush();
            out.close();
            
            return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
