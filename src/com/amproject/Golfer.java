package com.amproject;

import java.util.List;

public class Golfer {
    private GolfBag golfBag;
    private List<Round> rounds;
    private String name;
    private  double handicap;

    public Golfer(GolfBag golfBag, List<Round> rounds, String name) {
        this.golfBag = golfBag;
        this.rounds = rounds;
        this.name = name;
    }
    public Golfer(String name) {
        this.name = name;
    }

    public GolfBag getGolfBag() {
        return golfBag;
    }

    public void setGolfBag(GolfBag golfBag) {
        this.golfBag = golfBag;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHandicap() {
        return handicap;
    }

    public void setHandicap(double handicap) {
        this.handicap = handicap;
    }

    @Override
    public String toString() {
        return "Golfer{" +
                "golfBag=" + golfBag +
                ", rounds=" + rounds +
                ", name='" + name + '\'' +
                ", handicap=" + handicap +
                '}';
    }
}
