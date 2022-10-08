package group.su.map;

import group.Constant;

import static group.su.map.MapData.imageBrick;

public class Brick extends Obstacle {

    public Brick(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.BRICK);
        setBulletPass(false);
        setTankPass(false);
        setImage(imageBrick);
    }
}
