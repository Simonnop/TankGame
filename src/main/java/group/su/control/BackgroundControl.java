package group.su.control;

import group.su.view.GamePanel;
import group.su.view.MainFrame;

import static group.Attributes.gamePanel;
import static group.Attributes.mainFrame;

public class BackgroundControl {

    public BackgroundControl(){
        // 创建游戏主面板
        gamePanel = new GamePanel();
        // 创建主程序面板框
        mainFrame = new MainFrame();
    }

    public void welcomeMenuShow(){
        // mainFrame.add()
        System.out.println("this is welcomeMenu");
    }

    public void gamePanelShow(){
        mainFrame.getContentPane().add(gamePanel);
        System.out.println("gamePanelShow");
    }

    public void gamePanelOut() {
        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        System.out.println("gamePanelOut");
    }

    public void overMenuShow(){
        // mainFrame.add()
    }


}
