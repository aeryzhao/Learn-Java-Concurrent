package org.aeryzhao.base;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaoxg
 * @date 2023/8/17 21:12
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 第一种：Future + ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Task());
        System.out.println(future.get());

        // 第二种：FutureTask + ExecutorService
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());

        // 第三种：FutureTask + Thread
        futureTask = new FutureTask<>(new Task());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        executorService.shutdown();
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int i = new Random().nextInt(1000);
            Thread.sleep(i);
            return i;
        }
    }
}
