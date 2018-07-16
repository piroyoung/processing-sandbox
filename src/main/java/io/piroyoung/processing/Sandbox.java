package io.piroyoung.processing;

import io.piroyoung.processing.model.Audio;
import io.piroyoung.processing.model.Circle;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Sandbox extends PApplet {
    private static final int PHASE = 5;
    private static float velocity = 2f;
    private static float r = 64;
    private List<Circle> circles;
    private int tick;
    private Audio audio;
    private float[] levelBuffer;

    public static void main(String[] args) {
        PApplet.main("io.piroyoung.processing.Sandbox");
    }

    private static float average(float[] buffer) {
        float sum = 0f;
        for (float v : buffer) {
            sum += v;
        }
        return sum / buffer.length;
    }

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        tick = 0;
        audio = new Audio(this);
        levelBuffer = new float[PHASE];

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
        tick = tick < PHASE - 1 ? tick + 1 : 0;
        levelBuffer[tick] = audio.getInputLevel();
        float radiusGain = map(average(levelBuffer), 0f, 0.5f, 1f, 10f);
        circles.forEach(c -> {
            if (random(1.0f) < 0.01) {
                c.updateTheta();
            }
            c.updateZ();
            c.move(velocity);
            c.render(radiusGain);
        });
    }
}
