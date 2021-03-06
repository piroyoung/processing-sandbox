package io.piroyoung.processing.model;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.piroyoung.processing.util.Color.toDecs;

public class Circle {
    private float r;
    private float theta;
    private PVector c;
    private List<Float> decColor;
    private PApplet parent;

    public Circle(PApplet parent, float x, float y, float r) {
        this.parent = parent;
        this.c = new PVector(x, y, 0);
        this.r = r;
        this.theta = parent.random(2 * parent.PI);

        List<String> colors = new ArrayList<>();
        colors.add("ffeaa5");
        colors.add("fe5f55");
        colors.add("c7efcf");
        colors.add("eef5db");
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        String color = colors.get(randomizer.nextInt(colors.size()));
        this.decColor = toDecs(color);
    }

    public void updateTheta() {
        this.theta = parent.random(2 * parent.PI);
    }

    public void updateZ() {
        this.c.z = this.culcZ();
    }

    public float culcZ() {
        float distance = c.dist(new PVector(parent.width / 2, parent.height / 2));
        return 1 - distance / (parent.width / 1.5f);
    }

    public void move(float dx, float dy) {
        c = c.add(dx, dy);
        if (c.x > parent.width) {
            c.x = c.x - parent.width;
        } else if (c.x < 0) {
            c.x = c.x + parent.width;
        }

        if (c.y > parent.height) {
            c.y = c.y - parent.height;
        } else if (c.y < 0) {
            c.y = c.y + parent.height;
        }
    }

    public void move(float velocity) {
        move(c.z * velocity * PApplet.cos(theta), c.z * velocity * PApplet.sin(theta));
    }

    public void render() {
        render(1.0f);
    }

    public void render(float radiusGain) {
        this.parent.noStroke();
        this.parent.fill(decColor.get(0), decColor.get(1), decColor.get(2), 200 * c.z);
        this.parent.ellipse(c.x, c.y, radiusGain * r * c.z, radiusGain * r * c.z);
    }
}
