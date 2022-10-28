package group;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.su.map.Buff;
import group.su.map.Obstacle;
import group.su.view.GamePanel;
import group.su.view.MainFrame;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Attributes {

    // 描述文件: file/attributes_description.md

    public static boolean gameRun = false;

    public static boolean restart = false;

    public static MyTank myTank;

    public static Vector<EnemyTank> enemyTanksList;

    public static Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap;

    public static GamePanel gamePanel;

    public static Thread mainPanelThread;

    public static MainFrame mainFrame;

    public static Vector<Bullet> allBulletList;

    public static Vector<Buff> buffList;

    public static Set<EnemyTank> destroySet;

    public static String playerName;

    public static int time = 0;



    public static final int REFRESH_TIME = 25;

    public static final int OBJECT_SIZE = 40;

    public static final int BULLET_SIZE = 6;

    public static final int WINDOW_LENGTH = 600;

    public static final int WINDOW_WIDTH = 600;


}
