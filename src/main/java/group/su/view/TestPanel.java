package group.su.view;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,800,700);
    }
}
