package com.ccw.action.admin.market;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Item;
import com.ccw.bean.Itemtrunktype;
import com.ccw.common.Params;
import com.ccw.common.pages.PageContainer;
import com.ccw.dao.ItemDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class AdminItemAction extends ActionSupport {
	private static final long serialVersionUID = -6316426492225211072L;
	private Log log = LogFactory.getLog(AdminItemAction.class);
	private ArrayList<Itemtrunktype> trunkList;
	private Item item;
	private String itemId;
	private ArrayList<Item> itemList;
	private String startTime;
	private String endTime;
	private ItemDaoInf itemDao;
	private PageContainer pageContainer;
	private Integer pageNumber;

	public ArrayList<Itemtrunktype> getTrunkList() {
		return trunkList;
	}

	public void setTrunkList(ArrayList<Itemtrunktype> trunkList) {
		this.trunkList = trunkList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String gotoAddItemPage() {
		try {
			log.debug("AdminItemAction.gotoAddItemPage()");

			trunkList = itemDao.getAllItemtrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String addItem() {
		try {
			log.debug("AdminItemAction.addItem()");

			item.setAvaliable("yes");
			Date now = new Date();
			item.setItemId("I-" + now.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			item.setStartTime(sdf.parse(startTime));
			item.setEndTime(sdf.parse(endTime));
			itemDao.addItem(item);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String showItem() {
		try {
			log.debug("AdminItemAction.showItem()");
			if(pageNumber == null) {
				pageNumber = 1;
			}
			
			itemList = itemDao.getAllItemByPageNumber(pageNumber);
			
			Long count = itemDao.getAllItemsCount();
			pageContainer = new PageContainer();
			pageContainer.setup(pageNumber, (int)Math.ceil((double)count/Params.ADMIN_ITEM_PER_PAGE_NUMBER), "/_administrator/archives-item.htm", "");
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteItem() {
		try {
			log.debug("AdminItemAction.deleteItem()");

			itemDao.deleteItem(itemId);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String gotoUpdateItemPage() {
		try {
			log.debug("AdminItemAction.gotoUpdateItemPage()");

			item = itemDao.getDetailItem(itemId);
			trunkList = itemDao.getAllItemtrunktype();
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateItem() {
		try {
			log.debug("AdminItemAction.updateItem()");

			item.setAvaliable("yes");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			item.setStartTime(sdf.parse(startTime));
			item.setEndTime(sdf.parse(endTime));
			itemDao.updateItem(item);
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
