package com.ND.gizilocker.app;

import android.os.AsyncTask;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by quangdung on 13/06/2016.
 */
public class Connection extends AsyncTask<String, String, String> {
    final Logger log = Logger.getLogger(String.valueOf(MainActivity.class));
    @Override
    protected String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
//        loginId,passWord
        try {
//            Create data params
            List<NameValuePair> user = new ArrayList<NameValuePair>();
            user.add(new BasicNameValuePair("loginId", params[1]));
            user.add(new BasicNameValuePair("passWord", params[2]));
//            Request API, NameValuePair
            HttpPost httppost = new HttpPost(params[0]);
            httppost.setEntity(new UrlEncodedFormEntity(user));

            response = httpclient.execute(httppost);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();}
            else {
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
