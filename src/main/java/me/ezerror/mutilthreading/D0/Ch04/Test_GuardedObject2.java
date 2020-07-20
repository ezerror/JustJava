package me.ezerror.mutilthreading.D0.Ch04;


import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "g2")
public class Test_GuardedObject2 {
    private Object response;
    private final Object lock = new Object();

    public Object getResponse(long mills) {
        // 1、 记录最初时间
        long begin = System.currentTimeMillis();
        // 2、 已经经历的时间
        long passTime = 0;
        synchronized (lock) {
            while (response == null) {
                // 4、 假设 millis 是 1000，结果在 400 时唤醒了，那么还有 600 要等
                long waitTime = mills - passTime;
                if (waitTime <= 0) {
                    log.debug("break!");
                    break;
                }
                try {
                    lock.wait(waitTime);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                // 3、 如果提前被唤醒，这时已经经历的时间假设为 400
                passTime = System.currentTimeMillis() - begin;
                log.debug("timePassed: {}, object is null {}",
                        passTime, response == null);
            }
            return response;
        }
    }

    public void setResponse(Object response) {
        synchronized (lock) {
            this.response = response;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        Test_GuardedObject2 object = new Test_GuardedObject2();
        new Thread(() -> {
            log.debug("开始设置回应...");
            // 模拟耗时操作
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = "一个response";
            log.debug("给出回应,{}", response);
            object.setResponse(response);
        }, "t1").start();

        log.debug("等待...");
        Object response = object.getResponse(2000);
        log.debug(String.valueOf(response));
    }
}

