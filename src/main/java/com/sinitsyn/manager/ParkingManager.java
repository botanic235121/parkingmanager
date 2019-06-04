package com.sinitsyn.manager;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;
import com.sinitsyn.manager.parkingspaces.CarParkingSpace;
import com.sinitsyn.manager.parkingspaces.TruckParkingSpace;

/**
 * Main class for parking management. Includes methods to put vehicle to parking
 * and delete vehicle from parking.
 */
public class ParkingManager {

    /**
     * Manage car parking spaces.
     */
    private final CarParkingSpace carParkingSpace;
    /**
     * Manage truck parking spaces.
     */
    private final TruckParkingSpace truckParkingSpace;

    /**
     * Constructs an empty parking with the specified initial capacity.
     *
     * @param amountOfCarParkingSpaces   the initial capacity car parking spaces of the parking.
     * @param amountOfTruckParkingSpaces the initial capacity truck parking spaces of the parking.
     */
    public ParkingManager(int amountOfCarParkingSpaces, int amountOfTruckParkingSpaces) {
        carParkingSpace = new CarParkingSpace(amountOfCarParkingSpaces);
        truckParkingSpace = new TruckParkingSpace(amountOfTruckParkingSpaces);
    }

    /**
     * Append new car or truck to parking.
     *
     * @param vehicle new car or truck, that will be put to parking.
     */

    public void putVehicleIntoParking(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            carParkingSpace.addCarToParking((Car) vehicle);
        } else if (vehicle instanceof Truck) {
            if (truckParkingSpace.searchFreeSpace() > -1) {
                truckParkingSpace.addTruckToParking((Truck) vehicle);
            } else {
                carParkingSpace.addTruckToDoubleCarParkingSpaces((Truck) vehicle);
            }
        }
    }

    /**
     * Delete car or truck from parking.
     *
     * @param vehicle is car or truck, that want to leave from parking.
     */
    public void deleteVehicleFromParking(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            carParkingSpace.deleteCarFromParking((Car) vehicle);
        } else if (vehicle instanceof Truck) {
            truckParkingSpace.deleteTruckFromParking((Truck) vehicle);
            carParkingSpace.deleteTruckFromDoubleCarParkingSpaces((Truck) vehicle);
        }
    }

    /**
     * Getter
     *
     * @return carParkingSpace.
     */

    public CarParkingSpace getCarParkingSpace() {
        return carParkingSpace;
    }

    /**
     * Getter
     *
     * @return truckParkingSpace.
     */
    public TruckParkingSpace getTruckParkingSpace() {
        return truckParkingSpace;
    }
}
