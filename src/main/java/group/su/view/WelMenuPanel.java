package group.su.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static group.su.view.TextFieldHandler.checkNull;
import static group.su.view.WelMenuPanel.accountEnterField;
import static javax.swing.JOptionPane.showMessageDialog;

public class WelMenuPanel extends JPanel {

    static JTextField accountEnterField;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,800,700);

        g.setColor(new Color(87, 152, 203));
        g.setFont(new Font("幼圆", Font.BOLD, 20));
        g.drawString("坦 克 大 战", 130, 30);

        g.setColor(Color.gray);
        g.setFont(new Font("幼圆", Font.PLAIN, 15));
        g.drawString("请输入您的用户名", 130, 50);

        accountEnterField = new JTextField("用户名");
        accountEnterField.setSize(260, 40);
        accountEnterField.setLocation(60, 80);
        accountEnterField.addMouseListener(new TextFieldHandler());
        this.add(accountEnterField);

        JButton loginButton = new JButton("进入游戏");
        loginButton.setSize(260, 40);
        loginButton.setLocation(60, 230);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        loginButton.setBackground(new Color(1, 85, 157));
        loginButton.addActionListener(new ButtonHandler());
        this.add(loginButton);
        loginButton.requestFocus();
    }
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

    public static String checkNull() {

        String errorInfo = "";
        if (Objects.equals(accountEnterField.getText(), "") ||
            Objects.equals(accountEnterField.getText(), "用户名")) {
            accountEnterField.setText("用户名");
            errorInfo += "账号不能为空\n";
        }

        return errorInfo;
    }
}
class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String errorInfo = checkNull();
        String name = accountEnterField.getText();

        if (!Objects.equals(errorInfo, "")){
            showMessageDialog(null, errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
        } else{
            showMessageDialog(null, "您好,"+name, "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}