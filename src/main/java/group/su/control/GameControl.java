package group.su.control;

import group.Application;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.su.map.Obstacle;
import group.su.view.MainPanel;


import javax.swing.*;

import java.util.Vector;

import static group.Attributes.*;
import static group.Attributes.WINDOW_WIDTH;
import static group.su.map.MapData.map_1;
import static group.su.util.Detection.destoryDetection;
import static group.su.util.Factory.initialMap;

public class GameControl {

    public void gameInitial() {

        // 创建主程序面板框
        application = new Application();
        // 创建主面板
        mainPanel = new MainPanel();
        // 创建子弹列表
        allBulletList = new Vector<>();

        // 设置主程序面板框属性
        application.setSize(WINDOW_LENGTH, WINDOW_WIDTH);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 将主面板添加至主程序面板框
        application.add(mainPanel);

        // 根据地图中的点阵以工厂方法实例化障碍物,存储在Map中
        obstacleMap = initialMap(map_1);

        // 实例化坦克
        enemyTanksList = new Vector<>();
        enemyTanksList.add(new EnemyTank(0,0));
        enemyTanksList.add(new EnemyTank(80,0));
        enemyTanksList.add(new EnemyTank(240,0));
        enemyTanksList.add(new EnemyTank(400,0));

        myTank = new MyTank(240,480);
    }

    public void gameStart() throws InterruptedException {

        // 开启坦克线程,开始移动
        for (EnemyTank enemyTank:enemyTanksList
             ) {
            new Thread(enemyTank).start();
        }
        new Thread(myTank).start();

        // 加入监听器
        application.addKeyListener(new Listener());

        // 等待实例化
        Thread.sleep(1000);

        // 开启主面板线程
        new Thread(mainPanel).start();
        System.out.println("start");
    }

    public void gameUpdate() throws InterruptedException {

        // 加入各种遍历与判断
        while (gameRun) {
            // 主线程休息,控制刷新率与负载
            Thread.sleep(REFRESH_TIME);

            // 重绘主面板
            mainPanel.repaint();
        }
        System.out.println("out");
    }

    public void gameOver() {
        System.out.println("over");
    }
}
