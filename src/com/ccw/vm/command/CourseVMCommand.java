package com.ccw.vm.command;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Course;
import com.ccw.common.HtmlUtil;
import com.ccw.common.Params;
import com.ccw.dao.CourseDaoInf;
import com.ccw.vm.bean.CourseVM;

public class CourseVMCommand extends BaseCommand {
	private Log log = LogFactory.getLog(CourseVMCommand.class);
	private CourseDaoInf courseDao;
	private String contextpath;
	private CourseVM course;
	
	public CourseVMCommand(String contextpath, CourseDaoInf courseDao) {
		this.courseDao = courseDao;
		this.contextpath = contextpath;
	}

	@Override
	public void preDo() {
		try {
			course = new CourseVM();
			course.setLocationList(courseDao.getAllCourselocation());
			course.setToptitle(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_TOP_TEMP_FOLDER_NAME + File.separator + "toptitle.html"), "UTF-8"));
			course.setBottomfoot(FileUtils.readFileToString(new File(contextpath + File.separator + Params.WEBSITE_BOTTOM_TEMP_FOLDER_NAME + File.separator + "bottomfoot.html"), "UTF-8"));
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}

	@Override
	public void generateHtml() {
		try {
			ArrayList<Course> courses = courseDao.getAllCourse();
			Iterator<Course> iCourses = courses.iterator();
			Course c = null;
			while(iCourses.hasNext()) {
				c = iCourses.next();
				course.setCourse(c);
				
				HtmlUtil.generateHtml(contextpath + File.separator + Params.COURSE_PATH_CONTEXT + File.separator + c.getCoursetrunktype().getCourseTrunkTypePath() + File.separator + c.getCoursebranchtype().getCourseBranchTypePath() + File.separator, c.getCourseId() + ".html", "com/ccw/vm/template/", course);
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
