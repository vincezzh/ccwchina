package com.ccw.action.mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ccw.bean.Orderpublic;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;
import com.ccw.dao.OrderDaoInf;
import com.opensymphony.xwork2.ActionSupport;

public class UserOrdersAction extends ActionSupport {
	private static final long serialVersionUID = -3169125370754634661L;
	private Log log = LogFactory.getLog(UserOrdersAction.class);
	private String username;
	private ArrayList<Orderpublic> publicOrderList;
	private OrderDaoInf orderDao;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Orderpublic> getPublicOrderList() {
		return publicOrderList;
	}

	public void setPublicOrderList(ArrayList<Orderpublic> publicOrderList) {
		this.publicOrderList = publicOrderList;
	}

	public OrderDaoInf getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDaoInf orderDao) {
		this.orderDao = orderDao;
	}

	public String getPublicOrder() {
		log.debug("MobileUserOrdersAction.getPublicOrder()");
		Document document = DocumentHelper.createDocument();  
        Element publicOrders = document.addElement("publicOrders");
		try {
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			publicOrderList = orderDao.getAllOrderpublicByUser(username);
			
			if(publicOrderList.size() > 0) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				for(Orderpublic anOrder : publicOrderList) {
					Element order = publicOrders.addElement("order");
					
					Element orderPublicId = order.addElement("orderPublicId");
					orderPublicId.addText(anOrder.getOrderPublicId());
					
					Element classDate = order.addElement("classDate");
					classDate.addText(sdf1.format(anOrder.getCoursecalendar().getClassDate()));
					
					Element classTime = order.addElement("classTime");
					Element classTimeId = classTime.addElement("id");
					classTimeId.addText(anOrder.getCoursecalendar().getClasstime().getClassTimeId().toString());
					Element classTimeName = classTime.addElement("name");
					classTimeName.addText(anOrder.getCoursecalendar().getClasstime().getClassTimeContent());
					
					Element totalPeopleNumber = order.addElement("totalPeopleNumber");
					totalPeopleNumber.addText(anOrder.getTotalPeopleNumber().toString());
					
					Element courseLocation = order.addElement("courseLocation");
					Element courseLocationId = courseLocation.addElement("id");
					courseLocationId.addText(anOrder.getCoursecalendar().getCourselocation().getCourseLocationId().toString());
					Element courseLocationName = courseLocation.addElement("name");
					courseLocationName.addText(anOrder.getCoursecalendar().getCourselocation().getCourseLocationName());
					
					Element courseTrunkType = order.addElement("courseTrunkType");
					Element courseTrunkTypeId = courseTrunkType.addElement("id");
					courseTrunkTypeId.addText(anOrder.getCoursecalendar().getCoursetrunktype().getCourseTrunkTypeId().toString());
					Element courseTrunkTypeName = courseTrunkType.addElement("name");
					courseTrunkTypeName.addText(PropertyUtil.get(Params.USA, anOrder.getCoursecalendar().getCoursetrunktype().getCourseTrunkTypeNameKey()));
					Element fontColor = courseTrunkType.addElement("fontColor");
					fontColor.addText(anOrder.getCoursecalendar().getCoursetrunktype().getFontColor());
					Element backgroundColor = courseTrunkType.addElement("backgroundColor");
					backgroundColor.addText(anOrder.getCoursecalendar().getCoursetrunktype().getBackgroundColor());
					Element courseBranchType = courseTrunkType.addElement("courseBranchType");
					Element courseBranchTypeId = courseBranchType.addElement("id");
					courseBranchTypeId.addText(anOrder.getCoursecalendar().getCoursebranchtype().getCourseBranchTypeId().toString());
					Element courseBranchTypeName = courseBranchType.addElement("name");
					courseBranchTypeName.addText(PropertyUtil.get(Params.USA, anOrder.getCoursecalendar().getCoursebranchtype().getCourseBranchTypeNameKey()));
				}
			}else {
				Element message = publicOrders.addElement("message");
				message.addText(MobileConst.NO_PUBLIC_ORDERS);
			}
		}catch(Exception e) {
			Element errorMsg = publicOrders.addElement("errorMsg");
			errorMsg.addText(MobileConst.ERROR_MSG);
			
			log.error(e);
			log.error(e.getMessage());
		}
		
		sendBackXMLToMobile(document);
		return null;
	}
	
	private void sendBackXMLToMobile(Document document) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();      
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.write(document.asXML()); 
	        out.flush();
	        out.close();
		} catch (IOException e) {
			log.error(e);
			log.error(e.getMessage());
		}
	}
}
