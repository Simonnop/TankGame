package group.database.control;
import group.view.*;

public class ViewControl {
    // 面板显示控制类
    private GamePanel gamePanel; // 可对应多个游戏实例
    private final MainFrame mainFrame = new MainFrame();  // 唯一应用画框
    private final WelMenuPanel welMenuPanel = new WelMenuPanel(mainFrame);  // 唯一欢迎页
    private final OverMenuPanel overMenuPanel = new OverMenuPanel(mainFrame);  // 唯一结束页
    private final SelectPanel selectPanel =new SelectPanel(mainFrame);  // 唯一选择页


    public void createGamePanel(GameInstance gameInstance) {
        // 根据游戏实例动态建立游戏面板
        this.gamePanel = new GamePanel(gameInstance,mainFrame);
        overMenuPanel.setGameInstance(gameInstance);
    }

    public void welcomeMenuShow() {
        // 移除所有面板,添加欢迎页
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(welMenuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        System.out.println("welcomeMenuShow");
    }

    public void selectPanel(){
        // 移除所有面板,弹出游戏介绍,添加选择难度页
        gamePanel.new MyDialogDemo();
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(selectPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void gamePanelShow() {
        // 移除所有面板,添加游戏面板
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(gamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        // 游戏面板开始刷新
        new Thread(gamePanel).start();
        // 加入监听器,并以单例模式(饿汉)保证只有一个监听器
        mainFrame.addKeyListener(Listener.getListener(gamePanel.getGameInstance()));
        System.out.println("gamePanelShow "+mainFrame.getKeyListeners().length);
        mainFrame.requestFocus();
    }

    public void gamePanelOut() {
        // 移除游戏面板
        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        System.out.println("gamePanelOut");
    }

    public void overMenuShow() {
        //奇怪的调节方法？？？，但是可以让结束界面完整出现
        mainFrame.setSize(800 + 1, 700);
        mainFrame.setSize(815, 645);
        mainFrame.getContentPane().removeAll();
        // 添加结束页
        mainFrame.getContentPane().add(overMenuPanel);
        System.out.println("overMenuShow");
    }

    public void rankListShow(RankListPanel rankListPanel){
        // 移除所有面板,添加排行榜
        // 排行榜通过参数传入
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(rankListPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        System.out.println("rankListShow");
    }


}
