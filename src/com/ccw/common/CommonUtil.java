package com.ccw.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CommonUtil {
	public static String StringFilter(String str) throws PatternSyntaxException {      
		// 只允许字母和数字
		String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		//String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		
		return m.replaceAll("").trim();
	}
	
	public static String readProperty(String key, String fullPath) {
		String value = null;
		Properties prop = new Properties();
		InputStream fis = null;
		try {
			fis = new FileInputStream(fullPath);
			prop.load(fis);
			value = prop.getProperty(key);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
//	public static void main(String[] args) {
//		System.out.println(CommonUtil.StringFilter("昂abc$$_.def"));
//	}
}
