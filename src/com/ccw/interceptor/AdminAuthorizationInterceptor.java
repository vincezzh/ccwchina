package com.ccw.interceptor;

import java.util.Map;

import com.ccw.bean.Admindetail;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminAuthorizationInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 307074507084463663L;

	public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        Admindetail admin = (Admindetail)session.get("admin_session");
         if (admin != null) {
             return ai.invoke();
        } else {
             return Action.LOGIN;
        }        
    }
}
