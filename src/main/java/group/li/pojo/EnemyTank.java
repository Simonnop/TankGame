package group.li.pojo;

import group.li.util.CollisionDetection;
import group.li.util.RandomMove;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.enemyTanksList;
import static group.su.util.Factory.bulletOut;


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

    //使用工具类里的碰撞检测  敌方坦克是否碰到其他敌方坦克
    public boolean isTouchEnemyTank(){
        return CollisionDetection.IsTouchEnemyTank(this,enemyTanks);
    }

    @Override
    public void run() {

        while (true){


            //根据坦克方向，创建子弹，并使子弹线程开启




            //开始随机移动
            RandomMove.randomMove(this);
            //被子弹打中了，结束线程
            if(!isLive()){
                break;
            }
            bulletOut(this);
        }
    }
}
