package me.ezerror.mutilthreading.D0.Ch02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * 线程不安全的共享模型
 */
public class Ch03_Synchronized {
    static int counter = 0;
    /**
     * 定义一个对象，作为锁
     */
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }
}
