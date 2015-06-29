package com.jegumi.footballdata.network;

import java.util.HashMap;

public class Api {

    private static final String TAG = Api.class.getSimpleName();
    private static final String BASE_URL = "http://api.football-data.org/alpha/";
    private static final String COMPETITIONS_ENDPOINT = "soccerseasons/";
    private static final String TOKEN_KEY = "X-Auth-Token";
    private static final String TOKEN_VALUE = "XXXXXXXXXXXXXX";

    public static String getCompetitionsEndPoint() {
        return BASE_URL + COMPETITIONS_ENDPOINT;
    }

    public static HashMap<String, String> getHeaders() {
        HashMap headers = new HashMap<String, String>();
        headers.put(TOKEN_KEY, TOKEN_VALUE);

        return headers;
    }
}
