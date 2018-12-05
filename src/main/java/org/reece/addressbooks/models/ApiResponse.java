package org.reece.addressbooks.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponse {
	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private long timestamp;
	private String message;
	private Object data;


	public ApiResponse() {
		this.timestamp = Instant.now().toEpochMilli();
		this.data = null;
		this.status = HttpStatus.BAD_REQUEST;
		this.message = "Something Went Wrong";
	}
	
	public ApiResponse(HttpStatus status) {
		this.timestamp = LocalDateTime.now().hashCode();
		this.status = status;
	}
	

	public ApiResponse(Object data) {
		this.data = data;
		this.timestamp = Instant.now().toEpochMilli();
		this.status = HttpStatus.ACCEPTED;
		this.message = "Successful";
		
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
