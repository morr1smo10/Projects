import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneGame {
    private Player player1;
    private Player player2;
    private Player whoseTurn;
    private Scanner scnr;
    private String[] hiddenPhrases;
    private StringBuffer shownPhrase;
    private String hiddenPhrase;
    private int numPhrases;
    private Wheel wheel;
    private int spinValue;

    //this is the method that would be directly used in the "main"
    public WheelOfFortuneGame(int n, String fileName) throws IOException {
        /*The constructor method reads in the puzzles and initializes data fields. It
        calls playMatch(). A match is multiple games. */

        //Read in the text files of puzzles
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        fileByteStream = new FileInputStream(fileName);
        inFS = new Scanner(fileByteStream);

        //here I constucted a string array, and each element in the array is the sentence of phrase readed from file
        hiddenPhrases = new String[n];
        for (int i =0; i<n; i++){
            String line = inFS.nextLine();
            hiddenPhrases[i] = line;
        }

        //numPhrases is the number of phrases that we want to play in this file
        numPhrases = n;

        //We have to determine the name of players, such as Jim, Jen
        Scanner scnr = new Scanner(System.in);
        System.out.println("Type in the name 1:");
        String name1 = scnr.next();
        //constructor
        player1 = new Player(name1);
        System.out.println("Type in the name 2:");
        String name2 = scnr.next();
        //constructor
        player2 = new Player(name2);

        //randomly determine who goes first
        Random rand = new Random();
        int sequence = rand.nextInt(2)+1;
        if (sequence==1){
            //if player1 go first, set whoseTurn to be player1
            whoseTurn = player1;
            System.out.println(name1+", you go first");
        }
        else{
            whoseTurn = player2;
            System.out.println(name2+", you go first");
        }

        playMatch();
    }

    public void playMatch() {
        int player1money = 0;
        int player2money = 0;
        //Keep playing games as long as there are more puzzles to do and the players want to continue
        for (int i =0; i<numPhrases; i++){
            hiddenPhrase = hiddenPhrases[i];
            play(i);

            //accumulate the temp money to the final money after each play
            player1money = player1money + player1.getMoneyForGameTemp();
            player1.setMoneyForGameFinal(player1money);

            player2money = player2money + player2.getMoneyForGameTemp();
            player2.setMoneyForGameFinal(player2money);


            System.out.println(player1.getName() + ", you have won $" + player1.getMoneyForGameFinal() + " so far.");
            System.out.println(player2.getName() + ", you have won $" + player2.getMoneyForGameFinal() + " so far.");
            System.out.println();

            //this if statement means if all of the phrases are played, then quit the game
            if(i==numPhrases-1){
                return;
            }

            //if they say no, just quit the game
            System.out.println("Do you want to play another game? yes or no");
            Scanner scnr = new Scanner(System.in);
            String status = scnr.next();
            if (status.equals("no")) {
                return;
            }
        }

    }

    private boolean isConsonant(char c) {
        return (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u');
    }

    public void play(int n) {
        /*This method is the overview of playing one game. It contains a loop
        for switching between one player's turn and another until the puzzle is
        solved and the game is over. Then execution returns to playMatch after one
        game is played, where the players can say they want to play another match,
        which can be done as long as there are puzzles left.
         */

        //Initialize whatever needs to be reset at the beginning of each game
        //set temp money to zero at a start of new phrase
        player1.setMoneyForGameTemp(0);
        player2.setMoneyForGameTemp(0);

        //Make a shownPhrase with the same as the hiddenPhrase
        shownPhrase = new StringBuffer(hiddenPhrases[n]);

        //initializeShownPhrase (make to all subscores or blanks)
        initializeShownPhrase();

        //Let player 1 and player 2 alternate taking turns until someone solves the puzzle
        boolean playCondition = true;
        int i;
        while(playCondition) {
            i = 2;

            //this while loop means if player's turn is not over, then the player keep playing
            while (i == 2) {
                i = takeTurn();
            }

            // handle when a player guesses correct phrase
            if (i == 0) {
                playCondition = false;
                if (whoseTurn.getName().equals(player1.getName())){
                    player2.setMoneyForGameTemp(0);
                }
                else{
                    player1.setMoneyForGameTemp(0);
                }
            }

            //so this is the situation when one people's turn is over, we let the other take turn
            if (whoseTurn.getName().equals(player1.getName())) {
                player1 = whoseTurn; //have to update the playerx always
                whoseTurn = player2;
            } else {
                player2 = whoseTurn;
                whoseTurn = player1;
            }
        }
    }

    public int takeTurn() {
        /*takeTurn represents the turn of one player. It has a loop in it because
        a player can keep guessing letters until they make a mistake, spin a "Bankrupt"
        on the wheel, or spin a "Lose a Turn".
        Tip: Wherever you are in a loop, you can drop out the loop entirely with "return"
        in the cases where this makes sense.
         */

        /*One way to make this work:
        Return 2 from a case below means that the player's turn is not over
        Return 1 means that the player's turn is over
        Return 0 means that the puzzle is solved*/

        /*loop for a player who keeps guessing letters correctly
        {
            spin the wheel
            consider "bankrupt" or "lose a turn" cases
            otherwise allow the player to choose from the three options
                case 1: solveThePuzzle
                case 2: guessAVowel
                case 3: guessAConsonant
                return 0, 1, or 2 to play() as described abovce
         */

        //lets spin the wheel
        wheel = new Wheel();
        spinValue = wheel.spinWheel();

        //Setup
        System.out.println("It's "+whoseTurn.getName()+"'s turn");
        System.out.println(player1.getName()+", you have $"+player1.getMoneyForGameTemp());
        System.out.println(player2.getName()+", you have $"+player2.getMoneyForGameTemp());
        System.out.println(shownPhrase);

        //Bankrupt
        if (spinValue==-1){
            System.out.println("OH NO!!! YOU WENT BANKRUPT");
            System.out.println();
            whoseTurn.setMoneyForGameTemp(0);
            return 1;
        }

        //Lose
        if(spinValue == -2){
            System.out.println("Oh, dear. You spun Lose a Turn!");
            System.out.println("SORRY!! You lose a turn!");
            System.out.println();
            return 1;
        }

        //setup
        System.out.println(whoseTurn.getName()+", you spun $"+spinValue);
        System.out.println("What do you want to do?");
        System.out.println("1. SolveThePuzzle.");
        System.out.println("2. Buy a vowel.");
        System.out.println("3. Guess a consonant.");
        Scanner scnr = new Scanner(System.in);
        int guess = scnr.nextInt();

        //choose method
        switch(guess){
            case 1:
                if(solveThePuzzle()==1){
                    return 1;
                }
                else{
                    return 0;
                }

            case 2:
                if(guessAVowel()==1){
                    return 1;
                }
                else{
                    return 2;
                }

            case 3:
                if(guessAConsonant()==1){
                    return 1;
                }
                else{
                    return 2;
                }

        }


        return -1; //this is just a placeholder
    }

    /////////////////////
    public int solveThePuzzle() {
        /*If the player guesses the puzzle correctly, the player gets all the money
        they accumulated so far in this game, and the others win nothing for this game.
        Then a 0 is returned to takeTurn(), meaning that the puzzle has been
        solved; otherwise 1 is returned, meaning that the player's turn is over.*/
        System.out.println("What is your solution?");
        Scanner scnr = new Scanner(System.in);
        String guess = scnr.nextLine();

        //if solve the puzzle, return 0, otherwise return 1
        if(guess.equals(hiddenPhrase)){
            System.out.println("That's right!!!");
            System.out.println("You've won $" + whoseTurn.getMoneyForGameTemp());
            System.out.println();
            return 0;
        }
        else{
            System.out.println("No, you're wrong. Your turn is over.");
            System.out.println();
            return 1;
        }


    }

    public int guessAVowel() {
        /*If the player doesn't have $250 (in the current game's
        winnings to pay for the vowel, or the player guesses a
        consonant instead of a vowel, or the player guesses a vowel that was
        already guessed, or the player guesses a vowel that's not in the
        puzzle, 1 is returned, meaning the player's turn is over.
        Otherwise the player pays $250 out of this games winnings so far,
        and 2 is returned, meaning that the player's turn continues.
        (No money is awarded for the vowels present in the word.)*/

        //If the player doesn't have $250
        if (whoseTurn.getMoneyForGameTemp()<=250 ){
            System.out.println("You don't have enough money to buy a vowel.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }
        System.out.println("What vowel are you guessing?");
        Scanner scnr = new Scanner(System.in);
        Character guess = scnr.next().charAt(0);
        boolean check = false;

        //the player guesses a consonant instead of a vowel
        if (!(guess.equals('a')||guess.equals('e')||guess.equals('i')||guess.equals('o')||guess.equals('u'))){
            System.out.println("Not a vowel.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }


        /*
        // creates an a string that is empty
        // ex: the wizard of oz --> ___ _______ __ __
        String temp = hiddenPhrase.toLowerCase();
        for(char i = 'a'; i < 'z'; i++){
            temp.replace(i, '_');
        }

         */

        //the player guesses a vowel that was already guessed
        for (char secretLetter : shownPhrase.toString().toCharArray()){
            if (guess.equals(secretLetter)){
                check = true;
            }
        }
        if (check){
            System.out.println("The vowel is already guessed.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }

        //the player the player guesses a vowel that's not in the puzzle
        check = false;
        for (char secretLetter : hiddenPhrase.toCharArray()){
            if (guess.equals(secretLetter)){
                check = true;
            }
        }
        if (!check){
            System.out.println("The vowel is not in the puzzle.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }

        // add a for loop that updates shownphrase with the guess
        for(int i =0; i< shownPhrase.length(); i++){
            if(guess == hiddenPhrase.charAt(i)){
                String guessFiller = guess.toString();
                shownPhrase.replace(i, i+1, guessFiller);
            }
        }

        //success
        whoseTurn.setMoneyForGameTemp(whoseTurn.getMoneyForGameTemp()-250);
        System.out.println(whoseTurn.getName()+", you have $"+whoseTurn.getMoneyForGameTemp());
        System.out.println();
        return 2;


    }

    public int guessAConsonant() {
        /*If the player guesses a vowel instead of a consonant, or the player guesses a
        consonant that was already guessed or the player guesses a consonant that
        is not in the puzzle, 1 is returned to takeTurn(), meaning the player's turn is over.
        Otherwise, the player gets numInstances * spinValue dollars added
        to their winnings so far for the game, and 2 is returned to takeTurn(), meaning
        that the player's turn continues.*/
        System.out.println("What consonant are you guessing?");
        Scanner scnr = new Scanner(System.in);
        Character guess = scnr.next().charAt(0);
        boolean check = false;

        //the player guesses a vowel instead of a consonant
        if (guess.equals('a')||guess.equals('e')||guess.equals('i')||guess.equals('o')||guess.equals('u')){
            System.out.println("Not a consonant.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }

        //the player guesses a consonant that was already guessed
        for (char secretLetter : shownPhrase.toString().toCharArray()){
            if (guess.equals(secretLetter)){
                check = true;
            }
        }
        if (check){
            System.out.println("The consonant is already guessed.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }

        //the player guesses a consonant that is not in the puzzle
        check = false;
        for (char secretLetter : hiddenPhrase.toCharArray()){
            if (guess.equals(secretLetter)){
                check = true;
            }
        }
        if (!check){
            System.out.println("The consonant is not in the puzzle.");
            System.out.println("Your turn is over.");
            System.out.println();
            return 1;
        }

        // add a for loop that updates shownphrase with the guess
        for(int i =0; i< shownPhrase.length(); i++){
            if(guess == hiddenPhrase.charAt(i)){
                String guessFiller = guess.toString();
                shownPhrase.replace(i, i+1, guessFiller);
            }
        }

        //success
        int count = 0;
        for (char secretLetter : hiddenPhrase.toCharArray()){
            if (guess.equals(secretLetter)){
                count++;
            }
        }

        whoseTurn.setMoneyForGameTemp(whoseTurn.getMoneyForGameTemp()+count*spinValue);
        System.out.println(whoseTurn.getName()+", you have $"+whoseTurn.getMoneyForGameTemp());
        System.out.println();
        return 2;


    }

    public void initializeShownPhrase() {
        //Initialize shown phase with underscores and blanks
        /*Tip: You might want to use a StringBuffer rather than a String
        for the hiddenPhrase so that you can use the replace method to
        replace a character = ' ' or '_'
         */

        //this makes the letter into subscore and the blank is still blank
        for (int i=0; i<hiddenPhrase.length(); i++){
            if (Character.isLetter(hiddenPhrase.substring(i).charAt(0))){
                shownPhrase.replace(i,i+1,"_");
            }
            if (hiddenPhrase.substring(i).equals(" ")){
                shownPhrase.replace(i,i+1," ");
            }
        }
    }
}
