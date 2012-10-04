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

import org.apache.poi.hssf.usermodel.HSSFCell;
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
import com.ccw.common.Params;
import com.ccw.common.PropertyUtil;

public class ExcelCalendar {
	private String prefixPublic = Params.CCW_CONTEXT + "order/mail-public-order.htm?cid=";
	private String prefixPrivate = Params.CCW_CONTEXT + "order/mail-private-order.htm?courseDate=";
	private final String CONTENT_WRAP = "\n";
	private String[] days;
	private String warmTip;
	private List<String> weeks;
	private Locale locale;
	private Date firstDayOfCalendar;
	private Date lastDayOfCalendar;
	private int currentYear;
	private int currentMonth;
	private Map<String, List<Coursecalendar>> ccMap;
	private List<Classtime> allTimes;
	private Integer courseLocationId;
	private String privateCourseName;
	
	private HSSFWorkbook wb;
	private HSSFPalette palette;
	private Map<String, Short> colorMap;
	private short colorIndex = 63;
	private Map<String, HSSFCellStyle> styles;
	
	public ExcelCalendar(List<Coursecalendar> courseCalendars, List<Classtime> allTimes, Integer courseLocationId, Date currentMonth, Locale locale) {
		wb = new HSSFWorkbook();
		ccMap = new HashMap<String, List<Coursecalendar>>();
		colorMap = new HashMap<String, Short>();
		this.allTimes = allTimes;
		this.courseLocationId = courseLocationId;
		this.locale = locale;
		this.initilize();
		this.createWeeks(currentMonth);
		this.transformListToMap(courseCalendars);
		this.createStyles(wb);
	}
	
	private void initilize() {
		String[] daysEn = {
		        "Sunday", "Monday", "Tuesday",
		        "Wednesday", "Thursday", "Friday", "Saturday"};
		String[] daysJp = {
		        "日曜日", "月曜日", "火曜日",
		        "水曜日", "木曜日", "金曜日", "土曜日"};
		String[] daysZh = {
		        "星期天", "星期一", "星期二",
		        "星期三", "星期四", "星期五", "星期六"};
		String warmTipEn = "Tip: You can book courses directly by clicking the courses below.";
		String warmTipJp = "ヒント：以下のコースをクリックして、あなたは、オンライン予約を指示することができるようになります。";
		String warmTipZh = "提示：点击以下课程，您就能直接在线预定。";
		String privateCourseNameEn = "Available to Private Course Booking";
		String privateCourseNameJp = "スケジュールされたプライベートコース";
		String privateCourseNameZh = "可以进行私人课程预定";
		if(locale == null) {
			days = daysEn;
			warmTip = warmTipEn;
			privateCourseName = privateCourseNameEn;
		}else if(Params.USA.equals(locale.getLanguage())) {
			days = daysEn;
			warmTip = warmTipEn;
			privateCourseName = privateCourseNameEn;
		}else if(Params.CHINA.equals(locale.getLanguage())) {
			days = daysZh;
			warmTip = warmTipZh;
			privateCourseName = privateCourseNameZh;
		}else if(Params.JAPAN.equals(locale.getLanguage())) {
			days = daysJp;
			warmTip = warmTipJp;
			privateCourseName = privateCourseNameJp;
		}else {
			days = daysEn;
			warmTip = warmTipEn;
			privateCourseName = privateCourseNameEn;
		}
	}
	
	private void createWeeks(Date currentMonthDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentMonthDate);
		currentYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.DATE, 1 - c.get(Calendar.DAY_OF_WEEK));
		firstDayOfCalendar = c.getTime();
		c.setTime(currentMonthDate);
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
			sheetTitle.append(" ~ ");
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
        style.setWrapText(true);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)12);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.LIME.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        styles.put("weekTitle", style);

        Font weekendTitleFont = wb.createFont();
        weekendTitleFont.setFontHeightInPoints((short)14);
        weekendTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
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
        
        Font availableFont = wb.createFont();
        availableFont.setFontHeightInPoints((short)10);
        availableFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setWrapText(true);
        style.setFont(availableFont);
        styles.put("privateAvailable", style);
    }
	
	public HSSFWorkbook generateMonthScheduleExcel() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
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
            headerRow.setHeightInPoints(80);
            Cell titleCell = headerRow.createCell(0);
            titleCell.setCellValue(currentYear + " [" + week + "]" + CONTENT_WRAP + warmTip);
            titleCell.setCellStyle(styles.get("title"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$G$1"));

            //header with month titles
            Row monthRow = sheet.createRow(1);
            monthRow.setHeightInPoints(24);
            for (int i = 0; i < days.length; i++) {
                //set column widths, the width is measured in units of 1/256th of a character width
                sheet.setColumnWidth(i, 25*256); //the column is 25 characters wide
                Cell monthCell = monthRow.createCell(i);
                monthCell.setCellValue(days[i]);
                monthCell.setCellStyle(styles.get("weekTitle"));
            }

            //content
            int rownum = 2;
            sheet.createRow(rownum++);
            int totalRowNumber = this.caculateTotalRowNumber(firstDayOfWeek) + 3;
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
    						row.setHeightInPoints(65);
    						dayCell = row.createCell(i);
    						sb = new StringBuffer();
    						sb.append("[" + classTime.getClassTimeContent() + "]");
    						sb.append(" ");
    						sb.append(this.getShowWord(locale, courseCalendar.getCoursebranchtype().getCourseBranchTypeNameKey()));
    						sb.append(CONTENT_WRAP);
    						Iterator<Calendarwithcourse> iCalendarwithcourse = courseCalendar.getCalendarwithcourses().iterator();
    						Course course = null;
    						int number = 1;
    						while(iCalendarwithcourse.hasNext()) {
    							course = iCalendarwithcourse.next().getCourse();
    							sb.append(number + ". ");
    							if(locale == null) {
    								sb.append(course.getCourseNameEn());
    							}else if(Params.USA.equals(locale.getLanguage())) {
    								sb.append(course.getCourseNameEn());
    							}else if(Params.CHINA.equals(locale.getLanguage())) {
    								sb.append(course.getCourseNameCn());
    							}else if(Params.JAPAN.equals(locale.getLanguage())) {
    								sb.append(course.getCourseNameJp());
    							}else {
    								sb.append(course.getCourseNameEn());
    							}
    							sb.append(CONTENT_WRAP);
    							number++;
    						}
    						dayCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
    						String url = prefixPublic + courseCalendar.getCourseCalendarId();
    						dayCell.setCellFormula("HYPERLINK(\"" + url + "\",\"" + sb.toString() + "\")");
    						
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
    				}else {
    					if(currentMonth == calendar.get(Calendar.MONTH)) {
	    					row = sheet.getRow(rowNumIndex++);
							row.setHeightInPoints(65);
							dayCell = row.createCell(i);
							dayCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
							sb = new StringBuffer();
							sb.append("[" + classTime.getClassTimeContent() + "]");
							sb.append(CONTENT_WRAP);
							sb.append(privateCourseName);
							String url = prefixPrivate + sdf1.format(calendar.getTime()) + "&classTimeId=" + classTime.getClassTimeId() + "&courseLocationId=" + courseLocationId;
							dayCell.setCellFormula("HYPERLINK(\"" + url + "\",\"" + sb.toString() + "\")");
							dayCell.setCellStyle(styles.get("privateAvailable"));
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
	
	private String getShowWord(Locale locale, String key) {
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
