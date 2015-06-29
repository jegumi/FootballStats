package com.jegumi.footballdata.model;

import java.io.Serializable;

public class Competition implements Serializable {

    public int id;
    public String caption;
    public String league;
    public String year;
    public String lastUpdate;
    public int numberOfTeams;
    public int numberOfGames;
    public Links _links;

}
