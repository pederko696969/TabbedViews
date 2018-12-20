package com.example.ezivfil.tabbedviews;


import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tan on 2/18/2016.
 */
public class FileHelper {

    final static String fileName = "data.txt";
    final static String page="https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/readwrite/" ;
    final static String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/readwrite/" ;
    final static String TAG = FileHelper.class.getName();

    final List<String> lista = new ArrayList<>();

    public static  String ReadFile( Context context){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static boolean saveToFile( String data){
        try {
//            new File(path  ).mkdir();
//            File file = new File(path+ fileName);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
//            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
//
//            return true;

        }  catch(Exception e) {
            Log.d(TAG, e.getMessage());
//        }  catch(IOException ex) {
//            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }

    public static String getHTML(String urlToRead) //throws Exception
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
            return result.toString();
        }
        catch( Exception e )
        {
            return ("Nije uspeo HTTP zahtev");
        }



    }

    public static File getTempFile(Context context, String url) {
        File file=null;

        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }

}
