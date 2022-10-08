package group.su.map;

public class Wall extends Obstacle {
    public Wall(int x, int y) {
        super(x, y);
        setShape(Shape.WALL);
        setBulletPass(false);
        setTankPass(false);
    }
}
