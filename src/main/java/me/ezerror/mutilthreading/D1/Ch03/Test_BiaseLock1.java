package me.ezerror.mutilthreading.D1.Ch03;


import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "lock1")
public class Test_BiaseLock1 {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        synchronized (o1) {
            log.debug(SimpleClassLayout.parseInstance(o1).toPrintSimple());
        }
        TimeUnit.SECONDS.sleep(5);
        Object o2 = new Object();
        synchronized (o2) {
            log.debug(SimpleClassLayout.parseInstance(o2).toPrintSimple());
        }
    }
}
