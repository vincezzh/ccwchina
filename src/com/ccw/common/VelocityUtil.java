package com.ccw.common;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtil {
	private static final Log log = LogFactory.getLog(VelocityUtil.class);
	private Properties p = new Properties();
	private VelocityEngine ve = new VelocityEngine();
	
	public static VelocityUtil vu;
	
	public static VelocityUtil getInstance() {
		if(vu == null) {
			vu = new VelocityUtil();
		}
		return vu;
	}

	private VelocityUtil() {
		try {
			p.setProperty("resource.loader", "class");
			p.setProperty("class.resource.loader.description",
					"Velocity Classpath Resource Loader");
			p.setProperty("class.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			ve.init(p);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	public String generate(String packageName, Object entity) {
		String htmlReturn = "";
		try {
			Template t = ve.getTemplate(packageName + entity.getClass().getSimpleName() + ".vm", "UTF-8");
			VelocityContext context = new VelocityContext();
			context.put("entity", entity);
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			htmlReturn = writer.toString();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return htmlReturn;
	}

//	public static void main(String[] args) {
//		VelocityUtil vu = new VelocityUtil();
//		Group group = new Group();
//		group.setGroupName("test group");
//		group.setPath("/testPath");
//		System.out.println(vu.generate(group));
//	}
}