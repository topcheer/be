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

import java.util.List;

import com.brightedu.model.edu.BatchIndex;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DataBaseRPC")
public interface DataBaseRPC extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static DataBaseRPCAsync instance;
		public static DataBaseRPCAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(DataBaseRPC.class);
			}
			return instance;
		}
	}
	
	public List<BatchIndex> getBatchList(int offset, int limit);
	public List getBatchListAndTotalCounts(int offset, int limit);
	public boolean addBatch(String batch_name);
	public boolean deleteBatch(Integer batch_id);
	
	
}
