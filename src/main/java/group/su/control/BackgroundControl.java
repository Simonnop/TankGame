package group.su.control;

import group.su.view.GamePanel;
import group.su.view.MainFrame;

import static group.Attributes.*;
import static group.su.view.MainFrame.testPanel;
import static group.su.view.MainFrame.welMenuPanel;

public class BackgroundControl {

    public BackgroundControl(){
        // 创建游戏主面板
        gamePanel = new GamePanel();
        // 创建主程序面板框
        mainFrame = new MainFrame();
    }

    public void welcomeMenuShow(){
        System.out.println("this is welcomeMenu");
    }

    public void gamePanelShow() {
        mainFrame.getContentPane().add(gamePanel);
        mainFrame.revalidate();
        System.out.println("gamePanelShow");
        gameRun = true;
    }

    public void gamePanelOut() {
        //mainFrame.getContentPane().removeAll();
        System.out.println("gamePanelOut");

        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        mainFrame.getContentPane().add(welMenuPanel);
        mainFrame.revalidate();
    }

    public void overMenuShow(){
       /* mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        mainFrame.getContentPane().add();*/
    }


}
