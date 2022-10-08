package group.su.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import group.Constant;

import static group.Constant.*;

public class MapData {

    /*
     * TODO
     *  加载地图贴图
     *  二位数组列表存储地图
     * */

    static Image imageRiver = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/River.png"));
    static Image imageBrick = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/Brick.png"));
    static Image imageTree = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/Tree.png"));
    static Image imageWall = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/Wall.png"));

    public static List<int[][]> Map_1;
    static List<int[][]> Map_2;

    static int lengthPos = WINDOW_LENGTH / OBJECT_SIZE;
    static int widthPos = WINDOW_WIDTH / OBJECT_SIZE;

    static {
        Map_1 = new ArrayList<>();
        // BRICK, RIVER, WALL, TREE
        Map_1.add(new int[][]{{1, 0}, {1, 1}});
        Map_1.add(new int[][]{{3, 2}, {3, 3}});
        Map_1.add(new int[][]{{5, 4}, {5, 5}});
        Map_1.add(new int[][]{{7, 6}, {7, 7}});
    }
}
