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
 * News generated by hbm2java
 */
@Entity
@Table(name = "news", catalog = "ccwchina")
public class News implements java.io.Serializable {

	private Long newsId;
	private String newsTitle;
	private String newsContent;
	private String author;
	private Date issueDate;
	private String flag;
	private String avaliable;
	private String searchKeyWords;
	private String searchDescription;

	public News() {
	}

	public News(String newsTitle, String newsContent, String author,
			Date issueDate, String flag, String avaliable,
			String searchKeyWords, String searchDescription) {
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.author = author;
		this.issueDate = issueDate;
		this.flag = flag;
		this.avaliable = avaliable;
		this.searchKeyWords = searchKeyWords;
		this.searchDescription = searchDescription;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "newsId", unique = true, nullable = false)
	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	@Column(name = "newsTitle", length = 65535)
	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name = "newsContent", length = 65535)
	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Column(name = "author", length = 45)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "issueDate", length = 10)
	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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

	@Column(name = "searchKeyWords", length = 200)
	public String getSearchKeyWords() {
		return this.searchKeyWords;
	}

	public void setSearchKeyWords(String searchKeyWords) {
		this.searchKeyWords = searchKeyWords;
	}

	@Column(name = "searchDescription", length = 65535)
	public String getSearchDescription() {
		return this.searchDescription;
	}

	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}

}
