package me.ezerror.mutilthreading.D0.Ch02;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

@Slf4j(topic = "ch07")
/*
  锁膨胀过程
 */
public class Ch07_LockExpansion {
    static int counter = 0;
    /**
     * 定义一个对象，作为锁
     */
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        log.debug("1、初始：01 无锁");
        System.out.println(SimpleClassLayout.parseInstance(lock).toPrintSimple());

        log.debug("2、开启子线程,第一次synchonized上轻量级,休眠3s,保持锁持有状态: 00");
        new Thread(Ch07_LockExpansion::method1).start();
        Thread.sleep(500);

        log.debug("3、主线程请求锁,锁仍被占用，锁膨胀至：10");
        synchronized (lock) {
            System.out.println(SimpleClassLayout.parseInstance(lock).toPrintSimple());
        }
    }

    private static void method1() {
        synchronized (lock) {
            System.out.println(SimpleClassLayout.parseInstance(lock).toPrintSimple());
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
