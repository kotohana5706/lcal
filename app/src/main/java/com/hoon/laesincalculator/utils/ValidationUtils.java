package com.hoon.laesincalculator.utils;

/**
 * Created by hoon1 on 2015-07-21.
 */
public class ValidationUtils {
    public static double validateNumber(String src, double def) {
        return validateNumber(src, Double.MIN_VALUE, Double.MAX_VALUE, def);
    }

    public static double validateNumber(String src, double min, double max, double def) {
        if (src == null || src.isEmpty()) {
            return def;
        }

        double number = Double.parseDouble(src);

        if (number < min || number > max) {
            throw new IllegalArgumentException();
        }

        return number;
    }
}
