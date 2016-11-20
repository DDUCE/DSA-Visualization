package com.tipsandtricks.HelloCard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gandhi on 18-Sep-16.
 */
public class WebRequest {
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;


    public String makeWebServiceCall(String url, int requestmethod) {
        return this.makeWebServiceCall(url, requestmethod, null);
    }

    public String makeWebServiceCall(String urladdress, int requestmethod, LinkedHashMap<String, String> params) {
        URL url;
        HttpURLConnection conn = null;
        String response = "";
        try {
            url = new URL(urladdress);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);


            if (requestmethod == POST) {
                conn.setRequestMethod("POST");
            } else if (requestmethod == GET) {
                conn.setRequestMethod("GET");
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return  response;
    }
}
