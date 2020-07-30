package me.ezerror.mutilthreading.D1.Ch03;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class Test_BiaseLock8 {
    static int x = 0;

    static Object o = new Object();

    @Benchmark
    public void a() throws Exception {
        x++;
    }

    @Benchmark
    public void b() throws Exception {
        Object o = new Object();
        synchronized (o) {
            x++;
        }
    }

    @Benchmark
    public void c() throws Exception {
        synchronized (o) {
            x++;
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(Test_BiaseLock8.class.getSimpleName())
                // 预热3轮
                .warmupIterations(3)
                // 度量5轮
                .measurementIterations(5)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}