package com.example.cecil_finalproject;

import java.io.Serializable;

public class User implements Serializable {
    String uName, pWord;

    public User() {

    }

    public User(String u, String p) {
        uName = u;
        pWord = p;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getpWord() {
        return pWord;
    }

    public void setpWord(String pWord) {
        this.pWord = pWord;
    }
}
