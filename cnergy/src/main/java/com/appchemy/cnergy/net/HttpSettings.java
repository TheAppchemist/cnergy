package com.appchemy.cnergy.net;

import java.util.Hashtable;

public class HttpSettings 
{
	public static final int GET = 0;
	public static final int POST = 1;
	
	private int type;
	private Hashtable headers;
	private byte[] output = null;
	
	public HttpSettings()
	{
		this(GET);
	}
	
	public HttpSettings(int type)
	{
		this.type = type;
		headers = new Hashtable();
	}
	
	public void setHeaders(Hashtable headers)
	{
		this.headers = headers;
	}
	
	public void setHeader(String name, String value)
	{
		headers.put(name, value);
	}
	
	public void setOutput(byte[] output)
	{
		this.output = output;
	}
	
	public Hashtable getHeaders()
	{
		return headers;
	}
	
	public int getType()
	{
		return type;
	}
	
	public byte[] getOutput()
	{
		return output;
	}
}

