package com.ccw.vm.command;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.common.HtmlUtil;
import com.ccw.common.Params;
import com.ccw.dao.CommonDaoInf;
import com.ccw.vm.bean.Toptitle;

public class ToptitleCommand extends BaseCommand {
	private Log log = LogFactory.getLog(ToptitleCommand.class);
	private CommonDaoInf commonDao;
	private String contextpath;
	private Toptitle toptitle;
	
	public ToptitleCommand(String contextpath, CommonDaoInf commonDao) {
		this.commonDao = commonDao;
		this.contextpath = contextpath;
	}

	@Override
	public void preDo() {
		try {
			toptitle = new Toptitle();
			toptitle.setAdvertisment(commonDao.getToptitleAdvertisement());
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}

	@Override
	public void generateHtml() {
		HtmlUtil.generateHtml(contextpath + File.separator + Params.WEBSITE_TOP_TEMP_FOLDER_NAME + File.separator, toptitle.getClass().getSimpleName().toLowerCase() + ".html", "com/ccw/vm/template/top/", toptitle);
	}

	@Override
	public void postDo() {
		
	}

}
