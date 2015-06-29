package com.jegumi.footballdata.model;

import java.io.Serializable;

public class Standing implements Serializable {

    public Links _links;
    public int position;
    public String teamName;
    public int playedGames;
    public int points;
    public int goals;
    public int goalsAgainst;
    public int goalsDifference;
}
