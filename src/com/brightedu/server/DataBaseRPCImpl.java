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

import java.lang.reflect.Proxy;
import java.util.List;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataBaseRPCImpl extends RemoteServiceServlet implements
		DataBaseRPC {

	DataBaseRPC agent;

	public DataBaseRPCImpl() {
		DataBaseRPCAgent agt = new DataBaseRPCAgent();
		DataBaseRPCHandler handler = new DataBaseRPCHandler(agt);
		agent = (DataBaseRPC) Proxy.newProxyInstance(agt.getClass()
				.getClassLoader(), agt.getClass().getInterfaces(), handler);
	}

	/*********************** 批次管理 ************************************/

	public List<BatchIndex> getBatchList(int offset, int limit) {
		return agent.getBatchList(offset, limit);

	}

	public List getBatchListAndTotalCounts(int offset, int limit) {
		return agent.getBatchListAndTotalCounts(offset, limit);
	}

	@Override
	public boolean addBatch(String batch_name) {
		return agent.addBatch(batch_name);
	}

	@Override
	public boolean deleteBatch(List<Integer> batch_ids) {
		return agent.deleteBatch(batch_ids);
	}

	@Override
	public boolean saveBatch(BatchIndex editedBatch) {
		return agent.saveBatch(editedBatch);
	}

	/*********************** 学生层次管理 ************************************/
	@Override
	public List<StudentClassified> getStudentClassesList(int offset, int limit) {
		return agent.getStudentClassesList(offset, limit);
	}

	@Override
	public List getStudentClasseshListAndTotalCounts(int offset, int limit) {
		return agent.getStudentClasseshListAndTotalCounts(offset, limit);
	}

	@Override
	public boolean addStudentClass(String studentClassName) {
		return agent.addStudentClass(studentClassName);
	}

	@Override
	public boolean deleteStudentClasses(List<Integer> studentClassesId) {
		return agent.deleteStudentClasses(studentClassesId);
	}

	@Override
	public boolean saveStudentClasses(StudentClassified studentClass) {
		return agent.saveStudentClasses(studentClass);
	}

	/*********************** 学生类型管理 ************************************/
	@Override
	public List<StudentType> getStudentTypeList(int offset, int limit) {
		return agent.getStudentTypeList(offset, limit);
	}

	@Override
	public List getStudentTypeListAndTotalCounts(int offset, int limit) {
		return agent.getStudentTypeListAndTotalCounts(offset, limit);
	}

	@Override
	public boolean addStudentType(String studentClassName) {
		return agent.addStudentType(studentClassName);
	}

	@Override
	public boolean deleteStudentType(List<Integer> studentClassesId) {
		return agent.deleteStudentType(studentClassesId);
	}

	@Override
	public boolean saveStudentType(StudentType studentClass) {
		return agent.saveStudentType(studentClass);
	}

	
	/*********************** 合作高校代码维护 ************************************/

	@Override
	public List<College> getCollegeList(int offset, int limit) {
		return agent.getCollegeList(offset, limit);
	}

	@Override
	public List getCollegeListAndTotalCounts(int offset, int limit) {
		return agent.getCollegeListAndTotalCounts(offset, limit);

	}

	@Override
	public boolean addCollege(String collegeName) {
		return agent.addCollege(collegeName);
	}

	@Override
	public boolean deleteCollege(List<Integer> college_ids) {
		return agent.deleteCollege(college_ids);
	}

	@Override
	public boolean saveCollege(College college) {
		return agent.saveCollege(college);
	}

	/*********************** 专业代码维护 ************************************/
	@Override
	public List<Subjects> getSubjectsList(int offset, int limit) {
		return agent.getSubjectsList(offset, limit);
	}

	@Override
	public List getSubjectsListAndTotalCounts(int offset, int limit) {
		return agent.getSubjectsListAndTotalCounts(offset, limit);
	}

	@Override
	public boolean addSubject(String subjectName) {
		return agent.addSubject(subjectName);
	}

	@Override
	public boolean deleteSubject(List<Integer> subject_ids) {
		return agent.deleteSubject(subject_ids);
	}

	@Override
	public boolean saveSubject(Subjects subject) {
		return agent.saveSubject(subject);
	}
	
	/**************************获取agent列表************************************/

	public List<RecruitAgent> getRecruitAgentList(int offset, int limit){
		return agent.getRecruitAgentList(offset,limit);
	}
}
