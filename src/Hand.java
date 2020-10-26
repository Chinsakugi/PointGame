import java.util.ArrayList;

public class Hand {
    ArrayList<Card> cardList;   //一手牌

    public Hand(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public int getPoint(){  //返回手牌的点数值
        int point = 0;    //存储手牌点数总和
        int num_A = 0;    //存储A牌数量
         for(Card card : cardList){
             String faceValue = card.getFaceValve();
            switch (faceValue){
                case"J": case "K": case "Q":
                    point += 10;
                    break;
                case "A":
                    num_A += 1;
                    break;
                default:
                    point +=Integer.parseInt(faceValue);
            }
        }
         point += num_A * 11;                   //计算在不爆掉的情况下，点数的最大值
         while (point>21 && num_A >0){
             point = point -11 +1;
             --num_A;
         }
         return point;
    }

    public boolean isBlackJack(){  //判断手牌是否为黑杰克(已知点数总和为21点)
        return cardList.size() == 2;
    }
}
