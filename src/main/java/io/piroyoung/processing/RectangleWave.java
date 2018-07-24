package io.piroyoung.processing;

import io.piroyoung.processing.model.Audio;
import io.piroyoung.processing.model.Square;
import io.piroyoung.processing.util.SlidingBuffer;
import processing.core.PApplet;

import java.util.Random;
import java.util.stream.Stream;

public class RectangleWave extends PApplet {
    public static Random r = new Random();
    private static float SIZE = 5;
    Square[][] squares;
    float[][] brightnesses;
    private int horizonal, vatical;
    private SlidingBuffer longBuffer;
    private SlidingBuffer shortBuffer;
    private Audio audio;

    public static void main(String[] args) {
        PApplet.main("io.piroyoung.processing.RectangleWave");
    }

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        frameRate(10);
        audio = new Audio(this);
        longBuffer = new SlidingBuffer(512);
        shortBuffer = new SlidingBuffer(3);
        horizonal = (int) (width / SIZE);
        vatical = (int) (height / SIZE);
        squares = new Square[horizonal][vatical];
        brightnesses = new float[horizonal][vatical];
        for (int i = 0; i < horizonal; i++) {
            for (int j = 0; j < vatical; j++) {
                squares[i][j] = new Square(this, i * SIZE, j * SIZE, SIZE);
                brightnesses[i][j] = squares[i][j].brightness;
            }
        }

    }

    public void updateBrightness(int i, int j) {
        if (i == 0 || j == 0 || i == horizonal - 1 || j == vatical - 1) {
            float b = squares[i][j].brightness;
            brightnesses[i][j] = b - 10f > 0 ? b - 10f : 0;
        } else {
            brightnesses[i][j] = (
                    squares[i - 1][j].brightness +
                            squares[i + 1][j].brightness +
                            squares[i][j - 1].brightness +
                            squares[i][j + 1].brightness +
                            squares[i - 1][j - 1].brightness +
                            squares[i + 1][j - 1].brightness +
                            squares[i - 1][j + 1].brightness +
                            squares[i + 1][j + 1].brightness +
                            squares[i][j].brightness
            ) / 9f;
        }

    }

    @Override
    public void draw() {
        for (int i = 0; i < horizonal; i++) {
            for (int j = 0; j < vatical; j++) {
                updateBrightness(i, j);
            }
        }

        float level = audio.getInputLevel();
        shortBuffer.put(level);
        longBuffer.put(shortBuffer.getAverage());
        float gain = map(shortBuffer.getAverage(), longBuffer.getMin(), longBuffer.getMax(), 0f, 100f);
//
        if (gain > 10f) {
            Stream.generate(() -> null).limit((int) gain).forEach(x -> {
                int i = r.nextInt(horizonal);
                int j = r.nextInt(vatical);
                if (i != 0 && j != 0 && i != horizonal - 1 && j != vatical - 1) {
                    brightnesses[i][j] = 255f;
                }
            });
        }

        for (int i = 0; i < horizonal; i++) {
            for (int j = 0; j < vatical; j++) {
                squares[i][j].brightness = brightnesses[i][j];
                squares[i][j].render();
            }
        }
    }
}
