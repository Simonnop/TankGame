package group.su.control;

import group.su.view.GamePanel;
import group.su.view.MainFrame;
import group.su.view.OverMenuPanel;

import static group.Attributes.*;
import static group.su.view.MainFrame.*;

public class BackgroundControl {



    public BackgroundControl() {
        // 创建游戏主面板
        gamePanel = new GamePanel();
        // 创建主程序面板框
        mainFrame = new MainFrame();
    }

    public void welcomeMenuShow() {
        mainFrame.getContentPane().add(welMenuPanel);
        mainFrame.repaint();
        System.out.println("this is welcomeMenu");
    }

    public void gamePanelShow() {
        for (; ; ) {
            System.out.print("");
            if (gameRun) {
                mainFrame.getContentPane().remove(welMenuPanel);
                mainFrame.getContentPane().add(gamePanel);
                mainFrame.revalidate();
                mainFrame.repaint();
                System.out.println("gamePanelShow");
                break;
            }
        }
    }

    public void gamePanelOut() {
        for (; ; ) {
            System.out.print("");
            if (!gameRun) {
                mainFrame.getContentPane().remove(gamePanel);
                mainFrame.repaint();
                System.out.println("gamePanelOut");
                break;
            }
        }
    }

    public void overMenuShow() {
        //奇怪的调节方法？？？，但是可以让结束界面完整出现
        mainFrame.setSize(800+1,700);
        mainFrame.setSize(800,700);
        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.getContentPane().add(new OverMenuPanel());
    }

}
