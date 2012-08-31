package com.ccw.vm.bean;

import java.util.ArrayList;

import com.ccw.bean.Course;
import com.ccw.bean.Courselocation;

public class CourseVM {
	private String toptitle;
	private String bottomfoot;
	private Course course;
	private ArrayList<Courselocation> locationList;
	
	public String getToptitle() {
		return toptitle;
	}
	public void setToptitle(String toptitle) {
		this.toptitle = toptitle;
	}
	public String getBottomfoot() {
		return bottomfoot;
	}
	public void setBottomfoot(String bottomfoot) {
		this.bottomfoot = bottomfoot;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public ArrayList<Courselocation> getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList<Courselocation> locationList) {
		this.locationList = locationList;
	}
	
}
