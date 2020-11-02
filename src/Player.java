//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import javax.swing.JTextArea;

public class Player {
    private double balance;
    private Hand hand;
    private boolean isDoubleOdds = false;
    private int pokerChips;
    JTextArea textArea;

    public Player(int balance, Hand hand, int pokerChips, JTextArea textArea) {
        this.balance = (double)balance;
        this.hand = hand;
        this.pokerChips = pokerChips;
        this.textArea = textArea;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isDoubleOdds() {
        return this.isDoubleOdds;
    }

    public void setDoubleOdds(boolean doubleOdds) {
        this.isDoubleOdds = doubleOdds;
    }

    public int getPokerChips() {
        return this.pokerChips;
    }

    public void setPokerChips(int pokerChips) {
        this.pokerChips = pokerChips;
    }

    public void addCard(Card card) {
        this.hand.cardList.add(card);
    }

    public void printHand() {
        this.textArea.append("您现在的手牌为：\n");
        this.textArea.append("[ ");
        Iterator var2 = this.hand.getCardList().iterator();

        while(var2.hasNext()) {
            Card card = (Card)var2.next();
            this.textArea.append(card.getColor() + card.getFaceValve() + " ");
        }

        this.textArea.append("]\n");
        this.textArea.append("点数总和为：" + this.hand.getPoint() + '\n');
    }
}
