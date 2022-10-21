package group.su.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static WelMenuPanel welMenuPanel;

    public MainFrame(){

        welMenuPanel = new WelMenuPanel();
        // 设置主程序面板框属性
        setSize(800, 700);
        setTitle("Tank Battle");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
