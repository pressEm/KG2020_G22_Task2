package ru.vsu.cs.course1;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	        MainWindow mw = new MainWindow();
	        mw.setDefaultCloseOperation(
                    WindowConstants.EXIT_ON_CLOSE
            );
	        mw.setSize(800, 600);
            mw.setVisible(true);
    }
}
