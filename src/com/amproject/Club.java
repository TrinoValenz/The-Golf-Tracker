package com.amproject;

import java.util.List;

public class Club {
    private String name;
    private List<Double> trackedDistance;

    public Club(String name, List<Double> trackedDistance) {
        this.name = name;
        this.trackedDistance = trackedDistance;
    }
    public Club(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getTrackedDistance() {
        return trackedDistance;
    }

    public void setTrackedDistance(List<Double> trackedDistance) {
        this.trackedDistance = trackedDistance;
    }
}
