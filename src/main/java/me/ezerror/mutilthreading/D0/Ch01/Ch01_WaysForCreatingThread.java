package me.ezerror.mutilthreading.D0.Ch01;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Ch01_WaysForCreatingThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        W1();
//        W2();
//        W3();

        int i = 1;
        System.out.println(i);
    }

    private static void W3() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println("FutureTask 配合 Thread");
            Thread.sleep(2000);
            return "FutureTask 配合 Thread";
        });

        Thread t = new Thread(task, "thread-name");
        t.start();
        System.out.println(task.get());
    }

    /**
     * 直接使用 Thread
     */
    private static void W1() {
        Thread t = new Thread("thread-name") {
            public void run() {
                int a = 10;
                System.out.println("直接使用 Thread");
            }
        };
        t.start();
    }


    /**
     * 使用 Runnable 配合 Thread
     */
    private static void W2() {
        Runnable runnable = () -> System.out.println("使用 Runnable 配合 Thread");
        // 创建线程对象
        Thread t = new Thread(runnable);
        // 启动线程
        t.start();
    }
}
