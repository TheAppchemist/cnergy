package com.appchemy.cnergy.processor;

import com.appchemy.cnergy.processor.request.Request;
import com.appchemy.cnergy.processor.request.cnergy.CnergyJSONRequest;
import com.appchemy.cnergy.processor.request.cnergy.CnergyMultipartRequest;
import com.appchemy.cnergy.processor.request.cnergy.CnergyURLRequest;

/**
 * Created by melvin on 2/21/15.
 */
public abstract class CnergyProcessor extends Processor
{
    private String server;

    public CnergyProcessor(String server)
    {
        this.server = server;
    }

    protected void callFunction(Request request)
    {
        String function_name = "";

        if (request instanceof CnergyURLRequest)
        {
            function_name = ((CnergyURLRequest)request).functionName();
        }
        else if (request instanceof CnergyJSONRequest)
        {
            function_name = ((CnergyJSONRequest)request).functionName();
        }else if (request instanceof CnergyMultipartRequest)
        {
            function_name = ((CnergyMultipartRequest)request).functionName();
        }

        request(server + function_name, request);
    }
}
