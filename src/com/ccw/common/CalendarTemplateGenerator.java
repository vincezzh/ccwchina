package com.ccw.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.ccw.bean.Classtime;
import com.ccw.bean.Coursecalendar;
import com.ccw.bean.Courselocation;

public class CalendarTemplateGenerator {
	public static String generateAMonthCalendar(Date from, Date to, LinkedHashMap<Courselocation, LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>> allCourses) {
		String prefixPublic = Params.CCW_CONTEXT + "order/mail-public-order.htm?cid=";
		String prefixPrivate = Params.CCW_CONTEXT + "order/mail-private-order.htm?courseDate=";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		StringBuffer sb = new StringBuffer();
		
		Iterator<Courselocation> locations = allCourses.keySet().iterator();
		Courselocation cl = null;
		while(locations.hasNext()) {
			cl = locations.next();
			sb.append("<div><h3>" + cl.getCourseLocationName() + " (" + sdf.format(from) + " to " + sdf.format(to) + ")</h3></div>");
			sb.append("<table style='font-size:12px;' width='600'>");
			sb.append("<tr>");
			sb.append("<th bgcolor='#00CC00'>Date</th>");
			sb.append("<th bgcolor='#00CC00'>10:00 - 12:00</th>");
			sb.append("<th bgcolor='#00CC00'>13:00 - 15:00</th>");
			sb.append("<th bgcolor='#00CC00'>15:00 - 17:00</th>");
			sb.append("<th bgcolor='#00CC00'>19:00 - 21:00</th>");
			sb.append("</tr>");
			
			LinkedHashMap<Date, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>> allDates = allCourses.get(cl);
			Iterator<Date> days = allDates.keySet().iterator();
			Date day = null;
			String lineBackground = "bgcolor='#CCFFCC'";
			int lineNumber = 1;
			while(days.hasNext()) {
				String lineColor = "";
				if(lineNumber%2 == 0)
					lineColor = lineBackground;
				
				day = days.next();
				sb.append("<tr " + lineColor + ">");
				sb.append("<th>" + sdf.format(day) + "</th>");
				
				LinkedHashMap<Classtime, ArrayList<Coursecalendar>> allClasstimes = allDates.get(day);
				Iterator<Classtime> classTimes = allClasstimes.keySet().iterator();
				Classtime classTime = null;
				while(classTimes.hasNext()) {
					classTime = classTimes.next();
					
					ArrayList<Coursecalendar> courses = allClasstimes.get(classTime);
					if(courses.size() > 0) {
						Iterator<Coursecalendar> iCourses = courses.iterator();
						Coursecalendar cc = null;
						sb.append("<td " + lineColor + ">");
						while(iCourses.hasNext()) {
							cc = iCourses.next();
							sb.append("<div style='background-color:" + cc.getCoursetrunktype().getBackgroundColor() + ";'>");
							sb.append("<a style='text-decoration:none;color:" + cc.getCoursetrunktype().getFontColor() + ";' href='" + prefixPublic + cc.getCourseCalendarId() + "' target='_blank'>");
							sb.append(PropertyUtil.get(Params.USA, cc.getCoursebranchtype().getCourseBranchTypeNameKey()));
							sb.append("</a>");
							sb.append("</div>");
						}
						sb.append("</td>");
					}else {
						sb.append("<td " + lineColor + ">");
						sb.append("<div style='background-color:" + Params.AVALIABLE_BACKGROUND_COLOR + "'>");
						sb.append("<a style='text-decoration:none;color:#fff;' href='" + prefixPrivate + sdf1.format(day) + "&classTimeId=" + classTime.getClassTimeId() + "&courseLocationId=" + cl.getCourseLocationId() + "' target='_blank'>");
						sb.append("Avaliable");
						sb.append("</a>");
						sb.append("</div>");
						sb.append("</td>");
					}
				}
				sb.append("</tr>");
				lineNumber++;
			}
			sb.append("</table>");
			sb.append("<p>&nbsp;</p>");
		}
		
		return sb.toString();
	}
}
