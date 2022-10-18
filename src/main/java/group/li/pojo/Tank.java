package group.li.pojo;

import group.Attributes;
import group.GetInfo;

import java.awt.*;
import java.util.Vector;

public class Tank implements GetInfo {
    private int x;  //横坐标
    private int y;  //纵坐标
    private int direction; //坦克的方向 0-上  1-右 2-下 3-左
    private int speed = 1; //默认速度
    private boolean isLive = true; // 判断是否存活
    private Image image;

    private boolean movingLock = false;  // 碰撞后上运动锁

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
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

    public boolean getMovingLock() {
        return movingLock;
    }

    public void setMovingLock(boolean movingLock) {
        this.movingLock = movingLock;
    }

    // 坦克上右下左移动方法  由于所有坦克都要遵守，直接把地图边界限制也写在里面
    public void moveUp() {
        if (y > 0) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (x + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
            x += speed;
        }
    }

    public void moveDown() {
        if (y + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= speed;
        }
    }


    //重载坦克移动方法，便于我方坦克进行速度调整
    public void moveUp(double v) {
        if (y > 0) {
            y -= speed*v;
        }
    }

    public void moveRight(double v) {
        if (x + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
            x += speed*v;
        }
    }

    public void moveDown(double v) {
        if (y + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
            y += speed*v;
        }
    }

    public void moveLeft(double v) {
        if (x > 0) {
            x -= speed*v;
        }
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }


}
