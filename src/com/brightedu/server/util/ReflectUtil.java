package com.brightedu.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

	private static Object operate(Object obj, String fieldName,
			Object fieldVal, String type) throws Exception {
		Object ret = null;

		// 获得对象类型
		Class<? extends Object> classType = obj.getClass();
		// 获得对象的所有属性
		Field fields[] = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getName().equals(fieldName)) {

				String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和属性对应的getXXX()方法的名字
				if ("set".equals(type)) {
					String setMethodName = "set" + firstLetter
							+ fieldName.substring(1); // 获得和属性对应的getXXX()方法
					Method setMethod = classType.getMethod(setMethodName,
							new Class[] { field.getType() }); // 调用原对象的getXXX()方法
					ret = setMethod.invoke(obj, new Object[] { fieldVal });
				}
				if ("get".equals(type)) {
					String getMethodName = "get" + firstLetter
							+ fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字
					Method getMethod = classType.getMethod(getMethodName,
							new Class[] {});
					ret = getMethod.invoke(obj, new Object[] {});
				}
				return ret;
			}
		}

		return ret;
	}

	public static Object getVal(Object obj, String fieldName) throws Exception {
		return operate(obj, fieldName, null, "get");
	}

	public static void setVal(Object obj, String fieldName, Object fieldVal)
			throws Exception {
		operate(obj, fieldName, fieldVal, "set");
	}

	public static Method getDeclaredMethod(Class oc, String methodName,
			Class<?>... parameterTypes) {
		for (Class<?> superClass = oc; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				// superClass.getMethod(methodName, parameterTypes);
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method 不在当前类定义, 继续向上转型
			}
		}

		return null;
	}

	private static void makeAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
	}

	public static Field getDeclaredField(Object object, String filedName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(filedName);
			} catch (NoSuchFieldException e) {
				// Field 不在当前类定义, 继续向上转型
			}
		}
		return null;
	}

	public static Field[] getDeclaredFields(Class oc) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> superClass = oc; superClass != Object.class; superClass = superClass
				.getSuperclass()) {

			Field[] fs = superClass.getDeclaredFields();
			for (Field f : fs) {
				fields.add(f);
			}
		}
		return fields.toArray(new Field[fields.size()]);
	}

	public static Object invokeMethod(Object object, String methodName,
			Class<?>[] parameterTypes, Object[] parameters) throws Exception {
		Method method = getDeclaredMethod(object.getClass(), methodName,
				parameterTypes);

		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + object + "]");
		}

		method.setAccessible(true);

		return method.invoke(object, parameters);
	}

	public static void setFieldValue(Object object, String fieldName,
			Object value) throws Exception {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);
		field.set(object, value);
	}

	public static Object getFieldValue(Object object, String fieldName)
			throws Exception {
		Field field = getDeclaredField(object, fieldName);
		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		Object result = field.get(object);

		return result;
	}

}
