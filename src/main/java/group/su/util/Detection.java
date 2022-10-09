package group.su.util;

import group.li.pojo.Bullet;
import group.su.map.GetInfo;

import java.util.Vector;

import static group.Attributes.OBJECT_SIZE;

public class Detection {
    /*
     * TODO
     *  物体销毁函数(对象)
     * */

    // 子弹攻击检测
    // 第二个参数可接 障碍物 或 坦克 列表
    public static <T extends GetInfo> void destoryDetection(Vector<Bullet> bulletList, Vector<T> list) {
        for (T elem : list
        ) {
            for (Bullet bullet : bulletList
            ) {
                if (IsHit(bullet,elem)){
                    list.removeElement(elem);
                    bulletList.remove(bullet);
                }
            }
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
