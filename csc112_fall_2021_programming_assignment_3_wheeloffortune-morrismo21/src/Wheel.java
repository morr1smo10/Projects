import java.util.ArrayList;
import java.util.Random;

public class Wheel {
    /*Spinning -1 means "Bankrupt. Spinning -2 means "Lose a Turn"*/

    private int[] moneyValues = {100, 200, 300, 400, 500, 600, 700, 900, 900, 10000, -1, -2};

    //this is a method to get a random number from the array above and return it
    public int spinWheel() {
        Random rand = new Random();
        int temp = rand.nextInt(moneyValues.length);
        int result = moneyValues[temp];
        return result;
    }
}