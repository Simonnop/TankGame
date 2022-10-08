package group.li.pojo;

import group.li.util.RandomMove;

import java.awt.*;
import java.util.Vector;

//我方坦克
public class MyTank extends Tank implements Runnable{

    //可以发射多颗子弹
    private Vector<Bullet> bullets = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
        setImage(Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_up.png")));
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
            System.out.println("run");
            //开始随机移动
            //被子弹打中了，结束线程
            if(!isLive()){
                break;
            }
        }
    }
}
