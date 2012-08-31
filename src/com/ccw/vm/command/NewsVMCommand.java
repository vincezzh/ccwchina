package com.ccw.vm.command;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.News;
import com.ccw.common.HtmlUtil;
import com.ccw.common.Params;
import com.ccw.dao.CommonDaoInf;
import com.ccw.vm.bean.NewsVM;

public class NewsVMCommand extends BaseCommand {
	private Log log = LogFactory.getLog(NewsVMCommand.class);
	private CommonDaoInf commonDao;
	private String contextpath;
	private NewsVM news;
	
	public NewsVMCommand(String contextpath, CommonDaoInf commonDao) {
		this.commonDao = commonDao;
		this.contextpath = contextpath;
	}

	@Override
	public void preDo() {
		try {
			news = new NewsVM();
			news.setToptitle(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_TOP_TEMP_FOLDER_NAME + File.separator + "toptitle.html"), "UTF-8"));
			news.setBottomfoot(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_BOTTOM_TEMP_FOLDER_NAME + File.separator + "bottomfoot.html"), "UTF-8"));
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}

	@Override
	public void generateHtml() {
		try {
			ArrayList<News> newsList = commonDao.getAllNews();
			Iterator<News> iNewsList = newsList.iterator();
			News n = null;
			while(iNewsList.hasNext()) {
				n = iNewsList.next();
				news.setNews(n);
				
				HtmlUtil.generateHtml(contextpath + File.separator + Params.NEWS_PATH_CONTEXT + File.separator, n.getNewsId() + ".html", "com/ccw/vm/template/", news);
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
