package group.su.view;

import group.GetInfo;
import group.li.pojo.Bullet;
import group.su.control.GameInstance;
import group.su.map.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static group.Application.gameRun;
import static group.Attributes.*;

public class GamePanel extends JPanel implements Runnable {

    private final GameInstance gameInstance;

    public GamePanel(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 先画底层的水
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.RIVER));

        // 画 buff
        drawObjects(g, gameInstance.getBuffList());

        // 绘制坦克
        drawObjects(g, gameInstance.getEnemyTanksList());
        drawObjects(g, gameInstance.getMyTank());

        // 绘制子弹
        drawObjects(g, gameInstance.getAllBulletList());

        // 绘制地图其他障碍物
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.WALL));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.TREE));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BRICK));
    }

    private <T extends GetInfo> void drawObjects(Graphics g, Vector<T> list) {
        int size;
        if (list.isEmpty()) {
            return;
        }
        if (list.get(0) instanceof Bullet) {
            size = BULLET_SIZE;
        } else {
            size = OBJECT_SIZE;
        }
        for (T t : list) {
            g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
        }
    }

    private <T extends GetInfo> void drawObjects(Graphics g, T t) {
        int size = OBJECT_SIZE;
        g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void run() {
        while (gameRun) {
            try {
                // 线程休息,控制刷新率
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}
