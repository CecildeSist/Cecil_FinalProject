package com.example.cecil_finalproject;

import android.content.Context;
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
        super(c, database_name, null, 1);
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

    public String getUsers_table_name() {
        return users_table_name;
    }


}
