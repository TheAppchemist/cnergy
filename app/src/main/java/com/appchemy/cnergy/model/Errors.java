package com.appchemy.cnergy.model;

import org.json.JSONArray;

import java.lang.*;
import java.util.ArrayList;
import java.util.Vector;

public class Errors 
{
	public ArrayList<Error> errors;
	
	public Errors()
	{
		errors = new ArrayList<Error>();
	}
	
	public Errors(JSONArray array) throws Exception
	{
		errors = new ArrayList<Error>();
		Error error;
		int size = array.length();
		
		for (int i = 0; i < size; i++)
		{
			error = new Error(array.getJSONObject(i));
			errors.add(error);
		}
	}
}
