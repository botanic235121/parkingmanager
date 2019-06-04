package com.sinitsyn.manager;

import com.sinitsyn.manager.entity.Car;
import com.sinitsyn.manager.entity.Truck;
import com.sinitsyn.manager.entity.Vehicle;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingLifeCycle {
    private ArrayList<Vehicle> mockVehiclesReadyToParking;
    private volatile ArrayList<Vehicle> vehiclesAlreadyInParking;
    private ParkingManager parkingManager;


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
            for (int i = 0; i < 200; i++) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Vehicle vehicle = mockVehiclesReadyToParking.get((int) (Math.random() * mockVehiclesReadyToParking.size()));
                parkingManager.putVehicleIntoParking(vehicle);
                vehiclesAlreadyInParking.add(vehicle);
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < 200; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
