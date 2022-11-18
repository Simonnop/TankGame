package group.su.view;

import group.GetInfo;
import group.li.pojo.Bullet;
import group.su.control.GameInstance;
import group.su.map.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Vector;

import static group.Application.gameRun;
import static group.Application.tempStop;
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
    private final MainFrame mainFrame;

    Button helpButton = new Button("Help");
    Button exitButton = new Button("Exit");
    Button pauseButton = new Button("Pause");

    public GamePanel(GameInstance gameInstance, MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        this.gameInstance = gameInstance;

        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        helpButton.setBackground(Color.WHITE);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyDialogDemo();
                tempStop = true;
            }
        });
        this.add(helpButton);

        exitButton.setForeground(Color.BLACK);
        exitButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameRun = false;
            }
        });
        this.add(exitButton);

        pauseButton.setForeground(Color.BLACK);
        pauseButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        pauseButton.setBackground(Color.WHITE);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempStop == false) {
                    tempStop = true;
                    pauseButton.setLabel("Run");
                } else {
                    tempStop = false;
                    pauseButton.setLabel("Pause");
                }
            }
        });
        this.add(pauseButton);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 绘制游戏状态栏
        g.setColor(Color.GRAY);
        g.fillRect(WINDOW_LENGTH, 0, 200, WINDOW_WIDTH);

        exitButton.setSize(50, 40);
        exitButton.setLocation(610, 10);
        helpButton.setSize(50, 40);
        helpButton.setLocation(670, 10);
        pauseButton.setSize(60, 40);
        pauseButton.setLocation(730, 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("幼圆", Font.BOLD, 20));
        g.drawString("剩余敌人:", 630, 100);

        int[] counts = gameInstance.countKind();
        g.drawImage(enemyTank_up, 630, 130, 40, 40, this);
        g.drawString("x " + counts[0], 690, 160);
        g.drawImage(fastEnemyTank_up, 630, 190, 40, 40, this);
        g.drawString("x " + counts[1], 690, 220);
        g.drawImage(StrongEnemyTank_up, 630, 250, 40, 40, this);
        g.drawString("x " + counts[2], 690, 280);

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

    class MyDialogDemo extends JDialog{

        public MyDialogDemo(){
            this.setVisible(true);
            this.setBounds(800,0,800,500);
            this.setTitle("游戏介绍");

            Container container = this.getContentPane();

            JLabel label=new JLabel(new ImageIcon(
                    Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/intro.png"))));
            label.setSize(800,500);
            container.add(label);

            label.setHorizontalAlignment(SwingConstants.CENTER);

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    tempStop = false;
                    mainFrame.requestFocus();
                    System.out.println("go");
                }
            });
        }
    }
}
