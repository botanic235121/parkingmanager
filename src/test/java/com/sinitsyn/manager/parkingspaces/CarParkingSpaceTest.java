package com.sinitsyn.manager.parkingspaces;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CarParkingSpaceTest {

    public CarParkingSpaceTest() {
    }

    @Test
    public void addCarToParkingWillAddCarIfThereIsEnoughSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(2);
        Car carExpected = new Car();
        carParkingSpace.addCarToParking(carExpected);
        Vehicle carActual = carParkingSpace.getCarsAndTrucksList()[0];

        assertEquals(carExpected, carActual);

    }

    @Test
    public void addCarToParkingWillNotAddCarIfThereIsNotEnoughSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(1);
        Car carAdded = new Car();
        Car carNotAdded = new Car();
        carParkingSpace.addCarToParking(carAdded);
        carParkingSpace.addCarToParking(carNotAdded);
        List<Vehicle> carList = Arrays.asList(carParkingSpace.getCarsAndTrucksList());

        assertFalse(carList.contains(carNotAdded));

    }


    @Test
    public void deleteCarFromParkingWillDeleteExistedCar() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(1);
        Car carDeleted = new Car();
        carParkingSpace.addCarToParking(carDeleted);

        assertEquals(carDeleted, carParkingSpace.getCarsAndTrucksList()[0]);

        carParkingSpace.deleteCarFromParking(carDeleted);

        assertNull(carParkingSpace.getCarsAndTrucksList()[0]);
    }

    @Test
    public void addTruckToDoubleCarParkingSpacesWillAddTruckIfThereIsFreeDoubleSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(3);
        Car car = new Car();
        carParkingSpace.addCarToParking(car);
        Truck truckExpected = new Truck();
        carParkingSpace.addTruckToDoubleCarParkingSpaces(truckExpected);
        Truck truckActual = (Truck) carParkingSpace.getCarsAndTrucksList()[1];

        assertEquals(truckExpected, truckActual);

        assertEquals(truckExpected, carParkingSpace.getCarsAndTrucksList()[2]);
    }

    @Test
    public void addTruckToDoubleCarParkingSpacesWillNotAddTruckIfThereIsNotFreeDoubleSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(2);
        Car car = new Car();
        carParkingSpace.addCarToParking(car);
        Truck truckNotAdded = new Truck();
        carParkingSpace.addTruckToDoubleCarParkingSpaces(truckNotAdded);
        List<Vehicle> vehicleList = Arrays.asList(carParkingSpace.getCarsAndTrucksList());

        assertFalse(vehicleList.contains(truckNotAdded));
    }
    @Test
    public void addTruckToDoubleCarParkingSpacesWillNotAddTruckIfThereIsTwoFreeSingleCarSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(3);
        Car car1 = new Car();
        carParkingSpace.addCarToParking(car1);
        Car car2 = new Car();
        carParkingSpace.addCarToParking(car2);
        carParkingSpace.deleteCarFromParking(car1);
        Truck truckNotAdded = new Truck();
        carParkingSpace.addTruckToDoubleCarParkingSpaces(truckNotAdded);
        List<Vehicle> vehicleList = Arrays.asList(carParkingSpace.getCarsAndTrucksList());

        assertFalse(vehicleList.contains(truckNotAdded));
    }


    @Test
    public void deleteTruckFromDoubleCarParkingSpacesWillDeleteExistedTruck() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(3);
        Truck truckDeleted = new Truck();
        carParkingSpace.addTruckToDoubleCarParkingSpaces(truckDeleted);

        assertEquals(truckDeleted, carParkingSpace.getCarsAndTrucksList()[0]);

        carParkingSpace.deleteTruckFromDoubleCarParkingSpaces(truckDeleted);

        assertNull(carParkingSpace.getCarsAndTrucksList()[0]);
    }
}