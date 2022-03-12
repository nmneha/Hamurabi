package hammurabi;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int population = 100;
    int bushelsGrain = 2800;
    int acresLand = 1000;
    int landValueBushelsPerAcre = 19;

    public static void main(String[]  args) {

        new Hammurabi().playGame();

    }

    void playGame() {
        int population = 100;
        int bushelsGrain = 2800;
        int grossBushels = 3000;
        int acresLand = 1000;
        int landValueBushelsPerAcre = 19;
        int year = 0;
        int starved = 0;
        int immigrants = 5;
        int price = 3;
        int rats = 200;

        while (year <= 10) {
            System.out.println("O great Hammurabi!\n" +
                    "You are in year " + year + " of your ten year rule.\n" +
                    "In the previous year " + starved + " people starved to death.\n" +
                    "In the previous year " + immigrants + " people entered the kingdom.\n" +
                    "The population is now " + population + ".\n" +
                    "We harvested " + grossBushels + " bushels at " + price + " bushels per acre.\n" +
                    "Rats destroyed " + rats + " bushels, leaving " + bushelsGrain + " bushels in storage.\n" +
                    "The city owns " + acresLand + " acres of land.\n" +
                    "Land is currently worth " + landValueBushelsPerAcre + " bushels per acre.");
            System.out.println("\n");
            askHowManyAcresToBuy(price, bushelsGrain);


            break;

        }


    }

    int getNumber(String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    public int askHowManyAcresToBuy(int price, int bushelsGrain) {
//        System.out.println("How many acres would you like to buy?");
//        getNumber("My dear King Hammurabi, how many acres would you like to buy?");
//        int numberOfAcresToBuy = Integer.parseInt(scanner.nextLine());
        int numberOfAcresToBuy = getNumber("How many acres would you like ot buy?");
        acresLand += numberOfAcresToBuy;
        if(numberOfAcresToBuy >= 0) {

        } else {
            System.out.println("Invalid Entry: Negative Number");
            askHowManyAcresToBuy(price, bushelsGrain);
        }
        int bushelsSpent = price * numberOfAcresToBuy;
        if(bushelsSpent <= bushelsGrain) {

        } else {
            System.out.println("Invalid Entry: Price Exceeds Bushels In Storage");
            askHowManyAcresToBuy(price, bushelsGrain);
        }
        bushelsGrain -= bushelsSpent;
        System.out.println("You bought " + numberOfAcresToBuy + " acres.");
        System.out.println("You have " + bushelsGrain + " bushels left.");

//        scanner1.close();
        return (bushelsGrain);
    }



}
