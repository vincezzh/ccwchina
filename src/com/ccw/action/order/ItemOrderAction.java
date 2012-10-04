package com.ccw.action.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccw.bean.Courselocation;
import com.ccw.bean.Orderitem;
import com.ccw.bean.Orderstatus;
import com.ccw.bean.Userdetail;
import com.ccw.common.CommonUtil;
import com.ccw.common.Params;
import com.ccw.dao.ItemDaoInf;
import com.ccw.dao.OrderDaoInf;
import com.ccw.dao.UserDaoInf;
import com.ccw.mail.SendMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ItemOrderAction extends ActionSupport {
	private static final long serialVersionUID = 2956987787593049293L;
	private Log log = LogFactory.getLog(ItemOrderAction.class);
	private String itemId;
	private String email;
	private String contactPerson;
	private String cellphone;
	private Orderitem order;
	private Userdetail user;
	private String oId;
	private UserDaoInf userDao;
	private OrderDaoInf orderDao;
	private ItemDaoInf itemDao;
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public UserDaoInf getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInf userDao) {
		this.userDao = userDao;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Orderitem getOrder() {
		return order;
	}

	public void setOrder(Orderitem order) {
		this.order = order;
	}

	public Userdetail getUser() {
		return user;
	}

	public void setUser(Userdetail user) {
		this.user = user;
	}

	public String getOId() {
		return oId;
	}

	public void setOId(String id) {
		oId = id;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public ItemDaoInf getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDaoInf itemDao) {
		this.itemDao = itemDao;
	}

	public String bookItem() {
		try {
			log.debug("ItemOrderAction.bookItem()");
			
			Map session = ActionContext.getContext().getSession();
			Userdetail tempUser = (Userdetail)session.get("user_session");
			if(tempUser == null) {
				tempUser = new Userdetail();
				String u = email;
				if(u.indexOf("@") != -1)
					u = CommonUtil.StringFilter(u.substring(0, u.indexOf("@")));
				int num = 1;
				String checkUserName = u;
				while(userDao.getDetailUser(checkUserName) != null) {
					checkUserName = u + num;
					num++;
				}
				tempUser.setUserId(checkUserName);
				tempUser.setNickName(checkUserName);
				tempUser.setFirstName("");
				tempUser.setLastName("");
				tempUser.setCellphone(cellphone);
				tempUser.setPassword(Params.AUTOMATIC_REGISTERED_DEFAULT_PASSWORD);
				tempUser.setEmail(email);
				tempUser.setBirthday(new Date());
				Courselocation cl = new Courselocation();
				cl.setCourseLocationId(1);
				tempUser.setCourselocation(cl);
				Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
				if(locale == null) {
					locale = Locale.US;
					session.put("WW_TRANS_I18N_LOCALE", locale);
				}
				tempUser.setPreferredLanguage(locale.getLanguage());
				tempUser.setNeedNews("true");
				tempUser.setNeedCoupon("true");
				tempUser.setAvaliable("yes");
				tempUser.setIsVegetarian("false");
				tempUser.setPoints(0);
				tempUser.setRegisterTime(new Date());
				
				userDao.register(tempUser);
				session.put("user_session", tempUser);
				if(Params.CHINA.equals(tempUser.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.CHINA);
				}else if(Params.JAPAN.equals(tempUser.getPreferredLanguage())) {
					session.put("WW_TRANS_I18N_LOCALE", Locale.JAPAN);
				}else {
					session.put("WW_TRANS_I18N_LOCALE", Locale.US);
				}
				
				String templatePath = "/mail_template/register/register_template_" + tempUser.getPreferredLanguage() + ".txt";
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("#nickname#", tempUser.getNickName());
				content.put("#userId#", tempUser.getUserId());
				content.put("#password#", tempUser.getPassword());
				String[] emailAndContactPerson = new String[]{tempUser.getEmail() + "_with_" + tempUser.getNickName()};
				SendMail spom = new SendMail(Params.REGISTER_SUCCESSFUL, templatePath, content, emailAndContactPerson);
				spom.start();
			}
			user = tempUser;
			
			order = new Orderitem();
			Date now = new Date();
			order.setAvaliable("yes");
			order.setBookingTime(now);
			order.setCellphone(cellphone);
			order.setContactPerson(contactPerson);
			order.setEmail(email);
			order.setUserdetail(user);
			order.setItem(itemDao.getDetailItem(itemId));
			order.setOrderItemId("IT-" + now.getTime());
			Orderstatus status = new Orderstatus();
			status.setOrderStatusId(Params.ORDER_STATUS_PENDING_ID);
			order.setOrderstatus(status);
			
			orderDao.saveItemOrder(order);
			sendMails();
			oId = order.getOrderItemId();
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
		
	private void sendMails() throws Exception {
		String templatePath = "/mail_template/order/booking_item_template_" + user.getPreferredLanguage() + ".txt";
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("#name#", order.getContactPerson());
		content.put("#orderId#", order.getOrderItemId());
		if("ja".equals(user.getPreferredLanguage())) {
			content.put("#itemName#", order.getItem().getItemNameJp());
		}else if("zh".equals(user.getPreferredLanguage())) {
			content.put("#itemName#", order.getItem().getItemNameCn());
		}else {
			content.put("#itemName#", order.getItem().getItemNameEn());
		}
		String[] emailAndContactPerson = new String[]{order.getEmail() + "_with_" + order.getContactPerson()};
		SendMail spom = new SendMail(Params.BOOKING_ITEM_ORDER_MAIL_SUBJECT, templatePath, content, emailAndContactPerson);
		spom.start();
		
		String path = ItemOrderAction.class.getResource("/").getPath();
		String adminEmail = CommonUtil.readProperty("ADMIN_EMAIL", path + "ccw.properties");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		templatePath = "/mail_template/reminder/admin_item_reminder_template_zh.txt";
		content = new HashMap<String, String>();
		content.put("#orderType#", "市场购物");
		content.put("#itemName#", order.getItem().getItemNameCn());
		content.put("#bookingTime#", sdf1.format(order.getBookingTime()));
		content.put("#name#", order.getContactPerson());
		content.put("#cellphone#", order.getCellphone());
		content.put("#email#", order.getEmail());
		emailAndContactPerson = new String[]{adminEmail + "_with_CCW Auto Robot"};
		spom = new SendMail(Params.ADMIN_BOOKING_ITEM_MAIL_SUBJECT, templatePath, content, emailAndContactPerson, true);
		spom.start();
	}

}
