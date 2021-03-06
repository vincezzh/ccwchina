package com.ccw.bean;

// Generated Aug 28, 2012 11:30:32 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Coursetrunktype generated by hbm2java
 */
@Entity
@Table(name = "coursetrunktype", catalog = "ccwchina", uniqueConstraints = @UniqueConstraint(columnNames = "courseTrunkTypePath"))
public class Coursetrunktype implements java.io.Serializable {

	private Integer courseTrunkTypeId;
	private String courseTrunkTypeNameKey;
	private String courseTrunkTypePath;
	private String flag;
	private String avaliable;
	private String courseTrunkTypeDescriptionKey;
	private String fontColor;
	private String backgroundColor;
	private Set<Coursepackage> coursepackages = new HashSet<Coursepackage>(0);
	private Set<Course> courses = new HashSet<Course>(0);
	private Set<Coursecalendar> coursecalendars = new HashSet<Coursecalendar>(0);
	private Set<Coursebranchtype> coursebranchtypes = new HashSet<Coursebranchtype>(
			0);

	public Coursetrunktype() {
	}

	public Coursetrunktype(String courseTrunkTypeNameKey,
			String courseTrunkTypePath, String flag, String avaliable,
			String courseTrunkTypeDescriptionKey, String fontColor,
			String backgroundColor, Set<Coursepackage> coursepackages,
			Set<Course> courses, Set<Coursecalendar> coursecalendars,
			Set<Coursebranchtype> coursebranchtypes) {
		this.courseTrunkTypeNameKey = courseTrunkTypeNameKey;
		this.courseTrunkTypePath = courseTrunkTypePath;
		this.flag = flag;
		this.avaliable = avaliable;
		this.courseTrunkTypeDescriptionKey = courseTrunkTypeDescriptionKey;
		this.fontColor = fontColor;
		this.backgroundColor = backgroundColor;
		this.coursepackages = coursepackages;
		this.courses = courses;
		this.coursecalendars = coursecalendars;
		this.coursebranchtypes = coursebranchtypes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseTrunkTypeId", unique = true, nullable = false)
	public Integer getCourseTrunkTypeId() {
		return this.courseTrunkTypeId;
	}

	public void setCourseTrunkTypeId(Integer courseTrunkTypeId) {
		this.courseTrunkTypeId = courseTrunkTypeId;
	}

	@Column(name = "courseTrunkTypeNameKey", length = 100)
	public String getCourseTrunkTypeNameKey() {
		return this.courseTrunkTypeNameKey;
	}

	public void setCourseTrunkTypeNameKey(String courseTrunkTypeNameKey) {
		this.courseTrunkTypeNameKey = courseTrunkTypeNameKey;
	}

	@Column(name = "courseTrunkTypePath", unique = true, length = 45)
	public String getCourseTrunkTypePath() {
		return this.courseTrunkTypePath;
	}

	public void setCourseTrunkTypePath(String courseTrunkTypePath) {
		this.courseTrunkTypePath = courseTrunkTypePath;
	}

	@Column(name = "flag", length = 45)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "avaliable", length = 45)
	public String getAvaliable() {
		return this.avaliable;
	}

	public void setAvaliable(String avaliable) {
		this.avaliable = avaliable;
	}

	@Column(name = "courseTrunkTypeDescriptionKey", length = 100)
	public String getCourseTrunkTypeDescriptionKey() {
		return this.courseTrunkTypeDescriptionKey;
	}

	public void setCourseTrunkTypeDescriptionKey(
			String courseTrunkTypeDescriptionKey) {
		this.courseTrunkTypeDescriptionKey = courseTrunkTypeDescriptionKey;
	}

	@Column(name = "fontColor", length = 45)
	public String getFontColor() {
		return this.fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	@Column(name = "backgroundColor", length = 45)
	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coursetrunktype")
	public Set<Coursepackage> getCoursepackages() {
		return this.coursepackages;
	}

	public void setCoursepackages(Set<Coursepackage> coursepackages) {
		this.coursepackages = coursepackages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coursetrunktype")
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coursetrunktype")
	public Set<Coursecalendar> getCoursecalendars() {
		return this.coursecalendars;
	}

	public void setCoursecalendars(Set<Coursecalendar> coursecalendars) {
		this.coursecalendars = coursecalendars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "coursetrunktype")
	public Set<Coursebranchtype> getCoursebranchtypes() {
		return this.coursebranchtypes;
	}

	public void setCoursebranchtypes(Set<Coursebranchtype> coursebranchtypes) {
		this.coursebranchtypes = coursebranchtypes;
	}

}
