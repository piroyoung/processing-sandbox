package io.piroyoung.processing;

import io.piroyoung.processing.model.Circle;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Sandbox extends PApplet {
    private static final int PHASE = 64;
    private static float velocity = 1.2f;
    private static float r = 64;
    private List<Circle> circles;
    private int tick;

    public static void main(String[] args) {
        PApplet.main("io.piroyoung.processing.Sandbox");
    }

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        background(0);
        circles = new ArrayList<Circle>();
        for (int i = 0; i < width; i += 64) {
            for (int j = 0; j < height; j += 64) {
                circles.add(new Circle(this, i, j, r));
            }
        }
        circles.forEach(Circle::render);
    }

    @Override
    public void draw() {
        clear();
        tick = tick > PHASE ? 0 : tick + 1;

        circles.forEach(c -> {
            if (random(1.0f) < 0.01) {
                c.resetTheta();
            }
            c.move(velocity);
            c.render();
        });
    }
}
