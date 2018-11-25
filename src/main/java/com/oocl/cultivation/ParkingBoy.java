package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket=parkingLot.addCar(car);
        if(parkingTicket==null){
            lastErrorMessage="The parking lot is full.";
        }
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        if(ticket==null){
            lastErrorMessage="Please provide your parking ticket.";
            return null;
        }
        Car fetchedCar=parkingLot.getCar(ticket);
        if(fetchedCar==null) {
            lastErrorMessage="Unrecognized parking ticket.";
        }
        return fetchedCar;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
