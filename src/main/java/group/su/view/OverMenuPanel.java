package group.su.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class OverMenuPanel extends JPanel{

    //没找到分数在哪，暂时用一下

    // 分数没有以 int 储存, 为避免重复算分,其存在了 attributes 里的 destroySet (Set<EnemyTank>)
    // 我算分是取它的 size(), 后面应该会更改
    static int score = 1;
    public  OverMenuPanel(){

        this.setSize(800,700);
        this.setLayout(null);
        JLabel jl = new JLabel("您的分数为"+score);
        jl.setSize(320,80);
        jl.setLocation(300,50);
        jl.setFont(new Font("幼圆",Font.PLAIN,35));
        this.add(jl);

        //装按钮的框
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(500,50);
        buttonPanel.setLocation(150,200);
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel);

        //重新游戏按钮
        JButton restart = new JButton("重新游戏");
        restart.setSize(200,40);
        restart.setForeground(Color.WHITE);
        restart.setFont(new Font("幼圆", Font.PLAIN, 20));
        restart.setBackground(new Color(1, 85, 157));
        restart.addActionListener(new restartButtonHandler());
        buttonPanel.add(restart);

        //结束游戏按钮
        JButton endGame = new JButton("结束游戏");
        endGame.setSize(200,40);
        endGame.setForeground(Color.WHITE);
        endGame.setFont(new Font("幼圆", Font.PLAIN, 20));
        endGame.setBackground(new Color(1, 85, 157));
        endGame.addActionListener(new endGameButtonHandler());
        buttonPanel.add(endGame);
    }


 class restartButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

 class endGameButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
