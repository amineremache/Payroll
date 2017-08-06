package com.elcsresearch.mouachir.payroll;

import android.net.sip.SipAudioCall;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Created by neofly on 8/6/17.
 */

public class LoadJSONTask extends AsyncTask<String,Void,Response> {
    public LoadJSONTask (Listener listener) {
        mListener = listener;
    }


    public interface Listener {

        void onLoaded(List<Employee> empsList);

        void onError();
    }

    private Listener mListener;


    @Override
    protected Response doInBackground(String... strings) {

        try {

        String stringResponse = loadJSON(strings[0]);
        Gson gson = new Gson();

        return gson.fromJson(stringResponse,Response.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void onPostExecute(Response response) {



        if (response != null) {

            mListener.onLoaded(response.getEmployees());

        } else {

            mListener.onError();
        }
    }




    private String loadJSON(String jsonURL) throws IOException {

        URL url = new URL(jsonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = in.readLine()) != null) {

            response.append(line);
        }

        in.close();
        return response.toString();
    }


}
