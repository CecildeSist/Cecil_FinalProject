package com.example.cecil_finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "teambuilderApp.db";
    public static final String users_table_name = "Users";
    //NOTE TO SELF Android Studio could struggle with the accented "e" in "Pokemon." Use a regular "e" instead!
    private static final String pkmn_table_name = "Pokemon";
    public static final String teams_table_name = "Teams";
    private static final String reviews_table_name = "Reviews";

    public DatabaseHelper(Context c) {
        super(c, database_name, null, 74);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + users_table_name + " (username varchar(50) primary key not null, password varchar(50));");
        //I realized the availability booleans the game version tags aren't needed. I already allowed Pokemon who can only be obtained by trading, so any
        //Pokemon's availability could be increased via trading.
        db.execSQL("CREATE TABLE " + pkmn_table_name + " (pkmnName varchar(10) primary key not null, typeOne varchar(9), typeTwo varchar(17), baseStatTotal integer);");
        db.execSQL("CREATE TABLE " + teams_table_name + " (teamID integer primary key autoincrement not null, averageBST float, trainerName varchar(50), pkmnOne varchar(10), pkmnTwo varchar(10), pkmnThree varchar (10), pkmnFour varchar(10), pkmnFive varchar(10), pkmnSix varchar(10), foreign key (trainerName) references " + users_table_name + " (username), foreign key (pkmnOne) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnTwo) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnThree) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnFour) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnFive) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnSix) references " + pkmn_table_name + " (pkmnName));");
        db.execSQL("CREATE TABLE " + reviews_table_name + " (reviewID integer primary key autoincrement not null, reviewScore integer, teamID integer, userReviewing varchar(50), foreign key (teamID) references " + teams_table_name + " (teamID), foreign key (userReviewing) references " + users_table_name + " (username));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + users_table_name + ";");
        //Do we have to drop the Pokemon table since it never gets upgraded? Answer: yes.
        db.execSQL("DROP TABLE IF EXISTS " + teams_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + reviews_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + pkmn_table_name + ";");
        onCreate(db);
    }

    public static String getUsers_table_name() {
        return users_table_name;
    }

    public static String getPkmn_table_name() {
        return pkmn_table_name;
    }

    public static String getTeams_table_name() {
        return teams_table_name;
    }

    public static String getReviews_table_name() {
        return reviews_table_name;
    }

    public void initAllTables() {
        initUserTable();
        initPkmnTable();
        initTeamTable();
        initReviewTable();
    }

    public void initUserTable() {
        if(countRecordsFromTable(users_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + users_table_name + " (username, password) VALUES ('CecilDeSist', 'ILoveMephy');");
            db.execSQL("INSERT INTO " + users_table_name + " (username, password) VALUES ('Mephistopheles', 'ILoveCecil');");
            db.close();
        }
    }
    //Initialize the tables
    public void initPkmnTable() {
        if(countRecordsFromTable(pkmn_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Venusaur', 'Grass', 'Poison', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Charizard', 'Fire', 'Flying', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Blastoise', 'Water', 'No secondary type', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Butterfree', 'Bug', 'Flying', 305);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Beedrill', 'Bug', 'Poison', 305);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Pidgeot', 'Normal', 'Flying', 399);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Raticate', 'Normal', 'No secondary type', 343);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Fearow', 'Normal', 'Flying', 381);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Arbok', 'Poison', 'No secondary type', 359);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Raichu', 'Electric', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Sandslash', 'Ground', 'No secondary type', 405);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Nidoqueen', 'Poison', 'Ground', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Nidoking', 'Poison', 'Ground', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Clefable', 'Normal', 'No secondary type', 383);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Ninetales', 'Fire', 'No secondary type', 424);");
            db.execSQL("INSERT INTO "+ pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Wigglytuff', 'Normal', 'No secondary type', 350);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Golbat', 'Poison', 'Flying', 390);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Vileplume', 'Grass', 'Poison', 390);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Parasect', 'Bug', 'Grass', 345);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Venomoth', 'Bug', 'Poison', 375);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Dugtrio', 'Ground', 'No secondary type', 355);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Persian', 'Normal', 'No secondary type', 375);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Golduck', 'Water', 'No secondary type', 405);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Primeape', 'Fighting', 'No secondary type', 385);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Arcanine', 'Fire', 'No secondary type', 455);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Poliwrath', 'Water', 'Fighting', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Alakazam', 'Psychic', 'No secondary type', 405);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Machamp', 'Fighting', 'No secondary type', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Victreebel', 'Grass', 'Poison', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Tentacruel', 'Water', 'Poison', 435);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Golem', 'Rock', 'Ground', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Rapidash', 'Fire', 'No secondary type', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Slowbro', 'Water', 'Psychic', 390);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Magneton', 'Electric', 'No secondary type', 395);");
            //Removed the apostrophe in Farfetch’d’s name to prevent code issues
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Farfetchd', 'Normal', 'Flying', 290);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Dodrio', 'Normal', 'Flying', 400);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Dewgong', 'Water', 'Ice', 405);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Muk', 'Poison', 'No secondary type', 400);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Cloyster', 'Water', 'Ice', 480);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Gengar', 'Ghost', 'Poison', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Onix', 'Rock', 'Ground', 340);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Hypno', 'Psychic', 'No secondary type', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Kingler', 'Water', 'No secondary type', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Electrode', 'Electric', 'No secondary type', 400);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Marowak', 'Ground', 'No secondary type', 345);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Hitmonlee', 'Fighting', 'No secondary type', 345);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Hitmonchan', 'Fighting', 'No secondary type', 345);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Lickitung', 'Normal', 'No secondary type', 310);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Weezing', 'Poison', 'No secondary type', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Rhydon', 'Ground', 'Rock', 440);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Chansey', 'Normal', 'No secondary type', 415);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Tangela', 'Grass', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Kangaskhan', 'Normal', 'No secondary type', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Seadra', 'Water', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Seaking', 'Water', 'No secondary type', 385);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Starmie', 'Water', 'Psychic', 435);");
            //Made Mr. Mime’s name one word to prevent code errors
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Mr Mime', 'Psychic', 'No secondary type', 340);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Scyther', 'Bug', 'Flying', 420);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Jynx', 'Ice', 'Psychic', 340);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Electabuzz', 'Electric', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Magmar', 'Fire', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Pinsir', 'Bug', 'No secondary type', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Tauros', 'Normal', 'No secondary type', 450);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Gyarados', 'Water', 'Flying', 480);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Lapras', 'Water', 'Ice', 450);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Ditto', 'Normal', 'No secondary type', 240);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Vaporeon', 'Water', 'No secondary type', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Jolteon', 'Electric', 'No secondary type', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Flareon', 'Fire', 'No secondary type', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Porygon', 'Normal', 'No secondary type', 310);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Omastar', 'Rock', 'Water', 425);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Kabutops', 'Rock', 'Water', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Aerodactyl', 'Rock', 'Flying', 440);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Snorlax', 'Normal', 'No secondary type', 430);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Articuno', 'Ice', 'Flying', 485);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Zapdos', 'Electric', 'Flying', 490);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Moltres', 'Fire', 'Flying', 495);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Dragonite', 'Dragon', 'Flying', 500);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Mewtwo', 'Psychic', 'No secondary typing', 590);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Mew', 'Psychic', 'No secondary typing', 500);");
            db.close();
        }
    }

    public void initTeamTable(){
        if(countRecordsFromTable(teams_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + teams_table_name + " (teamID, averageBST, trainerName, pkmnOne, pkmnTwo, pkmnThree, pkmnFour, pkmnFive, pkmnSix) VALUES (1, 390, 'CecilDeSist', 'Magmar', 'Weezing', 'Mr Mime', 'Zapdos', 'Wigglytuff', 'Hitmonlee');");
            db.execSQL("INSERT INTO " + teams_table_name + " (teamID, averageBST, trainerName, pkmnOne, pkmnTwo, pkmnThree, pkmnFour, pkmnFive, pkmnSix) VALUES (2, 410.833, 'Mephistopheles', 'Mr Mime', 'Scyther', 'Venomoth', 'Parasect', 'Mewtwo', 'Magneton');");
            db.close();
        }
    }

    public void initReviewTable() {
        if(countRecordsFromTable(reviews_table_name) == 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO " + reviews_table_name + " (reviewID, reviewScore, teamID, userReviewing) VALUES (1, 5, 2, 'CecilDeSist');");
            db.execSQL("INSERT INTO " + reviews_table_name + " (reviewID, reviewScore, teamID, userReviewing) VALUES (2, 3, 1, 'Mephistopheles');");
        }
    }

    public int countRecordsFromTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();
        return numRows;
    }

    //Used to determine if a user's log-in attempt was successful
    public boolean logInValid(String uNameEntered, String pWordEntered) {
        /*SQLiteDatabase db = this.getReadableDatabase();
        //See if username exists
        String selectStatement = "SELECT count(username) FROM " + users_table_name + " WHERE username = '" + uNameEntered + "';";
        //Run the query
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if(count != 0) {
            selectStatement = "SELECT count(username) FROM " + users_table_name + " WHERE username = '" + uNameEntered + "' AND password = '" + pWordEntered + "';";
            cursor = db.rawQuery(selectStatement, null);
            count = cursor.getInt(0);
            if (count != 0) {
                db.close();
                return true;
            }
            else {
                db.close();
                return false;
            }
        }
        else {
            db.close();
            return false;
        }*/
        boolean vaLogOrNo;
        Log.d("Data Passed:", uNameEntered + " " + pWordEntered);
        String selectStatement = "SELECT * from " + users_table_name + " WHERE username = '" + uNameEntered + "' AND password = '" + pWordEntered + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            vaLogOrNo = true;
        }
        else {
            vaLogOrNo = false;
        }
        db.close();
        return vaLogOrNo;
    }

    //Used in the register activity
    public boolean usernameAvailable(String newUName) {
        SQLiteDatabase db = this.getReadableDatabase();
        //See if username exists
        String selectStatement = "SELECT count(username) FROM " + users_table_name + " WHERE username = '" + newUName + "';";
        //Run the query
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        if (count == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //Also used in the register activity
    public void addNewUser (String newUName, String newPWord) {
        //Create new user
        User newUser = new User();

        newUser.setuName(newUName);
        newUser.setpWord(newPWord);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + users_table_name + " (username, password) VALUES ('" + newUName + "', '" + newPWord + "');");
        db.close();
        Log.d("new number of users in database:", countRecordsFromTable(users_table_name) + "");
    }

    @SuppressLint("Range")
    public float teamAvgFloat (String pokemonA, String pokemonB, String pokemonC, String pokemonD, String pokemonE, String pokemonF) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer statsA, statsB, statsC, statsD, statsE, statsF;
        statsA = 0;
        statsB = 0;
        statsC = 0;
        statsD = 0;
        statsE = 0;
        statsF = 0;

        String selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonA + "';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsA = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonB + "';";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsB = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonC + "';";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsC = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonD + "';";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsD = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonE + "';";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsE = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        selectStatement = "SELECT baseStatTotal FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokemonF + "';";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                statsF = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));
            }
            while (cursor.moveToNext());
        }
        float average = (float) (statsA + statsB + statsC + statsD + statsE + statsF) / 6;
        db.close();

        return average;
    }

    public void addNewTeam(Integer teamID, Float averageBST, String trainerName, String pkmnA, String pkmnB, String pkmnC, String pkmnD, String pkmnE, String pkmnF) {
        Team newTeam = new Team(teamID, averageBST, trainerName, pkmnA, pkmnB, pkmnC, pkmnD, pkmnE, pkmnF);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + teams_table_name + " (teamID, averageBST, trainerName, pkmnOne, pkmnTwo, pkmnThree, pkmnFour, pkmnFive, pkmnSix) VALUES ('" + teamID + "','" + averageBST + "','" + trainerName + "','" + pkmnA + "','" + pkmnB + "','" + pkmnC + "','" + pkmnD + "','" + pkmnE + "','" + pkmnF + "');");
        db.close();
        Log.d("number of teams in database:", countRecordsFromTable(teams_table_name) + "");
    }

    //Calculate the average rating for a team
    @SuppressLint("Range")
    public String averageRating (Integer teamID) {
        String selectStatement = "Select reviewScore from " + reviews_table_name + " Where teamID = " + teamID + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        float averageRating = 2.5F;
        int totalScore = 0;
        int numReviews = 0;

        if (cursor.moveToFirst()) {
            do {
                totalScore = totalScore + cursor.getInt(cursor.getColumnIndex("reviewScore"));
                numReviews = numReviews + 1;
            }
            while (cursor.moveToNext());
            averageRating = totalScore/numReviews;
        }
        db.close();
        String string = Float.toString(averageRating);
        return string;
    }

    @SuppressLint("Range")
    public ArrayList<Team> welcomeTeams() {
        ArrayList<Team> returnTeams = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + teams_table_name + ";";

        SQLiteDatabase db = this.getReadableDatabase();

        //Run the query
        Cursor cursor = db.rawQuery(selectStatement, null);
        Integer tID;
        Float aBST;
        String tN, pOn, pTw, pTh, pFo, pFi, pSi;

        //Add teams to array list
        if (cursor.moveToFirst()) {
            do {
                tID = cursor.getInt(cursor.getColumnIndex("teamID"));
                aBST = cursor.getFloat(cursor.getColumnIndex("averageBST"));
                tN = cursor.getString(cursor.getColumnIndex("trainerName"));
                pOn = cursor.getString(cursor.getColumnIndex("pkmnOne"));
                pTw = cursor.getString(cursor.getColumnIndex("pkmnTwo"));
                pTh = cursor.getString(cursor.getColumnIndex("pkmnThree"));
                pFo = cursor.getString(cursor.getColumnIndex("pkmnFour"));
                pFi = cursor.getString(cursor.getColumnIndex("pkmnFive"));
                pSi = cursor.getString(cursor.getColumnIndex("pkmnSix"));

                Team teamAdded = new Team(tID, aBST, tN, pOn, pTw, pTh, pFo, pFi, pSi);
                returnTeams.add(teamAdded);
            }
            while (cursor.moveToNext());
        }
        return returnTeams;
    }

    @SuppressLint({"Range", "Recycle"})
    public ArrayList<Pokemon> pkmnOnTeam(Integer teamID) {
        ArrayList<Pokemon> returnPkmn = new ArrayList<>();
        //Get Pokemon 1
        String selectStatement = "SELECT pkmnOne FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        String pokeName, pkType1, pkType2;
        Integer pkStats;

        //Add Pokemon to array list
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnOne"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        //Repeat for each Pokemon. This is Pokemon B.
        selectStatement = "SELECT pkmnTwo FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnTwo"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        //Pokemon C
        selectStatement = "SELECT pkmnThree FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnThree"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        //Pokemon D
        selectStatement = "SELECT pkmnFour FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnFour"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        //Pokemon E
        selectStatement = "SELECT pkmnFive FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnFive"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        //Final Pokemon
        selectStatement = "SELECT pkmnSix FROM " + teams_table_name + " WHERE teamID = " + teamID + ";";
        cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            pokeName = cursor.getString(cursor.getColumnIndex("pkmnSix"));
            selectStatement = "SELECT * FROM " + pkmn_table_name + " WHERE pkmnName = '" + pokeName + "';";
            //Run new query
            cursor = db.rawQuery(selectStatement, null);
            if (cursor.moveToFirst()) {
                pkType1 = cursor.getString(cursor.getColumnIndex("typeOne"));
                pkType2 = cursor.getString(cursor.getColumnIndex("typeTwo"));
                pkStats = cursor.getInt(cursor.getColumnIndex("baseStatTotal"));

                Pokemon pkA = new Pokemon(pokeName, pkType1, pkType2, pkStats);
                returnPkmn.add(pkA);
            }
        }
        return returnPkmn;
    }

    @SuppressLint("Range")
    public ArrayList<Review> oneTeamsReviews(Team team) {
        Integer tID = team.getTeamID();
        Log.d("team ID:", tID + "");
        ArrayList<Review> listReviews = new ArrayList<>();
        String selectStatement = "Select * from " + reviews_table_name + " Where teamID = " + tID + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);

        Integer revID, revScore, teID;
        String uR;

        if (cursor.moveToFirst()) {
            do {
                revID = cursor.getInt(cursor.getColumnIndex("reviewID"));
                revScore = cursor.getInt(cursor.getColumnIndex("reviewScore"));
                teID = cursor.getInt(cursor.getColumnIndex("teamID"));
                uR = cursor.getString(cursor.getColumnIndex("userReviewing"));

                Review rev = new Review(revID, revScore, teID, uR);
                listReviews.add(rev);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return listReviews;
    }


    public boolean isReviewUnallowed(Team tV, String lU) {
        //tV stands for "Team Viewed." lU stands for "Logged User."
        //NOTE TO SELF REMOVE LOG STATEMENTS AFTER ENSURING THIS FUNCTION WORKS
        Log.d("String lU:", lU);
        boolean iRU = false;
        //Step 1: Check if the current user already reviewed this team.
        SQLiteDatabase db = this.getReadableDatabase();
        Integer tID = tV.getTeamID();
        Log.d("Integer tID:", tID + "");
        String teamMaker = tV.getUserTrainer();
        String selectStatement = "SELECT * FROM " + reviews_table_name + " WHERE teamID = " + tID + " AND userReviewing = '" + lU + "';";
        Log.d("Statement?", "Exists");
        Cursor cursor = db.rawQuery(selectStatement, null);
        Log.d("Cursor?", "Run");
        //Check if the person viewing the team is the creator or if the logged-in user has reviewed this team already
        if (cursor.moveToFirst()) {
            Integer count = cursor.getCount();
            if (count >= 1) {
                Log.d("condition 1:", "fulfilled");
                iRU = true;
            }
        }
        if (lU.equals(teamMaker)) {
            Log.d("condition 2:", "fulfilled");
            iRU = true;
        }
        db.close();
        return iRU;
    }

    //NOTE TO SELF NOT DONE
    /*public ArrayList<Integer> filterTeamIDs(String creator, String species, String type) {
        ArrayList<Integer> listIDs = new ArrayList<>();
        String selectStatement = "Select " + teams_table_name +".* from " + teams_table_name + " Where ";
        if (creator.isEmpty()) {
            selectStatement += "trainerName is not null ";
        }
        else {
            selectStatement += "trainerName = '" + creator + "'";
        }
        if(!species.isEmpty()) {
            selectStatement += " and pkmnOne = '" + species + "' or pkmnTwo = '" + species + "' or pkmnThree = '" + species + "' or pkmnFour = '" + species + "' or pkmnFive = '" + species + "' or pkmnFive = '" + species + "' or pkmnSix = '" + species + " ";
        }
    }*/

    public void addReview(Integer rID, Integer rS, Integer tID, String uR) {
        //rS = reviewScore, tID = teamID, uR = userReviewing
        SQLiteDatabase db = this.getWritableDatabase();
        String selectStatement = "INSERT INTO " + reviews_table_name + " (reviewID, reviewScore, teamID, userReviewing) VALUES (" + rID + ", " + rS + ", " + tID + ", '" + uR + "');";
        db.execSQL(selectStatement);
        db.close();
        Log.d("Review added?", "yes");
    }

    @SuppressLint("Range")
    public boolean oldPassCorrect(String uname, String oldPGuess) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT * FROM " + users_table_name + " WHERE username = '" + uname + "';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        String oldP = cursor.getString(cursor.getColumnIndex("password"));
        if (oldPGuess.equals(oldP)) {
            return true;
        }
        else {
            return false;
        }
    }

    //NOTE TO SELF NOT DONE
    @SuppressLint("Range")
    public void updatePass(String uname, String newPass) {
        //Step 1: Get writeable instance of user database
        SQLiteDatabase db = this.getWritableDatabase();

        String selectStatement = "SELECT password FROM " + users_table_name + " WHERE username = '" + uname + "';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        Log.d("query run?", "yes");

        if (cursor.moveToFirst()) {
            String updateStatement = "UPDATE " + users_table_name + " SET password = '" + newPass + "' WHERE username = '" + uname + "';";
            //Cursor newCursor = db.rawQuery(updateStatement, null);
            db.execSQL(updateStatement);

            //String nP = cursor.getString(cursor.getColumnIndex("password"));
            //NOTE TO SELF COMMENT THIS LINE OUT ONCE YOU'VE FIXED THE FUNCTION
            Log.d("New password:", newPass);
        }

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Team> chooseTeamToUpdate(String user) {
        ArrayList<Team> cTTU = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + teams_table_name + " WHERE trainerName = '" + user + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        Integer tID;
        Float aBST;
        String tN, pOn, pTw, pTh, pFo, pFi, pSi;
        if (cursor.moveToFirst()) {
            do {
                tID = cursor.getInt(cursor.getColumnIndex("teamID"));
                aBST = cursor.getFloat(cursor.getColumnIndex("averageBST"));
                tN = cursor.getString(cursor.getColumnIndex("trainerName"));
                pOn = cursor.getString(cursor.getColumnIndex("pkmnOne"));
                pTw = cursor.getString(cursor.getColumnIndex("pkmnTwo"));
                pTh = cursor.getString(cursor.getColumnIndex("pkmnThree"));
                pFo = cursor.getString(cursor.getColumnIndex("pkmnFour"));
                pFi = cursor.getString(cursor.getColumnIndex("pkmnFive"));
                pSi = cursor.getString(cursor.getColumnIndex("pkmnSix"));

                Team teamAdded = new Team(tID, aBST, tN, pOn, pTw, pTh, pFo, pFi, pSi);

                cTTU.add(teamAdded);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return cTTU;
    }

    public void updateTeam(Team tTU, Team uT) {
        Integer oldTeamID = tTU.getTeamID();
        String oldTrainerName = tTU.getUserTrainer();
        String oldPkmnA = tTU.getPkmnA();
        String oldPkmnB = tTU.getPkmnB();
        String oldPkmnC = tTU.getPkmnC();
        String oldPkmnD = tTU.getPkmnD();
        String oldPkmnE = tTU.getPkmnE();
        String oldPkmnF = tTU.getPkmnF();
        Float oldAvgBST = tTU.getAverageTotal();

        Integer newTeamID = uT.getTeamID();
        String newTrainerName = uT.getUserTrainer();
        String newPkmnA = uT.getPkmnA();
        String newPkmnB = uT.getPkmnB();
        String newPkmnC = uT.getPkmnC();
        String newPkmnD = uT.getPkmnD();
        String newPkmnE = uT.getPkmnE();
        String newPkmnF = uT.getPkmnF();
        Float newAvgBST = uT.getAverageTotal();

        //Create SQLite statement for query
        String selectStatement = "SELECT * FROM " + teams_table_name + " WHERE teamID = " + oldTeamID + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        /*if (cursor.moveToFirst()) {
            String updateStatement = "UPDATE " + teams_table_name + " SET averageBST = " + newAvgBST + ", pkmnOne = '" + newPkmnA + "', pkmnTwo = '" + newPkmnB + "', pkmnThree = '" + newPkmnC + "', pkmnFour = '" + newPkmnD + "', pkmnFive = '" + newPkmnE + "', pkmnSix = '" + newPkmnF + "' WHERE teamID = " + oldTeamID + ";";
            db.execSQL(updateStatement);
        }
        db.close();*/

        //Above code didn't work
        //Step 1: Create SQLite statement for updating
        if (cursor.moveToFirst()) {
            String updateStatement = "UPDATE " + teams_table_name + " SET averageBST = " + newAvgBST + ", pkmnOne = '" + newPkmnA + "', pkmnTwo = '" + newPkmnB + "', pkmnThree = '" + newPkmnC + "', pkmnFour = '" + newPkmnD + "', pkmnFive = '" + newPkmnE + "', pkmnSix = '" + newPkmnF + "', trainerName = '" + newTrainerName + "' WHERE teamID = " + oldTeamID + ";";
            db.execSQL(updateStatement);
        }

        db.close();
    }

    @SuppressLint("Range")
    public String oldPasswordCorrect(String user) {
        String oPC = "fillerStatement";
        String selectStatement = "SELECT password FROM " + users_table_name + " WHERE username = '" + user + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            do {
                oPC = cursor.getString(cursor.getColumnIndex("password"));
            }
            while (cursor.moveToNext());
        }
        db.close();
        Log.d("oPC:", oPC);
        return oPC;
    }

    @SuppressLint("Range")
    public ArrayList<Team> teamsOneUserReviewed(String username) {
        //Step one: Select teamIDs from all reviews a user has made.
        String selectStatement = "SELECT * FROM " + reviews_table_name + " WHERE userReviewing = '" + username + "';";
        ArrayList<Integer> teamIDs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);

        if (cursor.moveToFirst()) {
            do {
                Integer teamID = cursor.getInt(cursor.getColumnIndex("teamID"));
                teamIDs.add(teamID);
            }
            while (cursor.moveToNext());
        }

        ArrayList<Team> tOUR = new ArrayList<>();
        for (int i = 0; i < teamIDs.size(); i++) {
            selectStatement = "SELECT * FROM " + teams_table_name + " WHERE teamID = " + teamIDs.get(i) + ";";
            cursor = db.rawQuery(selectStatement, null);

            if (cursor.moveToFirst()) {
                Integer tID = i;
                Float aBST = cursor.getFloat(cursor.getColumnIndex("averageBST"));
                String tN = cursor.getString(cursor.getColumnIndex("trainerName"));
                String pOn = cursor.getString(cursor.getColumnIndex("pkmnOne"));
                String pTw = cursor.getString(cursor.getColumnIndex("pkmnTwo"));
                String pTh = cursor.getString(cursor.getColumnIndex("pkmnThree"));
                String pFo = cursor.getString(cursor.getColumnIndex("pkmnFour"));
                String pFi = cursor.getString(cursor.getColumnIndex("pkmnFive"));
                String pSi = cursor.getString(cursor.getColumnIndex("pkmnSix"));

                Team teamAtI = new Team(tID, aBST, tN, pOn, pTw, pTh, pFo, pFi, pSi);
                tOUR.add(teamAtI);
            }
        }
        db.close();
        return tOUR;
    }
}
