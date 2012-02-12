package com.brightedu.client;

import java.util.List;

import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataBaseRPCAsync {

	void getBatchList(int offset, int limit,
			AsyncCallback<List<BatchIndex>> callback);

	void getBatchListAndTotalCounts(int offset, int limit,
			AsyncCallback<List<BatchIndex>> callbac);

	void addBatch(String batch_name, AsyncCallback<Boolean> callback);

	void deleteBatch(List<Integer> batch_ids, AsyncCallback<Boolean> callback);

	void saveBatch(BatchIndex editedBatch, AsyncCallback<Boolean> callback);

	void getStudentClassesList(int offset, int limit,
			AsyncCallback<List<StudentClassified>> callback);

	void addStudentClass(String studentClassName,
			AsyncCallback<Boolean> callback);

	void deleteStudentClasses(List<Integer> studentClassesId,
			AsyncCallback<Boolean> callback);

	void getStudentClasseshListAndTotalCounts(int offset, int limit,
			AsyncCallback<List<StudentClassified>> callback);

	void saveStudentClasses(StudentClassified studentClass,
			AsyncCallback<Boolean> callback);

	void getCollegeList(int offset, int limit,
			AsyncCallback<List<College>> callback);

	void getCollegeListAndTotalCounts(int offset, int limit,
			AsyncCallback<List<College>> callback);

	void addCollege(String collegeName, AsyncCallback<Boolean> callback);

	void deleteCollege(List<Integer> college_ids,
			AsyncCallback<Boolean> callback);

	void saveCollege(College college, AsyncCallback<Boolean> callback);

	void addSubject(String subjectName, AsyncCallback<Boolean> callback);

	void deleteSubject(List<Integer> subject_ids,
			AsyncCallback<Boolean> callback);

	void getSubjectsList(int offset, int limit,
			AsyncCallback<List<Subjects>> callback);

	void getSubjectsListAndTotalCounts(int offset, int limit,
			AsyncCallback<List<Subjects>> callback);

	void saveSubject(Subjects subject, AsyncCallback<Boolean> callback);

	void getRecruitAgentList(int offset, int limit,AsyncCallback<List<RecruitAgent>> callback);

	void getStudentTypeList(int offset, int limit,
			AsyncCallback<List<StudentType>> callback);

	void getStudentTypeListAndTotalCounts(int offset, int limit,
			AsyncCallback<List<StudentType>> callback);

	void addStudentType(String studentClassName, AsyncCallback<Boolean> callback);

	void deleteStudentType(List<Integer> studentClassesId,
			AsyncCallback<Boolean> callback);

	void saveStudentType(StudentType studentType,
			AsyncCallback<Boolean> callback);

	void getAgentTypeList(int offset, int limit,
			AsyncCallback<List<AgentType>> callback);

	void getAgentTypeListAndTotalCounts(int offset, int limit,
			AsyncCallback<List> callback);

	void addAgentType(AgentType agentType, AsyncCallback<Boolean> callback);

	void deleteAgentType(List<Integer> agentType_ids,
			AsyncCallback<Boolean> callback);

	void saveAgentType(AgentType agenttype, AsyncCallback<Boolean> callback);

}
