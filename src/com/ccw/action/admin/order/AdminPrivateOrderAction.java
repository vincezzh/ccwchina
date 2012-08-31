package com.ccw.action.admin.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderstatus;
import com.ccw.bean.Packagewithcourse;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPrivateOrderAction extends ActionSupport {
	private static final long serialVersionUID = -3544866058237626144L;
	private Log log = LogFactory.getLog(AdminPrivateOrderAction.class);
	private ArrayList<Orderprivate> orderList;
	private ArrayList<Packagewithcourse> packageCourses;
	private String id;
	private String userId;
	private Integer points;
	private Orderprivate order;
	private Integer orderStatusId;
	private OrderDaoInf orderDao;
	private UserDaoInf userDao;
	private CourseDaoInf courseDao;
	private PageContainer pageContainer;
	private Integer pageNumber;
	
	public ArrayList<Orderprivate> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Orderprivate> orderList) {
		this.orderList = orderList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public PageContainer getPageContainer() {
		return pageContainer;
	}

	public void setPageContainer(PageContainer pageContainer) {
		this.pageContainer = pageContainer;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public Orderprivate getOrder() {
		return order;
	}

	public void setOrder(Orderprivate order) {
		this.order = order;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public ArrayList<Packagewithcourse> getPackageCourses() {
		return packageCourses;
	}

	public void setPackageCourses(ArrayList<Packagewithcourse> packageCourses) {
		this.packageCourses = packageCourses;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String gotoPrivateOrderPendingPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPrivateOrderPendingPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_PENDING_ID);
			
			Long count = orderDao.getAllOrderprivateCountByUserByStatus(null, Params.ORDER_STATUS_PENDING_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-private-pending-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_PENDING_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoPrivateOrderConfirmedPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPrivateOrderConfirmedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_CONFIRMED_ID);
			
			Long count = orderDao.getAllOrderprivateCountByUserByStatus(null, Params.ORDER_STATUS_CONFIRMED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-private-confirmed-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_CONFIRMED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoPrivateOrderFinishedPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPrivateOrderFinishedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderprivateCountByUserByStatus(null, Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-private-finished-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_FINISHED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String confirmPrivateOrder() {
		try {
			log.debug("AdminPublicOrderAction.confirmPrivateOrder()");
			
			order = orderDao.getDetailOrderprivate(id);
			
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_CONFIRMED_ID);
			order.getOrderbasic().setOrderstatus(orderstatus);
			
			order.getOrderbasic().setCheckingTime(new Date());
			
			orderDao.updatePrivateOrder(order);
			
			String templatePath = "/mail_template/order/confirmed_order_template_" + order.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getOrderbasic().getContactPerson());
			content.put("#orderId#", order.getOrderPrivateId());
			String[] emailAndContactPerson = new String[]{order.getOrderbasic().getEmail() + "_with_" + order.getOrderbasic().getContactPerson()};
			SendMail spom = new SendMail(Params.CONFIRMED_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
			spom.start();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String finishPrivateOrder() {
		try {
			log.debug("AdminPublicOrderAction.finishPrivateOrder()");
			
			order = orderDao.getDetailOrderprivate(id);
			order.getOrderbasic().setPoints(points);
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_FINISHED_ID);
			order.getOrderbasic().setOrderstatus(orderstatus);
			orderDao.updatePrivateOrder(order);
			
			Userdetail user = userDao.getDetailUser(userId);
			if(user.getPoints() != null && !"".equals(user.getPoints()))
				user.setPoints(user.getPoints() + points);
			else
				user.setPoints(points);
			userDao.updateUserInformation(user);
			
			String templatePath = "/mail_template/order/finished_order_template_" + order.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getOrderbasic().getContactPerson());
			content.put("#orderId#", order.getOrderPrivateId());
			content.put("#points#", String.valueOf(points));
			String[] emailAndContactPerson = new String[]{order.getOrderbasic().getEmail() + "_with_" + order.getOrderbasic().getContactPerson()};
			SendMail spom = new SendMail(Params.FINISHED_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
			spom.start();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String detailPrivateOrder() {
		try {
			log.debug("AdminPublicOrderAction.detailPrivateOrder()");
			
			order = orderDao.getDetailOrderprivate(id);
			packageCourses = courseDao.getAllPackagewithcourseByCoursepackage(order.getCoursepackage().getCoursePackageId());
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
