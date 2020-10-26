import java.util.ArrayList;
import java.util.Random;

public class Desk {   //牌桌类
    private double odds;  //赔率
    private ArrayList<Card> deck;  //牌堆
    private Player player;
    private Dealer dealer;

    public Desk(double odds, Player player, Dealer dealer) {
        this.odds = odds;
        this.deck = null;
        this.player = player;
        this.dealer = dealer;
    }

    public double getOdds() {
        return odds;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void  initDeck(){  //初始化一副牌
        ArrayList<Card> cards = new ArrayList<>();
        String faceValue;
        pokerColor[] colors = pokerColor.values();
        for (int i=1;i<=52;++i){
            if(i%13 == 1)
                faceValue = "A";
            else if (i%13 == 11)
                faceValue = "J";
            else if (i%13 == 12)
                faceValue = "Q";
            else if (i%13 == 0)
                faceValue = "K";
            else
                faceValue = i%13 + "";
            cards.add(new Card(faceValue, colors[(i-1)/13]));
        }
        deck = cards;
    }

    public static void swap(ArrayList<Card> cards,int i,int j){  //交换函数
        Card temp = cards.get(i);
        cards.set(i,cards.get(j));
        cards.set(j,temp);
    }

    public void shuffle(){   //初始化一副牌并洗牌
        initDeck();
        Random rand = new Random();
        for (int i =getDeck().size();i>0;--i){
            int randInt = rand.nextInt(i);
            swap(deck,randInt,i-1);
        }
    }

    public void licensing(Object joiner){ //发牌给player(每次一张)
        Card card = getDeck().get(0); //获取牌堆的第一张牌
        if (joiner.getClass() == Player.class)
            player.addCard(card);
        else
            dealer.addCard(card);

        deck.remove(0);  //删除牌堆的第一张牌
    }

    public void doubleOdds(){  //加倍赔率
        this.odds *= 2.0;
    }

    public boolean isBiggerThan21(Object obj){  //判断是否达到21点
        if (obj.getClass() ==Player.class)
            return player.getHand().getPoint() > 21;
        else
            return dealer.getHand().getPoint() > 21;
    }

    public void deleteHand(Object obj){  //清空手牌
        if (obj.getClass() == Player.class)
            player.setHand(new Hand(new ArrayList<Card>()));
        else
            dealer.setHand(new Hand(new ArrayList<Card>()));
    }
}
