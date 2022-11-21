package group.map;

import java.util.ArrayList;
import java.util.List;

public class Dot {
    int simpleX;
    int simpleY;
    Dot parentDot;

    public Dot(int[] xy, Dot parentDot) {
        simpleX = xy[0];
        simpleY = xy[1];
        this.parentDot = parentDot;
    }

    public Dot(int x, int y, Dot parentDot) {
        simpleX = x;
        simpleY = y;
        this.parentDot = parentDot;
    }

    public boolean isSamePosition(Dot dot) {
        return dot.simpleX == simpleX && dot.simpleY == simpleY;
    }

    public boolean isInvolveDots(ArrayList<Dot> dots) {
        for (Dot dot : dots
        ) {
            if (this.isSamePosition(dot)) {
                return true;
            }
        }

        return false;
    }

    public List<Dot> expend() {
        List<Dot> dots = new ArrayList<>();
        if (simpleX > 0) {
            dots.add(new Dot(simpleX - 1, simpleY, this));
        }
        if (simpleX < MapData.dotsLength) {
            dots.add(new Dot(simpleX + 1, simpleY, this));
        }
        if (simpleY > 0) {
            dots.add(new Dot(simpleX, simpleY - 1, this));
        }
        if (simpleX < MapData.dotsWidth) {
            dots.add(new Dot(simpleX, simpleY + 1, this));
        }

        return dots;
    }

    @Override
    public String toString() {
        return "Dot{" +
               "simpleX=" + simpleX +
               ", simpleY=" + simpleY +
               '}';
    }

    public Dot getParentDot() {
        return parentDot;
    }

    public int getSimpleX() {
        return simpleX;
    }

    public int getSimpleY() {
        return simpleY;
    }
}
