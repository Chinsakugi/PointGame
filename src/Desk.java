//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Random;

public class Desk {
    private double odds;
    private ArrayList<Card> deck;
    private Player player;
    private Dealer dealer;

    public Desk(double odds, Player player, Dealer dealer) {
        this.odds = odds;
        this.deck = null;
        this.player = player;
        this.dealer = dealer;
    }

    public double getOdds() {
        return this.odds;
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void initDeck() {
        ArrayList<Card> cards = new ArrayList();
        pokerColor[] colors = pokerColor.values();

        for(int i = 1; i <= 52; ++i) {
            String faceValue;
            if (i % 13 == 1) {
                faceValue = "A";
            } else if (i % 13 == 11) {
                faceValue = "J";
            } else if (i % 13 == 12) {
                faceValue = "Q";
            } else if (i % 13 == 0) {
                faceValue = "K";
            } else {
                faceValue = String.valueOf(i % 13);
            }

            cards.add(new Card(faceValue, colors[(i - 1) / 13]));
        }

        this.deck = cards;
    }

    public static void swap(ArrayList<Card> cards, int i, int j) {
        Card temp = (Card)cards.get(i);
        cards.set(i, (Card)cards.get(j));
        cards.set(j, temp);
    }

    public void shuffle() {
        this.initDeck();
        Random rand = new Random();

        for(int i = this.getDeck().size(); i > 0; --i) {
            int randInt = rand.nextInt(i);
            swap(this.deck, randInt, i - 1);
        }

    }

    public void licensing(Object joiner) {
        Card card = (Card)this.getDeck().get(0);
        if (joiner.getClass() == Player.class) {
            this.player.addCard(card);
        } else {
            this.dealer.addCard(card);
        }

        this.deck.remove(0);
    }

    public void doubleOdds() {
        this.odds *= 2.0D;
        this.player.setDoubleOdds(true);
    }

    public void resetOdds() {
        this.odds = 2.0D;
    }

    public boolean isBiggerThan21(Object obj) {
        if (obj.getClass() == Player.class) {
            return this.player.getHand().getPoint() > 21;
        } else {
            return this.dealer.getHand().getPoint() > 21;
        }
    }

    public void deleteHand(Object obj) {
        if (obj.getClass() == Player.class) {
            this.player.setHand(new Hand(new ArrayList()));
        } else {
            this.dealer.setHand(new Hand(new ArrayList()));
        }

    }
}
