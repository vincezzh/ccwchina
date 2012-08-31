package com.ccw.action.admin.calendar;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Classtime;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.common.CalendarTemplateGenerator;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminCalendarTemplateAction extends ActionSupport {
	private static final long serialVersionUID = 8487855027379765119L;
	private Log log = LogFactory.getLog(AdminCalendarTemplateAction.class);
	private String from;
	private String to;
	private CourseDaoInf courseDao;
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String gotoCalendarTemplate() {
		try {
			log.debug("AdminCalendarTemplateAction.gotoCalendarTemplate()");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			while(true) {
				if(c.get(Calendar.DAY_OF_WEEK) == 1) {
					from = sdf.format(c.getTime());
					c.add(Calendar.DATE, 6);
					to = sdf.format(c.getTime());
					
					break;
				}else {
					c.add(Calendar.DATE, 1);
				}
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getCalendarTemplate() {
		try {
			log.debug("AdminCalendarTemplateAction.getCalendarTemplate()");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = sdf.parse(from);
			Date toDate = sdf.parse(to);
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			
			if(fromDate.compareTo(toDate) > 0) {
				out.print("Date Error");
				return null;
			}
			
			LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>> allCourses = courseDao.getCalendarTemplateCourses(fromDate, toDate);
			String template = CalendarTemplateGenerator.generateAMonthCalendar(fromDate, toDate, allCourses);
			out.print(template);

			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
