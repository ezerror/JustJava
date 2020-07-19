package me.ezerror.mutilthreading.D0.Ch03;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


public class Test_BiaseLock9 {

    @Benchmark
    public void a() throws Exception {
        StringBuffer b = new StringBuffer();
        synchronized (b) {
            for (int i = 0; i < 100; i++) {
                b.append(i);
            }
        }
    }

    @Benchmark
    public void b() throws Exception {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            synchronized (b) {
                b.append(i);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(Test_BiaseLock9.class.getSimpleName())
                // 预热3轮
                .warmupIterations(10)
                // 度量5轮
                .measurementIterations(5)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}