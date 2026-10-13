package com.amproject;

import java.util.List;

public class GolfBag {
    private List<Club> clubs;

    public GolfBag(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
