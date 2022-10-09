package group.su.map;

import static group.su.map.MapData.imageTree;

public class Tree extends Obstacle {
    public Tree(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.TREE);
        setImage(imageTree);
    }
}
