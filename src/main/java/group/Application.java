package group;

import javax.swing.*;

import static group.su.control.GameControl.*;

public class Application extends JFrame {

    /*
     * TODO
     *  主程序流程
     * */

    public static void main(String[] args) throws InterruptedException {

        // 创建地图对象等
        gameInitial();

        // 敌方坦克动起来,加入监听器
        gameStart();

        // 更新判断等
        gameUpdate();

        // 游戏结束与结算
        gameOver();

    }
}
