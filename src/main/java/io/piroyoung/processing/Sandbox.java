package io.piroyoung.processing;

import io.piroyoung.processing.model.Audio;
import io.piroyoung.processing.model.Circle;
import io.piroyoung.processing.util.SlidingBuffer;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Sandbox extends PApplet {
    private static final int LONG_PHASE = 64;
    private static final int SHORT_PHASE = 3;
    private static float velocity = 2f;
    private static float r = 64;
    private List<Circle> circles;
    private Audio audio;
    private SlidingBuffer longBuffer;
    private SlidingBuffer shortBuffer;

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
        audio = new Audio(this);
        longBuffer = new SlidingBuffer(LONG_PHASE);
        shortBuffer = new SlidingBuffer(SHORT_PHASE);

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
        float level = audio.getInputLevel();
        longBuffer.put(level);
        shortBuffer.put(level);
        float radiusGain = map(shortBuffer.getAverage(), longBuffer.getMin(), longBuffer.getMax(), 1f, 5f);
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
