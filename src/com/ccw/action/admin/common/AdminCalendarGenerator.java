package com.ccw.action.admin.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

import com.ccw.bean.Classtime;
import com.ccw.bean.Coursecalendar;
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;

public class AdminCalendarGenerator {
	public static String generateAMonthCalendar(ArrayList<Coursecalendar> courses, ArrayList<Classtime> allTimes, Integer courseLocationId, Date month, Locale locale) {
		Calendar currentMonth = Calendar.getInstance();
		currentMonth.setTime(month);
		int daysNumber = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		HashMap<Integer, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>> monthDetail = new HashMap<Integer, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>>();
		for(int i=1; i<=daysNumber; i++) {
			Iterator<Coursecalendar> iCourses = courses.iterator();
			Coursecalendar coursecalendar = null;
			LinkedHashMap<Classtime, ArrayList<Coursecalendar>> dayDetail = new LinkedHashMap<Classtime, ArrayList<Coursecalendar>>();
			while(iCourses.hasNext()) {
				coursecalendar = iCourses.next();
				Calendar day = Calendar.getInstance();
				day.setTime(coursecalendar.getClassDate());
				if(i == day.get(Calendar.DAY_OF_MONTH)) {
					Iterator<Classtime> iAllTimes = allTimes.iterator();
					Classtime classtime = null;
					while(iAllTimes.hasNext()) {
						classtime = iAllTimes.next();
						if(classtime.getClassTimeId() == coursecalendar.getClasstime().getClassTimeId()) {
							ArrayList<Coursecalendar> tempCC = dayDetail.get(classtime);
							if(tempCC == null)
								tempCC = new ArrayList<Coursecalendar>();
							tempCC.add(coursecalendar);
							dayDetail.put(classtime, tempCC);
						}
					}
				}
			}
			monthDetail.put(i, dayDetail);
		}
		
		return setupCalendar(monthDetail, allTimes, courseLocationId, month, locale);
	}
	
	private static String setupCalendar(HashMap<Integer, LinkedHashMap<Classtime, ArrayList<Coursecalendar>>> monthDetail, ArrayList<Classtime> allTimes, Integer courseLocationId, Date month, Locale locale) {
		String avaliableContent = getShowWord(locale, "AVALIABLE");
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
		sb.append("<a class='hand' onclick=\"refreshCalendar(" + courseLocationId + ", '" + sdf1.format(thisMonth.getTime()) + "01')\">" + getShowWord(locale, "PREV_MONTH") + "</a>");
		sb.append("</th>");
		thisMonth.add(Calendar.MONTH, 1);
		sb.append("<th colspan='3'>" + sdf.format(thisMonth.getTime()) + "</th>");
		thisMonth.add(Calendar.MONTH, 1);
		sb.append("<th colspan='3' align='right'>");
		sb.append("<a class='hand' onclick=\"refreshCalendar(" + courseLocationId + ", '" + sdf1.format(thisMonth.getTime()) + "01')\">" + getShowWord(locale, "NEXT_MONTH") + "</a></th></tr>");
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
			Iterator<Classtime> allTimeShow = allTimes.iterator();
			Classtime timeShow = null;
			while(allTimeShow.hasNext()) {
				timeShow = allTimeShow.next();
				sb.append("<div>");
				sb.append(timeShow.getClassTimeContent());
				sb.append("</div>");
			}
			sb.append("</td>");
			
			for (int i = j * 7; i < (j + 1) * 7; i++) {
				sb.append("<td class='title_hover'>");
				if(!"".equals(days[i])) {
					sb.append("<h5>");
					//sb.append(sdf.format(thisMonth.getTime()) + "-" + days[i]);
					sb.append(days[i]);
					sb.append("</h5>");
					
					LinkedHashMap<Classtime, ArrayList<Coursecalendar>> dayDetail = monthDetail.get(new Integer(days[i]));
					Iterator<Classtime> iAllTimes = allTimes.iterator();
					Classtime classtime = null;
					if(dayDetail == null) {
						while(iAllTimes.hasNext()) {
							classtime = iAllTimes.next();
							createAvaliableLink(sb, sdf1.format(thisMonth.getTime()) + days[i], classtime.getClassTimeId(), courseLocationId, avaliableContent);
						}
					}else {
						while(iAllTimes.hasNext()) {
							classtime = iAllTimes.next();
							ArrayList<Coursecalendar> coursecalendars = dayDetail.get(classtime);
							if(coursecalendars != null) {
								if(coursecalendars.size() == 1) {
									Coursecalendar coursecalendar = coursecalendars.get(0);
									
									if(coursecalendar != null) {
										String courseTrunkTypeName = getShowWord(locale, coursecalendar.getCoursebranchtype().getCourseBranchTypeNameKey());
										sb.append("<div class='hand' style='color:" + coursecalendar.getCoursetrunktype().getFontColor() + ";background-color:" + coursecalendar.getCoursetrunktype().getBackgroundColor() + "' onclick=\"orderPublic('" + coursecalendar.getCourseCalendarId() + "')\">");
										sb.append(courseTrunkTypeName + " ");
										sb.append("<a onclick=\"orderPrivate('" + sdf1.format(thisMonth.getTime()) + days[i] + "'," + classtime.getClassTimeId() + "," + courseLocationId + ")\">+</a>");
										sb.append("</div>");
									}else {
										createAvaliableLink(sb, sdf1.format(thisMonth.getTime()) + days[i], classtime.getClassTimeId(), courseLocationId, avaliableContent);
									}
								}else {
									Iterator<Coursecalendar> iCoursecalendars = coursecalendars.iterator();
									Coursecalendar coursecalendar = null;
									
									sb.append("<div class='scrollDiv'>");
									sb.append("<div class='scrollText'>");
									sb.append("<ul>");
									
									while(iCoursecalendars.hasNext()) {
										coursecalendar = iCoursecalendars.next();
										
										if(coursecalendar != null) {
											String courseTrunkTypeName = getShowWord(locale, coursecalendar.getCoursebranchtype().getCourseBranchTypeNameKey());
											sb.append("<li>");
											sb.append("<div class='hand' style='color:" + coursecalendar.getCoursetrunktype().getFontColor() + ";background-color:" + coursecalendar.getCoursetrunktype().getBackgroundColor() + "' onclick=\"orderPublic('" + coursecalendar.getCourseCalendarId() + "')\">");
											sb.append(courseTrunkTypeName + " ");
											sb.append("<a onclick=\"orderPrivate('" + sdf1.format(thisMonth.getTime()) + days[i] + "'," + classtime.getClassTimeId() + "," + courseLocationId + ")\">+</a>");
											sb.append("</div>");
											sb.append("</li>");
										}else {
											createAvaliableLink(sb, sdf1.format(thisMonth.getTime()) + days[i], classtime.getClassTimeId(), courseLocationId, avaliableContent);
										}
									}
									
									sb.append("</ul>");
									sb.append("</div>");
									sb.append("</div>");
								}
							}else {
								createAvaliableLink(sb, sdf1.format(thisMonth.getTime()) + days[i], classtime.getClassTimeId(), courseLocationId, avaliableContent);
							}
						}
					}
				}
				sb.append("</td>");
			}
			
			sb.append("<td>");
			sb.append("<div>&nbsp;</div>");
			allTimeShow = allTimes.iterator();
			while(allTimeShow.hasNext()) {
				timeShow = allTimeShow.next();
				sb.append("<div>");
				sb.append(timeShow.getClassTimeContent());
				sb.append("</div>");
			}
			sb.append("</td>");
			sb.append("</tr></tbody>");
		}
		sb.append("</table></div>");

		return sb.toString();
	}
	
	private static void createAvaliableLink(StringBuffer sb, String date, Integer classTimeId, Integer courseLocationId, String avaliableContent) {
		sb.append("<div>");
		sb.append("<a class='hand' onclick=\"orderPrivate('" + date + "'," + classTimeId + "," + courseLocationId + ")\">" + avaliableContent + "</a>");
		sb.append("</div>");
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
	
//	public static void main(String[] args) {
//		Calendar thisMonth = Calendar.getInstance();
//		thisMonth.setTime(new Date());
//		String calendar = CalendarGenerator.getCalendar(new Date(), 6);
//		System.out.println(calendar);
//	}
}
