package com.ccw.action.admin.market;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Itemtrunktype;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.ItemDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminItemtypeAction extends ActionSupport {
	private static final long serialVersionUID = 6972740259810121559L;
	private Log log = LogFactory.getLog(AdminItemtypeAction.class);
	private ArrayList<Itemtrunktype> trunkList;
	private String nameCN;
	private String nameEN;
	private String nameJP;
	private Itemtrunktype trunk;
	private Integer itemTrunkTypeId;
	private ItemDaoInf itemDao;

	public ArrayList<Itemtrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Itemtrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameJP() {
		return nameJP;
	}

	public void setNameJP(String nameJP) {
		this.nameJP = nameJP;
	}

	public Itemtrunktype getTrunk() {
		return trunk;
	}

	public void setTrunk(Itemtrunktype trunk) {
		this.trunk = trunk;
	}

	public Integer getItemTrunkTypeId() {
		return itemTrunkTypeId;
	}

	public void setItemTrunkTypeId(Integer itemTrunkTypeId) {
		this.itemTrunkTypeId = itemTrunkTypeId;
	}

	public ItemDaoInf getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDaoInf itemDao) {
		this.itemDao = itemDao;
	}

	public String gotoAddItemPage() {
		try {
			log.debug("AdminItemtypeAction.gotoAddItemPage()");

			trunkList = itemDao.getAllItemtrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String addCategoryItem() {
		try {
			log.debug("AdminItemtypeAction.addCategoryItem()");

			trunk.setAvaliable("yes");
			itemDao.addItemtrunktype(trunk);
			
			String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
			PropertyUtil.writeToAllLanguageProperties(trunk.getItemTrunkTypeNameKey(), nameValues);
				
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showCategoryItem() {
		try {
			log.debug("AdminItemtypeAction.showCategoryItem()");

			trunkList = itemDao.getAllItemtrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteCategoryItem() {
		try {
			log.debug("AdminItemtypeAction.deleteCategoryItem()");

			itemDao.deleteItemtrunktype(itemTrunkTypeId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdateItemPage() {
		try {
			log.debug("AdminItemtypeAction.gotoUpdateItemPage()");

			trunkList = itemDao.getAllItemtrunktype();
			trunk = itemDao.getDetailItemtrunktype(itemTrunkTypeId);
			
			nameEN = PropertyUtil.get(Params.USA, trunk.getItemTrunkTypeNameKey());
			nameCN = PropertyUtil.get(Params.CHINA, trunk.getItemTrunkTypeNameKey());
			nameJP = PropertyUtil.get(Params.JAPAN, trunk.getItemTrunkTypeNameKey());
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateCategoryItem() {
		try {
			log.debug("AdminItemtypeAction.updateCategoryItem()");

			trunk.setAvaliable("yes");
			itemDao.updateItemtrunktype(trunk);
			
			String[] nameValues = new String[]{nameEN, nameEN, nameCN, nameJP}; 
			PropertyUtil.writeToAllLanguageProperties(trunk.getItemTrunkTypeNameKey(), nameValues);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
