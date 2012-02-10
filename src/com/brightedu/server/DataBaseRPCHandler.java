package com.brightedu.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import com.brightedu.server.util.Log;

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
		String methodName = method.getName();
		if (!methodName.startsWith("get")) {
			StringBuilder sb = new StringBuilder(methodName);
			sb.append(":");
			if (args != null) {
				for (Object o : args) {
					String audit = getObjectAudit(o);
					sb.append(audit).append("   #");
				}
			}
			audit(sb.toString());
		}

		return result;
	}

	private String getObjectAudit(Object o) {
		StringBuilder sb = new StringBuilder();
		if (o instanceof String || o instanceof Integer || o instanceof Long) {
			sb.append(o);
		} else if (o instanceof List) {
			List ol = (List) o;
			for (Object olo : ol) {
				sb.append(" #").append(getObjectAudit(olo)).append("#");
			}
		} else {
			sb.append(" #");
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					Method m = null;
					try {
						m = o.getClass().getMethod(
								getStandardMethodName(f.getName()));
						Object filedValue = m.invoke(o, null);
						sb.append(f.getName()).append("=")
								.append(filedValue.toString()).append(" - ");
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
			sb.append("#");
		}
		return sb.toString();
	}

	private String getStandardMethodName(String fieldName) {
		char c = fieldName.charAt(0);
		return "get" + String.valueOf(c).toUpperCase() + fieldName.substring(1);
	}

	private void audit(String content) {
		// FIXME record audit content, better be another manager thread
		Log.i("Audit -- "+content);
	}

}
