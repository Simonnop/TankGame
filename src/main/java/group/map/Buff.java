package group.map;

import group.GetInfo;
import group.database.control.GameInstance;
import group.database.control.Listener;
import group.model.pojo.MyTank;
import group.model.pojo.Tank;

import java.awt.*;
import java.util.Random;

import static group.Attributes.OBJECT_SIZE;

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
                    if (((MyTank) tank).getBulletNum() < ((MyTank) tank).getBulletNumLimit()) {
                        ((MyTank) tank).setBulletNum(((MyTank) tank).getBulletNumLimit());
                        gameInstance.addInfoMap("Buff: 获得弹药补给");
                    } else {
                        ADD_BULLETS_LIMIT.getBuff(tank);
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
                tank.setBulletSpeed(tank.getBulletSpeed() + 1);
                if (tank instanceof MyTank) {
                    gameInstance.addInfoMap("Buff: 炮弹加速");
                }
                System.out.println("SWIFT_BULLETS");
            }
        }, ADD_LIVES {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, ADD_LIVES);
            }

            @Override
            public void getBuff(Tank tank) {
                if (tank instanceof MyTank) {
                    if (tank.getHp() < ((MyTank) tank).getHpLimit()) {
                        tank.setHp(tank.getHp() + 1);
                        gameInstance.addInfoMap("Buff: 回复血量");
                    } else {
                        ADD_LIVES_LIMIT.getBuff(tank);
                    }
                } else {
                    tank.setHp(tank.getHp() + 1);
                }
                System.out.println("ADD_LIVES");
            }
        }, ADD_LIVES_LIMIT {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, ADD_LIVES_LIMIT);
            }

            @Override
            public void getBuff(Tank tank) {
                if (tank instanceof MyTank) {
                    if (((MyTank) tank).getHpLimit() > tank.getHp()) {
                        ADD_LIVES.getBuff(tank);
                    } else if (((MyTank) tank).getHpLimit() < 10) {
                        ((MyTank) tank).setHpLimit(((MyTank) tank).getHpLimit() + 1);
                        gameInstance.addInfoMap("Buff: 增加血量上限");
                    }
                }
                System.out.println("ADD_LIVES_LIMIT");
            }
        }, ADD_BULLETS_LIMIT {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, ADD_BULLETS_LIMIT);
            }

            @Override
            public void getBuff(Tank tank) {
                if (tank instanceof MyTank && ((MyTank) tank).getBulletNumLimit() < 20) {
                    ((MyTank) tank).setBulletNumLimit(((MyTank) tank).getBulletNumLimit() + 2);
                    gameInstance.addInfoMap("Buff: 增加备弹上限");
                }
                System.out.println("ADD_BULLETS_LIMIT");
            }
        }, FASTER_FIRE_GAP {
            @Override
            protected Buff returnBuff(int x, int y) {
                return new Buff(x, y, imageBuff, FASTER_FIRE_GAP);
            }

            @Override
            public void getBuff(Tank tank) {
                if (tank instanceof MyTank && Listener.timeSpan > 0.3) {
                    Listener.timeSpan -= 0.05;
                    gameInstance.addInfoMap("Buff: 减小弹药装填时间");
                }
                System.out.println("FASTER_FIRE_GAP");
            }
        };

        // 根据位置创建 buff 并返回
        protected Buff returnBuff(int x, int y) {
            return null;
        }

        // 坦克获得 buff 内容
        public void getBuff(Tank tank) {
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

    private static int[] getRandomPosition() {
        // 获得随机位置

        int[] position = new int[2];

        do {
            // x 坐标
            position[0] = new Random().nextInt(MapData.dotsLength) * OBJECT_SIZE;
            // y 坐标
            position[1] = new Random().nextInt(MapData.dotsWidth) * OBJECT_SIZE;

        } while (checkRandomPosition(position));

        return position;
    }

    private static boolean checkRandomPosition(int[] position) {

        // 检查位置是否与现有障碍物重叠

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

        // 集成化随机生成 buff

        BuffKind buffKind1 = BuffKind.values()[new Random().nextInt(BuffKind.values().length)];
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
