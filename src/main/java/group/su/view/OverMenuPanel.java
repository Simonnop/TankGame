package group.su.view;

import group.su.control.GameInstance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static group.Application.*;
import static group.Application.restart;
import static javax.swing.JOptionPane.showMessageDialog;


public class OverMenuPanel extends JPanel {
    private final MainFrame mainFrame;
    private GameInstance gameInstance;

    // 分数没有以 int 储存, 为避免重复算分,其存在了 gameInstance 里的 destroySet (Set<EnemyTank>)
    static int score = 1;

    public OverMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 700);

        g.setColor(Color.black);
        g.setFont(new Font("幼圆", Font.BOLD, 45));
        g.drawString(playerName + "的分数为" + gameInstance.getDestroySet().size(), 260, 180);

        //重新游戏按钮
        JButton restart = new JButton("重新游戏");
        restart.setSize(260, 40);
        restart.setForeground(Color.WHITE);
        restart.setLocation(250, 300);
        restart.setFont(new Font("幼圆", Font.PLAIN, 20));
        restart.setBackground(new Color(1, 85, 157));
        restart.addActionListener(new restartButtonHandler());
        this.add(restart);
        restart.requestFocus();


        //结束游戏按钮
        JButton endGame = new JButton("结束游戏");
        endGame.setSize(260, 40);
        endGame.setLocation(250, 370);
        endGame.setForeground(Color.WHITE);
        endGame.setFont(new Font("幼圆", Font.PLAIN, 20));
        endGame.setBackground(new Color(1, 85, 157));
        endGame.addActionListener(new endGameButtonHandler());
        this.add(endGame);
        endGame.requestFocus();
    }

    class restartButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            showMessageDialog(mainFrame,
                    "重新游戏", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            restart = true;
        }
    }

    class endGameButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    public void setGameInstance(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }
}
