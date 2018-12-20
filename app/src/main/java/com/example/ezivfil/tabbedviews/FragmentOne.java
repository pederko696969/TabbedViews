package com.example.ezivfil.tabbedviews;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FragmentOne extends Fragment  {


    public TextView btc,eth,evn,doge,zec,usd,eur,tv;

    JSONObject response;
    JSONObject data;
    JSONObject display;
    JSONObject coin;
    JSONObject coindetails;

    final ArrayList<String> coinlist = new ArrayList<>();
    final ArrayList<String> lista2 = new ArrayList<>();

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one,
                container, false);



        String CoinPick = getActivity().getIntent().getStringExtra("Ime");
     //   String url = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+CoinPick+"&tsyms=BTC,ETH,EVN,DOGE,ZEC,USD,EUR";

        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_one, container,false);

        View theView = inflater.inflate(R.layout.fragment_one, null);
        TextView tv = (TextView) theView.findViewById(R.id.coinname);
        DwDetails task1 = new DwDetails();
        task1.execute(CoinPick);



        TextView t = (TextView) myInflatedView.findViewById(R.id.coinname);

       // Log.d("URL je", url);


        return myInflatedView;
    }
    public class DwDetails extends AsyncTask<String, Integer, ArrayList> {
        public TextView btc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected ArrayList doInBackground(String... params) {
            String CoinPick = params[0];

            String url = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms="+CoinPick+"&tsyms=USD,EUR";
            response=HTML.getHTML(url);
            Log.d("HTML ", HTML.getHTML(url).toString());
            Log.d("resp", response.toString());

            try {

                    display = response.getJSONObject("DISPLAY");
                    Log.d("Display", display.toString());
                    coin = display.getJSONObject(CoinPick);
                    Log.d("Coin", coin.toString());
                    coindetails = coin.getJSONObject("USD");
                    //   usd.setText(coindetails.getString("PRICE"));
                    Log.d("USD", coindetails.getString("PRICE"));

                coinlist.add(coin.getJSONObject("BTC").getString("PRICE"));
                coinlist.add(coin.getJSONObject("ETH").getString("PRICE"));
                coinlist.add(coin.getJSONObject("EVN").getString("PRICE"));
                coinlist.add(coin.getJSONObject("DOGE").getString("PRICE"));
                coinlist.add(coin.getJSONObject("ZEC").getString("PRICE"));
                coinlist.add(coin.getJSONObject("USD").getString("PRICE"));
                coinlist.add(coin.getJSONObject("EUR").getString("PRICE"));
                    return coinlist;

            } catch (JSONException e) {
                Log.d("Greska", getErrorMsg(response));
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(ArrayList result) {

            super.onPostExecute(result);
            //kako da prosledim result u Fragment gore? :(

        }

        private String getErrorMsg (JSONObject Response)
        {
            try
            {
                return Response.getString("Message");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        return null;
        }

    }


}