package me.ezerror.mutilthreading.D0.Ch02;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;

@Slf4j(topic = "c4")
public class Ch04_ThreadSample {
    public static void main(String[] args) throws InterruptedException {
        Hashtable table = new Hashtable();
        Thread t1 = new Thread(() -> {
            if (table.get("key") == null) {
                try {
                    Thread.sleep(3000);
                    log.debug("t1....");
                    log.debug("t1....");
                    table.put("key", "1");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            if (table.get("key") == null) {
                try {
                    Thread.sleep(3000);
                    log.debug("t2....");
                    log.debug("t2....");
                    table.put("key", "2");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(table);
    }
}
