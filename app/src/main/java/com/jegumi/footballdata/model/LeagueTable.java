package com.jegumi.footballdata.model;

import java.io.Serializable;

public class LeagueTable implements Serializable {

    public String leagueCaption;
    public String matchday;
    public Standing[] standing;
}
