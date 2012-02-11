package com.brightedu.server.util;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
		@Signature(type = StatementHandler.class, method = "parameterize", args = { Statement.class }) })
public class PagingInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
//		BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler);
		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		
		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue(
				"delegate.boundSql.sql",
				getLimitString(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
//		if (Log.isDebugEnabled()) {
//			Log.d("生成分页SQL : " + boundSql.getSql());
//		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	private String getLimitString(String originalSQL, int offset, int limit) {
		//postgresql
		return originalSQL.trim() + " limit " + limit + " offset " + offset;
	}

}
