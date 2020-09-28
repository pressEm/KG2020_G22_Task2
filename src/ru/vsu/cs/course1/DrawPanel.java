package ru.vsu.cs.course1;

import javafx.print.JobSettings;
import ru.vsu.cs.course1.Pixel_lines.BresenhamLineDrawer;
import ru.vsu.cs.course1.Pixel_lines.DDALineDrawer;
import ru.vsu.cs.course1.Pixel_lines.WuLineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DrawPanel extends JPanel implements MouseMotionListener {

//    public DrawPanel() {
//        this.addMouseMotionListener(this);
//    }

    private Point2D position = new Point(0, 0);

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics gr = bf.createGraphics();
        gr.setColor(new Color(0xF9FFFD));
        gr.fillRect(0, 0, getWidth(), getHeight());

//        РИСОВАНИЕ
//        LineDrawer ld = new GraphicsLineDrawer(gr);
        PixelDrawer pd = new GraphicsPixelDrawer(gr);
//        LineDrawer ld = new DDALineDrawer(pd);
        LineDrawer ld = new BresenhamLineDrawer(pd);
//        LineDrawer ld = new WuLineDrawer(pd);
        gr.setColor(Color.black);
        drawAll(ld);

        g.drawImage(bf, 0, 0, null);
        gr.dispose();
    }

    public void drawAll(LineDrawer ld) {
//
//        snowFlake(ld, 200, 200, 80, 28);
//
        ld.drawLine(100, 100, 200, 150);
//        ld.drawLine(300, 300, 300, 500);
//        ld.drawLine(300, 300, 500, 300);

//        ld.drawLine(getWidth() / 2, getHeight() / 2, (int) position.getX(), (int) position.getY());
//        DDALineDrawer l = new DDALineDrawer()
//        lineBresenham(ld, 30, 30, 300, 200);
    }

//    public void lineBresenham1(LineDrawer ld, int x1, int y1, int x2, int y2) {
//        ld.drawLine(x1, y1, x1, y1);
//        double d, d1, d2;
//        int x, y;
//
//        double dx = x2 - x1;
//        double dy = y2 - y1;
//        if (((Math.abs(dx) > Math.abs(dy)) && (x2 < x1))
//                || ((Math.abs(dx) <= Math.abs(dy)) && (y2 < y1))) {
//            int x = x1;
//            x1 = x2;
//            x2 = x;
//            int y = y1;
//            y1 = y2;
//            y2 = y;
//        }
//        dx = x2 - x1;
//        dy = y2 - y1;
//        int stp = 1;
//        if (Math.abs(dx) > Math.abs(dy)) {
//            if (dy < 0) {
//                stp = -1;
//                dy = -dy;
//            }
//            d = (dy * 2) - dx;
//            d1 = dy * 2;
//            d2 = (dy - dx) * 2;
//            y = y1;
//            for (int i = x1 + 1; i < x2; i++) {
//                if (d > 0) {
//                    y = y + stp;
//                    d = d + d2;
//
//                }
//            }
//
//
//        }
//
//        d = (dy * 2) - dx;
//        d1 = dy * 2;
//        d2 = (dy - dx) * 2;
//        y = y1;
//        for (int i = x1 + 1; i < x2; i++) {
//
//            if (d > 0) {
//                y = y + stp;
//                d = d + d2;
//            } else {
//
//                d = d + d1;
//                ld.drawLine(x, y);
//            }
//
//        }
//
//    }




        public void lineBresenham(LineDrawer ld, int x1, int y1, int x2, int y2){
        ld.drawLine(x2, y2, x2, y2);

        int xCurr = x1;
        int yCurr = y1;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double tgA = dy/dx;
        double f = 0;
        double b = y1 - x1*dy/dx;

        while (xCurr < x2){
//            double dx = x2 - xCurr;
//            double dy = y2 - yCurr;
//            double tgA = dy/dx;
            xCurr++;
            if (f > 0.5){
                yCurr++;
                f--;
            } else if (f < 0.5){
                f= f + tgA;
            }
            ld.drawLine(xCurr, yCurr, xCurr, yCurr);
        }
        System.out.println(" " + y2 + " " + yCurr);
        System.out.println(" " + x2 + " " + xCurr);

    }

    public void snowFlake(LineDrawer ld, int x, int y, int r, int n){
        double d = 2*Math.PI/n;
        for (int i = 0; i < n; i++) {
            double dx = r*Math.cos(d*i);
            double dy = r*Math.sin(d*i);
            ld.drawLine(x, y,  x + (int) dx, y + (int) dy);
//            g.drawLine(x, y, x + (int) dx, y + (int) dy);
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        position = new Point(mouseEvent.getX(), mouseEvent.getY());
        repaint();
    }
}
