package com.example.footballleagueV2;


import java.util.ArrayList;

public class League {
    private String leagueName;
    private int leagueID;

    public static ArrayList<League> leagueList = new ArrayList<League>();


    public League(String leagueName, int leagueID) {
        this.leagueName = leagueName;
        this.leagueID = leagueID;
    }

    public League() {
    }

    public static ArrayList<League> getLeagueList() {
        return leagueList;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }
}
