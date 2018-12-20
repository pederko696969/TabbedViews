package com.example.ezivfil.tabbedviews;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTML {
    public static JSONObject getHTML(String urlToRead) //throws Exception
    {
        try
        {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
                Log.d("izlaz", line);
            }
            rd.close();
            JSONObject jsonObj = new JSONObject(result.toString());
            return jsonObj;
        }
        catch( Exception e )
        {
            return null;
        }



    }

}
