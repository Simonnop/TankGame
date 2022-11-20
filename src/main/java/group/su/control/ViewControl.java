package group.su.control;

import group.Mybatis.pojo.User;
import group.Mybatis.util.UserMethod;
import group.su.view.*;

import static group.Application.playerName;
import static group.su.control.Listener.getListener;

public class ViewControl {
    private GamePanel gamePanel;
    private final MainFrame mainFrame = new MainFrame();
    private final WelMenuPanel welMenuPanel = new WelMenuPanel(mainFrame);
    private final OverMenuPanel overMenuPanel = new OverMenuPanel(mainFrame);

    private final SelectPanel selectPanel =new SelectPanel(mainFrame);
    public void createGamePanel(GameInstance gameInstance) {
        // 根据游戏实例建立游戏面板
        this.gamePanel = new GamePanel(gameInstance,mainFrame);
        overMenuPanel.setGameInstance(gameInstance);

    }

    public void welcomeMenuShow() {

        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(welMenuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        System.out.println("welcomeMenuShow");
    }

    public void gamePanelShow() {

        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(gamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        // 游戏面板开始刷新
        new Thread(gamePanel).start();
        // 加入监听器,并以单例模式(饿汉)保证只有一个监听器
        mainFrame.addKeyListener(getListener(gamePanel.getGameInstance()));
        System.out.println("gamePanelShow "+mainFrame.getKeyListeners().length);
        mainFrame.requestFocus();
    }

    public void gamePanelOut() {

        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        System.out.println("gamePanelOut");
    }

    public void overMenuShow() {
        //奇怪的调节方法？？？，但是可以让结束界面完整出现
        mainFrame.setSize(800 + 1, 700);
        mainFrame.setSize(815, 645);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(overMenuPanel);
        System.out.println("overMenuShow");
    }

    public void rankListShow(RankListPanel rankListPanel){
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(rankListPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        System.out.println("rankListShow");
    }

    public void selectPanel(){
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(selectPanel);
        mainFrame.revalidate();
        System.out.println("selectPanel");
    }
}
