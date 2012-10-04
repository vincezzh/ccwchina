package com.ccw.action.course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Calendarwithcourse;
import com.ccw.bean.Classtime;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Userdetail;
import com.ccw.common.CalendarGenerator;
import com.ccw.dao.CourseDaoInf;
import com.ccw.excel.ExcelCalendar;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CourseListAction extends ActionSupport {
	private static final long serialVersionUID = -4961552815699434206L;
	private Log log = LogFactory.getLog(CourseListAction.class);
	private Date month;
	private String monthDate;
	private Integer courseLocationId;
	private String courseId;
	private ArrayList<Courselocation> locationList;
	private Courselocation location;
	
	private InputStream fileInputStream;
	private String contentType;
	private String fileName;
	
	private CourseDaoInf courseDao;
	
	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Integer getCourseLocationId() {
		return courseLocationId;
	}

	public void setCourseLocationId(Integer courseLocationId) {
		this.courseLocationId = courseLocationId;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public ArrayList<Courselocation> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Courselocation> locationList) {
		this.locationList = locationList;
	}

	public Courselocation getLocation() {
		return location;
	}

	public void setLocation(Courselocation location) {
		this.location = location;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAMonthCalendar() {
		try {
			log.debug("CourseListAction.getAMonthCalendar()");
			
			if(courseLocationId == null)
				courseLocationId = 1;
			if(monthDate == null) {
				month = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				month = sdf.parse(monthDate);
			}
			
			ArrayList<Coursecalendar> courses = courseDao.getCoursecalendarBycourseLocationByMonth(courseLocationId, month);
			ArrayList<Classtime> allTimes = courseDao.getAllClasstime();
			Map session = ActionContext.getContext().getSession();
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			String calendar = CalendarGenerator.generateAMonthCalendar(courses, allTimes, courseLocationId, month, locale);
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(calendar);

			return null;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String exportAMonthCalendar() {
        FileOutputStream out = null;
		try {
			log.debug("CourseListAction.exportAMonthCalendar()");
			
			if(courseLocationId == null)
				courseLocationId = 1;
			if(monthDate == null) {
				month = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				month = sdf.parse(monthDate);
			}
			
			ArrayList<Coursecalendar> courses = courseDao.getCoursecalendarBycourseLocationByMonth(courseLocationId, month);
			ArrayList<Classtime> allTimes = courseDao.getAllClasstime();
			Map session = ActionContext.getContext().getSession();
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			
			ExcelCalendar excelCalendar = new ExcelCalendar(courses, allTimes, courseLocationId, month, locale);
			HSSFWorkbook wb = excelCalendar.generateMonthScheduleExcel();
			String file = ServletActionContext.getServletContext().getRealPath("temp") + File.separator + new Date().getTime() + ".xls";
	        out = new FileOutputStream(file);
	        wb.write(out);
			setFileInputStream(new FileInputStream(file));
			setContentType("application/vnd.ms-excel");
			setFileName("CCW-Calendar.xls");
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		} finally {
			try {
				if(out != null) {
					out.flush(); out.close();
				}
			}catch (Exception e) {
				log.error(e);
				log.error(e.getMessage());
			}
		}
	}
	
	public String gotoCourseCalendar() {
		try {
			log.debug("CourseListAction.gotoCourseCalendar()");
			
			locationList = courseDao.getAllCourselocation();
			
			Map session = ActionContext.getContext().getSession();
			Userdetail user = (Userdetail)session.get("user_session");
			if(user != null) {
				location = user.getCourselocation();
			}else {
				location = locationList.get(0);
			}
			
			month = new Date();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String checkCourseCalendar() {
		try {
			log.debug("CourseListAction.checkCourseCalendar()");
			
			Date from = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(from);
			c.add(Calendar.DATE, 30);
			Date to = c.getTime();
			ArrayList<Calendarwithcourse> list = courseDao.getAllCalendarwithcourseByCourseAndLocationAndDate(courseId, courseLocationId, from, to);
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			if(list.size() > 0) {
				Iterator<Calendarwithcourse> i = list.iterator();
				Calendarwithcourse cwc = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				while(i.hasNext()) {
					cwc = i.next();
					out.print("<div>");
					out.print("<a href='/order/go-to-public-order.htm?courseCalendarId=" + cwc.getCoursecalendar().getCourseCalendarId() + "'>" + sdf.format(cwc.getCoursecalendar().getClassDate()) + " " + cwc.getCoursecalendar().getClasstime().getClassTimeContent() + "</a>");
					out.print("</div>");
				}
			}else {
				out.print("");
			}
			
			return null;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
