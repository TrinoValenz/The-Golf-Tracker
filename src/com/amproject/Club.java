package com.amproject;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private List<Integer> trackedDistances = new ArrayList<>();
    private double averageDistance;

    public Club(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTrackedDistance() {
        return trackedDistances;
    }

    public void setTrackedDistance(int distance) {
        trackedDistances.add(distance);
    }

    public void calculateAverage(){
        int total = 0;
         for(int distance: trackedDistances){
            total += distance;
        }
         averageDistance = (double) total / trackedDistances.size();

    }

    public double getAverageDistance() {
        return averageDistance;
    }
}
