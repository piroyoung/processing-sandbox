package io.piroyoung.processing.util;

public class SlidingBuffer {
    private float[] buffer;
    private int tick;
    private int size;

    public SlidingBuffer(int size) {
        this.size = size;
        this.tick = 0;
        this.buffer = new float[size];
    }

    private void next() {
        tick = tick < size - 1 ? tick + 1 : 0;
    }

    public void put(float v) {
        buffer[tick] = v;
        next();
    }

    public float getMax() {
        float max = Float.MIN_VALUE;
        for (float v : buffer) {
            if (max < v) {
                max = v;
            }
        }
        return max;
    }

    public float getMin() {
        float min = Float.MAX_VALUE;
        for (float v : buffer) {
            if (min > v) {
                min = v;
            }
        }
        return min;
    }

    public float getSum() {
        float sum = 0;
        for (float v : buffer) {
            sum += v;
        }
        return sum;
    }

    public float getAverage() {
        return getSum() / size;
    }
}
