package com.example.cecil_finalproject;

import java.io.Serializable;

public class Review implements Serializable {
    Integer revID, revScore, teamID;
    String uReviewer;

    public Review() {

    }

    public Review(Integer rID, Integer rS, Integer tID, String uR) {
        revID = rID;
        revScore = rS;
        teamID = tID;
        uReviewer = uR;
    }

    public Integer getRevID() {
        return revID;
    }

    public void setRevID(Integer revID) {
        this.revID = revID;
    }

    public Integer getRevScore() {
        return revScore;
    }

    public void setRevScore(Integer revScore) {
        this.revScore = revScore;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getuReviewer() {
        return uReviewer;
    }

    public void setuReviewer(String uReviewer) {
        this.uReviewer = uReviewer;
    }
}
