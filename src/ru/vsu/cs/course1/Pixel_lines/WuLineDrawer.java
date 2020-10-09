package ru.vsu.cs.course1.Pixel_lines;

import org.w3c.dom.ls.LSOutput;
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

    private Color SetColor(int c) {
//        int c = (int) (255*t);
        Color res = new Color(218, 53, 32, c);
        return res;
    }

//    @Override
//    public void drawLine(int x1, int y1, int x2, int y2) {
//        int dy = y2 - y1;
//        int dx = x2 - x1;
//        if (Math.abs(dy) < Math.abs(dx)) {
//            Wu(x1, y1, x2, y2, false);
//        } else if (Math.abs(dy) > Math.abs(dx)) {
//            Wu(y1, x1, y2, x2, true);
//        }
//    }

    private void Wu(int x1, int y1, int x2, int y2, boolean change) {
        int x = x1;
        int y = y1;
        int Dx = x2 - x1;
        int Dy = y2 - y1;
        int e = 2 * Dy - Dx;

        float d;
        Color color1;
        Color color2;
        for (int i = 1; i <= Dx; i++) {
            d = -1F * e / (Dy + Dx) / 1.15F;
            if (e >= 0) {
                color1 = SetColor((int) (255 * (1F / 2 - d)));
                color2 = SetColor((int) (255 * (1F / 2 + d)));
                pd.drawPixel(x, y, color1);
                pd.drawPixel(x, y + 1, color2);
                y++;
                e += -2 * Dx + 2 * Dy;
            } else {
                color1 = SetColor((int) (255 * (1F / 2 + d)));
                color2 = SetColor((int) (255 * (1F / 2 - d)));
                pd.drawPixel(x, y, color1);
                pd.drawPixel(x, y - 1, color2);
                e += 2 * Dy;
            }
            x++;
        }

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

//        // WU
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
        System.out.println("dx = " + dx + "   dy = "+ dy);

        int x = x1+1;
        int y = y1;
        int yy = y1;
        double tg = (double) dy / dx;
        double y_line;
        double grad;

        int d = 2 * dy - dx;
        int d1 = 2 * dy;
        int d2 = 2 * (dy - dx);
        while (x < x2) {
            y_line = tg * (x - x1) * step + y1;
            System.out.println("d = " + d + ";  x = " + x + ";  y = " + y + ";  y_line = " + y_line);
            if (d < 0) {
                d = d + d1;
            } else if (d >= 0){
                System.out.println("000");
                d = d + d2;
                y = y + step;
            }
            if (((step>0) && (y_line > y)) || ((step<0) && (y_line <y ))) {
                System.out.println("y_line > y");
                yy = y + step;
            } else {
                yy = y - step;
            }
            if (yy == y) {
                grad = 1;
            } else {
                if (Math.abs(y_line - y) < 1){
                    grad = Math.abs((y_line - y));
//                    System.out.println("y = "+ y + "; yy = "+yy+"; grad = "+grad);
                } else grad = Math.abs(y_line - yy);
            }
//            double ya = y1 + step*tg*(x-x1);
//            System.out.println(tg*(x - x1) + " ya = " + ya);
            System.out.println(";  x = " + x + ";  y = " + y + ";  yy = " + yy  + ";  y_line = " + y_line);

            if (change) {
                pd.drawPixel(yy, x, new Color(218, 53, 32, (int)(255*grad)));
                pd.drawPixel(y, x, new Color(218, 53, 32, (int)(255*(1 - grad))));

            } else {
                pd.drawPixel(x, yy, new Color(0, 53, 32, (int)(255*grad)));
                pd.drawPixel(x, y, new Color(0, 53, 32, (int)(255*(1-grad))));

            }
            x++;
        }
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        System.out.println("dx = " + dx + "dy = "+ dy);
        if (Math.abs(dy) < Math.abs(dx)) {
            draw(x1, y1, x2, y2, false);
        } else if (Math.abs(dy) >= Math.abs(dx)) {
            draw(y1, x1, y2, x2, true);
        }
    }
}