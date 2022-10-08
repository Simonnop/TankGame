package group.su;

import javax.swing.*;

public class gameControl {

    static boolean gameRun = true;
    static final int REFRESH_TIME = 25;

    static Application application = new Application();
    static MainPanel mainPanel= new MainPanel();
    static Thread mainPanelThread= new Thread(mainPanel);
    static Listener keyListener= new Listener();

    public static void gameInitial(){

        application.setSize(1080,720);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(mainPanel);
    }

    public static void gameStart(){
        System.out.println("start");
        mainPanelThread.start();
        application.addKeyListener(keyListener);
    }

    public static void gameUpdate() throws InterruptedException {

        while (gameRun) {

            Thread.sleep(REFRESH_TIME);

            // 加入各种遍历与判断
        }
        System.out.println("out");
    }

    public static void gameOver(){
        System.out.println("over");
    }
}
