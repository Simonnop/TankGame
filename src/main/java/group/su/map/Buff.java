package group.su.map;

import java.awt.*;
import java.util.Random;

import static group.Attributes.obstacleMap;
import static group.su.map.MapData.*;
import static group.su.map.MapData.imageBrick;

public class Buff {

    BuffKind buffKind;

    public enum BuffKind {
        MORE_BULLETS {
            @Override
            public Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, MORE_BULLETS);
            }
        }, SWIFT_BULLETS {
            @Override
            public Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, SWIFT_BULLETS);
            }
        }, ADD_LIVES {
            @Override
            public Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageRiver, ADD_LIVES);
            }
        };

        public Buff returnBuff(int x, int y) {
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

    private int[] getRandomPosition() throws InterruptedException {

        int[] position = new int[2];

        do {
            // x 坐标
            position[0] = new Random().nextInt(dotsLength);
            // y 坐标
            position[1] = new Random().nextInt(dotsWidth);
        } while (checkRandomPosition(position));

        return position;
    }

    private boolean checkRandomPosition(int[] position) throws InterruptedException {

        try {
            for (Obstacle.ObstacleKind kind : obstacleMap.keySet()
            ) {
                for (Obstacle o : obstacleMap.get(kind)) {
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
}
