package group.su.control;

import group.Application;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.su.map.Obstacle;
import group.su.view.MainPanel;

import javax.swing.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.*;
import static group.Attributes.WINDOW_WIDTH;
import static group.su.map.MapData.map_1;
import static group.su.util.Detection.destoryDetection;

public class GameControl {

    public void gameInitial() {

        // 创建主程序面板框
        application = new Application();
        // 创建主面板
        mainPanel = new MainPanel();

        // 设置主程序面板框属性
        application.setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 将主面板添加至主程序面板框
        application.add(mainPanel);

        // 根据地图中的点阵实例化障碍物,存储在Map中
        obstacleMap = initialMap(map_1);

        // 实例化坦克
        enemyTanksList = new Vector<>();
        enemyTanksList.add(new EnemyTank(270,0));
        enemyTanksList.add(new EnemyTank(540,0));
        enemyTanksList.add(new EnemyTank(810,0));

        myTank = new MyTank(520,600);
    }

    private Map<Obstacle.ObstacleKind, Vector<Obstacle>> initialMap(List<List<int[]>> map) {

        Map<Obstacle.ObstacleKind, Vector<Obstacle>> newMap = new HashMap<>();

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            newMap.put(obstacleKind, new Vector<>());
        }

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            int[][] array = map.get(obstacleKind.ordinal()).toArray(new int[0][]);
            for (int[] ints : array
            ) {
                // 工厂化创建对象
                newMap.get(obstacleKind).add(
                        obstacleKind.returnObject(ints[0] * OBJECT_SIZE, ints[1] * OBJECT_SIZE));
            }
        }
        return newMap;
    }

    public void gameStart() {

        // 开启坦克线程,开始移动
        for (EnemyTank enemyTank:enemyTanksList
             ) {
            new Thread(enemyTank).start();
        }
        new Thread(myTank).start();

        // 加入监听器
        application.addKeyListener(new Listener());

        // 开启主面板线程
        new Thread(mainPanel).start();
        System.out.println("start");
    }

    public void gameUpdate() throws InterruptedException {

        // 加入各种遍历与判断
        while (gameRun) {
            // 主线程休息,控制刷新率与负载
            Thread.sleep(REFRESH_TIME);
            // 我方坦克子弹打砖
            destoryDetection(myTank,obstacleMap.get(Obstacle.ObstacleKind.BRICK));
            // 我方坦克子弹打敌方坦克
            destoryDetection(myTank,enemyTanksList);
            // 敌方坦克打砖
            destoryDetection(enemyTanksList,obstacleMap.get(Obstacle.ObstacleKind.BRICK));
            // 敌方坦克打我方坦克
            destoryDetection(enemyTanksList,myTank);
            // 重绘主面板
            mainPanel.repaint();
        }
        System.out.println("out");
    }

    public void gameOver() {
        System.out.println("over");
    }
}
