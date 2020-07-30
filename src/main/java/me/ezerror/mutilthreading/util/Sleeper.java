package me.ezerror.mutilthreading.util;

public class Sleeper {

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
