package group.su.map;

public class Brick extends Obstacle {

    public Brick(int x, int y) {
        super(x, y);
        setShape(Shape.BRICK);
        setBulletPass(false);
        setTankPass(false);
    }
}
