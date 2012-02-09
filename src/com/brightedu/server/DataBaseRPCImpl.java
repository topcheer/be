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

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.dao.edu.BatchIndexMapper;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.BatchIndexExample;
import com.brightedu.model.edu.BatchIndexExample.Criteria;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.server.util.ConnectionManager;
import com.brightedu.server.util.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataBaseRPCImpl extends RemoteServiceServlet implements
		DataBaseRPC {

	SqlSessionFactory sessionFactory;

	public DataBaseRPCImpl() {
		sessionFactory = ConnectionManager.getSessionFactory();
	}

	/*********************** 批次管理 ************************************/

	public List<BatchIndex> getBatchList(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexExample ex = new BatchIndexExample();
			ex.setOrderByClause("batch_id");
			List<BatchIndex> result = session.selectList(
					"com.brightedu.dao.edu.BatchIndexMapper.selectByExample",
					ex, new RowBounds(offset, limit));
			return result;
		} finally {
			session.close();
		}

	}

	public List getBatchListAndTotalCounts(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			List batchList = getBatchList(offset, limit);
			// 避免影响缓存
			List result = new ArrayList(batchList);
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			Integer counts = bim.countByExample(null);
			result.add(counts);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean addBatch(String batch_name) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			BatchIndex bi = new BatchIndex();
			bi.setBatch_name(batch_name);
			int count = bim.insertSelective(bi);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteBatch(List<Integer> batch_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			// int count = bim.deleteByPrimaryKey(batch_id);
			BatchIndexExample ex = new BatchIndexExample();
			Criteria cr = ex.createCriteria();
			cr.andBatch_idIn(batch_ids);
			bim.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveBatch(BatchIndex editedBatch) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			bim.updateByPrimaryKey(editedBatch);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public List<StudentClassified> getStudentClassesList(int offset, int limit) {
		// BatchIndexExample ex = new BatchIndexExample();
		// ex.setOrderByClause("batch_id");
		// List<BatchIndex> result = session.selectList(
		// "com.brightedu.dao.edu.BatchIndexMapper.selectByExample", ex,
		// new RowBounds(offset, limit));
		// return result;
		return null;

	}

	/*********************** 学生类别管理 ************************************/
	@Override
	public List getStudentClasseshListAndTotalCounts(int offset, int limit) {

		return null;
	}

	@Override
	public boolean addStudentClass(String studentClassName) {

		return true;
	}

	@Override
	public boolean deleteStudentClasses(List<Integer> studentClassesId) {

		return true;
	}

	@Override
	public boolean saveStudentClasses(StudentClassified studentClass) {

		return true;
	}

}
