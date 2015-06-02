package com.appchemy.cnergy.processing.request.cnergy;

import com.appchemy.cnergy.processing.request.JSONRequest;

import org.json.JSONObject;

/**
 * Created by melvin on 2/21/15.
 */
public class CnergyJSONRequest extends JSONRequest
{
    private String function_name;
    private JSONObject json;
    private String name = null;

    public CnergyJSONRequest(int id, String function)
    {
        this(id, function, null);
    }

    public CnergyJSONRequest(int id, String function_name, String name)
    {
        super(POST, id);
        this.function_name = function_name;
        this.name = name;

        setHeader("User-Agent", "Android");
        setHeader("Accept", "application/json");
        json = new JSONObject();
    }

    public void set(JSONObject json)
    {
        this.json = json;
    }

    protected void add(String name, String value)
    {
        try
        {
            json.put(name, value);
        }
        catch (Exception e)
        {

        }
    }

    protected void add(String name, JSONObject value)
    {
        try {
            json.put(name, value);
        } catch (Exception e) {

        }
    }

    public String functionName()
    {
        return function_name;
    }

    @Override
    protected JSONObject data()
    {
        if (name != null)
        {
            try {
                JSONObject j = new JSONObject();
                j.put(name, json);
                return j;
            }
            catch (Exception e)
            {

            }
        }

        return json;
    }
}