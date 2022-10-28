package group.su.control;

import group.su.view.GamePanel;
import group.su.view.MainFrame;

import static group.Application.runApplication;
import static group.Attributes.*;
import static group.su.view.MainFrame.*;

public class PanelControl {

    public PanelControl() {
        // 创建游戏主面板
        gamePanel = new GamePanel();
        // 创建主程序面板框
        mainFrame = new MainFrame();
    }

    public void welcomeMenuShow() {
        mainFrame.getContentPane().removeAll();
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

    public void overMenuShow() throws InterruptedException {
        //奇怪的调节方法？？？，但是可以让结束界面完整出现
        mainFrame.setSize(800 + 1, 700);
        mainFrame.setSize(800, 700);
        mainFrame.getContentPane().add(overMenuPanel);
        mainFrame.repaint();
        Thread.sleep(1000);
        for (; ; ) {
            System.out.print("");
            if (restart) {
                gameRun = true;
                restart = false;
                runApplication();
            }
        }
    }
}
