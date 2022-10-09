package group.su.util;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.GetInfo;
import group.su.map.Obstacle;
import group.su.map.Wall;

import java.util.Map;
import java.util.Vector;

import static group.Attributes.OBJECT_SIZE;

public class Detection {


    // 子弹攻击检测
    /* 重载可选:
         1. 坦克, 障碍物/坦克列表
         2. 敌方坦克列表, 我方坦克
         3. 敌方坦克列表, 障碍物/坦克列表

     */

    public static <T extends GetInfo> void destoryDetection(Tank tank, Vector<T> list) {

        // 为避免线程内冲突,创建一个list来记录要删除的对象,后统一删除
        Vector<T> removeList = new Vector<>();

        for (T elem : list
        ) {
            for (Bullet bullet : tank.getBullets()
            ) {
                if (IsHit(bullet, elem)) {
                    if (!(elem instanceof Wall)) {
                        elem.setLive(false);
                    }
                    bullet.setLive(false);
                }
            }
            if (!elem.isLive()) {
                removeList.add(elem);
            }
        }

        list.removeAll(removeList);
    }

    public static void destoryDetection(Vector<EnemyTank> tankList, MyTank myTank) {
        Vector<Tank> oneTankList = new Vector<>();
        oneTankList.add(myTank);
        destoryDetection(tankList,oneTankList);
    }

    public static <T extends GetInfo> void destoryDetection(Vector<EnemyTank> tankList, Vector<T> list) {
        for (Tank tank:tankList
             ) {
            destoryDetection(tank,list);
        }
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
