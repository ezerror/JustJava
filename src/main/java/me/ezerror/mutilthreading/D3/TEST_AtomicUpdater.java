package me.ezerror.mutilthreading.D3;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TEST_AtomicUpdater {
    private volatile int field;

    public static void main(String[] args) {

        AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TEST_AtomicUpdater.class, "field");

        TEST_AtomicUpdater t = new TEST_AtomicUpdater();
        fieldUpdater.compareAndSet(t, 0, 10);

        // 修改成功 field = 10
        System.out.println(t.field);

        // 修改成功 field = 20
        fieldUpdater.compareAndSet(t, 10, 20);
        System.out.println(t.field);

        // 修改失败 field = 20
        fieldUpdater.compareAndSet(t, 10, 30);
        System.out.println(t.field);

    }
}
