package com.example.ezivfil.tabbedviews;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Secondary.class);
        String url = "https://min-api.cryptocompare.com/data/all/coinlist";



        myIntent.putExtra("Ime", "DANK");
        startActivity(myIntent);
    }


}
