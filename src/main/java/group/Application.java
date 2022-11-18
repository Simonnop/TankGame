package group;

import group.su.control.ViewControl;
import group.su.control.GameInstance;

import static group.su.map.MapData.map_1;

public class Application {

    /*
     *  主程序流程
     * */

    // 用户名,开始游戏,重新游戏
    // 可全局访问
    public static String playerName;

    public static boolean gameRun = false;
    public static boolean restart = false;

    // 一个程序只有一个面板结构,故设置为 final
    private static final ViewControl VIEW_CONTROL = new ViewControl();

    public static void main(String[] args) throws InterruptedException {

        runApplication(new GameInstance(map_1));
    }

    private static void runApplication(GameInstance gameInstance) throws InterruptedException {

        // 一次游戏对应一次游戏实例
        // 根据游戏实例来建立游戏画板
        VIEW_CONTROL.createGamePanel(gameInstance);

        // 欢迎起始菜单页面
        VIEW_CONTROL.welcomeMenuShow();
        // 创建地图对象等
        gameInstance.gameInitial();

        // 等待 gameRun 为 true
        for (; ; ) {
            System.out.print("");
            if (gameRun) {
                // 显示游戏面板并开始刷新,加入监听器
                VIEW_CONTROL.gamePanelShow();
                // 游戏开始,坦克动起来
                gameInstance.gameStart();

                break;
            }
        }

        while (gameRun) {
            // 更新判断等
            gameInstance.gameUpdate();
        }

        // 等待 gameRun 为 false,即上面循环执行结束
        // 游戏结束与结算
        gameInstance.gameOver();
        // 移除游戏面板
        VIEW_CONTROL.gamePanelOut();
        // 显示结算菜单
        VIEW_CONTROL.overMenuShow();

        // 等待 restart 为 true
        // 重新开始
        for (; ; ) {
            System.out.print("");
            if (restart) {
                gameRun = true;
                restart = false;
                runApplication(new GameInstance(map_1));
            }
        }
    }
}
