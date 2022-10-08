package group.su.map;

import group.Constant;

import static group.su.map.MapData.imageTree;

public class Tree extends Obstacle {
    public Tree(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.TREE);
        setBulletPass(true);
        setTankPass(true);
        setImage(imageTree);
    }
}
