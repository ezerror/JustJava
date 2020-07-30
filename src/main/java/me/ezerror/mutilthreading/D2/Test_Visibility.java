package me.ezerror.mutilthreading.D2;

import lombok.extern.slf4j.Slf4j;
import static me.ezerror.mutilthreading.util.Sleeper.sleep;

@Slf4j(topic = "visible")
public class Test_Visibility {
    volatile static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
                System.out.println("running");
            }
        });
        t.start();

        sleep(3);
        log.info("停止!");
        run = false; // 线程t不会如预想的停下来
    }
}
