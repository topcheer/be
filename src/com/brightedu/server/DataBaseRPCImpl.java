/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brightedu.server;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.dao.edu.BatchIndexMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.brightedu.model.edu.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataBaseRPCImpl extends RemoteServiceServlet implements DataBaseRPC {
	String resource = "com/brightedu/MapperConfig.xml"; 
	Reader reader = null;

	SqlSessionFactory sessionFactory ;
	SqlSession session;
	public DataBaseRPCImpl()
	{
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionFactory = new SqlSessionFactoryBuilder().build(reader); 
		session = sessionFactory.openSession(); 	
	}
	public List<BatchIndex> getBatchList()
	{

		BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
		return bim.selectByExample(null);
		

	}

	@Override
	public boolean addBatch(String batch_name) {
		
		BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
		BatchIndex bi = new BatchIndex();
		bi.setBatch_name(batch_name);
		int count = bim.insertSelective(bi);
			session.commit();
			return true;			

		
	}

	@Override
	public boolean deleteBatch(Integer batch_id) {
		BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
		int count = bim.deleteByPrimaryKey(batch_id);
		

		session.commit();
		return true;
			
		
		
	}
	
}
