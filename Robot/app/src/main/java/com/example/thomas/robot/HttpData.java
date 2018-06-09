package com.example.thomas.robot;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thomas on 2017/11/14.
 */

public class HttpData extends AsyncTask<String,Void,String>{
    private HttpURLConnection mHttpUrlConnection;
    private URL mUrl;
    private String url;
    private String line;
    private InputStream mInputStream;
    private HttpGetDataListener listener;
    private BufferedReader mBufferedReader;
    public HttpData(String url,HttpGetDataListener listener){
        this.url = url;
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            mUrl = new URL(url);
            mHttpUrlConnection = (HttpURLConnection)mUrl.openConnection();
            mHttpUrlConnection.setRequestMethod("GET");
            mHttpUrlConnection.connect();
            mInputStream = mHttpUrlConnection.getInputStream();
            mBufferedReader = new BufferedReader(new InputStreamReader(mInputStream));
            StringBuffer stringBuffer = new StringBuffer();
            while((line = mBufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            return stringBuffer.toString();
        }catch (Exception e){
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        listener.getDataUrl(result);
    }
}
