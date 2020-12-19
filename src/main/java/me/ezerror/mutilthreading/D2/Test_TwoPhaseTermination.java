package me.ezerror.mutilthreading.D2;

import lombok.extern.slf4j.Slf4j;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;


public class Test_TwoPhaseTermination {
    public static void main(String[] args) {
        TwoPhaseTermination2 tpt = new TwoPhaseTermination2();
        tpt.start();
        sleep(3.5);
        tpt.terminate();

    }
}

/**
 * 两阶段终止
 */
@Slf4j(topic = "tpt2")
class TwoPhaseTermination2 {

    private Thread monitor;

    private volatile static Boolean stop = false;

    public void start() {
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
