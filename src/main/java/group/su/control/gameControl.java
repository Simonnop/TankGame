package group.su.control;

import group.Application;
import group.su.view.MainPanel;

import javax.swing.*;

import static group.Constant.REFRESH_TIME;
import static group.Constant.gameRun;

public class gameControl {

    public static Application application;
    public static MainPanel mainPanel;
    public static Thread mainPanelThread;
    public static Listener keyListener;

    public static void gameInitial() {

        application = new Application();
        mainPanel = new MainPanel();
        mainPanelThread = new Thread(mainPanel);

        application.setSize(1080, 720);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(mainPanel);
    }

    public static void gameStart() {

        System.out.println("start");
        mainPanelThread.start();

        keyListener = new Listener();
        application.addKeyListener(keyListener);
    }

    public static void gameUpdate() throws InterruptedException {

        // 加入各种遍历与判断
        while (gameRun) {
            Thread.sleep(REFRESH_TIME);
        }
        System.out.println("out");
    }

    public static void gameOver() {
        System.out.println("over");
    }
}
