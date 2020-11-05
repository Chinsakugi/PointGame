//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import javax.swing.JTextArea;

public class Dealer {
    private Hand hand;  //手牌
    JTextArea textArea;

    public Dealer(Hand hand, JTextArea textArea) {
        this.hand = hand;
        this.textArea = textArea;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        this.hand.cardList.add(card);
    }

    public void printHand() {   //打印手牌
        this.textArea.append("庄家现在的手牌为：\n");
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
