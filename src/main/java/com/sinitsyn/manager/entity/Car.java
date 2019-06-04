package com.sinitsyn.manager.entity;

import java.util.Objects;

/**
 * This class is implementation of the Vehicle interface,
 * allows create entity Car.
 */
public class Car implements Vehicle {

    /**
     * Count of created cars.
     */
    private static long count;

    /**
     * The id of car.
     */
    private long id;

    public Car() {
        count++;
        id = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                '}';
    }
}
