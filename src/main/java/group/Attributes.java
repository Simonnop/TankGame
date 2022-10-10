package group;

import group.li.pojo.Bullet;
import group.li.pojo.EnemyTank;
import group.li.pojo.MyTank;
import group.su.map.Obstacle;
import group.su.view.MainPanel;

import java.util.Map;
import java.util.Vector;

public class Attributes {

    // 描述文件: file/attributes_description.md

    public static boolean gameRun = true;

    public static MyTank myTank;

    public static Vector<EnemyTank> enemyTanksList;

    public static Map<Obstacle.ObstacleKind, Vector<Obstacle>> obstacleMap;

    public static MainPanel mainPanel;

    public static Thread mainPanelThread;

    public static Application application;

    public static Vector<Bullet> allBulletList;



    public static final int REFRESH_TIME = 25;

    public static final int OBJECT_SIZE = 40;

    public static final int BULLET_SIZE = 6;

    public static final int WINDOW_LENGTH = 1080;

    public static final int WINDOW_WIDTH = 720;


}
