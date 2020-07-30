package me.ezerror.mutilthreading.D1.Ch03;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "lock6")
public class Test_BiaseLock6 {
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) throws InterruptedException {
        Vector<Lock> list = new Vector<>();
        int loopNumber = 5;
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Lock o = new Lock();
                list.add(o);
                synchronized (o) {
                    log.debug(++i + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
                }
                i--;
            }
            LockSupport.unpark(t2);
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            LockSupport.park();
            log.debug("================================");
            for (int i = 0; i < loopNumber; i++) {
                Lock o = list.get(i++);
                synchronized (o) {
                }
                log.debug(i-- + "\t" + SimpleClassLayout.parseInstance(o).toPrintSimple(false));
            }
        }, "t2");
        t2.start();
        t2.join();
        log.debug(SimpleClassLayout.parseInstance(new Lock()).toPrintSimple(false));
    }

    static class Lock {

    }
}