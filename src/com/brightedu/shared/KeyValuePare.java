package com.brightedu.shared;

import java.io.Serializable;

public class KeyValuePare implements Serializable {

	private static final long serialVersionUID = 5591323736094531736L;

	private Serializable key;

	private Serializable value;

	public KeyValuePare() {
	}

	public KeyValuePare(Serializable key, Serializable value) {
		this.key = key;
		this.value = value;
	}

	public Serializable getKey() {
		return key;
	}

	public void setKey(Serializable key) {
		this.key = key;
	}

	public Serializable getValue() {
		return value;
	}

	public void setValue(Serializable value) {
		this.value = value;
	}

	public String toString() {
		if (value == null)
			return "";
		return value.toString();
	}

}
