import com.amproject.GolfBag;
import com.amproject.Golfer;
import com.amproject.Hole;
import com.amproject.Round;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //create new golfer named bola
        Golfer bola = new Golfer("Bola");
        //created golf bag
        GolfBag golfBag = new GolfBag();
        //fill golf bag with standard 14 set and set it to golfer
        golfBag.fillStandardBag();
        bola.setGolfBag(golfBag);

        //creat round object and assign to Bola golfer
        LocalDate date = LocalDate.of(2026,10,11);
        Round riverRound = new Round("Pecan Valley River", date, 68.8,118);
        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(riverRound);

        // add club distance for driver
        ArrayList<Double> driverTracking = new ArrayList<>();
        driverTracking.add(268.0);
        bola.getGolfBag().getClub("Driver").setTrackedDistance(driverTracking);

        //create holes
        for(int i = 1; i < 19; i++) {
            Hole temp = new Hole(i);
            riverRound.getHoles().add(temp);
        }
        //add info fpr hole 1
        riverRound.getHoles().get(0).setStrokes(5);
        riverRound.getHoles().get(0).setPutts(0);
        riverRound.getHoles().get(0).setChips(2);
        riverRound.getHoles().get(0).setGreenInRegulation(true);
        riverRound.getHoles().get(0).setFairwayHit(true);
        riverRound.getHoles().get(0).setPar(4);
        riverRound.getHoles().get(0).setScoreResult();

        riverRound.setScore();


        //Test
        bola.setRounds(rounds);
        System.out.println(riverRound.toString());
        System.out.println(bola.toString());
        bola.getGolfBag().addClub("7 wood");
        System.out.println(bola.toString());
        bola.getGolfBag().removeClub("7 wood");
        System.out.println(bola.toString());
    }
}
