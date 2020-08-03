package me.ezerror.models._1_listener;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.models._1_listener.i.OnCompletedListner;

import static me.ezerror.mutilthreading.util.Sleeper.sleep;

/**
 * 监听模式
 */
@Slf4j(topic = "listener")
public class TestModel_Listener {

    public static void main(String[] args) {
        log.debug("");
        asyncMethod(() -> log.debug("异步方法真正执行完成！"));
        log.debug("执行异步方法后...");
    }

    /**
     * 异步方法
     *
     * @param listener
     */
    public static void asyncMethod(OnCompletedListner listener) {
        new Thread(() -> {
            log.debug("异步方法执行中...");
            sleep(3);
            listener.execute();
        }).start();
    }
}
