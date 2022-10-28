package group.su.map;

import java.awt.*;
import java.util.*;
import java.util.List;

import static group.Attributes.*;


public class MapData {

    /*
     *
     *  加载地图贴图
     *  二位数组列表存储地图
     * */

    static int dotsLength = WINDOW_LENGTH / OBJECT_SIZE;

    static int dotsWidth = WINDOW_WIDTH / OBJECT_SIZE;

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
        // 从初始化时使用 二维数组 是因为好写, 可以直接用 python 输出复制过来

        map_1 = new ArrayList<>();

        map_1.add(new ArrayList<>(Arrays.asList( // River
                new int[][]{}
        )));
        map_1.add(new ArrayList<>(Arrays.asList( // Wall
                new int[][]{{3, 0}, {3, 1}, {3, 7}, {3, 8}, {0, 8}, {6, 4}, {7, 6}, {8, 5},
                        {9, 3}, {10, 2}, {12, 4}, {10, 9}}
        )));
        map_1.add(new ArrayList<>(Arrays.asList( // Tree
                new int[][]{{0, 4}, {0, 5}, {1, 5}, {4, 6}, {4, 7}, {5, 6}, {6, 6}, {10, 4}, {10, 5}, {10, 6}}
        )));
        map_1.add(new ArrayList<>(Arrays.asList( // Brick
                new int[][]{{1, 1}, {1, 2}, {3, 3}, {3, 4}, {6, 2}, {7, 1}, {7, 2}, {5, 5}, {9, 1},
                        {9, 2}, {9, 4}, {11, 1}, {11, 2}, {11, 4}, {1, 6}, {2, 6}, {3, 6}, {11, 6},
                        {1, 8}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, {3, 9}, {3, 10}, {3, 12}, {5, 7},
                        {5, 8}, {5, 9}, {5, 10}, {6, 9}, {6, 10}, {7, 7}, {7, 8}, {7, 9}, {7, 10},
                        {9, 7}, {9, 9}, {9, 11}, {9, 12}, {10, 12}, {11, 6}, {11, 7}, {11, 8},
                        {11, 9}, {11, 11}, {11, 12}}
        )));
    }

    public static List<List<int[]>> map_2;

    public static Map<Obstacle.ObstacleKind, Vector<Obstacle>> initialMap(List<List<int[]>> map) {

        Map<Obstacle.ObstacleKind, Vector<Obstacle>> newMap = new HashMap<>();

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            newMap.put(obstacleKind, new Vector<>());
        }

        for (Obstacle.ObstacleKind obstacleKind : Obstacle.ObstacleKind.values()
        ) {
            int[][] array = map.get(obstacleKind.ordinal()).toArray(new int[0][]);
            for (int[] ints : array
            ) {
                // 工厂化创建对象
                newMap.get(obstacleKind).add(
                        obstacleKind.returnObject(ints[0] * OBJECT_SIZE, ints[1] * OBJECT_SIZE));
            }
        }
        return newMap;
    }
}
