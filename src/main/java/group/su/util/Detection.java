package group.su.util;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.GetInfo;
import group.su.map.Obstacle;
import group.su.map.Wall;

import java.awt.*;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.OBJECT_SIZE;
import static group.Attributes.gameRun;
import static group.su.util.Factory.drawBullet;

public class Detection {


    // 子弹攻击检测
    /* 重载可选:
         1. 坦克, 障碍物/坦克列表
         2. 敌方坦克列表, 我方坦克
         3. 敌方坦克列表, 障碍物/坦克列表

     */

    public static <T extends GetInfo> void destoryDetection(Bullet bullet, Vector<T> list) {


        for (int i = 0; i < list.size(); i++) {
            T elem = list.get(i);
            if (IsHit(bullet, elem)) {
                if (!(elem instanceof Wall) && !(elem instanceof MyTank)) {
                    elem.setLive(false);
                }
                bullet.setLive(false);
                if (elem instanceof MyTank) {
                    ((MyTank) elem).setHp(((MyTank) elem).getHp() - 1);
                    System.out.println("hit!!  " + ((MyTank) elem).getHp() + " hp left!");
                }
            }
            if (!elem.isLive()) {
                list.remove(elem);
                // 这里的 remove 之后会将遍历的指针前移,所以需要 i--
                i--;
            }
        }
    }

    public static void destoryDetection(Bullet bullet, MyTank myTank) {
        Vector<Tank> oneTankList = new Vector<>();
        oneTankList.add(myTank);
        destoryDetection(bullet, oneTankList);
    }

    public static <T extends GetInfo> boolean IsHit(Bullet bullet, T t) {
        if (bullet.getX() >= t.getX() &&
                bullet.getX() <= t.getX() + OBJECT_SIZE &&
                bullet.getY() >= t.getY() &&
                bullet.getY() <= t.getY() + OBJECT_SIZE) {
            return true;
        }
        return false;
    }

    // 碰撞检测, 参数可接障碍物与坦克
    public static <T extends GetInfo> boolean IsCollision(T t1, T t2) {

        //当前坦克的左上角坐标【bullet.getX(),bullet.getY()】

        if (t1.getX() >= t2.getX() &&
                t1.getX() <= t2.getX() + OBJECT_SIZE &&
                t1.getY() >= t2.getY() &&
                t1.getY() <= t2.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的右上角坐标【bullet.getX() + Constant.TankLength,bullet.getY()】
        if (t1.getX() + OBJECT_SIZE >= t2.getX() &&
                t1.getX() + OBJECT_SIZE <= t2.getX() + OBJECT_SIZE &&
                t1.getY() >= t2.getY() &&
                t1.getY() <= t2.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的左下角坐标【bullet.getX(),bullet.getY()+ Constant.TankLength】
        if (t1.getX() >= t2.getX() &&
                t1.getX() <= t2.getX() + OBJECT_SIZE &&
                t1.getY() + OBJECT_SIZE >= t2.getY() &&
                t1.getY() + OBJECT_SIZE <= t2.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的右下角坐标【bullet.getX()+ Constant.TankLength,bullet.getY()+ Constant.TankLength】
        if (t1.getX() + OBJECT_SIZE >= t2.getX() &&
                t1.getX() + OBJECT_SIZE <= t2.getX() + OBJECT_SIZE &&
                t1.getY() + OBJECT_SIZE >= t2.getY() &&
                t1.getY() + OBJECT_SIZE <= t2.getY() + OBJECT_SIZE) {
            return true;
        }


        return false;

    }

}
