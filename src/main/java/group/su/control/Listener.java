package group.su.control;

import group.li.util.CollisionDetection;
import group.li.util.DirectionUtil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static group.Attributes.*;
import static group.Attributes.mainFrame;
import static group.su.util.Factory.bulletOut;
import static group.su.view.MainFrame.testPanel;
import static group.su.view.MainFrame.welMenuPanel;

public class Listener implements KeyListener {

    Boolean movingLock_up = new Boolean(false);
    Boolean movingLock_down = new Boolean(false);
    Boolean movingLock_left = new Boolean(false);
    Boolean movingLock_right = new Boolean(false);

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

        //这个v代表默认速度的倍数，便于后期调整速度，可以放到其他类中变为静态
        int v = 5;

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            updateMovingLock(movingLock_down);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            if (!movingLock_down) {
                myTank.moveDown(v);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirection(0);
            updateMovingLock(movingLock_up);
            DirectionUtil.ChangeImageAccordingDirection(myTank);

            if (!movingLock_up) {
                myTank.moveUp(v);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            updateMovingLock(movingLock_right);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            if (!movingLock_right) {
                myTank.moveRight(v);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            updateMovingLock(movingLock_left);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            if (!movingLock_left) {
                myTank.moveLeft(v);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            bulletOut(myTank);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            mainFrame.getContentPane().remove(gamePanel);
            mainFrame.repaint();
//            mainFrame.getContentPane().add(testPanel);
            mainFrame.getContentPane().add(welMenuPanel);
            mainFrame.revalidate();
            System.out.println("get");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void updateMovingLock(Boolean lock) {

        int v = 5;

        if (lock == movingLock_up) {
            movingLock_down = new Boolean(false);
            movingLock_left = new Boolean(false);
            movingLock_right = new Boolean(false);
            if (CollisionDetection.IsTouchForMyTank()) {
                movingLock_up = new Boolean(true);
                myTank.moveDown(v);
            }
            System.out.println("up");
        }
        if (lock == movingLock_down) {
            movingLock_up = new Boolean(false);
            movingLock_left = new Boolean(false);
            movingLock_right = new Boolean(false);
            if (CollisionDetection.IsTouchForMyTank()) {
                movingLock_down = new Boolean(true);
                myTank.moveUp(v);
            }
            System.out.println("down");
        }
        if (lock == movingLock_left) {
            movingLock_down = new Boolean(false);
            movingLock_up = new Boolean(false);
            movingLock_right = new Boolean(false);
            if (CollisionDetection.IsTouchForMyTank()) {
                movingLock_left = new Boolean(true);
                myTank.moveRight(v);
            }
            System.out.println("left");
        }
        if (lock == movingLock_right) {
            movingLock_down = new Boolean(false);
            movingLock_left = new Boolean(false);
            movingLock_up = new Boolean(false);
            if (CollisionDetection.IsTouchForMyTank()) {
                movingLock_right = new Boolean(true);
                myTank.moveLeft(v);
            }
            System.out.println("right");
        }
    }
}
