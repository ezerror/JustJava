package me.ezerror.mutilthreading.D0.Ch03;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "lock6")
public class Test_BiaseLock6 {
    public static void main(String[] args) throws InterruptedException {
        Vector<Object> list = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Object o = new Object();
                list.add(o);
                synchronized (o) {
                    log.debug(i + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("===============> ");
            for (int i = 0; i < 30; i++) {
                Object o = list.get(i);
                log.debug(i + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
                synchronized (o) {
                    log.debug(i + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
                }
                log.debug(i + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
            }
        }, "t2");
        t2.start();
    }
}