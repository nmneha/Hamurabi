package hammurabi;

import java.util.Random;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

public class Hammurabi {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    int population = 100;
    int bushelsGrain = 2800;
    int acresLand = 1000;
    int landValueBushelsPerAcre = 19;
    int year = 0;
    // "statements go after declarations" --- may the ones that reference other methods?

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    // public static String getStringInput(String prompt) {
        // Scanner scanner = new Scanner(System.in);
        // println(prompt);
        // String userInputString = scanner.nextLine();
        // return userInputString;
    // }

    void playGame() {

    }

    public int askHowManyAcresToBuy(int price, int bushels) {
        System.out.println("How many acres would you like to buy?");
        Scanner scanner1 = new Scanner(System.in);
        int numberOfAcresToBuy = Integer.parseInt(scanner1.nextLine());
        acresLand += numberOfAcresToBuy;
        if(numberOfAcresToBuy >= 0) {

        } else {
            System.out.println("Invalid Entry: Negative Number");
            askHowManyAcresToBuy(price, bushels);
        }
        int bushelsSpent = price * numberOfAcresToBuy;
        if(bushelsSpent <= bushelsGrain) {

        } else {
            System.out.println("Invalid Entry: Price Exceeds Bushels In Storage");
            askHowManyAcresToBuy(price, bushels);
        }
        bushelsGrain -= bushelsSpent;
        System.out.println("You bought " + numberOfAcresToBuy + "acres.");
        scanner1.close();
        return numberOfAcresToBuy;
    }

    // other methods go here
    // ask, update variables
}
