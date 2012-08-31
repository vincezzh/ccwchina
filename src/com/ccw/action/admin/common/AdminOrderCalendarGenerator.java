package com.ccw.action.admin.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;

import com.ccw.bean.Orderprivate;
import com.ccw.bean.Orderpublic;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;

public class AdminOrderCalendarGenerator {
	public static String generateAMonthOrderCalendar(ArrayList<Orderpublic> publicOrders, ArrayList<Orderprivate> privateOrders, Integer courseLocationId, Date month, Locale locale) {
		HashMap<Integer, TreeMap<String, ArrayList<Orderpublic>>> monthPublicOrders = new HashMap<Integer, TreeMap<String, ArrayList<Orderpublic>>>();
		Iterator<Orderpublic> iPublicOrders = publicOrders.iterator();
		Orderpublic op1 = null;
		while(iPublicOrders.hasNext()) {
			op1 = iPublicOrders.next();
			
			Calendar classDay = Calendar.getInstance();
			classDay.setTime(op1.getCoursecalendar().getClassDate());
			Integer day = classDay.get(Calendar.DAY_OF_MONTH);
			if(monthPublicOrders.get(day) == null) {
				TreeMap<String, ArrayList<Orderpublic>> tempDayPublicOrders = new TreeMap<String, ArrayList<Orderpublic>>();
				monthPublicOrders.put(day, tempDayPublicOrders);
			}
			TreeMap<String, ArrayList<Orderpublic>> dayPublicOrders = monthPublicOrders.get(day);
			String classTime = op1.getCoursecalendar().getClasstime().getClassTimeContent();
			if(dayPublicOrders.get(classTime) == null) {
				ArrayList<Orderpublic> tempTimePublicOrders = new ArrayList<Orderpublic>();
				dayPublicOrders.put(classTime, tempTimePublicOrders);
			}
			ArrayList<Orderpublic> timePublicOrders = dayPublicOrders.get(classTime);
			timePublicOrders.add(op1);
		}
		
		HashMap<Integer, TreeMap<String, ArrayList<Orderprivate>>> monthPrivateOrders = new HashMap<Integer, TreeMap<String, ArrayList<Orderprivate>>>();
		Iterator<Orderprivate> iPrivateOrders = privateOrders.iterator();
		Orderprivate op2 = null;
		while(iPrivateOrders.hasNext()) {
			op2 = iPrivateOrders.next();
			
			Calendar classDay = Calendar.getInstance();
			classDay.setTime(op2.getCourseDate());
			Integer day = classDay.get(Calendar.DAY_OF_MONTH);
			if(monthPrivateOrders.get(day) == null) {
				TreeMap<String, ArrayList<Orderprivate>> tempDayPrivateOrders = new TreeMap<String, ArrayList<Orderprivate>>();
				monthPrivateOrders.put(day, tempDayPrivateOrders);
			}
			TreeMap<String, ArrayList<Orderprivate>> dayPrivateOrders = monthPrivateOrders.get(day);
			String classTime = op2.getClasstime().getClassTimeContent();
			if(dayPrivateOrders.get(classTime) == null) {
				ArrayList<Orderprivate> tempTimePrivateOrders = new ArrayList<Orderprivate>();
				dayPrivateOrders.put(classTime, tempTimePrivateOrders);
			}
			ArrayList<Orderprivate> timePrivateOrders = dayPrivateOrders.get(classTime);
			timePrivateOrders.add(op2);
		}
		
		return setupCalendar(monthPublicOrders, monthPrivateOrders, courseLocationId, month, locale);
	}
	
	private static String setupCalendar(HashMap<Integer, TreeMap<String, ArrayList<Orderpublic>>> monthPublicOrders, HashMap<Integer, TreeMap<String, ArrayList<Orderprivate>>> monthPrivateOrders, Integer courseLocationId, Date month, Locale locale) {
		StringBuffer sb = new StringBuffer("");
		String days[] = new String[42];
		for (int i = 0; i < 42; i++) {
			days[i] = "";
		}
		
		Calendar thisMonth = Calendar.getInstance();
		thisMonth.setTime(month);
		thisMonth.setFirstDayOfWeek(Calendar.SUNDAY);
		thisMonth.set(Calendar.DAY_OF_MONTH, 1);
		int firstIndex = thisMonth.get(Calendar.DAY_OF_WEEK) - 1;
		int maxIndex = thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < maxIndex; i++) {
			days[firstIndex + i] = String.valueOf(i + 1);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
		
		sb.append("<div class='calendar'>");
		sb.append("<table cellspacing='0' id='calendar-course' class='font_samll_2'>");
		sb.append("<thead>");
		sb.append("<tr class='calendar-title'>");
		thisMonth.add(Calendar.MONTH, -1);
		sb.append("<th colspan='3' align='left' style='border-left:1px solid #fff'>");
		sb.append("<a onclick=\"refreshCalendar(" + courseLocationId + ", '" + sdf1.format(thisMonth.getTime()) + "01')\">" + getShowWord(locale, "PREV_MONTH") + "</a>");
		sb.append("</th>");
		thisMonth.add(Calendar.MONTH, 1);
		sb.append("<th colspan='3'>" + sdf.format(thisMonth.getTime()) + "</th>");
		thisMonth.add(Calendar.MONTH, 1);
		sb.append("<th colspan='3' align='right'>");
		sb.append("<a onclick=\"refreshCalendar(" + courseLocationId + ", '" + sdf1.format(thisMonth.getTime()) + "01')\">" + getShowWord(locale, "NEXT_MONTH") + "</a></th></tr>");
		sb.append("<tr style='background-color:#a4c41b; height:26px;'><th width='8%'></th><th width='12%' class='weekend'>"
				+ getShowWord(locale, "SUNDAY") + "</th><th width='12%'>"
				+ getShowWord(locale, "MONDAY") + "</th><th width='12%'>"
				+ getShowWord(locale, "TUESDAY") + "</th><th width='12%'>"
				+ getShowWord(locale, "WEDNESDAY") + "</th><th width='12%'>"
				+ getShowWord(locale, "THURSDAY") + "</th><th width='12%'>"
				+ getShowWord(locale, "FRIDAY") + "</th><th width='12%' class='weekend'>"
				+ getShowWord(locale, "SATURDAY") + "</th><th width='8%'></th></tr>");
		sb.append("</thead>");
		
		sb.append("<tbody>");
		thisMonth.add(Calendar.MONTH, -1);
		for (int j = 0; j < 6; j++) {
			sb.append("<tr class='bg-gray-title'>");
			sb.append("<td>");
			sb.append("<div>&nbsp;</div>");
//			Iterator<Classtime> allTimeShow = allTimes.iterator();
//			Classtime timeShow = null;
//			while(allTimeShow.hasNext()) {
//				timeShow = allTimeShow.next();
//				sb.append("<div>");
//				sb.append(timeShow.getClassTimeContent());
//				sb.append("</div>");
//			}
			sb.append("</td>");
			
			for (int i = j * 7; i < (j + 1) * 7; i++) {
				sb.append("<td class='title_hover'>");
				if(!"".equals(days[i])) {
					sb.append("<h5>");
					//sb.append(sdf.format(thisMonth.getTime()) + "-" + days[i]);
					sb.append(days[i]);
					sb.append("</h5>");
					
					TreeMap<String, ArrayList<Orderpublic>> dayPublicOrders = monthPublicOrders.get(new Integer(days[i]));
					if(dayPublicOrders != null) {
						Set<String> publicKeys = dayPublicOrders.keySet();
						Iterator<String> iPublicKeys = publicKeys.iterator();
						ArrayList<Orderpublic> publicOrders = null;
						sb.append("<div>公共课订单</div>");
						while(iPublicKeys.hasNext()) {
							String key = iPublicKeys.next();
							publicOrders = dayPublicOrders.get(key);
							Iterator<Orderpublic> iPublicOrders = publicOrders.iterator();
							Orderpublic op = null;
							int totalGuestNumber = 0;
							StringBuffer sbTemp = new StringBuffer();
							sbTemp.append("<div title='课程细节' id='detail-dialog-public-" + days[i] + "-" + key.replace(":", "") + "' class='detail-dialog'>");
							while(iPublicOrders.hasNext()) {
								op = iPublicOrders.next();
								totalGuestNumber = totalGuestNumber + op.getTotalPeopleNumber();
								sbTemp.append("<div>");
								sbTemp.append(getShowWord(locale, op.getCoursecalendar().getCoursetrunktype().getCourseTrunkTypeNameKey()) + ", ");
								sbTemp.append(op.getOrderbasic().getPeopletitle().getPeopleTitleName() + " " + op.getOrderbasic().getContactPerson() + ", ");
								sbTemp.append(op.getOrderbasic().getCellphone() + ", ");
								sbTemp.append(op.getOrderbasic().getEmail() + ", ");
								sbTemp.append(op.getTotalPeopleNumber() + "人, ");
								sbTemp.append(op.getTotalPrice() + "元");
								sbTemp.append("</div>");
							}
							sbTemp.append("</div>");
							sb.append("<div class='hand' onclick='openDetailDialog(\"detail-dialog-public-" + days[i] + "-" + key.replace(":", "") + "\")'>" + key + ": " + totalGuestNumber + "</div>");
							sb.append(sbTemp.toString());
						}
					}
					
					TreeMap<String, ArrayList<Orderprivate>> dayPrivateOrders = monthPrivateOrders.get(new Integer(days[i]));
					if(dayPrivateOrders != null) {
						Set<String> privateKeys = dayPrivateOrders.keySet();
						Iterator<String> iPrivateKeys = privateKeys.iterator();
						ArrayList<Orderprivate> privateOrders = null;
						sb.append("<div>私人课订单</div>");
						while(iPrivateKeys.hasNext()) {
							String key = iPrivateKeys.next();
							privateOrders = dayPrivateOrders.get(key);
							Iterator<Orderprivate> iPrivateOrders = privateOrders.iterator();
							Orderprivate op = null;
							int totalGuestNumber = 0;
							StringBuffer sbTemp = new StringBuffer();
							sbTemp.append("<div title='课程细节' id='detail-dialog-private-" + days[i] + "-" + key.replace(":", "") + "' class='detail-dialog'>");
							while(iPrivateOrders.hasNext()) {
								op = iPrivateOrders.next();
								totalGuestNumber = totalGuestNumber + op.getTotalPeopleNumber();
								sbTemp.append("<div>");
								sbTemp.append(getShowWord(locale, "PRIVATE") + ", ");
								sbTemp.append(op.getOrderbasic().getPeopletitle().getPeopleTitleName() + " " + op.getOrderbasic().getContactPerson() + ", ");
								sbTemp.append(op.getOrderbasic().getCellphone() + ", ");
								sbTemp.append(op.getOrderbasic().getEmail() + ", ");
								sbTemp.append(op.getTotalPeopleNumber() + "人, ");
								sbTemp.append(op.getTotalPrice() + "元");
								sbTemp.append("</div>");
							}
							sbTemp.append("</div>");
							sb.append("<div class='hand' onclick='openDetailDialog(\"detail-dialog-private-" + days[i] + "-" + key.replace(":", "") + "\")'>" + key + ": " + totalGuestNumber + "</div>");
							sb.append(sbTemp.toString());
						}
					}
				}
				sb.append("</td>");
			}
			
			sb.append("<td>");
			sb.append("<div>&nbsp;</div>");
//			allTimeShow = allTimes.iterator();
//			while(allTimeShow.hasNext()) {
//				timeShow = allTimeShow.next();
//				sb.append("<div>");
//				sb.append(timeShow.getClassTimeContent());
//				sb.append("</div>");
//			}
			sb.append("</td>");
			sb.append("</tr></tbody>");
		}
		sb.append("</table></div>");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private static String getShowWord(Locale locale, String key) {
		String content = "";
		if(locale == null) {
			content = PropertyUtil.get(Params.DEFAULT, key);
		}else if(Params.USA.equals(locale.getLanguage())) {
			content = PropertyUtil.get(Params.USA, key);
		}else if(Params.CHINA.equals(locale.getLanguage())) {
			content = PropertyUtil.get(Params.CHINA, key);
		}else if(Params.JAPAN.equals(locale.getLanguage())) {
			content = PropertyUtil.get(Params.JAPAN, key);
		}else {
			content = PropertyUtil.get(Params.DEFAULT, key);
		}
		return content;
	}
}
