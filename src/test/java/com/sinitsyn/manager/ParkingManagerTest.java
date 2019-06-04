package com.sinitsyn.manager;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingManagerTest {
    public ParkingManagerTest() {
    }

    @Test
    public void putVehicleIntoParkingWillAddCarToCarParkingSpace() {
        ParkingManager parkingManager = new ParkingManager(2, 2);
        Vehicle carExpected = new Car();
        parkingManager.putVehicleIntoParking(carExpected);
        Vehicle carActual = parkingManager.getCarParkingSpace().getCarsAndTrucksList()[0];

        assertEquals(carExpected, carActual);
    }

    @Test
    public void putVehicleIntoParkingWillAddTruckToTruckParkingSpaceIfThereIsExistEnoughSpace() {
        ParkingManager parkingManager = new ParkingManager(2, 2);
        Vehicle truckExpected = new Truck();
        parkingManager.putVehicleIntoParking(truckExpected);
        Vehicle truckActual = parkingManager.getTruckParkingSpace().getTrucksList()[0];

        assertEquals(truckExpected, truckActual);
    }

    @Test
    public void putVehicleIntoParkingWillAddTruckToCarParkingSpaceIfThereIsNotExistEnoughSpaceInTruckParking() {
        ParkingManager parkingManager = new ParkingManager(2, 2);
        Vehicle truck1 = new Truck();
        parkingManager.putVehicleIntoParking(truck1);
        Vehicle truck2 = new Truck();
        parkingManager.putVehicleIntoParking(truck2);
        Vehicle truckAddedToCarParking = new Truck();
        parkingManager.putVehicleIntoParking(truckAddedToCarParking);
        List<Vehicle> truckList = Arrays.asList(parkingManager.getTruckParkingSpace().getTrucksList());

        assertFalse(truckList.contains(truckAddedToCarParking));

        List<Vehicle> carkList = Arrays.asList(parkingManager.getCarParkingSpace().getCarsAndTrucksList());

        assertTrue(carkList.contains(truckAddedToCarParking));

    }

    @Test
    public void deleteVehicleFromParkingWillDeleteCarFromCarParkingSpace() {
        ParkingManager parkingManager = new ParkingManager(2, 2);
        Vehicle carExpected = new Car();
        parkingManager.putVehicleIntoParking(carExpected);

        assertEquals(carExpected, parkingManager.getCarParkingSpace().getCarsAndTrucksList()[0]);

        parkingManager.deleteVehicleFromParking(carExpected);

        assertNull(parkingManager.getCarParkingSpace().getCarsAndTrucksList()[0]);
    }

    @Test
    public void deleteVehicleFromParkingWillDeleteTruckFromTruckParkingSpace() {
        ParkingManager parkingManager = new ParkingManager(2, 2);
        Vehicle truckExpected = new Truck();
        parkingManager.putVehicleIntoParking(truckExpected);

        assertEquals(truckExpected, parkingManager.getTruckParkingSpace().getTrucksList()[0]);

        parkingManager.deleteVehicleFromParking(truckExpected);

        assertNull(parkingManager.getTruckParkingSpace().getTrucksList()[0]);
    }

    @Test
    public void deleteVehicleFromParkingWillDeleteTruckFromCarParkingSpace() {
        ParkingManager parkingManager = new ParkingManager(2, 1);
        Vehicle truck1 = new Truck();
        parkingManager.putVehicleIntoParking(truck1);
        Vehicle truckExpected = new Truck();
        parkingManager.putVehicleIntoParking(truckExpected);

        assertEquals(truckExpected, parkingManager.getCarParkingSpace().getCarsAndTrucksList()[0]);

        parkingManager.deleteVehicleFromParking(truckExpected);

        assertNull(parkingManager.getCarParkingSpace().getCarsAndTrucksList()[0]);
    }


}