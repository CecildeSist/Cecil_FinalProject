package com.example.cecil_finalproject;

import java.io.Serializable;

public class Review implements Serializable {
    Integer revID, revScore;
    String teName, uReviwer;

    public Review() {

    }

    public Review(Integer rID, Integer rS, String tN, String uR) {
        revID = rID;
        revScore = rS;
        teName = tN;
        uReviwer = uR;
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

    public String getTeName() {
        return teName;
    }

    public void setTeName(String teName) {
        this.teName = teName;
    }

    public String getuReviwer() {
        return uReviwer;
    }

    public void setuReviwer(String uReviwer) {
        this.uReviwer = uReviwer;
    }
}
