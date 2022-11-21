package group.model.util;

import group.GetInfo;
import group.model.EnemyTank;
import group.model.MyTank;
import group.model.Tank;
import group.map.Buff;
import group.map.Obstacle;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.*;

//碰撞检测的方法
public class CollisionDetection {

    public static void isTouchEnemyTanks(Tank tank, Vector<EnemyTank> enemyTanks) {
        if (tank.getDirectionLock() != null) {
            return;
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            //从vector中取出一辆敌人的坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.equals(tank)) {
                continue;
            }
            if (isCollision(tank, enemyTank) != null) {
                tank.setDirectionLock(isCollision(tank, enemyTank));
            }
        }
    }

    public static void isTouchBuff(Tank tank, Vector<Buff> buffList) {
        for (int i = 0; i < buffList.size(); i++) {
            Buff buff = buffList.get(i);
            if (isCollision(tank, buff) != null && buff.isLive()) {
                buff.getBuffKind().getBuff(tank);
                buff.setLive(false);
            }
        }
    }

    public static void isTouchMyTank(EnemyTank thisTank, MyTank myTank) {
        if (thisTank.getDirectionLock() != null) {
            return;
        }
        thisTank.setDirectionLock(isCollision(thisTank, myTank));
    }

    public static void isTouchObstacles(Tank tank, Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap) {

        if (tank.getDirectionLock() != null) {
            return;
        }
        Iterator<Obstacle.ObstacleKind> iterator = obstacleMap.keySet().iterator();
        while (iterator.hasNext()) {
            Obstacle.ObstacleKind key = iterator.next();
            if (key == Obstacle.ObstacleKind.RIVER ||
                key == Obstacle.ObstacleKind.WALL ||
                key == Obstacle.ObstacleKind.BRICK ||
                key == Obstacle.ObstacleKind.BASE) {
                Vector<Obstacle> obstacles = obstacleMap.get(key);
                for (int i = 0; i < obstacles.size(); i++) {
                    if (isCollision(tank, obstacles.get(i)) != null) {
                        tank.setDirectionLock(isCollision(tank, obstacles.get(i)));
                        if (obstacles.get(i).getKind().equals(Obstacle.ObstacleKind.BASE) && tank instanceof MyTank) {
                            ((MyTank) tank).setBulletNum(((MyTank) tank).getBulletNumLimit());
                        }
                    }
                }
            }
        }
    }

    //为Tank准备的检测方法集成
    public static void isAboutTouchForTank(Tank tank) {
        if (tank instanceof MyTank) {
            tank.setDirectionLock(null);
            isTouchObstacles(tank, Tank.getGameInstance().getObstacleMap());
            isTouchEnemyTanks(tank, Tank.getGameInstance().getEnemyTanksList());
            isTouchBuff(tank, Tank.getGameInstance().getBuffList());
        } else {
            isTouchMyTank((EnemyTank) tank, Tank.getGameInstance().getMyTank());
            isTouchObstacles(tank, Tank.getGameInstance().getObstacleMap());
            isTouchEnemyTanks(tank, Tank.getGameInstance().getEnemyTanksList());
            isTouchBuff(tank, Tank.getGameInstance().getBuffList());
        }
    }

    public static <K extends Tank, T extends GetInfo> Tank.Direction isCollision(K t1, T t2) {


        //当前坦克的左上角坐标【bullet.getX(),bullet.getY()】

        //还是要分情况进行判断，坦克方向不同，检测的位置不一样

        // 根据当前坦克的方向判断障碍物,下一步将要碰撞,则把移动锁设置为该方向
        int step = 1;

        // 帮助坦克寻找空缺方向,实现平滑移动
        int getWayCount = 0;

        switch (t1.getDirection()) {
            case UP:
                //当前坦克的左上角坐标
                if (t1.getX() > t2.getX() &&
                    t1.getX() < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 2;
                }
                //当前坦克的左上角右上角中间坐标
                if (t1.getX() + OBJECT_SIZE / 2 > t2.getX() &&
                    t1.getX() + OBJECT_SIZE / 2 < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 3;
                }
                //当前坦克的右上角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 1;
                }
                if (getWayCount == 2 && !(t2 instanceof EnemyTank)) {
                    t1.setX((t1.getX() + 1));
                }
                if (getWayCount == 1 && !(t2 instanceof EnemyTank)) {
                    t1.setX((t1.getX() - 1));
                }
                if (getWayCount > 0) {
                    return Tank.Direction.UP;
                }
                break;
            case RIGHT:
                //当前坦克的右上角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() > t2.getY() &&
                    t1.getY() < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 2;
                }
                //当前坦克的右上角右下角中间坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE / 2 > t2.getY() &&
                    t1.getY() + OBJECT_SIZE / 2 < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 3;
                }
                //当前坦克的右下角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE > t2.getY() &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 1;
                }
                if (getWayCount == 2 && !(t2 instanceof EnemyTank)) {
                    t1.setY((t1.getY() + 1));
                }
                if (getWayCount == 1 && !(t2 instanceof EnemyTank)) {
                    t1.setY((t1.getY() - 1));
                }
                if (getWayCount > 0) {
                    return Tank.Direction.RIGHT;
                }
                break;
            case DOWN:
                //当前坦克的左下角坐标
                if (t1.getX() > t2.getX() &&
                    t1.getX() < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 2;
                }
                //当前坦克的右下角左下角中间坐标
                if (t1.getX() + OBJECT_SIZE / 2 > t2.getX() &&
                    t1.getX() + OBJECT_SIZE / 2 < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 3;
                }
                //当前坦克的右下角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    getWayCount += 1;
                }
                if (getWayCount == 2 && !(t2 instanceof EnemyTank)) {
                    t1.setX((t1.getX() + 1));
                }
                if (getWayCount == 1 && !(t2 instanceof EnemyTank)) {
                    t1.setX((t1.getX() - 1));
                }
                if (getWayCount > 0) {
                    return Tank.Direction.DOWN;
                }
                break;
            case LEFT:
                //当前坦克的左上角坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() > t2.getY() &&
                    t1.getY() < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 2;
                }
                //当前坦克的左上角左下角中间坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE / 2 > t2.getY() &&
                    t1.getY() + OBJECT_SIZE / 2 < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 3;
                }
                //当前坦克的左下角坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE > t2.getY() &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE) {
                    getWayCount += 1;
                }
                if (getWayCount == 2 && !(t2 instanceof EnemyTank)) {
                    t1.setY((t1.getY() + 1));
                }
                if (getWayCount == 1 && !(t2 instanceof EnemyTank)) {
                    t1.setY((t1.getY() - 1));
                }
                if (getWayCount > 0) {
                    return Tank.Direction.LEFT;
                }
                break;
        }
        return null;

    }
}