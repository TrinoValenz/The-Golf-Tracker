package com.amproject;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private List<Double> trackedDistances = new ArrayList<>();

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
        return trackedDistances;
    }

    public void setTrackedDistance(List<Double> trackedDistance) {
        this.trackedDistances = trackedDistance;
    }


}
