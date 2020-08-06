package me.ezerror.mutilthreading.D4.Ch03;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

@Slf4j(topic = "timer")
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 1");
                sleep(2);
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };
        // 使用 timer 添加两个任务，希望它们都在 1s 后执行
        // 但由于 timer 内只有一个线程来顺序执行队列中的任务，因此『任务1』的延时，影响了『任务2』的执行
        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
    }
}