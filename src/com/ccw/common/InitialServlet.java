package com.ccw.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vincezzh.meitounao.MeiTouNaoCenter;

/**
 * Servlet implementation class InitialServlet
 */
public class InitialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {
		try {
			ServletContext ctx=	getServletContext();
			WorkThread wt = new WorkThread();
			WorkCenter wc = (WorkCenter)WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("workCenter");
			MeiTouNaoCenter mtnc = (MeiTouNaoCenter)WebApplicationContextUtils.getWebApplicationContext(ctx).getBean("MeiTouNaoCenter");
			String certificatePath = getServletContext().getRealPath("WEB-INF/MeiTouNaoCertificate.p12");
			mtnc.setCertificatePath(certificatePath);
			System.out.println(certificatePath);
			
			wt.startThread(wc, mtnc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
