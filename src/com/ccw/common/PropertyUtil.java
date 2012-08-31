package com.ccw.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyUtil {
	private static final String path = PropertyUtil.class.getResource("/").getPath();
	private static final String[] fileNames = new String[]{
		"custom.properties", 
		"custom_en_US.properties", 
		"custom_zh_CN.properties", 
		"custom_ja_JP.properties"};
	
	public static String get(String type, String key) {
		if(Params.DEFAULT.equals(type))
			return readProperty(key, fileNames[0]);
		else if(Params.USA.equals(type))
			return readProperty(key, fileNames[1]);
		else if(Params.CHINA.equals(type))
			return readProperty(key, fileNames[2]);
		else if(Params.JAPAN.equals(type))
			return readProperty(key, fileNames[3]);
		else
			return readProperty(key, fileNames[0]);
	}
	
	public static String[] readFromAllLanguageProperties(String key) {
		String[] values = new String[fileNames.length];
		for(int i=0; i<fileNames.length; i++) {
			values[i] = readProperty(key, fileNames[i]);
		}
		
		return values;
	}
	
	public static String readProperty(String key, String propertyFileName) {
		String value = null;
		Properties prop = new Properties();
		InputStream fis = null;
		try {
			fis = new FileInputStream(path + propertyFileName);
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
	
	public static void writeToAllLanguageProperties(String key, String[] values) {
		Properties prop = new Properties();
		for(int i=0; i<fileNames.length; i++) {
			InputStream fis = null;
			OutputStream fos = null;
			try {
				fis = new FileInputStream(path + fileNames[i]);
				prop.load(fis);
				fos = new FileOutputStream(path + fileNames[i]);
				prop.setProperty(key, values[i]);
				prop.store(fos, null);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(fis != null) {
						fis.close();
					}
					if(fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//String[] values = new String[]{"wok", "wok work", "料理干活", "料的理干活"};
		//PropertyUtil.writeToAllLanguageProperties("C:\\DELL", values);
		PropertyUtil.readFromAllLanguageProperties("DIM_SUM_AND_WOK");
	}
}
