package com.ccw.action.admin.order;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.action.admin.common.AdminMonthlyOrderCalendarGenerator;
import com.ccw.action.admin.common.AdminOrderCalendarGenerator;
import com.ccw.bean.Courselocation;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminOrderCalendarAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(AdminOrderCalendarAction.class);
	private Date currentMonth;
	private String currentMonthDate;
	private Integer courseLocationId;
	private ArrayList<Courselocation> locationList;
	private Courselocation courseLocation;
	private OrderDaoInf orderDao;
	private CourseDaoInf courseDao;

	public Date getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Date currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentMonthDate() {
		return currentMonthDate;
	}

	public void setCurrentMonthDate(String currentMonthDate) {
		this.currentMonthDate = currentMonthDate;
	}

	public Integer getCourseLocationId() {
		return courseLocationId;
	}

	public void setCourseLocationId(Integer courseLocationId) {
		this.courseLocationId = courseLocationId;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
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

	public Courselocation getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(Courselocation courseLocation) {
		this.courseLocation = courseLocation;
	}

	public String showOrderCalendar() {
		try {
			log.debug("AdminOrderCalendarAction.showOrderCalendar()");
			
			locationList = courseDao.getAllCourselocation();
			courseLocation = locationList.get(0);
			
			currentMonth = new Date();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String refreshOrderCalendar() {
		try {
			log.debug("AdminOrderCalendarAction.refreshOrderCalendar()");
			
			if(courseLocationId == null)
				courseLocationId = 1;
			if(currentMonthDate == null) {
				currentMonth = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				currentMonth = sdf.parse(currentMonthDate);
			}
			ArrayList<Orderpublic> publicOrders = orderDao.getPublicOrderCalendarBycourseLocationByMonth(courseLocationId, currentMonth);
			ArrayList<Orderprivate> privateOrders = orderDao.getPrivateOrderCalendarBycourseLocationByMonth(courseLocationId, currentMonth);
			String calendar = AdminOrderCalendarGenerator.generateAMonthOrderCalendar(publicOrders, privateOrders, courseLocationId, currentMonth, Locale.CHINA);

			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(calendar);

			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showMonthlyOrderCalendar() {
		try {
			log.debug("AdminOrderCalendarAction.showMonthlyOrderCalendar()");
			
			locationList = courseDao.getAllCourselocation();
			courseLocation = locationList.get(0);
			
			currentMonth = new Date();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String refreshMonthlyOrderCalendar() {
		try {
			log.debug("AdminOrderCalendarAction.refreshMonthlyOrderCalendar()");
			
			if(courseLocationId == null)
				courseLocationId = 1;
			if(currentMonthDate == null) {
				currentMonth = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				currentMonth = sdf.parse(currentMonthDate);
			}
			ArrayList<Orderpublic> publicOrders = orderDao.getPublicOrderCalendarBycourseLocationByMonth(courseLocationId, currentMonth);
			ArrayList<Orderprivate> privateOrders = orderDao.getPrivateOrderCalendarBycourseLocationByMonth(courseLocationId, currentMonth);
			String calendar = AdminMonthlyOrderCalendarGenerator.generateAMonthOrderCalendar(publicOrders, privateOrders, courseLocationId, currentMonth, Locale.CHINA);

			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			out.print(calendar);

			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
