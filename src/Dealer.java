public class Dealer {  //庄家类
    private Hand hand;


    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card){  //在手牌种加一张牌
        hand.cardList.add(card);
    }

    public void printHand(){  //打印手牌
        System.out.print("庄家现在的手牌为：");
        System.out.print("[ ");
        for (Card card :hand.getCardList())
            System.out.print(card.getColor()+card.getFaceValve()+" ");
        System.out.print("]");
        System.out.println("   点数总和为：" + hand.getPoint());
    }
}
