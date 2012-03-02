package com.brightedu.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import com.brightedu.model.edu.User;
import com.brightedu.server.util.Log;
import com.brightedu.server.util.ReflectUtil;
import com.brightedu.server.util.Utils;
import com.brightedu.shared.OperatioinFailedException;

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
		try {
			long start = System.nanoTime();
			User user = (User) rpcAgent.getRemoteServlet().getUser();
			if (user == null) {
				rpcAgent.getRemoteServlet().unAuthorized();
				return null;
			}

			// 调用原始对象的方法
			Object result = method.invoke(this.rpcAgent, args);

			// 方法调用之后，录入audit
			String methodName = method.getName();
			if (!methodName.startsWith("get")) {
				StringBuilder sb = new StringBuilder();
				sb.append("(UserID=").append(user.getUser_id());
				sb.append(" UserName=").append(user.getUser_name()).append(")");
				sb.append(methodName);
				sb.append(": ");
				if (args != null) {
					for (Object o : args) {
						String audit = getObjectAudit(o);
						sb.append(audit).append("   #");
					}

				}
				audit(sb.toString());
			}
			long end = System.nanoTime();
			System.out.println("-----user: " + user.getUser_name() + "     "
					+ method.getName() + ", eclapsed " + (end - start)
					/ 1000000 + "ms");
			return result;
		} catch (Exception e) {
			Log.e("", e);
			throw e;
		}
	}

	private String getObjectAudit(Object o) {
		StringBuilder sb = new StringBuilder(o.getClass().getSimpleName());
		sb.append("  ");
		if (o instanceof String || o instanceof Integer || o instanceof Long
				|| o instanceof Date || o instanceof Double
				|| o instanceof Float || o instanceof Boolean) {
			sb.append(o);
		} else if (o instanceof Iterable) {
			Iterable ol = (Iterable) o;
			for (Object olo : ol) {
				sb.append(" #").append(getObjectAudit(olo)).append("#");
			}
		} else {
			sb.append(" #");
			Field[] fields = ReflectUtil.getDeclaredFields(o.getClass());
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					Method getMethod = null;
					try {
						getMethod = ReflectUtil.getDeclaredMethod(o.getClass(),
								Utils.getStandardGetMethodName(f.getName()));

						Object filedValue = getMethod.invoke(o, null);
						sb.append(f.getName()).append("=").append(filedValue)
								.append(" - ");
					} catch (Exception e) {
						//Do nothing here
					}
				}
			}
			sb.append("#");
		}
		return sb.toString();
	}

	private void audit(String content) {
		// FIXME record audit content, better be another manager thread
		Log.i("Audit -- " + content);
	}

}
