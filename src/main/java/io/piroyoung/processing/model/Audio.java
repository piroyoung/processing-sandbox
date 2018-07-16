package io.piroyoung.processing.model;

import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio {
    private PApplet parent;
    private Minim minim;
    private AudioInput audioIn;
    private AudioOutput audioOutput;

    public Audio(PApplet parent) {
        this.parent = parent;
        minim = new Minim(parent);
        audioIn = minim.getLineIn(Minim.MONO);
        audioOutput = minim.getLineOut();
    }

    public float getInputLevel() {
        return audioIn.mix.level();
    }
}
