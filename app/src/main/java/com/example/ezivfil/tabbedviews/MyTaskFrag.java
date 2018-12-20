package com.example.ezivfil.tabbedviews;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MyTaskFrag extends AsyncTask<String, Integer, String> {

    JSONObject response;


    //    private String url = "https://min-api.cryptocompare.com/data/all/coinlist";
    // Runs in UI before background thread is called
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Do something like display a progress bar
    }

    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        // get the string from params, which is an array
        //String myString = params[0];
        HttpJsonParser jsonParser = new HttpJsonParser();
        response = jsonParser.makeHttpRequest(url,"GET",null);


        // Do something that takes a long time, for example:
        // for (int i = 0; i <= 100; i++) {

        // Do things

        // Call this to update your progress
        //      publishProgress(i);
        // }

        try {
            String izl = (String) response.get("Response");
            Log.d("izlaz",izl);
            return izl;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "error";
    }

    // This is called from background thread but runs in UI
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        // Do things like update the progress bar
    }

    // This runs in UI when background thread finishes
    @Override
    protected void onPostExecute(String result) {
        // super.onPostExecute(result);
        Log.d("rezultS", result);
        // Do things like hide the progress bar or change a TextView
    }

}