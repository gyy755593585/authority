package com.wuji.authority.web.interceptor;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wuji.authority.vo.ActivityUser;

public class AuthorInterceptor extends MethodFilterInterceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = 471477605690202901L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String result = "notAccess";
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		if (session.containsKey("activityUser")) {
			ActivityUser activityUser = (ActivityUser) session.get("activityUser");
			Set<String> permitCodes = activityUser.getPermitCodes();
			String actionName = invocation.getProxy().getActionName();
			String methodName = invocation.getProxy().getMethod();
			String permitCode = actionName + "!" + methodName;

			if (activityUser.isAdmin()) {
				result = invocation.invoke();
				return result;
			}
			if (permitCodes == null) {
				return result;
			} else if (permitCodes.contains(actionName) && permitCodes.contains(permitCode)) {
				result = invocation.invoke();
			}

		}
		return result;
	}

}
