package com.ccw.mail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;

import com.ccw.common.MailUtil;
import com.ccw.common.TemplateUtil;

public class SendMail extends Thread {
	private Log log = LogFactory.getLog(SendMail.class);
	private String[] emailAndContactPerson;
	private String mailTemplate;
	private String subject;
	private EmailAttachment[] attachments;
	private boolean addBCC;

	public SendMail(String subject, String templatePath, HashMap<String, String> content, String[] emailAndContactPerson, boolean addBCC) throws Exception {
		this(subject, templatePath, content, emailAndContactPerson);
		this.addBCC = addBCC;
	}
	
	public SendMail(String subject, String templatePath, HashMap<String, String> content, String[] emailAndContactPerson) throws Exception {
		this.subject = subject;
		attachments = null;
		this.emailAndContactPerson = emailAndContactPerson;
		
		mailTemplate = TemplateUtil.readTemplate(getClass().getResourceAsStream(templatePath));
		if(content != null) {
			Set<String> keys = content.keySet();
			Iterator<String> i = keys.iterator();
			while(i.hasNext()) {
				String key = i.next();
				String value = content.get(key);
				mailTemplate = mailTemplate.replace(key, value);
			}
		}
	}
	
	public SendMail(String subject, String mailTemplate, String[] emailAndContactPerson) {
		this.subject = subject;
		this.mailTemplate = mailTemplate;
		this.emailAndContactPerson = emailAndContactPerson;
		this.attachments = null;
	}
	
	public void run() {
		try {
			log.debug("SendMail.run() START");
			System.out.println("SendMail.run() START");
			HashMap<String, String> subjectAndContent = new HashMap<String, String>();
			subjectAndContent.put("subject", subject);
			subjectAndContent.put("content", mailTemplate);
			MailUtil.send(subjectAndContent, emailAndContactPerson,	attachments, addBCC);
			System.out.println("SendMail.run() END");
			log.debug("SendMail.run() END");
		} catch (Exception e) {
			System.out.println("SendMail.run() FAILED");
			log.error(e);
			log.error(e.getMessage());
		}
	}
}
