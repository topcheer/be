package com.brightedu.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.brightedu.model.edu.User;
import com.brightedu.server.util.Log;

public class DataBaseRPCHandler implements InvocationHandler {
	// 要代理的原始对象
	private DataBaseRPCAgent rpcAgent;

	public DataBaseRPCHandler(Object obj) {
		this.rpcAgent = (DataBaseRPCAgent) obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 方法调用之前,校验权限
		
		User user = (User) rpcAgent.getRemoteServlet().getUser();
		if(user == null){
			Log.d("session expired or user not login");
			rpcAgent.getRemoteServlet().unAuthorized();
			return null;
		}
		System.out.println("-----user: " + user.getUser_name() + "     "
				+ method.getName());
		// 调用原始对象的方法
		Object result = method.invoke(this.rpcAgent, args);

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
		if (o instanceof String || o instanceof Integer || o instanceof Long
				|| o instanceof Date || o instanceof Double
				|| o instanceof Float) {
			sb.append(o);
		} else if (o instanceof Iterable) {
			Iterable ol = (Iterable) o;
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
		Log.i("Audit -- " + content);
	}

}
