package com.example.heriana.javaaplikasi.model;

import java.io.Serializable;

/**
 * Created by AKM on 7/2/2019.
 */
public class Score implements Serializable {

    private double score;
    private String userUID;

    private Score() {

    }
    private Score(double score, String userUID) {
        this.score = score;
        this.userUID = userUID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}
