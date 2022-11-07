package group.su.map;

import group.GetInfo;
import group.su.control.GameInstance;

import java.awt.*;
import java.util.Random;

import static group.Attributes.OBJECT_SIZE;
import static group.su.map.MapData.*;

public class Buff implements GetInfo {

    BuffKind buffKind;

    public static GameInstance gameInstance;

    public enum BuffKind {
        MORE_BULLETS {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, MORE_BULLETS);
            }
        }, SWIFT_BULLETS {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, SWIFT_BULLETS);
            }
        }, ADD_LIVES {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, ADD_LIVES);
            }
        };

        protected Buff returnBuff(int x, int y) {
            return null;
        }
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

    private static int[] getRandomPosition() throws InterruptedException {

        int[] position = new int[2];

        do {
            // x 坐标
            position[0] = new Random().nextInt(dotsLength) * OBJECT_SIZE;
            // y 坐标
            position[1] = new Random().nextInt(dotsWidth) * OBJECT_SIZE;
        } while (checkRandomPosition(position));

        return position;
    }

    private static boolean checkRandomPosition(int[] position) throws InterruptedException {

        try {
            for (Obstacle.ObstacleKind kind : gameInstance.getObstacleMap().keySet()
            ) {
                for (Obstacle o : gameInstance.getObstacleMap().get(kind)) {
                    if (o.getX() == position[0] && o.getY() == position[1]) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Thread.sleep(1000);
            // 出现错误,延时再次调用, 不知道行不行 ???
            return checkRandomPosition(position);
        }

        return false;
    }

    public static Buff createBuff() throws InterruptedException {

        BuffKind buffKind1 = BuffKind.values()[new Random().nextInt(3)];
        return buffKind1.returnBuff(getRandomPosition()[0], getRandomPosition()[1]);
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
}
