package com.appchemy.cnergy.processor;

import com.appchemy.cnergy.model.Error;
import com.appchemy.cnergy.processor.request.Request;

public interface OnProcessListener 
{
	public void onProcessStarted(Request request);
	public void onProcessFinished(Request request);
	public void onProcessSuccess(Object object, Request request);
	public void onProcessFailed(Error error, Request request);
	public void onResponseError(Request request);
	public void onNetworkError(Exception e, Request request);
}
