package com.brightedu.server;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DataBaseRPCHandler implements InvocationHandler {
	// 要代理的原始对象
	private Object objOriginal;

	public DataBaseRPCHandler(Object obj) {
		this.objOriginal = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result;

		// 方法调用之前,校验权限

		// 调用原始对象的方法
		result = method.invoke(this.objOriginal, args);

		// 方法调用之后，录入audit

		return result;
	}

}
