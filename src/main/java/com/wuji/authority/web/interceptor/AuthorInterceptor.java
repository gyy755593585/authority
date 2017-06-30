package com.wuji.authority.web.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

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
			HttpServletRequest request = ServletActionContext.getRequest();
			ActivityUser activityUser = (ActivityUser) session.get("activityUser");
			Set<String> permitCodes = activityUser.getPermitCodes();
			// Set<String> systemCode = (Set<String>) request.getAttribute("userSystemCodes");
			// for (String userSystem : systemCode) {
			// System.out.println(userSystem);
			// }
			String actionName = invocation.getProxy().getActionName();
			String methodName = invocation.getProxy().getMethod();
			String permitCode = actionName + "!" + methodName;
			System.out.println(methodName);
			System.out.println(permitCode);
			if (activityUser.isAdmin()) {
				result = invocation.invoke();
				return result;
			}
			if (permitCodes == null) {
				return result;
			} else if (permitCodes.contains(actionName) && permitCodes.contains(permitCode)) {
				result = invocation.invoke();
			}
			/*for (UserSystem userSystem : userSystems) {
				if (userSystem.getSystemCode().equals(systemCode)) {
					SysPermitType sysPermitType = rolePermit.get(userSystem);
					if (sysPermitType.equals(SysPermitType.READ)) {
						String actionName = invocation.getProxy().getMethod();
						if (Constant.READ_ONLY_ACTION.contains(actionName)) {
							result = invocation.invoke();
						}
					}
				}
			}*/

		}
		return result;
	}

}
