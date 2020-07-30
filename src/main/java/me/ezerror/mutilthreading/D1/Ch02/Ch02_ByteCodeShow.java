package me.ezerror.mutilthreading.D1.Ch02;

/**
 * 字节码展示
 */
public class Ch02_ByteCodeShow {
    static int i;

    public static void main(String[] args) {
        add();
        minus();
    }

    private static void minus() {
        i--;
    }

    private static void add() {
        i++;
    }
}
