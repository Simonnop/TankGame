package group.li.pojo;

import group.Application;
import group.GetInfo;
import group.su.control.GameInstance;
import group.su.map.Obstacle;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.*;

//每个子弹都是一个线程 所以实现Runnable接口
public class Bullet implements Runnable, GetInfo {

    public static GameInstance gameInstance;

    private int x;//子弹x坐标
    private int y;//子弹x坐标
    private Tank.Direction direction = null;//子弹方向
    private int speed = 5;//子弹默认速度
    private boolean isLive = true; //子弹是否还存活

    public static Image enemyTankBullet = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/EnemyTankBullet.png"));
    public static Image myTankBullet = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/MyTankBullet.png"));

    private Image image;

    public Bullet(int x, int y, Tank tank) {
        this.x = x;
        this.y = y;
        this.direction = tank.getDirection();
        this.speed = tank.getBulletSpeed();
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

    public Tank.Direction getDirection() {
        return direction;
    }

    public void setDirection(Tank.Direction direction) {
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

    public void move(Tank.Direction direction) {
        switch (direction) {
            case UP: //向上
                y -= speed;
                break;
            case RIGHT: //向右
                x += speed;
                break;
            case DOWN://向下
                y += speed;
                break;
            case LEFT://向左
                x -= speed;
                break;
        }
    }

    @Override
    public void run() {
        //子弹一旦开始创建 ，线程便开始无限循环
        while (Application.gameRun) {

            // 这里必须要加 sleep 否则子弹会直接飞得很远
            try {
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //每次循环都要判断子弹的方向 根据方向从而不停移动
            move(direction);

            // 子弹击中检测
            destoryDetection(this, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BRICK));
            destoryDetection(this, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.WALL));
            destoryDetection(this, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BASE));
            destoryDetection(this, gameInstance.getEnemyTanksList());
            destoryDetection(this, gameInstance.getMyTank());

            //当子弹碰到地图边缘时，线程结束
            if (!(x >= 0 && x <= WINDOW_LENGTH && y >= 0 && y <= WINDOW_WIDTH && isLive
            )) {
                //设置子弹销毁
                isLive = false;
                break;
            }
        }
    }

    public <T extends GetInfo> void destoryDetection(Bullet bullet, Vector<T> list) {


        for (int i = 0; i < list.size(); i++) {
            T elem = list.get(i);
            if (IsHit(bullet, elem)) {
                if (elem instanceof Obstacle && ((Obstacle) elem).getKind().equals(Obstacle.ObstacleKind.BRICK)) {
                    elem.setLive(false);
                }

                bullet.setLive(false);

                if (elem instanceof Tank) {
                    ((Tank) elem).setHp(((Tank) elem).getHp() - 1);
                    System.out.println("hit!!  " + ((Tank) elem).getHp() + " hp left!");
                }
            }

            //加这个判断是为了加快动画消失的速度
            if (elem instanceof Tank) {
                if (((Tank) elem).getHp() <= 0) {
                    elem.setLive(false);
                }
            }
        }
    }

    public void destoryDetection(Bullet bullet, MyTank myTank) {
        Vector<Tank> oneTankList = new Vector<>();
        oneTankList.add(myTank);
        destoryDetection(bullet, oneTankList);
    }

    public <T extends GetInfo> boolean IsHit(Bullet bullet, T t) {
        if (bullet.getX() >= t.getX() &&
            bullet.getX() <= t.getX() + OBJECT_SIZE &&
            bullet.getY() >= t.getY() &&
            bullet.getY() <= t.getY() + OBJECT_SIZE) {

            // 我们坦克命中敌方坦克加分判断
            if (bullet.getImage().equals(myTankBullet) && t instanceof EnemyTank) {
                // 直接加分容易出现重复加分,使用set来避免
                if (((EnemyTank) t).getHp() == 1) {
                    boolean add = gameInstance.getDestroySet().add((EnemyTank) t);
                    if (add) {
                        System.out.println("score: " + gameInstance.getDestroySet().size());
                    }
                }
            }

            return true;
        }
        return false;
    }

    public static GameInstance getGameInstance() {
        return gameInstance;
    }

    public static void setGameInstance(GameInstance gameInstance) {
        Bullet.gameInstance = gameInstance;
    }
}
