package group.li.pojo;

import group.Application;
import group.su.view.SelectPanel;

import java.awt.*;

public class FastEnemyTank extends EnemyTank {

    public static Image fastEnemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_up.png"));
    public static Image fastEnemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_down.png"));
    public static Image fastEnemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_left.png"));
    public static Image fastEnemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_right.png"));


    public FastEnemyTank(int x, int y) {
        super(x, y);
        setSpeed(3.0);
        setAddScore(10);
        setAttributes();
    }

    public void setAttributes(){
        switch (SelectPanel.difficulty){
            case "简单":  setSpeed(2);  break;
            case "普通":  setSpeed(2.5); break;
            case "困难":  setSpeed(3); break;
            case "地狱":  setSpeed(4.5); break;
        }
    }
}
