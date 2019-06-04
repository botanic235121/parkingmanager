package com.sinitsyn.manager.parkingspaces;

import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;

import java.util.logging.Logger;

/**
 * This class contains various methods for manipulating truck parking spaces (such as
 * add truck to parking space and delete truck from parking space).
 */

public class TruckParkingSpace {

    private static final Logger LOGGER = Logger.getLogger(TruckParkingSpace.class.getSimpleName());
    /**
     * The list for saving information about parking spaces. If current position in list is null,
     * it means that parking space is free. Otherwise in this position located truck.
     */
    private final Vehicle[] trucksList;

    public TruckParkingSpace(int amountOfTruckParkingSpaces) {
        this.trucksList = new Truck[amountOfTruckParkingSpaces];
    }

    /**
     * Return position of first empty space from trucksList,
     * return -1 if trucksList is full;
     */
    public int searchFreeSpace() {
        for (int i = 0; i < trucksList.length; i++) {
            if (trucksList[i] == null) return i;
        }
        return -1;
    }

    /**
     * Append new truck to the truckList.
     *
     * @param arriveTruck is new truck, that will be add to parking space.
     */
    public void addTruckToParking(Truck arriveTruck) {
        synchronized (this.trucksList) {
            int position = searchFreeSpace();
            if (position == -1) {
                LOGGER.warning("There are no free truck parking spaces! Truck details:  " + arriveTruck);
            } else {
                trucksList[position] = arriveTruck;
                LOGGER.info(arriveTruck + " was successfully added to parking.");
            }
        }
    }

    /**
     * Remove truck from the truckList.
     *
     * @param departureTruck is truck, that want to leave from parking space.
     */

    public void deleteTruckFromParking(Truck departureTruck) {
        synchronized (this.trucksList) {
            for (int i = 0; i < trucksList.length; i++) {
                if (departureTruck.equals(trucksList[i])) {
                    trucksList[i] = null;
                    LOGGER.info(departureTruck + " was successfully deleted from parking.");
                }
            }
        }
    }

    /**
     * Getter.
     *
     * @return clone of truckList
     */

    public Vehicle[] getTrucksList() {
        return trucksList.clone();
    }
}
