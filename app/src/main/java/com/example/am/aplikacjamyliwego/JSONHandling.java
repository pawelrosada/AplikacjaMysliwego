package com.example.am.aplikacjamyliwego;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pawel on 14.05.2016.
 */
public class JSONHandling extends AsyncTask<String, String, String> {
    public StringBuilder sb;
    private final MainActivity activityObj;

    public JSONHandling(final MainActivity activityObj){
        this.activityObj = activityObj;
    }
    protected String doInBackground(String... url) {
        if (isOnline()){
            String result = null;
            switch(url[1])
            {
                case "GET":
                    result = methodGet(url[0]);
                    break;
                default:
                    result = null;
                    break;
            }
            return result;
        }
        return null;
    }

    private String methodGet(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            //c.setConnectTimeout(100);
            //c.setReadTimeout(100);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }
            return sb.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public static void parseJson(JSONObject jsonResponse, final MainActivity activityObj){
        Database db = new Database(activityObj);
        try {

            JSONArray jsonMainNode = jsonResponse.optJSONArray("huntingArea");

            int lengthJsonArr = jsonMainNode.length();

            for(int i=0; i < lengthJsonArr; i++)
            {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                HuntingArea newHuntingArea = new HuntingArea();

                newHuntingArea.setId(Integer.parseInt(jsonChildNode.optString("Id").toString()));
                newHuntingArea.setName(jsonChildNode.optString("Name").toString());
                newHuntingArea.setDescription(jsonChildNode.optString("Description").toString());
                newHuntingArea.setTopLeftCorner(Double.parseDouble(jsonChildNode.optString("TopLeftCorner").toString()));
                newHuntingArea.setTopRightCorner(Double.parseDouble(jsonChildNode.optString("TopRightCorner").toString()));
                newHuntingArea.setBottomLeftCorner(Double.parseDouble(jsonChildNode.optString("BottomLeftCorner").toString()));
                newHuntingArea.setBottomRightCorner(Double.parseDouble(jsonChildNode.optString("BottomRightCorner").toString()));

                db.addHuntingArea(newHuntingArea);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activityObj.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
