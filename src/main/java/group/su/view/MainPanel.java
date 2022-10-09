package group.su.view;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.Tank;
import group.su.map.Obstacle;

import static group.Attributes.*;
import static group.su.util.Factory.drawBullet;
import static group.su.util.Factory.drawObject;

public class MainPanel extends JPanel implements Runnable {

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 绘制坦克与其子弹
        for (EnemyTank enemyTank : enemyTanksList
        ) {
            drawTankAndBullets(g, enemyTank);
        }
        drawTankAndBullets(g, myTank);
        // 绘制地图
        drawMap(g, obstacleMap);
    }

    @Override
    public void run() {

        int time = 0;

        while (gameRun) {

            System.out.println("test~~ " + time + "s");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }

    public void drawMap(Graphics g, Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap) {

        for (Obstacle.ObstacleKind obstacleKind : obstacleMap.keySet()
        ) {
            for (Obstacle obs : obstacleMap.get(obstacleKind)
            ) {
                drawObject(obs, g);
            }
        }
    }

    public void drawTankAndBullets(Graphics g, Tank tank) {
        drawObject(tank, g);

        for (Bullet b : tank.getBullets()
        ) {
            if (b.isLive()) {
                drawBullet(b, g);
            }else {
                tank.getBullets().remove(b);
                System.out.println("recycle");
            }
        }
    }
}
