package com.brightedu.shared;

import java.io.Serializable;

public class OperatioinFailedException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 3223992539421141157L;

	OperatioinFailedException() {
		super();
	}

	public OperatioinFailedException(String message) {
		super(message);
	}

	public OperatioinFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperatioinFailedException(Throwable cause) {
		super(cause);
	}
}
