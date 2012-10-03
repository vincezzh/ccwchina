package com.ccw.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ccw.bean.Calendarwithcourse;
import com.ccw.bean.Classtime;
import com.ccw.bean.Course;
import com.ccw.bean.Coursecalendar;

public class ExcelCalendar {
	private final String CONTENT_WRAP = "\r\n";
	private String[] days;
	private Map<Integer, String> months;
	private List<String> weeks;
	private Locale locale;
	private Date firstDayOfCalendar;
	private Date lastDayOfCalendar;
	private Map<String, List<Coursecalendar>> ccMap;
	private List<Classtime> allTimes;
	
	private HSSFWorkbook wb;
	private HSSFPalette palette;
	private Map<String, Short> colorMap;
	private short colorIndex = 63;
	private Map<String, HSSFCellStyle> styles;
	
	public ExcelCalendar(List<Coursecalendar> courseCalendars, List<Classtime> allTimes, Date currentMonth, Locale locale) {
		wb = new HSSFWorkbook();
		ccMap = new HashMap<String, List<Coursecalendar>>();
		colorMap = new HashMap<String, Short>();
		this.allTimes = allTimes;
		this.locale = locale;
		this.initilize();
		this.createWeeks(currentMonth);
		this.transformListToMap(courseCalendars);
		this.createStyles(wb);
	}
	
	private void initilize() {
		String[] days = {
		        "Sunday", "Monday", "Tuesday",
		        "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] months = {
		        "January", "February", "March","April", "May", "June","July", "August",
		        "September","October", "November", "December"};
		
	}
	
	private void createWeeks(Date currentMonth) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentMonth);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DATE, 1 - c.get(Calendar.DAY_OF_WEEK));
		firstDayOfCalendar = c.getTime();
		c.setTime(currentMonth);
		c.set(Calendar.DAY_OF_MONTH,  c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK));
		lastDayOfCalendar = c.getTime();
		
		weeks = new ArrayList<String>();
		c.setTime(firstDayOfCalendar);
		c.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		StringBuffer sheetTitle = null;
		do {
			sheetTitle = new StringBuffer();
			c.add(Calendar.DATE, 1);
			sheetTitle.append(sdf.format(c.getTime()));
			sheetTitle.append(" to ");
			c.add(Calendar.DATE, 6);
			sheetTitle.append(sdf.format(c.getTime()));
			weeks.add(sheetTitle.toString());
		}while(c.getTime().compareTo(lastDayOfCalendar) < 0);
	}
	
	private void transformListToMap(List<Coursecalendar> courseCalendars) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Coursecalendar cc : courseCalendars) {
			String ccMapKey = sdf.format(cc.getClassDate()) + "@@@" + cc.getClasstime().getClassTimeId();
			if(ccMap.get(ccMapKey) == null) {
				List<Coursecalendar> ccList = new ArrayList<Coursecalendar>();
				ccList.add(cc);
				ccMap.put(ccMapKey, ccList);
			}else {
				ccMap.get(ccMapKey).add(cc);
			}
			
			//Collection Colors
			this.collectColors(cc.getCoursetrunktype().getFontColor());
			this.collectColors(cc.getCoursetrunktype().getBackgroundColor());
		}
	}
	
	private void collectColors(String hexColor) {
		if(colorMap.get(hexColor) == null) {
			if(palette == null)
				palette = wb.getCustomPalette();
			
	    	palette.setColorAtIndex(colorIndex,
	                (byte) java.awt.Color.decode(hexColor).getRed(),
	                (byte) java.awt.Color.decode(hexColor).getGreen(),
	                (byte) java.awt.Color.decode(hexColor).getBlue()
	        );
	    	colorMap.put(hexColor, colorIndex);
	    	colorIndex--;
		}
	}
	
	private void createStyles(HSSFWorkbook wb){
		styles = new HashMap<String, HSSFCellStyle>();
        short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

        HSSFCellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)24);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)12);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        styles.put("weekTitle", style);

        Font weekendTitleFont = wb.createFont();
        weekendTitleFont.setFontHeightInPoints((short)14);
        weekendTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(weekendTitleFont);
        styles.put("weekendTitle", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setWrapText(true);
        styles.put("weekendContent", style);

        Font workdayTitleFont = wb.createFont();
        workdayTitleFont.setFontHeightInPoints((short)14);
        workdayTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(workdayTitleFont);
        styles.put("workdayTitle", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setWrapText(true);
        styles.put("workdayContent", style);
    }
	
	public HSSFWorkbook generateMonthScheduleExcel() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDayOfCalendar);
        StringBuffer sb = null;

        for (String week : weeks) {
        	Date firstDayOfWeek = calendar.getTime();
        	
            //create a sheet for each month
            Sheet sheet = wb.createSheet(week);

            //turn off gridlines
            sheet.setDisplayGridlines(false);
            sheet.setPrintGridlines(false);
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);
            PrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);

            //the following three statements are required only for HSSF
            sheet.setAutobreaks(true);
            printSetup.setFitHeight((short)1);
            printSetup.setFitWidth((short)1);

            //the header row: centered text in 48pt font
            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(40);
            Cell titleCell = headerRow.createCell(0);
            titleCell.setCellValue(week);
            titleCell.setCellStyle(styles.get("title"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$G$1"));

            //header with month titles
            Row monthRow = sheet.createRow(1);
            for (int i = 0; i < days.length; i++) {
                //set column widths, the width is measured in units of 1/256th of a character width
                sheet.setColumnWidth(i, 30*256); //the column is 13 characters wide
                Cell monthCell = monthRow.createCell(i);
                monthCell.setCellValue(days[i]);
                monthCell.setCellStyle(styles.get("weekTitle"));
            }

            //content
            int rownum = 2;
            sheet.createRow(rownum++);
            int totalRowNumber = this.caculateTotalRowNumber(firstDayOfWeek);
            for(int i=0; i<totalRowNumber; i++) {
            	sheet.createRow(rownum++);
            }
            for (int i = 0; i < days.length; i++) {
            	int rowNumIndex = 2;
            	//Date title
            	Row row = sheet.getRow(rowNumIndex++);
            	row.setHeightInPoints(20);
                Cell dayCell = row.createCell(i);
                dayCell.setCellValue(calendar.get(Calendar.DAY_OF_MONTH));
                
            	if(i == 0 || i == days.length-1) {
                    dayCell.setCellStyle(styles.get("weekendTitle"));
                } else {
                    dayCell.setCellStyle(styles.get("workdayTitle"));
                }
            	
            	//Date content
            	for(Classtime classTime : allTimes) {
    				String ccMapKey = sdf.format(calendar.getTime()) + "@@@" + classTime.getClassTimeId();
    				List<Coursecalendar> ccList = ccMap.get(ccMapKey);
    				if(ccList != null) {
    					for(Coursecalendar courseCalendar : ccList) {
    						row = sheet.getRow(rowNumIndex++);
    						row.setHeightInPoints(80);
    						dayCell = row.createCell(i);
    						sb = new StringBuffer();
    						sb.append("[" + classTime.getClassTimeContent() + "]");
    						sb.append(" ");
    						sb.append(courseCalendar.getCoursebranchtype().getCourseBranchTypeNameKey());
    						sb.append(CONTENT_WRAP);
    						Iterator<Calendarwithcourse> iCalendarwithcourse = courseCalendar.getCalendarwithcourses().iterator();
    						Course course = null;
    						while(iCalendarwithcourse.hasNext()) {
    							course = iCalendarwithcourse.next().getCourse();
    							sb.append(course.getCourseNameEn());
    							sb.append(CONTENT_WRAP);
    						}
    						dayCell.setCellValue(sb.toString());
    						
    						if(i == 0 || i == days.length-1) {
    							HSSFCellStyle style = wb.createCellStyle();
    							style.cloneStyleFrom(styles.get("weekendContent"));
    							style.setFillForegroundColor(colorMap.get(courseCalendar.getCoursetrunktype().getBackgroundColor()));
    							Font font = wb.createFont();
    							font.setFontHeightInPoints((short)10);
    							font.setColor(colorMap.get(courseCalendar.getCoursetrunktype().getFontColor()));
    							style.setFont(font);
    		                    dayCell.setCellStyle(style);
    		                } else {
    		                	HSSFCellStyle style = wb.createCellStyle();
    							style.cloneStyleFrom(styles.get("workdayContent"));
    							style.setFillForegroundColor(colorMap.get(courseCalendar.getCoursetrunktype().getBackgroundColor()));
    							Font font = wb.createFont();
    							font.setFontHeightInPoints((short)10);
    							font.setColor(colorMap.get(courseCalendar.getCoursetrunktype().getFontColor()));
    							style.setFont(font);
    		                    dayCell.setCellStyle(style);
    		                }
    					}
    				}
    			}
            	calendar.add(Calendar.DATE, 1);
            }
        }
        
        return wb;
    }
	
	private int caculateTotalRowNumber(Date firstDayOfWeek) {
		int totalRowNumber = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(firstDayOfWeek);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < days.length; i++) {
			int listSize = 0;
			for(Classtime classTime : allTimes) {
				String ccMapKey = sdf.format(c.getTime()) + "@@@" + classTime.getClassTimeId();
				if(ccMap.get(ccMapKey) != null) {
					listSize += ccMap.get(ccMapKey).size();
					
				}
			}
			if(listSize > totalRowNumber)
				totalRowNumber = listSize;
			c.add(Calendar.DATE, 1);
		}
		return totalRowNumber;
	}
}
