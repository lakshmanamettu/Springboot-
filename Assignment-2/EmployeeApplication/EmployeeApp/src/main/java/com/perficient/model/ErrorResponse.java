package com.perficient.model;



public class ErrorResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	
	private String statusMessage;
	

	public ErrorResponse(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


	@Override
	public String toString() {
		return "ErrorResponse [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}
}
