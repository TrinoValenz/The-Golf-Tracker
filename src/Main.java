import com.amproject.Golfer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        // Get golfer and initiate golfer object
        System.out.print("Enter golfer name: ");
        String consoleCommand = scn.nextLine();
        Golfer golfer = new Golfer(consoleCommand);
        // prompt user for adding clubs to golf bag
        System.out.println("Do you want to add a standard golf bag or add your own clubs?");
        System.out.print("Press 1 for standard or 2 to add your own clubs ");
        consoleCommand = scn.nextLine();
        //add standard clubs to golf bag
        if(consoleCommand.equals("1")){
            golfer.getGolfBag().fillStandardBag();
        } else {
            // while loop to add manually add clubs to golf bag
            while(!consoleCommand.equals("Q") && !consoleCommand.equals("q")){
                System.out.print("Enter golf club name or enter Q when finished: ");
                consoleCommand = scn.nextLine();
                if(!consoleCommand.equals("Q") && !consoleCommand.equals("q")){
                    golfer.getGolfBag().addClub(consoleCommand);
                }
            }
        }
        //print golf bag
        System.out.println(golfer.getName() + "'s golf bag: ");
        System.out.println(golfer.getGolfBag());
;
        while (!consoleCommand.equals("Q")) {

        }




    }


    public static String menu(){
        return null;
    }



}
