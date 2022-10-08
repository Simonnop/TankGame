package group.su.util;

import com.sun.glass.ui.Screen;
import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.Tank;
import group.su.map.Brick;
import group.su.map.GetPos;
import group.su.map.Obstacle;

import java.util.Vector;

import static group.Constant.OBJECT_SIZE;

public class Detection {
    /*
     * TODO
     *  物体销毁函数(对象)
     * */

    public static void destoryDetection(Vector<Bullet> bulletList, Vector<Brick> brickList) {
        for (Obstacle brick : brickList
        ) {
            for (Bullet bullet : bulletList
            ) {
                if (IsHit(bullet,brick)){
                    brickList.removeElement(brick);
                }
            }
        }
    }

    public static <T extends GetPos> boolean IsHit(Bullet bullet, T t) {
        if (bullet.getX() >= t.getX() &&
            bullet.getX() <= t.getX() + OBJECT_SIZE &&
            bullet.getY() >= t.getY() &&
            bullet.getY() <= t.getY() + OBJECT_SIZE) {
            return true;
        }
        return false;
    }


    public static <T extends GetPos> boolean IsCollision(T t1, T t2) {

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
