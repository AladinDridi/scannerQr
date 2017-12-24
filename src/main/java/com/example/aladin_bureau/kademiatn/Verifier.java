package com.example.aladin_bureau.kademiatn;
import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Aladin-Bureau on 05/11/2017.
 */

public class Verifier {


    public Verifier() {
    }

    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(),"UTF8"));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }





    public Boolean status() throws Exception {


        JSONArray jsonArray=new JSONArray(getText("https://blog.rumi-app.org/user_status/"));
        //iterate loop

            //get the JSON Object
            JSONObject obj=jsonArray.getJSONObject(0);
            char sfname=obj.getString("name").charAt(0);
            if(sfname=='c'){
                return true;
            }else
                {return false;}
        }


}


