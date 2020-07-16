package me.ezerror.mutilthreading;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class TEST_BiasedLock {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("================================================================================================");
        Object o2= new Object();
        System.out.println(ClassLayout.parseInstance(o2).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
