package group.li.pojo;

import group.li.util.RandomMove;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.gameRun;

//我方坦克
public class MyTank extends Tank implements Runnable {

    public static Image myTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_up.png"));
    public static Image myTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_down.png"));
    public static Image myTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_left.png"));
    public static Image myTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_right.png"));

    private int hp;


    //这个v代表默认速度speed的倍数
    public static int v = 5;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //可以发射多颗子弹
    private Vector<Bullet> bullets = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
        setImage(myTank_up);
        System.out.println("tank init");
        setHp(1);
        setSpeed(5);
        setDirection(Direction.UP);
    }


    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }


    @Override
    public void run() {
        while (gameRun) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //被子弹打中了，结束线程
            if (hp <= 0) {
                gameRun = false;
            }
        }
    }
}
