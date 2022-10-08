package group.su.map;

public class Tree extends Obstacle {
    public Tree(int x, int y) {
        super(x, y);
        setShape(Shape.TREE);
        setBulletPass(true);
        setTankPass(true);
    }
}
