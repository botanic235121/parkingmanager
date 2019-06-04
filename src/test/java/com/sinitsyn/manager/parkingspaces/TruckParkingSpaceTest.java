package com.sinitsyn.manager.parkingspaces;


import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TruckParkingSpaceTest {

    public TruckParkingSpaceTest() {
    }


    @Test
    public void addTruckToParkingWillAddTruckIfThereIsEnoughSpace() {
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(2);
        Truck truckExpected = new Truck();
        truckParkingSpace.addTruckToParking(truckExpected);
        Vehicle truckActual = truckParkingSpace.getTrucksList()[0];

        assertEquals(truckExpected, truckActual);
    }
    @Test
    public void addTruckToParkingWillNotAddTruckIfThereIsNotEnoughSpace() {
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(1);
        Truck truck1 = new Truck();
        Truck truck2 = new Truck();
        truckParkingSpace.addTruckToParking(truck1);
        truckParkingSpace.addTruckToParking(truck2);
        List<Vehicle> truckList = Arrays.asList(truckParkingSpace.getTrucksList());

        assertFalse(truckList.contains(truck2));
    }

    @Test
    public void deleteTruckFromParkingWillDeleteExistedTruck(){
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(1);
        Truck truck1 = new Truck();
        truckParkingSpace.addTruckToParking(truck1);

        assertEquals(truck1, truckParkingSpace.getTrucksList()[0]);

        truckParkingSpace.deleteTruckFromParking(truck1);

        assertNull(truckParkingSpace.getTrucksList()[0]);

    }

}