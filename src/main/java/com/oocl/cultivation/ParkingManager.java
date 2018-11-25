package com.oocl.cultivation;

import java.util.ArrayList;

public class ParkingManager {
    private final ArrayList<ParkingBoy> parkingBoys;

    public ParkingManager(ArrayList<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket parkCarByParkingBoy(ParkingBoy parkingBoy,Car car){
        return parkingBoy.park(car);
    }
}
