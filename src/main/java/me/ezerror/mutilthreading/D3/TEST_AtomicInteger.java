package me.ezerror.mutilthreading.D3;

import java.util.concurrent.atomic.AtomicInteger;

public class TEST_AtomicInteger {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);

        System.out.println(i.getAndIncrement());

        System.out.println(i.incrementAndGet());

        System.out.println(i.decrementAndGet());

        System.out.println(i.getAndDecrement());

        System.out.println(i.getAndAdd(5));

        System.out.println(i.addAndGet(-5));

        System.out.println(i.getAndUpdate(p -> p - 2));

        System.out.println(i.updateAndGet(p -> p + 2));

        i = new AtomicInteger(10);
        System.out.println(i.getAndAccumulate(10, (p, x) -> p * x));

        i = new AtomicInteger(10);
        System.out.println(i.accumulateAndGet(10, (p, x) -> p * x));
    }
}
