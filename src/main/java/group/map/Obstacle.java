package group.map;

import group.GetInfo;

import java.awt.*;

public class Obstacle implements GetInfo {

    /*
     *
     *  建立地图上的障碍物
     *  河,墙,树,砖
     *  根据不同的类型附上不同的属性
     * */

    ObstacleKind obstacleKind;

    public enum ObstacleKind {
        RIVER {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageRiver, RIVER);
            }
        }, WALL {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageWall, WALL);
            }
        }, TREE {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageTree, TREE);
            }
        }, BRICK {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageBrick, BRICK);
            }
        }, BASE {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageBase, BASE);
            }
        };

        /*, MyTank {
            @Override
            public Object returnObject(int x, int y) {
                return new MyTank(x, y);
            }
        }, EnemyTank {
            @Override
            public Object returnObject(int x, int y) {
                return new EnemyTank(x, y);
            }
        }, Bullet {
            @Override
            public Object returnObject(int x, int y) {
                return new Bullet(x, y, 0);
            }
        }*/
        public Obstacle returnObject(int x, int y) {
            return null;
        }
    }

    boolean isLive;

    final int x;
    final int y;

    Image image;

    public Obstacle(int x, int y, Image image, ObstacleKind obstacleKind) {
        this.obstacleKind = obstacleKind;
        this.x = x;
        this.y = y;
        this.image = image;
        this.isLive = true;
    }

    public ObstacleKind getKind() {
        return obstacleKind;
    }

    public void setObstacleKind(ObstacleKind obstacleKind) {
        this.obstacleKind = obstacleKind;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
