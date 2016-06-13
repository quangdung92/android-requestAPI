package com.ND.gizilocker.app;

import android.os.AsyncTask;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        try {
            response = httpclient.execute(new HttpPost(params[0]));
            StatusLine statusLine = response.getStatusLine();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            responseString = out.toString();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
