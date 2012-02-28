package com.brightedu.server.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {

	private static Map<String, DataSource> dataSources = new HashMap<String, DataSource>();
	private static String defaultSource;

	public static void load() {
		loadMybatis();
//		loadCustomPool();
	}
	
	protected static void loadMybatis(){
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader("/MapperConfig.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			Log.e(e.getMessage(), e);
		}
	}
	
	protected static void loadCustomPool(){
		Iterator<DataSource> it = dataSources.values().iterator();
		while (it.hasNext()) {
			DataSource source = it.next();
			if (source instanceof ComboPooledDataSource) {
				((ComboPooledDataSource) source).close();
			}
		}
		defaultSource = null;
		Document config = Utils.getXMLDocument(ConnectionManager.class
				.getResource("/datasource.xml"));
		NodeList sourceList = config.getElementsByTagName("pool");
		for (int i = 0; i < sourceList.getLength(); i++) {
			Element pool = (Element) sourceList.item(i);
			String poolType = pool.getAttribute("type");
			String name = pool.getAttribute("name");
			boolean defaultPool = "true".equals(pool.getAttribute("default")
					.toLowerCase());
			if (defaultPool)
				defaultSource = name;
			if (dataSources.containsKey("name")) {
				Log.e("duplicated datasource defined:" + name);
				break;
			}
			if (poolType.equals("jdbc")) {
				initJdbcDataSource(pool);
			} else if (poolType.equals("jndi")) {
				initJndiDataSource(pool);
			} else {
				Log.e("no such connection pool type: " + poolType);
				break;
			}
			Connection conn = getConnection(name);
			releaseConnection(conn);
		}
	}
	
	static SqlSessionFactory sessionFactory;
	
	public static SqlSessionFactory getSessionFactory(){
		return sessionFactory;
	}

	private static void initJndiDataSource(Element pool) {
		String name = pool.getAttribute("name");
		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			String javaEnv = pool.getAttribute("jndiprefix");
			if (javaEnv != null && !javaEnv.equals("")) {
				String jndiname = pool.getAttribute("jndiname");
				Context envCtx = (Context) initCtx.lookup(javaEnv);
				ds = (DataSource) envCtx.lookup(jndiname);

			} else {
				String jndiname = pool.getAttribute("jndiname");
				ds = (DataSource) initCtx.lookup(jndiname);
			}
			dataSources.put(name, ds);
		} catch (NamingException e) {
			Log.e("initJndiDataSource failed", e);
		}
	}

	private static void initJdbcDataSource(Element pool) {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource(true);
		// PoolBackedDataSource p = new PoolBackedDataSource();
		String name = pool.getAttribute("name");
		dataSources.put(name, pooledDataSource);

		NodeList paramConfig = pool.getElementsByTagName("param");
		for (int n = 0; n < paramConfig.getLength(); n++) {
			Element param = (Element) paramConfig.item(n);
			String paramName = param.getAttribute("name").toLowerCase();
			String value = param.getAttribute("value");
			if (paramName.equals("driver")) {
				try {
					pooledDataSource.setDriverClass(value);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			} else if (paramName.equals("url")) {
				pooledDataSource.setJdbcUrl(value);
			} else if (paramName.equals("user")) {
				pooledDataSource.setUser(value);
			} else if (paramName.equals("password")) {
				pooledDataSource.setPassword(value);
			} else if (paramName.equals("initial_size")) {
				pooledDataSource.setInitialPoolSize(Integer.parseInt(value));
			} else if (paramName.equals("max_size")) {
				pooledDataSource.setMaxPoolSize(Integer.parseInt(value));
			} else if (paramName.equals("min_size")) {
				pooledDataSource.setMinPoolSize(Integer.parseInt(value));
			} else if (paramName.equals("idle_time")) {
				pooledDataSource.setMaxIdleTime(Integer.parseInt(value));
			}
		}
		pooledDataSource.setIdleConnectionTestPeriod(60);
		pooledDataSource.setAutoCommitOnClose(true);
		pooledDataSource.setTestConnectionOnCheckout(true);
		pooledDataSource.setTestConnectionOnCheckin(true);
		pooledDataSource.setAcquireRetryAttempts(300000);
		pooledDataSource.setAcquireRetryDelay(3000);
		pooledDataSource.setAutomaticTestTable("INMEMDB_POOL_DO_NOT_DELETE");
		pooledDataSource.setAutoCommitOnClose(true);
	}

	@Deprecated
	public synchronized static Connection getConnection() {
		return getConnection(defaultSource);
	}

	@Deprecated
	public synchronized static Connection getConnection(String poolName) {
		Connection conn = null;
		try {
			DataSource datasource = dataSources.get(poolName);
			if (datasource == null) {
				Log.e("cannot get connection, no such datasource: " + poolName);
				return null;
			}
			conn = datasource.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			Log.e("SQL exception " + e.getMessage(), e);
		}
		return conn;
	}

	@Deprecated
	public synchronized static void releaseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				Log.e("release connection failed", e);
			}
		}
	}

	@Deprecated
	public synchronized static void rollbackConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				Log.e("roll back failed", e);
			}
		}
	}

}
