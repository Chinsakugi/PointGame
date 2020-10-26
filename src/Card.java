enum pokerColor{
    黑桃,红心,梅花,方块;
}

public class Card {
    private String faceValve;   //扑克牌的面值
    private pokerColor color; //扑克牌的花色

    public Card(String faceValve, pokerColor color) {
        this.faceValve = faceValve;
        this.color = color;
    }

    public String getFaceValve() {
        return faceValve;
    }

    public void setFaceValve(String faceValve) {
        this.faceValve = faceValve;
    }

    public pokerColor getColor() {
        return color;
    }
}
