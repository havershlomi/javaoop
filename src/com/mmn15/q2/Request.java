package com.mmn15.q2;

public class Request{

    private int flightNumber;
    private RequestType requestType;

    public Request(int flightNumber, RequestType requestType) {
        this.flightNumber = flightNumber;
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return "Request{" +
                "flightNumber=" + flightNumber +
                ", requestType=" + requestType +
                '}';
    }

    public int getFlightNumber() {
        return flightNumber;
    }
}

