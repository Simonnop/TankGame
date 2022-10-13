package group.su.view;

import javax.swing.*;
import java.awt.*;

public class WelMenuPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,800,700);
    }
}
