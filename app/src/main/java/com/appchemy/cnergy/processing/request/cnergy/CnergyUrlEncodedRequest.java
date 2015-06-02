package com.appchemy.cnergy.processing.request.cnergy;

import com.appchemy.cnergy.processing.request.UrlEncodedRequest;

public class CnergyUrlEncodedRequest extends UrlEncodedRequest
{
    private String function_name;

    public CnergyUrlEncodedRequest(int id, String function_name) {
        this(id, GET, function_name);
    }

    public CnergyUrlEncodedRequest(int id, int method, String function_name) {
        super(method, id);

        this.function_name = function_name;
        setHeader("User-Agent", "Android");
    }

    public String functionName() {
        return function_name;
    }
}
