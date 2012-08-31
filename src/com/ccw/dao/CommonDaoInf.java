package com.ccw.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import com.ccw.bean.Advertisement;
import com.ccw.bean.Infomaillist;
import com.ccw.bean.News;
import com.ccw.bean.Notifications;
import com.ccw.bean.Photo;
import com.ccw.bean.Phototrunktype;


public interface CommonDaoInf {
	public Advertisement getToptitleAdvertisement() throws Exception;
	
	public ArrayList<Advertisement> getMiddleTwoAdvertisement() throws Exception;
	
	public ArrayList<Advertisement> getImageSliderAdvertisement() throws Exception;
	
	public ArrayList<Advertisement> getAllAdvertisement() throws Exception;
	
	public Advertisement getDetailAdvertisement(Long advertisementId) throws Exception;
	
	public void updateAdvertisement(Advertisement advertisement) throws Exception;
	
	public void addNews(News news) throws Exception;
	
	public ArrayList<News> getAllNewsByPageNumber(Integer pageNumber) throws Exception;
	
	public ArrayList<News> getAllNews() throws Exception;
	
	public ArrayList<News> getNewsByYear(Integer year) throws Exception;
	
	public Long getAllNewsCount() throws Exception;
	
	public void deleteNews(Long newsId) throws Exception;
	
	public News getDetailNews(Long newsId) throws Exception;
	
	public void updateNews(News news) throws Exception;
	
	public void addPhoto(Photo photo) throws Exception;
	
	public ArrayList<Photo> getAllPhotoByPageNumber(Integer pageNumber) throws Exception;
	
	public ArrayList<Photo> getAllPhoto() throws Exception;
	
	public Long getAllPhotoCount() throws Exception;
	
	public void deletePhoto(String photoId) throws Exception;
	
	public Photo getDetailPhoto(String photoId) throws Exception;
	
	public void updatePhoto(Photo photo) throws Exception;
	
	public News getHighlightNews() throws Exception;
	
	public ArrayList<Phototrunktype> getAllPhototrunktype() throws Exception;
	
	public LinkedHashMap<String, ArrayList<Photo>> getAllPhotoForShow() throws Exception;
	
	public ArrayList<Photo> getPhotoByTrunkType(Integer photoTrunkTypeId) throws Exception;
	
	public void addMail(Infomaillist mail) throws Exception;
	
	//For MeiTouNao
	public ArrayList<Notifications> getTodayTomorrowNotifications() throws Exception;
	
	public ArrayList<Notifications> getNotificationsByRemindTime(Date from, Date to) throws Exception;
}
