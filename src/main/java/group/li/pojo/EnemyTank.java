package group.li.pojo;

import group.li.util.CollisionDetection;
import group.li.util.RandomMove;
import group.su.util.Detection;

import java.awt.*;
import java.util.Vector;

import static group.Attributes.*;
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


    @Override
    public void run() {
        //产生一个随机时间，敌方坦克每一个随机时间发射一颗子弹
        int randomTime = (int) (Math.random() * 4+2);
        while (gameRun){
            //开始随机移动
            RandomMove.randomMove(this);
            //被子弹打中了，结束线程
            if(!isLive()){
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            randomTime--;
            //产生一个随机时间，敌方坦克每一个随机时间发射一颗子弹
            if(randomTime==0){
                bulletOut(this);
               randomTime= (int) (Math.random() * 4+2); //2-6的随机数
            }

        }
    }
}
