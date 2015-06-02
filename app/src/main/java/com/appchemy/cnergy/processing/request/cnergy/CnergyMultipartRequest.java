package com.appchemy.cnergy.processing.request.cnergy;

import com.appchemy.cnergy.processing.request.MultiPartRequest;

public class CnergyMultipartRequest extends MultiPartRequest
{
    private String function_name;

    public CnergyMultipartRequest(int id, String function_name) {
        super(id);
        this.function_name = function_name;

        setHeader("User-Agent", "Android");
        setHeader("Accept", "application/json");
    }

    public String functionName() {
        return function_name;
    }
}
