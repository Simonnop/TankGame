package group.su.control;

import group.li.util.DirectionUtil;
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
         *
         * 按下相应的键之后，变换方向并移动
         * */

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirection(0);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
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
