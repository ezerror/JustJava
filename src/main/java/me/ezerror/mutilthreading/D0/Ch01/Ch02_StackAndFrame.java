package me.ezerror.mutilthreading.D0.Ch01;



public class Ch02_StackAndFrame {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        method2();
    }

    private static void method2() {
        method3();
    }

    private static void method3() {
        int i = 3;
        String test = "test";
        System.out.println("1");
    }

}
