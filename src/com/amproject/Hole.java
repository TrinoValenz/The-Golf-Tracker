package com.amproject;

public class Hole {
    private int holeNumber;
    private int strokes;
    private int putts;
    private int chips;
    private int penalties;
    private boolean greenInRegulation;
    private String girMiss;
    private boolean fairwayHit;
    private String fhMiss;
    private int par;
    private Terms scoreResult;

    public Hole(int holeNumber, int strokes, int putts, int chips, int penalties, boolean greenInRegulation, String girMiss, boolean fairwayHit, String fhMiss, int par) {
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

    public Hole(int holeNumber, int strokes) {
        this.holeNumber = holeNumber;
        this.strokes = strokes;
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

    public String getGirMiss() {
        return girMiss;
    }

    public void setGirMiss(String girMiss) {
        this.girMiss = girMiss;
    }

    public boolean isFairwayHit() {
        return fairwayHit;
    }

    public void setFairwayHit(boolean fairwayHit) {
        this.fairwayHit = fairwayHit;
    }

    public String getFhMiss() {
        return fhMiss;
    }

    public void setFhMiss(String fhMiss) {
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

    public void setScoreResult(Terms scoreResult) {
        this.scoreResult = scoreResult;
    }

    public Terms calculateScore(){
        int scoreToPar = strokes - par;
        if(strokes==1){
            return Terms.HOLE_IN_ONE;
        }

        return switch (scoreToPar) {
            case -3 -> Terms.ALBATROSS;
            case -2 -> Terms.EAGLE;
            case -1 -> Terms.BIRDIE;
            case 0 -> Terms.PAR;
            case 1 -> Terms.BOGEY;
            case 2 -> Terms.DOUBLE_BOGEY;
            default -> Terms.TRIPLE_BOGEY_OR_WORSE;
        };
    }
}

