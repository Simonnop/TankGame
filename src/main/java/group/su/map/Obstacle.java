package group.su.map;

public class Obstacle {

    /*
    * TODO
    *  建立地图上的障碍物
    *  砖,河,墙,树
    *  根据不同的类型附上不同的属性
    * */

    enum Shape {
        BRICK, RIVER, WALL, TREE
    }

    Shape shape;

    boolean isLive;

    final int x;
    final int y;

    boolean tankPass;
    boolean bulletPass;

    public Obstacle(int x, int y) {

        this.isLive = true;
        this.x = x;
        this.y = y;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public boolean isTankPass() {
        return tankPass;
    }

    public boolean isBulletPass() {
        return bulletPass;
    }

    public void setTankPass(boolean tankPass) {
        this.tankPass = tankPass;
    }

    public void setBulletPass(boolean bulletPass) {
        this.bulletPass = bulletPass;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
