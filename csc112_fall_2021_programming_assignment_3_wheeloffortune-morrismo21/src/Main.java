/*Morris Mo
CSC112 Fall 2021
Programming Assignment 3
November q, 2021
This program implements a simplified Wheel of Fortune game.
*/


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /*To change which file you're reading, just change the arguments to
    the WheelOfFortuneGame constructor. That way, you don't always have
    to be prompted and type in the filename and the number of puzzles every
    time you run the program.
     */

    //the main method: reads in two files and determine the number of phrases from each file
    public static void main(String [] args) throws IOException {
        //for example, the following one is play the first four phrases in titles.txt
        WheelOfFortuneGame game1 = new WheelOfFortuneGame(5, "titles.txt");

        WheelOfFortuneGame game2 = new WheelOfFortuneGame(5, "titles2.txt");
    }
}