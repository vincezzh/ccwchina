package com.ccw.interceptor;

import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class DefaultLanguageInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -7077168788157576916L;

	public String intercept(ActionInvocation ai) throws Exception {
		Map session = ActionContext.getContext().getSession();
		Locale locale = (Locale)session.get("WW_TRANS_I18N_LOCALE");
		if(locale == null) {
			locale = Locale.US;
			session.put("WW_TRANS_I18N_LOCALE", locale);
		}
		
        return ai.invoke();
    }
}