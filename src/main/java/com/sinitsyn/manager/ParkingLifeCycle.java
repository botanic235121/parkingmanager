package com.sinitsyn.manager;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ParkingLifeCycle {
    private ArrayList<Vehicle> mockVehiclesReadyToParking;
    private volatile ArrayList<Vehicle> vehiclesAlreadyInParking;
    private ParkingManager parkingManager;
    private static final Logger LOGGER = Logger.getLogger(ParkingLifeCycle.class.getSimpleName());


    public ParkingLifeCycle() {
        parkingManager = new ParkingManager(5, 5);
        mockVehiclesReadyToParking = new ArrayList<>();
        vehiclesAlreadyInParking = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockVehiclesReadyToParking.add(new Truck());
            mockVehiclesReadyToParking.add(new Car());
        }
    }

    public static void main(String[] args) {
        ParkingLifeCycle plc = new ParkingLifeCycle();
        plc.executeThreadOfVehicle();
    }

    private void executeThreadOfVehicle() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    LOGGER.warning(String.format("Unexpected exception. Details: \n %s",e.getMessage()));
                }
                Vehicle vehicle = mockVehiclesReadyToParking.get((int) (Math.random() * mockVehiclesReadyToParking.size()));
                mockVehiclesReadyToParking.remove(vehicle);
                parkingManager.putVehicleIntoParking(vehicle);
                vehiclesAlreadyInParking.add(vehicle);
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    LOGGER.warning(String.format("Unexpected exception. Details: \n %s",e.getMessage()));
                }
                if (!vehiclesAlreadyInParking.isEmpty()) {
                    Vehicle vehicle = vehiclesAlreadyInParking.get(0);
                    parkingManager.deleteVehicleFromParking(vehicle);
                    vehiclesAlreadyInParking.remove(vehicle);
                }
            }
        });


        executorService.shutdown();
    }
}
