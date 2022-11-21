package group.su.control;

import group.GetInfo;
import group.li.pojo.*;
import group.su.map.Buff;
import group.su.map.Obstacle;

import java.util.*;

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
    private Map<Obstacle.ObstacleKind, ArrayList<int[]>> map;

    private int time = 0;
    private int flashCount = 0;

    private boolean EnemyClear = false;

    public static int timeOfGenerateTank=25;
    public static int timeOfRefreshBuff=15;
    public GameInstance() {
        Tank.setGameInstance(this);
        Bullet.setGameInstance(this);
        Buff.setGameInstance(this);
        this.factory = Factory.getFactoryInstance(this);
    }

    public void gameInitial() {

        // 列表初始化
        allBulletList = new Vector<>();
        destroySet = new HashSet<>();
        buffList = new Vector<>();
        enemyTanksList = new Vector<>();

        // 根据地图中的点阵以工厂方法实例化障碍物,存储在Map中
        obstacleMap = initialMap(map);

        System.out.println("game init");

    }

    public void gameStart() {

        // 创建我方坦克
        factory.createGameObject(Factory.GameObject.MyTank, 280, 320);

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

        // 根据键盘输入移动
        moveByKeys();

        if (time % timeOfGenerateTank == 0 && flashCount == 0 && !tempStop) {
            // 每 timeOfGenerateTank s 刷敌方坦克
            factory.createGameObject(Factory.GameObject.EnemyTank, 0, 0);
            factory.createGameObject(Factory.GameObject.EnemyTank, 560, 0);
            factory.createGameObject(Factory.GameObject.EnemyTank, 0, 560);
            factory.createGameObject(Factory.GameObject.EnemyTank, 560, 560);
            EnemyClear = false;
        }

        if (time % timeOfRefreshBuff == 0 && flashCount == 0 && !tempStop) {
            // 每 timeOfRefreshBuff s 刷道具
            factory.createGameObject(Factory.GameObject.RandomBuff);
        }

        // 计时
        if (!tempStop){
            flashCount++;
        }
        if (flashCount == 1000 / REFRESH_TIME) {
            time++;
            System.out.println("test~~  " + time + "s");
            flashCount = 0;
        }

        if (enemyTanksList.isEmpty() && !EnemyClear) {
            // 打光了所有敌方坦克,刷道具
            factory.createGameObject(Factory.GameObject.RandomBuff);
            factory.createGameObject(Factory.GameObject.RandomBuff);
            factory.createGameObject(Factory.GameObject.RandomBuff);
            EnemyClear = true;
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
        // 统计敌方每种 tank 的数量

        int[] counts = new int[3];

        if (enemyTanksList.isEmpty()) {
            return counts;
        }

        synchronized (enemyTanksList) {
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

    public int calculateScore(){
        int score = 0;

        synchronized (destroySet) {
            for (EnemyTank e : destroySet
            ) {
                score += e.getAddScore();
            }
        }

        return score;
    }

    public void setMap(Map<Obstacle.ObstacleKind, ArrayList<int[]>> map) {
        this.map = map;
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
