package group.su.util;

import com.sun.glass.ui.Screen;
import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.Tank;
import group.li.util.Constant;
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
                if (IsCollision(bullet,brick)){
                    brickList.removeElement(brick);
                }
            }
        }
    }

    public static <T extends GetPos> boolean IsCollision(Bullet bullet, T t) {

        //当前坦克的左上角坐标【bullet.getX(),bullet.getY()】

        if (bullet.getX() >= t.getX() &&
            bullet.getX() <= t.getX() + OBJECT_SIZE &&
            bullet.getY() >= t.getY() &&
            bullet.getY() <= t.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的右上角坐标【bullet.getX() + Constant.TankLength,bullet.getY()】
        if (bullet.getX() + OBJECT_SIZE >= t.getX() &&
            bullet.getX() + OBJECT_SIZE <= t.getX() + OBJECT_SIZE &&
            bullet.getY() >= t.getY() &&
            bullet.getY() <= t.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的左下角坐标【bullet.getX(),bullet.getY()+ Constant.TankLength】
        if (bullet.getX() >= t.getX() &&
            bullet.getX() <= t.getX() + OBJECT_SIZE &&
            bullet.getY() + OBJECT_SIZE >= t.getY() &&
            bullet.getY() + OBJECT_SIZE <= t.getY() + OBJECT_SIZE) {
            return true;
        }
        //当前坦克的右下角坐标【bullet.getX()+ Constant.TankLength,bullet.getY()+ Constant.TankLength】
        if (bullet.getX() + OBJECT_SIZE >= t.getX() &&
            bullet.getX() + OBJECT_SIZE <= t.getX() + OBJECT_SIZE &&
            bullet.getY() + OBJECT_SIZE >= t.getY() &&
            bullet.getY() + OBJECT_SIZE <= t.getY() + OBJECT_SIZE) {
            return true;
        }


        return false;

    }

}
