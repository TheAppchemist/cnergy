package com.appchemy.cnergy.processing;

import android.util.Log;

import com.appchemy.cnergy.model.*;
import com.appchemy.cnergy.model.Error;
import com.appchemy.cnergy.net.HttpInterface;
import com.appchemy.cnergy.net.HttpSettings;
import com.appchemy.cnergy.net.OnHttpRequestListener;
import com.appchemy.cnergy.processing.request.Request;

import org.json.JSONObject;

public class Processor implements OnHttpRequestListener
{
	private OnProcessListener listener = null;
	private HttpInterface http;
	private Request request;
	private int state = STATE_IDLE;

	public static final int STATE_SUCCESS = 0;
	public static final int STATE_FAILED = 1;
	public static final int STATE_PENDING = 2;
	public static final int STATE_IDLE = 3;

	public int getState()
	{
		return state;
	}
	
	public Processor()
	{
		
	}
	
	protected void request(String url, Request request)
	{
		state = STATE_PENDING;
		if (listener != null)
		{
			listener.onProcessStarted(request);
		}
		HttpSettings settings = new HttpSettings(request.getType());
		settings.setOutput(request.getData());
		settings.setHeaders(request.getHeaders());
	
		try
		{
            Log.i("URL", url);
            http = new HttpInterface(url, settings);
			http.request(this);
			this.request = request;
		}
		catch (Exception e)
		{
			state = STATE_FAILED;
			onNetworkError(e);
            e.printStackTrace();
		}
	}
	
	public final void setOnProcessListener(OnProcessListener listener)
	{
		this.listener = listener;
	}

	public void onRequestSuccess(byte[] data, int code) 
	{
		state = STATE_FAILED;
        //Log.i("data", new String(data));


		try
		{
			JSONObject json = new JSONObject(new String(data));
			
			if (listener != null)
			{
				if (json.has("error"))
				{
                    listener.onProcessFailed(new Error(json.getJSONObject("error")), request);
				}
				else
				{
                    Object object = processResponse(json, request);
					state = STATE_SUCCESS;
                    listener.onProcessSuccess(object, request);
				}
			}
		}
		catch (Exception e)
		{
            if (listener != null) {
                listener.onResponseError(request);
            }
            e.printStackTrace();
		}

		if (listener != null)
		{
			listener.onProcessFinished(request);
		}
	}
	
	protected Object processResponse(JSONObject json, Request request) throws Exception
	{
		return json;
	}

	public void onRequestError(byte[] data, int code) 
	{
		
	}

	public void onNetworkError(Exception e) 
	{
		listener.onProcessFinished(request);
		listener.onNetworkError(e, request);
        e.printStackTrace();
	}
}
