package com.vincezzh.meitounao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Notifications;
import com.ccw.dao.CommonDaoInf;

public class MeiTouNaoCenter {
	private Log log = LogFactory.getLog(MeiTouNaoCenter.class);
	private CommonDaoInf commonDao;
	private String certificatePath;
	
	public CommonDaoInf getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDaoInf commonDao) {
		this.commonDao = commonDao;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public void sendNotifications() {
		log.debug("MeiTouNaoCenter.sendNotifications()");
		try {
			sendCurrentNotifications();
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
	
	private void sendCurrentNotifications() throws Exception {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.MINUTE, -10);
		Date past = c.getTime();
		List<Notifications> list = commonDao.getNotificationsByRemindTime(past, now);
		List<String> contents = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		for(Notifications n : list) {
			contents.add(sdf.format(n.getContentTime()) + " " + n.getContent());
		}
		MeiTouNaoTest.send(contents, certificatePath);
	}
}
