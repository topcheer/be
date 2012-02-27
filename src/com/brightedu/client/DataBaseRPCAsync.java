package com.brightedu.client;

import java.io.Serializable;
import java.util.List;

import com.brightedu.model.edu.AgentReturnKey;
import com.brightedu.model.edu.AgentReturnType;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAggregation;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.EntranceCost;
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
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataBaseRPCAsync {

	void getBatchList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<BatchIndex>> callback);

	// void addBatch(String batch_name, AsyncCallback<Boolean> callback);

	void deleteBatch(List<Integer> batch_ids, AsyncCallback<Boolean> callback);

	void saveBatch(BatchIndex editedBatch, AsyncCallback<Boolean> callback);

	void getStudentClassesList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<StudentClassified>> callback);

	void deleteStudentClasses(List<Integer> studentClassesId,
			AsyncCallback<Boolean> callback);

	void saveStudentClasses(StudentClassified studentClass,
			AsyncCallback<Boolean> callback);

	void getCollegeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<College>> callback);

	void deleteCollege(List<Integer> college_ids,
			AsyncCallback<Boolean> callback);

	void saveCollege(College college, AsyncCallback<Boolean> callback);

	void deleteSubject(List<Integer> subject_ids,
			AsyncCallback<Boolean> callback);

	void getSubjectsList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<Subjects>> callback);

	void saveSubject(Subjects subject, AsyncCallback<Boolean> callback);

	void getRecruitAgentList(int offset, int limit, boolean needTotalCounts,
			boolean only_can_return, AsyncCallback<List<RecruitAgent>> callback);

	void getStudentTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<StudentType>> callback);

	void deleteStudentType(List<Integer> studentClassesId,
			AsyncCallback<Boolean> callback);

	void saveStudentType(StudentType studentType,
			AsyncCallback<Boolean> callback);

	void getAgentTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<AgentType>> callback);

	void deleteAgentType(List<Integer> agentType_ids,
			AsyncCallback<Boolean> callback);

	void saveAgentType(AgentType agenttype, AsyncCallback<Boolean> callback);

	void getFeeTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<FeeType>> callback);

	void deletFeeType(List<Integer> feeType_ids, AsyncCallback<Boolean> callback);

	void saveFeeType(FeeType agenttype, AsyncCallback<Boolean> callback);

	void getChargeTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<ChargeType>> callback);

	void deletChargeType(List<Integer> chargeType_ids,
			AsyncCallback<Boolean> callback);

	void saveChargeType(ChargeType chargetype, AsyncCallback<Boolean> callback);

	void deletUserType(List<Integer> UserType_ids,
			AsyncCallback<Boolean> callback);

	void getUserTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<UserType>> callback);

	void saveUserType(UserType Usertype, AsyncCallback<Boolean> callback);

	void deletPictureType(List<Integer> PictureType_ids,
			AsyncCallback<Boolean> callback);

	void getPictureTypeList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<PictureType>> callback);

	void savePictureType(PictureType Picturetype,
			AsyncCallback<Boolean> callback);

	void deletStudentStatus(List<Integer> StudentStatus_ids,
			AsyncCallback<Boolean> callback);

	void getStudentStatusList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<StudentStatus>> callback);

	void saveStudentStatus(StudentStatus StudentStatus,
			AsyncCallback<Boolean> callback);

	void deletRecruitAgent(List<Integer> agent_ids,
			AsyncCallback<Boolean> callback);

	void saveRecruitAgent(RecruitAgent agent, AsyncCallback<Boolean> callback);

	void getCollegeSubjectList(int college, int level, int batch,
			AsyncCallback<List<CollegeSubjectView>> callback);

	void addCollegeSubject(List<CollegeSubject> collegeSubjects,
			AsyncCallback<Boolean> callback);

	void deletCollegeSubject(CollegeSubject collegeSubjects,
			AsyncCallback<Boolean> callback);

	void addOrUpdateCurrentBatch(Integer batchNo,
			AsyncCallback<Boolean> callback);

	void saveCollegeAgreement(CollegeAgreement college,
			AsyncCallback<Boolean> callback);

	void deleteCollegeAgreement(List<CollegeAgreement> college_ids,
			AsyncCallback<Boolean> callback);

	void getCollegeAgreementList(int offset, int limit,
			boolean needTotalCounts,
			AsyncCallback<List<CollegeAgreement>> callback);

	void getCurrentBatch(AsyncCallback<Integer> callback);

	void getRecruitPlanList(int batch, AsyncCallback<List<RecruitPlan>> callback);

	void addRightsCatetoryFunctions(
			List<RightsCategoryFunctionKey> rightsCategoryFunctionList,
			AsyncCallback<Boolean> callback);

	void addRightsFunction(RightsFunction function,
			AsyncCallback<Boolean> callback);

	void getRightsCategory(AsyncCallback<List<RightsCategory>> callback);

	void getRightsCategoryFunction(String categoryID,
			AsyncCallback<List<RightsCategoryFunctionKey>> callback);

	void getRightsFunction(AsyncCallback<List<RightsFunction>> callback);

	void deleteRightsCategory(RightsCategory category,
			AsyncCallback<Boolean> callback);

	void deleteRightsCatetoryFunctions(String rightsCategoryFunctionList,
			AsyncCallback<Boolean> callback);

	void deleteRightsFunction(List<String> function,
			AsyncCallback<Boolean> callback);

	void getObjectById(String mapperClassName, int id,
			AsyncCallback<Serializable> callback);

	void getRightsDefault(String user_typ_Id,
			AsyncCallback<List<RightsDefaultKey>> callback);

	void addRightsDefault(List<RightsDefaultKey> rightDefaultList,
			AsyncCallback<Boolean> callback);

	void deleteRightsDefault(Integer userType_id,
			AsyncCallback<Boolean> callback);

	void getUserList(int offset, int limit, boolean needTotalCounts,
			AsyncCallback<List<User>> callback);

	void deletUser(List<Integer> user_ids, AsyncCallback<Boolean> callback);

	void saveUser(User user, AsyncCallback<Boolean> callback);

	void setOverridePriv(List<RightsCategoryFunctionKey> list,
			boolean addOrRemove, AsyncCallback<Boolean> callback);

	void getUserRights(User user, AsyncCallback<List<UserRights>> callback);

	void getUserRightsEffective(User user,
			AsyncCallback<List<UserRightsEffective>> callback);

	void getNameValuePareList(String[] beanNames, AsyncCallback<List> callback);

	void setOverride(RightsCategoryFunctionKey override, User user,
			boolean addOrRemove, AsyncCallback<Boolean> callback);

	void getEntranceCost(String batchID, String agentID,
			AsyncCallback<List<EntranceCost>> callback);

	void saveEntranceCost(List<EntranceCost> entranceCosts,
			AsyncCallback<Boolean> callback);

	void deleteEntranceCost(EntranceCost cost, AsyncCallback<Boolean> callback);

	void addAgentReturn(AgentReturnKey rtn, AsyncCallback<Boolean> callback);

	void addAgentReturnType(AgentReturnType type,
			AsyncCallback<AgentReturnType> callback);

	void addCollegeAggregation(List<CollegeAggregation> list,
			AsyncCallback<Boolean> callback);

	void deleteAgentReturn(AgentReturnKey rtn, AsyncCallback<Boolean> callback);

	void deleteAgentReturnType(AgentReturnType type,
			AsyncCallback<Boolean> callback);

	void deleteCollegeAggregation(CollegeAggregation item,
			AsyncCallback<Boolean> callback);

	void saveCollegeAggregation(CollegeAggregation item,
			AsyncCallback<Boolean> callback);

	void getAgentReturnType(String agent, String batch,
			AsyncCallback<List<AgentReturnType>> callback);

	void getCollegeAggregationList(AgentReturnType type,
			AsyncCallback<List<CollegeAggregation>> callback);

	void getUnassignedCollegeList(String agent, String batch,
			AsyncCallback<List<College>> callback);

	void checkIfLastCollegeAggregation(CollegeAggregation item,
			AsyncCallback<Boolean> callback);

	void addModel(Serializable model, AsyncCallback<Boolean> callback);

}
