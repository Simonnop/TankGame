package group.li.pojo;

import java.util.Vector;

//我方坦克
public class MyTank extends Tank{

    //可以发射多颗子弹
    private Vector<Bullet> bullets = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }
}
