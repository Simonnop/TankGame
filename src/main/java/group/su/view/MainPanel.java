package group.su.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.su.map.Obstacle;
import group.su.util.DrawFactory;

import static group.Constant.*;
import static group.su.control.GameControl.enemyTank;
import static group.su.control.GameControl.myTank;
import static group.su.map.MapData.obstacleMap;
import static group.su.util.DrawFactory.drawObject;

public class MainPanel extends JPanel implements Runnable {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        drawObject(enemyTank, g);
        drawObject(myTank, g);
        drawMap(g, obstacleMap);
    }

    @Override
    public void run() {

        while (gameRun) {

            System.out.println("test~~");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

        updateMap(obstacleMap);
    }

    private void updateMap(Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap) {

    }
}
