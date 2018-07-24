package io.piroyoung.processing.util;

import io.piroyoung.processing.util.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

class ColorTest {

    @Test
    void testParse() {
        List<Float> colors = Color.toDecs("1234ff");
        System.out.println(colors);
    }
}