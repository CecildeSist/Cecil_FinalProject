package com.example.cecil_finalproject;

import java.io.Serializable;

public class Pokemon implements Serializable {
    String pkmnName, typeA, typeB;
    int statTotal;

    public Pokemon() {

    }

    public Pokemon(String pN, String tA, String tB, int sT) {
        pkmnName = pN;
        typeA = tA;
        typeB = tB;
        statTotal = sT;
    }

    public String getPkmnName() {
        return pkmnName;
    }

    public void setPkmnName(String pkmnName) {
        this.pkmnName = pkmnName;
    }

    public String getTypeA() {
        return typeA;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public String getTypeB() {
        return typeB;
    }

    public void setTypeB(String typeB) {
        this.typeB = typeB;
    }

    public int getStatTotal() {
        return statTotal;
    }

    public void setStatTotal(int statTotal) {
        this.statTotal = statTotal;
    }
}
