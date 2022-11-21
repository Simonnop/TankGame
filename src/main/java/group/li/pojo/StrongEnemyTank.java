package group.li.pojo;

import group.Application;
import group.su.view.SelectPanel;

import java.awt.*;

public class StrongEnemyTank extends EnemyTank {

    public static Image StrongEnemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_up.png"));
    public static Image StrongEnemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_down.png"));
    public static Image StrongEnemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_left.png"));
    public static Image StrongEnemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/StrongEnemyTank_right.png"));

    public StrongEnemyTank(int x, int y) {
        super(x, y);
        setAddScore(15);
        setAttributes();

    }
    public void setAttributes(){
        switch (SelectPanel.difficulty){
            case "简单":  setSpeed(1);  setHp(2);break;
            case "普通":  setSpeed(1.5); setHp(2);break;
            case "困难":  setSpeed(2);    setHp(3); break;
            case "地狱":  setSpeed(2.5); setHp(4);break;
        }
    }
}