package group.su.util;

import group.li.pojo.Tank;
import group.su.map.Obstacle;

import static group.Attributes.*;

public class CheckResource {

    // 检查静态资源是否准备完毕

    public static boolean checkResource(){

        if (myTank == null) {

            return false;
        }

        try {
            for (Tank tank:enemyTanksList) {}
            for (Obstacle.ObstacleKind kind:obstacleMap.keySet()
                 ) {
                for (Obstacle o : obstacleMap.get(kind)) {}
            }
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
}