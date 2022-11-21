package group.su.control;

import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;
import group.su.map.Buff;

import java.util.Random;

public class Factory {

    /*
     * TODO
     *  使用单例模式改进 Factory
     * */

    private GameInstance gameInstance;

    private static Factory factory = new Factory();

    public static Factory getFactoryInstance(GameInstance gameInstance) {
        factory.gameInstance = gameInstance;
        return factory;
    }

    enum GameObject {
        EnemyTank, MyTank, RandomBuff, StrongEnemyTank
    }

    public void createGameObject(GameObject gameObject, int... position) {
        if (gameObject.equals(GameObject.StrongEnemyTank)) {
            EnemyTank enemyTank = new StrongEnemyTank(position[0], position[1]);
            gameInstance.getEnemyTanksList().add(enemyTank);
            new Thread(enemyTank).start();
        }
        else if (gameObject.equals(GameObject.EnemyTank)) {
            int i = new Random().nextInt(6); // 0,1,2,3,4,5
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
