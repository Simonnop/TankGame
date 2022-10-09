package group.su.map;

import static group.su.map.MapData.imageWall;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.WALL);
        setImage(imageWall);
    }
}
