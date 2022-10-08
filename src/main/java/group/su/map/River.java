package group.su.map;

import group.Constant;

import static group.su.map.MapData.imageRiver;

public class River extends Obstacle {

    public River(int x, int y) {

        super(x, y);
        setObstacleKind(ObstacleKind.RIVER);
        setBulletPass(true);
        setTankPass(false);
        setImage(imageRiver);
    }
}
