package group.li.util;

import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;

import static group.li.pojo.EnemyTank.*;
import static group.li.pojo.FastEnemyTank.*;
import static group.li.pojo.StrongEnemyTank.*;

public class DirectionUtil {
    //敌方坦克根据方向改变图片
    public static void  ChangeImageAccordingDirection(EnemyTank tank){
        switch (tank.getDirection()){
            case 0: tank.setImage(enemyTank_up); break;
            case 1: tank.setImage(enemyTank_right); break;
            case 2:tank.setImage(enemyTank_down); break;
            case 3:tank.setImage(enemyTank_left); break;
        }
    }



    public static void ChangeImageAccordingDirectionFast(FastEnemyTank tank) {
        switch (tank.getDirection()){
            case 0: tank.setImage(fastEnemyTank_up); break;
            case 1: tank.setImage(fastEnemyTank_right); break;
            case 2:tank.setImage(fastEnemyTank_down); break;
            case 3:tank.setImage(fastEnemyTank_left); break;
        }
    }



    public static void ChangeImageAccordingDirectionStrong(StrongEnemyTank tank) {
        switch (tank.getDirection()){
            case 0: tank.setImage(StrongEnemyTank_up); break;
            case 1: tank.setImage(StrongEnemyTank_right); break;
            case 2:tank.setImage(StrongEnemyTank_down); break;
            case 3:tank.setImage(StrongEnemyTank_left); break;
        }
    }

    //我方坦克根据方向改变图片
    public static void ChangeImageAccordingDirection(MyTank tank){
        switch (tank.getDirection()){
            case 0: tank.setImage(MyTank.myTank_up); break;
            case 1: tank.setImage(MyTank.myTank_right); break;
            case 2:tank.setImage(MyTank.myTank_down); break;
            case 3:tank.setImage(MyTank.myTank_left); break;
        }
    }

}
