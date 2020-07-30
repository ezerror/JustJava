package me.ezerror.mutilthreading.D1.Ch06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;


@Slf4j(topic = "condition")
public class Test_Condition {
    static ReentrantLock lock = new ReentrantLock();
    static Condition 等外卖的队列 = lock.newCondition();
    static Condition 等快递的队列 = lock.newCondition();
    static volatile boolean 外卖到了 = false;
    static volatile boolean 快递到了 = false;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lock.lock();
                while (!外卖到了) {
                    try {
                        等外卖的队列.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("等到了它的外卖");
            }
            finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (!快递到了) {
                    try {
                        等快递的队列.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("等到了快递");
            }
            finally {
                lock.unlock();
            }
        }).start();

        sleep(1);
        sendBreakfast();
        sleep(1);
        sendCigarette();
    }

    private static void sendCigarette() {
        lock.lock();
        try {
            log.debug("送外卖来了");
            外卖到了 = true;
            等外卖的队列.signal();
        }
        finally {
            lock.unlock();
        }
    }

    private static void sendBreakfast() {
        lock.lock();
        try {
            log.debug("送快递来了");
            快递到了 = true;
            等快递的队列.signal();
        }
        finally {
            lock.unlock();
        }
    }
}
