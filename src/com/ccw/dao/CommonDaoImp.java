package com.ccw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.bean.Advertisement;
import com.ccw.bean.Infomaillist;
import com.ccw.bean.News;
import com.ccw.bean.Notifications;
import com.ccw.bean.Photo;
import com.ccw.bean.Phototrunktype;
import com.ccw.common.Params;

@Transactional
public class CommonDaoImp implements CommonDaoInf {
	private Log log = LogFactory.getLog(CommonDaoImp.class);
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public Advertisement getToptitleAdvertisement() throws Exception {
		log.debug("CommonDaoImp.getToptitleAdvertisement()");
		
		return getEntityManager().find(Advertisement.class, new Long(1));
	}
	
	public ArrayList<Advertisement> getMiddleTwoAdvertisement() throws Exception {
		log.debug("CommonDaoImp.getMiddleTwoAdvertisement()");
		
		Query query = getEntityManager().createQuery("select a from Advertisement a where a.avaliable='yes' and a.advertisementId>=2 and a.advertisementId<=3");
		ArrayList<Advertisement> list = (ArrayList<Advertisement>)query.getResultList();
		return list;
	}
	
	public ArrayList<Advertisement> getImageSliderAdvertisement() throws Exception {
		log.debug("CommonDaoImp.getImageSliderAdvertisement()");
		
		Query query = getEntityManager().createQuery("select a from Advertisement a where a.avaliable='yes' and a.advertisementId>=4 and a.advertisementId<=7");
		ArrayList<Advertisement> list = (ArrayList<Advertisement>)query.getResultList();
		return list;
	}

	public ArrayList<Advertisement> getAllAdvertisement() throws Exception {
		log.debug("CommonDaoImp.getAllAdvertisement()");
		
		Query query = getEntityManager().createQuery("select a from Advertisement a where a.avaliable='yes' order by a.advertisementId asc");
		ArrayList<Advertisement> list = (ArrayList<Advertisement>)query.getResultList();
		return list;
	}

	public Advertisement getDetailAdvertisement(Long advertisementId)
			throws Exception {
		log.debug("CommonDaoImp.getDetailAdvertisement()");
		
		return getEntityManager().find(Advertisement.class, advertisementId);
	}

	public void updateAdvertisement(Advertisement advertisement)
			throws Exception {
		log.debug("CommonDaoImp.updateAdvertisement()");
		
		getEntityManager().merge(advertisement);
	}

	public void addNews(News news) throws Exception {
		log.debug("CommonDaoImp.addNews()");
		
		getEntityManager().persist(getEntityManager().merge(news));
	}

	public ArrayList<News> getAllNewsByPageNumber(Integer pageNumber)
			throws Exception {
		log.debug("CommonDaoImp.getAllNewsByPageNumber()");
		
		Query query = getEntityManager().createQuery("select n from News n where n.avaliable='yes' order by n.issueDate asc");
		query.setFirstResult((pageNumber - 1) * Params.ADMIN_NEWS_PER_PAGE_NUMBER);
		query.setMaxResults(Params.ADMIN_NEWS_PER_PAGE_NUMBER);
		ArrayList<News> list = (ArrayList<News>)query.getResultList();
		return list;
	}
	
	public ArrayList<News> getAllNews() throws Exception {
		log.debug("CommonDaoImp.getAllNews()");
		
		Query query = getEntityManager().createQuery("select n from News n where n.avaliable='yes'");
		ArrayList<News> list = (ArrayList<News>)query.getResultList();
		return list;
	}

	public Long getAllNewsCount() throws Exception {
		log.debug("CommonDaoImp.getAllNewsCount()");
		
		Query query = getEntityManager().createQuery("select count(n) from News n where n.avaliable='yes'");
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	public ArrayList<News> getNewsByYear(Integer year) throws Exception {
		log.debug("CommonDaoImp.getNewsByYear()");
		
		Query query = getEntityManager().createQuery("select n from News n where n.avaliable='yes' and n.issueDate>='" + year + "-01-01' and n.issueDate<='" + year + "-12-31'");
		ArrayList<News> list = (ArrayList<News>)query.getResultList();
		return list;
	}

	public void deleteNews(Long newsId) throws Exception {
		log.debug("CommonDaoImp.deleteNews()");
		
		News news = getEntityManager().find(News.class, newsId);
		news.setAvaliable("no");
		getEntityManager().merge(news);
	}

	public News getDetailNews(Long newsId) throws Exception {
		log.debug("CommonDaoImp.getDetailNews()");
		
		return getEntityManager().find(News.class, newsId);
	}

	public void updateNews(News news) throws Exception {
		log.debug("CommonDaoImp.updateNews()");
		
		getEntityManager().merge(news);
	}
	
	public void addPhoto(Photo photo) throws Exception {
		log.debug("CommonDaoImp.addPhoto()");
		
		getEntityManager().persist(getEntityManager().merge(photo));
	}

	public ArrayList<Photo> getAllPhotoByPageNumber(Integer pageNumber)
			throws Exception {
		log.debug("CommonDaoImp.getAllPhotoByPageNumber()");
		
		Query query = getEntityManager().createQuery("select p from Photo p where p.avaliable='yes' order by p.updatedTime asc");
		query.setFirstResult((pageNumber - 1) * Params.ADMIN_PHOTO_PER_PAGE_NUMBER);
		query.setMaxResults(Params.ADMIN_PHOTO_PER_PAGE_NUMBER);
		ArrayList<Photo> list = (ArrayList<Photo>)query.getResultList();
		return list;
	}
	
	public ArrayList<Photo> getAllPhoto() throws Exception {
		log.debug("CommonDaoImp.getAllPhoto()");
		
		Query query = getEntityManager().createQuery("select p from Photo p where p.avaliable='yes' order by p.updatedTime asc");
		ArrayList<Photo> list = (ArrayList<Photo>)query.getResultList();
		return list;
	}

	public Long getAllPhotoCount() throws Exception {
		log.debug("CommonDaoImp.getAllPhotoCount()");
		
		Query query = getEntityManager().createQuery("select count(p) from Photo p where p.avaliable='yes'");
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public void deletePhoto(String photoId) throws Exception {
		log.debug("CommonDaoImp.deletePhoto()");
		
		Photo photo = getEntityManager().find(Photo.class, photoId);
		photo.setAvaliable("no");
		getEntityManager().merge(photo);
	}

	public Photo getDetailPhoto(String photoId) throws Exception {
		log.debug("CommonDaoImp.getDetailPhoto()");
		
		return getEntityManager().find(Photo.class, photoId);
	}

	public void updatePhoto(Photo photo) throws Exception {
		log.debug("CommonDaoImp.updatePhoto()");
		
		getEntityManager().merge(photo);
	}

	public News getHighlightNews() throws Exception {
		log.debug("CommonDaoImp.getHighlightNews()");
		
		Query query = getEntityManager().createQuery("select n from News n where n.avaliable='yes' and n.flag like '%highlight%' order by n.issueDate desc");
		ArrayList<News> list = (ArrayList<News>)query.getResultList();
		News news = null;
		if(list.size() > 0) {
			news = list.get(0);
		}
		return news;
	}

	public ArrayList<Phototrunktype> getAllPhototrunktype() throws Exception {
		log.debug("CommonDaoImp.getAllPhototrunktype()");
		
		Query query = getEntityManager().createQuery("select p from Phototrunktype p where p.avaliable='yes'");
		ArrayList<Phototrunktype> list = (ArrayList<Phototrunktype>)query.getResultList();
		return list;
	}

	public LinkedHashMap<String, ArrayList<Photo>> getAllPhotoForShow()
			throws Exception {
		log.debug("CommonDaoImp.getAllPhotoForShow()");
		
		Query query = getEntityManager().createQuery("select p from Phototrunktype p where p.avaliable='yes' order by p.photoTrunkTypeId asc");
		ArrayList<Phototrunktype> trunkList = (ArrayList<Phototrunktype>)query.getResultList();
		Iterator<Phototrunktype> i = trunkList.iterator();
		Phototrunktype trunk = null;
		ArrayList<Photo> value = null;
		LinkedHashMap<String, ArrayList<Photo>> list = new LinkedHashMap<String, ArrayList<Photo>>();
		while(i.hasNext()) {
			trunk = i.next();
			String key = trunk.getPhotoTrunkTypeNameKey();
			value = getPhotoByTrunkType(trunk.getPhotoTrunkTypeId());
			list.put(key, value);
		}
		
		return list;
	}
	
	public ArrayList<Photo> getPhotoByTrunkType(Integer photoTrunkTypeId) throws Exception {
		log.debug("CommonDaoImp.getPhotoByTrunkType()");
		
		Query query = getEntityManager().createQuery("select p from Photo p where p.avaliable='yes' and p.phototrunktype.photoTrunkTypeId=" + photoTrunkTypeId + " order by p.photoId desc");
		ArrayList<Photo> list = (ArrayList<Photo>)query.getResultList();
		return list;
	}

	public void addMail(Infomaillist mail) throws Exception {
		log.debug("CommonDaoImp.addMail()");
		
		getEntityManager().persist(getEntityManager().merge(mail));
	}

	public ArrayList<Notifications> getTodayTomorrowNotifications()
			throws Exception {
		log.debug("CommonDaoImp.getTodayTomorrowNotifications()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, 30);
		Date tomorrow = c.getTime();
		Query query = getEntityManager().createQuery("select n from Notifications n where n.contentTime>='" + sdf.format(today) + " 00:00:00' and n.contentTime<='" + sdf.format(tomorrow) + " 23:59:59'");
		ArrayList<Notifications> list = (ArrayList<Notifications>)query.getResultList();
		return list;
	}

	public ArrayList<Notifications> getNotificationsByRemindTime(Date from, Date to)
			throws Exception {
		log.debug("CommonDaoImp.getNotificationsByRemindTime()");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Query query = getEntityManager().createQuery("select n from Notifications n where n.remindTime>='" + sdf.format(from) + "' and n.remindTime<='" + sdf.format(to) + "'");
		ArrayList<Notifications> list = (ArrayList<Notifications>)query.getResultList();
		return list;
	}
}
