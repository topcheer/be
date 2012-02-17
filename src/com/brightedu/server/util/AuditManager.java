package com.brightedu.server.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AuditManager implements InvocationHandler {

	public static final int LEVEL1 = 1; // add, delete, update
	public static final int LEVEL2 = 2; // ALL Operations except get
	public static final int LEVEL3 = 3; // ALL Operation except get and search
	public static final int LEVEL4 = 4; // ALL Operation
	public static final int LEVEL5 = 5; // ALL Operation, include client info

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		return null;
	}

}
