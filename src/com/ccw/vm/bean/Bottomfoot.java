package com.ccw.vm.bean;

import java.util.ArrayList;
import java.util.HashMap;

import com.ccw.bean.Coursebranchtype;
import com.ccw.bean.Coursetrunktype;

public class Bottomfoot {
	private String courseContext;
	private HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> courseList;

	public HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> getCourseList() {
		return courseList;
	}

	public void setCourseList(HashMap<Coursetrunktype, ArrayList<Coursebranchtype>> courseList) {
		this.courseList = courseList;
	}

	public String getCourseContext() {
		return courseContext;
	}

	public void setCourseContext(String courseContext) {
		this.courseContext = courseContext;
	}
	
}
