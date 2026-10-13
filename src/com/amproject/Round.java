package com.amproject;

import java.time.LocalDate;
import java.util.List;

public class Round {
    private String courseName;
    private LocalDate date;
    private List<Hole> holes;
    private int score;
    private int slope;
    private double rating;

    public Round(String courseName, LocalDate date, List<Hole> holes, int slope, double rating) {
        this.courseName = courseName;
        this.date = date;
        this.holes = holes;
        this.slope = slope;
        this.rating = rating;
    }

    public Round(String courseName, LocalDate date) {
        this.courseName = courseName;
        this.date = date;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Hole> getHoles() {
        return holes;
    }

    public void setHoles(List<Hole> holes) {
        this.holes = holes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
