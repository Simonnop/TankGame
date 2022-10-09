package group.su.util;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.map.GetInfo;

import java.awt.*;

import static group.Attributes.*;
import static group.li.pojo.Bullet.enemyTankBullet;
import static group.li.pojo.Bullet.myTankBullet;

public class Factory {

    /*
     * TODO
     *  接入对象绘出物体
     *  使用泛型
     * */

    public static <T extends GetInfo> void drawObject(T t, Graphics g) {

        g.drawImage(t.getImage(),
                t.getX(), t.getY(),
                OBJECT_SIZE, OBJECT_SIZE,
                mainPanel);
    }

    public static void drawBullet(Bullet bullet, Graphics g) {

        // 这里还需要根据坦克的方向,设置子弹发出的位置
        g.drawImage(bullet.getImage(),
                bullet.getX(), bullet.getY(),
                BULLET_SIZE, BULLET_SIZE,
                mainPanel);
    }

    public static void bulletOut(Tank tank){

        Bullet bullet = new Bullet(tank.getX(), tank.getY(), tank.getDirection());
        if (tank instanceof MyTank) {
            bullet.setImage(myTankBullet);
        }else if (tank instanceof EnemyTank) {
            bullet.setImage(enemyTankBullet);
        }
        new Thread(bullet).start();
        System.out.println("bullet out");
        tank.getBullets().add(bullet);
    }
}
