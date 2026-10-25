package com.amproject;

import java.util.ArrayList;
import java.util.List;

public class GolfBag {
    private List<Club> clubs = new ArrayList<>();

    public GolfBag(List<Club> clubs) {
        this.clubs = clubs;
    }
    public GolfBag() {}

    public List<Club> getClubs() {
        return clubs;
    }

    public Club getClub(String clubName) {
        for (Club club : clubs) {
           if(club.getName().equals(clubName)) {
               return club;
           }
        }
        return null;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    //fill golf bag with standard 14 set
    public void fillStandardBag(){
        String[] clubs = {"Putter", "60-Degree Wedge", "56-Degree Wedge", "52-Degree Wedge", "Pitching Wedge", "9-Iron", "8-Iron", "7-Iron", "6-Iron" ,"5-Iron", "4-Iron", "5 wood","3 Wood", "Driver" };
        ArrayList<Club> clubList = new ArrayList<>();
        for(int i = 0; i < clubs.length; i++){
            clubList.add(new Club(clubs[i]));
        }
        this.clubs = clubList;
    }

    @Override
    public String toString() {
        String bagList = "";
        for(int i = 0; i < clubs.size(); i++){
            bagList += clubs.get(i).getName();
            bagList += ",";
        }
        bagList = bagList.substring(0,bagList.length()-1);
        return bagList;
    }

    public void addClub(String name){
        Club club = new Club(name);
        clubs.add(club);
    }

    public void removeClub(String name){
        for (Club club : clubs) {
            if(club.getName().equals(name)) {
                clubs.remove(club);
                break;
            }
        }
    }


}
