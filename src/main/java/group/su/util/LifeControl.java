package group.su.util;

import group.Constant;
import group.su.map.Obstacle;

import java.awt.*;

import static group.Constant.OBJECT_SIZE;
import static group.su.control.gameControl.mainPanel;

public class LifeControl {

    /*
     * TODO
     *  物体创建函数(类型,x,y)
     *  使用泛型
     * */

    public static void initialize(Obstacle.ObstacleKind objectKind, int x, int y, Graphics g) {

        Obstacle obstacle = objectKind.returnObject(x, y);

        g.drawImage(obstacle.getImage(),
                obstacle.getX() * OBJECT_SIZE, obstacle.getY() * OBJECT_SIZE,
                OBJECT_SIZE, OBJECT_SIZE,
                mainPanel);

        Thread thread = new Thread(obstacle);
        thread.start();

    }

    /*
     * TODO
     *  物体销毁函数(对象)
     * */

    public static void destory(Object object) {

    }
}
