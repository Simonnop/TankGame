package group.su.util;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.GetInfo;
import group.su.map.Obstacle;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.*;
import static group.li.pojo.Bullet.enemyTankBullet;
import static group.li.pojo.Bullet.myTankBullet;

public class Factory {

    /*
     *  接入对象绘出物体
     *  使用泛型
     * */

    public static <T extends GetInfo> void drawObject(T t, Graphics g) {

        g.drawImage(t.getImage(),
                t.getX(), t.getY(),
                OBJECT_SIZE, OBJECT_SIZE,
                gamePanel);
    }

    public static void drawBullet(Bullet bullet, Graphics g) {

        // TODO 这里还需要根据坦克的方向,设置子弹发出的位置
        g.drawImage(bullet.getImage(),
                bullet.getX(), bullet.getY(),
                BULLET_SIZE, BULLET_SIZE,
                gamePanel);
    }

    public static void bulletOut(Tank tank) {

        Bullet bullet;

        switch (tank.getDirection()) {
            case 0:  // 上
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getY() - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case 1:  // 右
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE + BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case 2:  // 下
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE + BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case 3:  // 左
                bullet = new Bullet(
                        tank.getX() - BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            default:
                bullet = new Bullet(tank.getX(), tank.getY(), tank.getDirection());
        }


        if (tank instanceof MyTank) {
            bullet.setImage(myTankBullet);
        } else if (tank instanceof EnemyTank) {
            bullet.setImage(enemyTankBullet);
        }

        //System.out.println("bullet out");

        new Thread(bullet).start();

        allBulletList.add(bullet);
    }

    public static Map<Obstacle.ObstacleKind, Vector<Obstacle>> initialMap(List<List<int[]>> map) {

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
}
