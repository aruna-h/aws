package com.bridgelabz.s3.dto;

import java.io.Serializable;

/**
 * @author bridgelabz
 * @since 18/7/2018 <br>
 *        <p>
 *        a business entity giving info about response dto
 *        </p>
 */
@SuppressWarnings("serial")

public class ResponseDto implements Serializable {

	private int status;
	private String message;

	public ResponseDto() {

	}

	public ResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
