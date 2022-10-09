package group.su.map;

import java.awt.*;

public interface GetInfo {

    // 获取对象的位置与贴图,方便统一绘制与检测

    int getX();
    int getY();

    Image getImage();
}
