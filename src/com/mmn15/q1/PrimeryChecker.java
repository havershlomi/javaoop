package com.mmn15.q1;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeryChecker extends Thread {

    private int countUntil;
    private ConcurrentSkipListSet<Integer> primeNumbers;
    private AtomicInteger currentIndex;
    private CountDownLatch latch;

    public PrimeryChecker(int m, AtomicInteger currentIndex, ConcurrentSkipListSet<Integer> primeNumbers, CountDownLatch latch) {
        this.countUntil = m;
        this.currentIndex = currentIndex;
        this.primeNumbers = primeNumbers;
        this.latch = latch;
    }

    //checks whether an int is prime or not.
    private boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    @Override
    public void run() {
        int currentNum;
        Boolean isPrime;
        //check for prime as long as we didn't pass m
        //get the next number for check and increase the counter atomically
        while ((currentNum = this.currentIndex.getAndAdd(1)) <= this.countUntil) {
            isPrime = isPrime(currentNum);
            if (isPrime) {
                primeNumbers.add(currentNum);
            }
        }
        //count down when finished your work
        latch.countDown();
    }
}
