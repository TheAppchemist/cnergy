package com.appchemy.cnergy.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Model implements Serializable
{
    private static final long serialVersionUID = 3041429559851798652L;
    private String json_str;

    public Model()
    {
        this.json_str = null;
    }

    public Model(JSONObject json) throws Exception
    {
        this.json_str = json.toString();
    }

    public String readableTime(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dte = null;
        try {
            dte = format.parse(date);
        }
        catch (Exception e)
        {
            return "";
        }
        long time = dte.getTime();

        String dif = "";

        long d = (System.currentTimeMillis() - time) / 1000;

        long day = d / (60 * 60 * 24);
        d = d - (day * (60 * 60 * 24));
        long hour = d / (60 * 60);
        d = d - (hour * (60 * 60));
        long min = d / (60);
        d = d - (min * (60));
        long sec = d;
        d = d - (sec);

        if (day != 0)
        {
            if (day > 365)
            {
                if ((int) (365 / day) == 1) {
                    dif = 1 + " year ago";
                } else {
                    dif = (int)(365 / day) + " years ago";
                }
            }
            else
            if (day > 30)
            {
                if ((int) (day / 31) == 1) {
                    dif = 1 + " month ago";
                } else {
                    dif = (int)(day / 31) + " months ago";
                }
            }
            else if (day > 6)
            {
                if ((int) (day / 7) == 1) {
                    dif = 1 + " week ago";
                } else {
                    dif = (int)(day / 7) + " weeks ago";
                }
            }
            else {
                if (day == 1) {
                    dif = "yesterday";
                } else {
                    dif = day + " days ago";
                }
            }
        } else if (hour != 0)
        {
            if (hour == 1)
            {
                dif = hour + " hour ago";
            }
            else
            {
                dif = hour + " hours ago";
            }
        } else if (min != 0)
        {
            if (min == 1)
            {
                dif = min + "minute ago";
            }
            else
            {
                dif = min + "minutes ago";
            }

        } else if (sec != 0)
        {
            dif = sec + "just now";
        }

        return dif;
    }

    public ArrayList jsonArrayToArrayList(String field, Class model) throws Exception
    {
        JSONObject json = new JSONObject(json_str);
        ArrayList list = new ArrayList();
        JSONArray array = json.getJSONArray(field);
        int size = array.length();

        for (int i = 0; i < size; i++)
        {
            Constructor<?> cons = model.getConstructor(JSONObject.class);
            list.add(cons.newInstance(array.get(i)));
        }

        return list;
    }

    public static ArrayList jsonArrayToArrayList(JSONObject json, String field, Class model) throws Exception {
        ArrayList list = new ArrayList();
        JSONArray array = json.getJSONArray(field);
        int size = array.length();

        for (int i = 0; i < size; i++) {
            Constructor<?> cons = model.getConstructor(JSONObject.class);
            list.add(cons.newInstance(array.get(i)));
        }

        return list;
    }
}
