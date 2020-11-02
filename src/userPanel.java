//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class userPanel {
    public userPanel() {
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("21 points");
        f.setSize(400, 900);
        f.setLocation(400, 150);
        f.setDefaultCloseOperation(3);
        JPanel panel = new JPanel();
        InputInterface(panel);
        f.add(panel);
        f.setVisible(true);
    }

    private static void InputInterface(JPanel panel) {
        int gap = 10;
        final JPanel pInput = new JPanel();
        pInput.setLayout((LayoutManager)null);
        pInput.setBounds(gap, gap, 375, 120);
        GroupLayout layout = new GroupLayout(pInput);
        pInput.setLayout(layout);
        final JSlider slider = new JSlider(5, 100, 5);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable<Integer, JComponent> hashtable = new Hashtable();
        hashtable.put(5, new JLabel("5$"));
        hashtable.put(25, new JLabel("25$"));
        hashtable.put(50, new JLabel("50$"));
        hashtable.put(75, new JLabel("75$"));
        hashtable.put(100, new JLabel("100$"));
        slider.setLabelTable(hashtable);
        Font f1 = new Font("宋体", 1, 20);
        Font f2 = new Font("宋体", 1, 25);
        final JTextArea textArea = new JTextArea(20, 10);
        final Player player = new Player(100, new Hand(new ArrayList()), 0, textArea);
        final Dealer dealer = new Dealer(new Hand(new ArrayList()), textArea);
        final Desk desk = new Desk(2.0D, player, dealer);
        textArea.append("本次游戏的初始赔率为：" + desk.getOdds() + "  您的余额为：" + desk.getPlayer().getBalance() + '$' + '\n');
        textArea.paintImmediately(textArea.getBounds());
        textArea.append("滑动选择您的筹码。\n");
        textArea.paintImmediately(textArea.getBounds());
        JButton bcheck = new JButton("下注");
        bcheck.setFont(f1);
        bcheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("您下注的筹码金额为：" + slider.getValue() + '$' + '\n');
                textArea.paintImmediately(textArea.getBounds());
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
        final JButton bfp = new JButton("发牌");
        bfp.setOpaque(false);
        bfp.setContentAreaFilled(false);
        bfp.setFont(f1);
        bstart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bfp.setOpaque(true);
                bfp.setContentAreaFilled(true);
                desk.shuffle();
            }
        });
        pInput.add(bstart);
        pInput.add(bend);
        pInput.add(slider);
        pInput.add(bcheck);
        pInput.add(textArea);
        final JButton bdb = new JButton("双倍");
        bdb.setOpaque(false);
        bdb.setContentAreaFilled(false);
        bdb.setFont(f1);
        final JButton btc = new JButton("拿牌");
        btc.setOpaque(false);
        btc.setContentAreaFilled(false);
        btc.setFont(f1);
        final JButton bsc = new JButton("停牌");
        bsc.setOpaque(false);
        bsc.setContentAreaFilled(false);
        bsc.setFont(f1);
        bfp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                this.checkOver(slider, desk.getPlayer().getBalance());
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
                textArea.append("庄家的牌为：[" + ((Card)desk.getDealer().getHand().getCardList().get(0)).getColor() + ((Card)desk.getDealer().getHand().getCardList().get(0)).getFaceValve() + ",暗牌" + "]\n");
                textArea.paintImmediately(textArea.getBounds());
                desk.licensing(desk.getPlayer());
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            }

            private void checkOver(JSlider sliderx, double balance) {
                if ((double)sliderx.getValue() > balance) {
                    JOptionPane.showMessageDialog(pInput, "筹码不能超过余额");
                    sliderx.grabFocus();
                }

            }
        });
        pInput.add(bfp);
        bdb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desk.doubleOdds();
                textArea.append("已选择双倍，此时的赔率为：" + desk.getOdds() + '\n');
                textArea.paintImmediately(textArea.getBounds());
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            }
        });
        pInput.add(bdb);
        btc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desk.licensing(desk.getPlayer());
                desk.getPlayer().printHand();
            }
        });
        pInput.add(btc);
        bsc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desk.resetOdds();
                boolean overp = false;
                boolean overd = false;
                boolean condition;
                if (desk.isBiggerThan21(desk.getPlayer())) {
                    textArea.append("您的牌已爆，");
                    textArea.paintImmediately(textArea.getBounds());
                    condition = true;
                    overp = true;
                }

                desk.getDealer().printHand();

                while(desk.getDealer().getHand().getPoint() < 17) {
                    textArea.append("庄家拿牌, ");
                    desk.licensing(desk.getDealer());
                    desk.getDealer().printHand();
                    if (desk.getDealer().getHand().getPoint() > 21) {
                        textArea.append("庄家爆牌，");
                        textArea.paintImmediately(textArea.getBounds());
                        condition = true;
                        overd = true;
                    }
                }

                textArea.append("庄家拿牌结束");
                textArea.paintImmediately(textArea.getBounds());
                int playerPoint = desk.getPlayer().getHand().getPoint();
                int dealerPoint = desk.getDealer().getHand().getPoint();
                int conditionx;
                if ((playerPoint <= dealerPoint || overp) && !overd) {
                    if (playerPoint >= dealerPoint && !overp) {
                        if (playerPoint < 21) {
                            conditionx = 3;
                        } else {
                            boolean i = desk.getPlayer().getHand().isBlackJack();
                            boolean j = desk.getDealer().getHand().isBlackJack();
                            if (i && j) {
                                conditionx = 3;
                            } else {
                                conditionx = i ? 1 : 2;
                            }
                        }
                    } else {
                        conditionx = 2;
                    }
                } else {
                    conditionx = 1;
                }

                if (overp && overd) {
                    conditionx = 3;
                }

                if (conditionx == 1) {
                    textArea.append("您赢了！");
                    textArea.paintImmediately(textArea.getBounds());
                    desk.getPlayer().setBalance(desk.getPlayer().getBalance() + (desk.getOdds() - 1.0D) * (double)desk.getPlayer().getPokerChips());
                } else if (conditionx == 2) {
                    textArea.append("您输了！");
                    textArea.paintImmediately(textArea.getBounds());
                    if (desk.getPlayer().isDoubleOdds()) {
                        desk.getPlayer().setBalance(desk.getPlayer().getBalance() - 2.0D * (double)desk.getPlayer().getPokerChips());
                    } else {
                        desk.getPlayer().setBalance(desk.getPlayer().getBalance() - (double)desk.getPlayer().getPokerChips());
                    }
                } else {
                    textArea.append("平局");
                }

                textArea.paintImmediately(textArea.getBounds());
                textArea.append("您的余额为：" + desk.getPlayer().getBalance() + '$' + '\n');
                textArea.append("可以重新选择筹码继续游戏。\n ");
                textArea.paintImmediately(textArea.getBounds());
                desk.deleteHand(player);
                desk.deleteHand(dealer);
            }
        });
        pInput.add(bsc);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        SequentialGroup hSeqGroup01 = layout.createSequentialGroup().addComponent(bstart).addComponent(bend);
        SequentialGroup hSeqGroup02 = layout.createSequentialGroup().addComponent(bdb).addComponent(btc).addComponent(bsc);
        ParallelGroup hParalGroup = layout.createParallelGroup().addGroup(hSeqGroup01).addComponent(slider).addComponent(bcheck, Alignment.CENTER).addComponent(textArea, Alignment.CENTER).addComponent(bfp).addGroup(hSeqGroup02);
        layout.setHorizontalGroup(hParalGroup);
        ParallelGroup vParalGroup01 = layout.createParallelGroup().addComponent(bstart).addComponent(bend);
        ParallelGroup vParalGroup02 = layout.createParallelGroup().addComponent(bdb).addComponent(btc).addComponent(bsc);
        SequentialGroup vSeqGroup = layout.createSequentialGroup().addGroup(vParalGroup01).addComponent(slider).addComponent(bcheck).addComponent(textArea).addComponent(bfp).addGroup(vParalGroup02);
        layout.setVerticalGroup(vSeqGroup);
        panel.add(pInput);
    }
}
