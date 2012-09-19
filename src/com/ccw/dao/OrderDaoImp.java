package com.ccw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.bean.Orderbasic;
import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.common.Params;

@Transactional
public class OrderDaoImp implements OrderDaoInf {
	private Log log = LogFactory.getLog(OrderDaoImp.class);
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public void savePublicOrder(Orderpublic order) throws Exception {
		log.debug("OrderDaoImp.savePublicOrder()");

		getEntityManager().persist(getEntityManager().merge(order));
	}

	public void savePrivateOrder(Orderprivate order) throws Exception {
		log.debug("OrderDaoImp.savePrivateOrder()");
		
		getEntityManager().persist(getEntityManager().merge(order));
	}

	public ArrayList<Orderpublic> getAllOrderpublicByUserByStatusByPageNumber(
			String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderpublicByUserByStatusByPageNumber()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.orderbasic.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select o from Orderpublic o where o.avaliable='yes'" + queryPlus.toString() + " order by o.orderbasic.bookingTime desc");
		if(pageNumber != null) {
			query.setFirstResult((pageNumber - 1) * recordNumber);
			query.setMaxResults(recordNumber);
		}
		ArrayList<Orderpublic> list = (ArrayList<Orderpublic>)query.getResultList();
		return list;
	}
	
	public ArrayList<Orderpublic> getAllOrderpublicByUser(String userId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderpublicByUser()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.orderbasic.userdetail.userId='" + userId + "'");
		}
		
		Query query = getEntityManager().createQuery("select o from Orderpublic o where o.avaliable='yes' and o.coursecalendar.classDate>='" + sdf.format(today) + "'" + queryPlus.toString() + " order by o.coursecalendar.classDate desc");
		ArrayList<Orderpublic> list = (ArrayList<Orderpublic>)query.getResultList();
		return list;
	}

	public Long getAllOrderpublicCountByUserByStatus(String userId, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderpublicCountByUserByStatus()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.orderbasic.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select count(o) from Orderpublic o where o.avaliable='yes'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	public ArrayList<Orderprivate> getAllOrderprivateByUserByStatusByPageNumber(
			String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderprivateByUserByStatusByPageNumber()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.orderbasic.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select o from Orderprivate o where o.avaliable='yes'" + queryPlus.toString() + " order by o.orderbasic.bookingTime desc");
		if(pageNumber != null) {
			query.setFirstResult((pageNumber - 1) * recordNumber);
			query.setMaxResults(recordNumber);
		}
		ArrayList<Orderprivate> list = (ArrayList<Orderprivate>)query.getResultList();
		return list;
	}

	public Long getAllOrderprivateCountByUserByStatus(String userId, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderprivateCountByUserByStatus()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.orderbasic.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderbasic.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select count(o) from Orderprivate o where o.avaliable='yes'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public Orderpublic getDetailOrderpublic(String orderId) throws Exception {
		log.debug("OrderDaoImp.getDetailOrderpublic()");
		
		return getEntityManager().find(Orderpublic.class, orderId);
	}

	public void updatePublicOrder(Orderpublic order) throws Exception {
		log.debug("OrderDaoImp.updatePublicOrder()");
		
		getEntityManager().merge(order);
	}

	public Orderprivate getDetailOrderprivate(String orderId) throws Exception {
		log.debug("OrderDaoImp.getDetailOrderprivate()");
		
		return getEntityManager().find(Orderprivate.class, orderId);
	}

	public void updatePrivateOrder(Orderprivate order) throws Exception {
		log.debug("OrderDaoImp.updatePrivateOrder()");
		
		getEntityManager().merge(order);
	}

	public void saveOrderbasic(Orderbasic orderBasic) throws Exception {
		log.debug("OrderDaoImp.saveOrderbasic()");
		
		getEntityManager().persist(getEntityManager().merge(orderBasic));
	}

	public ArrayList<Orderpublic> getAllOrderpublicByDateForReminder(Date date)
			throws Exception {
		log.debug("OrderDaoImp.getAllOrderpublicByDate()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select o from Orderpublic o where o.avaliable='yes' and o.coursecalendar.classDate='" + sdf.format(date) + "'");
		ArrayList<Orderpublic> list = (ArrayList<Orderpublic>)query.getResultList();
		Iterator<Orderpublic> ilist = list.iterator();
		Orderpublic op = null;
		while(ilist.hasNext()) {
			op = ilist.next();
			op.getOrderbasic().getUserdetail().getPreferredLanguage();
			op.getOrderbasic().getContactPerson();
			op.getOrderPublicId();
			op.getCoursecalendar().getClassDate();
			op.getCoursecalendar().getClasstime().getClassTimeContent();
			op.getCoursecalendar().getCourselocation().getCourseLocationName();
			op.getOrderbasic().getEmail();
			op.getTotalPeopleNumber();
		}
		return list;
	}
	
	public ArrayList<Orderprivate> getAllOrderprivateByDateForReminder(Date date)
			throws Exception {
		log.debug("OrderDaoImp.getAllOrderprivateByDate()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createQuery("select o from Orderprivate o where o.avaliable='yes' and o.courseDate='" + sdf.format(date) + "'");
		ArrayList<Orderprivate> list = (ArrayList<Orderprivate>)query.getResultList();
		Iterator<Orderprivate> ilist = list.iterator();
		Orderprivate op = null;
		while(ilist.hasNext()) {
			op = ilist.next();
			op.getOrderbasic().getUserdetail().getPreferredLanguage();
			op.getOrderbasic().getContactPerson();
			op.getOrderPrivateId();
			op.getCourseDate();
			op.getClasstime().getClassTimeContent();
			op.getCourselocation().getCourseLocationName();
			op.getOrderbasic().getEmail();
		}
		return list;
	}
	
	public ArrayList<Orderpublic> getAllOrderpublicByCostMax(Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getAllOrderpublicByCostMax()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.orderbasic.bookingTime>='" + sdf.format(from) + "' and o.orderbasic.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select o from Orderpublic o where o.avaliable='yes'" + queryPlus.toString() + " group by o.orderbasic.userdetail.userId order by sum(o.orderbasic.points) desc");
		query.setFirstResult(0);
		query.setMaxResults(Params.ADMIN_SHOW_TOP_NUMBER);

		ArrayList<Orderpublic> list = (ArrayList<Orderpublic>)query.getResultList();
		return list;
	}
	
	public Long getUserPublicorderTotalCost(String userId, Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getUserPublicorderTotalCost()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.orderbasic.bookingTime>='" + sdf.format(from) + "' and o.orderbasic.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select sum(o.orderbasic.points) from Orderpublic o where o.avaliable='yes' and o.orderbasic.userdetail.userId='" + userId + "'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	public ArrayList<Orderprivate> getAllOrderprivateByCostMax(Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getAllOrderprivateByCostMax()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.orderbasic.bookingTime>='" + sdf.format(from) + "' and o.orderbasic.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select o from Orderprivate o where o.avaliable='yes'" + queryPlus.toString() + " group by o.orderbasic.userdetail.userId order by sum(o.orderbasic.points) desc");
		query.setFirstResult(0);
		query.setMaxResults(Params.ADMIN_SHOW_TOP_NUMBER);

		ArrayList<Orderprivate> list = (ArrayList<Orderprivate>)query.getResultList();
		return list;
	}
	
	public Long getUserPrivateorderTotalCost(String userId, Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getUserPrivateorderTotalCost()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.orderbasic.bookingTime>='" + sdf.format(from) + "' and o.orderbasic.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select sum(o.orderbasic.points) from Orderprivate o where o.avaliable='yes' and o.orderbasic.userdetail.userId='" + userId + "'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	public void saveItemOrder(Orderitem order) throws Exception {
		log.debug("OrderDaoImp.saveItemOrder()");

		getEntityManager().persist(getEntityManager().merge(order));
	}
	
	public ArrayList<Orderitem> getAllOrderitemByUserByStatusByPageNumber(
			String userId, Integer pageNumber, Integer recordNumber, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderitemByUserByStatusByPageNumber()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select o from Orderitem o where o.avaliable='yes'" + queryPlus.toString() + " order by o.bookingTime desc");
		if(pageNumber != null) {
			query.setFirstResult((pageNumber - 1) * recordNumber);
			query.setMaxResults(recordNumber);
		}
		ArrayList<Orderitem> list = (ArrayList<Orderitem>)query.getResultList();
		return list;
	}

	public Long getAllOrderitemCountByUserByStatus(String userId, Integer orderStatusId) throws Exception {
		log.debug("OrderDaoImp.getAllOrderitemCountByUserByStatus()");
		
		StringBuffer queryPlus = new StringBuffer();
		if(userId != null) {
			queryPlus.append(" and o.userdetail.userId='" + userId + "'");
		}
		if(orderStatusId != null) {
			if(orderStatusId != -1)
				queryPlus.append(" and o.orderstatus.orderStatusId=" + orderStatusId);
		}else {
			queryPlus.append(" and o.orderstatus.orderStatusId!=" + Params.ORDER_STATUS_FINISHED_ID);
		}
		
		Query query = getEntityManager().createQuery("select count(o) from Orderitem o where o.avaliable='yes'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public Orderitem getDetailOrderitem(String orderId) throws Exception {
		log.debug("OrderDaoImp.getDetailOrderitem()");
		
		return getEntityManager().find(Orderitem.class, orderId);
	}

	public void updateItemOrder(Orderitem order) throws Exception {
		log.debug("OrderDaoImp.updateItemOrder()");
		
		getEntityManager().merge(order);
	}
	
	public ArrayList<Orderitem> getAllOrderitemByCostMax(Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getAllOrderitemByCostMax()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.bookingTime>='" + sdf.format(from) + "' and o.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select o from Orderitem o where o.avaliable='yes'" + queryPlus.toString() + " group by o.userdetail.userId order by sum(o.points) desc");
		query.setFirstResult(0);
		query.setMaxResults(Params.ADMIN_SHOW_TOP_NUMBER);

		ArrayList<Orderitem> list = (ArrayList<Orderitem>)query.getResultList();
		return list;
	}
	
	public Long getUserItemorderTotalCost(String userId, Date from, Date to) throws Exception {
		log.debug("OrderDaoImp.getUserItemorderTotalCost()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer queryPlus = new StringBuffer();
		if(from != null && to != null) {
			queryPlus.append(" and o.bookingTime>='" + sdf.format(from) + "' and o.bookingTime<='" + sdf.format(to) + "'");
		}
		
		Query query = getEntityManager().createQuery("select sum(o.points) from Orderitem o where o.avaliable='yes' and o.userdetail.userId='" + userId + "'" + queryPlus.toString());
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public ArrayList<Orderpublic> getPublicOrderCalendarBycourseLocationByMonth(
			Integer courseLocationId, Date currentMonth) throws Exception {
		log.debug("OrderDaoImp.getPublicOrderCalendarBycourseLocationByMonth()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(currentMonth);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date from = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date to = c.getTime();
		Query query = getEntityManager().createQuery("select o from Orderpublic o where o.avaliable='yes' and o.coursecalendar.courselocation.courseLocationId=" + courseLocationId + " and o.coursecalendar.classDate>='" + sdf.format(from) + "' and o.coursecalendar.classDate<='" + sdf.format(to) + "' order by o.coursecalendar.classDate,o.coursecalendar.classtime.order asc");
		ArrayList<Orderpublic> list = (ArrayList<Orderpublic>)query.getResultList();
		return list;
	}

	public ArrayList<Orderprivate> getPrivateOrderCalendarBycourseLocationByMonth(
			Integer courseLocationId, Date currentMonth) throws Exception {
		log.debug("OrderDaoImp.getPrivateOrderCalendarBycourseLocationByMonth()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(currentMonth);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date from = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date to = c.getTime();
		Query query = getEntityManager().createQuery("select o from Orderprivate o where o.avaliable='yes' and o.courselocation.courseLocationId=" + courseLocationId + " and o.courseDate>='" + sdf.format(from) + "' and o.courseDate<='" + sdf.format(to) + "' order by o.courseDate,o.classtime.order asc");
		ArrayList<Orderprivate> list = (ArrayList<Orderprivate>)query.getResultList();
		return list;
	}
}
