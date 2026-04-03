package com.electricity.utils;

/**
 * LoggerUtil — Simple console logger with log levels.
 */
public class LoggerUtil {

    public static void info(String message) {
        System.out.println("[INFO]  " + message);
    }

    public static void warn(String message) {
        System.out.println("[WARN]  " + message);
    }

    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void step(String message) {
        System.out.println("[STEP]  ➤ " + message);
    }

    public static void pass(String tcId, String message) {
        System.out.println("[PASS]  ✅ " + tcId + " — " + message);
    }

    public static void fail(String tcId, String message) {
        System.out.println("[FAIL]  ❌ " + tcId + " — " + message);
    }
}
