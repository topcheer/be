package com.brightedu.dao.edu;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeExample;

public class TestAgreementDAO {

	@Test
	public void test() throws IOException {
		
		
		String resource = "com/brightedu/MapperConfig.xml"; 
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader); 
		SqlSession session = sessionFactory.openSession(); 
		
		CollegeMapper cm = session.getMapper(CollegeMapper.class);
		
		CollegeExample ce = new CollegeExample();
		ce.createCriteria().andCollege_idIsNotNull();
		List<College> colleges = cm.selectByExample(ce);
		System.out.println(colleges.size());
		
	
//		College co = new College();
//		co.setCollege_name("Test Coll");
//		co.setRegister_date(new Date(System.currentTimeMillis()));
//		cm.insertSelective(co);
//		
//		session.commit();
//		session.close();
//		ConnectionManager.load();
//		try {
//			Connection conn = ConnectionManager.getConnection("edu");
//			System.out.print(conn.getAutoCommit());
//			PreparedStatement ps = conn.prepareStatement("insert into edu.co_college (college_name) values (?)");
//			ps.setString(1, "Test Col");
//			ps.execute();
//			ps.close();	
//			conn.commit();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
				
		
	
	}

}
