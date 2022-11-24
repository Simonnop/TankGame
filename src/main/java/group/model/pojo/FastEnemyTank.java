package group.model.pojo;

import group.database.control.GameInstance;

import java.awt.*;

public class FastEnemyTank extends EnemyTank {

    public static Image fastEnemyTank_up = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_up.png"));
    public static Image fastEnemyTank_down = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_down.png"));
    public static Image fastEnemyTank_left = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_left.png"));
    public static Image fastEnemyTank_right = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/fastEnemyTank_right.png"));


    public FastEnemyTank(int x, int y) {
        super(x, y);
        setAddScore(10);
        setAttributes();
    }

    public void setAttributes() {
        switch (GameInstance.difficulty) {
            case "简单":
                setSpeed(2);
                break;
            case "普通":
            case "困难":
                setSpeed(3);
                break;
            case "地狱":
                setSpeed(4);
                break;
        }
    }
}
