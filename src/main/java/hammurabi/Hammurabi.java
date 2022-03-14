package hammurabi;

import org.junit.Test;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

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
        //Main variables
        int year = 0;
        int population = 100;
        int acresLand = 1000;
        int bushelsGrain = 2800;
        int landValueBushelsPerAcre = 19;
        //Temp Variables
        int numberOfAcresToBuy = 0;
        int numberOfAcresToSell = 0;
        int grainFedToPeople = 0;
        int bushelsUsedAsSeed = 2;
        int acresPlanted = 0;
        //Randoms
        int peopleStarved = 0;
        int immigrants = 0;
        int oldAcreValue = landValueBushelsPerAcre;
        int eatenByRats = 0;
        int bushelsHarvested = 0;
        int diedFromPlague = 0;
        //Intro Summary
        System.out.println("My king, Hammurabi, you have been awarded the thrown!\n" +
                "You are in year 1 of your ten year rule.\n" +
                "In the previous year 0 people starved to death.\n" +
                "In the previous year 5 people entered the kingdom.\n" +
                "The population is now 100.\n" +
                "We harvested 3000 bushels at 3 bushels per acre.\n" +
                "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
                "The city owns 1000 acres of land.\n" +
                "Land is currently worth 19 bushels per acre.\n");
        //Loop Input of Game
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
            //How much grain is fed
            grainFedToPeople = askHowMuchGrainToFeedPeople(bushelsGrain);
            bushelsGrain -= grainFedToPeople;
            // Acres to Plant
            acresPlanted = askHowManyAcresToPlant(acresLand, population, bushelsGrain);
            bushelsGrain -= (acresPlanted * bushelsUsedAsSeed);
            //Deaths From Plague
            diedFromPlague = plagueDeaths(population);
            population -= diedFromPlague;
            //Starvation Deaths
            peopleStarved = starvationDeaths(population, grainFedToPeople);
            population -= peopleStarved;
            // uprising
            if (uprising(population, peopleStarved)) {
                break;
            }
            //IMMIGRANT
            if (peopleStarved == 0) {
                immigrants = immigrants(population, acresLand, bushelsGrain);
                population += immigrants;
            }
            //HARVEST
            bushelsHarvested = harvest(acresPlanted);
            bushelsGrain += bushelsHarvested;
            //RAT INFESTATION
            eatenByRats = grainEatenByRats(bushelsGrain);
            bushelsGrain -= eatenByRats;
            //Previous Year landValue
            oldAcreValue = landValueBushelsPerAcre;
            System.out.println(newCostOfLand());
            landValueBushelsPerAcre = newCostOfLand();

            year++;


            System.out.println("O great Hammurabi!\n" +
                    "You are in year " + year + " of your ten year rule.\n" +
                    "In the previous year " + peopleStarved + " people starved to death.\n" +
                    "In the previous year " + immigrants + " people entered the kingdom.\n" +
                    "The population is now " + population + ".\n" +
                    "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                    "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                    "The city owns " + acresLand + " acres of land.\n" +
                    "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");


            numberOfAcresToBuy = 0;
            numberOfAcresToSell = 0;
            grainFedToPeople = 0;
            bushelsUsedAsSeed = 2;
            acresPlanted = 0;
        }

        if (uprising(population, peopleStarved)) {
            if (year < 4) {
                System.out.println("O great Hammurabi!\n" +
                        "You are in year " + year + " of your ten year rule.\n" +
                        "In the previous year " + peopleStarved + " people starved to death.\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population is now " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");
            } else if (year >= 4 && year < 7) {
                System.out.println("O great Hammurabi!\n" +
                        "You are in year " + year + " of your ten year rule.\n" +
                        "In the previous year " + peopleStarved + " people starved to death.\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population is now " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");

            } else {
                System.out.println("O great Hammurabi!\n" +
                        "You are in year " + year + " of your ten year rule.\n" +
                        "In the previous year " + peopleStarved + " people starved to death.\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population is now " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");

            }
        }

        finalSummary(year, bushelsGrain, population, peopleStarved, immigrants, bushelsHarvested, oldAcreValue, eatenByRats, acresLand, landValueBushelsPerAcre);

    }

    // TODO add method call to main and toggle uprising if applicable
// (This will cause you to be immediately thrown out of office, ending the game.)
// // uprising
// boolean uprisingQuestionMark = uprising(population, howManyPeopleStarved);
// if (uprisingQuestionMark = true) { // syntax???
// <<INITIATE END OF GAME>>
//      System.out.println("YOU LOSE: More than 45% of your population has starved.");
//      System.exit(0);
// }
    public void finalSummary(int year, int bushelsGrain, int population, int peopleStarved,
                             int immigrants, int bushelsHarvested, int oldAcreValue, int eatenByRats,
                             int acresLand, int landValueBushelsPerAcre) {
        if (year == 10) {
            if (bushelsGrain > (population * 20)) {
                System.out.println("My great king, Hammurabi!\n" +
                        "You have reached the end of your ten year rule.\n" +
                        "In the previous year " + peopleStarved + " only people starved to death.\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population has now reached " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving a bountiful supply of " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");
            } else if (bushelsGrain == (population * 20)) {
                System.out.println("Hammurabi, your stretch is thin!\n" +
                        "You have reached the end of your ten year rule!\n" +
                        "But in the previous year " + peopleStarved + " people starved to death!\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population is now " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");

            } else if (bushelsGrain < (population * 20)) {
                System.out.println("O great Hammurabi!\n" +
                        "You are in year " + year + " of your ten year rule.\n" +
                        "In the previous year " + peopleStarved + " people starved to death.\n" +
                        "In the previous year " + immigrants + " people entered the kingdom.\n" +
                        "The population is now " + population + ".\n" +
                        "We harvested " + bushelsHarvested + " bushels at " + oldAcreValue + " bushels per acre.\n" +
                        "Rats destroyed " + eatenByRats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                        "The city owns " + acresLand + " acres of land.\n" +
                        "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.\n");

            }
        }
    }


    public int askHowManyAcresToBuy(int price, int bushelsGrain) {
        int trueFalse = 0;
        int acresToBuy = 0;
        while (trueFalse == 0) {
            acresToBuy = getNumber("How many acres would you like to buy?");
            if (acresToBuy >= 0 && price * acresToBuy <= bushelsGrain) {
                trueFalse = 1;
            } else if (acresToBuy < 0) {
                System.out.println("Invalid Input: Negative Number");
            } else if (price * acresToBuy > bushelsGrain) {
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

    public int plagueDeaths(int population) {
        int numberOfPlagueDeaths = 0;
        if (rand.nextInt(100) < 14) { // using 14 instead of 15 because nextInt includes 0 but not 100
            numberOfPlagueDeaths = population / 2;
        }
        return numberOfPlagueDeaths;
    }

// TODO update population variable based on this output ^^^, add call to this method to main
// // plagueDeaths
// int plagueDeaths = plagueDeaths(population);
// population -= plagueDeaths;

    public int starvationDeaths(int population, int grainFedToPeople) {

        //if more ppl are over fed grain =0
        int fedPpl = grainFedToPeople / 20;     // Each person needs 20 grains of bushels to surv
        int starvDeath = 0;

        if (fedPpl < population) {
            starvDeath = population - fedPpl;
        }
        return starvDeath;

    }

    public boolean uprising(int population, int howManyPeopleStarved) {
       double percentage = (double) howManyPeopleStarved / population; //double div by int =double
        return percentage > .45;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage) {

        int immigtrantWave = (20 * acresOwned + grainInStorage) / (100 * population) + 1; // this call is already an int no need to cast

        return immigtrantWave;
    }

    public int harvest(int acres) {
        //get random int from 1-6
        int randNumber = rand.nextInt(6);
        randNumber +=1;                         //it takes 2 bushels of grain to farm an acre of land thats why bushelsUsedAsSeed /2
//        int acresPlanted =acres;                //place holder for acres; 1st acres is unknown
//        if(acres  > bushelsUsedAsSeed /2){      //u can have more land than seeds
//             acresPlanted=bushelsUsedAsSeed/2;  // this is the relaity of not being able to plant all acres
//        }
        //acres * rand# = Bushels of grain
        int grainHarvested = acres * randNumber;
        return grainHarvested;
    }

    public int grainEatenByRats(int bushels) {
        int grainsEaten=0;
        if(rand.nextInt(100) > 60 ){

            grainsEaten = (rand.nextInt(21) + 10) * bushels / 100;
        }
        return  grainsEaten;
    }

    public int newCostOfLand() {
        //price of land is random
        //btwn 17 - 23 bushels per acre
        int randomLandCost = rand.nextInt(24-16 +1)+16;     //used based calc from skeleton file
        return randomLandCost;                                      //returns in case the print isnt needed
    }
}

