package me.ezerror.jdk;

public class CacheInteger {
    public static void main(String[] args) {
        Integer i1 = 0;
        Integer i2 = 0;

        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);

    }
}
