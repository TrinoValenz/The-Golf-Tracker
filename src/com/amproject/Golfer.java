package com.amproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Golfer {
    private GolfBag golfBag = new GolfBag();
    private List<Round> rounds = new ArrayList<>();
    private String name;
    private  double handicap = 0.0;

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

    public void setHandicap(double handicap) {
        this.handicap = handicap;
    }
    public double getHandicap() {
        return handicap;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("========== GOLFER PROFILE ==========\n");
        result.append("Name: ").append(name).append("\n");
        result.append("Handicap: ").append(handicap).append("\n");

        result.append("\n---------- GOLF BAG ----------\n");
        if (golfBag != null) {
            result.append(golfBag);
        } else {
            result.append("No golf bag assigned.");
        }

        result.append("\n\n---------- ROUND HISTORY ----------\n");

        if (rounds == null || rounds.isEmpty()) {
            result.append("No rounds recorded.\n");
        } else {
            result.append("Total rounds: ").append(rounds.size()).append("\n\n");

            for (int i = 0; i < rounds.size(); i++) {
                result.append("Round ").append(i + 1).append(":\n");
                result.append("  ").append(rounds.get(i)).append("\n");
            }
        }

        result.append("===================================");

        return result.toString();
    }


    public double calculateHandicap() {

        if (rounds == null) {
            throw new IllegalStateException(
                    "No rounds have been recorded."
            );
        }

        // Step 1: Find completed 18-hole rounds with valid data.
        List<Round> eligibleRounds = new ArrayList<>();

        for (Round round : rounds) {
            if (round != null
                    && round.getDate() != null
                    && round.getHoles() != null
                    && round.getHoles().size() == 18
                    && round.getAdjustedGrossScore() > 0
                    && round.getRating() > 0
                    && round.getSlope() > 0) {

                eligibleRounds.add(round);
            }
        }

        // Step 2: Keep the 20 most recent eligible rounds.
        eligibleRounds.sort(
                Comparator.comparing(Round::getDate).reversed()
        );

        if (eligibleRounds.size() > 20) {
            eligibleRounds = new ArrayList<>(
                    eligibleRounds.subList(0, 20)
            );
        }

        int numberOfRounds = eligibleRounds.size();

        // Step 3: At least 3 eligible scores are required.
        if (numberOfRounds < 3) {
            throw new IllegalStateException(
                    "At least 3 completed 18-hole rounds "
                            + "are needed to calculate a Handicap Index."
            );
        }

        // Step 4: Calculate the Score Differential for each round.
        List<Double> differentials = new ArrayList<>();

        for (Round round : eligibleRounds) {
            differentials.add(
                    round.calculateScoreDifferential()
            );
        }

        // Step 5: Sort from lowest (best) to highest.
        differentials.sort(Comparator.naturalOrder());

        // Step 6: Determine how many differentials to use
        // and which adjustment applies under WHS Rule 5.2.
        int scoresToUse;
        double adjustment = 0.0;

        if (numberOfRounds == 3) {
            scoresToUse = 1;
            adjustment = -2.0;

        } else if (numberOfRounds == 4) {
            scoresToUse = 1;
            adjustment = -1.0;

        } else if (numberOfRounds == 5) {
            scoresToUse = 1;

        } else if (numberOfRounds == 6) {
            scoresToUse = 2;
            adjustment = -1.0;

        } else if (numberOfRounds <= 8) {
            scoresToUse = 2;

        } else if (numberOfRounds <= 11) {
            scoresToUse = 3;

        } else if (numberOfRounds <= 14) {
            scoresToUse = 4;

        } else if (numberOfRounds <= 16) {
            scoresToUse = 5;

        } else if (numberOfRounds <= 18) {
            scoresToUse = 6;

        } else if (numberOfRounds == 19) {
            scoresToUse = 7;

        } else {
            scoresToUse = 8;
        }

        // Step 7: Average the best differentials.
        double total = 0.0;

        for (int i = 0; i < scoresToUse; i++) {
            total += differentials.get(i);
        }

        double calculatedHandicap =
                (total / scoresToUse) + adjustment;

        // Step 8: Round to one decimal place.
        calculatedHandicap =
                Math.round(calculatedHandicap * 10.0) / 10.0;

        // Step 9: Apply the maximum Handicap Index of 54.0.
        this.handicap = Math.min(calculatedHandicap, 54.0);

        return this.handicap;
    }
}
