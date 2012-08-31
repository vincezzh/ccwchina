package com.ccw.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.ccw.bean.Coursecalendar;

public class SamePackageUtil {
	public static Object[] findOutSamePackage(Coursecalendar course, ArrayList<Coursecalendar> courseList) {
		Object[] info = new Object[2];
		ArrayList<Date> packageClassesDate = new ArrayList<Date>();
		StringBuffer sb = new StringBuffer();
		
		if(course.getCoursetrunktype().getCourseTrunkTypeId().intValue() == Params.DIM_SUM_TRUNKTYPE_ID.intValue() || course.getCoursetrunktype().getCourseTrunkTypeId().intValue() == Params.WOK_TRUNKTYPE_ID.intValue()) {
			Calendar c = Calendar.getInstance();
			c.setTime(course.getClassDate());
			c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date endOfMonth = c.getTime();
			c.setTime(course.getClassDate());
			c.add(Calendar.DATE, 7);
			Coursecalendar tempCourse = null;
			for(;c.getTime().compareTo(endOfMonth) < 1; c.add(Calendar.DATE, 7)) {
				Iterator<Coursecalendar> i = courseList.iterator();
				while(i.hasNext()) {
					tempCourse = i.next();
					if(course.getCoursetrunktype().getCourseTrunkTypeId().intValue() == tempCourse.getCoursetrunktype().getCourseTrunkTypeId().intValue()
							&& course.getClasstime().getClassTimeId().intValue() == tempCourse.getClasstime().getClassTimeId().intValue()
							&& c.getTime().compareTo(tempCourse.getClassDate()) == 0) {
						packageClassesDate.add(tempCourse.getClassDate());
						sb.append(tempCourse.getCourseCalendarId() + ";");
					}
				}
			}
		}else {
			Iterator<Coursecalendar> i = courseList.iterator();
			Coursecalendar tempCourse = null;
			while(i.hasNext()) {
				tempCourse = i.next();
				if(course.getCoursetrunktype().getCourseTrunkTypeId().intValue() == tempCourse.getCoursetrunktype().getCourseTrunkTypeId().intValue()) {
					packageClassesDate.add(tempCourse.getClassDate());
					sb.append(tempCourse.getCourseCalendarId() + ";");
				}
			}
		}
		
		info[0] = packageClassesDate;
		info[1] = sb.toString();
		return info;
	}
}
