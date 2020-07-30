package me.ezerror.mutilthreading.D1.Ch03;

import lombok.extern.slf4j.Slf4j;
import me.ezerror.util.simpleclasslayout.SimpleClassLayout;

@Slf4j(topic = "lock3")
public class Test_BiaseLock3 {
    public static void main(String[] args) {
        Object o = new Object();
        log.debug("synchronized前："+SimpleClassLayout.parseInstance(o).toPrintSimple());
        synchronized (o) {
            log.debug("synchronized中："+SimpleClassLayout.parseInstance(o).toPrintSimple());
        }
        log.debug("synchronized后："+SimpleClassLayout.parseInstance(o).toPrintSimple());
    }
}