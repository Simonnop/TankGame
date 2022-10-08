package group.su.map;

public class River extends Obstacle {
    public River(int x, int y) {

        super(x, y);
        setShape(Shape.RIVER);
        setBulletPass(true);
        setTankPass(false);
    }
}
