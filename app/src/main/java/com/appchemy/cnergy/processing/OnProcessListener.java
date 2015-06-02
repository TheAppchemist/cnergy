package com.appchemy.cnergy.processing;

import com.appchemy.cnergy.model.Error;
import com.appchemy.cnergy.processing.request.Request;

import java.util.ArrayList;

public interface OnProcessListener 
{
	public void onProcessStarted(Request request);
	public void onProcessFinished(Request request);
	public void onProcessSuccess(Object object, Request request);
	public void onProcessFailed(Error error, Request request);
	public void onResponseError(Request request);
	public void onNetworkError(Exception e, Request request);
}
