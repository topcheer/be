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

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.dao.edu.BatchIndexMapper;
import com.brightedu.dao.edu.CollegeMapper;
import com.brightedu.dao.edu.StudentClassifiedMapper;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.BatchIndexExample;
import com.brightedu.model.edu.BatchIndexExample.Criteria;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeExample;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentClassifiedExample;
import com.brightedu.server.util.ConnectionManager;
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
			BatchIndexExample ex = new BatchIndexExample();
			ex.setOrderByClause("batch_id");
			List batchList = session.selectList(
					"com.brightedu.dao.edu.BatchIndexMapper.selectByExample",
					ex, new RowBounds(offset, limit));
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			Integer counts = bim.countByExample(null);
			batchList.add(counts);
			return batchList;
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

	/*********************** 学生类别管理 ************************************/
	@Override
	public List<StudentClassified> getStudentClassesList(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedExample ex = new StudentClassifiedExample();
			ex.setOrderByClause("classified_id");
			List<StudentClassified> result = session
					.selectList(
							"com.brightedu.dao.edu.StudentClassifiedMapper.selectByExample",
							ex, new RowBounds(offset, limit));
			return result;
		} finally {
			session.close();
		}

	}

	@Override
	public List getStudentClasseshListAndTotalCounts(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedExample ex = new StudentClassifiedExample();
			ex.setOrderByClause("classified_id");
			List result = session
					.selectList(
							"com.brightedu.dao.edu.StudentClassifiedMapper.selectByExample",
							ex, new RowBounds(offset, limit));
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			Integer counts = scm.countByExample(null);
			result.add(counts);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean addStudentClass(String studentClassName) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			StudentClassified sc = new StudentClassified();
			sc.setClassified_name(studentClassName);
			int count = scm.insertSelective(sc);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteStudentClasses(List<Integer> studentClassesId) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			StudentClassifiedExample ex = new StudentClassifiedExample();
			StudentClassifiedExample.Criteria cr = ex.createCriteria();
			cr.andClassified_idIn(studentClassesId);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveStudentClasses(StudentClassified studentClass) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			scm.updateByPrimaryKey(studentClass);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 合作高校代码维护 ************************************/

	@Override
	public List<College> getCollegeList(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeExample ex = new CollegeExample();
			ex.setOrderByClause("college_id");
			List<College> result = session.selectList(
					"com.brightedu.dao.edu.CollegeMapper.selectByExample", ex,
					new RowBounds(offset, limit));
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public List getCollegeListAndTotalCounts(int offset, int limit) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeExample ex = new CollegeExample();
			ex.setOrderByClause("college_id");
			List result = session.selectList(
					"com.brightedu.dao.edu.CollegeMapper.selectByExample", ex,
					new RowBounds(offset, limit));
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			Integer counts = cm.countByExample(null);
			result.add(counts);
			return result;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean addCollege(String collegeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			College co = new College();
			co.setCollege_name(collegeName);
			int count = cm.insertSelective(co);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteCollege(List<Integer> college_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			CollegeExample ex = new CollegeExample();
			CollegeExample.Criteria cr = ex.createCriteria();
			cr.andCollege_idIn(college_ids);
			cm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveCollege(College college) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session
					.getMapper(CollegeMapper.class);
			cm.updateByPrimaryKey(college);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

}
