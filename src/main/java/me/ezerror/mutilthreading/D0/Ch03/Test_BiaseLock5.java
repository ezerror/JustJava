package me.ezerror.mutilthreading.D0.Ch03;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "lock5")
public class Test_BiaseLock5 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        new Thread(() -> {
            log.debug(SimpleClassLayout.parseInstance(o).toPrintSimple());
            synchronized (o) {
                log.debug("wait前：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
                try {
                    o.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("wait后：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            synchronized (o) {
                log.debug("notify");
                o.notify();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("最终:" + SimpleClassLayout.parseInstance(o).toPrintSimple());
    }
}