package com.brightedu.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.brightedu.client.GreetingService;
import com.brightedu.dao.edu.UserMapper;
import com.brightedu.dao.edu.UserRightsEffectiveMapper;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserExample;
import com.brightedu.model.edu.UserRightsEffective;
import com.brightedu.model.edu.UserRightsEffectiveExample;
import com.brightedu.server.util.AuthManager;
import com.brightedu.server.util.ConnectionManager;
import com.brightedu.server.util.Log;
import com.brightedu.server.util.ServerProperties;
import com.brightedu.server.util.Utils;
import com.brightedu.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	public Serializable[] login(User user) {
		String userRight = null;
		if (user.getUser_name() == null) {
			user.setUser_name("admin");
			user.setUser_id(1);
			userRight = AuthManager.getFunctions(new String[] {
					"system_manage", "student_manage", "financial_manage",
					"student_personal" });
		}
		User loginedUser = logmein(user);
		Log.d("User login: " + user.getUser_name());
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute("user", loginedUser);
		if (userRight == null) {
			userRight = getUserRights(loginedUser);
		}

		return new Serializable[] { loginedUser, userRight };

	}

	private User logmein(User user) {

		SqlSession session = ConnectionManager.getSessionFactory()
				.openSession();
		try {
			UserMapper scm = session.getMapper(UserMapper.class);
			UserExample ex = new UserExample();
			ex.createCriteria().andUser_nameEqualTo(user.getUser_name());
			List<User> users = scm.selectByExample(ex);
			if (users.size() > 0) {

				return users.get(0);
			}

			return null;

		} finally {
			session.close();

		}

	}

	private String getUserRights(User user) {
		StringBuffer rights = new StringBuffer();
		SqlSession session = ConnectionManager.getSessionFactory()
				.openSession();
		Connection conn = session.getConnection();
		try {
			PreparedStatement ps;
			try {
				ps = conn
						.prepareStatement("select distinct category_id,category_name from userrights_effective where user_id = ?");

				ps.setInt(1, user.getUser_id());

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					rights.append(rs.getString(1)).append(" ")
							.append(rs.getString(2)).append(" root|");
				}

				rs.close();
				ps.close();
			} catch (SQLException e) {
				Log.e("", e);
			}
			UserRightsEffectiveMapper scm = session
					.getMapper(UserRightsEffectiveMapper.class);
			UserRightsEffectiveExample ex = new UserRightsEffectiveExample();
			ex.createCriteria().andUser_idEqualTo(user.getUser_id());

			for (UserRightsEffective right : scm.selectByExample(ex)) {
				rights.append(right.getFunction_id()).append(" ")
						.append(right.getFunction_name()).append(" ")
						.append(right.getCategory_id()).append("|");
			}

			return rights.toString();

		} finally {
			session.close();

		}
	}

}
