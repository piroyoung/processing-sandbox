package io.piroyoung.processing.model;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.piroyoung.processing.util.Color.toDecs;
import static processing.core.PApplet.*;

public class DiredtedPoint {

    private PApplet parent;
    private PVector posision;
    private List<Float> decColor;
    private float theta = PI * 6 / 4;

    public DiredtedPoint(PApplet parent, float x, float y) {
        this.parent = parent;
        this.posision = new PVector(x, y);

        List<String> colors = new ArrayList<>();
        colors.add("ffeaa5");
        colors.add("fe5f55");
        colors.add("c7efcf");
        colors.add("eef5db");
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        String color = colors.get(randomizer.nextInt(colors.size()));
        this.decColor = toDecs(color);
    }

    public void choice() {
        if (parent.random(1) < 0.5) theta = PI * 5 / 4;
        else theta = PI * 7 / 4;
    }

    public void move(float velocity) {
        this.posision.x += cos(theta) * velocity;
        this.posision.y += sin(theta) * velocity;
        if (this.posision.y < 0) this.posision.y += parent.height;
    }

    synchronized public void render() {
        this.parent.noStroke();
        this.parent.fill(decColor.get(0), decColor.get(1), decColor.get(2), 10);
        this.parent.ellipse(posision.x, posision.y, 2f, 2f);
    }

}
