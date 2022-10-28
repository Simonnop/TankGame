package group.su.view;

import group.Mybatis.pojo.User;
import group.su.control.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static group.Attributes.*;
import static group.Mybatis.util.UserMethod.addUser;
import static group.Mybatis.util.UserMethod.getAllUsers;
import static group.su.view.WelMenuPanel.accountEnterField;
import static javax.swing.JOptionPane.showMessageDialog;

public class WelMenuPanel extends JPanel {

    static JTextField accountEnterField;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 700);

        g.setColor(new Color(87, 152, 203));
        g.setFont(new Font("幼圆", Font.BOLD, 40));
        g.drawString("坦 克 大 战", 260, 130);

        g.setColor(Color.gray);
        g.setFont(new Font("幼圆", Font.PLAIN, 20));
        g.drawString("请输入您的用户名", 300, 170);

        accountEnterField = new JTextField("用户名");
        accountEnterField.setSize(260, 40);
        accountEnterField.setLocation(250, 220);
        accountEnterField.addMouseListener(new TextFieldHandler());
        this.add(accountEnterField);

        JButton loginButton = new JButton("进入游戏");
        loginButton.setSize(260, 40);
        loginButton.setLocation(250, 300);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        loginButton.setBackground(new Color(1, 85, 157));
        loginButton.addActionListener(new LoginButtonHandler());
        this.add(loginButton);
        loginButton.requestFocus();

        JButton localButton = new JButton("离线游戏(无需用户名)");
        localButton.setSize(260, 40);
        localButton.setLocation(250, 370);
        localButton.setForeground(Color.WHITE);
        localButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        localButton.setBackground(new Color(1, 85, 157));
        localButton.addActionListener(new LocalButtonHandler());
        this.add(localButton);
        localButton.requestFocus();
    }

    public static String checkNull() {

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
            boolean isNew = true;
            boolean isLocal = false;

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
                gameRun = true;
            } else if (!isNew) {
                showMessageDialog(mainFrame,
                        "您好老玩家, " + playerName, "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                gameRun = true;
            } else if (addUser(new User(playerName)) == 1) {
                showMessageDialog(mainFrame,
                        "您好新玩家, " + playerName, "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                gameRun = true;
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
            gameRun = true;
        }
    }

}


