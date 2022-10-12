package group;

import group.su.control.BackgroundControl;
import group.su.control.GameControl;

public class Application {

    /*
     *  主程序流程
     * */

    public static void main(String[] args) throws InterruptedException {

        // 创建程序控制托管对象
        GameControl gameControl = new GameControl();

        BackgroundControl backgroundControl = new BackgroundControl();

        // 欢迎起始菜单页面
        backgroundControl.welcomeMenuShow();
        // 创建地图对象等
        gameControl.gameInitial();

        // 显示游戏面板
        backgroundControl.gamePanelShow();
        // 敌方坦克动起来,加入监听器
        gameControl.gameStart();
        // 更新判断等
        gameControl.gameUpdate();

        // 游戏结束与结算
        gameControl.gameOver();
        // 移除游戏面板
        backgroundControl.gamePanelOut();
        // 显示结算菜单
        backgroundControl.overMenuShow();

    }
}
