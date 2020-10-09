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
//
    public void draw(int x1, int y1, int x2, int y2, boolean change) {
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
        double tg = (double)dy/dx;
        double y_line = tg * dx + y1;

        int d = 2 * dy - dx;
        int d1 = 2 * dy;
        int d2 = 2 * (dy - dx);
        while (x < x2) {
             y_line = tg * (x - x1) * step + y1;
            System.out.println("d = " + d +";  x = " + x + ";  y = "+ y+";  y_line = "+y_line);
            if (d < 0) {
                d = d + d1;
            } else {
                d = d + d2;
                y = y + step;
            }

//            double ya = y1 + step*tg*(x-x1);
//            System.out.println(tg*(x - x1) + " ya = " + ya);
            if (change) {
                pd.drawPixel(y, x, new Color(218, 53, 32,255));
            } else {
                pd.drawPixel(x, y, new Color(0, 53, 32,255));
            }
            x++;
        }
    }

//    public void draw(int x1, int y1, int x2, int y2, boolean change) {
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
//        double tg1 = (double) dy / dx;
//        int x = x1;
//        int y = y1;
//        int yy = y1;
//        double y_line = tg1 * dx + y1;
////        System.out.println("HHHHHHAAAAAAAAAAAAAAAAAAAAAAAAAAHH   tg   " + tg + "    tg1 = " + tg1);
//        double grad;
//
//        int d = 2 * dy - dx;
//        int d1 = 2 * dy;
//        int d2 = 2 * (dy - dx);
//        while (x < x2) {
//            y_line = tg1 * (x - x1) + y1;
//            System.out.println("d  " + d);
//            if (d < 0) {
//                d = d + d1;
//                yy = y + step;
//            } else {
//                d = d + d2;
//                yy = y;
//                y = y + step;
//            }
//            grad = Math.abs((y_line - step * y) / (y - yy));
//            int gradColor = (int) (255 * grad);
//            System.out.println("GRAD = " + (int) (255 * grad) + "  y = " + y);
//            if (change) {
//                pd.drawPixel(y, x, new Color(218, 53, 32, gradColor));
//                pd.drawPixel(yy, x, new Color(218, 53, 32, 255 - gradColor));
//
//            } else {
//                pd.drawPixel(x, y, new Color(218, 53, 32, gradColor));
//                pd.drawPixel(x, yy, new Color(218, 53, 32, 255 - gradColor));
//            }
//            x++;
//        }
//    }




    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        if (Math.abs(dy) < Math.abs(dx)) {
            draw(x1, y1, x2, y2, false);
        } else if (Math.abs(dy) > Math.abs(dx)) {
            draw(y1, x1, y2, x2, true);
        }
    }
}



