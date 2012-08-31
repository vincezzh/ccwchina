package com.ccw.interceptor;

import java.util.Map;

import com.ccw.bean.Userdetail;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -7077168788157576916L;

	public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        Userdetail user = (Userdetail)session.get("user_session");
         if (user != null) {
             return ai.invoke();
        } else {
             return Action.LOGIN;
        }        
    }
}