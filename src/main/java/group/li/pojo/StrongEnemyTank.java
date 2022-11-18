package group.li.pojo;

import group.Application;

import java.awt.*;

public class StrongEnemyTank extends EnemyTank {

    public static Image StrongEnemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_up.png"));
    public static Image StrongEnemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_down.png"));
    public static Image StrongEnemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_left.png"));
    public static Image StrongEnemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_right.png"));

    public StrongEnemyTank(int x, int y) {
        super(x, y);
        this.setHp(3);
    }

    @Override
    public void run() {
        /*坦克在2-6s刷新后发子弹*/
        int randomTime = (int) (Math.random() * 4.0 + 2.0);

        while (Application.gameRun) {
            randomMove(this);
            if (!this.isLive()) {
                break;
            }

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            }

            //游戏开始5s后再开始发射子弹
            if (gameInstance.getTime() > 5) {
                --randomTime;
                if (randomTime == 0) {
                    bulletOut(this);
                    randomTime = (int) (Math.random() * 4.0 + 2.0);
                }
            }
        }
    }

}