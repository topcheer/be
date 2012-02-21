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

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsDefaultKey;
import com.brightedu.model.edu.RightsFunction;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserRights;
import com.brightedu.model.edu.UserRightsEffective;
import com.brightedu.model.edu.UserType;

public class DataBaseRPCImpl extends BrightServlet implements DataBaseRPC {

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
	public boolean addStudentClass(StudentClassified studentClass) {
		return agent.addStudentClass(studentClass);
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
	public boolean addFeeType(FeeType feeType) {
		return agent.addFeeType(feeType);
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
	public List<CollegeSubjectView> getCollegeSubjectList(int college,
			int level, int batch) {
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

	@Override
	public List<RecruitPlan> getRecruitPlanList(int batch) {
		return agent.getRecruitPlanList(batch);
	}

	/************************** RecruitAgent维护 ************************************/

	public List<RecruitAgent> getRecruitAgentList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getRecruitAgentList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addRecruitAgent(RecruitAgent ra) {
		return agent.addRecruitAgent(ra);
	}

	@Override
	public boolean deletRecruitAgent(List<Integer> agent_ids) {
		return agent.deletRecruitAgent(agent_ids);
	}

	@Override
	public boolean saveRecruitAgent(RecruitAgent ra) {
		return agent.saveRecruitAgent(ra);
	}

	/*********************** 当前批次设置 ************************************/

	public boolean addOrUpdateCurrentBatch(Integer batchNo) {
		return agent.addOrUpdateCurrentBatch(batchNo);
	}

	@Override
	public Integer getCurrentBatch() {

		return agent.getCurrentBatch();
	}

	@Override
	public List<CollegeAgreement> getCollegeAgreementList(int offset,
			int limit, boolean needTotalCounts) {
		return agent.getCollegeAgreementList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addCollegeAgreement(CollegeAgreement agreement) {
		return agent.addCollegeAgreement(agreement);
	}

	@Override
	public boolean deleteCollegeAgreement(List<CollegeAgreement> agreements) {
		return agent.deleteCollegeAgreement(agreements);
	}

	@Override
	public boolean saveCollegeAgreement(CollegeAgreement agreement) {
		return agent.saveCollegeAgreement(agreement);
	}


	/************************ 权限基础数据设置 *********************************/
	
	@Override
	public List<RightsCategory> getRightsCategory() {
		return agent.getRightsCategory();
	}

	


	@Override
	public List<RightsFunction> getRightsFunction() {
		return agent.getRightsFunction();
	}

	@Override
	public List<RightsCategoryFunctionKey> getRightsCategoryFunction(
			String categoryID) {
		return agent.getRightsCategoryFunction(categoryID);
	}

	@Override
	public boolean addRightsCategory(RightsCategory category) {
		return agent.addRightsCategory(category);
	}

	@Override
	public boolean addRightsFunction(RightsFunction function) {
		return agent.addRightsFunction(function);
	}

	@Override
	public boolean addRightsCatetoryFunctions(
			List<RightsCategoryFunctionKey> rightsCategoryFunctionList) {
		return agent.addRightsCatetoryFunctions(rightsCategoryFunctionList);
	}

	@Override
	public boolean deleteRightsCategory(RightsCategory category) {
		return agent.deleteRightsCategory(category);
	}

	@Override
	public boolean deleteRightsFunction(List<String> function) {
		return agent.deleteRightsFunction(function);
	}

	@Override
	public boolean deleteRightsCatetoryFunctions(
			String rightsCategoryFunctionList) {
		return agent.deleteRightsCatetoryFunctions(rightsCategoryFunctionList);
	}
	@Override
	public List<RightsDefaultKey> getRightsDefault(String user_typ_Id) {
		return agent.getRightsDefault(user_typ_Id);
	}

	@Override
	public boolean addRightsDefault(List<RightsDefaultKey> rightDefaultList) {
		return agent.addRightsDefault(rightDefaultList);
	}
	

	@Override
	public boolean deleteRightsDefault(Integer userType_id) {
		return agent.deleteRightsDefault(userType_id);
	}
	/********************通用方法*****************************/
	@Override
	public Serializable getObjectById(String mapperClassName, int id) {
		return agent.getObjectById(mapperClassName, id);
	}

	/************************ 用户管理 *********************************/
	
	@Override
	public List<User> getUserList(int offset, int limit, boolean needTotalCounts) {
		return agent.getUserList(offset, limit, needTotalCounts);
	}

	@Override
	public boolean addUser(User user) {
		return agent.addUser(user);
	}

	@Override
	public boolean deletUser(List<Integer> user_ids) {
		return agent.deletUser(user_ids);
	}

	@Override
	public boolean saveUser(User user) {
		return agent.saveUser(user);
	}

	@Override
	public boolean setOverridePriv(List<RightsCategoryFunctionKey> list,
			boolean addOrRemove) {
		return agent.setOverridePriv(list, addOrRemove);
	}

	@Override
	public List<UserRights> getUserRights(User user) {
		return agent.getUserRights(user);
	}

	@Override
	public List<UserRightsEffective> getUserRightsEffective(User user) {
		return agent.getUserRightsEffective(user);
	}

	@Override
	public boolean setOverride(RightsCategoryFunctionKey override, User user,
			boolean addOrRemove) {
		return agent.setOverride(override, user, addOrRemove);
	}




}
