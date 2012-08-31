package com.ccw.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.bean.Item;
import com.ccw.bean.Itemtrunktype;
import com.ccw.common.Params;

@Transactional
public class ItemDaoImp implements ItemDaoInf {
	private Log log = LogFactory.getLog(ItemDaoImp.class);
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public ArrayList<Item> getAllItem() throws Exception {
		log.debug("ItemDaoImp.getAllItem()");
		
		Query query = getEntityManager().createQuery("select i from Item i where i.avaliable='yes'");
		ArrayList<Item> list = (ArrayList<Item>)query.getResultList();
		return list;
	}
	
	public ArrayList<Itemtrunktype> getAllItemtrunktype() throws Exception {
		log.debug("ItemDaoImp.getAllItemtrunktype()");
		
		Query query = getEntityManager().createQuery("select i from Itemtrunktype i where i.avaliable='yes'");
		ArrayList<Itemtrunktype> list = (ArrayList<Itemtrunktype>)query.getResultList();
		return list;
	}

	public void addItemtrunktype(Itemtrunktype trunk) throws Exception {
		log.debug("ItemDaoImp.addItemtrunktype()");
		
		getEntityManager().persist(getEntityManager().merge(trunk));
	}

	public void deleteItemtrunktype(Integer itemTrunkTypeId) throws Exception {
		log.debug("ItemDaoImp.deleteItemtrunktype()");
		
		Itemtrunktype trunk = getEntityManager().find(Itemtrunktype.class, itemTrunkTypeId);
		trunk.setAvaliable("no");
		getEntityManager().merge(trunk);
	}

	public Itemtrunktype getDetailItemtrunktype(Integer itemTrunkTypeId)
			throws Exception {
		log.debug("ItemDaoImp.getDetailItemtrunktype()");
		
		return getEntityManager().find(Itemtrunktype.class, itemTrunkTypeId);
	}

	public void updateItemtrunktype(Itemtrunktype trunk) throws Exception {
		log.debug("ItemDaoImp.updateItemtrunktype()");
		
		getEntityManager().merge(trunk);
	}

	public void addItem(Item item) throws Exception {
		log.debug("ItemDaoImp.addItem()");
		
		getEntityManager().persist(getEntityManager().merge(item));
	}

	public ArrayList<Item> getAllItemByPageNumber(Integer pageNumber)
			throws Exception {
		log.debug("ItemDaoImp.getAllItemByPageNumber()");
		
		Query query = getEntityManager().createQuery("select i from Item i where i.avaliable='yes' order by i.itemId desc");
		query.setFirstResult((pageNumber - 1) * Params.ADMIN_ITEM_PER_PAGE_NUMBER);
		query.setMaxResults(Params.ADMIN_ITEM_PER_PAGE_NUMBER);
		ArrayList<Item> list = (ArrayList<Item>)query.getResultList();
		return list;
	}

	public Long getAllItemsCount() throws Exception {
		log.debug("ItemDaoImp.getAllItemsCount()");
		
		Query query = getEntityManager().createQuery("select count(i) from Item i where i.avaliable='yes'");
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public void deleteItem(String itemId) throws Exception {
		log.debug("ItemDaoImp.deleteItem()");
		
		Item item = getEntityManager().find(Item.class, itemId);
		item.setAvaliable("no");
		getEntityManager().merge(item);
	}

	public Item getDetailItem(String itemId) throws Exception {
		log.debug("ItemDaoImp.getDetailItem()");
		
		return getEntityManager().find(Item.class, itemId);
	}

	public void updateItem(Item item) throws Exception {
		log.debug("ItemDaoImp.updateItem()");
		
		getEntityManager().merge(item);
	}
}
