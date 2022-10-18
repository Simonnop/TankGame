package group.li.util;

import group.Attributes;
import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.StrongEnemyTank;

import java.util.Random;

import static group.Attributes.time;

//敌方坦克随机移动的方法
//后面有待修改
public class RandomMove {

    public static void randomMove(EnemyTank tank) {
        //根据当前坦克的方向来继续移动
        //朝某个方向移动40*Constant.REFRESH_TIME ms 之后再改变方向

        int direction = 0;
        for (int i = 0; i < 30; i++) {
            switch (tank.getDirection()) {
                case 0://向上
                    if (tank.getY() > 0 && !tank.getMovingLock()) {

                        if(tank instanceof FastEnemyTank){
                            tank.moveUp(1.5);
                        }else {
                            tank.moveUp();
                        }
                    }else {
                        //tank.setDirection(2);
                        tank.moveDown(0.5);
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                        checkCollision(tank);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1://向右
                    if (tank.getX() + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH &&
                        !tank.getMovingLock()) {
                        if(tank instanceof FastEnemyTank){
                            tank.moveRight(1.5);
                        }else {
                            tank.moveRight();
                        }
                    }else {
                        //tank.setDirection(1);
                        tank.moveLeft(0.5);
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                        checkCollision(tank);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://向下
                    if (tank.getY() + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH &&
                        !tank.getMovingLock()) {
                        if(tank instanceof FastEnemyTank){
                            tank.moveDown(1.5);
                        }else {
                            tank.moveDown();
                        }
                    }else {
                        //tank.setDirection(2);
                        tank.moveUp(0.5);
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                        checkCollision(tank);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://向左
                    if (tank.getX() > 0 && !tank.getMovingLock()) {
                        if(tank instanceof FastEnemyTank){
                            tank.moveLeft(1.5);
                        }else {
                            tank.moveLeft();
                        }
                    }else{
                        //tank.setDirection(3);
                        tank.moveRight(0.5);
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

        if (time < 10) {
            //游戏刚开始10s  坦克少往上走 只有10%的概率往上走
            if (new Random().nextInt(100) > 90) {
            } else {
                //坦克有80%概率往下走
                if (new Random().nextInt(100) > 80) {
                    direction = 2;
                } else {
                    direction = (int) (Math.random() * 3 + 1);
                }
            }
        } else {
            //然后有60%机率改变方向随机的改变方向{
            if (new Random().nextInt(100) > 40) {
                direction = (int) (Math.random() * 4);
            }

        }
        //改变方向，根据换image
        tank.setDirection(direction);
        if(tank instanceof FastEnemyTank){
            DirectionUtil.ChangeImageAccordingDirectionFast((FastEnemyTank)tank);
        } else if (tank instanceof StrongEnemyTank) {
            DirectionUtil.ChangeImageAccordingDirectionStrong((StrongEnemyTank)tank);
        }else {
            DirectionUtil.ChangeImageAccordingDirection(tank);
        }

    }


    public static void checkCollision(EnemyTank enemyTank) {
        if (CollisionDetection.IsTouchForEnemyTank(enemyTank)) {
            enemyTank.setMovingLock(true);
            switch (enemyTank.getDirection()) {
                case 0:
                    enemyTank.moveDown();
                    break;
                case 1:
                    enemyTank.moveLeft();
                    break;
                case 2:
                    enemyTank.moveUp();
                    break;
                case 3:
                    enemyTank.moveRight();
                    break;
            }
        }

    }
}
