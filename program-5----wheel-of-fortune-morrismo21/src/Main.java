/*Morris Mo
CSC112 Fall 2021
Programming Assignment 5
November 30, 2021
This program implements GUI to the Wheel of fortune game
In summary, the instruction of my game is very intuitive, you can only choose to click the button that I enabled, and
where you need to input is highlighted, just follow them!
To play this game, as each game started, you could either and only choose spin, buy a vowel, or solve a puzzle. If you
choose spin, then you can only choose guess a consonant, and then you can only start inputing a consonant. If you choose
buy a vowel, if you don't have 250, the player would be swapped and start a new play, else, you can only start guessing
a vowel and it will cost 250$. If you choose to solve the puzzle, you can only solve the puzzle, if you solve wrong, the
player would be swapped and start a new play. If the puzzle is correctly solved, the winnings of each player would be
updated in JTable, and their game winnings would be updated to the match winning and would be displayed. Then you have
to and could only click the reinitialize and start a new game. When four games are all played, the final winner would be
displayed, and you have to and could only click the exit button to quit the GUI and the whole program. The instruction
of playing my game should be very intuitive, because I only enable the button that you can choose, and I have blue
highlight at where you should have input.
The special feature of my program is the intuitive system of enable, the background color change, and the exit button.
The intuitive system is, I only enable the button that you could choose: after you click the spin button, only the guess
consonant button is enabled and other buttons are all not enabled. Thus, player could only choose to guess a consonant,
which is the right thing to do. Another example is when one game is finished, all button would be disabled and the
reinitialize is the only button that is enabled. So player could only choose to reinitialize and start a new game. The
code involved is majorly where the enabled statement appear, majorly from line 350-435 (the actionperformed method),
line 578-622 (the solve method), line 652-677 (the dospin bankrupt and lose a turn).
Another special feature is the change of background color. When you have to "guess a consonant", "buy a vowel", or
"solve the puzzle", the label accordingly would become blue, so it tells the player what they should do. Another place
where background color is applied is the PlayerLabel, so the player1label or the player2label would become red when
player1 or player2 is playing. This vividly displaying who is playing the game. The code involved is in the construction
method, I have to set these labels setOpaque as true. Then, in the actionlisterner (line 350-435), I change the
background color to blue using guessConsLabel.setBackground(Color.cyan);, and after the player follow the instruction
correctly, the background color would back to normal, using this statement:
guessConsLabel.setBackground(new JLabel().getBackground()); Similarly, the current player who is playing would be marked
red backgrond color using the same ideology.
Another special feature is the exit button I created to exit the entire match. So at line 322-327, I created a block for
the exit button, and it is linked to System.exit(0); method in line 427. And the exit button is disabled for the entire
game unless the fourth puzzle is correctly solved. Then, the players could click the button and quit the match.

 */

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        WOFFrame wofFrame = new WOFFrame();
        wofFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wofFrame.pack();
        wofFrame.setVisible(true);
    }
}