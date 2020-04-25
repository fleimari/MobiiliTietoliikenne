package com.example.stockmonitor;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class fetchData extends AsyncTask<Void, Void, Void>  {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://financialmodelingprep.com/api/company/price/AAPL,GOOGL,FB,NOK?datatype=json");

            HttpURLConnection mConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = mConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsonObject = new JSONObject(data);

            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                JSONObject stock = jsonObject.getJSONObject(key);
                double stockPrice = stock.getDouble("price");

                Log.d("kk", "Osake: " + key + "hinta: " + stockPrice);
                singleParsed = "Osake: " + key + "\nhinta:" + stockPrice;
                dataParsed = dataParsed + singleParsed + "\n\n";
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.tvData.setText(this.dataParsed);
    }
}
