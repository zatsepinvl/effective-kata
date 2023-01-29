package com.effective.kata.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    private final int n;

    public Fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be zero or positive");
        }
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        ForkJoinTask<Integer> task = new Fibonacci(n - 1).fork();
        Fibonacci fibo = new Fibonacci(n - 2);
        return fibo.compute() + task.join();
    }
}
