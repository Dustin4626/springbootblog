package com.dustin.springbootblog.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * 	返回信息
 *	
 */
public class ApiResponse implements Serializable{

	/*
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 返回數據
	 */
	private Object data;
	
	public static ApiResponse of(String message, Object data) {
		return new ApiResponse(message, data);
	}

	public static ApiResponse ok(Object data) {
		return new ApiResponse(HttpStatus.OK.getReasonPhrase(), data);
	}
	
	public static ApiResponse msg(String message) {
        return of(message, null);
    }
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ApiResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	public ApiResponse() {
		super();
	}
	
	
}
