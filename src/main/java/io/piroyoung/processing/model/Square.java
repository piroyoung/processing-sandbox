package io.piroyoung.processing.model;

import processing.core.PApplet;

public class Square {
    public float brightness;
    private PApplet parent;
    private float x, y;
    private float size;

    public Square(PApplet parent, float x, float y, float size) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.size = size;
        this.brightness = parent.random(10f);
    }

    float getBrightness() {
        return brightness;
    }

    public void render() {
        parent.noStroke();
        parent.fill(brightness);
        parent.rect(x, y, size, size);
    }


}
