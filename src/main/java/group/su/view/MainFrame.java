package group.su.view;

import javax.swing.*;

import static group.Attributes.*;

public class MainFrame  extends JFrame {
    public MainFrame(){
        // 设置主程序面板框属性
        setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 将游戏主面板添加至主程序面板框
        add(gamePanel);
    }
}
