package group.su.map;

import static group.Attributes.REFRESH_TIME;
import static group.su.map.MapData.imageBrick;

public class Brick extends Obstacle{

    private boolean isLive = true;

    public Brick(int x, int y) {
        super(x, y);
        setObstacleKind(ObstacleKind.BRICK);
        setBulletPass(false);
        setTankPass(false);
        setImage(imageBrick);
        isLive = true;
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    @Override
    public void setLive(boolean live) {
        isLive = live;
    }

}
