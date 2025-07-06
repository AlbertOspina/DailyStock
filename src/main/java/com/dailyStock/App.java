package com.dailyStock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App {

    public static final String urlTiingo = "https://api.tiingo.com/tiingo/daily/";
    public static final String token = "?token=fc9a0e23dde6d1fd1757a6c222057af4b6948d21";
;
    public static void main(String[] args) {
        System.out.println("Hola " + args[0]);
        String query = args[1] + "/prices";
        StringBuffer content = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode Jnode;


        try {
            URL url = new URL(urlTiingo + query + token);
            System.out.println(url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            //System.out.println(con.getResponseCode());
            /*BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();*/

            InputStream response = con.getInputStream();

            Jnode = mapper.readTree(response);


        } catch (MalformedURLException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Jnode);


    }
}
