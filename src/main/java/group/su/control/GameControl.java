package group.su.control;

import group.Application;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.map.Obstacle;
import group.su.util.DrawFactory;
import group.su.view.MainPanel;

import javax.swing.*;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static group.Constant.*;
import static group.Constant.WINDOW_WIDTH;
import static group.su.map.MapData.map_1;
import static group.su.map.MapData.obstacleMap;
import static group.su.util.Detection.destoryDetection;

public class GameControl {

    // 因为这些量都不能缺少,所以设置为 static
    public static Application application;
    public static MainPanel mainPanel;
    public static Thread mainPanelThread;
    public static Listener keyListener;

    public static MyTank myTank;
    public static Vector<EnemyTank> enemyTanksList;

    public static EnemyTank enemyTank;

    public static void gameInitial() {

        application = new Application();
        mainPanel = new MainPanel();
        mainPanelThread = new Thread(mainPanel);

        enemyTanksList = new Vector<>();

        application.setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(mainPanel);



        initialMap(map_1);
    }

    private static void initialMap(List<List<int[]>> map) {

        obstacleMap = new HashMap<>();

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            obstacleMap.put(obstacleKind, new Vector<>());
        }

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            int[][] array = map.get(obstacleKind.ordinal()).toArray(new int[0][]);
            for (int[] ints : array
            ) {
                obstacleMap.get(obstacleKind).add(
                        obstacleKind.returnObject(ints[0] * OBJECT_SIZE, ints[1] * OBJECT_SIZE));
            }
        }

    }

    public static void gameStart() {

        System.out.println("start");
        mainPanelThread.start();

        enemyTank=new EnemyTank(400,0);
        new Thread(enemyTank).start();

        myTank = new MyTank(400,400);
        new Thread(myTank).start();

        keyListener = new Listener();
        application.addKeyListener(keyListener);
    }

    public static void gameUpdate() throws InterruptedException {

        // 加入各种遍历与判断
        while (gameRun) {
            Thread.sleep(REFRESH_TIME);
            mainPanel.repaint();

            // destoryDetection(null,null);
        }
        System.out.println("out");
    }

    public static void gameOver() {
        System.out.println("over");
    }
}
