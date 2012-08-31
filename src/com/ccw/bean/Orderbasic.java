package com.ccw.bean;

// Generated Aug 28, 2012 11:30:32 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orderbasic generated by hbm2java
 */
@Entity
@Table(name = "orderbasic", catalog = "ccwchina")
public class Orderbasic implements java.io.Serializable {

	private String orderBasicId;
	private Confirmtime confirmtime;
	private Peopletitle peopletitle;
	private Orderstatus orderstatus;
	private Userdetail userdetail;
	private String contactPerson;
	private String cellphone;
	private String telephone;
	private String fax;
	private String email;
	private String zipCode;
	private Date bookingTime;
	private String flag;
	private String avaliable;
	private String isVegetarian;
	private String allergic;
	private String address;
	private Date checkingTime;
	private Integer points;
	private String description;
	private Set<Orderpublic> orderpublics = new HashSet<Orderpublic>(0);
	private Set<Orderprivate> orderprivates = new HashSet<Orderprivate>(0);

	public Orderbasic() {
	}

	public Orderbasic(String orderBasicId) {
		this.orderBasicId = orderBasicId;
	}

	public Orderbasic(String orderBasicId, Confirmtime confirmtime,
			Peopletitle peopletitle, Orderstatus orderstatus,
			Userdetail userdetail, String contactPerson, String cellphone,
			String telephone, String fax, String email, String zipCode,
			Date bookingTime, String flag, String avaliable,
			String isVegetarian, String allergic, String address,
			Date checkingTime, Integer points, String description,
			Set<Orderpublic> orderpublics, Set<Orderprivate> orderprivates) {
		this.orderBasicId = orderBasicId;
		this.confirmtime = confirmtime;
		this.peopletitle = peopletitle;
		this.orderstatus = orderstatus;
		this.userdetail = userdetail;
		this.contactPerson = contactPerson;
		this.cellphone = cellphone;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.zipCode = zipCode;
		this.bookingTime = bookingTime;
		this.flag = flag;
		this.avaliable = avaliable;
		this.isVegetarian = isVegetarian;
		this.allergic = allergic;
		this.address = address;
		this.checkingTime = checkingTime;
		this.points = points;
		this.description = description;
		this.orderpublics = orderpublics;
		this.orderprivates = orderprivates;
	}

	@Id
	@Column(name = "orderBasicId", unique = true, nullable = false, length = 45)
	public String getOrderBasicId() {
		return this.orderBasicId;
	}

	public void setOrderBasicId(String orderBasicId) {
		this.orderBasicId = orderBasicId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "confirmTimeId")
	public Confirmtime getConfirmtime() {
		return this.confirmtime;
	}

	public void setConfirmtime(Confirmtime confirmtime) {
		this.confirmtime = confirmtime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peopleTitleId")
	public Peopletitle getPeopletitle() {
		return this.peopletitle;
	}

	public void setPeopletitle(Peopletitle peopletitle) {
		this.peopletitle = peopletitle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderStausId")
	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Userdetail getUserdetail() {
		return this.userdetail;
	}

	public void setUserdetail(Userdetail userdetail) {
		this.userdetail = userdetail;
	}

	@Column(name = "contactPerson", length = 100)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "cellphone", length = 45)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "telephone", length = 45)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "fax", length = 45)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "zipCode", length = 45)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bookingTime", length = 19)
	public Date getBookingTime() {
		return this.bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
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

	@Column(name = "isVegetarian", length = 45)
	public String getIsVegetarian() {
		return this.isVegetarian;
	}

	public void setIsVegetarian(String isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	@Column(name = "allergic", length = 65535)
	public String getAllergic() {
		return this.allergic;
	}

	public void setAllergic(String allergic) {
		this.allergic = allergic;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "checkingTime", length = 19)
	public Date getCheckingTime() {
		return this.checkingTime;
	}

	public void setCheckingTime(Date checkingTime) {
		this.checkingTime = checkingTime;
	}

	@Column(name = "points")
	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderbasic")
	public Set<Orderpublic> getOrderpublics() {
		return this.orderpublics;
	}

	public void setOrderpublics(Set<Orderpublic> orderpublics) {
		this.orderpublics = orderpublics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderbasic")
	public Set<Orderprivate> getOrderprivates() {
		return this.orderprivates;
	}

	public void setOrderprivates(Set<Orderprivate> orderprivates) {
		this.orderprivates = orderprivates;
	}

}
