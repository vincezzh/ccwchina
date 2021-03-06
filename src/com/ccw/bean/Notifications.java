package com.ccw.bean;

// Generated Aug 28, 2012 11:30:32 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notifications generated by hbm2java
 */
@Entity
@Table(name = "notifications", catalog = "ccwchina")
public class Notifications implements java.io.Serializable {

	private Integer id;
	private String content;
	private Date remindTime;
	private Date contentTime;

	public Notifications() {
	}

	public Notifications(String content, Date remindTime, Date contentTime) {
		this.content = content;
		this.remindTime = remindTime;
		this.contentTime = contentTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "remindTime", length = 19)
	public Date getRemindTime() {
		return this.remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contentTime", length = 19)
	public Date getContentTime() {
		return this.contentTime;
	}

	public void setContentTime(Date contentTime) {
		this.contentTime = contentTime;
	}

}
