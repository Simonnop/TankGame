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

        // 先画底层的水
        drawObstacle(g, obstacleMap.get(Obstacle.ObstacleKind.RIVER));

        // 绘制坦克
        for (EnemyTank enemyTank : enemyTanksList
        ) {
            drawObject(enemyTank, g);
        }
        drawObject(myTank, g);

        // 绘制子弹
        drawBullets(g,allBulletList);

        // 绘制地图其他障碍物
        drawObstacle(g, obstacleMap.get(Obstacle.ObstacleKind.WALL));
        drawObstacle(g, obstacleMap.get(Obstacle.ObstacleKind.TREE));
        drawObstacle(g, obstacleMap.get(Obstacle.ObstacleKind.BRICK));
    }

    @Override
    public void run() {

        int time = 0;

        while (gameRun) {

            System.out.println("test~~ mainPanel run  " + time + "s");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }

    public void drawObstacle(Graphics g, Vector<Obstacle> obstacleList) {

        for (Obstacle obs : obstacleList
        ) {
            drawObject(obs, g);
        }
    }

    public void drawBullets(Graphics g, Vector<Bullet> bullets) {

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            // 根据子弹的 isLive 值判断是否绘出
            // 如果 isLive 为 false 则移除该对象
            if (b.isLive()) {
                drawBullet(b, g);
            } else {
                bullets.remove(b);
                // 这里的 remove 之后会将遍历的指针前移,所以需要 i--
                i--;
            }
        }
    }
}
