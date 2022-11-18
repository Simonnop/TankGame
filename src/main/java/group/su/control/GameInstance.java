package group.su.control;

import group.GetInfo;
import group.li.pojo.*;
import group.su.map.Buff;
import group.su.map.Obstacle;

import java.util.*;

import static group.Application.gameRun;
import static group.Application.tempStop;
import static group.Attributes.*;
import static group.su.control.Listener.moveByKeys;


public class GameInstance {

    private final Factory factory;

    private MyTank myTank;
    private Vector<EnemyTank> enemyTanksList;
    private Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap;
    private Vector<Bullet> allBulletList;
    private Vector<Buff> buffList;
    private Set<EnemyTank> destroySet;
    private final Map<Obstacle.ObstacleKind, ArrayList<int[]>> map;

    private int time = 0;
    private int flashCount = 0;

    public GameInstance(Map<Obstacle.ObstacleKind, ArrayList<int[]>> map) {
        Tank.setGameInstance(this);
        Bullet.setGameInstance(this);
        Buff.setGameInstance(this);
        this.factory = new Factory(this);
        this.map = map;
    }

    public void gameInitial() {

        // 列表初始化
        allBulletList = new Vector<>();
        destroySet = new HashSet<>();
        buffList = new Vector<>();
        enemyTanksList = new Vector<>();

        // 创建敌坦克
        factory.createGameObject(Factory.GameObject.EnemyTank, 0, 0);
        factory.createGameObject(Factory.GameObject.StrongEnemyTank, 80, 0);
        factory.createGameObject(Factory.GameObject.FastEnemyTank, 240, 0);
        factory.createGameObject(Factory.GameObject.EnemyTank, 400, 0);
        // 创建我方坦克
        factory.createGameObject(Factory.GameObject.MyTank, 240, 480);
        // 根据地图中的点阵以工厂方法实例化障碍物,存储在Map中
        obstacleMap = initialMap(map);
        // 创建道具
        factory.createGameObject(Factory.GameObject.RandomBuff);
        factory.createGameObject(Factory.GameObject.RandomBuff);
        factory.createGameObject(Factory.GameObject.RandomBuff);

        System.out.println("game init");

    }

    public void gameStart() {

        // 开启坦克线程,开始移动
        for (EnemyTank enemyTank : enemyTanksList
        ) {
            new Thread(enemyTank).start();
        }
        new Thread(myTank).start();

        System.out.println("game start");
    }

    public void gameUpdate() throws InterruptedException {

        // 线程休息,控制负载
        Thread.sleep(REFRESH_TIME);

        // 尝试回收 isLive 为 false 的对象
        tryRecycle(obstacleMap.get(Obstacle.ObstacleKind.BRICK));
        tryRecycle(enemyTanksList);
        tryRecycle(allBulletList);
        tryRecycle(buffList);

        moveByKeys();

        // 计时
        if (!tempStop){
            flashCount++;
        }
        if (flashCount == 1000 / REFRESH_TIME) {
            time++;
            System.out.println("test~~  " + time + "s");
            flashCount = 0;
        }

        // 打光了所有敌方坦克,游戏结束
        if (enemyTanksList.isEmpty()) {
            gameRun = false;
            System.out.println("win");
        }
    }

    public void gameOver() {
        System.out.println("game over");
    }

    private <T extends GetInfo> void tryRecycle(Vector<T> list) {
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

    public int[] countKind() {
        int[] counts = new int[3];

        for (EnemyTank e : enemyTanksList
        ) {
            if (e instanceof FastEnemyTank) {
                counts[1]++;
            } else if (e instanceof StrongEnemyTank) {
                counts[2]++;
            } else {
                counts[0]++;
            }
        }

        return counts;
    }

    private Map<Obstacle.ObstacleKind, Vector<Obstacle>> initialMap(
            Map<Obstacle.ObstacleKind, ArrayList<int[]>> map) {

        Map<Obstacle.ObstacleKind, Vector<Obstacle>> newMap = new HashMap<>();

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            newMap.put(obstacleKind, new Vector<>());
        }

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            int[][] array = map.get(obstacleKind).toArray(new int[0][]);
            for (int[] ints : array
            ) {
                newMap.get(obstacleKind).add(
                        obstacleKind.returnObject(ints[0] * OBJECT_SIZE, ints[1] * OBJECT_SIZE));
            }
        }
        return newMap;
    }

    public Factory getFactory() {
        return factory;
    }

    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    public Vector<EnemyTank> getEnemyTanksList() {
        return enemyTanksList;
    }

    public Map<Obstacle.ObstacleKind, Vector<Obstacle>> getObstacleMap() {
        return obstacleMap;
    }

    public Vector<Bullet> getAllBulletList() {
        return allBulletList;
    }

    public Vector<Buff> getBuffList() {
        return buffList;
    }

    public Set<EnemyTank> getDestroySet() {
        return destroySet;
    }

    public int getTime() {
        return time;
    }
}
