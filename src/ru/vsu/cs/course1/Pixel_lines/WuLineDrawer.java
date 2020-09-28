package ru.vsu.cs.course1.Pixel_lines;

import ru.vsu.cs.course1.LineDrawer;
import ru.vsu.cs.course1.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }

    public void plot(int x, int y, double a) {
        int aInt = (int) ((int) 255 * a);
        System.out.println("a = " + a);
        pd.drawPixel(y, x, new Color(218, 53, 32, aInt));
        pd.drawPixel(x, y, new Color(218, 53, 32, 255 - aInt));
    }

    public double fpart(double x) {
        return (x - Math.round(x));
    }

    public void draw(int x1, int y1, int x2, int y2, boolean change) {
        int step;
        int dx;
        int dy;
        int a = 255;

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
        } else {
            step = 1;
        }

        dx = x2 - x1;
        dy = step * (y2 - y1);
        int x = x1;
        int y = y1;
        int yy;
        double tg = (double) dy / dx;

        int xend = x1;
        double yend = (y1 + tg * (xend - x1));
        double xgap = 1 - fpart(x1 + 0.5);
        int xpxl1 = xend;  // будет использоваться в основном цикле
        int ypxl1 = (int) Math.round(yend);
        plot(xpxl1, ypxl1, 1 - fpart(yend) * xgap);
        plot(xpxl1, ypxl1 + 1, fpart(yend) * xgap);
        double intery = yend + tg; // первое y-пересечение для цикла

        // обработать конечную точку
        xend = x2;
        yend = y2 + tg * (xend - x2);
        xgap = fpart(x2 + 0.5);
        int xpxl2 = xend; // будет использоваться в основном цикле
        int ypxl2 = (int) Math.round(yend);
        plot(xpxl2, ypxl2, 1 - fpart(yend) * xgap);
        plot(xpxl2, ypxl2 + 1, fpart(yend) * xgap);

        for (int xx = xpxl1 + 1; xx < xpxl2 - 1; xx++) {
            plot(x, (int) Math.round(intery), 1 - fpart(intery));
            plot(x, (int) Math.round(intery) + 1, fpart(intery));
            intery += tg;
        }

//
//            int d = 2 * dy - dx;
//            int d1 = 2 * dy;
//            int d2 = 2 * (dy - dx);
//            while (x < x2) {
//
//                double ya = (double) step * tg * (x - x1) + (double) y1;
//                System.out.println("yA = " + ya);
//                double tmp = (double) step / 2 - y + ya; // +/- step
//                if (dx == 0 || dy == 0) {
//                    tmp = (double) ya - y;
//                }
//                System.out.println(ya + " ya --- y  " + y);
//                System.out.println("tmp = " + tmp);
//                a = (int) (255 * Math.abs(tmp));
//                System.out.println(a);
//
//                if (d < 0) {
//                    d = d + d1;
////                yy = y + step;
//                } else {
//                    d = d + d2;
////                yy = y;
//                    y = y + step;
//                }
//
//
//                System.out.println("y = " + y + " ya = " + ya);
////            double kTmp1;
////            if ((y - ya) < (yy - ya)){
////                kTmp1 = (y-ya)/(yy-ya);
////            } else{
////                kTmp1 = (yy-ya)/ (y-ya);
////            }
////            System.out.println("kTmp = " + kTmp1);
////            a = (int) (255*Math.abs(kTmp1));
//
//
//                System.out.println(d + " " + d1 + " " + d2);
//
//                if (change) {
//                    pd.drawPixel(y, x, new Color(218, 53, 32, a));
//                    pd.drawPixel(yy, x, new Color(218, 53, 32, 255 - a));
//                } else {
//                    pd.drawPixel(x, y, new Color(218, 53, 32, a));
//                    pd.drawPixel(x, yy, new Color(218, 53, 32, 255 - a));
//                }
//                x++;
//            }
        }
//    public void draw(int x1, int y1, int x2, int y2, boolean change) {
//        int step;
//        int dx;
//        int dy;
//        int a = 255;
//
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
//        } else {
//            step = 1;
//        }
//
//        dx = x2 - x1;
//        dy = step * (y2 - y1);
//        int x = x1;
//        int y = y1;
//        int yy;
//        double tg = (double) dy / dx;
//
//        int d = 2 * dy - dx;
//        int d1 = 2 * dy;
//        int d2 = 2 * (dy - dx);
//        while (x < x2) {
//
//            double ya = (double) step * tg * (x - x1) + (double) y1;
//            System.out.println("yA = " + ya);
//            System.out.println(y);
//            double tmp = (double) step / 2 - y + ya; // +/- step
//            if (dx == 0 || dy == 0) {
//                tmp = (double) ya - y;
//            }
//            System.out.println(ya + " ya --- y  " + y);
//            System.out.println("tmp = " + tmp);
//            a = (int) (255 * Math.abs(tmp));
//            System.out.println(a);
//
//            if (d < 0) {
//                d = d + d1;
//                yy = y + step;
//            } else {
//                d = d + d2;
//                yy = y;
//                y = y + step;
//            }
//
//
//
//            System.out.println(d + " " + d1 + " " + d2);
//
//            if (change) {
//                pd.drawPixel(y, x, new Color(218, 53, 32, a));
//                pd.drawPixel(yy, x, new Color(218, 53, 32, 255 - a));
//            } else {
//                pd.drawPixel(x, y, new Color(218, 53, 32, a));
//                pd.drawPixel(x, yy, new Color(218, 53, 32, 255 - a));
//            }
//            x++;
//        }
//    }

        @Override
        public void drawLine ( int x1, int y1, int x2, int y2){

            int dx = x2 - x1;
            int dy = y2 - y1;

            if (Math.abs(dx) >= Math.abs(dy)) {
                draw(x1, y1, x2, y2, false);
            } else if (Math.abs(dx) < Math.abs(dy)) {
                draw(y1, x1, y2, x2, true);
            }
        }
    }