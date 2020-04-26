package com.example.footballleagueV2;

public class League {
    private String leagueName;
    private int leagueID;

    public League(String leagueName, int leagueID) {
        this.leagueName = leagueName;
        this.leagueID = leagueID;
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
