package com.brightedu.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.brightedu.server.util.ConnectionManager;
import com.brightedu.shared.IDCard;

public class TestConnectionManager {

	@Test
	public void test() {

		System.out.println(IDCard.verify("320623197512156257"));
//		ConnectionManager.load();
		// try {
		// Connection conn = ConnectionManager.getConnection("edu");
		// System.out.print(conn.getAutoCommit());
		// conn.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
