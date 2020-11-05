//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;

public class Hand {
    ArrayList<Card> cardList;

    public Hand(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public ArrayList<Card> getCardList() {
        return this.cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public int getPoint() {       //获取手牌点数
        int point = 0;
        int num_A = 0;
        Iterator var4 = this.cardList.iterator();

        while(true) {
            label42:
            while(true) {
                if (!var4.hasNext()) {
                    for(point += num_A * 11; point > 21 && num_A > 0; --num_A) {
                        point = point - 11 + 1;
                    }

                    return point;
                }

                Card card = (Card)var4.next();
                String faceValue = card.getFaceValve();
                switch(faceValue.hashCode()) {
                    case 65:
                        if (faceValue.equals("A")) {
                            ++num_A;
                            continue;
                        }
                        break;
                    case 74:
                        if (faceValue.equals("J")) {
                            break label42;
                        }
                        break;
                    case 75:
                        if (faceValue.equals("K")) {
                            break label42;
                        }
                        break;
                    case 81:
                        if (faceValue.equals("Q")) {
                            break label42;
                        }
                }

                point += Integer.parseInt(faceValue);
            }

            point += 10;
        }
    }

    public boolean isBlackJack() {   //判断手牌是否为黑杰克
        return this.cardList.size() == 2;
    }
}
