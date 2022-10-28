package group.su.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static WelMenuPanel welMenuPanel;

    public static OverMenuPanel overMenuPanel;

    public MainFrame(){

        welMenuPanel = new WelMenuPanel();

        overMenuPanel = new OverMenuPanel();
        // 设置主程序面板框属性
        setSize(800, 700);
        setTitle("Tank Battle");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
