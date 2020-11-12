package me.ezerror.test.none;

public class Testbreak {
    public static void main(String[] args) {
        flag:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i +" "+ j);
                break flag;
            }
        }
    }
}
