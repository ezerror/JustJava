package me.ezerror.mutilthreading.D2;

import lombok.extern.slf4j.Slf4j;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

public class Test_balking {
    public static void main(String[] args) {
        TwoPhaseTermination3 tpt = new TwoPhaseTermination3();
        tpt.start();
        tpt.start();
        tpt.start();
    }
}

/**
 * 两阶段终止
 */
@Slf4j(topic = "tpt3")
class TwoPhaseTermination3 {

    private Thread monitor;

    private volatile static Boolean stop = false;

    private volatile static boolean isRunning = false;

    public void start() {
        synchronized (this) {
            if (isRunning) {
                return;
            }
            isRunning = true;
        }
        monitor = new Thread(() -> {
            while (true) {
                if (stop) {
                    log.debug("处理后事！");
                    break;
                }
                sleep(1);
                log.debug("running!");
            }
        });
        monitor.start();
    }

    public void terminate() {
        stop = true;
        monitor.interrupt();
    }
}