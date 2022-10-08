package group.li.pojo;

import group.li.util.CollisionDetection;
import group.li.util.RandomMove;

import java.util.Vector;

//每个敌方坦克也是一个线程
public class EnemyTank extends Tank implements Runnable{

    //每个坦克都有子弹
    //使用Vector的原因是 保证线程安全
    private Vector<Bullet> Bullets =new Vector<>();

    //让每个敌方坦克都拥有敌方坦克集  可以随时遍历检测是否碰撞
    //可以在初始化坦克的时候 给每个敌方坦克加上敌方坦克集
    private Vector <EnemyTank> enemyTanks =new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public Vector<Bullet> getBullets() {
        return Bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        Bullets = bullets;
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
            RandomMove.randomMove(this);
            //被子弹打中了，结束线程
            if(!isLive()){
                break;
            }
        }
    }
}
