package com.ccw.bean;

// Generated Aug 28, 2012 11:30:32 PM by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Calendarwithcourse generated by hbm2java
 */
@Entity
@Table(name = "calendarwithcourse", catalog = "ccwchina")
public class Calendarwithcourse implements java.io.Serializable {

	private Long id;
	private Course course;
	private Coursecalendar coursecalendar;

	public Calendarwithcourse() {
	}

	public Calendarwithcourse(Course course, Coursecalendar coursecalendar) {
		this.course = course;
		this.coursecalendar = coursecalendar;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseId")
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseCalendarId")
	public Coursecalendar getCoursecalendar() {
		return this.coursecalendar;
	}

	public void setCoursecalendar(Coursecalendar coursecalendar) {
		this.coursecalendar = coursecalendar;
	}

}
