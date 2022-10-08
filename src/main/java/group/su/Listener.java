package group.su;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {

        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            gameControl.gameRun = false;
            System.out.println("get");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
