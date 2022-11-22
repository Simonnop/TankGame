package group.model.pojo;

import group.Application;
import group.Attributes;
import group.GetInfo;
import group.model.util.DirectionUtil;
import group.control.GameInstance;
import group.map.Dot;
import group.map.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static group.Application.tempStop;
import static group.Attributes.OBJECT_SIZE;
import static group.model.pojo.Tank.Direction.*;


//每个敌方坦克也是一个线程
public class EnemyTank extends Tank implements Runnable, GetInfo {

    public static Image enemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_up.png"));
    public static Image enemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_down.png"));
    public static Image enemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_left.png"));
    public static Image enemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_right.png"));

    //控制敌方坦克发子弹的一个间隔，timeSpanOfEnermyBullet+0~8 s
    public static double timeSpanOfEnemyBullet = 10;
    //调用算法间隔
    public static int timeSpanOfFindRoad = 8;


    int addScore = 5;
    private int targetX;
    private int targetY;

    int changeDirectionTime = 0;

    public EnemyTank(int x, int y) {
        super(x, y);
        setImage(enemyTank_down);
        setDirection(Direction.DOWN);
        setAttributes();
        timeSpanOfEnemyBullet -= (int) (gameInstance.getTime() / 60);
        if (timeSpanOfEnemyBullet < 0) {
            timeSpanOfEnemyBullet = 0;
        }
    }


    public void setAttributes() {
        switch (GameInstance.difficulty) {
            case "简单":
                setHp(1);
                setSpeed(1);
                timeSpanOfEnemyBullet = 10;
                break;
            case "普通":
                setHp(1);
                setSpeed(2);
                timeSpanOfEnemyBullet = 8;
                break;
            case "困难":
                setHp(1);
                setSpeed(2);
                timeSpanOfEnemyBullet = 6;
                break;
            case "地狱":
                setHp(2);
                setSpeed(3);
                timeSpanOfEnemyBullet = 3;
                break;
        }
    }

    public void run() {
        int randomTime = (int) (Math.random() * 2.0 + timeSpanOfEnemyBullet);
        int randomTimeFindRoad = timeSpanOfFindRoad;
        int followDotsIndex = 0;
        ArrayList<Dot> roadsDots = findRoadToTank(getGameInstance().getMyTank());
        while (Application.gameRun) {

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            }

            if (!tempStop) {
                //每timeSpanOfFindRoads调用一次算法
                --randomTimeFindRoad;
                if (randomTimeFindRoad == 0 && this.isLive()) {
                    roadsDots = findRoadToTank(getGameInstance().getMyTank());
                    randomTimeFindRoad = timeSpanOfFindRoad;
                    followDotsIndex = 0;
                }

                //randomMove(this);
                followDotsIndex = moveAccordingDots(roadsDots, followDotsIndex);
                //游戏开始5s后再开始发射子弹
                if (gameInstance.getTime() > 5) {
                    --randomTime;
                    if (randomTime == 0 && this.isLive()) {
                        if (!friendlyInDirection()) {
                            bulletOut(this);
                        }
                        //System.out.println("bullet");
                        randomTime = (int) (Math.random() * 2.0 + timeSpanOfEnemyBullet);
                    }
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

        for (int i = 0; i < 20; i++) {
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
                    if (tank.getX() + OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
                        tank.moveRight();
                    }
                    try {
                        Thread.sleep(Attributes.REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case DOWN: //向下
                    if (tank.getY() + OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
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
        for (Obstacle obstacle : Tank.getGameInstance().getObstacleMap().get(Obstacle.ObstacleKind.BASE)
        ) {
            banDots.add(new Dot(obstacle.toSimpleDot(), null));
        }

        // 由于是A*算法是扩展的,所以要将目标设置为起始点
        // 起始点设置
        Dot targetDot = new Dot(this.toSimpleDot(), null);
        Dot beginDot = new Dot(targetTank.toSimpleDot(), null);

        reachedDots.add(beginDot);

        //System.out.println("Find way from " + targetDot + " to " + beginDot);


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
        routeDots.remove(0);
        return routeDots;
    }

    public int moveAccordingDots(ArrayList<Dot> Dots, int followDotsIndex) {
        Direction direction;
        for (int i = followDotsIndex; i < Dots.size(); i++) {

            targetX = Dots.get(i).getSimpleX() * OBJECT_SIZE;
            targetY = Dots.get(i).getSimpleY() * OBJECT_SIZE;
            int currentX = getX();
            int currentY = getY();

            if (targetX - currentX == 0 && targetY - currentY == 0) {
                followDotsIndex++;
                changeDirectionTime = 0;
                return followDotsIndex;
            }

            if (targetX - currentX > 0) {
                direction = RIGHT;
                changeDirectionTime++;
            } else if (targetX - currentX < 0) {
                direction = LEFT;
                changeDirectionTime++;
            } else if (targetY - currentY > 0) {
                direction = DOWN;
                changeDirectionTime++;
            } else {
                direction = UP;
                changeDirectionTime++;
            }

            // 如果始终到不了目标点,先移动到整点
            if (changeDirectionTime > 6) {
                smoothMoveToSimpleDot();
                changeDirectionTime = 0;
            }
           /* if(targetX ==currentX && targetY==currentY){
                followDotsIndex++;
                System.out.println("followDotsIndex"+followDotsIndex);
                return followDotsIndex;
            }

            if(targetX - currentX >0){
                direction=  RIGHT;
            }else if (targetX - currentX <0){
                direction=  LEFT;
            } else if (targetY -currentY >0) {
                System.out.println("down");
                direction= DOWN;
            }else {
                direction= UP;
            }*/

            this.setDirection(direction);
            //先改变方向，根据换image
            // setDirection 集成换image

            this.setDirectionLock(null);

            for (int j = 0; j < 20; j++) {
                // 如果已经到了整点,break
                if (arriveTargetDot()) {
                    break;
                }
                try {
                    Thread.sleep(Attributes.REFRESH_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (direction) {
                    case UP: //向上
                        if (this.getY() > 0) {
                            this.moveUp();
                        }
                        break;
                    case RIGHT: //向右
                        if (this.getX() + OBJECT_SIZE < Attributes.WINDOW_LENGTH) {
                            this.moveRight();
                        }
                        break;
                    case DOWN: //向下
                        if (this.getY() + OBJECT_SIZE < Attributes.WINDOW_WIDTH) {
                            this.moveDown();
                        }
                        break;
                    case LEFT: //向左
                        if (this.getX() > 0) {
                            this.moveLeft();
                        }
                        break;
                }

            }

            return followDotsIndex;
        }
        return followDotsIndex;
    }

    private boolean friendlyInDirection() {
        // 给敌坦克看旁边有没有友军,防止误伤
        // 我方坦克贴脸,直接打
        Tank myTank = gameInstance.getMyTank();
        if (Math.pow(myTank.toSimpleDot()[0] - this.toSimpleDot()[0], 2) <= 1 &&
            Math.pow(myTank.toSimpleDot()[1] - this.toSimpleDot()[1], 2) <= 1) {
            return false;
        }
        for (GetInfo e : gameInstance.getEnemyTanksList()
        ) {
            if (!this.equals(e)) {
                if (this.getDirection().equals(UP) && e.getY() < this.getY() &&
                    Math.pow(e.toSimpleDot()[0] - this.toSimpleDot()[0], 2) <= 4) {
                    return true;
                }
                if (this.getDirection().equals(DOWN) && e.getY() > this.getY() &&
                    Math.pow(e.toSimpleDot()[0] - this.toSimpleDot()[0], 2) <= 4) {
                    return true;
                }
                if (this.getDirection().equals(RIGHT) && e.getX() > this.getX() &&
                    Math.pow(e.toSimpleDot()[1] - this.toSimpleDot()[1], 2) <= 4) {
                    return true;
                }
                if (this.getDirection().equals(LEFT) && e.getX() < this.getX() &&
                    Math.pow(e.toSimpleDot()[1] - this.toSimpleDot()[1], 2) <= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean arriveTargetDot() {
        if (getX() == targetX && getY() == targetY) {
            return true;
        }
        return false;
    }

    private void smoothMoveToSimpleDot() {
        while (true) {
            setDirectionLock(null);
            if (!(getX() == toSimpleDot()[0] * OBJECT_SIZE)) {
                setDirection(LEFT);
                moveLeft();
            } else if (!(getY() == toSimpleDot()[1] * OBJECT_SIZE)) {
                setDirection(UP);
                moveUp();
            } else break;
            try {
                Thread.sleep(Attributes.REFRESH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
        if (this instanceof FastEnemyTank) {
            DirectionUtil.ChangeImageAccordingDirectionFast((FastEnemyTank) this);
        } else if (this instanceof StrongEnemyTank) {
            DirectionUtil.ChangeImageAccordingDirectionStrong((StrongEnemyTank) this);
        } else {
            DirectionUtil.ChangeImageAccordingDirection(this);
        }
    }

    public int getAddScore() {
        return addScore;
    }

    public void setAddScore(int addScore) {
        this.addScore = addScore;
    }
}

