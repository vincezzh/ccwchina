package com.ccw.action.admin.user;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminMemberAction extends ActionSupport {
	private static final long serialVersionUID = 2591625787438399246L;
	private Log log = LogFactory.getLog(AdminMemberAction.class);
	private ArrayList<Userdetail> userList;
	private Userdetail user;
	private String userId;
	private ArrayList<Orderpublic> publicOrderList;
	private ArrayList<Orderprivate> privateOrderList;
	private ArrayList<Orderitem> itemOrderList;
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	private PageContainer pageContainer;
	private Integer pageNumber;
	
	public ArrayList<Userdetail> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Userdetail> userList) {
		this.userList = userList;
	}

	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public ArrayList<Orderitem> getItemOrderList() {
		return itemOrderList;
	}

	public void setItemOrderList(ArrayList<Orderitem> itemOrderList) {
		this.itemOrderList = itemOrderList;
	}

	public String showUser() {
		try {
			log.debug("AdminNewsAction.showUser()");
			if(pageNumber == null) {
				pageNumber = 1;
			}
			
			userList = userDao.getAllUserByPageNumber(pageNumber);
			
			Long count = userDao.getAllUserCount();
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_USER_PER_PAGE_NUMBER), "/_administrator/archives-members.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String detailUser() {
		try {
			log.debug("AdminNewsAction.detailUser()");

			user = userDao.getDetailUser(userId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String detailUserOrders() {
		try {
			log.debug("AdminNewsAction.detailUserOrders()");

			publicOrderList = orderDao.getAllOrderpublicByUserByStatusByPageNumber(userId, null, null, -1);
			privateOrderList = orderDao.getAllOrderprivateByUserByStatusByPageNumber(userId, null, null, -1);
			itemOrderList = orderDao.getAllOrderitemByUserByStatusByPageNumber(userId, null, null, -1);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
