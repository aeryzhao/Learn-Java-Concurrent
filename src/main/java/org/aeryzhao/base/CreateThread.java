package org.aeryzhao.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhaoxg
 * @date 2023/8/2 09:56
 */
public class CreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程的三种方式
        new MyThread().start();
        new MyRunnable().run();
        new Thread(new MyRunnable()).start();
        new Thread(() -> System.out.println("Lambda")).start();
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    // 1. 继承Thread类
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    // 2. 实现Runnable接口
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable");
        }
    }

    // 3. 实现Callable接口
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "MyCallable";
        }
    }
}
