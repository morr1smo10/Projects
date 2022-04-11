import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WOFFrame extends JFrame implements ActionListener{

    /* In this design of the game, the WOFFrame class is the class
    where you put the methods for playing the game.
     */

    /*First list your GUI objects, making them all private data fields of the class
    Note that these data fields are declared BEFORE (outside of) the constructor method.*/

    //For example

    //these are GUI related datafields defined
    private JLabel wofLabel;
    private JButton spinButton;
    private JButton consButton;
    private JTextField guessCons;
    private JTextField guessVowel;
    private JTextField solvePuzzle;
    private JLabel guessConsLabel;
    private JLabel guessVowelLabel;
    private JLabel solvePuzzleLabel;
    private JTextField puzzleText;
    private JLabel puzzleLabel;
    private JTextField winnerText;
    private JLabel playerLabel1;
    private JLabel playerLabel2;
    private JTextField gameMoneyText1;
    private JTextField gameMoneyText2;
    private JTextField matchMoneyText1;
    private JTextField matchMoneyText2;
    private JTextField resultOfSpin;
    private JLabel ResultOfSpin;
    private JButton vowelButton;
    private JButton solveButton;
    private JTextField turnText;
    private JLabel winGame1;
    private JLabel MatchWinner;
    private JLabel winGame2;
    private JTextField gameNumText;
    private JLabel winMatch1;
    private JLabel winMatch2;
    private JButton reinitialize;
    private JButton exit;
    private JTable table;

    //add more variables for GUI objects here

    /*Also, declare variables that are not GUI objects, but are places where you store
    values that are needed to play the game. For example:*/

    //other datafields are defined here
    private String[] puzzles = {"gone with the wind", "last of the mohicans", "lassie come home", "wizard of oz"};
    private int[] spin = {0, 1, 100, 200, 300, 400, 500, 600};
    private Player player1 = new Player(1);
    private Player player2 = new Player(2);
    private Player currentPlayer = null;
    private Player previousPlayer = null;
    String puzzle;
    int gameNum;
    int moneyWin = 0;
    StringBuffer shownPhrase;

    //this is for the JTable
    private String[] Names = {"",""};
    private Object[][] Datas = {
            {"Player1","Player2"},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""}
    };


    //Add more variables here

    public WOFFrame() {
        setTitle("Wheel of Fortune");
        ///Put your GUI objects in here, laid out with a GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints positionConst = new GridBagConstraints();

        /*Lay out your GUI objects in your own design
        and set the column and row numbers with gridx and gridy, respectively*/

        /*Here are three examples of inserting GUI objects. You don't have to use these.*/

        //this block for Wheel of fortune title
        positionConst.insets = new Insets(10, 10, 10, 10);
        positionConst.gridx = 1;
        positionConst.gridy = 0;
        wofLabel = new JLabel("Wheel of fortune");
        add(wofLabel, positionConst);

        //this block show the "shownpuzzle"
        positionConst.gridx = 1;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(0, 10, 0, 10);
        puzzleText = new JTextField(15);
        puzzleText.setEditable(false);
        add(puzzleText,positionConst);

        positionConst.insets = new Insets(0, 10, 10, 10);
        positionConst.gridx = 1;
        positionConst.gridy = 2;
        puzzleLabel = new JLabel("Puzzle");
        add(puzzleLabel,positionConst);

        //this block is the button "Spin"
        positionConst.gridx = 1;
        positionConst.gridy = 3;
        positionConst.insets = new Insets(10, 10, 10, 10);
        spinButton = new JButton("Spin");
        add(spinButton,positionConst);
        spinButton.addActionListener(this);

        //this block display the result of spin
        positionConst.gridx = 1;
        positionConst.gridy = 4;
        positionConst.insets = new Insets(10, 10, 0, 10);
        resultOfSpin = new JTextField(10);
        add(resultOfSpin,positionConst);
        resultOfSpin.setEditable(false);

        positionConst.gridx = 1;
        positionConst.gridy = 5;
        positionConst.insets = new Insets(0, 10, 10, 10);
        ResultOfSpin = new JLabel("Result of spin");
        add(ResultOfSpin,positionConst);

        //this block is the botton "Guess a consonant"
        positionConst.gridx = 0;
        positionConst.gridy = 6;
        positionConst.insets = new Insets(10, 10, 10, 10);
        consButton = new JButton("Guess a consonant");
        add (consButton,positionConst);
        consButton.addActionListener(this);

        //this block receive input of consonant
        positionConst.gridx = 1;
        positionConst.gridy = 6;
        positionConst.insets = new Insets(0, 10, 0, 10);
        guessCons = new JTextField(15);
        guessCons.setEditable(true);
        add(guessCons,positionConst);
        guessCons.addActionListener(this);

        positionConst.gridx = 1;
        positionConst.gridy = 7;
        positionConst.insets = new Insets(0, 10, 10, 10);
        guessConsLabel = new JLabel("Type a consonant");
        guessConsLabel.setOpaque(true);
        add(guessConsLabel,positionConst);

        //this block is the button "Buy a vowel"
        positionConst.gridx = 0;
        positionConst.gridy = 8;
        positionConst.insets = new Insets(10, 10, 10, 10);
        vowelButton = new JButton("Buy a vowel");
        add(vowelButton,positionConst);
        vowelButton.addActionListener(this);

        //this block recceive input of vowel
        positionConst.gridx = 1;
        positionConst.gridy = 8;
        positionConst.insets = new Insets(0, 10, 0, 10);
        guessVowel = new JTextField(15);
        guessVowel.setEditable(true);
        add(guessVowel,positionConst);
        guessVowel.addActionListener(this);

        positionConst.gridx = 1;
        positionConst.gridy = 9;
        positionConst.insets = new Insets(0, 10, 10, 10);
        guessVowelLabel = new JLabel("Type a vowel");
        guessVowelLabel.setOpaque(true);
        add(guessVowelLabel,positionConst);

        //this block is the button "Solve the puzzle"
        positionConst.gridx = 0;
        positionConst.gridy = 10;
        positionConst.insets = new Insets(10, 10, 10, 10);
        solveButton = new JButton("Solve the puzzle");
        add(solveButton,positionConst);
        solveButton.addActionListener(this);

        //this block receive input of solve puzzle
        positionConst.gridx = 1;
        positionConst.gridy = 10;
        positionConst.insets = new Insets(0, 10, 0, 10);
        solvePuzzle = new JTextField(15);
        solvePuzzle.setEditable(true);
        add(solvePuzzle,positionConst);
        solvePuzzle.addActionListener(this);

        positionConst.gridx = 1;
        positionConst.gridy = 11;
        positionConst.insets = new Insets(0, 10, 20, 10);
        solvePuzzleLabel = new JLabel("Type in the puzzle");
        solvePuzzleLabel.setOpaque(true);
        add(solvePuzzleLabel,positionConst);

        positionConst.gridx = 0;
        positionConst.gridy = 12;
        positionConst.insets = new Insets(10, 10, 0, 10);
        playerLabel1 = new JLabel("Player 1");
        playerLabel1.setOpaque(true);
        add(playerLabel1,positionConst);

        positionConst.gridx = 2;
        positionConst.gridy = 12;
        playerLabel2 = new JLabel("Player 2");
        playerLabel2.setOpaque(true);
        add(playerLabel2,positionConst);

        //as Player1 and Player2 blocks are set, I can apply initialsetup method, because method involve playerLabel1,2
        initialsetup();

        //this block display whose turn it is
        positionConst.gridx = 1;
        positionConst.gridy = 12;
        turnText = new JTextField("Player " + currentPlayer.getPlayerNum()+ ", it's your turn.");
        turnText.setEditable(false);
        add(turnText,positionConst);

        //this block display the money won in this game
        positionConst.gridx = 0;
        positionConst.gridy = 13;
        gameMoneyText1 = new JTextField(15);
        positionConst.insets = new Insets(10, 10, 0, 10);
        gameMoneyText1.setText("$0");
        add(gameMoneyText1,positionConst);
        gameMoneyText1.setEditable(false);

        positionConst.gridx = 2;
        positionConst.gridy = 13;
        gameMoneyText2 = new JTextField(15);
        gameMoneyText2.setText("$0");
        add(gameMoneyText2,positionConst);
        gameMoneyText2.setEditable(false);

        positionConst.gridx = 0;
        positionConst.gridy = 14;
        positionConst.insets = new Insets(0, 10, 10, 10);
        winGame1 = new JLabel("Winnings this game");
        add(winGame1,positionConst);

        //this show the number of current game
        positionConst.gridx = 1;
        positionConst.gridy = 14;
        gameNumText = new JTextField("Game" + gameNum);
        add(gameNumText,positionConst);
        gameNumText.setEditable(false);

        positionConst.gridx = 2;
        positionConst.gridy = 14;
        winGame2 = new JLabel("Winnings this game");
        add(winGame2,positionConst);

        //this block display the money won in this match
        positionConst.gridx = 0;
        positionConst.gridy = 15;
        matchMoneyText1 = new JTextField(15);
        positionConst.insets = new Insets(10, 10, 0, 10);
        matchMoneyText1.setText("$0");
        add(matchMoneyText1,positionConst);
        matchMoneyText1.setEditable(false);

        //this block display the winner's information
        positionConst.gridx = 1;
        positionConst.gridy = 15;
        winnerText = new JTextField(20);
        add(winnerText,positionConst);
        winnerText.setEditable(false);

        positionConst.gridx = 2;
        positionConst.gridy = 15;
        matchMoneyText2 = new JTextField(15);
        matchMoneyText2.setText("$0");
        add(matchMoneyText2,positionConst);
        matchMoneyText2.setEditable(false);

        positionConst.gridx = 0;
        positionConst.gridy = 16;
        positionConst.insets = new Insets(10, 10, 10, 10);
        winMatch1 = new JLabel("Winnings this match");
        add(winMatch1,positionConst);

        positionConst.gridx = 1;
        positionConst.gridy = 16;
        MatchWinner = new JLabel("Winner");
        add(MatchWinner,positionConst);

        positionConst.gridx = 2;
        positionConst.gridy = 16;
        winMatch2 = new JLabel("Winnings this match");
        add(winMatch2,positionConst);

        //this is the reinitialize game button
        positionConst.gridx = 1;
        positionConst.gridy = 17;
        positionConst.insets = new Insets(10, 10, 10, 10);
        reinitialize = new JButton("Reinitialize");
        add(reinitialize,positionConst);
        reinitialize.addActionListener(this);

        //this is the exit match button
        positionConst.gridx = 1;
        positionConst.gridy = 18;
        positionConst.insets = new Insets(10, 10, 10, 10);
        exit = new JButton("Exit");
        add(exit,positionConst);
        exit.addActionListener(this);

        //this is one of the "two other GUI element", JTable
        positionConst.gridx = 1;
        positionConst.gridy = 19;
        positionConst.insets = new Insets(10, 10, 10, 10);
        table = new JTable(Datas,Names);
        add(table, positionConst);
        table.setEnabled (false);

        initialize();


        /*BE SURE NOT TO REDECLARE VARIABLES WITH THE SAME NAMES AS THE PRIVATE DATA FIELDS, LIKE THIS:
        JButton solveButton = new JButton("SOLVE");
        IF YOU DO THIS, YOU ARE MAKING A LOCAL VARIABLE IN THE CONSTRUCTOR METHOD. IT IS NOT
        THE SAME AREA IN MEMORY AS THE DATA FIELD OF THE SAME NAME. IF YOU USE THE VARIABLE
        solveButton IN ANOTHER METHOD OF THE WOFFRAME CLASS, YOU WILL BE REFERRING TO THE DATA FIELD,
        NOT TO THIS LOCAL VARIABLE. YOU'LL GET A NULL REFERENCE ERROR BECAUSE YOU NEVER INSTANTIATED
        THE DATA FIELD solveButton.
         */

        //Note that the JLabel does not need an actionListener

    }

    /*Because you say that this class implements the interface ActionListener, you have to
    create an ActionPerformed method. This is where you can identify which object generated
    an event, like the clicking of a button or entering text into a JTextField*/


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spinButton) {
            //this make sure after clicking spinbutton, player can only choose guess a consonant
            vowelButton.setEnabled(false);
            solveButton.setEnabled(false);
            spinButton.setEnabled(false);
            consButton.setEnabled(true);
            doSpin();
        }
        else if (e.getSource() == consButton){
            //this make sure after clickiing guessconsonant, player can only type a consonant
            consButton.setEnabled(false);
            guessCons.setEnabled(true);
            //this color change would guide the player
            guessConsLabel.setBackground(Color.cyan);
        }
        else if (e.getSource() == guessCons){
            //after guessing consonant, these buttons would be enabled again, and the background color would be normal
            guessAConsonant();
            guessCons.setEnabled(true);
            vowelButton.setEnabled(true);
            solveButton.setEnabled(true);
            guessCons.setText("");//this code clean the textfield, it looks nice
            guessConsLabel.setBackground(new JLabel().getBackground());
        }
        else if (e.getSource() == vowelButton){
            JFrame frame = new JFrame();
            //this check whether it's larger than 250, if smaller than 250, you can't even type a vowel
            if (currentPlayer.getWinningsTemp() >= 250){
                //this make sure that once you click the buyvowel button, you can only type a vowel
                vowelButton.setEnabled(false);
                consButton.setEnabled(false);
                solveButton.setEnabled(false);
                spinButton.setEnabled(false);
                guessVowel.setEnabled(true);
                guessVowelLabel.setBackground(Color.cyan);
            }
            else{
                JOptionPane.showMessageDialog(frame, "No enough money");
                guessVowel.setEnabled(false);
                swapPlayers();
            }
        }
        else if (e.getSource() == guessVowel){
            //this enables button that should be enabled
            buyAVowel();
            guessVowel.setEnabled(false);
            vowelButton.setEnabled(true);
            consButton.setEnabled(false);
            solveButton.setEnabled(true);
            spinButton.setEnabled(true);
            guessVowelLabel.setBackground(new JLabel().getBackground());
            guessVowel.setText("");
        }
        else if (e.getSource() == solveButton){
            //this make sure that you click solve puzzle, you can only type in the puzzle
            solvePuzzle.setEnabled(true);
            vowelButton.setEnabled(false);
            consButton.setEnabled(false);
            solveButton.setEnabled(false);
            spinButton.setEnabled(false);
            solvePuzzleLabel.setBackground(Color.cyan);
        }
        else if (e.getSource() == solvePuzzle){
            solve();
            solvePuzzleLabel.setBackground(new JLabel().getBackground());
            solvePuzzle.setText("");
        }
        else if(e.getSource() == reinitialize){
            //this make sure when you click reinitialize, other buttons would be enabled
            reinitialize();
            reinitialize.setEnabled(false);
            spinButton.setEnabled(true);
            vowelButton.setEnabled(true);
            solveButton.setEnabled(true);
        }
        else if(e.getSource() == exit){//this is exit GUI method
            System.exit(0);
        }


        /*Put in cases for all GUI objects that can generate an "event". It's generally
        best to use method calls to handle each event.*/


    }

    public void initialsetup(){//this method determine who goes first, and highlight who go first with red color
        Random rnd = new Random();
        int n = rnd.nextInt(2)+1;
        if (n==1){
            currentPlayer = player1;
            previousPlayer = player1;
            playerLabel1.setBackground(Color.red);
            playerLabel2.setBackground(new JLabel().getBackground());
        }
        else {
            currentPlayer = player2;
            previousPlayer = player2;
            playerLabel2.setBackground(Color.red);
            playerLabel1.setBackground(new JLabel().getBackground());
        }
        gameNum = 0;
    }

    public void initialize(){//some setups
        moneyWin = 0;
        puzzle = puzzles[gameNum];
        initializeShownPhrase();
        player1.setWinningsTemp(0);
        player2.setWinningsTemp(0);
        consButton.setEnabled(false);
        guessCons.setEnabled(false);
        guessVowel.setEnabled(false);
        solvePuzzle.setEnabled(false);
        reinitialize.setEnabled(false);
        exit.setEnabled(false);
        gameNumText.setText("Game " + Integer.toString(gameNum+1));
    }

    public void initializeShownPhrase(){
        shownPhrase = new StringBuffer(puzzle);
        for (int i=0; i<puzzle.length(); i++){
            if (Character.isLetter(puzzle.substring(i).charAt(0))){
                shownPhrase.replace(i,i+1,"_");
            }
            if (puzzle.substring(i).equals(" ")){
                shownPhrase.replace(i,i+1," ");
            }
        }
        puzzleText.setText(String.valueOf(shownPhrase));
    }

    public void guessAConsonant(){
        JFrame frame = new JFrame();
        char consonant = guessCons.getText().charAt(0);
        if (!isConsonant(consonant)){
            JOptionPane.showMessageDialog(frame, "Not a consonant");
            swapPlayers();
        }
        else if (shownPhrase.indexOf(Character.toString(consonant))!=-1){
            JOptionPane.showMessageDialog(frame, "Already guessed");
            swapPlayers();
        }
        else if (!puzzle.contains(Character.toString(consonant))){
            JOptionPane.showMessageDialog(frame, "Not in the puzzle");
            swapPlayers();
        }
        else{
            JOptionPane.showMessageDialog(frame, "It's valid");
            int numConsonant = 0;
            for (int i = 0; i<puzzle.length(); i++){
                char guess = puzzle.charAt(i);
                if (guess == consonant){
                    shownPhrase.replace(i,i+1,Character.toString(consonant));
                    numConsonant++;
                }
            }
            puzzleText.setText(shownPhrase.toString());
            currentPlayer.setWinningsTemp(currentPlayer.getWinningsTemp() + numConsonant * moneyWin);
            if (player1 == currentPlayer){
                gameMoneyText1.setText("$"+ Integer.toString(currentPlayer.getWinningsTemp()));
            }
            else {
                gameMoneyText2.setText("$" + Integer.toString(currentPlayer.getWinningsTemp()));
            }
        }
        spinButton.setEnabled(true);
    }

    public void buyAVowel(){
        JFrame frame = new JFrame();
        char vowel = guessVowel.getText().charAt(0);
        if (isConsonant(vowel)){
            JOptionPane.showMessageDialog(frame, "Not a vowel");
            currentPlayer.payForVowel();
            if(currentPlayer == player1){
                gameMoneyText1.setText("$"+Integer.toString(player1.getWinningsTemp()));
            }
            else{
                gameMoneyText2.setText("$"+Integer.toString(player2.getWinningsTemp()));
            }
            guessVowel.setEnabled(false);
            swapPlayers();
        }
        else if (shownPhrase.indexOf(Character.toString(vowel))!=-1){
            JOptionPane.showMessageDialog(frame, "Already guessed");
            currentPlayer.payForVowel();
            if(currentPlayer == player1){
                gameMoneyText1.setText("$"+Integer.toString(player1.getWinningsTemp()));
            }
            else{
                gameMoneyText2.setText("$"+Integer.toString(player2.getWinningsTemp()));
            }
            guessVowel.setEnabled(false);
            swapPlayers();
        }
        else if (!puzzle.contains(Character.toString(vowel))){
            JOptionPane.showMessageDialog(frame, "Not in the puzzle");
            currentPlayer.payForVowel();
            if(currentPlayer == player1){
                gameMoneyText1.setText("$"+Integer.toString(player1.getWinningsTemp()));
            }
            else{
                gameMoneyText2.setText("$"+Integer.toString(player2.getWinningsTemp()));
            }
            guessVowel.setEnabled(false);
            swapPlayers();
        }
        else if (puzzle.contains(Character.toString(vowel))){
            JOptionPane.showMessageDialog(frame, "It's valid!");
            currentPlayer.payForVowel();
            for (int i=0; i<puzzle.length(); i++){
                char guess = puzzle.charAt(i);
                if (guess == vowel){
                    shownPhrase.replace(i,i+1, Character.toString(guess));
                }
            }
            puzzleText.setText(shownPhrase.toString());
            if(currentPlayer == player1){
                gameMoneyText1.setText("$"+Integer.toString(player1.getWinningsTemp()));
            }
            else{
                gameMoneyText2.setText("$"+Integer.toString(player2.getWinningsTemp()));
            }
        }
    }

    public void solve(){
        JFrame frame = new JFrame();
        String solution = solvePuzzle.getText();
        if (solution.compareTo(puzzle) == 0){
            JOptionPane.showMessageDialog(frame, "It's right!");
            int totalwinning = currentPlayer.getWinningsTemp() + currentPlayer.getWinningsTotal();
            currentPlayer.setWinningsTotal(totalwinning);
            //so the JTable saves the money each player wins in each game
            Datas[gameNum+1][0] = player1.getWinningsTemp();
            Datas[gameNum+1][1] = player2.getWinningsTemp();
            if (currentPlayer == player1){
                matchMoneyText1.setText("$"+Integer.toString(totalwinning));
                JOptionPane.showMessageDialog(frame, "The game ends. Player 1 wins the game! Player 1 wins $" + player1.getWinningsTemp());
            }
            else{
                matchMoneyText2.setText("$"+Integer.toString(totalwinning));
                JOptionPane.showMessageDialog(frame, "The game ends. Player 2 wins the game! Player 2 wins $" + player2.getWinningsTemp());
            }
            winnerText.setText("Player: "+ currentPlayer.getPlayerNum() + " wins " + currentPlayer.getWinningsTemp());
            reinitialize.setEnabled(true);
            spinButton.setEnabled(false);
            if(gameNum == 3){//this is when the forth puzzle is solved, then we quit the game
                if (player1.getWinningsTotal()>player2.getWinningsTotal()){
                    JOptionPane.showMessageDialog(frame, "The match ends. Player 1 wins the match! Player 1 wins $" + player1.getWinningsTotal());
                }
                else{
                    JOptionPane.showMessageDialog(frame, "The match ends. Player 2 wins the match! Player 2 wins $" + player2.getWinningsTotal());
                }
                spinButton.setEnabled(false);
                vowelButton.setEnabled(false);
                consButton.setEnabled(false);
                solveButton.setEnabled(false);
                solvePuzzle.setEnabled(false);
                reinitialize.setEnabled(false);
                exit.setEnabled(true);
            }
        }
        else{
            JOptionPane.showMessageDialog(frame, "It's wrong.");
            spinButton.setEnabled(true);
            vowelButton.setEnabled(true);
            solveButton.setEnabled(true);
            swapPlayers();
        }
    }

    public void reinitialize(){
        if (previousPlayer == player1){
            currentPlayer = player2;
            previousPlayer = player2;
            playerLabel2.setBackground(Color.red);
            playerLabel1.setBackground(new JLabel().getBackground());
        }
        else{
            currentPlayer = player1;
            previousPlayer = player1;
            playerLabel1.setBackground(Color.red);
            playerLabel2.setBackground(new JLabel().getBackground());
        }
        gameNum++;
        moneyWin = 0;
        gameNumText.setText("Game " + (gameNum+1));
        player1.setWinningsTemp(0);
        player2.setWinningsTemp(0);
        turnText.setText("Player "+ currentPlayer.getPlayerNum() + " it's your turn");
        puzzle = puzzles[gameNum];
        initializeShownPhrase();
        puzzleText.setText(shownPhrase.toString());
        gameMoneyText1.setText("$" + player1.getWinningsTemp());
        gameMoneyText2.setText("$"+ player2.getWinningsTemp());
        resultOfSpin.setText(" ");
        winnerText.setText(" ");
    }

    public void doSpin() {
        Random rnd = new Random();
        int n = rnd.nextInt(8);
        if (spin[n] == 0){
            resultOfSpin.setText("Bankrupt");
            currentPlayer.setWinningsTemp(0);
            if (currentPlayer == player1) {
                gameMoneyText1.setText("$0");
            }
            else{
                gameMoneyText2.setText("$0");
            }
            swapPlayers();
            spinButton.setEnabled(true);
            consButton.setEnabled(false);
            vowelButton.setEnabled(true);
            solveButton.setEnabled(true);
        }
        else if(spin[n] == 1){
            resultOfSpin.setText("Lose a turn");
            swapPlayers();
            spinButton.setEnabled(true);
            consButton.setEnabled(false);
            vowelButton.setEnabled(true);
            solveButton.setEnabled(true);
        }
        else{
            resultOfSpin.setText(Integer.toString(spin[n]));
            moneyWin = spin[n];
        }
    }

    public void swapPlayers(){
        if (currentPlayer == player1){//this change the highlight as the player who is playing changes
            currentPlayer = player2;
            playerLabel2.setBackground(Color.red);
            playerLabel1.setBackground(new JLabel().getBackground());
        }
        else {
            currentPlayer = player1;
            playerLabel1.setBackground(Color.red);
            playerLabel2.setBackground(new JLabel().getBackground());
        }
        turnText.setText("Player " + currentPlayer.getPlayerNum() + ", it's your turn");
    }

    public boolean isConsonant(char c){
        return (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u');
    }


}
