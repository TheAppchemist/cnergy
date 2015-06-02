package com.appchemy.cnergy.net;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.net.URL;

/**
 *
 * @author melvinmusehani
 */
public class HttpInterface extends AsyncTask<URL, Exception, byte[]>
{
	private static HttpURLConnection con;
	
	private HttpSettings settings;
	private OnHttpRequestListener listener;
    private URL url;
    private Hashtable headers;
	
	public HttpInterface(String link, HttpSettings settings) throws Exception
	{
		
		String str_params = "";
		if (settings.getType() == HttpSettings.GET && settings.getOutput() != null)
		{
			str_params += '?' + new String(settings.getOutput());
		}

        url = new URL(link + str_params);
		this.settings = settings;

		this.headers = settings.getHeaders();
	}
	
	public void request(OnHttpRequestListener listener)
	{
        Log.i("Final URL", url.toString());
        this.listener = listener;
        execute(url);
	}
	
	private void setHeaders(Hashtable headers) throws Exception
	{
		Enumeration keys = headers.keys();
		String key;
		String value;
		while (keys.hasMoreElements())
		{
			key = (String)keys.nextElement();
			value = (String)headers.get(key);
			con.setRequestProperty(key, value);
		}
	}

    @Override
    protected byte[] doInBackground(URL... params)
    {
        try
        {
            con = (HttpURLConnection)params[0].openConnection();
            setHeaders(headers);
            switch (settings.getType())
            {
                case HttpSettings.GET:
                {
                    con.setRequestMethod("GET");
                    break;
                }

                case HttpSettings.POST:
                {
                    con.setRequestMethod("POST");
                    break;
                }
            }

            if (HttpSettings.POST == settings.getType())
            {
                if (settings.getOutput() != null)
                {
                    OutputStream os = con.getOutputStream();
                    os.write(settings.getOutput());

                    Log.i("To send", new String(settings.getOutput()));
                }
            }

            InputStream is;

            if (con.getResponseCode() >= 200 && con.getResponseCode() <= 299)
            {
                is = con.getInputStream();
            }
            else
            {
                is = con.getErrorStream();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int k;

            while ((k = is.read()) != -1)
            {
                baos.write(k);
            }

            String xml = new String(baos.toByteArray());
            if(xml.length() > 4000) {
                for(int i=0;i<xml.length();i+=4000){
                    if(i+4000<xml.length())
                        Log.i("Final output"+i,xml.substring(i, i+4000));
                    else
                        Log.i("Final output"+i,xml.substring(i, xml.length()));
                }
            } else
                Log.i("Final output",xml);
            //Log.i("Final output", new String(baos.toByteArray()));

            return baos.toByteArray();
        }
        catch (Exception e)
        {
            publishProgress(e);
            Log.i("Error", e.toString());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Exception... values)
    {
        HttpInterface.this.listener.onNetworkError(values[0]);
    }

    @Override
    protected void onPostExecute(byte[] bytes)
    {
        if (bytes != null)
        {
            HttpInterface.this.listener.onRequestSuccess(bytes, 200);
        }
    }
}
