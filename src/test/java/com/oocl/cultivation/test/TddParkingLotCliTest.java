package com.oocl.cultivation.test;


import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TddParkingLotCliTest {
    //Given parking boy,parking lot,car
    //When customer give a car to parking boy to park and fetch
    //Then the car fetched should be same as the car parked.
    @Test
    public void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //Then
        assertSame(car,fetchedCar);

    }

    //Given parking boy,parking lot,cars
    //When customer give multiple cars to parking boy to park and fetch
    //Then the car fetched should be same as the car parked.
    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    //Given parking boy, wrong ticket
    //When parking boy fetch car
    //Then no car should be fetched
    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    //Given parking boy, ticket have been used
    //When parking boy fetch car
    //Then no car should be fetched
    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    //Given parking boy, parking lot with no position, car
    //When parking boy park the car
    //Then car should not be parked
    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }

    //Given parking boy, wrong ticket
    //When parking boy fetch car
    //Then parking boy output error msg "Unrecognized parking ticket."
    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    //Given parking boy, no ticket
    //When parking boy fetch car
    //Then  no car should be fetched
    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    //Given parking boy, no ticket
    //When parking boy fetch car
    //Then parking boy output error msg "Please provide your parking ticket."

    //Given parking boy, parking lot with no position
    //When parking boy fetch car
    //Then parking boy output error msg "Not enough position."

    //Given parking boy, parking lot 1 with no position managed by parking boy, parking lot 2 with position managed by parking boy
    //When parking boy park and fetch car
    //Then the car fetched should be fetched from parking lot 2.

    //Given smart parking boy, parking lot 1 managed by parking boy, parking lot 2 with more empty position managed by parking boy
    //When parking boy park and fetch car
    //Then the car fetched should be fetched from parking lot 2.

    //Given super parking boy, parking lot 1 managed by parking boy, parking lot 2 with larger available position rate managed by parking boy
    //When parking boy park and fetch car
    //Then the car fetched should be fetched from parking lot 2.

    //Given manager, parking boy managed by manager, car
    //When manager specify parking boy to park and fetch car
    //Then car should be fetched from parking lot managed by parking boy

    //Given manager, parking lot 1 with no position managed by parking boy, parking lot 2 with position managed by parking boy
    //When manager park and fetch car
    //Then the car fetched should be fetched from parking lot 2.

    //Given manager, parking boy
    //When manager tells parking boy park and parking boy fail to do
    //Then manager should output error msg


}
