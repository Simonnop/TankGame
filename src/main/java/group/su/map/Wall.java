package group.su.map;

import group.Constant;

import static group.su.map.MapData.imageWall;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.WALL);
        setBulletPass(false);
        setTankPass(false);
        setImage(imageWall);
    }
}
