package com.mmn15.q2;

import java.util.Random;

public class Flight extends Thread {

    private int flightNumber;
    private Airport departAirport;
    private Airport landingAirport;

    public Flight(int flightNumber, Airport airDepart, Airport airLand) {
        this.departAirport = airDepart;
        this.flightNumber = flightNumber;
        this.landingAirport = airLand;
    }

    @Override
    public void run() {

        int departLane = departAirport.depart(this.flightNumber);
        //flight time
        simulateSleep();
        departAirport.freeRunway(this.flightNumber, departLane);
        //simulate air time
        simulateSleep(3000);

        int landLane = landingAirport.land(this.flightNumber);
        //flight time
        simulateSleep();
        landingAirport.freeRunway(this.flightNumber, landLane);
    }

    private void simulateSleep() {
        Random r = new Random();
        Long sleepTime = Long.valueOf((1 + r.nextInt(4)) * 1000);
        simulateSleep(sleepTime);
    }

    private void simulateSleep(long sleepTime) {
        try {
            sleep(Long.valueOf(sleepTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
