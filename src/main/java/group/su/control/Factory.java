package group.su.control;

import group.li.pojo.EnemyTank;
import group.li.pojo.FastEnemyTank;
import group.li.pojo.MyTank;
import group.li.pojo.StrongEnemyTank;

public class Factory {

    /*
    * TODO
    *  使用单例模式改进 Factory
    * */

    private final GameInstance gameInstance;

    Factory(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    enum GameObject {
        EnemyTank, MyTank, StrongEnemyTank, FastEnemyTank
    }

    public void createGameObject(GameObject gameObject, int x, int y) {

        if (gameObject.equals(GameObject.EnemyTank)) {
            gameInstance.getEnemyTanksList().add(new EnemyTank(x, y));
        } else if (gameObject.equals(GameObject.MyTank)) {
            gameInstance.setMyTank(new MyTank(x, y));
        } else if (gameObject.equals(GameObject.StrongEnemyTank)) {
            gameInstance.getEnemyTanksList().add(new StrongEnemyTank(x, y));
        } else if (gameObject.equals(GameObject.FastEnemyTank)) {
            gameInstance.getEnemyTanksList().add(new FastEnemyTank(x, y));
        }
    }

}
