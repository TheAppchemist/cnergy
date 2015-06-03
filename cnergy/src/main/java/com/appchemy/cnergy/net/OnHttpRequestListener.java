package com.appchemy.cnergy.net;

public interface OnHttpRequestListener 
{
	public void onRequestSuccess(byte[] data, int code);
	public void onRequestError(byte[] data, int code);
	public void onNetworkError(Exception e);
}
