package group.su.map;

import java.awt.*;
import java.util.*;
import java.util.List;


public class MapData {

    /*
     * TODO
     *  加载地图贴图
     *  二位数组列表存储地图
     * */

    static Image imageRiver = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/River.png"));
    static Image imageBrick = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Brick.png"));
    static Image imageTree = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Tree.png"));
    static Image imageWall = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Wall.png"));

    public static List<List<int[]>> map_1;

    static {
        // RIVER, WALL, TREE, BRICK
        // 将 map 设置为 List 是因为便于遍历
        // 从初始化时使用 二维数组 是因为好写

        map_1 = new ArrayList<>();

        map_1.add(new ArrayList<>(Arrays.asList(
                new int[][]{{1, 0}, {1, 1}}
        )));
        map_1.add(new ArrayList<>(Arrays.asList(
                new int[][]{{3, 2}, {3, 3}}
        )));
        map_1.add(new ArrayList<>(Arrays.asList(
                new int[][]{{5, 4}, {5, 5}}
        )));
        map_1.add(new ArrayList<>(Arrays.asList(
                new int[][]{{7, 6}, {7, 7}}
        )));
    }

    public static List<List<int[]>> map_2;
}
