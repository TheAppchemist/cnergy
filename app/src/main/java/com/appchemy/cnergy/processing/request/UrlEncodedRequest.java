package com.appchemy.cnergy.processing.request;

import java.util.Enumeration;
import java.util.Hashtable;

public class UrlEncodedRequest extends Request
{
	private Hashtable params;
    private boolean url_encode;
	
	public UrlEncodedRequest(int type)
	{
		super(type);
	}
	
	public UrlEncodedRequest(int type, int id) 
	{
		super(type, id);
		params = new Hashtable();
		setHeader("Content-Type", "application/x-www-form-urlencoded");
	}

    public void setUrlEncoded(boolean encode) {
        this.url_encode = encode;
    }

    public boolean isUrlEncoded() {
        return url_encode;
    }
	
	public final void add(String name, String value)
	{
        if (isUrlEncoded())
        {
            params.put(name, encodeURL(value));
        }
        else
        {
            params.put(name, value);
        }
	}

	public byte[] getData() 
	{
		String url_params = "";
		String name;
		String value;
		Enumeration keys = params.keys();
		while (keys.hasMoreElements())
		{
			name = (String)keys.nextElement();
			value = (String)params.get(name);
			
			if (url_params.length() > 0)
			{
				url_params += '&';
			}
			
			url_params += name + '=' + value;
		}
		
		return url_params.getBytes();
	}

    private String encodeURL(String text)
    {
        String result = text;

        int size = result.length();
        char c;
        for (int i = 0; i < size; i++) {
            c = result.charAt(i);

            if (
                    !(((c >= 0x30) && (c <= 0x39)) || // 0-9
                            ((c >= 0x41) && (c <= 0x5a)) || // a-z
                            ((c >= 0x61) && (c <= 0x7a)))     // A-Z
                    ) {
                result = result.substring(0, i) + "%" + Integer.toHexString(c) +
                        result.substring(i + 1);
                i += ("%" + Integer.toHexString(c)).length() - 1;
                size = result.length();
            }
        }

        return result;
    }
}
