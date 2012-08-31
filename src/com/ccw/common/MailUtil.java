package com.ccw.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

public class MailUtil {
	public static void main(String[] args) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("host", "smtp.gmail.com");
		params.put("username", "booking@chinesecookingworkshop.com");
		params.put("password", "jinqiao1418");
		params.put("from", "booking@chinesecookingworkshop.com");
		params.put("showname", "Info Admin");
		params.put("subject", "Apache Mail Testing with attachment!");
//		File f = new File("C:\\Users\\vzhang\\Desktop\\sheet001.html");
//		InputStream in = new FileInputStream(f);
//		String mailTemplate = TemplateUtil.readTemplate(in);
//		System.out.println(mailTemplate);
		params.put("content", "Test");
		
		String[] to = new String[1];
		to[0] = "vincezzh@gmail.com_with_Vince Zhang";
//		to[1] = "vzhang@infotrustgroup.com_with_Zhang Zhe Han";
//		to[2] = "infoallinone@yahoo.cn_with_Info Test";
		
//		EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath("/Users/vince/Desktop/1.jpg");
//        attachment.setDisposition(EmailAttachment.ATTACHMENT);
//        attachment.setDescription("Apache Mail JPG");
//        attachment.setName("1.jpg"); 
//        EmailAttachment attachment1 = new EmailAttachment();
//        attachment1.setPath("/Users/vince/Desktop/1.jpg");
//        attachment1.setDisposition(EmailAttachment.ATTACHMENT);
//        attachment1.setDescription("Apache Mail JPG");
//        attachment1.setName("1.jpg"); 
//        EmailAttachment[] attachments = new EmailAttachment[2];
//        attachments[0] = attachment;
//        attachments[1] = attachment1;
        
		MailUtil.send(params, to, null);
		System.out.println("Test done!");
	}

	public static void send(HashMap<String, String> subjectAndContent, String[] to, EmailAttachment[] attachments) throws Exception {
		String path = MailUtil.class.getResource("/").getPath();
		String CCW_MAIL_SENDER = CommonUtil.readProperty("CCW_MAIL_SENDER", path + "ccw.properties");
		String CCW_MAIL_PASSWORD = CommonUtil.readProperty("CCW_MAIL_PASSWORD", path + "ccw.properties");
		String CCW_MAIL_TITLE = CommonUtil.readProperty("CCW_MAIL_TITLE", path + "ccw.properties");
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("host", "smtp.gmail.com");
		params.put("username", CCW_MAIL_SENDER);
		params.put("password", CCW_MAIL_PASSWORD);
		params.put("from", CCW_MAIL_SENDER);
		params.put("showname", CCW_MAIL_TITLE);
		
		HtmlEmail email = new HtmlEmail();

		// email.setTLS(true); //是否TLS校验，某些邮箱需要TLS安全校验，同理有SSL校验
		email.setSSL(true);
		email.setHostName(params.get("host"));
		email.setAuthentication(params.get("username"), params.get("password"));
		email.setSslSmtpPort("465");
		email.setFrom(params.get("from"), params.get("showname"));
		email.setCharset("utf8");
		for(String aTo : to) {
			String[] toAndName = aTo.split("_with_");
			email.addTo(toAndName[0], toAndName[1]); // 接收方
		}
		//email.addCc("vincezzh@chinesecookingworkshop.com"); //抄送方
		email.addBcc("vincezzh@chinesecookingworkshop.com"); //秘密抄送方
		email.setSubject(subjectAndContent.get("subject")); // 标题
		//email.setTextMsg("测试成功1, Testing Successful!"); 
        email.setHtmlMsg(subjectAndContent.get("content"));
        if(attachments != null) {
	        for(EmailAttachment attachment : attachments) {
	        	email.attach(attachment);
	        }
        }
		email.send();
	}
}
