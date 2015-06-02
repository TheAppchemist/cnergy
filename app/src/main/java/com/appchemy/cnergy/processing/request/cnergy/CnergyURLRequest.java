package com.appchemy.cnergy.processing.request.cnergy;

import com.appchemy.cnergy.processing.request.UrlEncodedRequest;

public class CnergyURLRequest extends UrlEncodedRequest
{
    private String function_name;

    public CnergyURLRequest(int type, int id, String function_name)
    {
        super(type, id);
        this.function_name = function_name;

        setHeader("User-Agent", "Android");
        setHeader("Accept", "application/json");
    }

    public String functionName()
    {
        return function_name;
    }
}
