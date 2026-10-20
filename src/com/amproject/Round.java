package com.amproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private String courseName;
    private LocalDate date;
    private List<Hole> holes = new ArrayList<>();
    private int score;
    private int slope;
    private double rating;
    private List<Club> clubs;
    private int adjustedGrossScore;
    private double pccAdjustment = 0.0;

    public Round(String courseName, LocalDate date,  double rating, int slope) {
        this.courseName = courseName;
        this.date = date;
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

    public void setScore() {
        int total = 0;

        for (Hole hole : holes) {
            total += hole.getStrokes();
        }

        this.score = total;
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

    @Override
    public String toString() {
        return "Round{" +
                "courseName='" + courseName + '\'' +
                ", date=" + date +
                ", holes=" + holes.get(0).toString() +
                ", score=" + score +
                ", slope=" + slope +
                ", rating=" + rating +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public Club getClub(String clubName) {
        for (Club club : clubs) {
            if(club.getName().equals(clubName)) {
                System.out.println("Found it");
                return club;
            }
        }
        return null;
    }
    public int getAdjustedGrossScore() {
        return adjustedGrossScore;
    }

    public void setAdjustedGrossScore(int adjustedGrossScore) {
        this.adjustedGrossScore = adjustedGrossScore;
    }

    public double getPccAdjustment() {
        return pccAdjustment;
    }

    public void setPccAdjustment(double pccAdjustment) {
        this.pccAdjustment = pccAdjustment;
    }

    public double calculateScoreDifferential() {

        if (adjustedGrossScore <= 0) {
            throw new IllegalStateException(
                    "Adjusted gross score has not been entered."
            );
        }

        if (rating <= 0 || slope <= 0) {
            throw new IllegalStateException(
                    "Course rating and slope must be valid."
            );
        }

        double differential =
                (113.0 / slope)
                        * (adjustedGrossScore - rating - pccAdjustment);

        return Math.round(differential * 10.0) / 10.0;
    }


}
