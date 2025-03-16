package me.qvx.utils;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void info(String message) {
        System.out.println(ANSI_WHITE + "[INFO]: " + ANSI_RESET + message);
    }

    public static void warning(String message) {
        System.out.println(ANSI_YELLOW + "[WARNING]: " + ANSI_RESET + message);
    }

    public static void error(String message) {
        System.out.println(ANSI_RED + "[ERROR]: " + message + ANSI_RESET);
    }

    public static void debug(String message) {
        System.out.println(ANSI_BLUE + "[DEBUG]: " + ANSI_RESET + message);
    }
}
