package com.dailyStock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class App {

    public static final String URL_TIINGO = "https://api.tiingo.com/tiingo/daily/";
    public static final String TOKEN = "?token=fc9a0e23dde6d1fd1757a6c222057af4b6948d21";
    static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        logger.info("Hola " + args[0]);
        String query = args[1] + "/prices";
        String resultado = "";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jNode = null;

        try {
            URL url = new URL(URL_TIINGO + query + TOKEN);
            logger.info(url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            InputStream response = con.getInputStream();
            jNode = mapper.readTree(response);

            jNode = jNode.get(0);
            jNode = jNode.get("close");
            resultado = jNode.asText();

        } catch (Exception e){
            resultado = e.toString();
        }

        logger.info(resultado);
    }
}
