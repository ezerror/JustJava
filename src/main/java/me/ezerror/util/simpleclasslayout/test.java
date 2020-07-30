package me.ezerror.util.simpleclasslayout;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.simpleClassLayoutTest")
public class test {
    public static void main(String[] args) {
        Object o = new Object();
        log.debug("synchronized前：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
        synchronized (o) {
            log.debug("synchronized中：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
        }
        log.debug("synchronized后：" + SimpleClassLayout.parseInstance(o).toPrintSimple());
    }
}
