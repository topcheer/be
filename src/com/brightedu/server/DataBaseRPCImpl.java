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
import com.brightedu.model.edu.AgentReturnKey;
import com.brightedu.model.edu.AgentReturnType;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.Announcement;
import com.brightedu.model.edu.BankAccount;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAggregation;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.EntranceCost;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.MessageReal;
import com.brightedu.model.edu.Messages;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsDefaultKey;
import com.brightedu.model.edu.RightsFunction;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserRights;
import com.brightedu.model.edu.UserRightsEffective;
import com.brightedu.model.edu.UserType;
import com.brightedu.shared.SearchCriteria;

public class DataBaseRPCImpl extends BrightServlet implements DataBaseRPC {

	/*********************** 批次管理 ************************************/

	public List<BatchIndex> getBatchList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getBatchList(offset, limit, needTotalCounts);
	}

	// @Override
	// public boolean addBatch(String batch_name) {
	// return agent.addBatch(batch_name);
	// }

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
			boolean needTotalCounts, boolean only_can_return) {
		return agent.getRecruitAgentList(offset, limit, needTotalCounts,
				only_can_return);
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

	/******************** 通用方法 *****************************/
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
	public List getNameValuePareList(String[] beanNames) {
		return agent.getNameValuePareList(beanNames);
	}

	@Override
	public boolean setOverride(RightsCategoryFunctionKey override, User user,
			boolean addOrRemove) {
		return agent.setOverride(override, user, addOrRemove);
	}

	/************************ 入学费用设置 *********************************/
	@Override
	public List<EntranceCost> getEntranceCost(String batchID, String agentID) {
		return agent.getEntranceCost(batchID, agentID);
	}

	@Override
	public boolean saveEntranceCost(List<EntranceCost> entranceCosts) {
		return agent.saveEntranceCost(entranceCosts);
	}

	@Override
	public boolean deleteEntranceCost(EntranceCost cost) {
		return agent.deleteEntranceCost(cost);
	}

	/************************ 招生点返利设置 *********************************/

	@Override
	public AgentReturnType addAgentReturnType(AgentReturnType type) {
		return agent.addAgentReturnType(type);
	}

	@Override
	public boolean deleteAgentReturnType(AgentReturnType type) {
		return agent.deleteAgentReturnType(type);
	}

	@Override
	public boolean addAgentReturn(AgentReturnKey rtn) {
		return agent.addAgentReturn(rtn);
	}

	@Override
	public boolean deleteAgentReturn(AgentReturnKey rtn) {
		return agent.deleteAgentReturn(rtn);
	}

	@Override
	public boolean addCollegeAggregation(List<CollegeAggregation> list) {
		return agent.addCollegeAggregation(list);
	}

	@Override
	public boolean deleteCollegeAggregation(CollegeAggregation item) {
		return agent.deleteCollegeAggregation(item);
	}

	@Override
	public boolean saveCollegeAggregation(CollegeAggregation item) {
		return agent.saveCollegeAggregation(item);
	}

	@Override
	public List<College> getUnassignedCollegeList(String agentId, String batch) {
		return agent.getUnassignedCollegeList(agentId, batch);
	}

	@Override
	public List<CollegeAggregation> getCollegeAggregationList(
			AgentReturnType type) {
		return agent.getCollegeAggregationList(type);
	}

	@Override
	public List<AgentReturnType> getAgentReturnType(String agentID, String batch) {
		return agent.getAgentReturnType(agentID, batch);
	}

	@Override
	public boolean checkIfLastCollegeAggregation(CollegeAggregation item) {
		return agent.checkIfLastCollegeAggregation(item);
	}

	@Override
	public boolean addModel(Serializable model) {
		return agent.addModel(model);
	}

	@Override
	public boolean deleteModel(String modelName, String id_field_name,
			List<Integer> modelIds) {
		return agent.deleteModel(modelName, id_field_name, modelIds);
	}

	@Override
	public boolean saveModel(Serializable model) {
		return agent.saveModel(model);
	}

	@Override
	public boolean modelAction(Serializable[] model, String methodName) {
		return agent.modelAction(model, methodName);
	}

	@Override
	public List getStudentList(int offset, int limit, boolean needTotalCounts) {
		return agent.getStudentList(offset, limit, needTotalCounts);
	}

	/************************** 站内用户短信 ******************************/
	@Override
	public boolean sendMessage(List<Messages> messages) {
		return agent.sendMessage(messages);
	}

	@Override
	public List<MessageReal> readMessage(User user) {
		return agent.readMessage(user);
	}

	@Override
	public boolean checkNewMessages(User user) {
		return agent.checkNewMessages(user);
	}

	@Override
	public boolean markAsRead(MessageReal message) {
		return agent.markAsRead(message);
	}

	/************************** 通知通告管理 ******************************/
	@Override
	public boolean addAnnouncement(Announcement ann) {
		return agent.addAnnouncement(ann);
	}

	@Override
	public boolean deleteAnnouncement(List<Integer> annIDs) {
		return agent.deleteAnnouncement(annIDs);
	}

	@Override
	public boolean saveAnnouncement(Announcement ann) {
		return agent.saveAnnouncement(ann);
	}

	@Override
	public List<Announcement> getAnnouncementList(int offset, int limit,
			boolean needTotalCounts) {
		return agent.getAnnouncementList(offset, limit, needTotalCounts);

	}

	@Override
	public boolean addStudent(StudentInfo stu, List<StudentPicture> pictures) {
		return agent.addStudent(stu, pictures);
	}

	@Override
	public List<StudentPicture> getPictures(int student_id) {
		return agent.getPictures(student_id);
	}

	@Override
	public boolean saveStudent(StudentInfo stu, List<StudentPicture> pictures) {
		return agent.saveStudent(stu, pictures);
	}

	@Override
	public boolean deleteStudent(List<StudentInfo> studentIds) {
		return agent.deleteStudent(studentIds);
	}
	/**************************学生打款帐号管理******************************/
	@Override
	public boolean addBankAccount(BankAccount ba) {
		return agent.addBankAccount(ba);
	}

	@Override
	public boolean deleteBankAccount(BankAccount ba) {
		return agent.deleteBankAccount(ba);
	}

	@Override
	public boolean saveBankAccount(BankAccount ba) {
		return agent.saveBankAccount(ba);
	}

	@Override
	public List<BankAccount> getBankAccountList(Integer batchId,int offset, int limit,
			boolean needTotalCounts) {
		return agent.getBankAccountList(batchId,offset, limit, needTotalCounts);
	}

	@Override
	public BankAccount getBankAccount(BankAccount ba) {
		return agent.getBankAccount(ba);
				
	}

	@Override
	public boolean initBankAccount(Integer batchId) {
		return agent.initBankAccount(batchId);
	}


	@Override
	public List getModels(String modelName, SearchCriteria[] searchCriteria,
			int offset, int limit, boolean needTotalCounts) {

		return agent.getModels(modelName, searchCriteria, offset, limit,
				needTotalCounts);
	}

}
