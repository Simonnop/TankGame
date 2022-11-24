package group.control;

import group.GetInfo;
import group.map.Obstacle;
import group.map.Buff;
import group.model.pojo.*;

import java.util.*;

import static group.Application.tempStop;
import static group.Attributes.*;

public class GameInstance {

    public static String difficulty; // 游戏难度
    private final Factory factory; // 工厂
    private MyTank myTank; // 我方坦克
    private Vector<EnemyTank> enemyTanksList; // 敌方坦克集(所有种类)
    private Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap; // 可调用的游戏地图  K:障碍物种类 V:障碍物实例
    private Vector<Bullet> allBulletList; // 所有子弹集
    private Vector<Buff> buffList; // Buff集
    private Set<EnemyTank> destroySet; // 摧毁的坦克集
    private Map<Obstacle.ObstacleKind, ArrayList<int[]>> map; // MapData 传过来的地图点集:用于地图生成
    private TreeMap<Integer,String> infoMap = new TreeMap<>(); // 游戏信息图  K:创建时间 V:信息

    private int time = 0; // 游戏进行时间
    private int flashCount = 0; // 刷新的帧数: 用于计时
    private boolean createdObjects = false; // 该秒内是否刷新了下一波物体
    private boolean enemyClear = false; // 敌人是否清空
    public static int timeOfGenerateTank = 25; // 刷新敌人坦克间隔时间
    public static int timeOfRefreshBuff = 15; // 刷新 buff 的时间间隔
    private int lastAddInfoTime = 0; // 上一个游戏信息的添加时间

    public GameInstance() {
        // 设置类指向的游戏实例,方便碰撞监测等
        // 非常不安全,但是方便与高效是真
        Tank.setGameInstance(this);
        Bullet.setGameInstance(this);
        Buff.setGameInstance(this);
        // 设置工厂
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
        // 开启我方坦克线程
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
        // 回收显示一定时间的信息栏消息
        tryRecycleInfoMap();

        // 根据键盘输入移动
        Listener.moveByKeys();

        if (time % timeOfGenerateTank == 0 && !createdObjects && !tempStop) {
            // 每 timeOfGenerateTank s 刷敌方坦克
            factory.createGameObject(Factory.GameObject.EnemyTank, 0, 0);
            factory.createGameObject(Factory.GameObject.EnemyTank, 560, 0);
            factory.createGameObject(Factory.GameObject.EnemyTank, 0, 560);
            factory.createGameObject(Factory.GameObject.EnemyTank, 560, 560);
            // 随着时间推移,每次刷的坦克变多
            if (time > 60) {
                factory.createGameObject(Factory.GameObject.EnemyTank, 0, 40);
            }
            if (time > 120) {
                factory.createGameObject(Factory.GameObject.StrongEnemyTank, 40, 560);
            }
            if (time > 180) {
                factory.createGameObject(Factory.GameObject.EnemyTank, 520, 0);
            }
            if (time > 240) {
                factory.createGameObject(Factory.GameObject.StrongEnemyTank, 560, 520);
            }
            if (time > 300) {
                factory.createGameObject(Factory.GameObject.EnemyTank, 40, 0);
            }
            if (time > 360) {
                factory.createGameObject(Factory.GameObject.StrongEnemyTank, 0, 520);
            }
            if (time > 420) {
                factory.createGameObject(Factory.GameObject.EnemyTank, 560, 40);
            }
            if (time > 480) {
                factory.createGameObject(Factory.GameObject.StrongEnemyTank, 520, 560);
            }
            enemyClear = false;
            // 防止一秒内多次刷新
            createdObjects = true;

            addInfoMap("Game: 新一波敌人来袭");
        }

        if (time % timeOfRefreshBuff == 0 && !createdObjects && !tempStop) {

            // 每 timeOfRefreshBuff s 刷道具
            factory.createGameObject(Factory.GameObject.RandomBuff);
            createdObjects = true;

            addInfoMap("Game: 补给箱已到达");
        }

        // 计时
        if (!tempStop) {
            flashCount++;
        }
        if (flashCount % (1000 / REFRESH_TIME) == 0 && !tempStop) {
            // 刷新的帧数乘上一帧的时间正好满一秒,游戏时间+1
            time++;
            createdObjects = false;
            System.out.println("test~~  " + time + "s");
        }

        if (enemyTanksList.isEmpty() && !enemyClear && time > 5) {
            // 打光了所有敌方坦克,刷道具
            factory.createGameObject(Factory.GameObject.RandomBuff);
            factory.createGameObject(Factory.GameObject.RandomBuff);
            enemyClear = true;
            addInfoMap("Game: 清空敌人奖励--补给箱已到达");
        }
    }

    public void gameOver() {
        System.out.println("game over");
    }

    private <T extends GetInfo> void tryRecycle(Vector<T> list) {
        // 集成回收死亡物体函数: 通过 GetInfo 的 isLive 方法监测
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
        // 统计敌方每种 tank 的数量,供面板显示

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

        // 将原始的点图转化为实例化的障碍物图

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

    public int calculateScore() {
        int score = 0;
        // 计分,通过读取每种被摧毁坦克的对应分数
        synchronized (destroySet) {
            for (EnemyTank e : destroySet
            ) {
                score += e.getAddScore();
            }
        }

        return score;
    }

    public TreeMap<Integer, String> getInfoMap() {
        return infoMap;
    }

    public void addInfoMap(String str) {
        // 添加游戏信息
        int currentTime = time;
        if (time == lastAddInfoTime) {
            // 如果该秒内添加了信息,顺延到下一秒
            currentTime++;
        }
        infoMap.put(currentTime,str);
        lastAddInfoTime = currentTime;
    }

    private void tryRecycleInfoMap() {
        // 回收信息
        if (infoMap.isEmpty()) {
            return;
        }
        synchronized (infoMap) {
            int size = infoMap.size();
            Integer[] times = infoMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < size; i++) {
                // 显示时间达到三秒,就移除
                if (time - times[i] > 3) {
                    infoMap.remove(times[i]);
                }
            }
        }
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

    public double getTime() {
        return flashCount * 25.0 / 1000;
    }
}
