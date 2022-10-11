package group.li.util;

import group.Attributes;
import group.li.pojo.EnemyTank;

import java.security.SecureRandom;
import java.util.Random;

//敌方坦克随机移动的方法
//后面有待修改
public class RandomMove {

    public static void randomMove(EnemyTank tank){
        //根据当前坦克的方向来继续移动
        //朝某个方向移动40*Constant.REFRESH_TIME ms 之后再改变方向

        int direction=0;
        for (int i = 0; i < 30; i++) {
            switch (tank.getDirection()) {
                case 0://向上
                    if (tank.getY() > 0 && !CollisionDetection.IsTouchForEnemyTank(tank)){
                        tank.moveUp();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1://向右
                    if (tank.getX() + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH && !CollisionDetection.IsTouchForEnemyTank(tank)){
                        tank.moveRight();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://向下
                    if (tank.getY() + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH && !CollisionDetection.IsTouchForEnemyTank(tank)){
                        tank.moveDown();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://向左
                    if (tank.getX() > 0  && !CollisionDetection.IsTouchForEnemyTank(tank)){
                        tank.moveLeft();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }


        //然后有60%机率改变方向随机的改变方向{
        if (new Random().nextInt(100) > 40) {
             direction = (int) (Math.random() * 4);
        }
        //改变方向，根据换image
        tank.setDirection(direction);
        DirectionUtil.ChangeImageAccordingDirection(tank);

    }
}
