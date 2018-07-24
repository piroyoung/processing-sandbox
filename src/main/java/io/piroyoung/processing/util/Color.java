package io.piroyoung.processing.util;

import java.util.ArrayList;
import java.util.List;

public class Color {
    public static List<Float> toDecs(String color) {
        List<Float> decColors = new ArrayList<>(3);
        decColors.add((float) Integer.parseInt(color.substring(0, 2), 16));
        decColors.add((float) Integer.parseInt(color.substring(2, 4), 16));
        decColors.add((float) Integer.parseInt(color.substring(4, 6), 16));
        return decColors;
    }
}
