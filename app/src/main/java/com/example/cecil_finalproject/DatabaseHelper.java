package com.example.cecil_finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "teambuilderApp.db";
    private static final String users_table_name = "Users";
    //NOTE TO SELF Android Studio could struggle with the accented "e" in "Pokemon." Use a regular "e" instead!
    private static final String pkmn_table_name = "Pokemon";
    private static final String teams_table_name = "Teams";
    private static final String reviews_table_name = "Reviews";

    public DatabaseHelper(Context c) {
        super(c, database_name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + users_table_name + " (username varchar(50) primary key not null, password varchar(50));");
        //I realized the availability booleans the game version tags aren't needed. I already allowed Pokemon who can only be obtained by trading, so any
        //Pokemon's availability could be increased via trading.
        db.execSQL("CREATE TABLE " + pkmn_table_name + " (pkmnName varchar(10) primary key not null, typeOne varchar(9), typeTwo varchar(17), baseStatTotal integer);");
        db.execSQL("CREATE TABLE " + teams_table_name + " (teamName varchar (50) primary key not null, averageBST float, foreign key (username) references " + users_table_name + " (username), foreign key (pkmnOne) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnTwo) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnThree) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnFour) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnFive) references " + pkmn_table_name + " (pkmnName), foreign key (pkmnSix) references " + pkmn_table_name + " (pkmnName));");
        db.execSQL("CREATE TABLE " + reviews_table_name + " (reviewID integer primary key autoincrement not null, reviewScore integer, foreign key (teamReviewed) references " + teams_table_name + " (teamName), foreign key (userReviewing) references " + users_table_name + " (username));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + users_table_name + ";");
        //NOTE TO SELF do we have to drop the Pokemon table since it never gets upgraded?
        db.execSQL("DROP TABLE IF EXISTS " + teams_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + reviews_table_name + ";");
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

    //We only need to initialize the table listing Pokemon because that's the only one to contain data when the program is run for the first time ever
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
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Ninetails', 'Fire', 'No secondary type', 424);");
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
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Kangashkan', 'Normal', 'No secondary type', 410);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Seadra', 'Water', 'No secondary type', 395);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Seaking', 'Water', 'No secondary type', 385);");
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Starmie', 'Water', 'Psychic', 435);");
            //Removed the period from Mr. Mime’s name to prevent code errors
            db.execSQL("INSERT INTO " + pkmn_table_name + " (pkmnName, typeOne, typeTwo, baseStatTotal) VALUES ('Mister Mime', 'Psychic', 'No secondary type', 340);");
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

    public int countRecordsFromTable(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();
        return numRows;
    }

    //Used to determine if a user's log-in attempt was successful
    public boolean logInValid(String uNameEntered, String pWordEntered) {
        SQLiteDatabase db = this.getReadableDatabase();
        //See if username exists
        String selectStatement = "SELECT count(username) FROM " + users_table_name + " WHERE username = '" + uNameEntered + "';";
        //Run the query
        Cursor cursor = db.rawQuery(selectStatement, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if(count != 0) {
            selectStatement = "SELECT count(username) FROM " + users_table_name + " WHERE username = '" + uNameEntered + "' AND password = ''" + pWordEntered + "';";
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
        }
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
        if (count == 0) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }

    //Also used in the register activity
    public void addNewUser (String newUName, String newPWord) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Add new user
        db.execSQL("INSERT INTO " + users_table_name + " (username, password) VALUES ('" + newUName + "', '" + newPWord + "');");
        db.close();
    }
}
