package com.ccw.vm.command;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.common.HtmlUtil;
import com.ccw.common.Params;
import com.ccw.dao.CourseDaoInf;
import com.ccw.vm.bean.Bottomfoot;

public class BottomfootCommand extends BaseCommand {
	private Log log = LogFactory.getLog(BottomfootCommand.class);
	private CourseDaoInf courseDao;
	private String contextpath;
	private Bottomfoot bottomfoot;
	
	public BottomfootCommand(String contextpath, CourseDaoInf courseDao) {
		this.courseDao = courseDao;
		this.contextpath = contextpath;
	}
	
	@Override
	public void preDo() {
		try {
			bottomfoot = new Bottomfoot();
			bottomfoot.setCourseContext(Params.COURSE_PATH_CONTEXT);
			bottomfoot.setCourseList(courseDao.getCoursesOfBottomfoot());
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
	
	@Override
	public void generateHtml() {
		HtmlUtil.generateHtml(contextpath + File.separator + Params.WEBSITE_BOTTOM_TEMP_FOLDER_NAME + File.separator, bottomfoot.getClass().getSimpleName().toLowerCase() + ".html", "com/ccw/vm/template/bottom/", bottomfoot);
	}
	
	@Override
	public void postDo() {
		
	}
}
