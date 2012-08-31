package com.ccw.action.admin.common;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ccw.bean.Course;
import com.ccw.bean.Coursebranchtype;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.CourseDaoInf;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminTypeAction extends ActionSupport {
	private static final long serialVersionUID = -2418534056804533239L;
	private Log log = LogFactory.getLog(AdminTypeAction.class);
	private ArrayList<Coursebranchtype> branchList;
	private Integer courseTrunkTypeId;
	private ArrayList<Course> courseList;
	private CourseDaoInf courseDao;

	public ArrayList<Coursebranchtype> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Coursebranchtype> branchList) {
		this.branchList = branchList;
	}

	public CourseDaoInf getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoInf courseDao) {
		this.courseDao = courseDao;
	}

	public Integer getCourseTrunkTypeId() {
		return courseTrunkTypeId;
	}

	public void setCourseTrunkTypeId(Integer courseTrunkTypeId) {
		this.courseTrunkTypeId = courseTrunkTypeId;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public String changeCoursebranchtypeList() {
		try {
			log.debug("Start AdminTypeAction.changeCoursebranchtypeList()");
			branchList = courseDao.getAllCourseBranchTypeByTrunk(courseTrunkTypeId);
			Iterator<Coursebranchtype> i = branchList.iterator();

			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse) ctx
					.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			Coursebranchtype bt = null;
			out.println("<select name='courseBranchTypeId'>");
			out.println("<option value='-1'>请选择</option>");
			while (i.hasNext()) {
				bt = i.next();
				out.println("<option value='" + bt.getCourseBranchTypeId() + "'>"
						+ PropertyUtil.get("zh", bt.getCourseBranchTypeNameKey()) + "</option>");
			}
			out.println("</select>");
			return null;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
	
	public String changeCourseContent() {
		try {
			log.debug("Start AdminTypeAction.changeCourseContent()");
			courseList = courseDao.getAllCourseByCoursetrunktype(courseTrunkTypeId);
			Iterator<Course> i = courseList.iterator();

			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse) ctx
					.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			Course c = null;
			out.println("<select name='courseIds' size='10' multiple='multiple'>");
			while (i.hasNext()) {
				c = i.next();
				out.println("<option value='" + c.getCourseId() + "'>"
						+ c.getCourseNameCn() + "</option>");
			}
			out.println("</select>");
			return null;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
