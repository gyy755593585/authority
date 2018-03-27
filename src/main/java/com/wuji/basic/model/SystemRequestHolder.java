package com.wuji.basic.model;

public class SystemRequestHolder {

	private final static ThreadLocal<SystemRequest> SYSTEM_REQUEST_THREADLOCAL = new ThreadLocal<SystemRequest>();

	public static void initRequestHolder(SystemRequest systemRequest) {
		SYSTEM_REQUEST_THREADLOCAL.set(systemRequest);
	}

	public static SystemRequest getSystemRequest() {
		return SYSTEM_REQUEST_THREADLOCAL.get();
	}

	public static void remove() {
		SYSTEM_REQUEST_THREADLOCAL.remove();
	}
}
