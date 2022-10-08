package group.su.control;

import group.su.map.Obstacle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static group.su.map.MapData.obstacleMap;

public class Listener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        /*
         * TODO
         *  控制坦克移动
         * */

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            obstacleMap.get(Obstacle.ObstacleKind.BRICK).remove(0);
            System.out.println("get");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
