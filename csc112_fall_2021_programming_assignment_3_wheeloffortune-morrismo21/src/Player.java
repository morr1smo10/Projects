public class Player {
    private String name;
    private int numWins;
    private int playerNum;
    private int moneyForGameTemp;
    private int moneyForGameFinal;

    //this is the constructor method, I construct the name according to the parameter, and set others to zero
    public Player(String name) {
        this.name = name;
        numWins = 0;
        playerNum = 0;
        moneyForGameTemp = 0;
        moneyForGameFinal = 0;
    }

    public void setMoneyForGameFinal(int val) {
        moneyForGameFinal = val;
    }

    public int getMoneyForGameFinal() {
        return moneyForGameFinal;
    }

    public void setMoneyForGameTemp(int val) {
        moneyForGameTemp = val;
    }

    public int getMoneyForGameTemp() {
        return moneyForGameTemp;
    }

    public void setNumWins(int n) {
        numWins = n;
    }

    public int getNumWins() {
        return numWins;
    }

    public void incrementNumWins() {
        numWins++;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setPlayerNum(int n) {
        playerNum = n;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void payForVowel() {
        moneyForGameTemp -= 250;
    }
}