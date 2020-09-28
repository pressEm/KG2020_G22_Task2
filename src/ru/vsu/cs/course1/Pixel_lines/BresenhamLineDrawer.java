package ru.vsu.cs.course1.Pixel_lines;

import ru.vsu.cs.course1.LineDrawer;
import ru.vsu.cs.course1.PixelDrawer;

import java.awt.*;
import java.util.Map;

public class BresenhamLineDrawer implements LineDrawer {
    PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

//    public void draw(int x1, int y1, int x2, int y2, boolean change) {
//        System.out.println("kek");
//
//        int step;
//        int dx;
//        int dy;
//        if (x1 > x2) {
//            int c = x1;
//            x1 = x2;
//            x2 = c;
//            int cy = y1;
//            y1 = y2;
//            y2 = cy;
//        }
//
//        if (y2 < y1) {
//            step = -1;
//        } else step = 1;
//        dx = x2 - x1;
//        dy = step * (y2 - y1);
//        int x = x1;
//        int y = y1;
//
//        int d = 2 * dy - dx;
//        int d1 = 2 * dy;
//        int d2 = 2 * (dy - dx);
//        while (x < x2) {
//            System.out.println(d);
//            if (d < 0) {
//                d = d + d1;
//            } else {
//                d = d + d2;
//                y = y + step;
//            }
//            if (change) {
//                pd.drawPixel(y, x, Color.red);
//            } else {
//                pd.drawPixel(x, y, Color.red);
//            }
//            x++;
//        }
//    }


    public void draw(int x1, int y1, int x2, int y2, boolean change, double tg) {
        System.out.println("kek");

        int step;
        int dx;
        int dy;
        if (x1 > x2) {
            int c = x1;
            x1 = x2;
            x2 = c;
            int cy = y1;
            y1 = y2;
            y2 = cy;
        }

        if (y2 < y1) {
            step = -1;
        } else step = 1;
        dx = x2 - x1;
        dy = step * (y2 - y1);
        int x = x1;
        int y = y1;

        int d = 2 * dy - dx;
        int d1 = 2 * dy;
        int d2 = 2 * (dy - dx);
        while (x < x2) {
            System.out.println(d);
            if (d < 0) {
                d = d + d1;
            } else {
                d = d + d2;
                y = y + step;
            }
            double ya = y1 + step*tg*(x-x1);
            System.out.println(tg*(x - x1) + " ya = " + ya);
            if (change) {
                pd.drawPixel(y, x, new Color(218, 53, 32,255));
            } else {
                pd.drawPixel(x, y, new Color(218, 53, 32,255));
            }
            x++;
        }
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (Math.abs(dx) >= Math.abs(dy)) {
            double tg = dy/dx;
            System.out.println(tg);
            draw(x1, y1, x2, y2, false, tg);
        } else if (Math.abs(dx) < Math.abs(dy)) {
            double tg = dy/dx;
            draw(y1, x1, y2, x2, true, tg);
        }
    }
}


