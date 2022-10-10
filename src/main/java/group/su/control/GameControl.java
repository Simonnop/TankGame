package group.su.control;

import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.su.view.GamePanel;
import group.su.view.MainFrame;


import javax.swing.*;

import java.util.Vector;

import static group.Attributes.*;
import static group.Attributes.WINDOW_WIDTH;
import static group.su.map.MapData.map_1;
import static group.su.util.CheckResource.checkResource;
import static group.su.util.Detection.destoryDetection;
import static group.su.util.Factory.initialMap;

public class GameControl {

    public void gameInitial() {
        // 创建游戏主面板
        gamePanel = new GamePanel();
        // 创建主程序面板框
        mainFrame = new MainFrame();
        // 创建子弹列表
        allBulletList = new Vector<>();

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

        // 加入监听器
        mainFrame.addKeyListener(new Listener());

        if (checkResource()) {

            // 开启游戏主面板线程
            new Thread(gamePanel).start();
            // 开启坦克线程,开始移动
            for (EnemyTank enemyTank:enemyTanksList
            ) {
                new Thread(enemyTank).start();
            }
            new Thread(myTank).start();

            System.out.println("start");
        }
    }

    public void gameUpdate() throws InterruptedException {

        // 可加入各种遍历与判断
        while (gameRun) {
            // 主线程休息,控制刷新率与负载
            Thread.sleep(REFRESH_TIME);

            // 重绘游戏主面板
            gamePanel.repaint();
        }
        System.out.println("out");
    }

    public void gameOver() {
        System.out.println("over");
    }
}
