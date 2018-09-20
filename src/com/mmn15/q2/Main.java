package com.mmn15.q2;

import com.mmn15.q1.PrimeryChecker;

import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Airport a1 = new Airport("Tel aviv", 3);
        Airport a2 = new Airport("Eilat", 3);

        Random rnd = new Random();

        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int flightNum = rnd.nextInt(1000);

            //decide which way the plane goes
            if (rnd.nextBoolean()) {
                Flight f1 = new Flight(flightNum, a1, a2);
                flights.add(f1);
            } else {
                Flight f1 = new Flight(flightNum, a2, a1);
                flights.add(f1);
            }
        }

        for (int i = 0; i < flights.size(); i++) {
            flights.get(i).start();
        }

        for (int i = 0; i < flights.size(); i++) {
            flights.get(i).join();
        }

        System.out.println("all the flights has landed");
    }
}
