package com.demo.pojo;

import io.swagger.annotations.ApiModel;

//@ApiModel(value="data", description="Sample model" )
public class ErrorRep {
	
	private int code;
	private String message;
	private String fields;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
