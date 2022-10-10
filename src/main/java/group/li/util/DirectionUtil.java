package group.li.util;

import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;

import static group.li.pojo.EnemyTank.*;

public class DirectionUtil {
    //我方坦克根据方向改变图片
    public static void ChangeImageAccordingDirection(EnemyTank tank){
        switch (tank.getDirection()){
            case 0: tank.setImage(enemyTank_up); break;
            case 1: tank.setImage(enemyTank_right); break;
            case 2:tank.setImage(enemyTank_down); break;
            case 3:tank.setImage(enemyTank_left); break;
        }

    }
    //敌方坦克根据方向改变图片
    public static void ChangeImageAccordingDirection(MyTank tank){
        switch (tank.getDirection()){
            case 0: tank.setImage(MyTank.myTank_up); break;
            case 1: tank.setImage(MyTank.myTank_right); break;
            case 2:tank.setImage(MyTank.myTank_down); break;
            case 3:tank.setImage(MyTank.myTank_left); break;
        }
    }

}
