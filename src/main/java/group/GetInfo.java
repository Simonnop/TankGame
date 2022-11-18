package group;

import java.awt.*;

import static group.Attributes.OBJECT_SIZE;

public interface GetInfo {

    // 获取对象的位置与贴图,方便统一绘制与检测

    int getX();

    int getY();

    Image getImage();

    void setLive(boolean live);

    boolean isLive();

    default int[] toSimpleDot() {

        int[] dot = new int[2];
        dot[0] = getX() / OBJECT_SIZE;
        dot[1] = getY() / OBJECT_SIZE;

        return dot;
    }
}
