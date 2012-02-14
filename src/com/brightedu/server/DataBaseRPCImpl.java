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

import javax.servlet.http.HttpSession;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.brightedu.model.edu.UserType;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataBaseRPCImpl extends RemoteServiceServlet implements
		DataBaseRPC {

	DataBaseRPC agent;

	public DataBaseRPCImpl() {
		DataBaseRPCAgent agt = new DataBaseRPCAgent();
		agt.setRemoteServlet(this);
		DataBaseRPCHandler handler = new DataBaseRPCHandler(agt);
		agent = (DataBaseRPC) Proxy.newProxyInstance(agt.getClass()
				.getClassLoader(), agt.getClass().getInterfaces(), handler);

	}

	public HttpSession getSession() {
		return this.getThreadLocalRequest().getSession();
	}

	/*********************** 批次管理 ************************************/

	public List<BatchIndex> getBatchList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getBatchList(offset, limit, needTotalCounts);
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
	public List<StudentClassified> getStudentClassesList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getStudentClassesList(offset, limit, needTotalCounts);
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
	public List<StudentType> getStudentTypeList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getStudentTypeList(offset, limit, needTotalCounts);
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
	public List<College> getCollegeList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getCollegeList(offset, limit, needTotalCounts);
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
	public List<Subjects> getSubjectsList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getSubjectsList(offset, limit, needTotalCounts);
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

	/*********************** 机构类型维护 ************************************/
	@Override
	public List<AgentType> getAgentTypeList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getAgentTypeList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addAgentType(AgentType typeName) {
		return agent.addAgentType(typeName);
	}

	@Override
	public boolean deleteAgentType(List<Integer> agentType_ids) {
		return agent.deleteAgentType(agentType_ids);
	}

	@Override
	public boolean saveAgentType(AgentType agenttype) {
		return agent.saveAgentType(agenttype);
	}

	/*********************** 费用类型维护 ************************************/
	@Override
	public List<FeeType> getFeeTypeList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getFeeTypeList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addFeeType(String typeName) {
		return agent.addFeeType(typeName);
	}

	@Override
	public boolean deletFeeType(List<Integer> feeType_ids) {
		return agent.deletFeeType(feeType_ids);
	}

	@Override
	public boolean saveFeeType(FeeType agenttype) {
		return agent.saveFeeType(agenttype);
	}

	/*********************** 入账类型维护 ************************************/
	@Override
	public List<ChargeType> getChargeTypeList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getChargeTypeList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addChargeType(String typeName) {
		return agent.addChargeType(typeName);
	}

	@Override
	public boolean deletChargeType(List<Integer> chargeType_ids) {
		return agent.deletChargeType(chargeType_ids);
	}

	@Override
	public boolean saveChargeType(ChargeType chargetype) {
		return agent.saveChargeType(chargetype);
	}

	/*********************** 用户类型维护 ************************************/
	@Override
	public List<UserType> getUserTypeList(int offset, int limit,
			boolean needTotaoCounts) {
		return agent.getUserTypeList(offset, limit, needTotaoCounts);
	}

	@Override
	public boolean addUserType(String typeName) {
		return agent.addUserType(typeName);
	}

	@Override
	public boolean deletUserType(List<Integer> UserType_ids) {
		return agent.deletUserType(UserType_ids);
	}

	@Override
	public boolean saveUserType(UserType Usertype) {
		return agent.saveUserType(Usertype);
	}

	/*********************** 照片类型维护 ************************************/
	@Override
	public List<PictureType> getPictureTypeList(int offset, int limit,
			boolean needTotaoCounts) {
		return agent.getPictureTypeList(offset, limit, needTotaoCounts);
	}

	@Override
	public boolean addPictureType(String typeName) {
		return agent.addPictureType(typeName);
	}

	@Override
	public boolean deletPictureType(List<Integer> PictureType_ids) {
		return agent.deletPictureType(PictureType_ids);
	}

	@Override
	public boolean savePictureType(PictureType Picturetype) {
		return agent.savePictureType(Picturetype);
	}

	/*********************** 学生状态类型维护 ************************************/
	@Override
	public List<StudentStatus> getStudentStatusList(int offset, int limit,
			boolean needTotaoCounts) {
		return agent.getStudentStatusList(offset, limit, needTotaoCounts);
	}

	@Override
	public boolean addStudentStatus(String typeName) {
		return agent.addStudentStatus(typeName);
	}

	@Override
	public boolean deletStudentStatus(List<Integer> StudentStatus_ids) {
		return agent.deletStudentStatus(StudentStatus_ids);
	}

	@Override
	public boolean saveStudentStatus(StudentStatus studentStatus) {
		return agent.saveStudentStatus(studentStatus);
	}
	

	/*********************** 招生计划维护 ************************************/
	@Override
	public List<CollegeSubject> getCollegeSubjectList(int college, int level, int batch) {
		return agent.getCollegeSubjectList(college, level, batch);
	}

	@Override
	public boolean addCollegeSubject(List<CollegeSubject> collegeSubjects) {
		return agent.addCollegeSubject(collegeSubjects);
	}

	@Override
	public boolean deletCollegeSubject(CollegeSubject collegeSubjects) {
		return agent.deletCollegeSubject(collegeSubjects);
	}

	
	
	/************************** 获取agent列表 ************************************/

	public List<RecruitAgent> getRecruitAgentList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getRecruitAgentList(offset, limit, needTotalCounts);
	}
}
