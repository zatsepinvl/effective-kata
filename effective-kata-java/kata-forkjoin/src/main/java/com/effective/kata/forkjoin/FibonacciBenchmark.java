package com.effective.kata.forkjoin;

import com.effective.kata.common.profiler.Profiler;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

public class FibonacciBenchmark {
    public static void main(String[] args) {
        int n = 40;
        profile("loop", () -> calculateLoopFibonacci(n));
        profile("forkjoin", () -> forkJoinFibonacci(n));
    }

    private static void profile(String name, Supplier<Integer> calculator) {
        info("-------");
        info("Start: " + name);
        Profiler profiler = Profiler.start("loop");
        int result = calculator.get();
        profiler.finish();
        info("Time elapsed: " + profiler.getRunningTime().toMillis() + " millis");
        info("Result " + result);
        info("-------");
    }

    private static int forkJoinFibonacci(int n) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Fibonacci fibonacci = new Fibonacci(n);
        return forkJoinPool.invoke(fibonacci);
    }

    private static int calculateLoopFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int nMin2 = 0;
        int nMin1 = 1;
        for (int i = 2; i <= n; i++) {
            int fibo = nMin2 + nMin1;
            nMin2 = nMin1;
            nMin1 = fibo;
        }
        return nMin1;
    }

    private static void info(String x) {
        System.out.println(x);
    }
}
