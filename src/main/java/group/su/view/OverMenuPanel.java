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

    JButton restartButton = new JButton("回到菜单");
    JButton endGameButton = new JButton("结束游戏");

    public OverMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        restartButton.setSize(260, 40);
        restartButton.setForeground(Color.WHITE);
        restartButton.setLocation(250, 300);
        restartButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        restartButton.setBackground(new Color(1, 85, 157));
        restartButton.addActionListener(new restartButtonHandler());
        this.add(restartButton);

        endGameButton.setSize(260, 40);
        endGameButton.setLocation(250, 370);
        endGameButton.setForeground(Color.WHITE);
        endGameButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        endGameButton.setBackground(new Color(1, 85, 157));
        endGameButton.addActionListener(new endGameButtonHandler());
        this.add(endGameButton);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 700);

        g.setColor(Color.black);
        g.setFont(new Font("幼圆", Font.BOLD, 45));
        g.drawString(playerName + "的分数为" + gameInstance.getDestroySet().size(), 260, 180);

        endGameButton.setSize(260, 40);
        endGameButton.setLocation(250, 370);
        restartButton.setSize(260, 40);
        restartButton.setLocation(250, 300);
        restartButton.requestFocus();
        endGameButton.requestFocus();
    }

    class restartButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
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
