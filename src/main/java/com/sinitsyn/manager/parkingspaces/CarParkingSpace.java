package com.sinitsyn.manager.parkingspaces;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;

import java.util.logging.Logger;

/**
 * This class contains various methods for manipulating car parking spaces (such as
 * add car to parking space and delete car from parking space). This class also contains
 * methods for add truck to double car parking space and delete truck from this space.
 */
public class CarParkingSpace {

    private static final Logger LOGGER = Logger.getLogger(CarParkingSpace.class.getSimpleName());

    /**
     * The list for saving information about parking spaces. If current position in list is null,
     * it means that parking space is free. Otherwise in this position located car or truck.
     */
    private final Vehicle[] carsAndTrucksList;

    public CarParkingSpace(int amountOfCarParkingSpaces) {
        this.carsAndTrucksList = new Vehicle[amountOfCarParkingSpaces];
    }

    /**
     * Return position of first empty space from carsAndTrucksList,
     * return -1 if carsAndTrucksList is full;
     */
    private int searchFreeSpace() {
        for (int i = 0; i < carsAndTrucksList.length; i++) {
            if (carsAndTrucksList[i] == null) return i;
        }
        return -1;
    }

    /**
     * Append new car to the carsAndTrucksList.
     *
     * @param arriveCar is new car, that will be add to parking space.
     */

    public void addCarToParking(Car arriveCar) {
        synchronized (this.carsAndTrucksList) {
            int position = searchFreeSpace();
            if (position == -1) {
                LOGGER.warning("There are no free car parking spaces! Car details: " + arriveCar);
            } else {
                carsAndTrucksList[position] = arriveCar;
                LOGGER.info(arriveCar + " was successfully added to parking.");
            }
        }
    }

    /**
     * Remove car from the carsAndTrucksList.
     *
     * @param departureCar is car, that want to leave from parking space.
     */
    public void deleteCarFromParking(Car departureCar) {
        synchronized (this.carsAndTrucksList) {
            for (int i = 0; i < carsAndTrucksList.length; i++) {
                if (departureCar.equals(carsAndTrucksList[i])) {
                    carsAndTrucksList[i] = null;
                    LOGGER.info(departureCar + " was successfully deleted from parking.");
                }
            }
        }
    }

    /**
     * Return first position of first empty double spaces,
     * otherwise return -1.
     */
    private int searchFreeDoubleCarSpaces() {
        for (int i = 0; i < carsAndTrucksList.length - 1; i++) {
            if (carsAndTrucksList[i] == null && carsAndTrucksList[i + 1] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Append new truck to the carsAndTrucksList if exist free double car spaces .
     *
     * @param arriveTruck is new truck, that will be add to parking space.
     */
    public void addTruckToDoubleCarParkingSpaces(Truck arriveTruck) {
        synchronized (this.carsAndTrucksList) {
            int position = searchFreeDoubleCarSpaces();
            if (position == -1) {
                LOGGER.warning("There are no free double car parking spaces! Truck details: " + arriveTruck);
            } else {
                carsAndTrucksList[position] = arriveTruck;
                carsAndTrucksList[position + 1] = arriveTruck;
                LOGGER.info(arriveTruck + " was successfully added to parking.");
            }
        }
    }

    /**
     * Remove truck from the carsAndTrucksList.
     *
     * @param departureTruck is truck, that want to leave from parking space.
     */
    public void deleteTruckFromDoubleCarParkingSpaces(Truck departureTruck) {
        synchronized (this.carsAndTrucksList) {
            for (int i = 0; i < carsAndTrucksList.length; i++) {
                if (departureTruck.equals(carsAndTrucksList[i])) {
                    carsAndTrucksList[i] = null;
                    LOGGER.info(departureTruck + " was successfully deleted from parking.");
                }
            }
        }
    }

    /**
     * Getter.
     *
     * @return clone of carsAndTrucksList
     */
    public Vehicle[] getCarsAndTrucksList() {
        return carsAndTrucksList.clone();
    }
}
