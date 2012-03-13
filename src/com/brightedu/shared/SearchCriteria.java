package com.brightedu.shared;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

	private String criteriaKey;
	private Serializable criteriaValue;
	private boolean isLike = true;

	public SearchCriteria() {
	}

	public SearchCriteria(String key, Serializable value) {
		criteriaKey = key;
		criteriaValue = value;
		isLike = false;
	}

	public SearchCriteria(String key, Serializable value, boolean isLike) {
		criteriaKey = key;
		criteriaValue = value;
		this.isLike = isLike;
	}

	public String getCriteriaKey() {
		return criteriaKey;
	}

	public void setCriteriaKey(String criteriaKey) {
		this.criteriaKey = criteriaKey;
	}

	public Serializable getCriteriaValue() {
		return criteriaValue;
	}

	public void setCriteriaValue(Serializable criteriaValue) {
		this.criteriaValue = criteriaValue;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

}
