package group.su.util;

import group.su.map.Brick;
import group.su.map.Obstacle;

import java.awt.*;
import java.util.Vector;

import static group.Constant.OBJECT_SIZE;
import static group.su.control.GameControl.mainPanel;
import static group.su.map.MapData.obstacleMap;

public class DrawFactory {

    /*
     * TODO
     *  以工厂模式物体创建函数(类型,x,y)
     *  使用泛型
     * */

    public static void drawObject(Obstacle obstacle, Graphics g) {

        g.drawImage(obstacle.getImage(),
                obstacle.getX() * OBJECT_SIZE, obstacle.getY() * OBJECT_SIZE,
                OBJECT_SIZE, OBJECT_SIZE,
                mainPanel);
    }

    public static void drawObject(Object obj, int x, int y, Graphics g) {
        // 重载,以便接其他类型
    }


}
