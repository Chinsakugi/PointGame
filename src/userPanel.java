import java.util.ArrayList;
import java.util.Scanner;

public class userPanel {
    public static void startGameInfo() {
        System.out.println("欢迎来到二十一点游戏！");
        System.out.println("请选择：");
        System.out.println("1.开始游戏     2.结束游戏");
    }

    public static void endGameInfo() {
        System.out.println("游戏结束，感谢您的游玩！");
    }

    public static void menu() {
        System.out.println("请选择：");
        System.out.println("1.双倍     2.拿牌     3.停牌");
    }

    public static int startGame(Desk desk) {     //返回1即表示玩家赢了,2表示输了，3表示平局
        Scanner scanner = new Scanner(System.in);

        while (true) {
            desk.shuffle();
            System.out.println("本次游戏的初始赔率为：" + desk.getOdds() + "  您的余额为：" + desk.getPlayer().getBalance());
            System.out.println("请输入你要投注的筹码金额");
            int money = scanner.nextInt();  //筹码金额
            desk.getPlayer().setPokerChips(money);
            System.out.println("您下注的筹码金额为：" + money);
            System.out.println("请选择：1.发牌     2.重新下注");
            int input1 = scanner.nextInt();
            if (input1 == 1) {
                desk.licensing(desk.getDealer());
                desk.licensing(desk.getDealer());
                System.out.println("庄家的牌为：[" + desk.getDealer().getHand().getCardList().get(0).getColor() + desk.getDealer().getHand().getCardList().get(0).getFaceValve() + ",暗牌" + "]");

                desk.licensing(desk.getPlayer());
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
                break;
            }
            if (input1 == 2) {
                break;
            } else
                System.out.println("输入无效，请重新选择！");
        }
        while (true) {          //玩家选择双倍或拿牌或停牌
            menu();
            int option = scanner.nextInt();
            if (option == 1) {
                desk.doubleOdds();
                System.out.println("已选择双倍，此时的赔率为：" + desk.getOdds());
            }
            if (option == 1 || option == 2) {
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            } else if (option == 3) {
                break;
            } else
                System.out.println("输入无效，请重新选择！");
            if (desk.isBiggerThan21(desk.getPlayer())) {
                System.out.print("您的牌已爆，");
                return 2;
            }
        }
        desk.getDealer().printHand();
        while (desk.getDealer().getHand().getPoint() < 17) {  //庄家拿牌
            System.out.print("庄家拿牌, ");
            desk.licensing(desk.getDealer());
            desk.getDealer().printHand();
            if (desk.getDealer().getHand().getPoint() > 21) {
                System.out.print("庄家爆牌，");
                return 1;
            }
        }
        System.out.println("庄家拿牌结束");

        int playerPoint = desk.getPlayer().getHand().getPoint(); //玩家手牌点数
        int dealerPoint = desk.getDealer().getHand().getPoint(); //庄家手牌点数
        if (playerPoint > dealerPoint)           //判断胜负
            return 1;
        else if (playerPoint < dealerPoint)
            return 2;
        else {                                                 //是否平局判断
            if (playerPoint < 21)
                return 3;
            else {
                boolean i = desk.getPlayer().getHand().isBlackJack();
                boolean j = desk.getDealer().getHand().isBlackJack();
                if (i && j)
                    return 3;
                else
                    return i ? 1 : 2;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player(100, new Hand(new ArrayList<Card>()), 0);
        Dealer dealer = new Dealer(new Hand(new ArrayList<Card>()));

        Desk desk = new Desk(2.0, player, dealer);
        while (true) {
            startGameInfo();
            int input = sc.nextInt();
            if (input == 1) {
                int condition = startGame(desk);
                if (condition == 1) {
                    System.out.print("您赢了！");
                    desk.getPlayer().setBalance(desk.getPlayer().getBalance() + desk.getOdds() * desk.getPlayer().getPokerChips());
                } else if (condition == 2) {
                    System.out.print("您输了！");
                    desk.getPlayer().setBalance(desk.getPlayer().getBalance() - desk.getPlayer().getPokerChips());
                } else
                    System.out.print("平局");
                System.out.println("  您的余额为：" + desk.getPlayer().getBalance());
                desk.deleteHand(player);   //清空手牌
                desk.deleteHand(dealer);
            } else if (input == 2) {
                endGameInfo();
                break;
            } else
                System.out.println("输入无效，请重新选择！");
        }
    }
}
