package com.brightedu.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConnectionManager {


	@Test
	public void test() {
		

		ConnectionManager.load();
		try {
			Connection conn = ConnectionManager.getConnection("edu");
			System.out.print(conn.getAutoCommit());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
