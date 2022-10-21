package group.su.control;

import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;


import java.util.HashSet;
import java.util.Vector;

import static group.Attributes.*;
import static group.su.map.MapData.map_1;
import static group.su.util.CheckResource.checkResource;
import static group.su.util.DestroyDetection.destoryDetection;
import static group.su.util.Factory.initialMap;

public class GameControl {

    public void gameInitial() {

        // 创建子弹列表
        allBulletList = new Vector<>();

        destroySet = new HashSet<>();

        // 根据地图中的点阵以工厂方法实例化障碍物,存储在Map中
        obstacleMap = initialMap(map_1);

        // 实例化坦克
        enemyTanksList = new Vector<>();
        enemyTanksList.add(new EnemyTank(0, 0));
        enemyTanksList.add(new StrongEnemyTank(80, 0));
        enemyTanksList.add(new FastEnemyTank(240, 0));
        enemyTanksList.add(new EnemyTank(400, 0));

        myTank = new MyTank(240, 480);
    }

    public void gameStart() {

        // 持续等待
        for (; ; ) {
            // 等待静态资源准备完毕
            // 使用 && 短路,减少资源消耗
            if (gameRun && checkResource()) {
                // 开启游戏主面板线程
                new Thread(gamePanel).start();
                // 开启坦克线程,开始移动
                for (EnemyTank enemyTank : enemyTanksList
                ) {
                    new Thread(enemyTank).start();
                }
                new Thread(myTank).start();

                // 加入监听器
                mainFrame.addKeyListener(new Listener());

                System.out.println("start");

                break;
            }
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
