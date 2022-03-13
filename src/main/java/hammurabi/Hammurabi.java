package hammurabi;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    private int population;
    private int bushelsGrain;
    private int acresLand;
    private int landValueBushelsPerAcre;
    private int year;
    private int peopleStarved;
    private int numberOfAcresToSell;
    private int numberOfAcresToBuy;
    private int immigrants;
    private int bushelsHarvested;
    private int oldAcreValue;
    private int bushelsEatenByRats;
    private int bushelsRemaining;


    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    public int getNumber(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    void playGame() {
        System.out.println("O great Hammurabi!\n" +
                "You are in year 1 of your ten year rule.\n" +
                "In the previous year 0 people starved to death.\n" +
                "In the previous year 5 people entered the kingdom.\n" +
                "The population is now 100.\n" +
                "We harvested 3000 bushels at 3 bushels per acre.\n" +
                "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
                "The city owns 1000 acres of land.\n" +
                "Land is currently worth 19 bushels per acre.\n");

        while (year <= 10) {
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

            year++;
            System.out.println("O great Hammurabi!\n" +
                    "You are in year " + (year + 1) + " of your ten year rule.\n" +
                    "In the previous year " + starvationDeaths(population, bushelsGrain) + " people starved to death.\n" +
                    "In the previous year " + immigrants(population, acresLand, bushelsGrain) + " people entered the kingdom.\n" +
                    "The population is now " + population + ".\n" +
                    "We harvested " + harvest(acresLand) + " bushels at " + landValueBushelsPerAcre + " bushels per acre.\n" +
                    "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
                    "The city owns " + acresLand + " acres of land.\n" +
                    "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");
        }


    }

    public void startValues() {
        year = 1;
        peopleStarved = 0;
        immigrants = 5;
        population = 100;
        bushelsGrain = 3000;
        oldAcreValue = 3;
        bushelsEatenByRats = 200;
        bushelsRemaining = 2800;
        acresLand = 1000;
        landValueBushelsPerAcre = 19;

    }

    public int askHowManyAcresToBuy(int price, int bushelsGrain) {
        int trueFalse = 0;
        int acresToBuy = 0;
        while (trueFalse == 0) {
            acresToBuy = getNumber("How many acres would you like to buy?");
            if (acresToBuy >= 0 && landValueBushelsPerAcre * acresToBuy <= bushelsGrain) {
                trueFalse = 1;
            } else if (acresToBuy < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (landValueBushelsPerAcre * acresToBuy > bushelsGrain) {
                System.out.println("Invalid Input: Price Exceeds Bushels In Storage");
            }
        }
        return acresToBuy;
    }

    public int askHowManyAcresToSell(int acresLand) {
        int trueFalse = 0;
        int acresToSell = 0;
        while (trueFalse == 0) {
            acresToSell = getNumber("How many acres would you like to sell?");
            if (acresToSell >= 0 && acresToSell <= acresLand) {
                trueFalse = 1;
            } else if (acresToSell < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (acresToSell > acresLand) {
                System.out.println("Invalid Input: Acres To Sell Exceeds Acres Owned");
            }
        }
        return acresToSell;
    }

    public int askHowMuchGrainToFeedPeople(int bushelsGrain) {
        int trueFalse = 0;
        int howMuchGrain = 0;
        while (trueFalse == 0) {
            howMuchGrain = getNumber("How many bushels of grain would you like to use to feed the people?");
            if (howMuchGrain >= 0 && howMuchGrain <= bushelsGrain) {
                trueFalse = 1;
            } else if (howMuchGrain < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (howMuchGrain > bushelsGrain) {
                System.out.println("Invalid Input: Input Exceeds Bushels In Storage");
            }
        }
        return howMuchGrain;
    }

    // TODO update bushelsGrain variable based on this output ^^^, add call to this method to main
    // howMuchGrainForFood
    // int grainForFood = askHowMuchGrainToFeedPeople(bushelsGrain)
    // bushelsGrain -= grainForFood;
    // <<grainForFood is a temporary value but we actually probably don't need to explicitly reset these>>

    public int askHowManyAcresToPlant(int acresLand, int population, int bushelsGrain) {
        int trueFalse = 0;
        int acresToPlant = 0;
        while (trueFalse == 0) {
            acresToPlant = getNumber("How many acres of grain would you like to plant?");
            if (acresToPlant >= 0 && acresToPlant <= acresLand &&
            bushelsGrain >= acresToPlant * 2 && acresToPlant <= population * 10) {
                trueFalse = 1;
            } else if (acresToPlant < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (acresToPlant > acresLand) { // start,,, add 2+ more else lines
                System.out.println("Invalid Input: Input Exceeds Acres Available");
            } else if (acresToPlant * 2 > bushelsGrain) {
                int out1 = acresToPlant * 2;
                System.out.println("Invalid Input: Input requires " + out1 + "bushels. " + bushelsGrain + " available.");
            } else if (acresToPlant > population * 10) {
                int out2 = acresToPlant / 10;
                System.out.println("Invalid Input: Input requires population size of " + out2 + ". " +
                        "Current population: " + population);
            }
        }
        return acresToPlant;
    }

    // TODO update bushelsGrain variable based on this output ^^^, add call to this method to main
    // howManyAcresToPlant
    // int howManyAcresToPlant = askHowManyAcresToPlant(acresLand, population, bushelsGrain);
    // bushelsGrain -= 2 * howManyAcresToPlant;
    // <<howManyAcresToPlant is a temporary value but we actually probably don't need to explicitly reset these>>

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

    public int harvest(int acres){ //int bushelsUsedAsSeed) {
        //get random int from 1-6
        int randNumber = rand.nextInt(7);
        randNumber +=1;                         //it takes 2 bushels of grain to farm an acre of land thats why bushelsUsedAsSeed /2
        int acresPlanted =acres;                //place holder for acres; 1st acres is unknown
        if(acres  > bushelsUsedAsSeed /2){      //u can have more land than seeds
             acresPlanted=bushelsUsedAsSeed/2;  // this is the relaity of not being able to plant all acres
        }
        //acres * rand# = Bushels of grain
        int BushlsOfGrain = acresPlanted * randNumber;
        return BushlsOfGrain;
    }

    public int grainEatenByRats(int bushels) {
        int grainsEaten=0;

        //Do we have a rat prob : Y/N
        // If Y then fig out grain eaten
         if(rand.nextInt(100) < 40 ){
            int  grainMax = rand.nextInt()*20; //get random # from 0-20
            grainMax += 10;                     // sets gran max to 30
          int grainPercentage = grainMax /100;   //makes % of  grains eaten
                                                //find out how much the rats eaten
           grainsEaten = grainPercentage * bushels;
        }
        return  grainsEaten;
         }

    public int newCostOfLand() {
        //price of land is random
        //btwn 17 - 23 bushels per acre
        int randomLandCost = rand.nextInt(23-17 +1)+17;     //used based calc from skeleton file
        System.out.println(randomLandCost);                         // Prints new land cost for user because its needed for next round
        return randomLandCost;                                      //returns in case the print isnt needed


    }

}