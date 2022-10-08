package group.su.map;

import static group.Constant.REFRESH_TIME;
import static group.su.map.MapData.imageBrick;

public class Brick extends Obstacle implements Runnable{

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

    @Override
    public void run() {
        while (isLive) {
            try {
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
