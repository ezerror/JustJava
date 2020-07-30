package me.ezerror.mutilthreading.D1.Ch01;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

public class Ch03_testThreadMethod {


    private final static Logger log = LoggerFactory.getLogger(Ch03_testThreadMethod.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testinterruptPark();
    }

    private static void testinterrupt1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        sleep(500);
        t1.interrupt();
        log.debug(" 打断状态: {}" + t1.isInterrupted());
    }

    private static void testinterrupt2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {

            }
        }, "t1");
        t1.start();
        t1.interrupt();
        log.debug(" 打断状态: {}" + t1.isInterrupted());
    }


    private static void testinterruptPark() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug(" 打断状态: {}" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.debug("unpark...");

        }, "t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }


}

/**
 * 两阶段终止
 */
class TwoPhaseTermination {

    private final static Logger log = LoggerFactory.getLogger(TwoPhaseTermination.class);

    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("处理后事！");
                    break;
                }
                try {
                    sleep(1000);
                    log.debug("运行中");
                }
                catch (Exception e) {
                    log.error(e.getMessage(), e);
                    // 由于sleep中被打断，打断标记重置为false,重新设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    public void terminate() {
        monitor.interrupt();
    }
}
