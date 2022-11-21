package group;

import group.su.control.ViewControl;
import group.su.control.GameInstance;
import group.su.view.SelectPanel;

import static group.su.view.SelectPanel.returnSelectedMap;

public class Application {

    /*
     *  主程序流程
     * */

    // 用户名,开始游戏,重新游戏
    // 可全局访问

    public static String playerName = null;
    public static boolean gameRun = false;
    public static boolean restart = false;
    public static boolean tempStop = false;
    // 是否更新数据库
    public static boolean isUpdate = false;
    //菜单阶段是否结束
    public static boolean isInput = false;

    // 一个程序只有一个面板结构,故设置为 final
    public static final ViewControl VIEW_CONTROL = new ViewControl();

    public static void main(String[] args) throws InterruptedException {

        runApplication(new GameInstance());
    }

    private static void runApplication(GameInstance gameInstance) throws InterruptedException {

        // 一次游戏对应一次游戏实例
        // 根据游戏实例来建立游戏画板
        VIEW_CONTROL.createGamePanel(gameInstance);

        // 欢迎起始菜单页面
        VIEW_CONTROL.welcomeMenuShow();

        // 等待输入用户名
        // 选择难度和地图
        for (; ; ) {
            System.out.print("");
            if (isInput) {
                VIEW_CONTROL.selectPanel();
                isInput = false;
            }
            if (SelectPanel.allIsSelect) {
                gameInstance.setMap(returnSelectedMap());
                gameRun = true;
                break;
            }
        }

        // 等待 gameRun  为 true
        for (; ; ) {
            System.out.print("");
            if (gameRun) {
                // 创建地图对象等
                gameInstance.gameInitial();
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
                gameRun = false;
                restart = false;
                isUpdate = false;
                SelectPanel.allIsSelect = false;
                GameInstance.difficulty = null;
                SelectPanel.isTheSameType = true;
                runApplication(new GameInstance());
            }
        }
    }
}
