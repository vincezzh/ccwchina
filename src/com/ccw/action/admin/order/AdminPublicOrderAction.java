package com.ccw.action.admin.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderpublic;
import com.ccw.bean.Orderstatus;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPublicOrderAction extends ActionSupport {
	private static final long serialVersionUID = -3347013412745980601L;
	private Log log = LogFactory.getLog(AdminPublicOrderAction.class);
	private ArrayList<Orderpublic> orderList;
	private String id;
	private String userId;
	private Integer points;
	private Orderpublic order;
	private Integer orderStatusId;
	private OrderDaoInf orderDao;
	private UserDaoInf userDao;
	private PageContainer pageContainer;
	private Integer pageNumber;
	
	public ArrayList<Orderpublic> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Orderpublic> orderList) {
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

	public Orderpublic getOrder() {
		return order;
	}

	public void setOrder(Orderpublic order) {
		this.order = order;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String gotoPublicOrderPendingPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPublicOrderPendingPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_PENDING_ID);
			
			Long count = orderDao.getAllOrderpublicCountByUserByStatus(null, Params.ORDER_STATUS_PENDING_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-public-pending-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_PENDING_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoPublicOrderConfirmedPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPublicOrderConfirmedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_CONFIRMED_ID);
			
			Long count = orderDao.getAllOrderpublicCountByUserByStatus(null, Params.ORDER_STATUS_CONFIRMED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-public-confirmed-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_CONFIRMED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoPublicOrderFinishedPage() {
		try {
			log.debug("AdminPublicOrderAction.gotoPublicOrderFinishedPage()");
			
			if(pageNumber == null)
				pageNumber = 1;
			orderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(null, pageNumber, Params.ADMIN_ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderpublicCountByUserByStatus(null, Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ORDER_PER_PAGE_NUMBER), "/_administrator/archives-public-finished-orders.htm", "");
			
			orderStatusId = Params.ORDER_STATUS_FINISHED_ID;
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String confirmPublicOrder() {
		try {
			log.debug("AdminPublicOrderAction.confirmPublicOrder()");
			
			order = orderDao.getDetailOrderpublic(id);
			
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_CONFIRMED_ID);
			order.getOrderbasic().setOrderstatus(orderstatus);
			
			order.getOrderbasic().setCheckingTime(new Date());
			
			orderDao.updatePublicOrder(order);
			
			String templatePath = "/mail_template/order/confirmed_order_template_" + order.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getOrderbasic().getContactPerson());
			content.put("#orderId#", order.getOrderPublicId());
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
	
	public String finishPublicOrder() {
		try {
			log.debug("AdminPublicOrderAction.finishPublicOrder()");
			
			order = orderDao.getDetailOrderpublic(id);
			order.getOrderbasic().setPoints(points);
			Orderstatus orderstatus = new Orderstatus();
			orderstatus.setOrderStatusId(Params.ORDER_STATUS_FINISHED_ID);
			order.getOrderbasic().setOrderstatus(orderstatus);
			orderDao.updatePublicOrder(order);
			
			Userdetail user = userDao.getDetailUser(userId);
			if(user.getPoints() != null && !"".equals(user.getPoints()))
				user.setPoints(user.getPoints() + points);
			else
				user.setPoints(points);
			userDao.updateUserInformation(user);
			
			String templatePath = "/mail_template/order/finished_order_template_" + order.getOrderbasic().getUserdetail().getPreferredLanguage() + ".txt";
			HashMap<String, String> content = new HashMap<String, String>();
			content.put("#name#", order.getOrderbasic().getContactPerson());
			content.put("#orderId#", order.getOrderPublicId());
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
	
	public String detailPublicOrder() {
		try {
			log.debug("AdminPublicOrderAction.detailPublicOrder()");
			
			order = orderDao.getDetailOrderpublic(id);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
