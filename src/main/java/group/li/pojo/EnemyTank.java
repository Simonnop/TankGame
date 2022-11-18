package group.li.pojo;

import group.Application;
import group.Attributes;
import group.GetInfo;
import group.li.util.DirectionUtil;
import group.su.map.Dot;
import group.su.map.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


//每个敌方坦克也是一个线程
public class EnemyTank extends Tank implements Runnable, GetInfo {

    public static Image enemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_up.png"));
    public static Image enemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_down.png"));
    public static Image enemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_left.png"));
    public static Image enemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_right.png"));


    public EnemyTank(int x, int y) {
        super(x, y);
        setImage(enemyTank_down);
        setDirection(Direction.DOWN);
    }

    public void run() {
        /*坦克在2-4s刷新后发子弹*/
        int randomTime = (int) (Math.random() * 2.0 + 2.0);

        while (Application.gameRun) {

            randomMove(this);
            try {
                Thread.sleep(10L);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            }
            //游戏开始5s后再开始发射子弹
            if (gameInstance.getTime() > 5) {
                --randomTime;
                if (randomTime == 0 && this.isLive()) {
                    bulletOut(this);
                    randomTime = (int) (Math.random() * 2.0 + 2.0);
                }
            }
            if (!this.isLive()) {
                break;
            }
        }

    }

    public void randomMove(EnemyTank tank) {
        //根据当前坦克的方向来继续移动
        int direction = 0;

        if (gameInstance.getTime() < 10) {
            //游戏刚开始10s  坦克少往上走 只有10%的概率往上走
            if (new Random().nextInt(100) > 90) {
            } else {
                //坦克有80%概率往下走
                if (new Random().nextInt(100) > 80) {
                    direction = 2;
                } else {
                    direction = (int) (Math.random() * 3 + 1);
                }
            }
        } else {
            //然后有60%机率随机的改变方向{
            if (new Random().nextInt(100) > 40) {
                direction = (int) (Math.random() * 4);
            }
        }
        //改变方向，根据换image
        tank.setDirection(Tank.Direction.values()[direction]);
        if (tank instanceof FastEnemyTank) {
            DirectionUtil.ChangeImageAccordingDirectionFast((FastEnemyTank) tank);
        } else if (tank instanceof StrongEnemyTank) {
            DirectionUtil.ChangeImageAccordingDirectionStrong((StrongEnemyTank) tank);
        } else {
            DirectionUtil.ChangeImageAccordingDirection(tank);
        }

        // 改变方向后重置锁
        tank.setDirectionLock(null);

        for (int i = 0; i < 30; i++) {
            switch (tank.getDirection()) {
                case UP: //向上
                    if (tank.getY() > 0) {
                        tank.moveUp();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case RIGHT: //向右
                    if (tank.getX() + Attributes.OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
                        tank.moveRight();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case DOWN: //向下
                    if (tank.getY() + Attributes.OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
                        tank.moveDown();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case LEFT: //向左
                    if (tank.getX() > 0) {
                        tank.moveLeft();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }


    // 通过 A* 算法查找路径
    public ArrayList<Dot> findRoadToTank(Tank targetTank) {

        ArrayList<Dot> banDots = new ArrayList<>();
        ArrayList<Dot> reachedDots = new ArrayList<>();
        ArrayList<Dot> routeDots = new ArrayList<>();
        Dot followDot;

        // 根据地图设置不可到达点
        for (Obstacle obstacle : Tank.getGameInstance().getObstacleMap().get(Obstacle.ObstacleKind.RIVER)
        ) {
            banDots.add(new Dot(obstacle.toSimpleDot(), null));
        }
        for (Obstacle obstacle : Tank.getGameInstance().getObstacleMap().get(Obstacle.ObstacleKind.WALL)
        ) {
            banDots.add(new Dot(obstacle.toSimpleDot(), null));
        }
        for (Obstacle obstacle : Tank.getGameInstance().getObstacleMap().get(Obstacle.ObstacleKind.BRICK)
        ) {
            banDots.add(new Dot(obstacle.toSimpleDot(), null));
        }

        // 由于是A*算法是扩展的,所以要将目标设置为起始点
        // 起始点设置
        Dot targetDot = new Dot(this.toSimpleDot(), null);
        Dot beginDot = new Dot(targetTank.toSimpleDot(), null);

        reachedDots.add(beginDot);

        System.out.println("Find way from " + targetDot + " to " + beginDot);


        loop:
        // 开始扩展
        while (true) {
            for (int i = 0; i < reachedDots.size(); i++
            ) {
                Dot dot = reachedDots.get(i);
                for (Dot newDot : dot.expend()
                ) {
                    if (!newDot.isInvolveDots(reachedDots) && !newDot.isInvolveDots(banDots)) {
                        reachedDots.add(newDot);
                    }
                    if (newDot.isSamePosition(targetDot)) {
                        followDot = newDot;
                        break loop;
                    }
                }
            }
        }

        // 向上查找路线
        while (followDot.getParentDot() != null) {
            routeDots.add(followDot);
            followDot = followDot.getParentDot();
        }
        routeDots.add(beginDot);
        return routeDots;
    }
}
