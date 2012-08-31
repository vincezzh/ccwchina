package com.ccw.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.bean.Admindetail;
import com.ccw.bean.Confirmtime;
import com.ccw.bean.Infomaillist;
import com.ccw.bean.Peopletitle;
import com.ccw.bean.Userdetail;
import com.ccw.common.Params;

@Transactional
public class UserDaoImp implements UserDaoInf {
	private Log log = LogFactory.getLog(UserDaoImp.class);
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public Admindetail adminLogin(Admindetail admin) throws Exception {
		log.debug("UserDaoImp.login()");
		
		Admindetail loginAdmin = null;
		Query query = getEntityManager().createQuery("select a from Admindetail a where a.avaliable='yes' and a.adminId='" + admin.getAdminId() + "' and a.password='" + admin.getPassword() + "'");
		List<Admindetail> ls = query.getResultList();
		if(ls.size() == 1) {
			loginAdmin = ls.get(0);
		}
		return loginAdmin;
	}

	public Userdetail getDetailUser(String userId) throws Exception {
		log.debug("UserDaoImp.getDetailUser()");
		
		return getEntityManager().find(Userdetail.class, userId);
	}

	public ArrayList<Peopletitle> getAllPeopletitle() throws Exception {
		log.debug("UserDaoImp.getAllPeopletitle()");
		
		Query query = getEntityManager().createQuery("select p from Peopletitle p where p.avaliable='yes'");
		ArrayList<Peopletitle> list = (ArrayList<Peopletitle>)query.getResultList();
		return list;
	}

	public ArrayList<Confirmtime> getAllConfirmTime() throws Exception {
		log.debug("UserDaoImp.getAllConfirmTime()");
		
		Query query = getEntityManager().createQuery("select c from Confirmtime c where c.avaliable='yes'");
		ArrayList<Confirmtime> list = (ArrayList<Confirmtime>)query.getResultList();
		return list;
	}

	public Userdetail login(String username, String password) throws Exception {
		log.debug("UserDaoImp.login()");
		
		Userdetail user = null;
		Query query = getEntityManager().createQuery("select u from Userdetail u where u.avaliable='yes' and u.userId='" + username + "' and u.password='" + password + "'");
		List<Userdetail> ls = query.getResultList();
		if(ls.size() == 1) {
			user = ls.get(0);
		}
		return user;
	}

	public void register(Userdetail user) throws Exception {
		log.debug("UserDaoImp.register()");
		
		getEntityManager().persist(getEntityManager().merge(user));
	}

	public void updateUserInformation(Userdetail user) throws Exception {
		log.debug("UserDaoImp.updateUserInformation()");
		
		getEntityManager().merge(user);
	}

	public ArrayList<Userdetail> getAllUserByPageNumber(Integer pageNumber)
			throws Exception {
		log.debug("UserDaoImp.getAllUserByPageNumber()");
		
		Query query = getEntityManager().createQuery("select u from Userdetail u where u.avaliable='yes' order by u.userId asc");
		query.setFirstResult((pageNumber - 1) * Params.ADMIN_USER_PER_PAGE_NUMBER);
		query.setMaxResults(Params.ADMIN_USER_PER_PAGE_NUMBER);
		ArrayList<Userdetail> list = (ArrayList<Userdetail>)query.getResultList();
		return list;
	}

	public Long getAllUserCount() throws Exception {
		log.debug("UserDaoImp.getAllUserCount()");
		
		Query query = getEntityManager().createQuery("select count(u) from Userdetail u where u.avaliable='yes'");
		Long count = (Long)query.getSingleResult();
		return count;
	}
	
	public Long getAllNamedUserCount(String nameFormat) throws Exception {
		log.debug("UserDaoImp.getAllNamedUserCount()");
		
		Query query = getEntityManager().createQuery("select count(u) from Userdetail u where u.userId like '%" + nameFormat + "%'");
		Long count = (Long)query.getSingleResult();
		return count;
	}

	public Peopletitle getDetailPeopletitle(Integer peopleTitleId)
			throws Exception {
		log.debug("UserDaoImp.getDetailPeopletitle()");
		
		return getEntityManager().find(Peopletitle.class, peopleTitleId);
	}

	public ArrayList<Infomaillist> getAllInfomaillist() throws Exception {
		log.debug("UserDaoImp.getAllInfomaillist()");
		
		Query query = getEntityManager().createQuery("select i from Infomaillist i where i.avaliable='yes' order by i.mailId asc");
		ArrayList<Infomaillist> list = (ArrayList<Infomaillist>)query.getResultList();
		return list;
	}

	public ArrayList<Userdetail> getAllUser() throws Exception {
		log.debug("UserDaoImp.getAllUser()");
		
		Query query = getEntityManager().createQuery("select u from Userdetail u where u.avaliable='yes' order by u.userId asc");
		ArrayList<Userdetail> list = (ArrayList<Userdetail>)query.getResultList();
		return list;
	}
}
