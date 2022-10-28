package group.li.pojo;

import group.Attributes;
import group.GetInfo;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.*;
import static group.li.pojo.Bullet.enemyTankBullet;
import static group.li.pojo.Bullet.myTankBullet;
import static group.li.util.CollisionDetection.isAboutTouchForTank;

public class Tank implements GetInfo {

    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }
    // 建立方向的枚举类,看数字真的好累 >_<
    // 适用于坦克,子弹和方向锁

    private int x;  //横坐标
    private int y;  //纵坐标
    private Direction direction; //坦克的方向 0-上  1-右 2-下 3-左
    private int speed = 1; //默认速度
    private boolean isLive = true; // 判断是否存活
    private Image image;

    private Direction directionLock = null;  // 碰撞后上运动锁

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

    public Direction getDirectionLock() {
        return directionLock;
    }

    public void setDirectionLock(Direction directionLock) {
        this.directionLock = directionLock;
    }

    // 坦克上右下左移动方法  由于所有坦克都要遵守，直接把地图边界限制也写在里面
    // speed的作用机制,以speed倍移动一小步,每一次运动前都要进行碰撞检测
    // speed若为分数,则循环 speed向下取整 +1 次
    public void moveUp() {
        for (int i = 0; i < speed; i++) {
            isAboutTouchForTank(this);
            if (y > 0 && getDirectionLock() != Direction.UP) {
                y -= 1;
            }
        }
    }

    public void moveRight() {
        for (int i = 0; i < speed; i++) {
            isAboutTouchForTank(this);
            if (x + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH && getDirectionLock() != Direction.RIGHT) {
                x += 1;
            }
        }
    }

    public void moveDown() {
        for (int i = 0; i < speed; i++) {
            isAboutTouchForTank(this);
            if (y + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH && getDirectionLock() != Direction.DOWN) {
                y += 1;
            }
        }
    }

    public void moveLeft() {
        for (int i = 0; i < speed; i++) {
            isAboutTouchForTank(this);
            if (x > 0 && getDirectionLock() != Direction.LEFT) {
                x -= 1;
            }
        }
    }

    public static void bulletOut(Tank tank) {

        Bullet bullet;

        switch (tank.getDirection()) {
            case UP:  // 上
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getY() - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case RIGHT:  // 右
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE + BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case DOWN:  // 下
                bullet = new Bullet(
                        tank.getX() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE + BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            case LEFT:  // 左
                bullet = new Bullet(
                        tank.getX() - BULLET_SIZE / 2,
                        tank.getY() + OBJECT_SIZE / 2 - BULLET_SIZE / 2,
                        tank.getDirection());
                break;
            default:
                bullet = new Bullet(tank.getX(), tank.getY(), tank.getDirection());
        }


        if (tank instanceof MyTank) {
            bullet.setImage(myTankBullet);
        } else if (tank instanceof EnemyTank) {
            bullet.setImage(enemyTankBullet);
        }

        //System.out.println("bullet out");

        new Thread(bullet).start();

        allBulletList.add(bullet);
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
