package com.ccw.action.admin.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Orderpublic;
import com.ccw.bean.Userdetail;
import com.ccw.dao.OrderDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPublicOrderConclusionAction extends ActionSupport {
	private static final long serialVersionUID = 217001519408416200L;
	private Log log = LogFactory.getLog(AdminPublicOrderConclusionAction.class);
	private LinkedHashMap<Userdetail, Long> recordList;
	private Integer type;
	private OrderDaoInf orderDao;

	public LinkedHashMap<Userdetail, Long> getRecordList() {
		return recordList;
	}

	public void setRecordList(LinkedHashMap<Userdetail, Long> recordList) {
		this.recordList = recordList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public String concludePublicOrderReport() {
		try {
			log.debug("AdminPublicOrderConclusionAction.concludePublicOrderReport()");
			
			if(type == null)
				type = 1;
			
			switch(type) {
				case 1:
					ArrayList<Orderpublic> list1 = orderDao.getAllOrderpublicByCostMax(null, null);
					Iterator<Orderpublic> iList1 = list1.iterator();
					Orderpublic op1 = null;
					recordList = new LinkedHashMap<Userdetail, Long>();
					int i=0;
					while(iList1.hasNext()) {
						System.out.println(i++);
						op1 = iList1.next();
						recordList.put(op1.getOrderbasic().getUserdetail(), orderDao.getUserPublicorderTotalCost(op1.getOrderbasic().getUserdetail().getUserId(), null, null));
					}
					break;
					
				case 2:
					Calendar c2 = Calendar.getInstance();
					Date to2 = new Date();
					c2.setTime(to2);
					c2.add(Calendar.DATE, -30);
					Date from2 = c2.getTime();
					ArrayList<Orderpublic> list2 = orderDao.getAllOrderpublicByCostMax(from2, to2);
					Iterator<Orderpublic> iList2 = list2.iterator();
					Orderpublic op2 = null;
					recordList = new LinkedHashMap<Userdetail, Long>();
					while(iList2.hasNext()) {
						op2 = iList2.next();
						recordList.put(op2.getOrderbasic().getUserdetail(), orderDao.getUserPublicorderTotalCost(op2.getOrderbasic().getUserdetail().getUserId(), from2, to2));
					}
					break;
				case 3:
					Calendar c3 = Calendar.getInstance();
					Date to3 = new Date();
					c3.setTime(to3);
					c3.add(Calendar.DATE, -60);
					Date from3 = c3.getTime();
					ArrayList<Orderpublic> list3 = orderDao.getAllOrderpublicByCostMax(from3, to3);
					Iterator<Orderpublic> iList3 = list3.iterator();
					Orderpublic op3 = null;
					recordList = new LinkedHashMap<Userdetail, Long>();
					while(iList3.hasNext()) {
						op3 = iList3.next();
						recordList.put(op3.getOrderbasic().getUserdetail(), orderDao.getUserPublicorderTotalCost(op3.getOrderbasic().getUserdetail().getUserId(), from3, to3));
					}
					break;
			}
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
