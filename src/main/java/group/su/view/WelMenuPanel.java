package group.su.view;

import group.Application;
import group.Mybatis.pojo.User;
import group.li.pojo.Tank;
import group.li.util.DirectionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import static group.Application.playerName;
import static group.Mybatis.util.UserMethod.addUser;
import static group.Mybatis.util.UserMethod.getAllUsers;
import static javax.swing.JOptionPane.showMessageDialog;

public class WelMenuPanel extends JPanel {

    public static Image background = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/background.jpg"));

    private final MainFrame mainFrame;
    private static TextField accountEnterField = new TextField("用户名");
    JButton loginButton = new JButton("进入游戏");
    JButton localButton = new JButton("离线游戏(无需用户名)");

    static  boolean isNew ;
    public static  boolean isLocal ;
    public WelMenuPanel(MainFrame mainFrame){

        this.mainFrame = mainFrame;

        accountEnterField = new TextField("用户名");
        accountEnterField.addMouseListener(new TextFieldHandler());
        this.add(accountEnterField);

        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        loginButton.setBackground(new Color(1, 85, 157));
        loginButton.addActionListener(new LoginButtonHandler());
        this.add(loginButton);
        localButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showMessageDialog(mainFrame,
                            "开始离线游戏", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                    playerName = "您";
                    isLocal = true;
                    Application.gameRun = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        localButton.setForeground(Color.WHITE);
        localButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        localButton.setBackground(new Color(1, 85, 157));
        localButton.addActionListener(new LocalButtonHandler());
        this.add(localButton);

    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        g.drawImage(background, 0, 0, 800, 700, this);

        g.setColor(Color.WHITE);
        g.fillRoundRect(220, 70, 350, 400,20,20);

        g.setColor(new Color(87, 152, 203));
        g.setFont(new Font("幼圆", Font.BOLD, 40));
        g.drawString("坦 克 大 战", 280, 130);

        g.setColor(Color.gray);
        g.setFont(new Font("幼圆", Font.PLAIN, 20));
        g.drawString("请输入您的用户名", 315, 170);

        accountEnterField.setSize(260, 40);
        accountEnterField.setLocation(265, 220);
        if (playerName != null) {
            accountEnterField.setText(playerName);
        }
        if (Objects.equals(playerName, "您")) {
            accountEnterField.setText("用户名");
        }
        accountEnterField.requestFocus();

        loginButton.setSize(260, 40);
        loginButton.setLocation(265, 300);
        loginButton.requestFocus();

        localButton.setSize(260, 40);
        localButton.setLocation(265, 370);
        localButton.requestFocus();
    }

    private static String checkNull() {

        String errorInfo = "";
        if (Objects.equals(accountEnterField.getText(), "") ||
            Objects.equals(accountEnterField.getText(), "用户名")) {
            accountEnterField.setText("用户名");
            errorInfo += "账号不能为空\n";
        }

        return errorInfo;
    }

    class TextFieldHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            checkNull();

            if (e.getSource().equals(accountEnterField) &&
                Objects.equals(accountEnterField.getText(), "用户名")) {
                accountEnterField.setText("");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class LoginButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String errorInfo = checkNull();
            playerName = accountEnterField.getText();
            isNew = true;
            isLocal = false;

            try {
                for (User user : getAllUsers()) {
                    if (user.getUsername().equals(playerName)) {
                        isNew = false;
                        break;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Database connection error");
                isLocal = true;
            }

            if (!Objects.equals(errorInfo, "")) {
                showMessageDialog(mainFrame,
                        errorInfo, "错误",
                        JOptionPane.ERROR_MESSAGE);
            } else if (isLocal) {
                showMessageDialog(mainFrame,
                        "数据库连接错误\n为您开始本地游戏", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                Application.gameRun = true;
            } else if (!isNew) {
                showMessageDialog(mainFrame,
                        "您好老玩家, " + playerName, "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                Application.gameRun = true;
            } else if (addUser(new User(playerName)) == 1) {
                showMessageDialog(mainFrame,
                        "您好新玩家, " + playerName, "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                Application.gameRun = true;
            }
        }
    }

    class LocalButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMessageDialog(mainFrame,
                    "开始离线游戏", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            playerName = "您";
            isLocal = true;
            Application.gameRun = true;
        }
    }

}


