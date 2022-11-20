package group.su.control;

import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;
import group.su.map.Buff;

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
        EnemyTank, MyTank, StrongEnemyTank, FastEnemyTank, RandomBuff
    }

    public void createGameObject(GameObject gameObject, int... position) {

        if (gameObject.equals(GameObject.EnemyTank)) {
            gameInstance.getEnemyTanksList().add(new EnemyTank(position[0], position[1]));
        }
        else if (gameObject.equals(GameObject.MyTank)) {
            gameInstance.setMyTank(new MyTank(position[0], position[1]));
        }
        else if (gameObject.equals(GameObject.StrongEnemyTank)) {
            gameInstance.getEnemyTanksList().add(new StrongEnemyTank(position[0], position[1]));
        }
        else if (gameObject.equals(GameObject.FastEnemyTank)) {
            gameInstance.getEnemyTanksList().add(new FastEnemyTank(position[0], position[1]));
        }
        else if (gameObject.equals(GameObject.RandomBuff)) {
            gameInstance.getBuffList().add(Buff.createBuff());
        }
    }

}
