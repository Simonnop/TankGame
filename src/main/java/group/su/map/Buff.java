package group.su.map;

import group.GetInfo;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.control.GameInstance;

import java.awt.*;
import java.util.Random;

import static group.Attributes.OBJECT_SIZE;
import static group.su.map.MapData.*;

public class Buff implements GetInfo {

    BuffKind buffKind;

    public static GameInstance gameInstance;

    static Image imageBuff = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Buff.png"));

    public enum BuffKind {
        ADD_BULLETS {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, ADD_BULLETS);
            }
            @Override
            public void getBuff(Tank tank) {
                if (tank instanceof MyTank) {
                    if (((MyTank) tank).getBulletNum() < 6) {
                        ((MyTank) tank).setBulletNum(6);
                    }
                }
                System.out.println("ADD_BULLETS");
            }
        }, SWIFT_BULLETS {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, SWIFT_BULLETS);
            }
            @Override
            public void getBuff(Tank tank) {
                tank.setBulletSpeed(12);
                System.out.println("SWIFT_BULLETS");
            }
        }, ADD_LIVES {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, ADD_LIVES);
            }
            @Override
            public void getBuff(Tank tank) {
                if (tank.getHp()<4) {
                    tank.setHp(tank.getHp()+1);
                }
                System.out.println("ADD_LIVES");
            }
        };

        protected Buff returnBuff(int x, int y) {
            return null;
        }
        public void getBuff(Tank tank){}
    }

    boolean isLive;

    final int x;
    final int y;

    Image image;

    public Buff(int x, int y, Image image, BuffKind buffKind) {
        this.buffKind = buffKind;
        this.isLive = true;
        this.x = x;
        this.y = y;
        this.image = image;
    }

    private static int[] getRandomPosition() {

        int[] position = new int[2];

        do {
            // x 坐标
            position[0] = new Random().nextInt(dotsLength) * OBJECT_SIZE;
            // y 坐标
            position[1] = new Random().nextInt(dotsWidth) * OBJECT_SIZE;
        } while (checkRandomPosition(position));

        return position;
    }

    private static boolean checkRandomPosition(int[] position) {

        for (Obstacle.ObstacleKind kind : gameInstance.getObstacleMap().keySet()
        ) {
            for (Obstacle o : gameInstance.getObstacleMap().get(kind)) {
                if (o.getX() == position[0] && o.getY() == position[1]) {
                    System.out.println("again");
                    return true;
                }
            }
        }

        return false;
    }

    public static Buff createBuff() {

        BuffKind buffKind1 = BuffKind.values()[new Random().nextInt(3)];
        int[] position = getRandomPosition();
        return buffKind1.returnBuff(position[0], position[1]);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setLive(boolean live) {
        this.isLive = live;
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    public static void setGameInstance(GameInstance gameInstance) {
        Buff.gameInstance = gameInstance;
    }

    public BuffKind getBuffKind() {
        return buffKind;
    }
}
