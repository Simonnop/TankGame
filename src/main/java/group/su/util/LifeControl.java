package group.su.util;

import group.Constant;
import group.su.map.Obstacle;

import java.awt.*;
import java.util.Stack;

import static group.Constant.OBJECT_SIZE;
import static group.su.control.GameControl.mainPanel;

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
    }

    /*
    重载initialize方法 ,tank Bullet的初始化
    public static void initialize(Object o,int x, int y, Graphics g){
    }
    */


    /*
     * TODO
     *  物体销毁函数(对象)
     * */

    public static void destory(Object object) {

    }
}

