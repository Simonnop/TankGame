package group.su.map;

import java.util.ArrayList;
import java.util.List;

public class MapData {

    /*
    * TODO
    *  二位数组列表存储地图
    * */

    static List<int[][]> Map_1;
    static List<int[][]> Map_2;

    static {
        Map_1 = new ArrayList<>();
        // BRICK, RIVER, WALL, TREE
        Map_1.add(new int[][]{{1,2},{2,2}});
        Map_1.add(new int[][]{{1,2},{2,2}});
        Map_1.add(new int[][]{{1,2},{2,2}});
        Map_1.add(new int[][]{{1,2},{2,2}});
    }

    public static void main(String[] args) {
        for (int[][] ints : Map_1) {
            for (int[] set : ints
            ) {
                System.out.println("[" + set[0] + "," + set[1] + "]");
            }
        }
    }
}
