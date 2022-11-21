package group.li.pojo;

import group.su.control.Listener;
import group.su.view.SelectPanel;

import java.awt.*;
import java.util.Vector;

import static group.Application.gameRun;

//我方坦克
public class MyTank extends Tank implements Runnable {

    public static Image myTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_up.png"));
    public static Image myTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_down.png"));
    public static Image myTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_left.png"));
    public static Image myTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/MyTank_right.png"));

    private int hp;

    private int hpLimit;

    //可以子弹数限制
    private int bulletNum;

    private int bulletNumLimit;

    public MyTank(int x, int y) {
        super(x, y);
        setImage(myTank_up);
        setHp(4);
        setHpLimit(4);
        setSpeed(3);
        setBulletNum(6);
        setBulletNumLimit(6);
        setAttributes();
        setDirection(Direction.UP);
    }

    public void setAttributes(){
        switch (SelectPanel.difficulty){
            case "简单":  setSpeed(3);  setHp(2);Listener.timeSpan=0.5; setBulletNum(6);break;
            case "普通":  setSpeed(3);   setHp(2);Listener.timeSpan=0.5;setBulletNum(6);break;
            case "困难":  setSpeed(3);    setHp(4);Listener.timeSpan=0.5; setBulletNum(8);break;
            case "地狱":  setSpeed(3.5); setHp(5); Listener.timeSpan=0.25;setBulletNum(10); break;
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBulletNum() {
        return bulletNum;
    }

    public void setBulletNum(int bulletNum) {
        this.bulletNum = bulletNum;
    }

    public int getHpLimit() {
        return hpLimit;
    }

    public void setHpLimit(int hpLimit) {
        this.hpLimit = hpLimit;
    }

    public int getBulletNumLimit() {
        return bulletNumLimit;
    }

    public void setBulletNumLimit(int bulletNumLimit) {
        this.bulletNumLimit = bulletNumLimit;
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
