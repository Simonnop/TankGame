package group.database.control;

import group.model.pojo.MyTank;
import group.model.pojo.Tank;
import group.model.util.DirectionUtil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static group.Application.tempStop;
import static group.model.pojo.Tank.bulletOut;

public class Listener implements KeyListener{

    // 监听器

    /*
     * 使用饿汉单例模式
     * - 防止未及时初始化而添加失败
     * - 防止重复添加
     * */
    private static Listener listener = new Listener();

    public static Listener getListener(GameInstance gameInstance) {
        // 设置对应的游戏实例
        listener.gameInstance = gameInstance;
        // 清空现有的输入字符集
        if (!keys.isEmpty()) {
            keys.clear();
        }
        temp_time = 0;
        return listener;
    }

    GameInstance gameInstance;
    // 储存开火间隔
    public static double temp_time = 0;
    double time;

    //子弹发射的时间间隔
    public static double timeSpan=0.5;

    static MyTank myTank;

    // 储存键盘输入的字符: 用于改善控制手感
    static ArrayList<Character> keys = new ArrayList<>();

    public Listener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // 游戏暂停,则监听器失效
        if (tempStop) {
            return;
        }
        /*
         *
         *  控制坦克移动,与攻击
         *
         * 按下相应的键之后，变换方向并移动
         * */
        myTank = gameInstance.getMyTank();
        //用于坦克开火限制，临时记录时间
        time = gameInstance.getTime();

        // 监听上下左右键,并添加键入至列表首个
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
            System.out.println("fire");
            //坦克开火限制 ，暂时设置为timeSpans内只能射击一次
            if (temp_time == 0 && myTank.getBulletNum() > 0) {
                bulletOut(myTank);
                myTank.setBulletNum(myTank.getBulletNum() - 1);
                temp_time = time;
            }
            if (time - temp_time > timeSpan && myTank.getBulletNum() > 0) {
                bulletOut(myTank);
                myTank.setBulletNum(myTank.getBulletNum() - 1);
                temp_time = time;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 按键松开则移除
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

        // 按下相应的键之后，变换方向并移动
        // 读取 列表中的第一个字符 来进行移动
        // 会灵敏的读取所有的键入,避免原有监听器键入的覆盖

        synchronized (keys) {
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
}
