package me.ezerror.mutilthreading.D4.Ch03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

@Slf4j(topic = "timer")
public class TestTimer3 {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
//        log.debug("start...");
//        pool.scheduleAtFixedRate(() -> {
//            log.debug("running...");
//        }, 1, 1, TimeUnit.SECONDS);
//
//        log.debug("start...");
//        pool.scheduleAtFixedRate(() -> {
//            log.debug("running...");
//            sleep(2);
//        }, 1, 1, TimeUnit.SECONDS);
        log.debug("start...");
        pool.scheduleWithFixedDelay(() -> {
            log.debug("running...");
            sleep(2);
        }, 1, 1, TimeUnit.SECONDS);

    }
}
