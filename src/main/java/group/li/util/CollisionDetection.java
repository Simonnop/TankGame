package group.li.util;

import group.Constant;
import group.li.pojo.EnemyTank;

import java.util.Vector;

//碰撞检测的方法
public class CollisionDetection {

    //判断敌方坦克相互之间是否发生了碰撞  ,是则返回true
    //判断机制是：每个坦克的四个顶点都不能在敌方坦克的模型范围内
    public static boolean  IsTouchEnemyTank(EnemyTank thisTank, Vector<EnemyTank> enemyTanks){
                //让当前的thisTank 敌人坦克 和 其他所有的敌人坦克比较
                for (int i = 0;i< enemyTanks.size();i++){
                    //从vector中取出一辆敌人的坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (thisTank != enemyTank){
                            //当前坦克的左上角坐标【thisTank.getX(),thisTank.getY()】
                            if (thisTank.getX() >= enemyTank.getX() && thisTank.getX() <= enemyTank.getX() + Constant.OBJECT_SIZE &&
                                    thisTank.getY() >= enemyTank.getY() && thisTank.getY() <= enemyTank.getY() + Constant.OBJECT_SIZE){
                                return true;
                            }
                            //当前坦克的右上角坐标【thisTank.getX() + Constant.OBJECT_SIZE,thisTank.getY()】
                            if (thisTank.getX() + Constant.OBJECT_SIZE >= enemyTank.getX() && thisTank.getX()  + Constant.OBJECT_SIZE <= enemyTank.getX() + Constant.OBJECT_SIZE &&
                                    thisTank.getY() >= enemyTank.getY() && thisTank.getY() <= enemyTank.getY() + Constant.OBJECT_SIZE){
                                return true;
                            }
                            //当前坦克的左下角坐标【thisTank.getX(),thisTank.getY()+ Constant.OBJECT_SIZE】
                            if (thisTank.getX() >= enemyTank.getX() && thisTank.getX()  <= enemyTank.getX() + Constant.OBJECT_SIZE &&
                                    thisTank.getY()+ Constant.OBJECT_SIZE >= enemyTank.getY() && thisTank.getY()+ Constant.OBJECT_SIZE <= enemyTank.getY() + Constant.OBJECT_SIZE){
                                return true;
                            }
                            //当前坦克的右下角坐标【thisTank.getX()+ Constant.OBJECT_SIZE,thisTank.getY()+ Constant.OBJECT_SIZE】
                            if (thisTank.getX()+ Constant.OBJECT_SIZE >= enemyTank.getX() && thisTank.getX()+ Constant.OBJECT_SIZE  <= enemyTank.getX() + Constant.OBJECT_SIZE &&
                                    thisTank.getY()+ Constant.OBJECT_SIZE >= enemyTank.getY() && thisTank.getY()+ Constant.OBJECT_SIZE <= enemyTank.getY() + Constant.OBJECT_SIZE){
                                return true;
                            }
                    }
                }

        return false;

    }

}
