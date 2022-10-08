package group.li.pojo;

import group.Constant;

//每个子弹都是一个线程 所以实现Runnable接口
public class Bullet implements Runnable{
    private int x;//子弹x坐标
    private int y;//子弹x坐标
    private int direction = 0;//子弹方向
    private int speed = 5;//子弹默认速度
    private boolean isLive = true; //子弹是否还存活


    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        //子弹一旦开始创建 ，线程便开始无限循环
        while (true){

            //每次循环都要判断子弹的方向 根据方向从而不停移动
            switch (direction){
                case 0: //向上
                    y -= speed;
                    break;
                case 1: //向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
            //当子弹碰到敌人坦克时，线程结束
            //当子弹移动到面板的边界时，销毁子弹 线程结束
            if (!(x >= 0 && x <= Constant.WINDOW_WIDTH && y >= 0 && y <= Constant.WINDOW_LENGTH && isLive)){
                //设置子弹销毁
                isLive = false;
                break;
            }


        }
    }

}
