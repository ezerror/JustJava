package me.ezerror.mutilthreading.D0.Ch03;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "lock4")
public class Test_BiaseLock4 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        log.debug("synchronized前：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
        synchronized (o) {
            log.debug("synchronized中：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
        }
        log.debug("synchronized后：" + SimpleClassLayout.parseInstance(o).toPrintSimple());

        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            log.debug("synchronized前：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
            synchronized (o) {
                log.debug("synchronized中：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
            }
            log.debug("synchronized后：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
        },"t2").start();
    }
}