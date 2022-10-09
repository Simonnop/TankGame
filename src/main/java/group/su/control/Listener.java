package group.su.control;

import group.su.map.Obstacle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static group.Attributes.myTank;
import static group.Attributes.obstacleMap;
import static group.su.util.Factory.bulletOut;

public class Listener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        /*
         *
         *  控制坦克移动,与攻击
         * */

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            bulletOut(myTank);
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            obstacleMap.get(Obstacle.ObstacleKind.BRICK).remove(0);
            System.out.println("get");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
