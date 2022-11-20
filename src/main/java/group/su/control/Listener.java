package group.su.control;

import group.li.pojo.MyTank;
import group.li.pojo.Tank;
import group.li.util.DirectionUtil;
import group.su.map.Dot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static group.Application.gameRun;
import static group.li.pojo.Tank.bulletOut;

public class Listener implements KeyListener{


    private static Listener listener = new Listener();

    public static Listener getListener(GameInstance gameInstance) {
        listener.gameInstance = gameInstance;
        return listener;
    }

    GameInstance gameInstance;
    public int temp_time = 0;
    int time;

    static MyTank myTank;

    static ArrayList<Character> keys = new ArrayList<>();

    public Listener() {
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
            if (!keys.contains('D')) {
                keys.add(0,'D');
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!keys.contains('U')) {
                keys.add(0,'U');
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!keys.contains('R')) {
                keys.add(0,'R');
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!keys.contains('L')) {
                keys.add(0,'L');
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            //坦克开火限制 ，暂时设置为1s内只能射击一次
            if (temp_time == 0 && myTank.getBulletNum() > 0) {
                bulletOut(myTank);
                myTank.setBulletNum(myTank.getBulletNum() - 1);
                temp_time = time;
            }
            if (time - temp_time > 1 && myTank.getBulletNum() > 0) {
                bulletOut(myTank);
                myTank.setBulletNum(myTank.getBulletNum() - 1);
                temp_time = time;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 测试其他功能用
            gameRun = false;
            System.out.println("get");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setHp(100);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys.remove((Object)'D');
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys.remove((Object)'U');
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys.remove((Object)'R');
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys.remove((Object)'L');
        }
    }

    public static void moveByKeys() {

        if (keys.isEmpty()) {
            return;
        }
        if (keys.get(0) == 'D') {
            myTank.setDirection(Tank.Direction.DOWN);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveDown();
        }
        if (keys.get(0) == 'U') {
            myTank.setDirection(Tank.Direction.UP);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveUp();
        }
        if (keys.get(0) == 'R') {
            myTank.setDirection(Tank.Direction.RIGHT);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveRight();
        }
        if (keys.get(0) == 'L') {
            myTank.setDirection(Tank.Direction.LEFT);
            DirectionUtil.ChangeImageAccordingDirection(myTank);
            myTank.moveLeft();
        }
    }
}
