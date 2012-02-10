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

import com.brightedu.client.ds.Page;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.BatchIndexExample;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeExample;
import com.brightedu.model.edu.School;
import com.brightedu.model.edu.SchoolExample;

public class TestAgreementDAO {

	@Test
	public void test() throws IOException {
		
		
		String resource = "com/brightedu/MapperConfig.xml"; 
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader); 
		SqlSession session = sessionFactory.openSession(); 
		
		SchoolMapper cm = session.getMapper(SchoolMapper.class);
		
		SchoolExample ce = new SchoolExample();
		Page page = new Page(10, 10);
		ce.setPage(page);
		
		page.setCount(cm.countByExample(ce));
		List<School> colleges = cm.selectByExample(ce);
		System.out.println(page.getCurrent());
		System.out.println(page.getTotal());
		System.out.println(colleges.size());

		session.close();
	
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
	public static void main(String[] args) throws IOException
	{
		new TestAgreementDAO().test();
	}

}
