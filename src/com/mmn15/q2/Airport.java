package com.mmn15.q2;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {

    private String name;
    private int numberOfLanes;
    private ConcurrentLinkedQueue<Request> requestOrder = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Integer> emptyLanes = new ConcurrentLinkedQueue<>();
    private HashMap<Integer, Integer> laneDic = new HashMap<>();
    private ReentrantLock departLock = new ReentrantLock();
    private ReentrantLock landLock = new ReentrantLock();

    public Airport(String name, int numberOfLanes) {
        this.name = name;
        this.numberOfLanes = numberOfLanes;
        //initialize list of empty lanes
        for (int i = 0; i < numberOfLanes; i++) {
            emptyLanes.add(i);
        }
    }

    public int depart(int flightNumber) {
        try {
            //add request for depart to the queue
            requestOrder.add(new Request(flightNumber, RequestType.Departure));
            print("Flight number [" + flightNumber + "] is asking for a lane to depart from in [" + this.name + "] Airport");
            //lock the lane allocation
            departLock.lock();
            //wait until the next request is yours and there is an empty lane for you
            while ((requestOrder.peek().getRequestType() != RequestType.Departure &&
                    requestOrder.peek().getFlightNumber() != flightNumber) ||
                    emptyLanes.isEmpty()) {

            }
            //if we are here that means we can depart
            int lane = emptyLanes.poll();
            //update the lane dic
            laneDic.put(lane, flightNumber);
            print("Flight number [" + flightNumber + "] could depart from lane: [" + lane + "] in  [" + this.name + "] Airport");
            //remove the request from the queue
            requestOrder.poll();
            return lane;
        } finally {
            departLock.unlock();
        }
    }

    public int land(int flightNumber) {
        try {
            //add request for land to the queue
            requestOrder.add(new Request(flightNumber, RequestType.Land));
            print("Flight number [" + flightNumber + "] is asking for a lane for landing in [" + this.name + "] Airport");

            //lock the lane allocation
            landLock.lock();

            //wait until the next request is yours and there is an empty lane for you
            while ((requestOrder.peek().getRequestType() != RequestType.Land &&
                    requestOrder.peek().getFlightNumber() != flightNumber) ||
                    emptyLanes.isEmpty()) {
            }
            //if we are here that means we can land
            int lane = emptyLanes.poll();
            //update the lane dic
            laneDic.put(lane, flightNumber);

            print("Flight number [" + flightNumber + "] could land in lane: [" + lane + "] in [" + this.name + "] Airport");
            //remove the request from the queue
            requestOrder.poll();
            return lane;
        } finally {
            landLock.unlock();
        }
    }

    public void freeRunway(int flightNumber, int lane) {
        print("Requesting to free runway: [" + lane + "] by Flight: [" + flightNumber + "] in [" + this.name + "] Airport");
        //validate the lane is bleongs to the flight number before making him free
        if (laneDic.get(lane) == flightNumber) {
            laneDic.remove(lane);
            //add the free lane for the list
            emptyLanes.add(lane);
            print("lane: [" + lane + "] is now empty in [" + this.name + "] Airport");
        } else {
            print("flight number: [" + flightNumber + "] can't free lane: [" + lane + "] in [" + this.name + "] Airport");
        }
    }
    //print message with timestamp
    private void print(String message) {

        //System.out.println(LocalDateTime.now() + " " + message);
        System.out.println(message);
    }
}

