package io.piroyoung.processing.model;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Circle extends PApplet {
    public float x, y, r;
    public float theta;
    private List<Float> decColor;
    private PApplet parent;

    public Circle(PApplet parent, float x, float y, float r) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.r = r;
        this.theta = random(2 * PI);

        List<String> colors = new ArrayList<>();
        colors.add("ffeaa5");
        colors.add("fe5f55");
        colors.add("c7efcf");
        colors.add("eef5db");
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        String color = colors.get(randomizer.nextInt(colors.size()));
        this.decColor = toDecs(color);
    }

    public static List<Float> toDecs(String color) {
        List<Float> decColors = new ArrayList<>(3);
        decColors.add((float) Integer.parseInt(color.substring(0, 2), 16));
        decColors.add((float) Integer.parseInt(color.substring(2, 4), 16));
        decColors.add((float) Integer.parseInt(color.substring(4, 6), 16));
        return decColors;
    }

    public void resetTheta() {
        this.theta = random(2 * PI);
    }

    public void move(float dx, float dy) {
        if (x + dx > parent.width) {
            x += dx - parent.width;
        } else if (x + dx < 0) {
            x += dx + parent.width;
        } else {
            x += dx;
        }

        if (y + dy > parent.height) {
            y += dy - parent.height;
        } else if (y + dy < 0) {
            y += dy + parent.height;
        } else {
            y += dy;
        }
    }

    public void move(float velocity) {
        move(velocity * cos(theta), velocity * sin(theta));
    }

    public void render() {
        this.parent.noStroke();
        this.parent.fill(decColor.get(0), decColor.get(1), decColor.get(2), 128);
        this.parent.ellipse(x, y, r, r);
    }
}
