package group.li.util;

import group.li.pojo.EnemyTank;

//敌方坦克随机移动的方法
public class RandomMove {

    public static void randomMove(EnemyTank tank){
        //根据当前坦克的方向来继续移动
        for (int i = 0; i < 30; i++) {
            switch (tank.getDirection()) {
                case 0://向上
                    if (tank.getY() > 0 && !tank.isTouchEnemyTank()){
                        tank.moveUp();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1://向右
                    if (tank.getX() + 60 < 1000  && !tank.isTouchEnemyTank()){
                        tank.moveRight();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2://向下
                    if (tank.getY() + 60 < 722  && !tank.isTouchEnemyTank()){
                        tank.moveDown();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://向左
                    if (tank.getX() > 0  && !tank.isTouchEnemyTank()){
                        tank.moveLeft();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        //每次移动后，休眠0.05s 这个设定可以改
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //然后随机的改变方向
        int direction = (int) (Math.random() * 4);
        tank.setDirection(direction);

    }
}
