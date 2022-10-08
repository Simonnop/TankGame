package group.su.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;


import group.su.map.Obstacle;
import group.su.util.LifeControl;

import static group.Constant.gameRun;
import static group.su.map.MapData.Map_1;

public class MainPanel extends JPanel implements Runnable {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 1080, 720);

        drawMap(g, Map_1);
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

    public void drawMap(Graphics g, List<int[][]> map) {
        for (int i = 0; i < map.size(); i++
        ) {
            for (Obstacle.ObstacleKind o:Obstacle.ObstacleKind.values()
                 ) {
                int[][] array = map.get(o.ordinal());
                for (int[] ints:array
                     ) {
                    LifeControl.initialize(o,ints[0],ints[1],g);
                }
            }
        }
    }
}
