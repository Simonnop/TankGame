package group.su.map;

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
                return new River(x, y);
            }
        }, WALL {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Wall(x, y);
            }
        }, TREE {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Tree(x, y);
            }
        },BRICK {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Brick(x, y);
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

    boolean tankPass;
    boolean bulletPass;

    Image image;

    public Obstacle(int x, int y) {

        this.isLive = true;
        this.x = x;
        this.y = y;
    }

    public ObstacleKind getObstacleKind() {
        return obstacleKind;
    }

    public void setObstacleKind(ObstacleKind obstacleKind) {
        this.obstacleKind = obstacleKind;
    }

    public boolean isTankPass() {
        return tankPass;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isBulletPass() {
        return bulletPass;
    }

    public void setTankPass(boolean tankPass) {
        this.tankPass = tankPass;
    }

    public void setBulletPass(boolean bulletPass) {
        this.bulletPass = bulletPass;
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
