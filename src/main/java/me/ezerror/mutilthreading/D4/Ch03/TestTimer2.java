package me.ezerror.mutilthreading.D4.Ch03;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

@Slf4j(topic = "timer")
public class TestTimer2 {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        // 添加两个任务，希望它们都在 1s 后执行
        executor.schedule(() -> {
            System.out.println("任务1，执行时间：" + new Date());
            sleep(1);
        }, 1000, TimeUnit.MILLISECONDS);
        executor.schedule(() -> {
            System.out.println("任务2，执行时间：" + new Date());
        }, 1000, TimeUnit.MILLISECONDS);
    }
}
