package com.mmn15.q1;
/*
MMN 15
Shlomi Haver
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static volatile ConcurrentSkipListSet<Integer> listOfPrimes;
    private static volatile AtomicInteger index;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Scanner in = new Scanner(System.in);
        // an atomic counter that indicates the current number being checked
        index = new AtomicInteger();
        //a concurrent collection of all the prime number that were found
        listOfPrimes = new ConcurrentSkipListSet<>();

        System.out.println("Please specify the amount of threads you want?");
        int n = 1;
        try {
            n = in.nextInt();
            if (n <= 0) {
                System.out.println("Number of threads has to be bigger then 0");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid number of threads");
            return;
        }
        System.out.println("Please specify the last number you would like to check?");
        int m = 1;
        try {
            m = in.nextInt();
            if (m <= 0) {
                System.out.println("The last number has to be bigger then 0");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid last number");
            return;
        }

        //this latch make sure that the numbers will be printed only after all the threads are done
        CountDownLatch latch = new CountDownLatch(n);
        ArrayList<PrimeryChecker> threads = new ArrayList<>();

        //create the threads
        for (int i = 0; i < n; i++) {
            threads.add(new PrimeryChecker(m, index, listOfPrimes, latch));
        }

        //start all the threads
        for (PrimeryChecker primeryChecker : threads) {
            primeryChecker.start();
        }

        //wait until all the threads are finished
        for (PrimeryChecker primeryChecker : threads) {
            primeryChecker.join();
        }

        //wait until all the threads are done
        latch.await();

        //Print numbers
        for (Integer num : listOfPrimes) {
            System.out.print(num + ",");
        }
    }
}
