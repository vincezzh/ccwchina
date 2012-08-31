package com.ccw.action.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TitleAction extends ActionSupport {
	private static final long serialVersionUID = -7870007759570528045L;
	private Log log = LogFactory.getLog(TitleAction.class);

	public String getTitleContent() {
		try {
			log.debug("TitleAction.getTitleContent()");
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8"); 
			PrintWriter out = response.getWriter();
			
			String title1 = getText("TITLE1");
			String title2 = getText("TITLE2");
			String title3 = getText("TITLE3");
			String title4 = getText("TITLE4");
			String title5 = getText("TITLE5");
			String title6 = getText("TITLE6");
			String title7 = getText("TITLE7");
			String title8 = getText("TITLE8");
			
			out.print(title1 + "_" + title2 + "_" + title3 + "_" + title4 + "_" + title5 + "_" + title6 + "_" + title7 + "_" + title8);
			
			return null;
		}catch(Exception e) {
			log.error(e);
			log.error(e.getMessage());
			return ERROR;
		}
	}
}
