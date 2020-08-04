package me.ezerror.mutilthreading.D4.Ch01.i;


import me.ezerror.mutilthreading.D4.Ch01.BlockingQueue;

// 拒绝策略
@FunctionalInterface
public
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}