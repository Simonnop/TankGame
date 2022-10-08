package group.li.util;

import group.Constant;
import group.li.pojo.EnemyTank;

//敌方坦克随机移动的方法
//后面有待修改
public class RandomMove {

    public static void randomMove(EnemyTank tank){
        //根据当前坦克的方向来继续移动
        //朝某个方向移动20*Constant.REFRESH_TIME ms 之后再改变方向
        for (int i = 0; i < 20; i++) {
            switch (tank.getDirection()) {
                case 0://向上
                    if (tank.getY() > 0 && !tank.isTouchEnemyTank()){
                        tank.moveUp();
                    }
                    try {
                        Thread.sleep(Constant.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1://向右
                    if (tank.getX() + Constant.OBJECT_SIZE < Constant.WINDOW_WIDTH  && !tank.isTouchEnemyTank()){
                        tank.moveRight();
                    }
                    try {
                        Thread.sleep(Constant.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://向下
                    if (tank.getY() + Constant.OBJECT_SIZE < Constant.WINDOW_LENGTH  && !tank.isTouchEnemyTank()){
                        tank.moveDown();
                    }
                    try {
                        Thread.sleep(Constant.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://向左
                    if (tank.getX() > 0  && !tank.isTouchEnemyTank()){
                        tank.moveLeft();
                    }
                    try {
                        Thread.sleep(Constant.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

        //然后随机的改变方向
        int direction = (int) (Math.random() * 4);
        tank.setDirection(direction);

    }
}
