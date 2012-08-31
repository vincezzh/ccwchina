package com.ccw.action.user;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.bean.Packagewithcourse;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.CourseDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrdersAction extends ActionSupport {
	private static final long serialVersionUID = -3169125370754634661L;
	private Log log = LogFactory.getLog(OrdersAction.class);
	private Userdetail user;
	private ArrayList<Orderpublic> publicOrderList;
	private ArrayList<Orderprivate> privateOrderList;
	private ArrayList<Orderitem> itemOrderList;
	private ArrayList<Packagewithcourse> packageCourses;
	private Orderpublic puOrder;
	private Orderprivate prOrder;
	private Orderitem itOrder;
	private String id;
	private String language;
	private OrderDaoInf orderDao;
	private CourseDaoInf courseDao;
	private PageContainer pageContainer;
	private Integer pageNumber;
	
	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public ArrayList<Orderpublic> getPublicOrderList() {
		return publicOrderList;
	}

	public void setPublicOrderList(ArrayList<Orderpublic> publicOrderList) {
		this.publicOrderList = publicOrderList;
	}

	public ArrayList<Orderprivate> getPrivateOrderList() {
		return privateOrderList;
	}

	public void setPrivateOrderList(ArrayList<Orderprivate> privateOrderList) {
		this.privateOrderList = privateOrderList;
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

	public ArrayList<Orderitem> getItemOrderList() {
		return itemOrderList;
	}

	public void setItemOrderList(ArrayList<Orderitem> itemOrderList) {
		this.itemOrderList = itemOrderList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Orderpublic getPuOrder() {
		return puOrder;
	}

	public void setPuOrder(Orderpublic puOrder) {
		this.puOrder = puOrder;
	}

	public Orderprivate getPrOrder() {
		return prOrder;
	}

	public void setPrOrder(Orderprivate prOrder) {
		this.prOrder = prOrder;
	}

	public Orderitem getItOrder() {
		return itOrder;
	}

	public void setItOrder(Orderitem itOrder) {
		this.itOrder = itOrder;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getPublicOrder() {
		try {
			log.debug("OrdersAction.getPublicOrder()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			publicOrderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, null);

			Long count = orderDao.getAllOrderpublicCountByUserByStatus(user.getUserId(), null);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/public-orders.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getPublicOrderHistory() {
		try {
			log.debug("OrdersAction.getPublicOrderHistory()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			publicOrderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderpublicCountByUserByStatus(user.getUserId(), Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/public-orders-history.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getPrivateOrder() {
		try {
			log.debug("OrdersAction.getPrivateOrder()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			privateOrderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, null);
			
			Long count = orderDao.getAllOrderprivateCountByUserByStatus(user.getUserId(), null);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/private-orders.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getPrivateOrderHistory() {
		try {
			log.debug("OrdersAction.getPrivateOrderHistory()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			privateOrderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderprivateCountByUserByStatus(user.getUserId(), Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/private-orders-history.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getItemOrder() {
		try {
			log.debug("OrdersAction.getItemOrder()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			itemOrderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, null);
			
			Long count = orderDao.getAllOrderitemCountByUserByStatus(user.getUserId(), null);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/item-orders.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getItemOrderHistory() {
		try {
			log.debug("OrdersAction.getItemOrderHistory()");
			
			Map session = ActionContext.getContext().getSession();
			user = (Userdetail)session.get("user_session");
			
			if(pageNumber == null)
				pageNumber = 1;
			itemOrderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(user.getUserId(), pageNumber, Params.ORDER_PER_PAGE_NUMBER, Params.ORDER_STATUS_FINISHED_ID);
			
			Long count = orderDao.getAllOrderitemCountByUserByStatus(user.getUserId(), Params.ORDER_STATUS_FINISHED_ID);
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ORDER_PER_PAGE_NUMBER), "/user/item-orders-history.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getPublicOrderDetail() {
		try {
			log.debug("OrdersAction.getPublicOrderDetail()");
			
			puOrder = orderDao.getDetailOrderpublic(id);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String getPrivateOrderDetail() {
		try {
			log.debug("OrdersAction.getPrivateOrderDetail()");
			
			prOrder = orderDao.getDetailOrderprivate(id);
			packageCourses = courseDao.getAllPackagewithcourseByCoursepackage(prOrder.getCoursepackage().getCoursePackageId());
			
			Map session = ActionContext.getContext().getSession();
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
	
	public String getItemOrderDetail() {
		try {
			log.debug("OrdersAction.getItemOrderDetail()");
			
			itOrder = orderDao.getDetailOrderitem(id);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
