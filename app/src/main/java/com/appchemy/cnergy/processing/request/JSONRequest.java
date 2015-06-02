package com.appchemy.cnergy.processing.request;

import android.content.SharedPreferences;

import org.json.JSONObject;

public abstract class JSONRequest extends Request
{
	public JSONRequest(int type, int id) 
	{
		super(type, id);
		setHeader("Content-type", "application/json");
	}
	
	protected abstract JSONObject data();

	public byte[] getData() 
	{
		return data().toString().getBytes();
	}

}

