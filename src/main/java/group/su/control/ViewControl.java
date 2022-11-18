package group.su.control;

import group.su.view.*;

public class ViewControl {

    private GamePanel gamePanel;
    private final MainFrame mainFrame = new MainFrame();
    private final WelMenuPanel welMenuPanel = new WelMenuPanel(mainFrame);
    private final OverMenuPanel overMenuPanel = new OverMenuPanel(mainFrame);

    public void createGamePanel(GameInstance gameInstance) {
        // 根据游戏实例建立游戏面板
        this.gamePanel = new GamePanel(gameInstance);
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

        mainFrame.getContentPane().remove(welMenuPanel);
        mainFrame.getContentPane().add(gamePanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        // 游戏面板开始刷新
        new Thread(gamePanel).start();
        // 加入监听器,并保证只有一个监听器
        if (mainFrame.getKeyListeners().length != 0) {
            mainFrame.removeKeyListener(mainFrame.getKeyListeners()[0]);
        }
        mainFrame.addKeyListener(new Listener(gamePanel.getGameInstance()));
        System.out.println("gamePanelShow");
    }

    public void gamePanelOut() {

        mainFrame.getContentPane().remove(gamePanel);
        mainFrame.repaint();
        System.out.println("gamePanelOut");
    }

    public void overMenuShow() {
        //奇怪的调节方法？？？，但是可以让结束界面完整出现
        mainFrame.setSize(800 + 1, 700);
        mainFrame.setSize(800, 700);
        mainFrame.getContentPane().add(overMenuPanel);
        mainFrame.repaint();

        System.out.println("overMenuShow");
    }
}
