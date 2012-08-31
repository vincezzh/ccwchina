package com.ccw.dao;

import java.util.ArrayList;

import com.ccw.bean.Item;
import com.ccw.bean.Itemtrunktype;

public interface ItemDaoInf {
	public ArrayList<Item> getAllItem() throws Exception;
	
	public ArrayList<Itemtrunktype> getAllItemtrunktype() throws Exception;
	
	public void addItemtrunktype(Itemtrunktype trunk) throws Exception;
	
	public void deleteItemtrunktype(Integer itemTrunkTypeId) throws Exception;
	
	public Itemtrunktype getDetailItemtrunktype(Integer itemTrunkTypeId) throws Exception;
	
	public void updateItemtrunktype(Itemtrunktype trunk) throws Exception;
	
	public void addItem(Item item) throws Exception;
	
	public ArrayList<Item> getAllItemByPageNumber(Integer pageNumber) throws Exception;
	
	public Long getAllItemsCount() throws Exception;
	
	public void deleteItem(String itemId) throws Exception;
	
	public Item getDetailItem(String itemId) throws Exception;
	
	public void updateItem(Item item) throws Exception;
}
