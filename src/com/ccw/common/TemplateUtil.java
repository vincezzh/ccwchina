package com.ccw.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TemplateUtil {
	public static String readTemplate(InputStream in) throws Exception {
		try {
	        InputStreamReader read = new InputStreamReader(in, "UTF-8");
	        BufferedReader reader=new BufferedReader(read);
	        StringBuffer template = new StringBuffer();
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	template.append(line);
	        }
	        
	        return template.toString();
		}finally {
			if(in != null) {
				in.close();
			}
		}
	}
}
