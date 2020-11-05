import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class userPanel {
    public static void main(String[] args) {
        //创建 JFrame 实例
        JFrame f = new JFrame("21 points");
        f.setSize(1200, 900);
        f.setLocation(400, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //自定义面板
        JPanel panel = new JPanel();
        InputInterface(panel);
        f.add(panel);
        f.setVisible(true);
    }



    private static void InputInterface(JPanel panel) {
        int gap = 10;
        JPanel pInput = new JPanel();
        pInput.setLayout(null);
        pInput.setBounds(gap, gap, 375, 120);
        GroupLayout layout = new GroupLayout(pInput);
        pInput.setLayout(layout);

        // 选择筹码的滑块
        JSlider slider = new JSlider(5, 100, 5);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        //自定义刻度
        Hashtable<Integer, JComponent> hashtable = new Hashtable<Integer, JComponent>();
        hashtable.put(5, new JLabel("5$"));
        hashtable.put(25, new JLabel("25$"));
        hashtable.put(50, new JLabel("50$"));
        hashtable.put(75, new JLabel("75$"));
        hashtable.put(100, new JLabel("100$"));
        slider.setLabelTable(hashtable);

        //字体
        Font f1= new Font("宋体",Font.BOLD,20);
        Font f2= new Font("宋体",Font.BOLD,25);
        Font f3= new Font("宋体",Font.ITALIC,16);
        Font f4= new Font("仿宋",Font.PLAIN,22);

        //主体文本框
        JTextArea textArea = new JTextArea(20,80);
        textArea.setFont(f4);
        JScrollPane jscrollPane = new JScrollPane(textArea);
        jscrollPane.setBounds(13, 10, 350, 340);
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Player player = new Player(100, new Hand(new ArrayList<Card>()), 0, textArea);
        Dealer dealer = new Dealer(new Hand(new ArrayList<Card>()),textArea);
        Desk desk = new Desk(2.0, player, dealer);

        //选择筹码文本框
        JTextArea textf = new JTextArea("滑动选择您的筹码。",1,10);
        textf.setFont(f3);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                textf.setText(" ");
                textf.append("您下注的筹码金额为：" + slider.getValue()+'$');
                textf.paintImmediately(textArea.getBounds());
            }
        });
        JButton bstart = new JButton("开始游戏");
        bstart.setFont(f2);
        JButton bend = new JButton("结束游戏");
        bend.setFont(f2);
        bend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //开始驱动：设置筹码，准备游戏
        JButton bfp = new JButton("发牌");
        bfp.setOpaque(false);
        bfp.setContentAreaFilled(false);
        bfp.setFont(f1);
        bstart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bfp.setOpaque(true);
                bfp.setContentAreaFilled(true);
                desk.shuffle();
                textArea.append("本次游戏的初始赔率为：" + desk.getOdds() + "  您的余额为：" + desk.getPlayer().getBalance()+'$'+'\n');
                textArea.paintImmediately(textArea.getBounds());
            }
        });

        pInput.add(bstart);
        pInput.add(bend);
        pInput.add(slider);
        pInput.add(textf);
        panel.add(jscrollPane);

        //过程驱动：玩家的选择
        JButton bdb = new JButton("双倍");
        bdb.setOpaque(false);
        bdb.setContentAreaFilled(false);
        bdb.setFont(f1);
        JButton btc = new JButton("拿牌");
        btc.setOpaque(false);
        btc.setContentAreaFilled(false);
        btc.setFont(f1);
        JButton bsc = new JButton("停牌");
        bsc.setOpaque(false);
        bsc.setContentAreaFilled(false);
        bsc.setFont(f1);
        //发牌按钮事件
        bfp.addActionListener(new ActionListener() {
            boolean checkedpass = true;
            public void actionPerformed(ActionEvent e) {
                checkOver(slider, desk.getPlayer().getBalance());
                if(checkedpass)
                {
                    bdb.setOpaque(true);
                    bdb.setContentAreaFilled(true);
                    btc.setOpaque(true);
                    btc.setContentAreaFilled(true);
                    bsc.setOpaque(true);
                    bsc.setContentAreaFilled(true);
                    int money = slider.getValue();
                    desk.getPlayer().setPokerChips(money);
                    desk.licensing(desk.getDealer());
                    desk.licensing(desk.getDealer());
                    textArea.append("庄家的牌为：[" + desk.getDealer().getHand().getCardList().get(0).getColor() + desk.getDealer().getHand().getCardList().get(0).getFaceValve() + ",暗牌" + "]\n");
                    textArea.paintImmediately(textArea.getBounds());
                    desk.licensing(desk.getPlayer());
                    desk.licensing(desk.getPlayer());
                    desk.getPlayer().printHand();
                }
            }
            private void checkOver(JSlider slider, double balance){
                if(slider.getValue() > balance){
                    JOptionPane.showMessageDialog(pInput,"筹码不能超过余额");
                    slider.grabFocus();
                    checkedpass = false;
                }
                else
                    checkedpass = true;
            }
        });
        pInput.add(bfp);
        //双倍按钮事件
        bdb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desk.doubleOdds();
                textArea.append("已选择双倍，此时的赔率为：" + desk.getOdds()+'\n');
                textArea.paintImmediately(textArea.getBounds());
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            }
        });
        pInput.add(bdb);
        //拿牌按钮事件
        btc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            }
        });
        pInput.add(btc);
        //停牌按钮事件
        bsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int condition;
                boolean overp = false;
                boolean overd = false;
                if (desk.isBiggerThan21(desk.getPlayer())) {
                    textArea.append("您的牌已爆，");
                    textArea.paintImmediately(textArea.getBounds());
                    condition = 2;
                    overp = true;
                }
                desk.getDealer().printHand();
                while (desk.getDealer().getHand().getPoint() < 17) {  //庄家拿牌
                    textArea.append("庄家拿牌, ");
                    desk.licensing(desk.getDealer());
                    desk.getDealer().printHand();
                    if (desk.getDealer().getHand().getPoint() > 21) {
                        textArea.append("庄家爆牌，");
                        textArea.paintImmediately(textArea.getBounds());
                        condition = 1;
                        overd = true;
                    }
                }
                textArea.append("庄家拿牌结束。\n");
                textArea.paintImmediately(textArea.getBounds());
                int playerPoint = desk.getPlayer().getHand().getPoint(); //玩家手牌点数
                int dealerPoint = desk.getDealer().getHand().getPoint(); //庄家手牌点数
                if ((playerPoint > dealerPoint && !overp) ||overd)           //判断胜负
                    condition = 1;
                else if (playerPoint < dealerPoint || overp)
                    condition = 2;
                else {                                                 //是否平局判断
                    if (playerPoint < 21)
                        condition = 3;
                    else {
                        boolean i = desk.getPlayer().getHand().isBlackJack();
                        boolean j = desk.getDealer().getHand().isBlackJack();
                        if (i == j)
                            condition = 3;
                        else
                            condition = i ? 1 : 2;
                    }
                }
                if(overp && overd)
                    condition = 3;
                if (condition == 1) {
                    textArea.append("您赢了！");
                    textArea.paintImmediately(textArea.getBounds());
                    desk.getPlayer().setBalance(desk.getPlayer().getBalance() + (desk.getOdds()-1.0) * desk.getPlayer().getPokerChips());
                } else if (condition == 2) {
                    textArea.append("您输了！");
                    textArea.paintImmediately(textArea.getBounds());
                    if(desk.getPlayer().isDoubleOdds())
                        desk.getPlayer().setBalance(desk.getPlayer().getBalance() - 2.0*desk.getPlayer().getPokerChips());
                    else
                        desk.getPlayer().setBalance(desk.getPlayer().getBalance() - desk.getPlayer().getPokerChips());
                } else
                    textArea.append("平局");
                textArea.paintImmediately(textArea.getBounds());
                textArea.append("您的余额为：" + desk.getPlayer().getBalance()+'$'+'\n');
                textArea.append("可以重新选择筹码继续游戏。\n " );
                textArea.paintImmediately(textArea.getBounds());
                desk.deleteHand(player);   //清空手牌
                desk.deleteHand(dealer);
                desk.resetOdds();
            }
        });
        pInput.add(bsc);
        //分组水平垂直布局
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hSeqGroup01 = layout.createSequentialGroup().addComponent(bstart).addComponent(bend);
        hSeqGroup01.addGap(60);
        GroupLayout.SequentialGroup hSeqGroup02 = layout.createSequentialGroup().addComponent(bdb).addComponent(btc).addComponent(bsc);
        hSeqGroup02.addGap(20);
        GroupLayout.ParallelGroup hParalGroup = layout.createParallelGroup().addGroup(hSeqGroup01).addComponent(slider).addComponent(textf,GroupLayout.Alignment.CENTER).addComponent(jscrollPane, GroupLayout.Alignment.CENTER).addComponent(bfp).addGroup(hSeqGroup02);
        layout.setHorizontalGroup(hParalGroup);

        GroupLayout.ParallelGroup vParalGroup01 = layout.createParallelGroup().addComponent(bstart).addComponent(bend);
        vParalGroup01.addGap(60);
        GroupLayout.ParallelGroup vParalGroup02 = layout.createParallelGroup().addComponent(bdb).addComponent(btc).addComponent(bsc);
        vParalGroup02.addGap(20);
        GroupLayout.SequentialGroup vSeqGroup = layout.createSequentialGroup().addGroup(vParalGroup01).addComponent(slider).addComponent(textf).addComponent(jscrollPane).addComponent(bfp).addGroup(vParalGroup02);
        layout.setVerticalGroup(vSeqGroup);

        panel.add(pInput);




    }
}