package group.model.util;

import group.model.EnemyTank;
import group.model.FastEnemyTank;
import group.model.MyTank;
import group.model.StrongEnemyTank;

import static group.model.EnemyTank.*;
import static group.model.FastEnemyTank.*;
import static group.model.StrongEnemyTank.*;

public class DirectionUtil {
    //敌方坦克根据方向改变图片
    public static void ChangeImageAccordingDirection(EnemyTank tank) {
        switch (tank.getDirection()) {
            case UP:
                tank.setImage(enemyTank_up);
                break;
            case RIGHT:
                tank.setImage(enemyTank_right);
                break;
            case DOWN:
                tank.setImage(enemyTank_down);
                break;
            case LEFT:
                tank.setImage(enemyTank_left);
                break;
        }
    }


    public static void ChangeImageAccordingDirectionFast(FastEnemyTank tank) {
        switch (tank.getDirection()) {
            case UP:
                tank.setImage(fastEnemyTank_up);
                break;
            case RIGHT:
                tank.setImage(fastEnemyTank_right);
                break;
            case DOWN:
                tank.setImage(fastEnemyTank_down);
                break;
            case LEFT:
                tank.setImage(fastEnemyTank_left);
                break;
        }
    }


    public static void ChangeImageAccordingDirectionStrong(StrongEnemyTank tank) {
        switch (tank.getDirection()) {
            case UP:
                tank.setImage(StrongEnemyTank_up);
                break;
            case RIGHT:
                tank.setImage(StrongEnemyTank_right);
                break;
            case DOWN:
                tank.setImage(StrongEnemyTank_down);
                break;
            case LEFT:
                tank.setImage(StrongEnemyTank_left);
                break;
        }
    }

    //我方坦克根据方向改变图片
    public static void ChangeImageAccordingDirection(MyTank tank) {
        switch (tank.getDirection()) {
            case UP:
                tank.setImage(MyTank.myTank_up);
                break;
            case RIGHT:
                tank.setImage(MyTank.myTank_right);
                break;
            case DOWN:
                tank.setImage(MyTank.myTank_down);
                break;
            case LEFT:
                tank.setImage(MyTank.myTank_left);
                break;
        }
    }

}
