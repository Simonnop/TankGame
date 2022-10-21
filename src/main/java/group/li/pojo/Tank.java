package group.li.pojo;

import group.Attributes;
import group.GetInfo;

import java.awt.*;
import java.util.Vector;

import static group.li.util.CollisionDetection.IsTouchForTank;

public class Tank implements GetInfo {

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    private int x;  //横坐标
    private int y;  //纵坐标
    private Direction direction; //坦克的方向 0-上  1-右 2-下 3-左
    private double speed = 1; //默认速度
    private boolean isLive = true; // 判断是否存活
    private Image image;

    private Direction movingLock = null;  // 碰撞后上运动锁

    private Vector<Bullet> bullets = new Vector<>();

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Direction getMovingLock() {
        return movingLock;
    }

    public void setMovingLock(Direction movingLock) {
        this.movingLock = movingLock;
    }

    // 坦克上右下左移动方法  由于所有坦克都要遵守，直接把地图边界限制也写在里面
    public void moveUp() {
        for (int i = 0; i < speed; i++) {
            IsTouchForTank(this);
            if (y > 0 && getMovingLock() != Direction.UP) {
                y -= 1;
            }
        }
    }

    public void moveRight() {
        for (int i = 0; i < speed; i++) {
            IsTouchForTank(this);
            if (x + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH && getMovingLock() != Direction.RIGHT) {
                x += 1;
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < speed; i++) {
            IsTouchForTank(this);
            if (y + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH && getMovingLock() != Direction.DOWN) {
                y += 1;
            }
        }
    }

    public void moveLeft() {
        for (int i = 0; i < speed; i++) {
            IsTouchForTank(this);
            if (x > 0 && getMovingLock() != Direction.LEFT) {
                x -= 1;
            }
        }
    }


    //重载坦克移动方法，便于我方坦克进行速度调整
//    public void moveUp(double v) {
//        if (y > 0) {
//            y -= speed * v;
//        }
//    }
//
//    public void moveRight(double v) {
//        if (x + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
//            x += speed * v;
//        }
//    }
//
//    public void moveDown(double v) {
//        if (y + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
//            y += speed * v;
//        }
//    }
//
//    public void moveLeft(double v) {
//        if (x > 0) {
//            x -= speed * v;
//        }
//    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }


}
