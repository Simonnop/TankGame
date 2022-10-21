package group.li.util;

import group.GetInfo;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.map.Obstacle;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.*;

//碰撞检测的方法
public class CollisionDetection {


    public static void isTouchEnemyTanks(Tank tank, Vector<EnemyTank> enemyTanks) {
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


    public static void isTouchMyTank(EnemyTank thisTank, MyTank tank) {
        tank.setDirectionLock(isCollision(tank, thisTank));
    }

    public static void isTouchObstacles(Tank tank, Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap) {

        Iterator<Obstacle.ObstacleKind> iterator = obstacleMap.keySet().iterator();
        while (iterator.hasNext()) {
            Obstacle.ObstacleKind key = iterator.next();
            if (key == Obstacle.ObstacleKind.RIVER ||
                key == Obstacle.ObstacleKind.WALL ||
                key == Obstacle.ObstacleKind.BRICK) {
                Vector<Obstacle> obstacles = obstacleMap.get(key);
                for (int i = 0; i < obstacles.size(); i++) {
                    if (isCollision(tank, obstacles.get(i)) != null) {
                        tank.setDirectionLock(isCollision(tank, obstacles.get(i)));
                    }
                }
            }
        }
    }

    //为Tank准备的检测方法集成
    public static void isAboutTouchForTank(Tank tank) {
        if (tank instanceof MyTank) {
            tank.setDirectionLock(null);
            isTouchObstacles(tank, obstacleMap);
            isTouchEnemyTanks(tank, enemyTanksList);
        } else {
            isTouchObstacles(tank, obstacleMap);
            isTouchEnemyTanks(tank, enemyTanksList);
            isTouchMyTank((EnemyTank) tank, myTank);
        }
    }

    public static <K extends Tank, T extends GetInfo> Tank.Direction isCollision(K t1, T t2) {

        // 根据当前坦克的方向判断障碍物,下一步将要碰撞,则把移动锁设置为该方向

        //当前坦克的左上角坐标【bullet.getX(),bullet.getY()】

        //还是要分情况进行判断，坦克方向不同，检测的位置不一样
        int step = 1;

        switch (t1.getDirection()) {
            case UP:
                //当前坦克的左上角坐标
                if (t1.getX() > t2.getX() &&
                    t1.getX() < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.UP;
                }
                //当前坦克的左上角右上角中间坐标
                if (t1.getX() + OBJECT_SIZE / 2 > t2.getX() &&
                    t1.getX() + OBJECT_SIZE / 2 < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.UP;
                }
                //当前坦克的右上角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE &&
                    t1.getY() > t2.getY() - step &&
                    t1.getY() < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.UP;
                }
                break;
            case RIGHT:
                //当前坦克的右上角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() > t2.getY() &&
                    t1.getY() < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.RIGHT;
                }
                //当前坦克的右上角右下角中间坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE / 2 > t2.getY() &&
                    t1.getY() + OBJECT_SIZE / 2 < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.RIGHT;
                }
                //当前坦克的右下角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() - step &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE > t2.getY() &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.RIGHT;
                }
                break;
            case DOWN:
                //当前坦克的左下角坐标
                if (t1.getX() > t2.getX() &&
                    t1.getX() < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.DOWN;
                }
                //当前坦克的右下角左下角中间坐标
                if (t1.getX() + OBJECT_SIZE / 2 > t2.getX() &&
                    t1.getX() + OBJECT_SIZE / 2 < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.DOWN;
                }
                //当前坦克的右下角坐标
                if (t1.getX() + OBJECT_SIZE > t2.getX() &&
                    t1.getX() + OBJECT_SIZE < t2.getX() + OBJECT_SIZE &&
                    t1.getY() + OBJECT_SIZE > t2.getY() - step &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE + step) {
                    return Tank.Direction.DOWN;
                }
                break;
            case LEFT:
                //当前坦克的左上角坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() > t2.getY() &&
                    t1.getY() < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.LEFT;
                }
                //当前坦克的左上角左下角中间坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE / 2 > t2.getY() &&
                    t1.getY() + OBJECT_SIZE / 2 < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.LEFT;
                }
                //当前坦克的左下角坐标
                if (t1.getX() > t2.getX() - step &&
                    t1.getX() < t2.getX() + OBJECT_SIZE + step &&
                    t1.getY() + OBJECT_SIZE > t2.getY() &&
                    t1.getY() + OBJECT_SIZE < t2.getY() + OBJECT_SIZE) {
                    return Tank.Direction.LEFT;
                }
                break;
        }
        return null;

    }
}