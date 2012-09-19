package com.ccw.dao;

import java.util.ArrayList;
import java.util.Date;

import com.ccw.bean.Orderbasic;
import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;

public interface OrderDaoInf {
	public void savePublicOrder(Orderpublic order) throws Exception;
	
	public void savePrivateOrder(Orderprivate order) throws Exception;
	
	public ArrayList<Orderpublic> getAllOrderpublicByUserByStatusByPageNumber(String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception;
	
	public ArrayList<Orderpublic> getAllOrderpublicByUser(String userId) throws Exception;
	
	public Long getAllOrderpublicCountByUserByStatus(String userId, Integer orderStatusId) throws Exception;
	
	public ArrayList<Orderprivate> getAllOrderprivateByUserByStatusByPageNumber(String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception;
	
	public Long getAllOrderprivateCountByUserByStatus(String userId, Integer orderStatusId) throws Exception;
	
	public Orderpublic getDetailOrderpublic(String orderId) throws Exception;
	
	public void updatePublicOrder(Orderpublic order) throws Exception;
	
	public Orderprivate getDetailOrderprivate(String orderId) throws Exception;
	
	public void updatePrivateOrder(Orderprivate order) throws Exception;
	
	public void saveOrderbasic(Orderbasic orderBasic) throws Exception;
	
	public ArrayList<Orderpublic> getAllOrderpublicByDateForReminder(Date date) throws Exception;
	
	public ArrayList<Orderprivate> getAllOrderprivateByDateForReminder(Date date) throws Exception;
	
	public ArrayList<Orderpublic> getAllOrderpublicByCostMax(Date from, Date to) throws Exception;
	
	public Long getUserPublicorderTotalCost(String userId, Date from, Date to) throws Exception;
	
	public ArrayList<Orderprivate> getAllOrderprivateByCostMax(Date from, Date to) throws Exception;
	
	public Long getUserPrivateorderTotalCost(String userId, Date from, Date to) throws Exception;
	
	public void saveItemOrder(Orderitem order) throws Exception;
	
	public ArrayList<Orderitem> getAllOrderitemByUserByStatusByPageNumber(String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception;
	
	public Long getAllOrderitemCountByUserByStatus(String userId, Integer orderStatusId) throws Exception;
	
	public Orderitem getDetailOrderitem(String orderId) throws Exception;
	
	public void updateItemOrder(Orderitem order) throws Exception;
	
	public ArrayList<Orderitem> getAllOrderitemByCostMax(Date from, Date to) throws Exception;
	
	public Long getUserItemorderTotalCost(String userId, Date from, Date to) throws Exception;
	
	public ArrayList<Orderpublic> getPublicOrderCalendarBycourseLocationByMonth(Integer courseLocationId, Date currentMonth) throws Exception;
	
	public ArrayList<Orderprivate> getPrivateOrderCalendarBycourseLocationByMonth(Integer courseLocationId, Date currentMonth) throws Exception;
}
