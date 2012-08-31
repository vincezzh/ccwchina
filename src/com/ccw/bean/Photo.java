package com.ccw.bean;

// Generated Aug 28, 2012 11:30:32 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Photo generated by hbm2java
 */
@Entity
@Table(name = "photo", catalog = "ccwchina")
public class Photo implements java.io.Serializable {

	private String photoId;
	private Phototrunktype phototrunktype;
	private Userdetail userdetail;
	private String photoIndexPath;
	private String photoPath;
	private String photoTitle;
	private String photoDesc;
	private String flag;
	private String avaliable;
	private Date updatedTime;

	public Photo() {
	}

	public Photo(String photoId) {
		this.photoId = photoId;
	}

	public Photo(String photoId, Phototrunktype phototrunktype,
			Userdetail userdetail, String photoIndexPath, String photoPath,
			String photoTitle, String photoDesc, String flag, String avaliable,
			Date updatedTime) {
		this.photoId = photoId;
		this.phototrunktype = phototrunktype;
		this.userdetail = userdetail;
		this.photoIndexPath = photoIndexPath;
		this.photoPath = photoPath;
		this.photoTitle = photoTitle;
		this.photoDesc = photoDesc;
		this.flag = flag;
		this.avaliable = avaliable;
		this.updatedTime = updatedTime;
	}

	@Id
	@Column(name = "photoId", unique = true, nullable = false, length = 45)
	public String getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photoTrunkTypeId")
	public Phototrunktype getPhototrunktype() {
		return this.phototrunktype;
	}

	public void setPhototrunktype(Phototrunktype phototrunktype) {
		this.phototrunktype = phototrunktype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Userdetail getUserdetail() {
		return this.userdetail;
	}

	public void setUserdetail(Userdetail userdetail) {
		this.userdetail = userdetail;
	}

	@Column(name = "photoIndexPath", length = 65535)
	public String getPhotoIndexPath() {
		return this.photoIndexPath;
	}

	public void setPhotoIndexPath(String photoIndexPath) {
		this.photoIndexPath = photoIndexPath;
	}

	@Column(name = "photoPath", length = 65535)
	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(name = "photoTitle", length = 100)
	public String getPhotoTitle() {
		return this.photoTitle;
	}

	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}

	@Column(name = "photoDesc", length = 65535)
	public String getPhotoDesc() {
		return this.photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedTime", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
