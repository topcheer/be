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
package com.brightedu.client;

import java.io.Serializable;
import java.util.List;

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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DataBaseRPC")
public interface DataBaseRPC extends RemoteService {

	/*********************** 批次管理 ************************************/
	public List<BatchIndex> getBatchList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteBatch(List<Integer> batch_ids);

	boolean saveBatch(BatchIndex editedBatch);

	/*********************** 学生层次管理 ************************************/
	public List<StudentClassified> getStudentClassesList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteStudentClasses(List<Integer> studentClassesId);

	boolean saveStudentClasses(StudentClassified studentClass);

	/*********************** 学生层次管理 ************************************/
	public List<StudentType> getStudentTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteStudentType(List<Integer> studentClassesId);

	boolean saveStudentType(StudentType studentType);

	/*********************** 合作高校代码维护 ************************************/
	public List<College> getCollegeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteCollege(List<Integer> college_ids);

	boolean saveCollege(College college);
	
	/*********************** 合作高校协议维护 ************************************/
	public List<CollegeAgreement> getCollegeAgreementList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteCollegeAgreement(List<CollegeAgreement> agreements);

	boolean saveCollegeAgreement(CollegeAgreement agreement);

	/*********************** 专业代码维护 ************************************/
	public List<Subjects> getSubjectsList(int offset, int limit,
			boolean needTotalCounts);


	public boolean deleteSubject(List<Integer> subject_ids);

	boolean saveSubject(Subjects subject);

	/*********************** 机构类型维护 ************************************/

	public List<AgentType> getAgentTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deleteAgentType(List<Integer> agentType_ids);

	public boolean saveAgentType(AgentType agenttype);

	/*********************** 费用类型维护 ************************************/
	public List<FeeType> getFeeTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deletFeeType(List<Integer> feeType_ids);

	public boolean saveFeeType(FeeType agenttype);

	/*********************** 入账类型维护 ************************************/
	public List<ChargeType> getChargeTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deletChargeType(List<Integer> chargeType_ids);

	public boolean saveChargeType(ChargeType chargetype);

	/*********************** 用户类型维护 ************************************/
	public List<UserType> getUserTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deletUserType(List<Integer> userType_ids);

	public boolean saveUserType(UserType usertype);

	/*********************** 照片类型维护 ************************************/
	public List<PictureType> getPictureTypeList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deletPictureType(List<Integer> pictureType_ids);

	public boolean savePictureType(PictureType picturetype);

	/*********************** 学生状态类型维护 ************************************/
	public List<StudentStatus> getStudentStatusList(int offset, int limit,
			boolean needTotalCounts);

	public boolean deletStudentStatus(List<Integer> studentStatus_ids);

	public boolean saveStudentStatus(StudentStatus studentStatus);

	/*********************** RecruitAgent维护 ************************************/
	public List<RecruitAgent> getRecruitAgentList(int offset, int limit,
			boolean needTotalCounts,boolean only_can_return);

	public boolean deletRecruitAgent(List<Integer> agent_ids);

	public boolean saveRecruitAgent(RecruitAgent agent);

	/*********************** 招生计划维护 ************************************/
	public List<CollegeSubjectView> getCollegeSubjectList(int college, int level,
			int batch);

	public boolean addCollegeSubject(List<CollegeSubject> collegeSubjects);

	public boolean deletCollegeSubject(CollegeSubject collegeSubjects);
	
	public List<RecruitPlan> getRecruitPlanList(int batch);
	
	/*********************** 当前批次设置 ************************************/
	public boolean addOrUpdateCurrentBatch(Integer batchNo);
	public Integer getCurrentBatch();

	

	/************************ 权限基础数据设置 *********************************/
	
	public List<RightsCategory> getRightsCategory();
	public List<RightsFunction> getRightsFunction();
	public List<RightsCategoryFunctionKey> getRightsCategoryFunction(String categoryID);
	public boolean addRightsCatetoryFunctions(List<RightsCategoryFunctionKey> rightsCategoryFunctionList);
	public boolean deleteRightsCategory(RightsCategory category);
	public boolean deleteRightsFunction(List<String> function);
	public boolean deleteRightsCatetoryFunctions(String rightsCategoryFunctionList);
	public List<RightsDefaultKey> getRightsDefault(String user_typ_Id);
	public boolean addRightsDefault(List<RightsDefaultKey> rightDefaultList);
	public boolean deleteRightsDefault(Integer userType_id);
	
	/************************ 用户管理 *********************************/
	public List<User> getUserList(int offset, int limit,boolean needTotalCounts);
	public boolean deletUser(List<Integer> user_ids);
	public boolean saveUser(User user);
	public boolean setOverridePriv(List<RightsCategoryFunctionKey> list, boolean addOrRemove);
	public List<UserRights> getUserRights(User user);
	public boolean setOverride(RightsCategoryFunctionKey override, User user, boolean addOrRemove);
	
	public List<UserRightsEffective> getUserRightsEffective(User user);
	
	/*******************通用方法***************************/
	public Serializable getObjectById(String mapperClassName, int id);
	public List getNameValuePareList(String[] beanNames); 
	boolean addModel(Serializable model);
	boolean deleteModel(String modelName, String id_field_name,
			List<Integer> modelIds);
	boolean saveModel(Serializable model);
	boolean modelAction(Serializable[] model, String methodName);
	List getModels(String modelName, SearchCriteria[] searchCriteria,
			int offset, int limit, boolean needTotalCounts);
	
	/************************ 入学费用设置 *********************************/
	public List<EntranceCost> getEntranceCost(String batchID,String agentID);
	public boolean saveEntranceCost(List<EntranceCost> entranceCosts);
	public boolean deleteEntranceCost(EntranceCost cost);

	/************************ 招生点返利设置 *********************************/
	public AgentReturnType addAgentReturnType(AgentReturnType type);
	public boolean deleteAgentReturnType(AgentReturnType type);
	public boolean addAgentReturn(AgentReturnKey rtn);
	public boolean deleteAgentReturn(AgentReturnKey rtn);
	public boolean addCollegeAggregation(List<CollegeAggregation> list);
	public boolean deleteCollegeAggregation(CollegeAggregation item);
	public boolean saveCollegeAggregation(CollegeAggregation item);

	public List<College> getUnassignedCollegeList(String agentID, String batch);
	public List<CollegeAggregation> getCollegeAggregationList(AgentReturnType type);
	public List<AgentReturnType> getAgentReturnType(String agentID, String batch);
	public boolean checkIfLastCollegeAggregation(CollegeAggregation item);



	/**************************学生管理********************************/
	List getStudentList(SearchCriteria[] searchCriteria,int offset, int limit, boolean needTotalCounts);
	boolean addStudent(StudentInfo stu, List<StudentPicture> pictures);
	List<StudentPicture> getPictures(int student_id);
	boolean saveStudent(StudentInfo stu, List<StudentPicture> pictures);
	boolean deleteStudent(List<StudentInfo> studentIds);
	boolean addStudents(List<StudentInfo> stus);  //批量
	
	/**************************站内用户短信******************************/
	
	public boolean sendMessage(List<Messages> messages);
	public List<MessageReal> readMessage(User user);
	public boolean checkNewMessages(User user);
	public boolean markAsRead(MessageReal message);
	
	/**************************通知通告管理******************************/
	public boolean addAnnouncement(Announcement ann);
	public boolean deleteAnnouncement(List<Integer> annIDs);
	public boolean saveAnnouncement(Announcement ann);
	public List<Announcement> getAnnouncementList(int offset, int limit,
			boolean needTotalCounts);


	/**************************学生打款帐号管理******************************/
	public boolean addBankAccount(BankAccount ba);
	public boolean deleteBankAccount(BankAccount ba);
	public boolean saveBankAccount(BankAccount ba);
	public BankAccount getBankAccount(BankAccount ba);
	public List<BankAccount> getBankAccountList(Integer batchId, int offset, int limit,
			boolean needTotalCounts);
	public boolean initBankAccount(Integer batchId);

}
