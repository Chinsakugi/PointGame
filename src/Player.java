public class Player {    //玩家类
    private double balance;
    private Hand hand;
    private boolean isDoubleOdds = false;  //是否选择双倍赔率
    private int pokerChips;   //筹码

    public Player(int balance, Hand hand,int pokerChips) {
        this.balance = balance;
        this.hand = hand;
        this.pokerChips = pokerChips;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isDoubleOdds() {
        return isDoubleOdds;
    }

    public void setDoubleOdds(boolean doubleOdds) {
        isDoubleOdds = doubleOdds;
    }

    public int getPokerChips() {
        return pokerChips;
    }

    public void setPokerChips(int pokerChips) {
        this.pokerChips = pokerChips;
    }

    public void addCard(Card card){  //在手牌种加一张牌
        hand.cardList.add(card);
    }

    public void printHand(){  //打印手牌
        System.out.print("您现在的手牌为：");
        System.out.print("[ ");
        for (Card card :hand.getCardList())
            System.out.print(card.getColor()+card.getFaceValve()+" ");
        System.out.print("]");
        System.out.println("   点数总和为：" + hand.getPoint());
    }
}
