package group.su.view;

import javax.swing.*;
import java.awt.*;

public class WelMenuPanel extends JPanel {
    @Override
    public void print(Graphics g) {
        g.drawRect(0, 0, 800, 700);
        g.drawString("MenuPanel Test", 400, 350);
    }
}
