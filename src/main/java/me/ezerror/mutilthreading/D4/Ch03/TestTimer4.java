package me.ezerror.mutilthreading.D4.Ch03;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

@Slf4j(topic = "timer")
public class TestTimer4 {
    public static void main(String[] args) {
        // 获得当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取本周四 18:00:00.000
        LocalDateTime thursday =
                now.with(DayOfWeek.SATURDAY).withHour(19).withMinute(47).withSecond(0).withNano(0);
        // 如果当前时间已经超过 本周四 18:00:00.000， 那么找下周四 18:00:00.000
        if (now.compareTo(thursday) >= 0) {
            thursday = thursday.plusWeeks(1);
        }

        // 计算时间差，即延时执行时间
        long initialDelay = Duration.between(now, thursday).toMillis();
        // 计算间隔时间，即 1 周的毫秒值
        long oneWeek = 7 * 24 * 3600 * 1000;

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        System.out.println("开始时间：" + new Date());
        executor.scheduleAtFixedRate(() -> {
            System.out.println("执行时间：" + new Date());
        }, initialDelay, oneWeek, TimeUnit.MILLISECONDS);
    }
}