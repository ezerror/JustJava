package me.ezerror.mutilthreading.D0.Ch04;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "g1")
public class Test_GuardedObject {
    private Object response;
    private final Object lock = new Object();

    public Object getResponse() {
        synchronized (lock) {
            while (response == null) {
                try {
                    lock.wait();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
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
        Test_GuardedObject object = new Test_GuardedObject();
        new Thread(() -> {
            log.debug("开始设置回应...");
            // 模拟耗时操作
            try {
                TimeUnit.SECONDS.sleep(3);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            String response = "一个response";
            log.debug("给出回应,{}", response);
            object.setResponse(response);
        }, "t1").start();

        log.debug("等待...");
        Object response = object.getResponse();
        log.debug(String.valueOf(response));
    }
}

