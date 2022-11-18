package group.su.view;

import group.GetInfo;
import group.li.pojo.Bullet;
import group.su.control.GameInstance;
import group.su.map.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static group.Application.gameRun;
import static group.Attributes.*;
import static group.li.pojo.EnemyTank.enemyTank_up;
import static group.li.pojo.FastEnemyTank.fastEnemyTank_up;
import static group.li.pojo.StrongEnemyTank.StrongEnemyTank_up;
import static group.su.map.MapData.dotsLength;
import static group.su.map.MapData.dotsWidth;

public class GamePanel extends JPanel implements Runnable {

    public static Image lives = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/lives.png"));
    public static Image bullet = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/bullet.png"));
    public static Image floor = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/floor.png"));

    private final GameInstance gameInstance;

    public GamePanel(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 绘制游戏状态栏
        g.setColor(Color.GRAY);
        g.fillRect(WINDOW_LENGTH, 0, 200, WINDOW_WIDTH);

        g.setColor(Color.WHITE);
        g.setFont(new Font("幼圆", Font.BOLD, 20));
        g.drawString("剩余敌人:", 630, 60);

        int[] counts = gameInstance.countKind();
        g.drawImage(enemyTank_up, 630, 100, 40, 40, this);
        g.drawString("x " + counts[0], 690, 130);
        g.drawImage(fastEnemyTank_up, 630, 180, 40, 40, this);
        g.drawString("x " + counts[1], 690, 210);
        g.drawImage(StrongEnemyTank_up, 630, 260, 40, 40, this);
        g.drawString("x " + counts[2], 690, 290);

        g.drawString("剩余生命:", 630, 360);
        for (int i = 0; i < gameInstance.getMyTank().getHp(); i++) {
            g.drawImage(lives, 630 + i * 30, 390, 25, 20, this);
        }

        g.drawString("剩余弹药:", 630, 460);
        for (int i = 0; i < gameInstance.getMyTank().getBulletNum(); i++) {
            g.drawImage(bullet, 630 + i * 15, 490, 10, 25, this);
        }

        g.drawString("得分: " + gameInstance.getDestroySet().size(), 630, 560);


        for (int i = 0; i < dotsLength / 2 + 1; i++) {
            for (int j = 0; j < dotsWidth / 2 + 1; j++) {
                g.drawImage(floor, i * 80 - 40, j * 80 - 40, 80, 80, this);
            }
        }
        // 绘制游戏内容
        // 先画底层的水
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.RIVER));
        // 画 buff
        drawObjects(g, gameInstance.getBuffList());
        // 绘制坦克
        drawObjects(g, gameInstance.getEnemyTanksList());
        drawObjects(g, gameInstance.getMyTank());
        // 绘制子弹
        drawObjects(g, gameInstance.getAllBulletList());
        // 绘制地图其他障碍物
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.WALL));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.TREE));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BRICK));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BASE));
    }

    private <T extends GetInfo> void drawObjects(Graphics g, Vector<T> list) {
        int size;
        if (list.isEmpty()) {
            return;
        }
        if (list.get(0) instanceof Bullet) {
            size = BULLET_SIZE;
        } else {
            size = OBJECT_SIZE;
        }
        synchronized (list) {
            for (T t : list) {
                g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
            }
        }
    }

    private <T extends GetInfo> void drawObjects(Graphics g, T t) {
        int size = OBJECT_SIZE;
        g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void run() {
        while (gameRun) {
            try {
                // 线程休息,控制刷新率
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }

}
