package myweather.myweather.utils;

public class Log {

    public static void debug(String msg) {
        System.out.println("\n### [DEBUG] " + msg + " ###");
    }

    public static void info(String msg) {
        System.out.println("\n### [INFO] " + msg + " ###");
    }

    public static void error(String msg) {
        System.out.println("\n### [ERROR] " + msg + " ###");
    }
}
