package com.accenture.dsg.backend.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecaptchaResponse {
	@JsonProperty("success")
	private boolean success;
	@JsonProperty("error-codes")
	private Collection<String> errorCodes;
	
	public RecaptchaResponse() {}
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errorCodes
	 */
	public Collection<String> getErrorCodes() {
		return errorCodes;
	}

	/**
	 * @param errorCodes the errorCodes to set
	 */
	public void setErrorCodes(Collection<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	@Override
	public String toString() {
		return success + " " + errorCodes;
	}
}