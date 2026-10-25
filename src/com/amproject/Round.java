package com.amproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Round {
    private String courseName;
    private LocalDate date;
    private List<Hole> holes = new ArrayList<>();
    private int score;
    private int slope;
    private double rating;
    private List<Club> clubs = new ArrayList<>();;
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
                return club;
            }
        }
        return null;
    }


    //methods for handicap
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


    //display output format
    private static String fit(String value, int maxLength) {
        if (value == null || value.isBlank()) {
            return "-";
        }

        if (value.length() <= maxLength) {
            return value;
        }

        return value.substring(0, maxLength - 3) + "...";
    }

    private String getScoreResultForDisplay(Hole hole) {
        if (hole.getPar() <= 0 || hole.getStrokes() <= 0) {
            return "Not scored";
        }

        hole.setScoreResult();

        Terms result = hole.getScoreResult();

        if (result == null) {
            return "Unknown";
        }

        return fit(result.name().replace('_', ' '), 22);
    }

    private String getClubNamesForDisplay(Hole hole) {
        if (hole.getClubList() == null || hole.getClubList().isEmpty()) {
            return "-";
        }

        StringJoiner names = new StringJoiner(", ");

        for (Club club : hole.getClubList()) {
            if (club != null
                    && club.getName() != null
                    && !club.getName().isBlank()) {
                names.add(club.getName());
            }
        }

        return fit(names.toString(), 24);
    }

    private String formatMiss(Misses miss) {
        if (miss == null) {
            return "-";
        }

        return fit(miss.name().replace('_', ' '), 13);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        String separator = "-".repeat(123);
        String border = "=".repeat(123);

        String ratingText = rating > 0
                ? String.format("%.1f", rating)
                : "N/A";

        String slopeText = slope > 0
                ? String.valueOf(slope)
                : "N/A";

        String rowFormat =
                "%-4s %3s %7s %-22s %5s %5s %4s %5s %-13s %7s %-13s %-24s%n";

        output.append("\n").append(border).append("\n");
        output.append("ROUND SCORECARD\n");
        output.append(border).append("\n");

        output.append("Course: ")
                .append(courseName == null ? "Not specified" : courseName)
                .append("\n");

        output.append("Date: ")
                .append(date == null ? "Not specified" : date)
                .append("\n");

        output.append("Course Rating: ").append(ratingText)
                .append(" | Slope: ").append(slopeText)
                .append(" | PCC: ").append(String.format("%.1f", pccAdjustment))
                .append("\n");

        if (holes == null || holes.isEmpty()) {
            output.append(separator).append("\n");
            output.append("No holes recorded yet.\n");
            output.append(border);
            return output.toString();
        }

        output.append("\nHOLE-BY-HOLE SCORECARD\n");
        output.append(separator).append("\n");

        output.append(String.format(
                rowFormat,
                "Hole", "Par", "Strokes", "Result",
                "Putts", "Chips", "Pen", "GIR",
                "GIR Miss", "Fairway", "FW Miss", "Clubs Used"
        ));

        output.append(separator).append("\n");

        int totalPar = 0;
        int totalStrokes = 0;
        int totalPutts = 0;
        int totalChips = 0;
        int totalPenalties = 0;
        int scoredHoleCount = 0;
        int holeCount = 0;

        for (Hole hole : holes) {
            if (hole == null) {
                continue;
            }

            holeCount++;

            String parText = hole.getPar() > 0
                    ? String.valueOf(hole.getPar())
                    : "-";

            String strokesText = hole.getStrokes() > 0
                    ? String.valueOf(hole.getStrokes())
                    : "-";

            String result = getScoreResultForDisplay(hole);

            String girText = hole.isGreenInRegulation()
                    ? "Yes"
                    : "No";

            String fairwayText = hole.isFairwayHit()
                    ? "Yes"
                    : "No";

            output.append(String.format(
                    rowFormat,
                    hole.getHoleNumber(),
                    parText,
                    strokesText,
                    result,
                    hole.getPutts(),
                    hole.getChips(),
                    hole.getPenalties(),
                    girText,
                    formatMiss(hole.getGirMiss()),
                    fairwayText,
                    formatMiss(hole.getFhMiss()),
                    getClubNamesForDisplay(hole)
            ));

            if (hole.getPar() > 0) {
                totalPar += hole.getPar();
            }

            if (hole.getStrokes() > 0) {
                totalStrokes += hole.getStrokes();
                scoredHoleCount++;
            }

            totalPutts += hole.getPutts();
            totalChips += hole.getChips();
            totalPenalties += hole.getPenalties();
        }

        output.append(separator).append("\n");

        output.append(String.format(
                rowFormat,
                "TOT.", totalPar, totalStrokes, "",
                totalPutts, totalChips, totalPenalties,
                "", "", "", "", ""
        ));

        output.append(separator).append("\n");

        output.append("Holes recorded: ")
                .append(holeCount).append("\n");

        output.append("Holes with scores: ")
                .append(scoredHoleCount).append("\n");

        output.append("Gross score (recorded holes): ");

        if (scoredHoleCount > 0) {
            output.append(totalStrokes).append("\n");
        } else {
            output.append("Not recorded\n");
        }

        if (adjustedGrossScore > 0) {
            output.append("Adjusted Gross Score: ")
                    .append(adjustedGrossScore).append("\n");
        }

        output.append(border);

        return output.toString();
    }

}
