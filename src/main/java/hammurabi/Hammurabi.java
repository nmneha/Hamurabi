package hammurabi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    void playGame() {
        int population = 100;
        int bushelsGrain = 2800;
        int acresLand = 1000;
        int landValueBushelsPerAcre = 19;
        int year = 0;
        int numberOfAcresToBuy = 0;
        int numberOfAcresToSell = 0;

        // numberOfAcresToBuy
        numberOfAcresToBuy = askHowManyAcresToBuy(landValueBushelsPerAcre, bushelsGrain);
        acresLand += numberOfAcresToBuy;
        bushelsGrain -= landValueBushelsPerAcre * numberOfAcresToBuy;

        // numberOfAcresToSell
        if (numberOfAcresToBuy == 0) {
            numberOfAcresToSell = askHowManyAcresToSell(acresLand);
            acresLand -= numberOfAcresToSell;
            bushelsGrain += landValueBushelsPerAcre * numberOfAcresToSell;
        }

        // reset temporary variables
        numberOfAcresToBuy = 0;
        numberOfAcresToSell = 0;

    }

    public int askHowManyAcresToBuy(int price, int bushelsGrain) {
        int trueFalse = 0;
        int input = 0;
        while (trueFalse == 0) {
            input = getNumber("How many acres would you like to buy?");
            if (input >= 0 && price * input <= bushelsGrain) {
                trueFalse = 1;
            } else if (input < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (price * input > bushelsGrain) {
                System.out.println("Invalid Input: Price Exceeds Bushels In Storage");
            }
        }
        return input;
    }

    public int askHowManyAcresToSell(int acresLand) {
        int trueFalse = 0;
        int input = 0;
        while (trueFalse == 0) {
            input = getNumber("How many acres would you like to sell?");
            if (input >= 0 && input <= acresLand) {
                trueFalse = 1;
            } else if (input < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (input > acresLand) {
                System.out.println("Invalid Input: Acres To Sell Exceeds Acres Owned");
            }
        }
        return input;
    }

        //Alissa's Methods int Starv to Immmigrants done so far
    public int starvationDeaths(int population, int bushelsFedToPeople) {

        //if more ppl are over fed grain =0
        int fedPpl = bushelsFedToPeople / 20;     // Each person needs 20 grains of bushels to surv
        int starvDeath = 0;

        if (fedPpl < population) {
            starvDeath = population - fedPpl;
        }
        return starvDeath;

    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        //return true if 45% of ppl starve
        double percentage = (double) howManyPeopleStarved / population; //double div by int =double
        boolean didMoreThan45PercDie = percentage < 0.45;


        return didMoreThan45PercDie;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage) {

        int immigtrantWave = (20 * acresOwned + grainInStorage) / (100 * population) + 1; // this call is already an int no need to cast

        return immigtrantWave;
    }

    public int harvest(int acres, int bushelsUsedAsSeed) {
        //get random int from 1-6

        return 0; // place holder
    }

    public int grainEatenByRats(int bushels) {
        return 0; // place holder
    }

    public int newCostOfLand() {
        return 0; // place holder
    }

}