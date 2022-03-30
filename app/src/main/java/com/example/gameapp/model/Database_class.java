package com.example.gameapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import javax.annotation.Nullable;

public class Database_class extends SQLiteOpenHelper {
    public Database_class(@Nullable Context context) {
        super(context,"UsersDB",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE If Not Exists Users (UserName text, HighScore Double, Gems text, Electrons text, Animals text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void CreateTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="CREATE TABLE If Not Exists Users (UserName text, HighScore Double, Gems text, Electrons text, Animals text);";
        db.execSQL(sql);
    }
    public void Insert(String userName, Double highScore, String gems,String electrons,
                          String animals)
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        String sql = "SELECT * FROM Users";
        Cursor c = db1.rawQuery(sql,null);
        if(!c.moveToNext()) {
            sql = "INSERT INTO Users (UserName, HighScore, Gems, Electrons, Animals) values ('"+userName+"',"+ highScore+", " +
                    "'"+gems+"','"+electrons+"','"+animals+"');";
            SQLiteDatabase db2 = this.getWritableDatabase();
            db2.execSQL(sql);
        }
    }
    public Cursor ReadData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Users";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return c;
    }

    public void DropTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Drop table Users";
        db.execSQL(sql);
    }

    public void UpdateHighScore(int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update Users set HighScore='"+Integer.toString(score)+"' Where UserName='Guest'";
        db.execSQL(sql);
    }
    public void UpdateGems(int gems)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update Users set Gems='"+Integer.toString(gems)+"' Where UserName='Guest'";
        db.execSQL(sql);
    }
    public void UpdateElectrons(int electrons)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update Users set Electrons='"+Integer.toString(electrons)+"' Where UserName='Guest'";
        db.execSQL(sql);
    }
    public void UpdateAnimals(String unlockedAnimals)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update Users set Animals='"+unlockedAnimals+"' Where UserName='Guest'";
        db.execSQL(sql);
    }

}
