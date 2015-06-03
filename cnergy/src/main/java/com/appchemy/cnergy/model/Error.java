package com.appchemy.cnergy.model;

import org.json.JSONObject;

public class Error
{
	public String message;
	public int code;
	
	public static final int ERROR_CODE_NOT_LOGGED_IN = 401;
	
	public Error()
	{
		
	}
	
	public Error(String message)
	{
		this.message = message;
		this.code = -1;
	}
	
	public Error(JSONObject json) throws Exception
	{
		message = json.getString("message");
		code = json.getInt("code");
	}
}
