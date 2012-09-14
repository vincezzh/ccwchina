package com.ccw.action.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ccw.bean.Calendarwithcourse;
import com.ccw.bean.Course;
import com.ccw.bean.Coursecalendar;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class CourseListAction extends ActionSupport {
	private static final long serialVersionUID = -4961552815699434206L;
	private Log log = LogFactory.getLog(CourseListAction.class);
	private Date month;
	private String monthDate;
	private CourseDaoInf courseDao;

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public String getAMonthCalendar() {
		log.debug("MobileCourseListAction.getAMonthCalendar()");
		Document document = DocumentHelper.createDocument();  
        Element ccwCalendar = document.addElement("CCWCalendar");
		try {
			if(monthDate == null) {
				month = new Date();
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				month = sdf.parse(monthDate);
			}
			
			ArrayList<Coursecalendar> courses = courseDao.getCoursecalendarByMonth(month);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			for(Coursecalendar aCourse : courses) {
				Element courseCalendar = ccwCalendar.addElement("courseCalendar");
				
				Element courseCalendarId = courseCalendar.addElement("courseCalendarId");
				courseCalendarId.addText(aCourse.getCourseCalendarId());
				
				Element classDate = courseCalendar.addElement("classDate");
				classDate.addText(sdf1.format(aCourse.getClassDate()));
				
				Element classTime = courseCalendar.addElement("classTime");
				Element classTimeId = classTime.addElement("id");
				classTimeId.addText(aCourse.getClasstime().getClassTimeId().toString());
				Element classTimeName = classTime.addElement("name");
				classTimeName.addText(aCourse.getClasstime().getClassTimeContent());
				
				Element pricePerPerson = courseCalendar.addElement("pricePerPerson");
				pricePerPerson.addText(aCourse.getPricePerPerson().toString());
				
				Element seatLeft = courseCalendar.addElement("seatLeft");
				seatLeft.addText(aCourse.getSeatLeft().toString());
				
				Element courseLocation = courseCalendar.addElement("courseLocation");
				Element courseLocationId = courseLocation.addElement("id");
				courseLocationId.addText(aCourse.getCourselocation().getCourseLocationId().toString());
				Element courseLocationName = courseLocation.addElement("name");
				courseLocationName.addText(aCourse.getCourselocation().getCourseLocationName());
				
				Element courseTrunkType = courseCalendar.addElement("courseTrunkType");
				Element courseTrunkTypeId = courseTrunkType.addElement("id");
				courseTrunkTypeId.addText(aCourse.getCoursetrunktype().getCourseTrunkTypeId().toString());
				Element courseTrunkTypeName = courseTrunkType.addElement("name");
				courseTrunkTypeName.addText(PropertyUtil.get(Params.USA, aCourse.getCoursetrunktype().getCourseTrunkTypeNameKey()));
				Element fontColor = courseTrunkType.addElement("fontColor");
				fontColor.addText(aCourse.getCoursetrunktype().getFontColor());
				Element backgroundColor = courseTrunkType.addElement("backgroundColor");
				backgroundColor.addText(aCourse.getCoursetrunktype().getBackgroundColor());
				Element courseBranchType = courseTrunkType.addElement("courseBranchType");
				Element courseBranchTypeId = courseBranchType.addElement("id");
				courseBranchTypeId.addText(aCourse.getCoursebranchtype().getCourseBranchTypeId().toString());
				Element courseBranchTypeName = courseBranchType.addElement("name");
				courseBranchTypeName.addText(PropertyUtil.get(Params.USA, aCourse.getCoursebranchtype().getCourseBranchTypeNameKey()));
				
				Element coursesElement = courseCalendar.addElement("courses");
				for(Calendarwithcourse calendarwithcourse : aCourse.getCalendarwithcourses()) {
					Course course = calendarwithcourse.getCourse();
					
					Element courseElement = coursesElement.addElement("course");
					Element courseIdElement = courseElement.addElement("id");
					courseIdElement.addText(course.getCourseId());
					Element courseNameElement = courseElement.addElement("courseNameEn");
					courseNameElement.addText(course.getCourseNameEn());
					Element pictureOne = courseElement.addElement("pictureOne");
					pictureOne.addText(course.getPictureOne());
				}
			}
		} catch (Exception e) {
			Element errorMsg = ccwCalendar.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
        return null;
	}
	
	private void sendBackXMLToMobile(Document document) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();      
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(document.asXML()); 
	        out.flush();
	        out.close();
		} catch (IOException e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
}
