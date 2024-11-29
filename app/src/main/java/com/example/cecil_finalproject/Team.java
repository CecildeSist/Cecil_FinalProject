package com.example.cecil_finalproject;

import android.net.IpSecAlgorithm;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Team implements Serializable {
    Integer teamID;
    String userTrainer, pkmnA, pkmnB, pkmnC, pkmnD, pkmnE, pkmnF;
    Float averageTotal;

    public Team() {

    }

    public Team(Integer tID, Float aT, String uT, String pA, String pB,String pC, String pD, String pE, String pF) {
        teamID = tID;
        userTrainer = uT;
        averageTotal = aT;
        pkmnA = pA;
        pkmnB = pB;
        pkmnC = pC;
        pkmnD = pD;
        pkmnE = pE;
        pkmnF = pF;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public String getUserTrainer() {
        return userTrainer;
    }

    public void setUserTrainer(String userTrainer) {
        this.userTrainer = userTrainer;
    }

    public String getPkmnA() {
        return pkmnA;
    }

    public void setPkmnA(String pkmnA) {
        this.pkmnA = pkmnA;
    }

    public String getPkmnB() {
        return pkmnB;
    }

    public void setPkmnB(String pkmnB) {
        this.pkmnB = pkmnB;
    }

    public String getPkmnC() {
        return pkmnC;
    }

    public void setPkmnC(String pkmnC) {
        this.pkmnC = pkmnC;
    }

    public String getPkmnD() {
        return pkmnD;
    }

    public void setPkmnD(String pkmnD) {
        this.pkmnD = pkmnD;
    }

    public String getPkmnE() {
        return pkmnE;
    }

    public void setPkmnE(String pkmnE) {
        this.pkmnE = pkmnE;
    }

    public String getPkmnF() {
        return pkmnF;
    }

    public void setPkmnF(String pkmnF) {
        this.pkmnF = pkmnF;
    }

    public Float getAverageTotal() {
        return averageTotal;
    }

    public void setAverageTotal(Float averageTotal) {
        this.averageTotal = averageTotal;
    }

    static class pkmnName {
        static ArrayList<String> pkName = new ArrayList<>(Arrays.asList("Venusaur", "Charizard", "Blastoise", "Butterfree", "Beedrill", "Pidgeot", "Raticate", "Fearow", "Arbok", "Raichu", "Sandslash", "Nidoqueen", "Nidoking", "Clefable", "Ninetales", "Wigglytuff", "Golbat", "Vileplume", "Parasect", "Venomoth", "Dugtrio", "Persian", "Golduck", "Primeape", "Primeape", "Arcanine", "Poliwrath", "Alakazam", "Machamp", "Victreebel", "Tentacruel", "Golem", "Rapidash", "Slowbro", "Magneton", "Farfetchd", "Dodrio", "Dewgong", "Muk", "Cloyster", "Gengar", "Onix", "Hypno", "Kingler", "Electrode", "Marowak", "Hitmonlee", "Hitmonchan", "Lickitung", "Weezing", "Rhydon", "Chansey", "Tangela", "Kangaskhan", "Seadra", "Starmie", "Mister Mime", "Scyther", "Jynx", "Electabuzz", "Magmar", "Pinsir", "Tauros", "Gyarados", "Lapras", "Ditto", "Vaporeon", "Jolteon", "Flareon", "Porygon", "Omastar", "Kabutops", "Aerodactyl", "Snorlax", "Articuno", "Zapdos", "Moltres", "Mewtwo", "Mew"));

        public static ArrayList<String> getAllPokemon() {
            return pkName;
        }

        public static String getPkmnAt(int i) {
            return pkName.get(i);
        }
    }

    static class pkmnTypes {
        static ArrayList<String> pkTypes = new ArrayList<>(Arrays.asList("Bug", "Dragon", "Electric", "Fighting", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Normal", "Water", "Poison", "Psychic", "Rock"));

        public static ArrayList<String> getAllTypes() {
            return pkTypes;
        }

        public static String getTypeAt(int i) {
            return pkTypes.get(i);
        }
    }


}
