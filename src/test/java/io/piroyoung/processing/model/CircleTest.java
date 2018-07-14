package io.piroyoung.processing.model;

import org.junit.jupiter.api.Test;

import java.util.List;

class CircleTest {

    @Test
    void testParse() {
        List<Float> colors = Circle.toDecs("1234ff");
        System.out.println(colors);
    }
}