package group.su.view;

import group.GetInfo;
import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.su.map.Buff;
import group.su.map.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static group.Attributes.*;

public class GamePanel extends JPanel implements Runnable {

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 先画底层的水
        drawObjects(g, obstacleMap.get(Obstacle.ObstacleKind.RIVER));

        // 画 buff
        drawObjects(g, buffList);

        // 绘制坦克
        drawObjects(g, enemyTanksList);
        drawObjects(g, myTank);

        // 绘制子弹
        drawObjects(g, allBulletList);

        // 绘制地图其他障碍物
        drawObjects(g, obstacleMap.get(Obstacle.ObstacleKind.WALL));
        drawObjects(g, obstacleMap.get(Obstacle.ObstacleKind.TREE));
        drawObjects(g, obstacleMap.get(Obstacle.ObstacleKind.BRICK));
    }

    @Override
    public void run() {

        time = 0;
        while (gameRun) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test~~  " + time + "s");
            time++;
        }
    }

    public <T extends GetInfo> int drawObjects(Graphics g, Vector<T> list) {
        int size;
        if (list.isEmpty()) {
            return 0;
        } else if (list.get(0) instanceof Bullet) {
            size = BULLET_SIZE;
        } else {
            size = OBJECT_SIZE;
        }
        for (T t : list) {
            g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, gamePanel);
        }
        return 0;
    }

    public <T extends GetInfo> void drawObjects(Graphics g, T t) {
        int size = OBJECT_SIZE;
        g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, gamePanel);
    }
}
