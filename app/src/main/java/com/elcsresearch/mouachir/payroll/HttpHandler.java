package com.elcsresearch.mouachir.payroll;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by neofly on 8/7/17.
 */

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String getJSONList(String reqUrl) {

        String response = null;

        try
        {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
            conn.disconnect();

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return response;
    }



    public String postJSONCreds() {

        String response = null;


        try
        {
            URL url = new URL("http://192.168.1.122:8000/payroll/ListeEmployeJSON/");
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("GET");
            urlConn.setRequestProperty("Accept", "application/json");
            urlConn.setRequestProperty("username", "admin@admin.com");
            urlConn.setRequestProperty("password", "admin");
            urlConn.connect();
            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            response = convertStreamToString(in);
            urlConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;

    }

    private String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
