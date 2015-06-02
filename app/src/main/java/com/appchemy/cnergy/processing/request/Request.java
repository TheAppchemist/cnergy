package com.appchemy.cnergy.processing.request;

import java.util.Hashtable;

public abstract class Request
{
	public static final int GET = 0;
	public static final int POST = 1;
	
	private int type;
	private Hashtable headers;
	private int id;
    private String loading_title = "Please wait..";
    private String loading_text = "Loading...";
	
	public Request(int type)
	{
		this(type, -1);
	}
	
	public Request(int type, int id)
	{
		this.type = type;
		this.id = id;
		headers = new Hashtable();
	}

    public final void setLoadingTitle(String loading_title)
    {
        this.loading_title = loading_title;
    }

    public final void setLoadingText(String loading_text)
    {
        this.loading_text = loading_text;
    }

    public final String getLoadingText()
    {
        return loading_text;
    }

    public final String getLoadingTitle()
    {
        return loading_title;
    }
	
	public final void setHeader(String name, String value)
	{
		headers.put(name, value);
	}
	
	public final int getType()
	{
		return type;
	}
	
	public final Hashtable getHeaders()
	{
		return headers;
	}
	
	public int getId()
	{
		return id;
	}
	
	public abstract byte[] getData();
}

