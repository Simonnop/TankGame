package group.database.control;

import group.model.pojo.EnemyTank;
import group.model.pojo.FastEnemyTank;
import group.model.pojo.MyTank;
import group.model.pojo.StrongEnemyTank;
import group.map.Buff;

import java.util.Random;

public class Factory {

    /*
     * 为 GameInstance动态创建游戏物体
     * 为 单例模式
     * */

    private GameInstance gameInstance;

    private static Factory factory = new Factory();

    public static Factory getFactoryInstance(GameInstance gameInstance) {
        factory.gameInstance = gameInstance;
        return factory;
    }

    enum GameObject {
        EnemyTank, MyTank, RandomBuff, StrongEnemyTank,
        // EnemyTank: 敌方坦克类(三种坦克都会刷),实例化并添加进入列表
        // MyTank: 我方坦克
        // RandomBuff: 随机Buff,调用 Buff 的 createBuff 方法
        // StrongEnemyTank: 敌方重型坦克,实例化并添加进入列表
    }

    public void createGameObject(GameObject gameObject, int... position) {
        // 第一个参数: 物体种类
        // 第二个可变参数: 位置(randomBuff不需要)
        if (gameObject.equals(GameObject.StrongEnemyTank)) {
            EnemyTank enemyTank = new StrongEnemyTank(position[0], position[1]);
            gameInstance.getEnemyTanksList().add(enemyTank);
            new Thread(enemyTank).start();
        }
        else if (gameObject.equals(GameObject.EnemyTank)) {
            int i = new Random().nextInt(6); // 0,1,2,3,4,5
            // 随机刷新坦克种类: 3:2:1
            if (i == 0 || i == 1 || i == 2) {
                EnemyTank enemyTank = new EnemyTank(position[0], position[1]);
                gameInstance.getEnemyTanksList().add(enemyTank);
                new Thread(enemyTank).start();
            }
            if (i == 3) {
                EnemyTank enemyTank = new StrongEnemyTank(position[0], position[1]);
                gameInstance.getEnemyTanksList().add(enemyTank);
                new Thread(enemyTank).start();
            }
            if (i == 4 || i == 5) {
                EnemyTank enemyTank = new FastEnemyTank(position[0], position[1]);
                gameInstance.getEnemyTanksList().add(enemyTank);
                new Thread(enemyTank).start();
            }
        } else if (gameObject.equals(GameObject.MyTank)) {
            gameInstance.setMyTank(new MyTank(position[0], position[1]));
        } else if (gameObject.equals(GameObject.RandomBuff)) {
            gameInstance.getBuffList().add(Buff.createBuff());
        }
    }

}
