package group.li.pojo;

import group.li.util.RandomMove;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.gameRun;

//我方坦克
public class MyTank extends Tank implements Runnable{

    static Image myTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_up.png"));
    static Image myTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_down.png"));
    static Image myTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_left.png"));
    static Image myTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_right.png"));

    //可以发射多颗子弹
    private Vector<Bullet> bullets = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
        setImage(myTank_up);
        System.out.println("tank init");
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }


    @Override
    public void run() {
        while (true){
            //开始随机移动
            //被子弹打中了，结束线程
            if(!isLive()){
                gameRun = false;
                break;
            }
        }
    }
}
