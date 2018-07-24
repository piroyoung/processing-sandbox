package io.piroyoung.processing;

import io.piroyoung.processing.model.DiredtedPoint;
import processing.core.PApplet;

import java.util.Arrays;

public class BinaryTrees extends PApplet {
    private static final int NUM = 30;
    private static final int FRAERATE = 120;
    private DiredtedPoint[] points;
    private int tick;

    public static void main(String[] args) {
        PApplet.main("io.piroyoung.processing.BinaryTrees");
    }

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        background(0);
        frameRate(FRAERATE);
        points = new DiredtedPoint[NUM];
        for (int i = 0; i < NUM; i++) {
            points[i] = new DiredtedPoint(this, width / 2, height);
        }
        ;
    }

    public void draw() {
        tick++;
        Arrays.stream(points).forEach(p -> {
            if (tick % 10 == 0) p.choice();
            p.move(1);
            p.render();
        });

    }
}
