package group;

import group.su.control.PanelControl;
import group.su.control.GameControl;

public class Application {

    /*
     *  主程序流程
     * */

    // 创建程序控制托管对象
    private static final GameControl gameControl = new GameControl();

    private static final PanelControl panelControl = new PanelControl();


    public static void main(String[] args) throws InterruptedException {
        runApplication();
    }

    public static void runApplication() throws InterruptedException {

        // 欢迎起始菜单页面
        panelControl.welcomeMenuShow();

        // 创建地图对象等
        gameControl.gameInitial();

        // 显示游戏面板
        panelControl.gamePanelShow();
        // 敌方坦克动起来,加入监听器
        gameControl.gameStart();
        // 更新判断等
        gameControl.gameUpdate();

        // 游戏结束与结算
        gameControl.gameOver();
        // 移除游戏面板
        panelControl.gamePanelOut();
        // 显示结算菜单
        panelControl.overMenuShow();

    }
}
