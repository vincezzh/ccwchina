package com.ccw.action.store;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Item;
import com.ccw.bean.Itemtrunktype;
import com.ccw.common.Params;
import com.ccw.dao.ItemDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ItemListAction extends ActionSupport {
	private static final long serialVersionUID = -7620889497051122752L;
	private Log log = LogFactory.getLog(ItemListAction.class);
	private ArrayList<Item> itemList;
	private ArrayList<Itemtrunktype> trunkList;
	private String language;
	private String itemContext;
	private ItemDaoInf itemDao;
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public ItemDaoInf getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDaoInf itemDao) {
		this.itemDao = itemDao;
	}

	public ArrayList<Itemtrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Itemtrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getItemContext() {
		return itemContext;
	}

	public void setItemContext(String itemContext) {
		this.itemContext = itemContext;
	}

	public String gotoStore() {
		try {
			log.debug("ItemListAction.gotoStore()");
			
			itemList = itemDao.getAllItem();
			trunkList = itemDao.getAllItemtrunktype();
			itemContext = Params.ITEM_PATH_CONTEXT;
			Map session = ActionContext.getContext().getSession();
			Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
			if(locale == null) {
				locale = Locale.US;
				session.put("WW_TRANS_I18N_LOCALE", locale);
			}
			language = locale.getLanguage();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
