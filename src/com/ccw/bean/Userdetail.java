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
 * Userdetail generated by hbm2java
 */
@Entity
@Table(name = "userdetail", catalog = "ccwchina")
public class Userdetail implements java.io.Serializable {

	private String userId;
	private Peopletitle peopletitle;
	private Courselocation courselocation;
	private String password;
	private String email;
	private String nickName;
	private String flag;
	private String avaliable;
	private String cellphone;
	private String isVegetarian;
	private String allergic;
	private String firstName;
	private String lastName;
	private String telephone;
	private String fax;
	private String address;
	private String zipcode;
	private String needNews;
	private String needCoupon;
	private Integer points;
	private Date registerTime;
	private String preferredLanguage;
	private Date birthday;
	private Set<Orderbasic> orderbasics = new HashSet<Orderbasic>(0);
	private Set<Orderitem> orderitems = new HashSet<Orderitem>(0);
	private Set<Photo> photos = new HashSet<Photo>(0);

	public Userdetail() {
	}

	public Userdetail(String userId) {
		this.userId = userId;
	}

	public Userdetail(String userId, Peopletitle peopletitle,
			Courselocation courselocation, String password, String email,
			String nickName, String flag, String avaliable, String cellphone,
			String isVegetarian, String allergic, String firstName,
			String lastName, String telephone, String fax, String address,
			String zipcode, String needNews, String needCoupon, Integer points,
			Date registerTime, String preferredLanguage, Date birthday,
			Set<Orderbasic> orderbasics, Set<Orderitem> orderitems,
			Set<Photo> photos) {
		this.userId = userId;
		this.peopletitle = peopletitle;
		this.courselocation = courselocation;
		this.password = password;
		this.email = email;
		this.nickName = nickName;
		this.flag = flag;
		this.avaliable = avaliable;
		this.cellphone = cellphone;
		this.isVegetarian = isVegetarian;
		this.allergic = allergic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.fax = fax;
		this.address = address;
		this.zipcode = zipcode;
		this.needNews = needNews;
		this.needCoupon = needCoupon;
		this.points = points;
		this.registerTime = registerTime;
		this.preferredLanguage = preferredLanguage;
		this.birthday = birthday;
		this.orderbasics = orderbasics;
		this.orderitems = orderitems;
		this.photos = photos;
	}

	@Id
	@Column(name = "userId", unique = true, nullable = false, length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
	@JoinColumn(name = "preferredCity")
	public Courselocation getCourselocation() {
		return this.courselocation;
	}

	public void setCourselocation(Courselocation courselocation) {
		this.courselocation = courselocation;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nickName", length = 100)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	@Column(name = "cellphone", length = 45)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	@Column(name = "firstName", length = 100)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", length = 100)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zipcode", length = 45)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "needNews", length = 45)
	public String getNeedNews() {
		return this.needNews;
	}

	public void setNeedNews(String needNews) {
		this.needNews = needNews;
	}

	@Column(name = "needCoupon", length = 45)
	public String getNeedCoupon() {
		return this.needCoupon;
	}

	public void setNeedCoupon(String needCoupon) {
		this.needCoupon = needCoupon;
	}

	@Column(name = "points")
	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registerTime", length = 19)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "preferredLanguage", length = 45)
	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userdetail")
	public Set<Orderbasic> getOrderbasics() {
		return this.orderbasics;
	}

	public void setOrderbasics(Set<Orderbasic> orderbasics) {
		this.orderbasics = orderbasics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userdetail")
	public Set<Orderitem> getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userdetail")
	public Set<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

}
