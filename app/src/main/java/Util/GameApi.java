package Util;

import android.app.Application;

public class GameApi extends Application {
    private String userID;
    private String email;
    private String userName;
    private int gems;
    private int electrons;
    private Double highScore;
    private String animals;
    private static GameApi instance;

    public static GameApi getInstance(){
        if(instance==null)
            instance= new GameApi();
        return instance;
    }

    public GameApi(){}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {return userName; }

    public void setUserName(String userName) {this.userName = userName; }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public Double getHighScore() {
        return highScore;
    }

    public void setHighScore(Double highScore) {
        this.highScore = highScore;
    }

    public int getElectrons() {
        return electrons;
    }

    public void setElectrons(int electrons) {
        this.electrons = electrons;
    }

    public String getAnimals() {
        return animals;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

}
