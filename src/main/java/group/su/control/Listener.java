package group.su.control;

import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.li.util.DirectionUtil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static group.Application.gameRun;
import static group.li.pojo.Tank.bulletOut;

public class Listener implements KeyListener {

    // TODO 优化为单例模式

    GameInstance gameInstance;
    public int temp_time = 0;
    int time;

    MyTank myTank;

    public Listener(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

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
        //int v = 5;


        myTank = gameInstance.getMyTank();
        //用于坦克开火限制，临时记录时间
        time = gameInstance.getTime();

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirection(Tank.Direction.DOWN);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirection(Tank.Direction.UP);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirection(Tank.Direction.RIGHT);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirection(Tank.Direction.LEFT);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //坦克开火限制 ，暂时设置为1s内只能射击一次
            if (temp_time == 0) {
                bulletOut(myTank);
                temp_time = time;
            }
            if (time - temp_time > 1) {
                bulletOut(myTank);
                temp_time = time;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            gameRun = false;
            System.out.println("get");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
