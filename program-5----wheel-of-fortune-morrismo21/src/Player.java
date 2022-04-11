public class Player {
    private String name;
    private int playerNum;
    private int winningsTemp;
    private int winningsTotal;

    public Player(int num) {
        winningsTemp = 0;
        winningsTotal = 0;
        playerNum = num;
    }

    public void setName(String n) {
        name = n;
    }

    public void setPlayerNum(int n) {
        playerNum = n;
    }

    public void setWinningsTemp(int wt) {
        winningsTemp = wt;
    }

    public void setWinningsTotal(int wt) {
        winningsTotal = wt;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getWinningsTemp() {
        return winningsTemp;
    }
    public String getName() {
        return name;
    }

    public int getWinningsTotal() {
        return winningsTotal;
    }
    public void payForVowel() {winningsTemp -= 250;}
}
