package group.su;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1080,720);
    }

    @Override
    public void run() {

        while (gameControl.gameRun) {

            System.out.println("test~~");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
