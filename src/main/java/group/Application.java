package group;

import group.su.control.GameControl;

import javax.swing.*;

public class Application extends JFrame {

    /*
     * TODO
     *  主程序流程
     * */

    public static void main(String[] args) throws InterruptedException {

        // 创建程序控制托管对象
        GameControl gameControl = new GameControl();

        // 创建地图对象等
        gameControl.gameInitial();

        // 敌方坦克动起来,加入监听器
        gameControl.gameStart();

        // 更新判断等
        gameControl.gameUpdate();

        // 游戏结束与结算
        gameControl.gameOver();

    }
}
