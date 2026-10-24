package com.amproject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Hole {
    private int holeNumber;
    private int strokes;
    private int putts;
    private int chips;
    private int penalties;
    private boolean greenInRegulation;
    private Misses girMiss;
    private boolean fairwayHit;
    private Misses fhMiss;
    private int par;
    private Terms scoreResult;
    private List<Club> clubList = new ArrayList<>();

    public Hole(int holeNumber, int strokes, int putts, int chips, int penalties, boolean greenInRegulation, Misses girMiss, boolean fairwayHit, Misses fhMiss, int par) {
        this.holeNumber = holeNumber;
        this.strokes = strokes;
        this.putts = putts;
        this.chips = chips;
        this.penalties = penalties;
        this.greenInRegulation = greenInRegulation;
        this.girMiss = girMiss;
        this.fairwayHit = fairwayHit;
        this.fhMiss = fhMiss;
        this.par = par;
    }

    public Hole(int holeNumber, int strokes, int par) {
        this.holeNumber = holeNumber;
        this.strokes = strokes;
        this.par = par;
    }
    //remove this later
    public Hole(int holeNumber) {
        this.holeNumber = holeNumber;
    }


    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    public int getStrokes() {
        return strokes;
    }

    public void setStrokes(int strokes) {
        this.strokes = strokes;
    }

    public int getPutts() {
        return putts;
    }

    public void setPutts(int putts) {
        this.putts = putts;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public boolean isGreenInRegulation() {
        return greenInRegulation;
    }

    public void setGreenInRegulation(boolean greenInRegulation) {
        this.greenInRegulation = greenInRegulation;
    }

    public Misses getGirMiss() {
        return girMiss;
    }

    public void setGirMiss(Misses girMiss) {
        this.girMiss = girMiss;
    }

    public boolean isFairwayHit() {
        return fairwayHit;
    }

    public void setFairwayHit(boolean fairwayHit) {
        this.fairwayHit = fairwayHit;
    }

    public Misses getFhMiss() {
        return fhMiss;
    }

    public void setFhMiss(Misses fhMiss) {
        this.fhMiss = fhMiss;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public Terms getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult() {
        int scoreToPar = strokes - par;
        Terms term = null;
        if(strokes==1){
            term = Terms.HOLE_IN_ONE;
            this.scoreResult = term;
        } else {
            switch (scoreToPar) {
                case -3 -> term = Terms.ALBATROSS;
                case -2 -> term = Terms.EAGLE;
                case -1 -> term = Terms.BIRDIE;
                case 0 -> term = Terms.PAR;
                case 1 -> term = Terms.BOGEY;
                case 2 -> term = Terms.DOUBLE_BOGEY;
                default -> term = Terms.TRIPLE_BOGEY_OR_WORSE;
            };
            this.scoreResult = term;
        }
    }

    public List<Club> getClubList() {
        return clubList;
    }

    private String getClubNamesForDisplay() {
        if (clubList == null || clubList.isEmpty()) {
            return "None recorded";
        }

        StringJoiner names = new StringJoiner(", ");

        for (Club club : clubList) {
            if (club != null
                    && club.getName() != null
                    && !club.getName().isBlank()) {
                names.add(club.getName());
            }
        }

        return names.length() == 0
                ? "None recorded"
                : names.toString();
    }

    @Override
    public String toString() {
        String result = "Not scored";

        if (par > 0 && strokes > 0) {
            setScoreResult();

            if (scoreResult != null) {
                result = scoreResult.name().replace('_', ' ');
            }
        }

        String parText = par > 0 ? String.valueOf(par) : "-";
        String strokesText = strokes > 0
                ? String.valueOf(strokes)
                : "-";

        String girMissText = girMiss == null
                ? "-"
                : girMiss.name().replace('_', ' ');

        String fairwayMissText = fhMiss == null
                ? "-"
                : fhMiss.name().replace('_', ' ');

        String separator = "-".repeat(46);

        return String.format(
                "%s%n" +
                        "HOLE %02d%n" +
                        "Par: %s | Strokes: %s | Result: %s%n" +
                        "Putts: %d | Chips: %d | Penalties: %d%n" +
                        "GIR: %s | GIR Miss: %s%n" +
                        "Fairway Hit: %s | Fairway Miss: %s%n" +
                        "Clubs Used: %s%n" +
                        "%s",
                separator,
                holeNumber,
                parText,
                strokesText,
                result,
                putts,
                chips,
                penalties,
                greenInRegulation ? "Yes" : "No",
                girMissText,
                fairwayHit ? "Yes" : "No",
                fairwayMissText,
                getClubNamesForDisplay(),
                separator
        );
    }
}

