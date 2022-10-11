package group.li.util;

import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.map.Obstacle;
import group.su.util.Detection;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import static group.Attributes.*;

//碰撞检测的方法
public class CollisionDetection {


    public static boolean  IsTouchEnemyTanks(Tank tank, Vector<EnemyTank> enemyTanks){
        //让当前的thisTank 敌人坦克 和 其他所有的敌人坦克比较
        for (int i = 0;i< enemyTanks.size();i++){
            //从vector中取出一辆敌人的坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //不和自己比较
            if (tank != enemyTank){
                Detection.IsCollision(tank,enemyTank);
            }
        }
        return false;
    }



    public static  boolean IsTouchMyTank(EnemyTank thisTank, MyTank tank) {
        return Detection.IsCollision(thisTank,tank);
    }
    public static boolean  IsTouchObstacles(Tank tank, Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap){

        Iterator<Obstacle.ObstacleKind> iterator=obstacleMap.keySet().iterator();
        while (iterator.hasNext()){
            Obstacle.ObstacleKind key=iterator.next();
            if(key== Obstacle.ObstacleKind.RIVER ||key== Obstacle.ObstacleKind.WALL ||key== Obstacle.ObstacleKind.BRICK){
                Vector<Obstacle> obstacles = obstacleMap.get(key);
                for (int i=0;i<obstacles.size();i++){
                    if(Detection.IsCollision(tank,obstacles.get(i))==true){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    //为myTank准备的检测方法集成
    public static boolean  IsTouchForMyTank(){
        if(IsTouchObstacles(myTank,obstacleMap)==true){
            return true;
        }
        if(IsTouchEnemyTanks(myTank,enemyTanksList)==true){
            return true;
        }
        return false;
    }
    //为enemyTank准备的检测方法集成
    public static boolean  IsTouchForEnemyTank(EnemyTank tank){

        if(IsTouchObstacles(tank,obstacleMap)==true){
            return true;
        }
        if(IsTouchEnemyTanks(tank,enemyTanksList)==true){
            return true;
        }
        if(IsTouchMyTank(tank,myTank)==true){
            return true;
        }
        return false;

    }

}