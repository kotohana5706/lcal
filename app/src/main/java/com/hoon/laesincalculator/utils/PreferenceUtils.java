package com.hoon.laesincalculator.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hoon.laesincalculator.LaesinCalculator;

public class PreferenceUtils {
    private static SharedPreferences sPreferences;

    static {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(LaesinCalculator.getAppContext());
    }

    public static void putString(int keyId, String value) {
        putString(LaesinCalculator.getAppContext().getString(keyId), value);
    }

    public static void putString(String key, String value) {
        sPreferences
                .edit()
                .putString(key, value)
                .commit();
    }

    public static String getString(int keyId) {
        return getString(keyId, null);
    }

    public static String getString(int keyId, String defValue) {
        return getString(LaesinCalculator.getAppContext().getString(keyId), defValue);
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defValue) {
        return sPreferences.getString(key, defValue);
    }

    public static void putBoolean(int keyId, boolean value) {
        putBoolean(LaesinCalculator.getAppContext().getString(keyId), value);
    }

    public static void putBoolean(String key, boolean value) {
        sPreferences
                .edit()
                .putBoolean(key, value)
                .commit();
    }

    public static boolean getBoolean(int keyId) {
        return getBoolean(LaesinCalculator.getAppContext().getString(keyId), false);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sPreferences.getBoolean(key, defValue);
    }

    public static void putInt(int keyId, int value) {
        putInt(LaesinCalculator.getAppContext().getString(keyId), value);
    }

    public static void putInt(String key, int value) {
        sPreferences
                .edit()
                .putInt(key, value)
                .commit();
    }

    public static int getInt(int keyId) {
        return getInt(LaesinCalculator.getAppContext().getString(keyId), -1);
    }

    public static int getInt(int keyId, int defValue) {
        return getInt(LaesinCalculator.getAppContext().getString(keyId), defValue);
    }

    public static int getInt(String key, int defValue) {
        return sPreferences.getInt(key, defValue);
    }

    public static void putDouble(int keyId, double value) {
        putDouble(LaesinCalculator.getAppContext().getString(keyId), value);
    }

    public static void putDouble(String key, double value) {
        sPreferences
                .edit()
                .putLong(key, Double.doubleToLongBits(value))
                .commit();
    }

    public static double getDouble(int keyId) {
        return getDouble(LaesinCalculator.getAppContext().getString(keyId), -1);
    }

    public static double getDouble(int keyId, double defValue) {
        return getDouble(LaesinCalculator.getAppContext().getString(keyId), defValue);
    }

    public static double getDouble(String key, double defValue) {
        return Double.longBitsToDouble(sPreferences.getLong(key, Double.doubleToLongBits(defValue)));
    }

    public static void putLong(int keyId, long value) {
        putLong(LaesinCalculator.getAppContext().getString(keyId), value);
    }

    public static void putLong(String key, long value) {
        sPreferences
                .edit()
                .putLong(key, value)
                .commit();
    }

    public static long getLong(int keyId) {
        return getLong(LaesinCalculator.getAppContext().getString(keyId));
    }

    public static long getLong(String key) {
        return sPreferences.getLong(key, -1l);
    }

    public static void clear() {
        sPreferences
                .edit()
                .clear()
                .commit();
    }
}
