package group.su.control;

import group.GetInfo;
import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;
import group.su.map.Obstacle;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import static group.Attributes.*;
import static group.su.map.Buff.createBuff;
import static group.su.map.MapData.initialMap;
import static group.su.map.MapData.map_1;

public class GameControl {

    static Listener listener = new Listener();

    public void gameInitial() throws InterruptedException {

        // 创建子弹列表
        allBulletList = new Vector<>();

        destroySet = new HashSet<>();

        buffList = new Vector<>();

        // 根据地图中的点阵以工厂方法实例化障碍物,存储在Map中
        obstacleMap = initialMap(map_1);

        // 实例化坦克
        enemyTanksList = new Vector<>();
        enemyTanksList.add(new EnemyTank(0, 0));
        enemyTanksList.add(new StrongEnemyTank(80, 0));
        enemyTanksList.add(new FastEnemyTank(240, 0));
        enemyTanksList.add(new EnemyTank(400, 0));

        myTank = new MyTank(240, 480);

        buffList.add(createBuff());
        buffList.add(createBuff());
    }

    public void gameStart() {

        // 持续等待
        for (; ; ) {
            if (gameRun) {
                // 开启游戏主面板线程
                new Thread(gamePanel).start();
                // 开启坦克线程,开始移动
                for (EnemyTank enemyTank : enemyTanksList
                ) {
                    new Thread(enemyTank).start();
                }
                new Thread(myTank).start();

                // 加入监听器,并保证只有一个监听器
                mainFrame.removeKeyListener(listener);
                mainFrame.addKeyListener(listener);

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

            tryRecycle(obstacleMap.get(Obstacle.ObstacleKind.BRICK));
            tryRecycle(enemyTanksList);
            tryRecycle(allBulletList);

            // 重绘游戏主面板
            gamePanel.repaint();
        }
        System.out.println("out");
    }

    public void gameOver() {
        System.out.println("over");
    }

    public <T extends GetInfo> void tryRecycle(Vector<T> list) {
        synchronized (list) {
            Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                T t = iterator.next();
                if (!t.isLive()) {
                    iterator.remove();
                }
            }
        }
    }
}
