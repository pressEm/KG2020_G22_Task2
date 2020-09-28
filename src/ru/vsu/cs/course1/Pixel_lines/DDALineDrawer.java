package ru.vsu.cs.course1.Pixel_lines;

import ru.vsu.cs.course1.LineDrawer;
import ru.vsu.cs.course1.PixelDrawer;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    public DDALineDrawer(PixelDrawer pd){  // alt+insert
        this.pd = pd;
    }

    private PixelDrawer pd;
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        if (Math.abs(dx) > Math.abs(dy)){ // горизонтальная прямая
            if (x1 > x2) {
                int x = x1;
                x1 = x2;
                x2 = x;
                int y = y1;
                y1 = y2;
                y2 = y;
            }
            double k = dy/dx;
            for (int j = x1; j < x2; j++) {
                double i = k*(j-x1) + y1; // y1 = kx1 + b; y = kx + y1 - kx1 = k * (x - x1) + y1;
                pd.drawPixel(j, (int) i, Color.red);
            }
        } else{
            if (y1 > y2) {
                int x = x1;
                x1 = x2;
                x2 = x;
                int y = y1;
                y1 = y2;
                y2 = y;
            }
            double kObr = dx/dy;
            for (int i = y1; i < y2; i++) {
                double j = kObr*(i-y1) + x1; // y1 = kx1 + b; y = kx + y1 - kx1 = k * (x - x1) + y1;
                pd.drawPixel((int) j, i, Color.blue);
            }
        }
    }
}
