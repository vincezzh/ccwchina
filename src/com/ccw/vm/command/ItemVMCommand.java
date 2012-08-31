package com.ccw.vm.command;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Item;
import com.ccw.common.HtmlUtil;
import com.ccw.common.Params;
import com.ccw.dao.ItemDaoInf;
import com.ccw.vm.bean.ItemVM;

public class ItemVMCommand extends BaseCommand {
	private Log log = LogFactory.getLog(ItemVMCommand.class);
	private ItemDaoInf itemDao;
	private String contextpath;
	private ItemVM item;
	
	public ItemVMCommand(String contextpath, ItemDaoInf itemDao) {
		this.itemDao = itemDao;
		this.contextpath = contextpath;
	}

	@Override
	public void preDo() {
		try {
			item = new ItemVM();
			item.setToptitle(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_TOP_TEMP_FOLDER_NAME + File.separator + "toptitle.html"), "UTF-8"));
			item.setBottomfoot(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_BOTTOM_TEMP_FOLDER_NAME + File.separator + "bottomfoot.html"), "UTF-8"));
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}

	@Override
	public void generateHtml() {
		try {
			ArrayList<Item> items = itemDao.getAllItem();
			Iterator<Item> iItems = items.iterator();
			Item i = null;
			while(iItems.hasNext()) {
				i = iItems.next();
				item.setItem(i);
				
				HtmlUtil.generateHtml(contextpath + File.separator + Params.ITEM_PATH_CONTEXT + File.separator + i.getItemtrunktype().getItemTrunkTypePath() + File.separator, i.getItemId() + ".html", "com/ccw/vm/template/", item);
			}
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}

	@Override
	public void postDo() {
		
	}

}
