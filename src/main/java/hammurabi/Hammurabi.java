package hammurabi;

import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        int population = 100;
        int bushelsGrain = 2800;
        int acresLand = 1000;
        int landValueBushelsPerAcre = 19;
        int year = 0;
        // "statements go after declarations" --- may the ones that reference other methods?
    }

    // other methods go here
}
