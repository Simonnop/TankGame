package group.li.pojo;

import group.Attributes;
import group.li.util.RandomMove;
import group.su.util.Factory;

import java.awt.*;

public class FastEnemyTank extends EnemyTank {

    public static Image fastEnemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_up.png"));
    public static Image fastEnemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_down.png"));
    public static Image fastEnemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_left.png"));
    public static Image fastEnemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_right.png"));


    public FastEnemyTank(int x, int y) {
        super(x, y);
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
