package group.li.pojo;

import group.Attributes;
import group.li.util.RandomMove;
import group.su.util.Factory;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.enemyTanksList;


//每个敌方坦克也是一个线程
public class EnemyTank extends Tank implements Runnable{

    public static Image enemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_up.png"));
    public static Image enemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_down.png"));
    public static Image enemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_left.png"));
    public static Image enemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/EnemyTank_right.png"));

    //让每个敌方坦克都拥有敌方坦克集  可以随时遍历检测是否碰撞
    //可以在初始化坦克的时候 给每个敌方坦克加上敌方坦克集
    private Vector <EnemyTank> enemyTanks =new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
        setImage(enemyTank_down);
        enemyTanksList.add(this);
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }


    public void run() {
        /*坦克在2-6s刷新后发子弹*/
        int randomTime = (int)(Math.random() * 4.0 + 2.0);

        while(Attributes.gameRun) {
            RandomMove.randomMove(this);
            if (!this.isLive()) {
                break;
            }

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            }

            //游戏开始5s后再开始发射子弹
            if (Attributes.time > 5) {
                --randomTime;
                if (randomTime == 0) {
                    Factory.bulletOut(this);
                    randomTime = (int)(Math.random() * 4.0 + 2.0);
                }
            }
        }

    }
}
