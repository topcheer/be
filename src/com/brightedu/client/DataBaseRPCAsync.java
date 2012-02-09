package com.brightedu.client;

import java.util.List;

import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.StudentClassified;
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
			AsyncCallback<List> callback);

	void saveStudentClasses(StudentClassified studentClass,
			AsyncCallback<Boolean> callback);

}
