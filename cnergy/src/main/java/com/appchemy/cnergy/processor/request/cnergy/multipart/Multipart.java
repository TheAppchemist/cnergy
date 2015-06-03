package com.appchemy.cnergy.processor.request.cnergy.multipart;

import java.util.Enumeration;
import java.util.Hashtable;

public class Multipart
{
    private String content_type;
    private byte[] data;
    private String name = null;
    private String filename = null;

    private Multipart(String name)
    {
        this.name = name;
    }

    public Multipart(String name, byte[] bytes)
    {
        this(name);
        content_type = "application/octet-stream";
        data = bytes;
    }

    public Multipart(String filename, String string)
    {
        this(filename);
        data = string.getBytes();
    }

    public Multipart(Hashtable<String, String> params)
    {
        this("urlencoded");
        content_type = null;
        String name;
        String value;
        String url_params = "";
        Enumeration keys = params.keys();
        while (keys.hasMoreElements()) {
            name = (String) keys.nextElement();
            value = params.get(name);

            if (url_params.length() > 0) {
                url_params += '&';
            }

            url_params += name + '=' + value;
        }

        data = url_params.getBytes();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContentType(String content_type)
    {
        this.content_type = content_type;
    }

    public String getContentType() {
        return content_type;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
