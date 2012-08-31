package com.ccw.action.admin.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderstatus;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class AdminItemOrderAction extends ActionSupport {
	private static final long serialVersionUID = -3370498656302761579L;
	private Log log = LogFactory.getLog(AdminItemOrderAction.class);
	private ArrayList<Orderitem> orderList;
	private String id;
	private String userId;
	private Integer points;
	private Orderitem order;
	private Integer orderStatusId;
	private OrderDaoInf orderDao;
	private UserDaoInf userDao;
	private PageContainer pageContainer;
	private Integer pageNumber;

	public ArrayList<Orderitem> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Orderitem> orderList) {
		this.orderList = orderList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Orderitem getOrder() {
		return order;
	}

	public void setOrder(Orderitem order) {
		this.order = order;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
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

	public String gotoItemOrderPendingPage() {
		try {
			log.debug("AdminItemOrderAction.gotoItemOrderPendingPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_PENDING_ID);
			
			Long count = orderDao.getAllOrderitemCountByUserByStatus(null, Params.ORDER_STATUS_PENDING_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-item-pending-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_PENDING_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoItemOrderConfirmedPage() {
		try {
			log.debug("AdminItemOrderAction.gotoItemOrderConfirmedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_CONFIRMED_ID);
			
			Long count = orderDao.getAllOrderitemCountByUserByStatus(null, Params.ORDER_STATUS_CONFIRMED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-item-confirmed-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_CONFIRMED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoItemOrderFinishedPage() {
		try {
			log.debug("AdminItemOrderAction.gotoItemOrderFinishedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderitemCountByUserByStatus(null, Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-item-finished-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_FINISHED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String confirmItemOrder() {
		try {
			log.debug("AdminItemOrderAction.confirmItemOrder()");
			
			order = orderDao.getDetailOrderitem(id);
			
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_CONFIRMED_ID);
			order.setOrderstatus(orderstatus);
			
			order.setCheckTime(new Date());
			
			orderDao.updateItemOrder(order);
			
			String templatePath = "/mail_template/order/confirmed_order_template_" + order.getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getContactPerson());
			content.put("#orderId#", order.getOrderItemId());
			String[] emailAndContactPerson = new String[]{order.getEmail() + "_with_" + order.getContactPerson()};
			SendMail spom = new SendMail(Params.CONFIRMED_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
			spom.start();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String finishItemOrder() {
		try {
			log.debug("AdminPublicOrderAction.finishItemOrder()");
			
			order = orderDao.getDetailOrderitem(id);
			order.setPoints(points);
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_FINISHED_ID);
			order.setOrderstatus(orderstatus);
			orderDao.updateItemOrder(order);
			
			Userdetail user = userDao.getDetailUser(userId);
			if(user.getPoints() != null && !"".equals(user.getPoints()))
				user.setPoints(user.getPoints() + points);
			else
				user.setPoints(points);
			userDao.updateUserInformation(user);
			
			String templatePath = "/mail_template/order/finished_order_template_" + order.getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getContactPerson());
			content.put("#orderId#", order.getOrderItemId());
			content.put("#points#", String.valueOf(points));
			String[] emailAndContactPerson = new String[]{order.getEmail() + "_with_" + order.getContactPerson()};
			SendMail spom = new SendMail(Params.FINISHED_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
			spom.start();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String detailItemOrder() {
		try {
			log.debug("AdminPublicOrderAction.detailItemOrder()");
			
			order = orderDao.getDetailOrderitem(id);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
